package joyuan;

import javafx.scene.control.Skin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.DirectHandler;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(value = {MyChannel.class}) //启动stream功能,和rabbit建立连接
@SpringBootApplication
public class Demo1Application  {

	//接收消息
	@StreamListener("myInput2")
	public void receive( Message<Object> msg){
		System.out.println("消息收到了:"+msg.getPayload());
	}

	//接收消息
	@StreamListener("myInput2")
	public void receive2( Message<Object> msg){
		System.out.println("消息收到了2:"+msg.getPayload());
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Demo1Application.class)
				.web(false)
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
