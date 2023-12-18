package com.example.hongpark.dto;

import com.example.hongpark.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private String password;

    //public MemberForm(String email, String password) {
    //    this.email = email;
    //    this.password = password;
    //}
    //
    //@Override
    //public String toString() {
    //    return "MemberFrom{" +
    //            "email='" + email + '\'' +
    //            ", password='" + password + '\'' +
    //            '}';
    //}

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
