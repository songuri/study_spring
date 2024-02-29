package hello.core.configuration;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {


    @Test
    void configTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        MemberRepository memberRepository1 = memberRepository;
        MemberRepository memberRepository2 = memberService.getMemberRepository();
        MemberRepository memberRepository3 = orderService.getMemberRepository();

        System.out.println(memberRepository1);
        System.out.println(memberRepository2);
        System.out.println(memberRepository3);

        Assertions.assertEquals(memberRepository1, memberRepository2);


    }


    @Test
    void configDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println(">>>>>>>>>>>>>  " + bean.getClass());
        //>>>>>>>>>>>>>  class hello.core.AppConfig$$SpringCGLIB$$0
    }
}
