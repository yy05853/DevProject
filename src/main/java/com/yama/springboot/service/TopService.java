package com.yama.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;

/**
 * トップのservice
 * @author yama
 * @version 1.0
 */
@Service
public class TopService {

	private DevProjectApplication devProjectApplication;
	/**
	 * 全量のトピックス情報から指定されたグループの情報のみ抽出する
	 * @param groupName 抽出対象のグループ名
	 * @return 抽出後のトピックス情報
	 */
	public List<TopicsData> extractTopics(String groupName) {

		return devProjectApplication.findByName("グループ1");
	}
	public List<TopicsData> testTrue(String groupName) {
		List<TopicsData> datalist = new ArrayList<>();
		return datalist;
	}
}
