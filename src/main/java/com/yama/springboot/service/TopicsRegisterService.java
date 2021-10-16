package com.yama.springboot.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.form.TopicsRegisterForm;
import com.yama.springboot.repository.TopicsDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TopicsRegisterService {

	private final TopicsDataRepository topicsDataRepository;

	/**
	 *  FIXME: サービスクラスにプレゼンテーション層のオブジェクトが混じってるのはよくなさそう
	 *  依存の方向が、form ← serviceになってしまう
	 *  あとは扱ってるのがDTOじゃなくてentityなのは要修正
	 */


	/**
	 * 引数のトピックスIDに該当するフォーム情報を構築する
	 * @param topicsId
	 * @return
	 */
	public TopicsRegisterForm buildFormById(String topicsId) {

		TopicsData data = searchTopicsData(topicsId);
		return createFrom(data);
	}

	/**
	 * トピックスデータを新しく登録する
	 * @param form
	 * @return
	 */
	public boolean createTopicsData(TopicsRegisterForm form) {
		TopicsData data = new TopicsData();
		data = createFrom(form);
		data.setId(UUID.randomUUID().hashCode());
		return saveTopicsData(data);
	}
	/**
	 * トピックスデータを更新する
	 * @param form
	 * @return
	 */
	public boolean updateTopicsData(TopicsRegisterForm form) {
		TopicsData data = new TopicsData();
		data = createFrom(form);
		return saveTopicsData(data);
	}
	/**
	 * トピックスデータを削除する
	 * @param form
	 * @return
	 */
	public boolean deleteTopicsData(TopicsRegisterForm form) {
		TopicsData data = new TopicsData();
		data = createFrom(form);
		return deleteTopicsData(data);
	}


	/**
	 * TODO: DBが構築されたら不要
	 */
	/**
	 * トピックスデータを探索する
	 * @param topicsId
	 * @return
	 */
	private TopicsData searchTopicsData(String topicsId) {

		// トピックスデータのリストから、引数のIDに該当するものを探し出す
		List<TopicsData> dataList =
		DevProjectApplication.topicsDataList.stream()
																.filter(item -> Integer.toString(item.getId()).equals(topicsId))
																.collect(Collectors.toList());

		// 念の為チェック
		// FIXME: ここでスローするのはあまりよろしくない気がする
		if(dataList.size() > 1) {
			throw new IllegalStateException("同じIDのデータが２つ以上存在してるよ");
		}

		// データが取得できていない場合は、空のインスタンスを返す
		return dataList.size() == 0 ? new TopicsData() : dataList.get(0);
	}

	/**
	 * トピックスデータを探索する
	 * @param topicsId
	 * @return
	 */
	private int indexOf(int topicsId) {
		List<TopicsData> dataList = topicsDataRepository.getAllTopics();
		// トピックスデータのリストから、引数のIDに該当するものを探し出す
		for(int i = 0, ilen = dataList.size(); i < ilen; i++) {
			if(dataList.get(i).getId() == topicsId){
				return i;
			}
		}
		return -1;
	}


	/**
	 * トピックスデータを保存する
	 * @param data
	 * @return
	 */
	private boolean saveTopicsData(TopicsData data) {
		try {
			int index = indexOf(data.getId());
			if(index == -1) {
				topicsDataRepository.addTopics(data);
				//				DevProjectApplication.topicsDataList.add(data);
//				System.out.println("登録されたよ：" + data.getId());
			}else {
				DevProjectApplication.topicsDataList.set(index, data);
//				System.out.println("更新されたよ：" + data.getId());
			}

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * トピックスデータを保存する
	 * @param data
	 * @return
	 */
	private boolean deleteTopicsData(TopicsData data) {
		try {
			int index = indexOf(data.getId());
			if(index == -1) {
				return true;
			}else {
				DevProjectApplication.topicsDataList.remove(index);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * フォームにデータを詰める
	 * @param data
	 * @return
	 */
	private TopicsRegisterForm createFrom(TopicsData data) {
		TopicsRegisterForm form = new TopicsRegisterForm();

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");

		form.setId(data.getId());
		form.setTitle(data.getTitle());
		form.setDisplayStartDate(sdfDate.format(data.getDisplayStartTime()));
		form.setDisplayStartTime(sdfTime.format(data.getDisplayStartTime()));
		if(data.getDisplayEndTime() != null) {
			form.setDisplayEndDate(sdfDate.format(data.getDisplayEndTime()));
			form.setDisplayEndTime(sdfTime.format(data.getDisplayEndTime()));
		}
//		form.setDisplayEndTime(sdf.format(data.getDisplayEndTime()));
		form.setSendTo(data.getSendTo());
		form.setSendFrom(data.getSendFrom());
		return form;
	}

	private TopicsData createFrom(TopicsRegisterForm form) {
		TopicsData data = new TopicsData();

		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd,hh:mm");

		data.setId(form.getId());
		data.setTitle(form.getTitle());
		try {
			data.setDisplayStartTime(
					new Timestamp(
							sdf.parse(
									form.getDisplayStartDate()
									+ ","
									+ form.getDisplayStartTime()).getTime()));
			data.setDisplayEndTime(
					new Timestamp(
							sdf.parse(
									form.getDisplayEndDate()
									+ ","
									+ form.getDisplayEndTime()).getTime()));
		} catch(ParseException e) {
			// 何もしない
			e.printStackTrace();
		}
		data.setSendTo(form.getSendTo());
		data.setSendFrom(form.getSendFrom());
		return data;
	}

}
