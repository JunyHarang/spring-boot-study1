package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test @DisplayName("스프링 없는 순수 DI Container Test")
    void pureContainer() {
        AppConfig appconfig = new AppConfig();

        // 1. 조회 : 1번 Client가 호출할 때 객체를 생성하는지 확인
        MemberService memberService = appconfig.memberService();

        // 2. 조회 : 2번 Client가 호출할 때 1번과 다른 객체를 생성하는지 확인
        MemberService memberService1 = appconfig.memberService();

        // 3. 조회 : 참조값이 다른 것을 호출할 때
        System.out.println("memberService = " + memberService);
        System.out.println("memberService1 = " + memberService1);

        // memberService 객체와 memberService1 객체가 달라야 한다.
        Assertions.assertThat(memberService).isNotSameAs(memberService1);

    }

    @Test @DisplayName("Singletone Pattern 적용 객체 사용")
    void singletoneServiceTest() {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService1 = SingletonService.getInstance();

        System.out.println("singletonService = " + singletonService);
        System.out.println("singletonService1 = " + singletonService1);

        Assertions.assertThat(singletonService).isSameAs(singletonService1);
                // isSameAS는 ==처럼 인스턴스를 비교하는 것이고, isEqual는 Equals Mehotd처럼 비교하는 것
    } // singletoneServiceTest() 끝

    @Test @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 1번 Client가 호출할 때 객체를 생성하는지 확인
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 2번 Client가 호출할 때 1번과 다른 객체를 생성하는지 확인
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 3. 조회 : 참조값이 다른 것을 호출할 때
        System.out.println("memberService = " + memberService);
        System.out.println("memberService1 = " + memberService1);

        // memberService 객체와 memberService1 객체가 달라야 한다.
        Assertions.assertThat(memberService).isSameAs(memberService1);
    } // springContainer() 끝
} // Class끝끝
