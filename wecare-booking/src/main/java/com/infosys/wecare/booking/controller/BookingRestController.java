package com.infosys.wecare.booking.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.wecare.booking.dtos.BookingDto;
import com.infosys.wecare.booking.dtos.SlotAndDateDto;
import com.infosys.wecare.booking.exceptions.WeCareException;
import com.infosys.wecare.booking.service.BookingService;

@RestController
@RequestMapping("bookings")
@CrossOrigin
public class BookingRestController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/users/{userId}/booking/{coachId}")
    public ResponseEntity<Boolean> bookAppointment(@PathVariable String userId, @PathVariable String coachId,
                                                   @RequestBody SlotAndDateDto slotAndDate) throws WeCareException, InterruptedException, ExecutionException {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCoachId(coachId);
        bookingDto.setUserId(userId);
        bookingDto.setAppointmentDate(slotAndDate.getAppointmentDate());
        bookingDto.setSlot(slotAndDate.getSlot());
        return new ResponseEntity<>(bookingService.bookAppointment(bookingDto), HttpStatus.OK);
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Long bookingId,
                                                         @RequestBody SlotAndDateDto slotAndDate) throws WeCareException, InterruptedException, ExecutionException {
        return new ResponseEntity<>(bookingService.rescheduleAppointment(bookingId, slotAndDate), HttpStatus.OK);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long bookingId) throws WeCareException, InterruptedException, ExecutionException {
        bookingService.cancelAppointment(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
