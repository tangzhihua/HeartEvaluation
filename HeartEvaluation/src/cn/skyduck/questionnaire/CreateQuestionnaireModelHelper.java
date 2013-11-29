package cn.skyduck.questionnaire;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

import android.text.TextUtils;

/**
 * 创建 "问卷模型" 对象的助手类
 * 
 * @author zhihua.tang
 * 
 */
public final class CreateQuestionnaireModelHelper {
	/**
	 * 
	 */
	private static QuestionnaireModelClassNameMapping strategyClassNameMapping = new QuestionnaireModelClassNameMapping();

	/**
	 * 
	 * @param codeEnum
	 * @return
	 * @throws Exception
	 */
	public static FullQuestionnaireModel getQuestionnaireModelByQuestionnaireCodeEnum(QuestionnaireCodeEnum codeEnum) throws Exception {

		if (codeEnum == null) {
			throw new NullPointerException("codeEnum is empty!");
		}

		String className = strategyClassNameMapping.getTargetClassNameForKey(codeEnum.name());
		if (TextUtils.isEmpty(className)) {
			// 找不到对应的算法类
			throw new IllegalStateException("codeEnum is invalid!");
		}

		@SuppressWarnings("rawtypes")
		Class cl = Class.forName(className);
		FullQuestionnaireModel questionnaireModel = (FullQuestionnaireModel) cl.newInstance();

		if (questionnaireModel == null) {
			throw new NullPointerException("创建 '问卷模型' 对象失败!");
		}
		return questionnaireModel;
	}
}
