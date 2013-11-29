package cn.skyduck.question_frame_fragment.embu;

import java.io.Serializable;

import cn.skyduck.global_data_cache.GlobalConstant;

@SuppressWarnings("serial")
public class EMBUPartOnePageThreeUserAnswerDataSource implements Serializable {
	// 父亲文化程度
	private GlobalConstant.EducationEnum educationEnumOfFather = null;

	// 父亲职业
	private GlobalConstant.JobTypeEnum jobTypeEnumOfFather = null;

	public GlobalConstant.EducationEnum getEducationEnumOfFather() {
		return educationEnumOfFather;
	}

	public void setEducationEnumOfFather(GlobalConstant.EducationEnum educationEnumOfFather) {
		this.educationEnumOfFather = educationEnumOfFather;
	}

	public GlobalConstant.JobTypeEnum getJobTypeEnumOfFather() {
		return jobTypeEnumOfFather;
	}

	public void setJobTypeEnumOfFather(GlobalConstant.JobTypeEnum jobTypeEnumOfFather) {
		this.jobTypeEnumOfFather = jobTypeEnumOfFather;
	}

}
