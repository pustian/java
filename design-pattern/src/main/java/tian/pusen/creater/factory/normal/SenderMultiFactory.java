package tian.pusen.creater.factory.normal;

import tian.pusen.creater.factory.implment.Sender;
import tian.pusen.creater.factory.implment.impl.MailSender;
import tian.pusen.creater.factory.implment.impl.SmsSender;
import tian.pusen.creater.factory.implment.impl.WechatSender;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/10/31 13:13
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SenderMultiFactory {
    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }

    public static Sender produceWechat() {
        return new WechatSender();
    }
}
