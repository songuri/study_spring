package hello.core.lifecycle;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


    @Test
    public void lifeCycleTest() {
        //ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);


        ac.close();

    }


    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init" , destroyMethod = "close")  // 이렇게 쓸수도 있다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring-dev");
            return networkClient;
        }


    }
}
