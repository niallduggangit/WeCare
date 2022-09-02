package com.infosys.wecare.coach.utility;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CoachIdGenerator implements IdentifierGenerator {
    private static int counter = 101;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        int id = counter++;
        LocalDateTime now = LocalDateTime.now();
        String value = "LC" + now.getDayOfMonth() + now.getMonthValue() + now.getYear() + now.getHour() + now.getMinute();
        return value + id;
    }
}
