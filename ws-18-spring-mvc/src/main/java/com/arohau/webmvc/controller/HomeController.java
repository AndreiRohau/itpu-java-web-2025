package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("username", "John Doe");
		return "home"; // Thymeleaf will resolve to templates/home.html
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@ModelAttribute("user")
	public User userModel(){
		return new User();
	}

	@GetMapping("/user")
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getName());
		return "user";
	}

	@GetMapping("/register")
	public String register(Model model) {
		System.out.println("call register");
		System.out.println(model);
		return "registration";
	}

	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("user") User user,
						   BindingResult bindingResult, Model model) {
		System.out.println("call register");
		System.out.println(user);
		System.out.println(bindingResult);
		System.out.println(model);
		return "registration";
	}
}
