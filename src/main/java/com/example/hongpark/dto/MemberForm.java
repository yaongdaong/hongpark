package com.example.hongpark.dto;

import com.example.hongpark.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

//데이터 조회 과정
//1. 사용자가 데이터를 조회해 달라고 웹 페이지에서 URL 요청을 보냄
//2. 서버의 컨트롤러가 요청을 받아 해당 URL에서 찾으려는 데이터 정보를 리파지터리에 전달
//3. 리파지터리는 정보를 가지로 DB에 데이터 조회를 요청
//4. DB는 해당 데이터를 찾아 이를 엔티티로 반환
//5. 반환된 엔티티는 모델을 통해 뷰 템플릿으로 전달
//6. 최종적으로 결과 뷰 페이지가 완성돼 사용자의 화면에 출력됨
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
