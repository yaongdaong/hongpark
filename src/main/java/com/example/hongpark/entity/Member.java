package com.example.hongpark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    Long id;
    @Column
    String email;
    @Column
    String password;


    //public Member(Long id, String email, String password) {
    //    this.id = id;
    //    this.email = email;
    //    this.password = password;
    //}
    //
    //@Override
    //public String toString() {
    //    return "Member{" +
    //            "id=" + id +
    //            ", email='" + email + '\'' +
    //            ", password='" + password + '\'' +
    //            '}';
    //}


}
