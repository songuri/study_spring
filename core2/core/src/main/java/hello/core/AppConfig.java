package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // application의 설정 정보. 이거 빼고 @Bean만 쓰면 싱글톤 보장을 해주지 않는다.
public class AppConfig {

    // IOC 제어의 역전
    // 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.


    //프레임워크 vs 라이브러리
    //프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다. (JUnit) / Android  생명주기 등
    //반면에 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리다  xml to json 처럼 변경하는 라이브러리.


    // 이런 기능을 하는 애들을
    // IOC 컨테이너 , DI 컨테이너 라고 한다.

    /*
    스프링 컨테이너는 @Configuration 이 붙은 AppConfig 를 설정(구성) 정보로 사용한다. 여기서 @Bean 이
    라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된
    객체를 스프링 빈이라 한다.
    */


    /**
     * Bean 저장소는 빈이름 빈객체 이렇게 키 밸류로 저장되는데
     * @Bean이 붙은 메소드 명이 빈이름 / 반환되는 객체가 빈객체로 저장됨.
     *
     * 뿐만 아니라 서로의 의존관계 (MemberService를 사용하려면 MemeberRepository가 의존관계가 필요한것 처럼)도 함께 컨테이너에 생성 저장함.
     *
     */



    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    // 어 ? 2번 호출되면서 Singleton 깨지는거 아님?

    @Bean
    public MemberService memberService() {
        //생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
        return new FixDiscountPolicy();
    }
}
