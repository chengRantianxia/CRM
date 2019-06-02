package com.briup.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowPageController {
	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
	@RequestMapping("/left")
	public String showLeft(){
		return "left";
	}
	@RequestMapping("/switch")
	public String showSwitch(){
		return "switch";
	}
	@RequestMapping("/top")
	public String showTop(){
		return "top";
	}
	@RequestMapping("/help")
	public String showHelp(){
		return "help";
	}
}
