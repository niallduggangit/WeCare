package com.infosys.wecare.booking.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.wecare.booking.dtos.UserDto;

@FeignClient("UserMs")
public interface UserService {

	@RequestMapping(value="/users/{userId}")
	UserDto getUser(@PathVariable("userId") String userId);
}
