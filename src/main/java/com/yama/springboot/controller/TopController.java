package com.yama.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.service.TopService;

import lombok.RequiredArgsConstructor;

/**
 * トップのcontroller
 * @author yama
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class TopController {

	// TOPのサービスクラス
	final private TopService topService;

	@RequestMapping("/top")
	public ModelAndView init(ModelAndView mav) {

		// グループ名を指定する
		String groupName = "グループ1";

		// TOPICS情報からグループ1向けのデータのみ抽出する
		List<TopicsData> extractTopicsDataList = topService.extractTopics(groupName);

		// 抽出したTOPICS情報を画面に返す
		mav.addObject("topicsList", extractTopicsDataList);
		// TOP画面を表示する
		mav.setViewName("top");
		return mav;
	}
}
