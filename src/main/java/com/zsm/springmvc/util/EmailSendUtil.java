package com.zsm.springmvc.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * 模拟邮箱发送邮件 http://www.cnblogs.com/yeli/p/7795701.html
 *
 * @Author: zsm.
 * @Description:
 * @Date:Created in 2017/11/10 23:18.
 * @Modified By:
 */
public class EmailSendUtil
{
    public static void sendMail(String host, String sender, String nickname, String password, String[] receivers,
                                String subject, String content) throws Exception
    {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", host);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 开启ssl加密，目前qq是要开的
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        // 定义邮件主题,内容,发件人
        Message msg = new MimeMessage(session);
        msg.setSubject(subject);
        StringBuilder builder = new StringBuilder();
        builder.append(content);
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress(sender, nickname, "UTF-8"));

        //传输连接，并发送
        Transport transport = session.getTransport();
        transport.connect(host, sender, password);
        Address[] address = new Address[receivers.length];
        for (int i = 0; i < receivers.length; i++)
        {
            address[i] = new InternetAddress(receivers[i]);
        }
        transport.sendMessage(msg, address);
        transport.close();
    }
}
