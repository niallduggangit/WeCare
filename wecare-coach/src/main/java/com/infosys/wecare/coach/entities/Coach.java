package com.infosys.wecare.coach.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coach {
    @Id
    @GeneratedValue(generator = "coach-generator")
    @GenericGenerator(name = "coach-generator",
    strategy = "com.infosys.wecare.coach.utility.CoachIdGenerator")
    private String id;
    private String name;
    private Character gender;
    private LocalDate dateOfBirth;
    private String password;
    private Long mobileNumber;
    private String speciality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coach coach = (Coach) o;
        return id != null && Objects.equals(id, coach.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
