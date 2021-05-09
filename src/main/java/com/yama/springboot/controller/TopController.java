package com.yama.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.DevProjectApplication;

/**
 * トップのcontroller
 * @author yama
 * @version 1.0
 */
@Controller
public class TopController {

	@RequestMapping("/top")
	public ModelAndView init(ModelAndView mav) {
		System.out.println(DevProjectApplication.topicsDataList.get(0).getTitle());

		mav.setViewName("top");
		return mav;
	}
}
