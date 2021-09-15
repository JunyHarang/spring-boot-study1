package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 인터페이스에 대한 구현체가 하나일 때는 관례상 임플(Impl)을 클래스명 뒤에 붙혀준다.
@Component public class MemberServiceImpl implements MemberService{

    // private final MemberRepository memberRepository; 이렇게만 하면 NPE가 발생함으로, 객체를 새로 생성하는 아래와 같은 방식으로 해야 한다.
    // Service Class가 Interface인 MemberRepository 뿐만 아니라, 구현체인 MeemoryMemberRepository를 의존한다.
    // 이것은 OCP, DIP 원칙 위배 사항이다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // @Autowired를 생성자에 붙혀주면 매개변수 타입에 맞는 것을 찾아와서 의존관계 주입을 자동으로 연결하여 주입해 준다.
    // 아래 Autowired는 ac.getBean(MemberRepository.class)와 같이 동작한다.
    @Autowired public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test용 Getter
    public MemberRepository getMemberRepository() {
        return memberRepository;
    } // getMemberRepository()

} //Class 끝
