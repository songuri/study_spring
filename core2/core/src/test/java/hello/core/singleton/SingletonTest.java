package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SingletonTest {


    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContatiner() {
        // 이렇게 만들면 같은 종류의 객체가 감당할수 없을 만큼 생성 될 경우가 발생함.
        // 딱 하나의 객체만 만들어서 Client에게 넘기는게 가장 이상적이다.


        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //memberService1 != memberService2

        Assertions.assertNotEquals(memberService1, memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴 적용")
    void singletoneServiceTest() {
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
        SingletonService instance3 = SingletonService.getInstance();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance3);
        /**
         * RESULT  모두 같은 값 호출 됨
         * hello.core.singleton.SingletonService@76b07f29
         * hello.core.singleton.SingletonService@76b07f29
         * hello.core.singleton.SingletonService@76b07f29
         */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContatiner() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        /**
         * 이미 싱글톤이 적용이 되어 있다.
         * memberService1 = hello.core.member.MemberServiceImpl@53499d85
         * memberService2 = hello.core.member.MemberServiceImpl@53499d85
         */
        //memberService1 != memberService2

        Assertions.assertEquals(memberService, memberService2);

    }
}
