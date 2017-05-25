package com.xxkj.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2017/5/7.
 */
public class MessageUtils {
	public static boolean sendMail(String to, String code, String username) {

		try {
			Properties props = System.getProperties();// 获取系统属性
			props.setProperty("mail.smtp.host", "smtp.qq.com");// 设置邮件服务器
			props.put("mail.smtp.auth", "true");// 打开认证
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);

			Session mailSession = Session.getInstance(props,
					new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							// TODO Auto-generated method stub
							return new PasswordAuthentication(
									"748656590@qq.com", "mznmxztekmsfbbcd");
						}
					});
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress("748656590@qq.com"));
			msg.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			msg.setSubject("激活邮件");
			msg.setContent(
					"<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://localhost:8080/SecKillManger/user/actives?username="
							+ username
							+ "&code="
							+ code
							+ "'>http://localhost:8080/SecKillManger/active?username="
							+ username + "&code=" + code + "</a></h3>",
					"text/html;charset=UTF-8");
			msg.saveChanges();
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return true;
	}
}
