package com.example.hongpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러(클라이언트의 요청을 받아 서버에서 이를 처리하는 역할) 선언
@Controller
public class FirstController {

    // 클라이언트의 URL 요청을 받아 특정 컨트롤러의 메서드가 처리하게 한다.
    @GetMapping("/hi")
    // 모델은 컨트롤러의 메서드에서 매개변수로 받아온다. 데이터를 관리하는 역할
    public String niceToMeetYou(Model model) {
        // 모델에서 변수를 등록할 때 addAttribute()메서드를 사용
        model.addAttribute("username","홍팍");
        // 뷰 페이지 반환
        return "greetings";
    }

    @GetMapping("/bye")
    // 모델 객체 받아 오기
    public String seeYouNext(Model model) {
        // 모델 변수 등록하기
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}
