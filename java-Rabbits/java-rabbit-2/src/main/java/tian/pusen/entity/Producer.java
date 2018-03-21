package tian.pusen.entity;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * The producer endpoint that writes to the queue.
 * Created by tianpusen on 2017/4/14.
 */
public class Producer extends EndPoint {
    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException, TimeoutException{
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
