package com.app.ticketmanagement.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.ticketmanagement.App;
import com.app.ticketmanagement.dao.UserRepository;
import com.app.ticketmanagement.models.Ticket;
import com.app.ticketmanagement.models.User;
import com.app.ticketmanagement.validate.Message;

@Controller
public class MainController {
	private static final Logger log= LoggerFactory.getLogger(MainController.class);
   

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model) {
		log.info("This is Home page");
		model.addAttribute("title", "Ticket Generator");
		return "home";
		
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		log.info("This is signup Page");
		model.addAttribute("title", "Ticket Generator");
		model.addAttribute("user", new User());
		return "signup";
	}

	// Handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, Model model,
			HttpSession session) {

		try {
			if (result1.hasErrors()) {
				System.out.println("ERROR " + result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_USER");
		
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("USER" + user);
			User result = this.userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
			return "signup";
		} catch (Exception e) {
			log.error("This is error Message");
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went Wrong", "alert-danger"));
			return "signup";
		}

	}
	
	
	//handler for custom login
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		log.info("Welcome to signin Page");
		model.addAttribute("title","Login Page");
		return "login";
	}
}
