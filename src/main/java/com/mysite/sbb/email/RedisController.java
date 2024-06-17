package com.mysite.sbb.email;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedisController {
	private final RedisService redisUserService;
	private final EmailUtil emailUtil;

	@PostMapping("/email")
	@ResponseBody
	public Map<String, Object> Email(@RequestBody Map<String, Object> params) {
		log.info("email params={}", params);
		// 인증번호 저장
		String serial = Integer.toString(ThreadLocalRandom.current().nextInt(100000, 1000000));
		String body = (String) params.get("body") + serial;
		return emailUtil.sendEmail((String) params.get("username"), (String) params.get("subject"), body, serial);

	}

	@PostMapping("/check")
	@ResponseBody
	public String CheckAuth(@RequestBody Map<String, Object> params) {
		log.info("email params={}", params);
		String serial = (String) params.get("serial");
		String username = (String) params.get("username");
		String getSerial = redisUserService.getUserByUsername(username);
		if (getSerial.equals(serial)) {
			return "1";
		} else {
			return "0";
		}
	}
}
