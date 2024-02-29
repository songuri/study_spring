package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWriedTest {


    @Test
    void AutoWiredTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);


    }


    static class TestBean {

        // required = false 안하면 Memeber는 Bean에 등록된 클래스가 아니기 때문에 오류터진다.
        // required = false 처리가 되있을때, 자동주입할 class가 없다면 아에 생성자도 호출이 되지 않음.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1  " + noBean1);
        }

        // @Nullable 처리를 하면 생성자가 호출은 됨 근데 null임.
        @Autowired
        public void setNoBean2(@Nullable Member noBean1) {
            System.out.println("noBean2  " + noBean1);
        }

        // 이거는 생성자는 호출이 되긴하는데 empty 처리가 된다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean1) {
            System.out.println("noBean3  " + noBean1);
        }
    }
}
