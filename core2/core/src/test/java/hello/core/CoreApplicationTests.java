package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class CoreApplicationTests {

	/**
	 * 이렇게 테스트 할때만 쓰자.
	 */
	@Test
	void contextLoads() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		Member member = new Member(1L, "memberA", Grade.VIP);
		MemberService memberService = ac.getBean(MemberService.class);

		memberService.join(member);

		OrderService orderService = ac.getBean(OrderService.class);
		orderService.createOrder(1L, "dfaf", 12121);
	}

}
