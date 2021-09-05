package com.yama.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.repository.TopicsDataRepository;

import lombok.RequiredArgsConstructor;

/**
 * トップのservice
 * @author yama
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class TopService {

	private final TopicsDataRepository topicsDataRepository;

	/**
	 * 全量のトピックス情報から指定されたグループの情報のみ抽出する
	 * @param groupName 抽出対象のグループ名
	 * @return 抽出後のトピックス情報
	 */
	public List<TopicsData> extractTopics(String groupName) {

		return topicsDataRepository.getAllTopics().stream()
				.filter(topics -> groupName.equals(topics.getSendTo()))
				.collect(Collectors.toList());
	}
	public List<TopicsData> testTrue(String groupName) {
		List<TopicsData> datalist = new ArrayList<>();
		return datalist;
	}
}
