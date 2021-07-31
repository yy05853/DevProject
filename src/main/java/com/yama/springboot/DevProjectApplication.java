package com.yama.springboot;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yama.springboot.entity.TopicsData;

@SpringBootApplication
public class DevProjectApplication {

	// トピックスデータリスト
	public static List<TopicsData> topicsDataList;

	public static void main(String[] args) {

		topicsDataList = new LinkedList<>();

		// 1つ目のトピックスデータを作成する
		TopicsData topicsData = new TopicsData();
		topicsData.setId(1);
		topicsData.setSendTo("グループ1");
		topicsData.setSendFrom("グループ1");
		topicsData.setTitle("コロナによる全面リモートワークのお知らせ");
		try {
			topicsData.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		topicsData.setDisplayEndTime(null);
		topicsDataList.add(topicsData);

		SpringApplication.run(DevProjectApplication.class, args);
	}

	public List<TopicsData> findByName(String groupName) {
		return DevProjectApplication.topicsDataList.stream()
				.filter(topics -> groupName.equals(topics.getSendTo()))
				.collect(Collectors.toList());
	}

}
