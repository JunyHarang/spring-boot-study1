package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.orderImsi.OrderService;
import hello.core.orderImsi.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // 설정을 담당하는 Class에 이것이 설정 (구성)을 담당하는 Class라고 알리는 어노테이션
// application 전체를 구성하는 Class
public class AppConfig {
    // 리펙터링 전
//    public MemberService memberService() {
//        // 어디선가 memberservice를 불러서 쓸 때 MemberServiceImpl 객체를 생성할 때, 생성자를 통해 주입을 한다. (생성자 주입)
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }

    // 리펙터링 후
    @Bean public MemberService memberService() {  // MemberService 역할
        System.out.println("call AppConfig.memberService");
        // 어디선가 memberservice를 불러서 쓸 때 MemberServiceImpl 객체를 생성할 때, 생성자를 통해 주입을 한다. (생성자 주입)
        return new MemberServiceImpl(memberRepository());
    }

    @Bean public MemberRepository memberRepository() { // MemberRepository 역할
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean public OrderService orderService() { // OrderService 역할
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean public DiscountPolicy discountPolicy() { // DiscountPolicy 역할
    // 고정 할인에서 정률 할인 정책 변경을 위해 아래와 같이 바꾸었다.
        //    return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
