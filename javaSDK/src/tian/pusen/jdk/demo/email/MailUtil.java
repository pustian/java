///**
// * 
// */
//package javaABC.demo.email;
//
///**
// * <p>
// * Title:MailUtil
// * </p>
// * <p>
// * Description:
// * </p>
// * <p>
// * Company: Masapay
// * </p>
// * 
// * @author: tianpusen
// * @Date : 2016年10月19日 下午5:12:39
// */
//public final class MailUtil {
//	public boolean send(Mail mail) {
//		// 发送email
//		HtmlEmail email = new HtmlEmail();
//		try {
//			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
//			email.setHostName(mail.getHost());
//			// 字符编码集的设置
//			email.setCharset(Mail.ENCODING);
//			// 收件人的邮箱
//			email.addTo(mail.getReceiver());
//			// 发送人的邮箱
//			email.setFrom(mail.getSender(), mail.getName());
//			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
//			email.setAuthentication(mail.getUsername(), mail.getPassword());
//			// 要发送的邮件主题
//			email.setSubject(mail.getSubject());
//			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
//			email.setMsg(mail.getMessage());
//			// 发送
//			email.send();
//			System.out.println(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
//			return true;
//		} catch (EmailException e) {
//			System.out.println(mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败");
//			e.printStackTrace();
//			return false;
//		}
//	}
//}
