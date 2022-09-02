package com.infosys.wecare.booking.service;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.wecare.booking.dtos.CoachDto;
import com.infosys.wecare.booking.dtos.UserDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class BookingHystrixService {

	@Autowired
	UserService userService;
	@Autowired
	CoachService coachService;
	
	@HystrixCommand(fallbackMethod="getUserFallback")
	public Future<CoachDto> getCoach(String coachId) {
		return new AsyncResult<CoachDto>() {
			@Override
			public CoachDto invoke() {
				return coachService.getCoach(coachId);
			}
		};
	}
	
	@HystrixCommand
	public Future<UserDto> getUser(String userId) {
		return new AsyncResult<UserDto>(){
			@Override
			public UserDto invoke() {
				return userService.getUser(userId);
			}
		};
	}
	
    public CoachDto getUserFallback(String coachId) {
    	return new CoachDto();
    }
}
