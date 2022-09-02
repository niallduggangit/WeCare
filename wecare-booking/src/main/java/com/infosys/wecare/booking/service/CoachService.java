package com.infosys.wecare.booking.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.wecare.booking.dtos.CoachDto;

@FeignClient("CoachMs")
public interface CoachService {

	@RequestMapping(value="/coaches/{coachId}")
	CoachDto getCoach(@PathVariable("coachId") String coachId);
}
