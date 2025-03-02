package com.yagsog.api.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public User createUser(String cookieId) {
        User newUser = new User(cookieId);
        return userRepository.save(newUser);
    }

    public User getUser(String cookieId) {
        return userRepository.findByCookieId(cookieId)
                .orElseThrow(() -> new IllegalArgumentException("getUser :: User not found"));
    }

    @Override
    public void updateUserInfo(String cookieId, Map<String, String> updateInfo) {
        User user = userRepository.findByCookieId(cookieId)
                .orElseThrow(() -> new IllegalArgumentException("updateUserInfo :: User not found"));

        updateInfo.forEach((key, value) -> {
            switch(key) {
                case "name":
                    user.setName((String) value);
                    break;
                case "age":
                    user.setAge((String) value);
                    break;
                case "gender":
                    user.setGender((String) value);
                    break;
                case "medications":
                    user.setMedications((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("updateUserInfo :: Invalid key: " + key);
            }
        });
    }


    // String -> Json convert
    @Override
    public String convertToJson(String str) throws JsonProcessingException {
        return objectMapper.writeValueAsString(str);
    }

    @Override
    public Optional<User> getUserByCookieId(String cookieId) {
        return Optional.empty();
    }

    @Override
    public String getUserInfo(String cookieId, String key) {
        User user = getUser(cookieId);

        switch (key) {
            case "name":
                return user.getName();
            case "age":
                return String.valueOf(user.getAge());
            case "gender":
                return user.getGender();
            case "medications":
                return user.getMedications();
            default:
                return null;
        }
    }

    @Override
    public Map<String, String> getAllUserInfo(String cookieId) {
        User user = getUser(cookieId);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("name", user.getName());
        userInfo.put("age", user.getAge());
        userInfo.put("gender", user.getGender());
        userInfo.put("medications", user.getMedications());

        return userInfo;
    }
}
