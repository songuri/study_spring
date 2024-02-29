package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

public class SingletoneWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean2.class);

        PrototypeBean2 prototypeBean1 = ac.getBean(PrototypeBean2.class);
        prototypeBean1.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean2 prototypeBean2 = ac.getBean(PrototypeBean2.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }


    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean2.class);



        ClientBean clientBean = ac.getBean(ClientBean.class);
        int count1 = clientBean.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean.logic();

        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {

       // private final PrototypeBean2 prototypeBean;


//        @Autowired
//        private ObjectProvider<PrototypeBean2> prototypeBeanProvider;

        @Autowired
        private Provider<PrototypeBean2> prototypeBean2Provider;
        //javax.inject

//        @Autowired
//        public ClientBean(PrototypeBean2 prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
            PrototypeBean2 prototypeBean2 = prototypeBean2Provider.get();
            prototypeBean2.addCount();
            int count = prototypeBean2.getCount();
            System.out.println("prototypeBean > " + prototypeBean2);
            return count;

        }
    }

    @Scope("prototype")
    static class PrototypeBean2 {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
