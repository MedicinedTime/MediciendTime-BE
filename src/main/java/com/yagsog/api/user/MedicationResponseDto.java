package com.yagsog.api.user;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MedicationResponseDto {
    private List<MedicationsDto> medications;
    private int total;

    public MedicationResponseDto(List<MedicationsDto> medications) {
        this.medications = medications;
        this.total = medications.size(); // 배열 길이로 total 자동 계산
    }
}

