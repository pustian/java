package tian.pusen.creater.builder;

import tian.pusen.creater.factory.implment.Sender;
import tian.pusen.creater.factory.implment.impl.MailSender;
import tian.pusen.creater.factory.normal.SenderFactory;
import tian.pusen.creater.factory.normal.SenderMultiFactory;
import tian.pusen.creater.factory.provider.impl.MailProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 21:50
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Builder {
    private List<Sender> senderList = new ArrayList<>();
    public void produceSenderList() {
        senderList.add(new MailSender());
        senderList.add(SenderFactory.produce("wechat"));
        senderList.add(SenderMultiFactory.produceSms());
        senderList.add(new MailProvider().produce());
    }
}
