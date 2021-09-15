package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test @DisplayName("Bean Name으로 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }  // findBeanName() 끝

    @Test @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    } // findBeanByType() 끝

    @Test @DisplayName("구체 타입으로 조회")
    void findBeanName1() {  // 이렇게 구체화를 통해 조회를 하는 것은 좋지 않다. 왜냐하면? 추상화에 의존해야 하는데, 구체화에 의존하기 때문
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    } // findBeanByType() 끝

    @Test @DisplayName("빈 이름으로 조회가 되지 않을 때")
    void findBeanByNameX() { // Test를 만들 때는 실패에 대한 것도 만들어야 한다.
        // ac.getBean("xxxx", MemberService.class); MemberService에 "xxxx"라는 이름으로 된 Bean이 없기 때문에 NoSuchBeanDefinitionException Exception이 뜰 것이다.

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); // 오른쪽에 인자값이 실행되면 왼쪽 인자값 Exception이 터져야 Test 정상이 나온다.
    } // findBeanByNameX() 끝

} // Class 끝
