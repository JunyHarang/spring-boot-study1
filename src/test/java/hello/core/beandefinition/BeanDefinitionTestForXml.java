package hello.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTestForXml {

    // 변수 자료형 타입을 ApplicationContext로 하지 않는 이유는 그렇게 하면 getBeanDefinition을 사용할 수 없기 때문
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test @DisplayName("Bean Configuration Meta Information Check For XML")
    void findApplicationBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + "\n beanDefinition 자체 출력 = " + beanDefinition);
            } // if 문 끝
        } // for 문 끝

    } // findApplicationBean() 끝
} // Class 끝
