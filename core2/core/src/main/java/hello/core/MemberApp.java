package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 등록된 빈으로부터 객체를 꺼내는 과정

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMemeber = memberService.findMemeber(1L);

        System.out.println(member.getName());
        System.out.println(findMemeber.getName());
    }
}
