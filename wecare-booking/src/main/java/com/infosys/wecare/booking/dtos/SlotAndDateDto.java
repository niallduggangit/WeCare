package com.infosys.wecare.booking.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.sun.istack.NotNull;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SlotAndDateDto {
	@NotNull
    private LocalDate appointmentDate;
	@NotNull
    private Character slot;
}
