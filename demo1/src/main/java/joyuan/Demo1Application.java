package joyuan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.DirectHandler;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(value = {Sink.class}) //启动stream功能,和rabbit建立连接
@SpringBootApplication
public class Demo1Application  {

	//订阅input 通道的消息
	@StreamListener(Sink.INPUT)
	public void receive(Message<byte[]> msg){
		System.out.println("收到消息:"+new String(msg.getPayload()));
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Demo1Application.class)
				.web(false)
				.run(args);
	}
}
