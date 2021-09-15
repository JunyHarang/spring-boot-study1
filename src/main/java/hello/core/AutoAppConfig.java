package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @component가 붙은 Class를 모두 Spring Bean으로 등록 시켜주기 위해 사용하는 어노테이션
@ComponentScan (
        // 컴포넌트를 스캔할 대상 패키지를 직접 지정
        // 만약 "hello.core.member"로 하면 member 패키지의 @Component만 찾아서 Spring Bean에 등록한다.
        basePackages = "hello.core",
        // @Component가 붙은 Class를 모두 Spring Bean으로 등록시켜 주는 것들 중 뺄 것을 지정하기 위해 사용
        excludeFilters = @ComponentScan.Filter(
                // 아래에서는 필터 타입은 어노테이션으로 하고, Class중에 Configuration Class는 제외해 주겠다는 것
                type = FilterType.ANNOTATION, classes = Configuration.class
        )
)
public class AutoAppConfig {
} // Class 끝
