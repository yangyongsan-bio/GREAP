package com.lyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/view/{template}")
	public String forward(@PathVariable("template") String view){
		System.out.println("view-name:"+view);
		return view;
	}
}
