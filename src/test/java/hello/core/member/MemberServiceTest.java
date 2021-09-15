package hello.core.member;


import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    // 각 테스트가 실행 전 무조건 실행되게 하려는 것을 넣게 하기 위한 어노테이션(각 클래스내에 테스트 개수만큼 돌아간다.)
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {

        // given (~~가 주어졌다.)
        Member member = new Member(1L, "memberA", Grade.VIP);


        // when (~~을 했다.)
        memberService.join(member);
        Member serviceMember = memberService.findMember(1L);

        // then (이런 결과가 나와야한다.)
        Assertions.assertThat(member).isEqualTo(serviceMember);
    } // join() 끝
} // Class 끝
