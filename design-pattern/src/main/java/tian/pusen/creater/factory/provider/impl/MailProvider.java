package tian.pusen.creater.factory.provider.impl;

import tian.pusen.creater.factory.implment.Sender;
import tian.pusen.creater.factory.implment.impl.MailSender;
import tian.pusen.creater.factory.provider.Provider;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/11/1 13:00
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MailProvider implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
