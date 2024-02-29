package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() { // 이렇게 private 생성자를 설정해버리면 된다.

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
