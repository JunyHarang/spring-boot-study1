package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;   // 무조건 1000원만 할인

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {   // enum은 비교할 때 '==' 사용
            return discountFixAmount;
        } else {
            return 0;
        } // if-else 끝
    } // discount() 끝
} // Class 끝
