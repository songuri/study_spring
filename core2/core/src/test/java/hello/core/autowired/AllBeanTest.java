package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public class AllBeanTest {


    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "UserA", Grade.VIP);

        int fixDiscountPolicy = discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(fixDiscountPolicy).isEqualTo(1000);


        int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(rateDiscountPolicy).isEqualTo(2000);


    }

    @Component
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;

            System.out.println("policyMap : " + policyMap);
            System.out.println("polices : " + polices);
            /**
             * policyMap : {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@3546d80f, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@579d011c}
             * polices : [hello.core.discount.FixDiscountPolicy@3546d80f, hello.core.discount.RateDiscountPolicy@579d011c]
             */

        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
