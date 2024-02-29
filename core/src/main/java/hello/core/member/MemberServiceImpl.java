package hello.core.member;

public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member findMemeber(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
