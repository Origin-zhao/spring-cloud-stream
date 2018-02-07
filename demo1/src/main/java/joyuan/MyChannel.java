package joyuan;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {
    //使用方法名称作为通道名称
    //@Input
    //SubscribableChannel myInput1();

    //使用注解参数作为通道名称
    @Input(value = "myInput2")
    SubscribableChannel myInput2();

    //@Output
    //MessageChannel myOutPut1();

    @Output("myOutPut2")
    MessageChannel myOutPut2();
}
