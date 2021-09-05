package com.yama.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.repository.TopicsDataRepository;

import lombok.RequiredArgsConstructor;

/**
 * トピックス一覧画面のコントローラー
 * @author ttm
 *@version 1.0
 */
@RequiredArgsConstructor
@Controller
public class TopicsListController {

	private final TopicsDataRepository topicsDataRepository;

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

		List<TopicsData> list = topicsDataRepository.getAllTopics();
		mav.addObject("topicsList", list);
		mav.setViewName("TopicsList.html");
		return mav;

	}

}
