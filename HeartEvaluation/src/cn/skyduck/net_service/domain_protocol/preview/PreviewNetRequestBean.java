package cn.skyduck.net_service.domain_protocol.preview;

import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

public final class PreviewNetRequestBean {
	private FullQuestionnaireModel questionnaireModel;
	private GlobalConstant.FunctionOptionsEnum optionsEnum;

	public PreviewNetRequestBean(FullQuestionnaireModel questionnaireModel, GlobalConstant.FunctionOptionsEnum optionsEnum) {
		this.questionnaireModel = questionnaireModel;
		this.optionsEnum = optionsEnum;

		if (this.questionnaireModel == null || this.optionsEnum == null) {
			throw new IllegalArgumentException("入参非法 : this.questionnaireModel == null || this.optionsEnum == null");
		}
	}

	public FullQuestionnaireModel getQuestionnaireModel() {
		return questionnaireModel;
	}

	public GlobalConstant.FunctionOptionsEnum getOptionsEnum() {
		return optionsEnum;
	}

}
