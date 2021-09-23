package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    } // NetworkClient() 끝

    public void setUrl(String url) {
        this.url = url;
    } // setUrl() 끝

    // 서비스를 시작할 때, 호출하는 Method
    public void connect() {
        System.out.println("connect : " + url);
    } // connect() 끝

    public void call(String message){
        System.out.println("call : " + url + "message = " + message);
    } // call() 끝

    // 서비스 종료 시 호출되는 Method
    public void disconnect() {
        System.out.println("close : " + url);
    } // disconnect()끝

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet이 호출되었습니다.");
        connect();
        call("초기화 연결 Message");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destory가 호출되었습니다.");
        disconnect();
    }
} // Class 끝
