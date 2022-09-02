package com.infosys.wecare.booking.dtos;

import lombok.*;

import javax.persistence.*;

import com.sun.istack.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {

    private Long id;
    private String userId;
    private String coachId;
    private LocalDate appointmentDate;
    private Character slot;
}
