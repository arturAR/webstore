package com.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.configuration.SecurityConfiguration;
import com.packt.webstore.model.WebstoreUser;
import com.packt.webstore.service.UserService;

@Controller
public class RegisterUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String getAddNewUserForm(Model model){
		WebstoreUser user = new WebstoreUser();
		model.addAttribute("newUser", user);
		return "registerUser";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processAddNewUserForm(@ModelAttribute(name="newUser") WebstoreUser user, 
			BindingResult result, HttpServletRequest request) {
		try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfiguration.PASSWORD_STRENGHT);
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);

        } catch (Exception e) {
            return "redirect:/register";
        }
	
		userService.save(user);
		return "redirect:/login";
	}
}
