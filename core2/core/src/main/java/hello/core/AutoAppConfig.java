package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(

//        basePackages = "hello.core.member", // 이런식으로 ComponentScan 범위를 지정할 수 있다.
        //만약 아무것도 지정안하면? ComponentScan이 선언된 클래스의 package의 하위 package를다 뒤진다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // Configuration 콤퍼넌트는 제외 해준다.
) // 자동으로 해준다고 ? @Component Anootation이 붙은 애들을 전부 스캔해서 스프링빈에 등록 해준다.
public class AutoAppConfig {


    // 그런데 최근에 Springboot에는 빈에 등록되는 이름이 중복될경우 그냥 팅겨버리도록 함.
    // Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
//    @Bean(name = "memoryMemberRepository") // 이렇게 중복이 될경우(MemoryMemberRepository 는 이미 Compomnent로 등록이 되어 있는경우) 수동이 우선권을 가진다.
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
