package cn.skyduck.question_frame_fragment.embu;

import java.io.Serializable;

import cn.skyduck.global_data_cache.GlobalConstant;

@SuppressWarnings("serial")
public class EMBUPartOnePageFourUserAnswerDataSource implements Serializable {
	// 母亲文化程度
	private GlobalConstant.EducationEnum educationEnumOfMother = null;

	// 母亲职业
	private GlobalConstant.JobTypeEnum jobTypeEnumOfMother = null;

	public GlobalConstant.EducationEnum getEducationEnumOfMother() {
		return educationEnumOfMother;
	}

	public void setEducationEnumOfMother(GlobalConstant.EducationEnum educationEnumOfMother) {
		this.educationEnumOfMother = educationEnumOfMother;
	}

	public GlobalConstant.JobTypeEnum getJobTypeEnumOfMother() {
		return jobTypeEnumOfMother;
	}

	public void setJobTypeEnumOfMother(GlobalConstant.JobTypeEnum jobTypeEnumOfMother) {
		this.jobTypeEnumOfMother = jobTypeEnumOfMother;
	}

}
