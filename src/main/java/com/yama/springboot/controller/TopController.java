package com.yama.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * トップのcontroller
 * @author yama
 * @version 1.0
 */
@Controller
public class TopController {

	@RequestMapping("/top")
	public ModelAndView init(ModelAndView mav) {
		// コメント外すとエラーになります
//		System.out.println(topicsDataList.getTitle());

		mav.setViewName("top");
		return mav;
	}
}
