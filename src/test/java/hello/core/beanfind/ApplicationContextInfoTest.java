package hello.core.beanfind;


import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test @DisplayName("모든 Bean 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            System.out.println("name = " + beanDefinitionName + "\n object = " + bean);
            System.out.println("----------------------------------------------------------------");
        }
    }

    @Test @DisplayName("Application Bean 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);  // getBeanDefinition 이것은 Bean에 대한 Meta Data 정보를 가지고 있다.
            // ROLE_INFRASTRUCTOR는 스프링 내부에서 사용하는 Bean에 대해 정보를 가지고 있다.
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {  // getRole Method에서 ROL_AAPLICATON은 Spring이 사전에 등록한 Bean이 아닌 사용자가 생성한 Bean 혹은 외부 Library 라는 뜻

                Object bean = ac.getBean(beanDefinitionName);

                System.out.println("name = " + beanDefinitionName + "\n object = " + bean);
                System.out.println("----------------------------------------------------------------");

            }
        }
    }
} // Class 끝

