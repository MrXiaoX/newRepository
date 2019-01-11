package cn.itrip.auth.service;

import javax.servlet.http.HttpServletResponse;

/**
 * 邮件发送接口
 * @author hduser
 *
 */
public interface MailService {
	public void sendActivationMail(String mailTo, String activationCode);
}
