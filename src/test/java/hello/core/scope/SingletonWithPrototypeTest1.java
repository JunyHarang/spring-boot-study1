package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {
    @Test void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);
    }

    @Scope("prototype") static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct public void init() {
            System.out.println("PrototypeBean.init 이 호출 되었습네다!" + this);
        }

        @PreDestroy public void destroy() {
            System.out.println("PrototypeBean.destory 이 호출 되었습네다!");
        }
    }
}
