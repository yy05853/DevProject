package com.yama.springboot.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.form.TopicsRegisterForm;
import com.yama.springboot.service.TopicsRegisterService;

import lombok.RequiredArgsConstructor;

/**
 * トピックス設定画面のコントローラー
 * @author tatta
 *@version 1.0
 */
@Controller
@RequiredArgsConstructor
public class TopicsRegisterController {

	// 画面名
	final private String VIEW_NAME = "topicsRegister";

	// 参照用
	final private List<TopicsData> dataList = DevProjectApplication.topicsDataList;

	// メッセージ定義用のこんすと
	public enum TopicsRegisterMsgConst {
		I_TPC_REG_OK("I-TPC-001", "登録成功したよ"),
		I_TPC_UPD_OK("I-TPC-002", "更新成功したよ"),
		I_TPC_DEL_OK("I-TPC-003", "削除成功したよ"),
		E_TPC_REG_NG("E-TPC-001", "登録失敗..."),
		E_TPC_UPD_NG("E-TPC-002", "更新失敗..."),
		E_TPC_DEL_NG("E-TPC-003", "削除失敗...");

		String msgCode;
		String msgDetail;

		TopicsRegisterMsgConst(String msgCode, String msgDetail){
			this.msgCode = msgCode;
			this.msgDetail = msgDetail;
		}
		public String getMsgCode() {
			return this.msgCode;
		}
		public String getMsgDetail() {
			return this.msgDetail;
		}
	}

	// トピックス設定サービスクラス
	final private TopicsRegisterService topicsRegisterService;

//	@Autowired
//	public TopicsRegisterController(TopicsRegisterService topicsRegisterService){
//		this.topicsRegisterService = topicsRegisterService;
//	}


	/**
	 * 初期表示（新規登録）
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/new", method=RequestMethod.GET)
	public ModelAndView newTopics(ModelAndView mav) {
		TopicsRegisterForm form = newForm();

		this.setForm(mav, form);
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 初期表示（更新）
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}/edit", method=RequestMethod.GET)
	public ModelAndView initById(ModelAndView mav, @PathVariable("id") String topicsId) {
		TopicsRegisterForm form = newForm();

		// TODO:Formクラスでバリデーションする
		if(Pattern.matches("^[^0-9]+$", topicsId)) {
			mav.setViewName("redirect:/" + VIEW_NAME + "/new");
			return mav;
		}

		// リクエストパラメータのIDを使用する
		form.setId(Integer.parseInt(topicsId));
		form = topicsRegisterService.buildFormById(topicsId);

		// formとviewの設定
		this.setForm(mav, form);
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 登録処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/new", method=RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute("form") TopicsRegisterForm form,
												BindingResult result, ModelAndView mav) {
		boolean isSuccess = topicsRegisterService.createTopicsData(form);
		if(isSuccess) {
			mav.addObject("msg", TopicsRegisterMsgConst.I_TPC_REG_OK.getMsgDetail());
		}else {
			mav.addObject("msg", TopicsRegisterMsgConst.E_TPC_REG_NG.getMsgDetail());
		}
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 更新処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}/edit", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("form") TopicsRegisterForm form,
												BindingResult result, ModelAndView mav, @PathVariable("id") String topicsId) {
		boolean isSuccess = topicsRegisterService.updateTopicsData(form);

		if(isSuccess) {
			mav.addObject("msg", TopicsRegisterMsgConst.I_TPC_UPD_OK.getMsgDetail());
		}else {
			mav.addObject("msg", TopicsRegisterMsgConst.E_TPC_UPD_NG.getMsgDetail());
		}
		mav.setViewName(VIEW_NAME);
		return mav;
	}

	/**
	 * 削除処理
	 * @param mav
	 * @return
	 */
	@RequestMapping(path="/topicsRegister/{id}/delete", method=RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("form") TopicsRegisterForm form,
												BindingResult result, ModelAndView mav, @PathVariable("id") String topicsId) {
		topicsRegisterService.deleteTopicsData(form);
		mav.setViewName("redirect:/" + VIEW_NAME + "/new");
		return mav;
	}



	// --- private method

	/**
	 * 新しいフォーム情報を生成する
	 * @return
	 */
	private TopicsRegisterForm newForm() {
		return new TopicsRegisterForm();
	}

	/**
	 * ModelAndViewにフォーム情報を設定する
	 * @param mav
	 * @return
	 */
	private void setForm(ModelAndView mav, TopicsRegisterForm form) {
		mav.addObject("form", form);
	}

}
