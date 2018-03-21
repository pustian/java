package tian.pusen.entity;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 读取队列的程序端，实现了Runnable接口。
 * Created by tianpusen on 2017/4/14.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {
    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void run() {
        //start consuming messages. Auto acknowledge messages.
        try {
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //// Consumer 中的接口
    // Called when consumer is registered.
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }
    public void handleCancelOk(String s) {

    }

    public void handleCancel(String s) throws IOException {

    }

    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    public void handleRecoverOk(String s) {

    }

    // Called when new message is available.
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        System.out.println("Thread:"+Thread.currentThread()+"Message Number："+ map.get("message number") + " received.");
    }
}
