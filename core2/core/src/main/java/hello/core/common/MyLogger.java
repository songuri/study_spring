package hello.core.common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;


    public void log(String message) {
        System.out.println("[ " + uuid + " ]"  + " [ " +  requestURL + " ] " + message +"   :"+ this);
    }


    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[ " + uuid + " ] request scope bean crete. "  + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[ " + uuid + " ] request scope bean close. "  + this);
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRequestURL(String requestURL) {
        System.out.println("MyLogger.setRequestURL uuid : " + uuid);
        this.requestURL = requestURL;
    }
}
