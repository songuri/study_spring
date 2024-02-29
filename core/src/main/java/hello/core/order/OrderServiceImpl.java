package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy; // 동적인 객체 인스턴스 의존 관계. 실행 시점에


    // 프로그래머는 추상화에 의존해야지 구체화에 의존하면 안된다.


    //애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와
    //서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다.

    //의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다
    // 정적 관계를 전혀 손보지 않고 다 변경 가능.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // AppConfig가 의존성 주입을 해줌.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
