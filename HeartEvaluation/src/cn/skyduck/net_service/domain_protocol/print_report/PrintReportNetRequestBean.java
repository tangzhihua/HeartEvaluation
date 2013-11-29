package cn.skyduck.net_service.domain_protocol.print_report;

import java.util.ArrayList;
import java.util.List;

import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

public final class PrintReportNetRequestBean {
	private List<FullQuestionnaireModel> questionnaireModels;
	private GlobalConstant.FunctionOptionsEnum optionsEnum;

	public PrintReportNetRequestBean(List<FullQuestionnaireModel> questionnaireModels, GlobalConstant.FunctionOptionsEnum optionsEnum) {
		// 保护性拷贝
		this.questionnaireModels = new ArrayList<FullQuestionnaireModel>(questionnaireModels);
		this.optionsEnum = optionsEnum;

		if (this.questionnaireModels.size() <= 0 || this.optionsEnum == null) {
			throw new IllegalArgumentException("入参非法 : this.questionnaireModels.size() <= 0 || this.optionsEnum == null");
		}
	}

	public List<FullQuestionnaireModel> getQuestionnaireModels() {
		return new ArrayList<FullQuestionnaireModel>(questionnaireModels);
	}

	public GlobalConstant.FunctionOptionsEnum getOptionsEnum() {
		return optionsEnum;
	}

}
