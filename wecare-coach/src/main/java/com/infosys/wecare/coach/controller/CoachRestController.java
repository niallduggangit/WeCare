package com.infosys.wecare.coach.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.wecare.coach.dtos.CoachDto;
import com.infosys.wecare.coach.dtos.LoginDto;
import com.infosys.wecare.coach.exceptions.WeCareException;
import com.infosys.wecare.coach.service.CoachService;

@RestController
@RequestMapping("coaches")
@CrossOrigin
public class CoachRestController {
    @Autowired
    CoachService coachService;


    @PostMapping("/create")
    public ResponseEntity<String> createCoach(@RequestBody @Valid CoachDto coachDto) throws WeCareException {
        String createdId = coachService.createCoach(coachDto);
        return new ResponseEntity<>(createdId, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDto loginDto) throws WeCareException {

        Boolean verified = coachService.loginCoach(loginDto);

        return new ResponseEntity<>(verified, HttpStatus.OK);

    }

    @GetMapping("/{coachId}")
    public ResponseEntity<CoachDto> getCoachProfile(@PathVariable String coachId) throws WeCareException {
        CoachDto coachProfile = coachService.getCoach(coachId);
        return new ResponseEntity<>(coachProfile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoachDto>> showAllCoaches(){
        List<CoachDto> coachDtoList = coachService.getCoachList();
        return new ResponseEntity<>(coachDtoList, HttpStatus.OK);
    }
    
    @GetMapping("exists/{coachId")
    public ResponseEntity<String> getCoachIdIfExists(@PathVariable String coachId) throws WeCareException {
        coachService.getIdIfExists(coachId);
        return new ResponseEntity<>(coachId, HttpStatus.OK);
    }
}
