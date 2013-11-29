package cn.skyduck.question_frame_fragment.fill_tester_personal_information;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;

/**
 * 受访者信息父类
 * 
 * @author hesiming
 * 
 */
@SuppressWarnings("serial")
public abstract class RespondentsInformationBase implements Serializable {

	public RespondentsInformationBase(QuestionnaireCodeEnum questionnaireCodeEnum) {
		this.questionnaireCodeEnum = questionnaireCodeEnum;
	}

	// 当前量表类型
	private QuestionnaireCodeEnum questionnaireCodeEnum;

	public QuestionnaireCodeEnum getQuestionnaireCodeEnum() {
		return questionnaireCodeEnum;
	}

	// 名字 (必填)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 年龄 (必填)
	private int age = 0;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// 性别 (必填)
	private GlobalConstant.SexEnum sexEnum;

	public GlobalConstant.SexEnum getSexEnum() {
		return sexEnum;
	}

	public void setSexEnum(GlobalConstant.SexEnum sexEnum) {
		this.sexEnum = sexEnum;
	}

	// 挂号卡号
	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	// 其他(备注)
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// 常模
	private GlobalConstant.NormalModeTypeEnum normalModeTypeEnum;

	public GlobalConstant.NormalModeTypeEnum getNormalModeTypeEnum() {
		return normalModeTypeEnum;
	}

	public void setNormalModeTypeEnum(GlobalConstant.NormalModeTypeEnum normalModeTypeEnum) {
		this.normalModeTypeEnum = normalModeTypeEnum;
	}

	// 所属区域
	private String area;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	// 年龄范围
	private int minAge = 0;
	private int maxAge = 0;

	/**
	 * 设置年龄范围
	 * 
	 * @param minAge
	 * @param maxAge
	 */
	public void setAgeRange(int minAge, int maxAge) {
		this.minAge = minAge;
		this.maxAge = maxAge;
	}

	/**
	 * 判断是否在规定的年龄范围内
	 * 
	 * @param age
	 * @return
	 */
	public boolean isWithinTheSpecifiedAgeRange(int age) {
		do {
			if (minAge == 0 && maxAge == 0) {
				// 没有设置 年龄范围
				break;
			}

			if (age >= minAge && age <= maxAge) {
				// 判断年龄范围要在 minAge ~ maxAge
				break;
			}

			return false;
		} while (false);

		return true;
	}

	public String getAgeRangeString() {
		if (minAge == 0 && maxAge == 0) {
			// 没有设置 年龄范围
			return "";
		}

		return "" + minAge + "~" + maxAge;
	}

	// 必选项列表
	private Set<Object> necessaryOptionSet = new HashSet<Object>();

	public void addNecessaryOption(Object necessaryOptionEnum) {
		if (necessaryOptionEnum == null) {
			assert false : "入参为空!";
			return;
		}
		necessaryOptionSet.add(necessaryOptionEnum);
	}

	public boolean isNecessaryOption(Object necessaryOptionEnum) {
		if (necessaryOptionEnum == null) {
			assert false : "入参为空!";
			return false;
		}

		return necessaryOptionSet.contains(necessaryOptionEnum);
	}

	// 扩展项列表
	private Set<Object> expandOptionSet = new HashSet<Object>();

	public void addExpandOption(Object expandOptionEnum) {
		if (expandOptionEnum == null) {
			assert false : "入参为空!";
			return;
		}
		expandOptionSet.add(expandOptionEnum);
	}

	public boolean isExpandOption(Object expandOptionEnum) {
		if (expandOptionEnum == null) {
			assert false : "入参为空!";
			return false;
		}

		return expandOptionSet.contains(expandOptionEnum);
	}

	/**
	 * 获取受访者信息的字段列表
	 * 
	 * @return
	 */
	public List<String> getRespondentsInformationFieldList() {
		final List<String> itemList = new ArrayList<String>();
		// 姓名
		itemList.add(name);
		// 性别
		String sexString = null;
		if (sexEnum != null) {
			sexString = sexEnum.getName();
		}
		itemList.add(sexString);
		// 年龄
		itemList.add(Integer.toString(age));
		// 挂号号码
		itemList.add(cardNumber);
		return itemList;
	}
}
