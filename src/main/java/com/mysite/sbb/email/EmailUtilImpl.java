package com.mysite.sbb.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailUtilImpl implements EmailUtil {
	private final UserService userService;
	@Autowired
	private JavaMailSender sender;

	@Override
	public Map<String, Object> sendEmail(String username, String subject, String body) {
		Map<String, Object> result = new HashMap<String, Object>();
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		SiteUser user = userService.getUser(username);
		String toAddress = user.getEmail();
		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
			result.put("resultCode", 200);
		} catch (MessagingException e) {
			e.printStackTrace();
			result.put("resultCode", 500);
		}

		sender.send(message);
		return result;
	}
}