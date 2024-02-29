package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();


//        MemberService memberService = new MemberServiceImpl();
        MemberService memberService = appConfig.memberService();
//        OrderService orderService = new OrderServiceImpl();
        OrderService orderService = appConfig.orderService();
        MemberRepository memberRepository = new MemoryMemberRepository();

        Long memberId = 1L;

        Member member = new Member(memberId, "MemeberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println(order.toString());

    }
}
