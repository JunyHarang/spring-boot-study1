package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("prototypeBean을 찾갔습네다!");
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1을 찾갔습네다!");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean 은 다음과 같습네다! " + prototypeBean);
        System.out.println("prototypeBean1 은 다음과 같습네다! " + prototypeBean1);

        Assertions.assertThat(prototypeBean).isNotSameAs(prototypeBean1);

        ac.close();

        // 싱글톤 Bean Scope와 다르게 Prototype은 직접 destory()를 호출한 곳에서 호출해주어야 한다.
        prototypeBean.destroy();
        prototypeBean1.destroy();
    } // prototypeBeanFind() 끝

    @Scope("prototype") static class PrototypeBean {
        @PostConstruct public void init() {
            System.out.println("SingletonBean.init 이 호출 되었습네다!");
        } // init() 끝

        @PreDestroy public void destroy() {
            System.out.println("SingletonBean.destroy 이 호출 되었습네다!");
        } // destroy() 끝
    } // PrototypeBean Class 끝
} // Class 끝
