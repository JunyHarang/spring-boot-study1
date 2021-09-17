package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    } // AutoWiredOption() 끝

    static class TestBean{ /* 아래 코드에서 사용하는 Member는 Spring Bean이 아닌 상태 */

        // 아래 코드에서 required가 false일 때 의존관계가 없으면 Method 자체가 호출이 되지 않는다.
        @Autowired(required = false) public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable을 사용하면 Method가 호출은 되지만, Null값이 들어오게 된다.
        @Autowired public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional은 Null을 막기 위해 자바 8부터 사용되는 기능으로 이것을 사용하여 의존관계가 없으면 Optional.empty로 감싸준다.
        @Autowired public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    } // TestBean Class 끝
} // Class 끝
