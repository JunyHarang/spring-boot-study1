package hello.core.orderImsi;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component public class OrderServiceImpl implements OrderService{
    // 이 코드도 구현체와 인터페이스를 의존하므로 DIP, OCP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // 고정 할인에서 정률 할인 정책으로 바뀌게 되어 아래와 같이 수정 해 준다.
    // 아래 두 코드는 DIP와 OCP위반이다. 인터페이스와 구현체를 함께 의존하기 때문.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 인터페이스만 의존하도록 하기 위해 아래처럼 작성(결과로 이렇게하면 NPE가 발생한다.)
    private final DiscountPolicy discountPolicy;

    // 생성자를 통해 DI가 가능하게 하면 NPE를 막을 수 있다.
      @Autowired public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
          this.memberRepository = memberRepository;
          this.discountPolicy = discountPolicy;
      }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // order 서비스 입장에서는 할인에 대해서는 몰라도 된다. 알아서 하고, 결과만 나한테 알려줘! 라고 하는 것과 같다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    } // createOrder() 끝

    // Test용 Getter
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
} // Class 끝
