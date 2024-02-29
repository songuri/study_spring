package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {



    private final MemberRepository memberRepository;
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    // ac.getBean(MemberRepository.class) 기존에 했던걸 여기서 해주는 거임.
    @Autowired // 의존관계 주입을 위해, Singleton 유지를 위해. 이렇게 해주면 스프링에서 기존에 Component로 등록된 memberRepository를 찾아서 등록 해줌.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member findMemeber(Long memberId) {
        return memberRepository.findById(memberId);
    }


    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
