package myUtil.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSendUtil {
    private final static String host = "smtp.163.com"; //163的服务器
    private final static String formName = "15050580882@163.com";//你的邮箱
    private final static String password = "x65j133j"; //授权码
    private final static String replayAddress = "15050580882@163.com"; //你的邮箱

    private static void sendTextMail(MailInfo info) throws Exception {
        info.setHost(host);
        info.setFormName(formName);
        info.setFormPassword(password);   //网易邮箱的授权码~不一定是密码
        info.setReplayAddress(replayAddress);
        Message message = getMessage(info);
        //消息发送的内容
        message.setText(info.getContent());

        Transport.send(message);
    }

    private static Message getMessage(MailInfo info) throws Exception {
        final Properties p = System.getProperties();
        p.setProperty("mail.smtp.host", info.getHost());
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", info.getFormName());
        p.setProperty("mail.smtp.pass", info.getFormPassword());

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getInstance(p, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"), p.getProperty("mail.smtp.pass"));
            }
        });
        session.setDebug(true);
        Message message = new MimeMessage(session);
        //消息发送的主题
        message.setSubject(info.getSubject());
        //接受消息的人
        message.setReplyTo(InternetAddress.parse(info.getReplayAddress()));
        //消息的发送者
        message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"), "Tickets会员服务组"));
        // 创建邮件的接收者地址，并设置到邮件消息中
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(info.getToAddress()));
        // 消息发送的时间
        message.setSentDate(new Date());


        return message;
    }

    public static void sendActivateMail(String mail, int id) {
        String title = "Tickets 邮箱验证";
        String content = "您好，您在Tickets上注册了账号，请点击下面的链接激活您的邮箱：https://localhost:8000/member/activate/?id=" + id;
        MailInfo info = new MailInfo();
        info.setToAddress(mail);
        info.setSubject(title);
        info.setContent(content);
        try {
            MailSendUtil.sendTextMail(info);
        } catch (Exception e) {
            System.out.print("'" + title + "'的邮件发送失败！");
            e.printStackTrace();
        }
    }
}