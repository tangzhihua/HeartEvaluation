package cn.skyduck.questionnaire;

import cn.skyduck.questionnaire.model.FullQuestionnaireModel;


/**
 * 创建一张问卷(整张 量表)
 * 
 * @author skyduck
 * 
 */
public interface ICreateQuestionnaireModelFactory {
	public FullQuestionnaireModel createQuestionnaireModel();
}
