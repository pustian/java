package tian.pusen.creater.factory.normal;

import tian.pusen.creater.factory.implment.Sender;
import tian.pusen.creater.factory.implment.impl.MailSender;
import tian.pusen.creater.factory.implment.impl.SmsSender;
import tian.pusen.creater.factory.implment.impl.WechatSender;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/10/31 12:56
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SenderFactory {
    public static Sender produce(String type) {
        if("mail".equals(type)) {
            return new MailSender();
        } else if("wechat".equals(type)) {
            return new WechatSender();
        } else if("sms".equals(type)){
            return new SmsSender();
        } else {
            System.out.println("不支持类型");
            return null;
        }
    }

}
