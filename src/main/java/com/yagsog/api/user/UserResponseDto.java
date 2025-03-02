package com.yagsog.api.user;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String age;
    private String gender;
    private List<MedicationsDto> medications;

    public UserResponseDto(String name, String age, String gender, List<MedicationsDto> medications) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medications = medications;
    }
}
