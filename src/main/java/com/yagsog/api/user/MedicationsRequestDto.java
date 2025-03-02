package com.yagsog.api.user;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MedicationsRequestDto {
    private List<MedicationsDto> medications;

    public List<MedicationsDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationsDto> medications) {
        this.medications = medications;
    }
}

