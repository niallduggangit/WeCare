package com.infosys.wecare.coach.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoachDto {

    private String id;
    @NotNull
    @Min(value = 3)
    @Max(value = 50)
    private String name;
    private Character gender;
    private LocalDate dateOfBirth;
    @NotNull
    @Min(value = 5)
    @Max(value = 10)
    private String password;
    @NotNull
    @Min(value = 10)
    @Max(value = 10)
    private Long mobileNumber;
    @NotNull
    @Min(value = 3)
    @Max(value = 50)
    private String speciality;
}
