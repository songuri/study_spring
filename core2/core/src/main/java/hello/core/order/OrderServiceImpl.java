package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 필수값인(final인) 멤버들의 생성자를 자동으로 만들어줌 [컨트롤 + f12로 확인가능]
public class OrderServiceImpl implements OrderService{


    /**
     * ##################################################################################
     *      1번쨰 방법 (생성자 이용하기)
     *      멤버변수를 FINAL로 선언해야함.
     * ####################################################################################
     */


    /**
     * final로 하는 이유?
     * 무조건 값이 있어야(초기화가 되어야) 함.
     * 그래서 생성자에 주입 되는 코드가 없으면 컴파일 에러.
     */
    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy; // 동적인 객체 인스턴스 의존 관계. 실행 시점에


    // 프로그래머는 추상화에 의존해야지 구체화에 의존하면 안된다.


    //애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와
    //서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다.

    //의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다
    // 정적 관계를 전혀 손보지 않고 다 변경 가능.

    /**
     *  생성자 호출 시점에 딱 1번만 호출되는 것이 보장됨
     *  불변, 필수 의존 관계에 사용된다.
     *  그래서 이런 객체를 set하는 함수를 함부로 public하게 해놓으면 좆되는 경우가 있음 조심.
     *  생성자가 딱 1개만 있을경우 @Autowired 생략이 가능하다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * ##################################################################################
     *      2번쨰 방법 (setter이용하기)
     *      선언히 final 빼야함.
     *      이렇게도 의존성 주입이 가능하다
     *      중간에 해당 setter를 사용해서 객체를 변경/선택할 수 있긴하다.
     * ####################################################################################
     */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memeberRepository");
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy");
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * ##################################################################################
     *      3번쨰 방법 (멤버변수에 바로 선언해버리기)
     *      선언한 final 빼야함.
     *      이렇게도 의존성 주입이 가능하다
     *      하지만 좋은 방법은 아니다. 테스트할 방법이 없음.(변경이 불가능함)
     *
     * @Autowired final MemberRepository memberRepository;
     * @Autowired final DiscountPolicy discountPolicy; // 동적인 객체 인스턴스 의존 관계. 실행 시점에
     * ####################################################################################
     */


    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // AppConfig가 의존성 주입을 해줌.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
