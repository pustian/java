package tian.pusen.creater.factory.implment.impl;

import tian.pusen.creater.factory.implment.Sender;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2017/10/31 13:08
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SmsSender implements Sender {
    @Override
    public void sendMsg(String message) {
        System.out.println("Send message "+message+" by SMS");
    }
}
