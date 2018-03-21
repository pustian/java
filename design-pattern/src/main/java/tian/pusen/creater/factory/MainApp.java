package tian.pusen.creater.factory;

import tian.pusen.creater.factory.implment.Sender;
import tian.pusen.creater.factory.normal.SenderFactory;
import tian.pusen.creater.factory.normal.SenderMultiFactory;
import tian.pusen.creater.factory.provider.Provider;
import tian.pusen.creater.factory.provider.impl.MailProvider;
import tian.pusen.creater.factory.provider.impl.SmsProvider;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 12:48
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MainApp {
    public static void main(String[] args) {
        Sender senderMail = null;
        Sender senderSms = null;
        Sender senderWechat = null;
        System.out.println("Factory with type -------begin");
        senderMail = SenderFactory.produce("mail");
        senderMail.sendMsg("SOS");
        senderSms = SenderFactory.produce("sms");
        senderSms.sendMsg("SOS");
        senderWechat = SenderFactory.produce("wechat");
        senderWechat.sendMsg("SOS");
        System.out.println("Factory with type -------end");

        System.out.println("Factory create known type -------begin");
        senderMail = SenderMultiFactory.produceMail();
        senderMail.sendMsg("SOS");
        senderSms = SenderMultiFactory.produceSms();
        senderSms.sendMsg("SOS");
        senderWechat = SenderMultiFactory.produceWechat();
        senderWechat.sendMsg("SOS");
        System.out.println("Factory create known type -------begin");

        Provider provider = null;
        System.out.println("Provider interface -------begin");
        provider = new MailProvider();
        senderMail = provider.produce();
        senderMail.sendMsg("SOS");
        provider = new SmsProvider();
        senderSms = provider.produce();
        senderSms.sendMsg("SOS");
        System.out.println("Provider interface -------end");

    }
}
