package com.yagsog.api.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagsog.api.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
public class UserController {
    private UserService userService;
    private Map<String, String> updateInfo = new HashMap<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getUser(@RequestHeader(value = "USER_COOKIE", required = false) String cookieId,
                                               HttpServletResponse response) {
        System.out.println("1. cookie : " + cookieId);
        User user = null;
        // 쿠키가 없으면 새로운 세션 ID 생성
        if (cookieId == null) {
            System.out.println("2. cookie : " + cookieId);
            cookieId = UUID.randomUUID().toString(); // 랜덤한 값 생성
            System.out.println("3. cookie : " + cookieId);
            Cookie cookie = new Cookie("USER_COOKIE", cookieId);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60); // 1시간 동안 유지
            System.out.println("4. cookie : " + cookieId);
            response.addCookie(cookie);
            System.out.println("5. cookie : " + cookieId);
            user = userService.createUser(cookieId);
            System.out.println("6. cookie : " + cookieId);
        } else {
            // 쿠키 ID로 사용자 식별
            user = userService.getUser(cookieId);
            System.out.println("7. cookie : " + cookieId);
        }

        ApiResponse apiResponse = new ApiResponse("초기 페이지 접속", user);
        return ResponseEntity.ok(apiResponse);

    }

    @PatchMapping("/name")
    public ResponseEntity<Map<String, String>> updateName(@CookieValue(value = "USER_COOKIE", required = false) String cookieId,
                                             @RequestBody Map<String, String> requestData) {
        String name = requestData.get("name");
        updateInfo.put("name", name);
        userService.updateUserInfo(cookieId, updateInfo);

        Map<String, String> response = new HashMap<>();
        response.put("name", name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    public ResponseEntity<Map<String, String>> getName(@CookieValue(value = "USER_COOKIE", required = false) String cookieId) {
        String name = userService.getUserInfo(cookieId, "name");

        Map<String, String> response = new HashMap<>();
        response.put("name", name);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/age")
    public ResponseEntity<Map<String, String>> updateAge(@CookieValue(value = "USER_COOKIE", required = false) String cookieId,
                                            @RequestBody Map<String, String> requestData) {
        String age = requestData.get("age");
        updateInfo.put("age", age);
        userService.updateUserInfo(cookieId, updateInfo);

        Map<String, String> response = new HashMap<>();
        response.put("age", age);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/age")
    public ResponseEntity<Map<String, String>> getAge(@CookieValue(value = "USER_COOKIE", required = false) String cookieId) {
        String age = userService.getUserInfo(cookieId, "age");

        Map<String, String> response = new HashMap<>();
        response.put("age", age);
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/gender")
    public ResponseEntity<Map<String, String>> updateGender(@CookieValue(value = "USER_COOKIE", required = false) String cookieId,
                                               @RequestBody Map<String, String> requestData) {
        String gender = requestData.get("gender");
        updateInfo.put("gender", gender);
        userService.updateUserInfo(cookieId, updateInfo);

        Map<String, String> response = new HashMap<>();
        response.put("gender", gender);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gender")
    public ResponseEntity<Map<String, String>> getGender(@CookieValue(value = "USER_COOKIE", required = false) String cookieId) {
        String gender = userService.getUserInfo(cookieId, "gender");

        Map<String, String> response = new HashMap<>();
        response.put("gender", gender);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/medications")
    public ResponseEntity<MedicationResponseDto> updateMedications(@CookieValue(value = "USER_COOKIE", required = false) String cookieId,
                                                                 @RequestBody String requestData) throws JsonProcessingException {

            System.out.println("/medications , requestData : " + requestData);

            updateInfo.put("medications", requestData);
            userService.updateUserInfo(cookieId, updateInfo);

            ObjectMapper objectMapper = new ObjectMapper();
            MedicationsRequestDto request = objectMapper.readValue(requestData, MedicationsRequestDto.class);

            // 변환된 데이터 확인
            List<MedicationsDto> medications = request.getMedications();
            MedicationResponseDto response = new MedicationResponseDto(medications);

            return ResponseEntity.ok(response);

//            Map<String, String> resultData = new HashMap<>();
//            // 변환된 데이터 확인
//            for (MedicationsDto med : request.getMedications()) {
//                Map<String, String> medicationsData = new HashMap<>();
//                medicationsData.put("type", med.getType());
//                medicationsData.put("day", med.getDay());
//                medicationsData.put("frequency", med.getFrequency());
//                resultData.put()
//            }
//            resultData.put("total", String.valueOf(request.getMedications().size()));
//
//            return ResponseEntity.ok(resultData);
    }

    @GetMapping("/medications")
    public ResponseEntity<Map<String, String>> getMedications(@CookieValue(value = "USER_COOKIE", required = false) String cookieId) {
        String medications = userService.getUserInfo(cookieId, "medications");

        Map<String, String> response = new HashMap<>();
        response.put("medications", medications);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/personal-info")
    public ResponseEntity<Map<String, String>> getUserInfo(@CookieValue(value = "USER_COOKIE", required = false) String cookieId) {
        Map<String, String> userInfo = userService.getAllUserInfo(cookieId);

        // 샘플 데이터 생성 (실제 DB에서 가져와야 함)
//        List<MedicationDto> medications = Arrays.asList(
//                new MedicationDto() {{ setType("아스피린"); setDay(3); setFrequency(2); }},
//                new MedicationDto() {{ setType("타이레놀"); setDay(1); setFrequency(2); }}
//        );
//
//        UserResponseDto response = new UserResponseDto("김춘자", "80", "남자", medications);
//        return ResponseEntity.ok(response);

        return ResponseEntity.ok(userInfo);
    }

}