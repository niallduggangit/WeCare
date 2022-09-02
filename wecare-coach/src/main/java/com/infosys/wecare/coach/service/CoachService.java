package com.infosys.wecare.coach.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.wecare.coach.dtos.CoachDto;
import com.infosys.wecare.coach.dtos.LoginDto;
import com.infosys.wecare.coach.entities.Coach;
import com.infosys.wecare.coach.exceptions.MessageConstants;
import com.infosys.wecare.coach.exceptions.WeCareException;
import com.infosys.wecare.coach.repository.CoachRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CoachService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private CoachRepository coachRepository;

    public String createCoach(CoachDto coach) throws WeCareException {
        return Optional.ofNullable(coachRepository
        		.saveAndFlush(modelMapper.map(coach, Coach.class)))
        		.orElseThrow(IllegalArgumentException::new)
        		.getId();
    }

    public Boolean loginCoach(LoginDto loginDto) throws WeCareException {
        return coachRepository
        		.findById(loginDto.getId())
        		.orElseThrow(() -> new WeCareException(environment.getProperty(MessageConstants.COACH_NOT_FOUND.toString())))
        		.getPassword()
        		.equals(loginDto.getPassword());
    }

    public CoachDto getCoach(String coachId) throws WeCareException {
        return coachRepository
	        	.findById(coachId)
	        	.map(coach -> modelMapper.map(coach, CoachDto.class))
	        	.orElseThrow(() -> new WeCareException(environment.getProperty(MessageConstants.COACH_NOT_FOUND.toString())));   	
    }

    public List<CoachDto> getCoachList() {
        return coachRepository.findAll()
                .stream()
                .map(coach -> modelMapper.map(coach, CoachDto.class))
                .toList();
    }

	public void getIdIfExists(String coachId) throws WeCareException {
    	if(!coachRepository.existsById(coachId))
    		throw new WeCareException(environment.getProperty(MessageConstants.COACH_NOT_FOUND.toString())); 
    }
}
