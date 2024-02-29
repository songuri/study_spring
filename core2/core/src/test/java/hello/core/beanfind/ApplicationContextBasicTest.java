package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회하기.")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Assertions.assertEquals(memberService.getClass(), MemberServiceImpl.class);
        System.out.println("memberService  " + memberService);
        System.out.println("memberService.getClass  " + memberService.getClass());
    }


    @Test
    @DisplayName("이름없이 타입으로만 조회.")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertEquals(memberService.getClass(), MemberServiceImpl.class);
        System.out.println("memberService  " + memberService);
        System.out.println("memberService.getClass  " + memberService.getClass());


        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            MemberService memberService1 = ac.getBean("dddddd", MemberService.class);
        });
    }
}
