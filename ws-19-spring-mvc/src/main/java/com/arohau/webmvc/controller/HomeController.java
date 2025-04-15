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

	@ModelAttribute("user")
	public User userModel(){
		return new User();
	}

	@GetMapping("/")
	public String home(Locale locale, Model model) {
		System.out.println("HomeController /");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@GetMapping("/registration")
	public String register(Model model) {
		System.out.println("HomeController /registration");
		System.out.println(model);
		return "registration";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("username", "John Doe");
		return "home";
	}

	@GetMapping("/user")
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getName());
		return "user";
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
