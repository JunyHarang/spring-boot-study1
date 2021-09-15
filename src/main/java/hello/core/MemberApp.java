package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        /* Spring을 사용하지 않고, 순수 Java로 코딩할 때 쓰는 코드 */
//      AppConfig appConfig = new AppConfig();
//      MemberService memberService = appConfig.memberService();

        /* Spring을 사용한 코드 */
        // ApplicationContext가 AppConfig처럼 설정(구성)을 총괄하는 역할을 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberService Class에 있는 memberService Method의 Bean 객체를 memberService 변수에 넣어라.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

//      MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member serviceMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + serviceMember.getName());
    } // Main() 끝
} //Class 끝
