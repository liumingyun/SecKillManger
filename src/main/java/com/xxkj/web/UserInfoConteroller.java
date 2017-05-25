package com.xxkj.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxkj.bean.UserInfo;
import com.xxkj.eto.UserInfoResult;
import com.xxkj.exceptions.UserInfoExecption;
import com.xxkj.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/user")
// url:模块/资源/{}/细分
public class UserInfoConteroller {
	@Autowired
	private UserInfoService userInfoService;

	@ResponseBody
	@RequestMapping(value = "/regisiter", method = RequestMethod.POST)
	public UserInfoResult<UserInfoExecption> regisiter(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		UserInfoExecption result = userInfoService.register(username, password,
				email);
		// System.out.println(new UserInfoResult<UserInfoExecption>(true,
		// result));
		return new UserInfoResult<UserInfoExecption>(true, result);
	}

	@RequestMapping(value = "/actives", method = RequestMethod.GET)
	public String active(@RequestParam("username") String username,
			@RequestParam("code") String code) {
		UserInfoExecption result = userInfoService.Active(username, code);
		UserInfoResult<UserInfoExecption> u = new UserInfoResult<UserInfoExecption>(
				true, result);
		if (u.getData().getStateInfo().equals("激活成功")) {
			return "login";
		}
		return "";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email,
			@RequestParam("password") String password,HttpServletRequest req,
			HttpServletResponse response) {
		UserInfo userInfo = userInfoService.login(email, password);
		if (userInfo != null) {
			System.out.println("登陆成功");

			Cookie cookie = new Cookie("uid", userInfo.getUid() + "");
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 7);// 保留7天
			response.addCookie(cookie);
			return "redirect:/seckill/" + userInfo.getUid() + "/list";
		}
		return "login";
	}

}
