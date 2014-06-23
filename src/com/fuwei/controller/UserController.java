package com.fuwei.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuwei.commons.LoginedUser;
import com.fuwei.commons.SystemContextUtils;
import com.fuwei.constant.Constants;
import com.fuwei.entity.Module;
import com.fuwei.entity.Role;
import com.fuwei.entity.User;
import com.fuwei.service.ModuleService;
import com.fuwei.service.RoleService;
import com.fuwei.service.UserService;
import com.fuwei.util.SerializeTool;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;
	
	/**
	 * 登录
	 * @throws Exception 
	 * @throws Exception
	 * 
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> Login (String username, String password,
			HttpSession session,HttpServletResponse response) throws Exception {
		try{
			User user = userService.login(username, password);
			LoginedUser loginUser = new LoginedUser();		
			loginUser.setLoginedUser(user);
			//获取登录用户的角色与权限
			Role role = null;
			List<Module> moduleList = null;
			Integer roleId = user.getRoleId();
			if(roleId != null){
				role = roleService.get(roleId);
				moduleList = moduleService.getList(roleId);
			}
			loginUser.setModulelist(moduleList);
			loginUser.setRole(role);
			session.setAttribute(Constants.LOGIN_SESSION_NAME, loginUser);
			return this.returnSuccess();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView Index (HttpSession session,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			LoginedUser user =  SystemContextUtils.getCurrentUser(session);
			return new ModelAndView("user/index");
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
