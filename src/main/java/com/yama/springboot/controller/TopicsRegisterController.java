package com.yama.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.form.TopicsRegisterForm;

/**
 * トピックス設定画面のコントローラー
 * @author tatta
 *@version 1.0
 */
@Controller
public class TopicsRegisterController {

	// 画面名
	private final String VIEW_NAME = "topicsRegister";


	/**
	 * 初期表示（新規登録）
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/new", method=RequestMethod.GET)
	public ModelAndView newTopics(ModelAndView mav) {

		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 初期表示（更新）
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}", method=RequestMethod.GET)
	public ModelAndView initById(ModelAndView mav) {

		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 登録処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister", method=RequestMethod.POST)
	public ModelAndView regist(ModelAndView mav) {
		TopicsRegisterForm form = (TopicsRegisterForm)mav.getModel().get("form");
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 更新処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}", method=RequestMethod.PUT)
	public ModelAndView update(ModelAndView mav) {
		TopicsRegisterForm form = (TopicsRegisterForm)mav.getModel().get("form");
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 削除処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}", method=RequestMethod.DELETE)
	public ModelAndView delete(ModelAndView mav) {
		TopicsRegisterForm form = (TopicsRegisterForm)mav.getModel().get("form");
		mav.setViewName(VIEW_NAME);
		return mav;
	}


}
