package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletoneBean.class);

        SingletoneBean singletoneBean = ac.getBean(SingletoneBean.class);
        SingletoneBean singletoneBean1 = ac.getBean(SingletoneBean.class);

        System.out.println("singletoneBean = " + singletoneBean);
        System.out.println("singletoneBean1 = " + singletoneBean1);

        Assertions.assertThat(singletoneBean).isSameAs(singletoneBean1);

        ac.close();
    } // singletonBeanFind() 끝

    @Scope("singleton") static class SingletoneBean{
        @PostConstruct public void init() {
            System.out.println("SingletoneBean.init 이 호출 되었습네다!");
        } // init() 끝

        @PreDestroy public void destroy() {
            System.out.println("SingletoneBean.destroy 이 호출 되었습네다!");
        } // destroy() 끝
    } // SingletoneBean Class 끝

} // Class 끝
