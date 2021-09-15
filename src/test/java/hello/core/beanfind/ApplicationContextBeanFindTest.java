package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    static class SameBeanConfig { // Bean 이름이 다르고, 객체 타입(인스턴스 타입)이 같게 할 수 있다.

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        } // memberRepository1() 끝

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        } // memberRepository2() 끝
    } // SameBeanConfig Class 끝

    @Test @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
    void findBeanByTypeDuplicate() {
        MemberRepository bean = ac.getBean(MemberRepository.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    } // findBeanByTypeDuplicate() 끝

    @Test @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, Bean 이름 지정 시 해결")
    void findBeanByName() {
       MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    } // findBeanByName() 끝

    @Test @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beanOfType = ac.getBeansOfType(MemberRepository.class);

        for (String key : beanOfType.keySet()) {
            System.out.println("key = " + key +  "\n Value = " + beanOfType.get(key));
        }

        System.out.println("beanOfType = " + beanOfType);

        org.assertj.core.api.Assertions.assertThat(beanOfType.size()).isEqualTo(2);
    } // findAllBeanByType() 끝
} // Class 끝
