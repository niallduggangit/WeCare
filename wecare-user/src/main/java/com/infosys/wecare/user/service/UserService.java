package com.infosys.wecare.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.wecare.user.dtos.LoginDto;
import com.infosys.wecare.user.dtos.UserDto;
import com.infosys.wecare.user.entities.User;
import com.infosys.wecare.user.exceptions.MessageConstants;
import com.infosys.wecare.user.exceptions.WeCareException;
import com.infosys.wecare.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private UserRepository userRepository;

    public String createUser(UserDto user) throws WeCareException {
        return Optional.ofNullable(
        		userRepository
        		.saveAndFlush(modelMapper.map(user, User.class)))
        		.orElseThrow(IllegalArgumentException::new)
        		.getId();
    }

    public Boolean loginUser(LoginDto loginDto) throws WeCareException {
        return userRepository
        		.findById(loginDto.getId())
        		.orElseThrow(() -> new WeCareException(environment.getProperty(MessageConstants.USER_NOT_FOUND.toString())))
        		.getPassword()
        		.equals(loginDto.getPassword());
    }

    public UserDto getUser(String userId) throws WeCareException {
        return userRepository
        		.findById(userId)
        		.map(user -> modelMapper.map(user, UserDto.class))
        		.orElseThrow(() -> new WeCareException(environment.getProperty(MessageConstants.USER_NOT_FOUND.toString())));
    }

    public List<UserDto> getUserList() {
        return userRepository
        		.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
    
	public void exists(String userId) throws WeCareException {
        if(!userRepository.existsById(userId))
        	throw new WeCareException(environment.getProperty(MessageConstants.USER_NOT_FOUND.toString())); 
    }
}
