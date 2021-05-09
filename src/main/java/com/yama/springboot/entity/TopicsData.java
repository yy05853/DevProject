package com.yama.springboot.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * トピックスデータのentity
 * @author yama
 * @version 1.0
 */
@Data
public class TopicsData {
	// ID
	private int id;
	// 配布先グループ
	private String sendTo;
	// 配布元グループ
	private String sendFrom;
	// タイトル
	private String title;
	// 表示開始日時
	private Timestamp displayStartTime;
	// 表示終了日時
	private Timestamp displayEndTime;
}
