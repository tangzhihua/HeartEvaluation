package cn.skyduck.question_frame_fragment.fill_tester_personal_information.children;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.RespondentsInformationBase;

/**
 * 受访者信息 - 儿童
 * 
 * @author hesiming
 * 
 */
@SuppressWarnings("serial")
public class RespondentsInformationOfChildren extends RespondentsInformationBase implements Serializable {
	public RespondentsInformationOfChildren(QuestionnaireCodeEnum questionnaireCodeEnum) {
		super(questionnaireCodeEnum);
	}

	// 填表人
	private GlobalConstant.WhoWillReplaceTheAnswerEnum whoWillReplaceTheAnswerEnum;

	public GlobalConstant.WhoWillReplaceTheAnswerEnum getWhoWillReplaceTheAnswerEnum() {
		return whoWillReplaceTheAnswerEnum;
	}

	public void setWhoWillReplaceTheAnswerEnum(GlobalConstant.WhoWillReplaceTheAnswerEnum whoWillReplaceTheAnswerEnum) {
		this.whoWillReplaceTheAnswerEnum = whoWillReplaceTheAnswerEnum;
	}

	/**
	 * 获取受访者信息的字段列表
	 * 
	 * @return
	 */
	public List<String> getRespondentsInformationFieldList() {
		final List<String> itemList = new ArrayList<String>(super.getRespondentsInformationFieldList());
		// 教育程度
		itemList.add(null);
		// 婚姻状况
		itemList.add(null);

		return itemList;
	}
}
