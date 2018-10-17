package com.blog.controller.permission;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;


@RestController
@RequestMapping(value="/error/permission")
public class ViewController {

	@Permission(action=Action.Skip)
	@RequestMapping("/illegalAccess")
	public String illegalAccess() {
		return "illegal access";
	}
	
}
