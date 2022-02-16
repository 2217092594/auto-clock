package zxl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;


public class MailSender {
    Properties properties;
    String smtpHost = "smtp.qq.com";//smtp服务，一般为smtp.163.com  smtp.qq.com
    String sendMail = "xxx";//发件人邮箱地址 一般为smtp对应
    String sendNickname = "zxl";
//    String receiveNickname = "zxm";
//    String receiveMail = "xxx";//接收人邮箱地址，可以是任意的合法邮箱即可
//    String mailSubject = "test";//创建的邮件主题
//    String mailContent = "this is a test!!!";//创建邮件的内容，可以添加html标签
    Date sentDate = null;//设置发送时间，null为立即发送
    String authUserName = "xxx";//验证服务器是的用户名，一般和发件人邮箱保持一致
    String authPassword = "填写你的邮箱授权码或验证码";//验证服务器的密码，一般为登录邮箱的密码，也可能是邮箱独立密码
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public  void  sendScMail(String receiveMail,String receiveNickname) throws MessagingException, UnsupportedEncodingException {

        String mailSubject = "打卡成功";//创建的邮件主题
        String mailContent = "打卡已经完成，请接着happy接着浪！！！";
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
        props.setProperty(" mail.smtp.auth ", "true");  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        props.setProperty(" mail.smtp.timeout ", " 25000 ");
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", smtpHost);   // 发件人的邮箱的 SMTP 服务器地址

        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);

        session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log,开发时候使用

        //3、通过session得到transport对象
        Transport ts = session.getTransport();
        // 4使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        ts.connect(smtpHost, authUserName, authPassword);
        //5、创建邮件
        Message message = createSimpleMail(session,receiveMail,receiveNickname,mailContent,mailSubject);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        ts.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        ts.close();
        System.out.println("成功邮件已经发送");

    }

    public void sendFaMail(String receiveMail,String receiveNickname) throws MessagingException, UnsupportedEncodingException {
        String mailSubject = "打卡失败";//创建的邮件主题
        String mailContent = "打卡失败，请滚回去打卡！！！";
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
        props.setProperty(" mail.smtp.auth ", " true ");  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        props.setProperty(" mail.smtp.timeout ", " 25000 ");
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", smtpHost);   // 发件人的邮箱的 SMTP 服务器地址
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);

        session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log,开发时候使用

        //3、通过session得到transport对象
        Transport ts = session.getTransport();
        // 4使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        ts.connect(smtpHost, authUserName, authPassword);
        //5、创建邮件
        Message message = createSimpleMail(session,receiveMail,receiveNickname,mailContent,mailSubject);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        ts.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        ts.close();
        System.out.println("失败已经邮件发送");
    }
    private Message createSimpleMail(Session session,String receiveMail,String receiveNickname,String mailSubject,String mailContent) throws MessagingException, UnsupportedEncodingException {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail,sendNickname,"UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveNickname, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(mailSubject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(mailContent, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(sentDate!=null ? sentDate : new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;

    }


}
