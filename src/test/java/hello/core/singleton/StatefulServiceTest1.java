package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest1 {
    @Test void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService = ac.getBean(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);

        //Thread A : A 사용자가 10,000원 주문
        int userAPrice = statefulService.order("userA", 10000);

        // Thread B : B 사용자가 20,000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        // Thread A : 사용자 A의 주문 금액 조회
//        int price = statefulService.getPrice();

        System.out.println("price = " + userAPrice);

//        org.assertj.core.api.Assertions.assertThat(statefulService.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean public StatefulService statefulService() {
            return new StatefulService();
        } // statefulService() 끝
    }
}