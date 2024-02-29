package hello.core.lifecycle;



public class NetworkClient  {
//implements InitializingBean, DisposableBean
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출  = " +  url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);

    }

    public void call(String message) {
        System.out.println("message = " + message);
    }

    public void disconnect() {
        System.out.println("close  =" + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    public void init(){
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }


    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
