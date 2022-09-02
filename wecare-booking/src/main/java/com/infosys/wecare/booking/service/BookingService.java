package com.infosys.wecare.booking.service;


import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.wecare.booking.dtos.BookingDto;
import com.infosys.wecare.booking.dtos.CoachDto;
import com.infosys.wecare.booking.dtos.SlotAndDateDto;
import com.infosys.wecare.booking.dtos.UserDto;
import com.infosys.wecare.booking.entities.Booking;
import com.infosys.wecare.booking.exceptions.MessageConstants;
import com.infosys.wecare.booking.exceptions.WeCareException;
import com.infosys.wecare.booking.repository.BookingRepository;
import com.infosys.wecare.booking.utility.MailUtility;

@Service
public class BookingService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MailUtility mailUtility;
    @Autowired
    private BookingHystrixService bookingHystrixService;


    public Boolean bookAppointment(BookingDto bookingDto) throws WeCareException, InterruptedException, ExecutionException {
        Booking newAppointment = modelMapper.map(bookingDto, Booking.class);
                
        Future<CoachDto> futureCoach = bookingHystrixService.getCoach(bookingDto.getCoachId());
          
        Future<UserDto> futureUser = bookingHystrixService.getUser(bookingDto.getUserId());
        
        CoachDto coach = futureCoach.get();
        UserDto user = futureUser.get();
        
        //Annoyed about this why Java doesn't allow to throw checked exceptions inside throws statement.
        if(bookingRepository
        	.findByUserIdAndAppointmentDateAndSlot(bookingDto.getUserId(), bookingDto.getAppointmentDate(), bookingDto.getSlot())
        	.isPresent())
        	throw new WeCareException(environment.getProperty(MessageConstants.BOOKING_ALREADY_EXISTS.toString()));

        Booking newBooking = bookingRepository.save(newAppointment);
        mailUtility.sendSchedulingEmail(user.getName(), coach.getName(),
                user.getEmail(), newBooking.getId(), newBooking.getSlot().toString(), newBooking.getAppointmentDate());
        return true;
    }

    public Boolean rescheduleAppointment(Long bookingId, SlotAndDateDto slotAndDate) throws WeCareException, InterruptedException, ExecutionException {
        Booking rescheduleBooking = 
        		bookingRepository
        		.findById(bookingId)
        		.orElseThrow(() -> new WeCareException(MessageConstants.NO_SUCH_BOOKING_EXISTS.toString()));

        if(bookingRepository.findByUserIdAndAppointmentDateAndSlot(rescheduleBooking.getUserId(), slotAndDate.getAppointmentDate(), slotAndDate.getSlot())
        		.isPresent())
        	throw new WeCareException(MessageConstants.BOOKING_ALREADY_EXISTS.toString());

        rescheduleBooking.setAppointmentDate(slotAndDate.getAppointmentDate());
        rescheduleBooking.setSlot(slotAndDate.getSlot());
        rescheduleBooking = bookingRepository.save(rescheduleBooking);

        Future<CoachDto> futureCoach = bookingHystrixService.getCoach(rescheduleBooking.getCoachId());
        
        Future<UserDto> futureUser = bookingHystrixService.getUser(rescheduleBooking.getUserId());
        
        CoachDto coach = futureCoach.get();
        UserDto user = futureUser.get();

        mailUtility.sendReschedulingEmail(user.getName(), coach.getName(),
                user.getEmail(), rescheduleBooking.getId(), rescheduleBooking.getSlot().toString(), rescheduleBooking.getAppointmentDate()
                );
        return true;
    }

    public void cancelAppointment(Long bookingId) throws WeCareException, InterruptedException, ExecutionException {
        Booking cancelBooking = bookingRepository
        		.findById(bookingId)
        		.orElseThrow(() -> new WeCareException(MessageConstants.NO_SUCH_BOOKING_EXISTS.toString()));
       
        bookingRepository.deleteById(bookingId);
        
        Future<CoachDto> futureCoach = bookingHystrixService.getCoach(cancelBooking.getCoachId());
        
        Future<UserDto> futureUser = bookingHystrixService.getUser(cancelBooking.getUserId());
        
        CoachDto coach = futureCoach.get();
        UserDto user = futureUser.get();

        mailUtility.sendCancellingEmail(user.getName(), coach.getName(),
                user.getEmail(), bookingId, cancelBooking.getSlot().toString(), cancelBooking.getAppointmentDate());
    }

    public BookingDto findByBookingId(Long bookingId) throws WeCareException {
        return bookingRepository
        		.findById(bookingId)
        		.map(booking -> modelMapper.map(booking, BookingDto.class))
        		.orElseThrow(() -> new WeCareException(MessageConstants.NO_SUCH_BOOKING_EXISTS.toString()));
    }

    public List<BookingDto> findBookingsByUserId(String userId) {
    	bookingHystrixService.getUser(userId);
        return bookingRepository
        		.findByUserIdAndAppointmentDateGreaterThanToday(userId, LocalDate.now())
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    public List<BookingDto> findBookingsByCoachId(String coachId) {
    	bookingHystrixService.getCoach(coachId);
        return bookingRepository
        		.findByCoachIdAndAppointmentDateGreaterThanToday(coachId, LocalDate.now())
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }
    

}
