package com.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;

import cn.hutool.json.JSONObject;



@RestController
@RequestMapping(value="/error")
public class ViewControllerj {

	@GetMapping(value="/unauthor",produces="application/json;charset=utf-8")
	public Object unauthor() {
		
		JSONObject json = new JSONObject();
		json.put("returnMsg", "未授权,非法访问");
		json.put("returnCode","444444");
		return json;
	}
	
}
