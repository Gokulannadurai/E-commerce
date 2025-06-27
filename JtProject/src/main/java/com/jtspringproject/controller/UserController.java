package com.jtspringproject.controller;

import com.jtspringproject.models.Cart;
import com.jtspringproject.models.Product;
import com.jtspringproject.models.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jtspringproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.services.IUserService;
import com.jtspringproject.services.IProductService;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

/**
 * Controller for handling user-facing functionalities.
 * This includes user registration, login, viewing products, and managing user profiles.
 */
@Controller
public class UserController{

	private final IUserService userService;
	private final IProductService productService;

	@Autowired
	public UserController(IUserService userService, IProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}

	@GetMapping("/login")
	public ModelAndView userlogin(@RequestParam(required = false) String error) {
	    ModelAndView mv = new ModelAndView("userLogin");
	    if ("true".equals(error)) {
	        mv.addObject("msg", "Please enter correct email and password");
	    }
	    return mv;
	}
	
	@GetMapping("/")
	public ModelAndView indexPage()
	{
		ModelAndView mView  = new ModelAndView("index");	
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		mView.addObject("username", username);
		List<Product> products = this.productService.getProducts();

		if (products.isEmpty()) {
			mView.addObject("msg", "No products are available");
		} else {
			mView.addObject("products", products);
		}
		return mView;
	}
	
	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}
	
	@PostMapping("/newuserregister")
	public ModelAndView newUseRegister(@Valid @ModelAttribute User user, BindingResult result)
	{
		if (result.hasErrors()) {
			ModelAndView mView = new ModelAndView("register");
			mView.addObject("msg", "Validation failed! Please check your input.");
			return mView;
		}
		long startTime = System.currentTimeMillis();
		
		boolean exists = this.userService.checkUserExists(user.getUsername());

		if(!exists) {
			user.setRole("ROLE_NORMAL");
			this.userService.addUser(user);
			
			long endTime = System.currentTimeMillis();
			System.out.println("[PERFORMANCE] newUseRegister (Success): " + (endTime - startTime) + "ms");

			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "User registered successfully! Please login.");
			return mView;
		} else {
			long endTime = System.currentTimeMillis();
			System.out.println("[PERFORMANCE] newUseRegister (Username Taken): " + (endTime - startTime) + "ms");

			ModelAndView mView = new ModelAndView("register");
			mView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
			return mView;
		}
	}

	@GetMapping("/profileDisplay")
	public String profileDisplay(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("user", user);
		return "updateProfile";
	}
	

	   //for Learning purpose of model
		@GetMapping("/test")
		public String Test(Model model)
		{
			System.out.println("test page");
			model.addAttribute("author","jay gajera");
			model.addAttribute("id",40);
			
			List<String> friends = new ArrayList<String>();
			model.addAttribute("f",friends);
			friends.add("xyz");
			friends.add("abc");
			
			return "test";
		}
		
		// for learning purpose of model and view ( how data is pass to view)
		
		@GetMapping("/test2")
		public ModelAndView Test2()
		{
			System.out.println("test page");
			//create modelandview object
			ModelAndView mv=new ModelAndView();
			mv.addObject("name","jay gajera 17");
			mv.addObject("id",40);
			mv.setViewName("test2");
			
			List<Integer> list=new ArrayList<Integer>();
			list.add(10);
			list.add(25);
			mv.addObject("marks",list);
			return mv;
			
			
		}
}