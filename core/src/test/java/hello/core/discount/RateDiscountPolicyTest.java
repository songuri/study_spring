package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("무조건 10퍼센트 할인")
    void vip_o() {

        //given
        Member member = new Member(1L, "MemeberVip", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);


        //then
        Assertions.assertEquals(discount, 1000);
    }

}