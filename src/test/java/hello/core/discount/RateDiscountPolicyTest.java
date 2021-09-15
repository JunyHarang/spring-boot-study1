package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test

    //JUnir 5에서부터 지원하는 것으로 문자열을 확인란에 출력한다.
    @DisplayName("VIP는 10% 할인이 적용 되어야 한다.")
    void vip_o() {

        // given ( ~~가 주어지고,)
        Member member =  new Member(1L, "memberVIP", Grade.VIP);

        // when ( ~~을 하면)
        int discount = discountPolicy.discount(member, 10000);


                Assertions.assertThat(discount).isEqualTo(1000);

        // then (어떤 값이 나와야 한다.)
    }


    @Test
    @DisplayName("VIP가 아니면 할인 미 적용")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }

}

