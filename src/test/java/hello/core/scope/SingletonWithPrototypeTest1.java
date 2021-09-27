package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);
    } // prototypeFind() 끝

    @Test void singtoneClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean = ac.getBean(ClientBean.class);
        int count = clientBean.logic();
        Assertions.assertThat(count).isEqualTo(1);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);
    } // singtoneClientUsePrototype() 끝

    @Scope("singleton") static class ClientBean {

        // 생성시점에 이미 주입
//      private final PrototypeBean prototypeBean;

        @Autowired Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();

            // int count = prototypeBean.getCount();
            // return count;
            // 위의 코드를 줄이면 아래와 같다.

            return prototypeBean.getCount();
        } // logic()끝
    } // ClientBean Class 끝

    @Scope("prototype") static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        } // addCount() 끝

        public int getCount() {
            return count;
        } // getCount() 끝

        @PostConstruct public void init() {
            System.out.println("PrototypeBean.init 이 호출 되었습네다!" + this);
        } // init() 끝

        @PreDestroy public void destroy() {
            System.out.println("PrototypeBean.destory 이 호출 되었습네다!");
        } // destroy() 끝
    } // PrototypeBean Class 끝
} // Class 끝


