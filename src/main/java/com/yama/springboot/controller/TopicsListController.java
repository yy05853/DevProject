package com.yama.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;

/**
 * トピックス一覧画面のコントローラー
 * @author ttm
 *@version 1.0
 */
@Controller
public class TopicsListController {

	@RequestMapping("/TopicsList")
	public ModelAndView TopicsList(){
		ModelAndView mav = new ModelAndView();
/**		mav.addObject("id", DevProjectApplication.topicsDataList.get(0).getId());
		mav.addObject("sendTo", DevProjectApplication.topicsDataList.get(0).getSendTo());
		mav.addObject("sendFrom", DevProjectApplication.topicsDataList.get(0).getSendFrom());
		mav.addObject("title", DevProjectApplication.topicsDataList.get(0).getTitle());
		mav.addObject("displayStartTime", DevProjectApplication.topicsDataList.get(0).getDisplayStartTime());
		mav.addObject("displayEndTime", DevProjectApplication.topicsDataList.get(0).getDisplayEndTime());
*/

		List<TopicsData> list = DevProjectApplication.topicsDataList;
		mav.addObject("topicsList", list);
		mav.setViewName("TopicsList.html");
		return mav;

	}

}
