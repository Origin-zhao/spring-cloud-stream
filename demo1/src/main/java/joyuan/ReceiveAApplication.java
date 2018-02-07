package joyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(value = {MyChannel.class}) //启动stream功能,和rabbit建立连接
@SpringBootApplication
public class ReceiveAApplication {

    //接收消息
    @StreamListener("myInput2")
    public void receive( Message<Object> msg){
        System.out.println("A消息收到了:"+msg.getPayload());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ReceiveAApplication.class)
                .web(false)
                .profiles("receivea")
                .run(args);

    }
}
