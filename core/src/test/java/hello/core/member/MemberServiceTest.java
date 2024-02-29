package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //AppConfig appConfig = new AppConfig();
    MemberService memberService;

    @BeforeEach
    public void beforeEacch() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMemeber(1L);

        //then
        Assertions.assertEquals(member.getId(), findMember.getId());
    }
}
