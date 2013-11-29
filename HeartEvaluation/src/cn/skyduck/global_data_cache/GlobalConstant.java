package cn.skyduck.global_data_cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;

public final class GlobalConstant {
	private GlobalConstant() {

	}

	public static final int HANDLER_EMPTY_MESSAGE_WHAT = -2012;

	/**
	 * 测试题所归属的类别
	 * 
	 * @author computer
	 * 
	 */
	public static enum QuestionnaireCategoryEnum {
		// 常用测试 ----------------- 0
		COMMON_TEST("常用测试"),
		// 人格类测验 ---------------- 1
		PERSONALITY("人格类测验"),
		// 儿童及老年 ---------------- 2
		CHILDREN_ELDERLY("儿童及老年"),
		// 精神类测验 ---------------- 3
		MENTAL("精神类测验"),
		// 情绪/应激类 ---------------- 4
		EMOTIONS_STRESS("情绪/应激类"),
		// 家庭生活类 ---------------- 5
		FAMILY_LIFE("家庭生活类"),
		// 综合类测验 ---------------- 6
		COMPLEX("综合类测验"),
		// 其它测验 ---------------- 7
		OTHER("其它测验");

		private final String name;

		public String getName() {
			return name;
		}

		private QuestionnaireCategoryEnum(String name) {
			this.name = name;
		}

	}

	/**
	 * 测试题 代号, 就是标示具体测试题的代号(例如:症状自评量表的代号是 SCL_90)
	 * 
	 * @author computer
	 * 
	 */

	// 性别
	public static enum SexEnum {
		// 男人
		MAN("男", 1),
		// 女人
		WOMAN("女", 0);

		private final String name;

		public String getName() {
			return name;
		}

		private final int value;

		public int getValue() {
			return value;
		}

		private SexEnum(String name, int value) {
			this.value = value;
			this.name = name;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (SexEnum item : SexEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		// Implementing a fromString method on an enum type
		private static final Map<String, SexEnum> stringToEnum = new HashMap<String, GlobalConstant.SexEnum>();
		static {// Initialize map from constant name to enum constant
			for (SexEnum item : values()) {
				stringToEnum.put(item.name, item);
			}
		}

		// returns Enum for string, or null if string is invalid.
		public static SexEnum fromName(String name) {
			return stringToEnum.get(name);
		}
	}

	// 受教育程度
	public static enum EducationEnum {
		// 小学
		PrimarySchool("小学", 0),
		// 初中
		JuniorSecondary("初中", 1),
		// 高中
		SeniorSecondary("高中", 2),
		// 大学
		University("大学", 3),
		// 研究生
		GraduateStudent("研究生", 4),
		// 博士
		Doctor("博士", 5),
		// 未受任何教育
		DidNotReceiveAnyEducation("未受任何教育", 6);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private EducationEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (EducationEnum item : EducationEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static EducationEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (EducationEnum item : EducationEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// 婚姻状况
	public static enum MaritalStatusEnum {
		// 已婚
		Married("已婚", 0),
		// 未婚"
		Single("未婚", 1),
		// 离异
		Divorced("离异", 2),
		// 丧偶
		Widowed("丧偶", 3);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private MaritalStatusEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (MaritalStatusEnum item : MaritalStatusEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static MaritalStatusEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (MaritalStatusEnum item : MaritalStatusEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// 居住区域(南方人/北方人)
	public static enum ResidentialAreaEnum {
		// 南方人
		South("南方人", 0),
		// 北方人
		North("北方人", 1);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private ResidentialAreaEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (ResidentialAreaEnum item : ResidentialAreaEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static ResidentialAreaEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (ResidentialAreaEnum item : ResidentialAreaEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// 填表人
	public static enum WhoWillReplaceTheAnswerEnum {
		// 父亲
		Father("父亲", 0),
		// 母亲
		Mother("母亲", 1),
		// 其他人
		Ohters("其他人", 1);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private WhoWillReplaceTheAnswerEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (WhoWillReplaceTheAnswerEnum item : WhoWillReplaceTheAnswerEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static WhoWillReplaceTheAnswerEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (WhoWillReplaceTheAnswerEnum item : WhoWillReplaceTheAnswerEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// 评定时间范围
	public static enum AssessmentTimeRangeEnum {
		// 3个月
		Month_3("3个月", 0),
		// 6个月
		Month_6("6个月", 1),
		// 9个月
		Month_9("9个月", 1),
		// 12个月
		Month_12("12个月", 1);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private AssessmentTimeRangeEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (AssessmentTimeRangeEnum item : AssessmentTimeRangeEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static AssessmentTimeRangeEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (AssessmentTimeRangeEnum item : AssessmentTimeRangeEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	/**
	 * 常模类型枚举
	 * 
	 * @author computer
	 * 
	 */
	public static enum NormalModeTypeEnum {
		// 全国常模
		ENTIRE_COUNTRY("全国常模", 0x001),
		// 性别常模
		SEX("性别常模", 0x010),
		// 职业常模
		PROFESSION("职业常模", 0x100);
		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private NormalModeTypeEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (NormalModeTypeEnum item : NormalModeTypeEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static NormalModeTypeEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (NormalModeTypeEnum item : NormalModeTypeEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	/**
	 * 量表支持的打印方式枚举
	 * 
	 * @author computer
	 * 
	 */
	public static enum PrintTypeEnum {
		//
		NORMAL_PRINT("普通打印", 1),
		//
		DETAIL_PRINT("详细打印", 2),
		//
		FULL_PRINT("全面打印", 3);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		/**
		 * 
		 * @param name
		 *          中文名称
		 * @param value
		 *          跟服务器协议对应的code
		 * @param markValue
		 *          用于 或 操作
		 */
		private PrintTypeEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (PrintTypeEnum item : PrintTypeEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static PrintTypeEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (PrintTypeEnum item : PrintTypeEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	/**
	 * 某个正在测试中的量表的状态
	 * 
	 * @author skyduck
	 * 
	 *         我们固定设计量表 第一页是患者信息录入界面 第二页是指导语界面 第三页开始就是问卷答题界面了
	 */
	public static enum QuestionnaireStateEnum {
		// 未开始(一过指导语界面, 就认为测试开始了)
		NOT_STARTED,
		// 正在进行测试
		TESTING,
		
		/// COMPLETED 是个分水岭, 之后都是已完成状态, 外部判断时, 用 compareTo 进行判断即可
		// 测试已经完成, 此时可以进入 "发送给医生界面"
		COMPLETED,
		// 测试已经完成, 但是是提前交卷的, 比如说MMPI
		COMPLETED_OF_END_TEST_IN_ADVANCE
	}

	// HPII 量表的184-196的答案
	public static enum AbilityValueEnum {
		// 1分
		one("1分", 1),
		// 2分
		two("2分", 2),
		// 3分
		three("3分", 3),
		// 4分
		four("4分", 4),
		// 5分
		five("5分", 5),
		// 6分
		six("6分", 6),
		// 7分
		seven("7分", 7);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private AbilityValueEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (AbilityValueEnum item : AbilityValueEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static AbilityValueEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (AbilityValueEnum item : AbilityValueEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// HPII 量表的196-199的答案
	public static enum ProfessionalValueEnum {

		// 1
		one("1．工资高、福利好", 1),
		// 2
		two("2．工作环境(物质方面)舒适", 2),
		// 3
		three("3．人际关系良好", 3),
		// 4
		four("4．工作稳定有保障", 4),
		// 5
		five("5．能提供较好的受教育机会", 5),
		// 6
		six("6．有较高的社会地位", 6),
		// 7
		seven("7．工作不太紧张、外部压力少", 7),
		// 8
		eight("8．能充分发挥自己的能力特长", 8),
		// 9
		nine("9．社会需要与社会贡献大", 9);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private ProfessionalValueEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (ProfessionalValueEnum item : ProfessionalValueEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static ProfessionalValueEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (ProfessionalValueEnum item : ProfessionalValueEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	// 工作种类
	public static enum JobTypeEnum {
		//
		Workers("工人", 0),
		//
		Farmers("农民", 1),
		//
		Intellectuals("知识分子", 3),
		//
		Cadre("干部", 4),
		//
		Freelance("自由职业", 5),
		//
		Other("其它", 6);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private JobTypeEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static List<String> getNameList() {
			List<String> nameList = new ArrayList<String>();
			for (JobTypeEnum item : JobTypeEnum.values()) {
				nameList.add(item.getName());
			}
			return nameList;
		}

		public static JobTypeEnum getItemByName(final String name) {
			do {
				if (TextUtils.isEmpty(name)) {
					break;
				}

				for (JobTypeEnum item : JobTypeEnum.values()) {
					if (name.equals(item.getName())) {
						return item;
					}
				}
			} while (false);

			return null;
		}
	}

	public static enum FunctionOptionsEnum {
		// 返回
		back("返回", -1),
		// 删除
		delete("删除", -1),
		// 删除全部
		delete_all("删除全部", -1),
		// 继续答题
		continue_test("继续答题", -1),

		// 普通打印
		normal_print("普通打印", 1),
		// 详细打印
		detail_print("详细打印", 2),
		// 完整打印
		full_print("全面打印", 3),
		// 只上传数据不打印
		upload("上传", 4),
		// 普通预览
		normal_preview("普通预览", 5),
		// 详细预览
		detail_preview("详细预览", 6),
		// 完整预览
		full_preview("完整预览", 7),
		// 预览
		preview("预览", -1),
		// 默认打印
		default_print("默认打印", -1);

		private final int value;

		public int getValue() {
			return value;
		}

		private final String name;

		public String getName() {
			return name;
		}

		private FunctionOptionsEnum(String name, int value) {
			this.name = name;
			this.value = value;
		}
	}
}
