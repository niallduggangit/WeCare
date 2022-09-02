package com.infosys.wecare.user.entities;

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
@Entity(name="user_table")
public class User {
    @Id
    @GeneratedValue(generator = "user-generator")
    @GenericGenerator(name = "user-generator",
            strategy = "com.infosys.wecare.user.utility.UserIdGenerator")
    private String id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String password;
    private Long mobileNumber;
    private String email;
    private Integer pincode;
    private String city;
    private String state;
    private String country;



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
