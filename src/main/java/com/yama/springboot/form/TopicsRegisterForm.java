package com.yama.springboot.form;

import java.io.Serializable;

import lombok.Data;

/**
 * トピックス設定画面の情報
 * @author tatta
 *@version 1.0
 */
@Data
public class TopicsRegisterForm implements Serializable {
	// ID
	private int id;
	// タイトル
	private String title;
	// 表示開始日時
//	private Timestamp displayStartTime;
	private String displayStartDate;
	private String displayStartTime;
	// 表示終了日時
//	private Timestamp displayEndTime;
	private String displayEndDate;
	private String displayEndTime;
	// 配布先グループ
	private String sendTo;
	// 配布元グループ
	private String sendFrom;
}
