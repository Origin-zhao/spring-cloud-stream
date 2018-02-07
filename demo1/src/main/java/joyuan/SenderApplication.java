package joyuan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(value = {MyChannel.class}) //启动stream功能,和rabbit建立连接
@SpringBootApplication
public class SenderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SenderApplication.class)
                .web(false)
                .profiles("send")
                .run(args);

        send(context);
    }

    //发送消息
    public static void send(ConfigurableApplicationContext context){
        MyChannel bean = context.getBean(MyChannel.class);
        for (int i=0;i<10;i++){
            bean.myOutPut2().send(MessageBuilder.withPayload("hello:"+i).build());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
