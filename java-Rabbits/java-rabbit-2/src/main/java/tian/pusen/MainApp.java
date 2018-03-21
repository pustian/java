package tian.pusen;

import tian.pusen.entity.Producer;
import tian.pusen.entity.QueueConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by tianpusen on 2017/4/14.
 */
public class MainApp {
    public static void main(String[] args) throws IOException, TimeoutException {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread0 = new Thread(consumer);
        consumerThread0.start();

        Producer producer = new Producer("queue");
        for (int i = 0; i < 1000; i++) {
            HashMap message = new HashMap();
            message.put("message number", "message No:"+i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }
}
