package com.infosys.wecare.booking.dtos;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String id;
    @NotNull
    @Min(value = 3)
    @Max(value = 50)
    private String name;
    private String gender;
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
    @Email
    private String email;
    @NotNull
    @Min(value = 6)
    @Max(value = 6)
    private Integer pincode;
    @NotNull
    @Min(value = 3)
    @Max(value = 20)
    private String city;
    @NotNull
    @Min(value = 3)
    @Max(value = 20)
    private String state;
    @NotNull
    @Min(value = 3)
    @Max(value = 20)
    private String country;
}
