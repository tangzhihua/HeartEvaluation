package cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.RespondentsInformationBase;

/**
 * 受访者信息 - 成人
 * 
 * @author hesiming
 * 
 */
@SuppressWarnings("serial")
public class RespondentsInformationOfAdults extends RespondentsInformationBase implements Serializable {

	public RespondentsInformationOfAdults(QuestionnaireCodeEnum questionnaireCodeEnum) {
		super(questionnaireCodeEnum);
	}

	// 必选项扩展枚举, 这里罗列了那些可以被设置成必选项的属性
	public static enum NecessaryOptionEnum {
		// 文化程度
		education,
		// 评定时间范围(3个月, 6个月, 9个月, 12个月)
		assessmentTimeRange
	}

	// 扩展项扩展枚举, 这里罗列了那些可以增加显示的属性, 如果没有就会隐藏
	public static enum ExpandOptionEnum {
		// 居住区域(属于 南方人还是北方人)
		residentialArea,
		// 评定时间范围(3个月, 6个月, 9个月, 12个月)
		assessmentTimeRange,
		// 评定次数
		numberOfTest;
	}

	// 职业
	private String profession;

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	// 婚姻状况
	private GlobalConstant.MaritalStatusEnum maritalStatusEnum;

	public GlobalConstant.MaritalStatusEnum getMaritalStatusEnum() {
		return maritalStatusEnum;
	}

	public void setMaritalStatusEnum(GlobalConstant.MaritalStatusEnum maritalStatusEnum) {
		this.maritalStatusEnum = maritalStatusEnum;
	}

	// 文化程度
	private GlobalConstant.EducationEnum educationEnum;

	public GlobalConstant.EducationEnum getEducationEnum() {
		return educationEnum;
	}

	public void setEducationEnum(GlobalConstant.EducationEnum educationEnum) {
		this.educationEnum = educationEnum;
	}

	// 下面是需要特殊定制的

	// 居住区域(属于 南方人还是北方人)
	private GlobalConstant.ResidentialAreaEnum residentialAreaEnum = null;

	public GlobalConstant.ResidentialAreaEnum getResidentialAreaEnum() {
		return residentialAreaEnum;
	}

	public void setResidentialAreaEnum(GlobalConstant.ResidentialAreaEnum residentialAreaEnum) {
		this.residentialAreaEnum = residentialAreaEnum;
	}

	// 评定时间范围(3个月, 6个月, 9个月, 12个月)
	private GlobalConstant.AssessmentTimeRangeEnum assessmentTimeRangeEnum = null;

	public GlobalConstant.AssessmentTimeRangeEnum getAssessmentTimeRangeEnum() {
		return assessmentTimeRangeEnum;
	}

	public void setAssessmentTimeRangeEnum(GlobalConstant.AssessmentTimeRangeEnum assessmentTimeRangeEnum) {
		this.assessmentTimeRangeEnum = assessmentTimeRangeEnum;
	}

	// 评定次数
	private String numberOfTest;

	public String getNumberOfTest() {
		return numberOfTest;
	}

	public void setNumberOfTest(String numberOfTest) {
		this.numberOfTest = numberOfTest;
	}

	/**
	 * 获取受访者信息的字段列表
	 * 
	 * @return
	 */
	public List<String> getRespondentsInformationFieldList() {
		final List<String> itemList = new ArrayList<String>(super.getRespondentsInformationFieldList());
		// 文化程度
		String educationString = null;
		if (educationEnum != null) {
			educationString = educationEnum.getName();
		}
		itemList.add(educationString);
		// 婚姻状况
		String maritalStatusString = null;
		if (maritalStatusEnum != null) {
			maritalStatusString = maritalStatusEnum.getName();
		}
		itemList.add(maritalStatusString);

		return itemList;
	}
}
