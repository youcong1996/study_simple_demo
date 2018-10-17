package com.blog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.kisso.AuthToken;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.SSOProperties;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.blog.entity.User;
import com.blog.service.UserService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author youcong123
 * @since 2018-07-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Login(action = Action.Skip)
	@PostMapping(value="login",produces="application/json;charset=utf-8")
	public Object login(HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject json  = new JSONObject();
	

	        String returnUrl = request.getParameter(SSOConfig.getInstance().getParamReturl());
	        
	        System.out.println("returnUrl:"+returnUrl);
	        Token token = SSOHelper.getToken(request);
	        
	         if(token!=null) {
	       
	            /**
	             * 正常登录 需要过滤sql及脚本注入
	             */
	            WafRequestWrapper wr = new WafRequestWrapper(request);
	            String loginCode = wr.getParameter("loginCode");
	            String password = wr.getParameter("password");

	       

	            if (loginCode != null && !"".equals(loginCode)) {

	            	
	            	
	                User user = userService.login(loginCode);
	                if(user != null){
	                    if(user.getPassword().equals(SecureUtil.md5(password))){

	                        /*
	                         * 设置登录 Cookie
	                         * 最后一个参数 true 时添加 cookie 同时销毁当前 JSESSIONID 创建信任的 JSESSIONID
	                         */
	                        SSOToken st = new SSOToken(request, user.getUserId()+"");

	                        SSOHelper.setSSOCookie(request, response, st, true);
	                        json.put("returnCode", "000000");
	            			json.put("returnMsg", "success");

	                    }else{//证明密码不匹配
	                    	json.put("returnCode", "333333");
	            			json.put("returnMsg", "pass_error");
	                    	
	                    }

	              
	                }else {
	                  	json.put("returnCode", "222222");
            			json.put("returnMsg", "user error");
	                }

	            }else {
	             	json.put("returnCode", "111111");
        			json.put("returnMsg", "login_code error");
	            }
	         }else {
	        		json.put("returnCode", "444444");
        			json.put("returnMsg", "token error");
	         }
	            return json;

		
		
	}
	
	@GetMapping(value="getUserById/{userId}",produces="application/json;charset=utf-8")
	public Object getUserById(@PathVariable String userId) {
		
		JSONObject json  = new JSONObject();
		
		if(!StrUtil.isEmpty(userId)) {
			User user = userService.getUserById(userId);
			json.put("returnCode", "000000");
			json.put("returnMsg", "success");
			json.put("user", user);
		}else {
			json.put("returnCode", "111111");
			json.put("returnMsg", "error");
		}
		return json;

	}
	
	

	    @GetMapping(value="replylogin",produces="application/json;charset=utf-8")
	    public Object replylogin(HttpServletRequest request, HttpServletResponse response) {
	    	JSONObject json  = new JSONObject();
	     
	        Token token = SSOHelper.getToken(request);
	        if (token != null) {
	            String askData = request.getParameter("askData");
	            if (askData != null && !"".equals(askData)) {
	                /**
	                 *
	                 * 用户自定义配置获取
	                 *
	                 * <p>
	                 * 由于不确定性，kisso 提倡，用户自己定义配置。
	                 * </p>
	                 *
	                 */
	                SSOProperties prop = SSOConfig.getSSOProperties();

	                //下面开始验证票据，签名新的票据每一步都必须有。
	                AuthToken at = SSOHelper.replyCiphertext(request, askData);
	                if (at != null) {

	                    //1、业务系统公钥验证签名合法性（此处要支持多个跨域端，取 authToken 的 app 名找到对应系统公钥验证签名）
	                    at = at.verify(prop.get("sso.defined." + at.getApp() + "_public_key"));
	                    if (at != null) {

	                        //at.getUuid() 作为 key 设置 authToken 至分布式缓存中，然后 sso 系统二次验证
	                        //at.setData(data); 设置自定义信息，当然你也可以直接 at.setData(token.jsonToken()); 把当前 SSOToken 传过去。

	                        at.setUid(token.getUid());//设置绑定用户ID
	                        at.setTime(token.getTime());//设置登录时间

	                        //2、SSO 的私钥签名
	                        at.sign(prop.get("sso.defined.sso_private_key"));

	                     
	                        
	                        json.put("returnCode", "000000");
	            			json.put("returnMsg", "success");
	                    } else {
	            
	                        
	                        json.put("returnCode", "000000");
	            			json.put("returnMsg", "error -1");
	                    }
	                } else {
	            
	                    
	                    json.put("returnCode", "333333");
	        			json.put("returnMsg", "error -2");
	                }
	            }
	        } else {
	        	json.put("returnCode", "333333");
      			json.put("returnMsg", "error -2");
	        }
	        
	        return json;
	    

	    }

	@PostMapping(value="/logout",produces="application/json;charset=utf-8")
	public Object logout(HttpServletRequest request,HttpServletResponse response) {
		JSONObject json  = new JSONObject();
		
		SSOHelper.clearLogin(request, response);
		
		json.put("returnCode", "000000");
		json.put("returnMsg", "logout success");
		
		return json;
	}
	


	
	
	
}

