package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {


    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    /**
     *     @Autowired MemberRepository memberRepository;
     *     @Autowired DiscountPolicy discountPolicy; // 동적인 객체 인스턴스 의존 관계. 실행 시점에
     *
     *     이렇게 의존성을 주입하게 되면 setter를 또 따로 만들거나 해야하는 불편함.
     *     테스트의 어려움이 있음. 내가 원하는 애들을 바로바로 주입할 수 가 없기 때문에.
     */
    @Test
    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "name", 10000); // NULLPOINT EXCEPTION 터진다.,
    }
}
