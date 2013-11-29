package cn.skyduck.questionnaire;

import java.util.List;

import android.text.TextUtils;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.question_frame_fragment.embu.EMBUPartOnePageFourUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.embu.EMBUPartOnePageOneUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.embu.EMBUPartOnePageThreeUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.embu.EMBUPartOnePageTwoUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.embu.EMBUQuestionUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.RespondentsInformationBase;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.RespondentsInformationOfChildren;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneUserAnswerDataSource;
import cn.skyduck.question_frame_fragment.rcbc.RCBC29UserAnswerDataSource;
import cn.skyduck.question_frame_fragment.rcbc.RCBC30And31UserAnswerDataSource;
import cn.skyduck.question_frame_fragment.ssrs.SSRSQuestion10And11UserAnswerDataSource;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;

public final class PackAnswerDataTools {
	private PackAnswerDataTools() {

	}

	/**
	 * 通过问题索引, 用户回答的答案数据源, 来获取一道题的用户答案结果(字节类型)
	 * 
	 * @param answerDataSource
	 * @return
	 */
	public static byte getAnswerValueResultFromAnswerDataSourceOfDefault(final Object answerDataSource, final int defaultValue) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案(只适用于那些不可以跳题的量表, 有些量表是返回0代表放弃)
			return (byte) defaultValue;
		}

		// UI上的控件选项是从0开始的, 这里只适用于那些答题选项是从0开始的量表
		final Integer answerOption = (Integer) answerDataSource;

		return answerOption.byteValue();
	}

	/**
	 * 获取第一部分选择题的用户答案数据(字节类型)
	 * 
	 * @param questionnaireModel
	 * @return
	 */
	public static byte[] getPartOneAnswerResultsOfDefault(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[1024];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				@SuppressWarnings("unused")
				final int trueQuestionIndex = questionIndex + 1;
				dataOfTmp[dataIndex++] = questionnaireModel.getAnswerValueResultFromAnswerDataSource(baseFramePageModel.getAnswerDataSource());
			}
		}

		final byte[] dataOfReturn = new byte[dataIndex];
		System.arraycopy(dataOfTmp, 0, dataOfReturn, 0, dataOfReturn.length);
		return dataOfReturn;
	}

	public static String getPartTwoAnswerResultsStringDataOfDefaultChildren(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展
		final RespondentsInformationOfChildren respondentsInformationOfChildren = (RespondentsInformationOfChildren) questionnaireModel.getRespondentsInformation();

		// 填表人
		infoStringBuffer.append("&");
		if (respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum() != null) {
			infoStringBuffer.append(respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum().getName());
		}

		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	/**
	 * 所有量表 第二部分数据 的公共字段部分
	 * 
	 * @param option
	 * @param questionnaireModel
	 * @return
	 */
	private static String getPartTwoAnswerResultsCommonFields(final int option, final FullQuestionnaireModel questionnaireModel) {
		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		final RespondentsInformationBase respondentsInformationBase = questionnaireModel.getRespondentsInformation();
		final List<String> fieldListsStrings = respondentsInformationBase.getRespondentsInformationFieldList();
		for (String fieldString : fieldListsStrings) {
			infoStringBuffer.append("&");
			if (!TextUtils.isEmpty(fieldString)) {
				infoStringBuffer.append(fieldString);
			}
		}
		// 答题开始时间
		infoStringBuffer.append("&");
		infoStringBuffer.append(questionnaireModel.getBeginTestDateString());
		// 答题耗时时间
		infoStringBuffer.append("&");
		infoStringBuffer.append(questionnaireModel.getTimeConsumingString());
		// 其他
		infoStringBuffer.append("&");
		infoStringBuffer.append(respondentsInformationBase.getRemark());
		// 操作选项 : 1:普通 2:详细 3:全面 4:只上传数据不打印 5:普通预览 6:详细预览 7:全面预览
		infoStringBuffer.append("&");
		infoStringBuffer.append(option);

		return infoStringBuffer.toString();
	}

	/**
	 * 获取第二部分中文信息的用户答案数据-默认方式(字节类型)
	 * 
	 * @param option
	 * @param questionnaireModel
	 * @return
	 */
	public static String getPartTwoAnswerResultsStringDataOfDefault(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * 跟据问题索引 和 答案数据源 得到该问题答案值
	 * 
	 * 适用量表 :
	 * 
	 * @param answerDataSource
	 * @param questionIndex
	 * @return
	 */
	public static byte getAnswerValueResultFromAnswerDataSourceOfIntegerType1(final Object answerDataSource) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return 0;
		}

		// UI上控件选项从0开始, 这里只适用于那些答题选项是从1开始的量表
		final Integer answerOption = (Integer) answerDataSource + 1;

		return answerOption.byteValue();

	}

	/**
	 * 
	 * @param answerDataSource
	 * @param defaultValue
	 *          默认值, 如果入参非法, 或者用户未做回答时的答案选项
	 * @return
	 */

	public static byte getAnswerValueResultFromAnswerDataSourceOfBoolean(final Object answerDataSource, final int defaultValue) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return (byte) defaultValue;
		}

		// UI上控件选项 "YES" 是 "0", "NO" 是 "1"
		final Integer answerOption = (Integer) answerDataSource;
		// 答案选项是从 0 开始的.
		int answerValue = 0;
		if (answerOption == 0) {
			answerValue = 1;
		} else {
			answerValue = 0;
		}

		return (byte) answerValue;

	}

	public static byte getAnswerValueResultFromAnswerDataSourceOfInputAndSingleOneUserAnswerDataSource(final Object answerDataSource) {
		if (!(answerDataSource instanceof InputAndSingleOneUserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return 0;
		}

		// UI上控件选项从0开始, 这里只适用于那些答题选项是从1开始的量表
		final Integer answerOption = ((InputAndSingleOneUserAnswerDataSource) answerDataSource).getSingleAnswerOption() + 1;

		return answerOption.byteValue();
	}

	public static byte getAnswerValueResultFromAnswerDataSourceOfRCBC29UserAnswerDataSource(final Object answerDataSource) {
		if (!(answerDataSource instanceof RCBC29UserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return -1;
		}

		final Integer answerOption = ((RCBC29UserAnswerDataSource) answerDataSource).getOptionPartOneRadioAnswer();

		return answerOption.byteValue();
	}

	public static byte getAnswerValueResultFromAnswerDataSourceOfRCBC30And31UserAnswerDataSource(final Object answerDataSource) {
		if (!(answerDataSource instanceof RCBC30And31UserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return -1;
		}

		final Integer answerOption = ((RCBC30And31UserAnswerDataSource) answerDataSource).getOptionPartOneRadioAnswer();

		return answerOption.byteValue();
	}

	private static byte getAnswerValueResultFromAnswerDataSourceOfSSRSS10And11(final Object answerDataSource) {
		if (!(answerDataSource instanceof SSRSQuestion10And11UserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return 0;
		}

		// UI上控件选项从0开始, 这里只适用于那些答题选项是从1开始的量表
		final Integer answerOption = ((SSRSQuestion10And11UserAnswerDataSource) answerDataSource).getOptionPartOneRadioAnswer();

		return answerOption.byteValue();
	}

	private static enum ParentEnum {
		//
		FATHER,
		//
		MOTHER
	};

	public static byte getAnswerValueResultFromAnswerDataSourceOfEMBU(final Object answerDataSource, final ParentEnum parentEnum) {
		if (!(answerDataSource instanceof EMBUQuestionUserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return 0;
		}

		// UI上控件选项从0开始, 这里只适用于那些答题选项是从1开始的量表
		int answerValue = 0;
		Integer answerOption = 0;
		if (parentEnum == ParentEnum.FATHER) {
			answerOption = ((EMBUQuestionUserAnswerDataSource) answerDataSource).getOptionOfFather();
		} else {
			answerOption = ((EMBUQuestionUserAnswerDataSource) answerDataSource).getOptionOfMother();
		}

		// -1是无效选项, 4是 "不适合选择"
		if (answerOption == -1 || answerOption == 4) {
			answerValue = 0;
		} else {
			answerValue += 1;
		}

		return (byte) answerValue;
	}

	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * Y-BOCS 专用
	 * 
	 * @return
	 */
	public static String getPartTwoAnswerResultsStringDataOfY_BOCS(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		final RespondentsInformationOfAdults respondentsInformationOfAdults = (RespondentsInformationOfAdults) questionnaireModel.getRespondentsInformation();

		// 第几次测试(评定次数)
		infoStringBuffer.append("&");
		if (!TextUtils.isEmpty(respondentsInformationOfAdults.getNumberOfTest())) {
			infoStringBuffer.append(respondentsInformationOfAdults.getNumberOfTest());
		}
		// 结尾
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	/**
	 * CBCL 专用
	 * 
	 * @return
	 */
	// TODO : 有问题
	public static String getPartTwoAnswerResultsStringDataOfCBCL(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));
		final RespondentsInformationOfChildren respondentsInformationOfChildren = (RespondentsInformationOfChildren) questionnaireModel.getRespondentsInformation();

		// 出生日期
		infoStringBuffer.append("&");
		// 民族
		infoStringBuffer.append("&");
		// 父亲职业
		infoStringBuffer.append("&");
		// 母亲职业
		infoStringBuffer.append("&");
		// 填表人
		String whoWillReplaceTheAnswerString = null;
		if (respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum() != null) {
			whoWillReplaceTheAnswerString = respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum().getName();
		}
		infoStringBuffer.append(whoWillReplaceTheAnswerString);
		infoStringBuffer.append("&");

		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static byte[] getPartOneAnswerResultsOfLSAS(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[48];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				@SuppressWarnings("unused")
				final int trueQuestionIndex = questionIndex + 1;

				int[] answerList = (int[]) baseFramePageModel.getAnswerDataSource();
				dataOfTmp[dataIndex] = (byte) answerList[0];
				dataOfTmp[dataIndex + 24] = (byte) answerList[1];
				dataIndex++;
			}
		}

		return dataOfTmp;
	}

	public static byte[] getPartOneAnswerResultsOfMMSE(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[30];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;

				if (trueQuestionIndex == 29) {
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfInputAndSingleOneUserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else {
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfIntegerType1(baseFramePageModel.getAnswerDataSource());
				}
			}
		}

		return dataOfTmp;
	}

	public static byte[] getPartOneAnswerResultsOfPSQI(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[20];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {

				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 14 || trueQuestionIndex == 24) {
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfInputAndSingleOneUserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else if (trueQuestionIndex >= 5) {
					// 从 第五题开始是选择题
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfIntegerType1(baseFramePageModel.getAnswerDataSource());
				}
			}
		}

		return dataOfTmp;
	}

	public static String getPartTwoAnswerResultsStringDataOfPSQI(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		String[] textArray = new String[6];
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {

				final int trueQuestionIndex = questionIndex + 1;

				switch (trueQuestionIndex) {
				case 1:
					textArray[0] = (String) baseFramePageModel.getAnswerDataSource();
					break;
				case 2:
					textArray[1] = (String) baseFramePageModel.getAnswerDataSource();
					break;
				case 3:
					textArray[2] = (String) baseFramePageModel.getAnswerDataSource();
					break;
				case 4:
					textArray[3] = (String) baseFramePageModel.getAnswerDataSource();
					break;
				case 14:
					textArray[4] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 24:
					textArray[5] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				default:
					break;
				}
			}
		}

		// 1,2,3,4----14----24
		for (int i = 0; i < textArray.length; i++) {
			infoStringBuffer.append("&");
			String string = textArray[i];
			if (!TextUtils.isEmpty(string)) {
				infoStringBuffer.append(string);
			}
		}

		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static String getPartTwoAnswerResultsStringDataOfTABP(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		final RespondentsInformationOfAdults respondentsInformationOfAdults = (RespondentsInformationOfAdults) questionnaireModel.getRespondentsInformation();
		// 南方或者北方人
		infoStringBuffer.append("&");
		GlobalConstant.ResidentialAreaEnum residentialAreaEnum = respondentsInformationOfAdults.getResidentialAreaEnum();
		if (residentialAreaEnum != null) {
			infoStringBuffer.append(residentialAreaEnum.getName());
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static byte[] getPartOneAnswerResultsOfRCBC(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[31];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 30 || trueQuestionIndex == 31) {
					// 30 / 31
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfRCBC30And31UserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else if (trueQuestionIndex == 29) {
					// 29
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfRCBC29UserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else if (trueQuestionIndex == 28) {
					// 28
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfInputAndSingleOneUserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else {
					// 1 ~ 27
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfDefault(baseFramePageModel.getAnswerDataSource(), -1);
				}
			}
		}

		return dataOfTmp;
	}

	// TODO : 有问题
	public static String getPartTwoAnswerResultsStringDataOfRCBC(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		final RespondentsInformationOfChildren respondentsInformationOfChildren = (RespondentsInformationOfChildren) questionnaireModel.getRespondentsInformation();

		// 填表人
		infoStringBuffer.append("&");
		if (respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum() != null) {
			infoStringBuffer.append(respondentsInformationOfChildren.getWhoWillReplaceTheAnswerEnum().getName());
		}

		//
		String[] textArray = new String[4];
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				switch (trueQuestionIndex) {
				case 28:
					textArray[0] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 29:
					StringBuffer tmpBuffer29 = new StringBuffer();
					int[] optionPartTwoCheckBoxAnswerArray = ((RCBC29UserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getOptionPartTwoCheckBoxAnswerArray();
					for (int i = 0; i < optionPartTwoCheckBoxAnswerArray.length; i++) {
						switch (optionPartTwoCheckBoxAnswerArray[i]) {
						case 0:
							tmpBuffer29.append("A");
							break;
						case 1:
							tmpBuffer29.append("B");
							break;
						case 2:
							tmpBuffer29.append("C");
							break;
						default:
							break;
						}

						if (i != optionPartTwoCheckBoxAnswerArray.length - 1) {
							tmpBuffer29.append("&");
						}
					}
					textArray[1] = tmpBuffer29.toString();
					break;
				case 30:
				case 31:
					StringBuffer tmpBuffer30 = new StringBuffer();
					final int optionPartTwoRadioAnswer = ((RCBC30And31UserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getOptionPartTwoRadioAnswer();
					switch (optionPartTwoRadioAnswer) {
					case 0:
						tmpBuffer30.append("A");
						break;
					case 1:
						tmpBuffer30.append("B");
						break;
					case 2:
						tmpBuffer30.append("C");
						break;
					default:
						break;
					}
					tmpBuffer30.append("&");
					final String optionPartTwoInputAnswer = ((RCBC30And31UserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getOptionPartTwoInputAnswer();
					if (!TextUtils.isEmpty(optionPartTwoInputAnswer)) {
						tmpBuffer30.append(optionPartTwoInputAnswer);
					}
					if (trueQuestionIndex == 30) {
						textArray[2] = tmpBuffer30.toString();
					} else {
						textArray[3] = tmpBuffer30.toString();
					}

					break;
				default:
					break;
				}
			}
		}

		// 28, 29, 30, 31
		for (int i = 0; i < textArray.length; i++) {
			infoStringBuffer.append("&");
			String string = textArray[i];
			if (!TextUtils.isEmpty(string)) {
				infoStringBuffer.append(string);
			}
		}
	
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static String getPartTwoAnswerResultsStringDataOfMMSE(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		// 18题记录被测者所述的句子 (按我们的索引是第29题)
		infoStringBuffer.append("&");
		String userInputTextOf29 = "";
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;

				if (trueQuestionIndex == 29) {
					userInputTextOf29 = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				}
			}
		}
		if (!TextUtils.isEmpty(userInputTextOf29)) {
			infoStringBuffer.append(userInputTextOf29);
		}

		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static String getPartTwoAnswerResultsStringDataOfSAPS(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		// 21 ~ 25 备注部分
		infoStringBuffer.append("&");
		String[] textArray = new String[5];
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {

				final int trueQuestionIndex = questionIndex + 1;

				switch (trueQuestionIndex) {
				case 21:
					textArray[0] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 22:
					textArray[1] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 23:
					textArray[2] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 24:
					textArray[3] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				case 25:
					textArray[4] = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
					break;
				default:
					break;
				}

				if (trueQuestionIndex == 25) {
					break;
				}
			}
		}

		// 21 ~ 25
		for (int i = 0; i < textArray.length; i++) {
			infoStringBuffer.append("&");
			String string = textArray[i];
			if (!TextUtils.isEmpty(string)) {
				infoStringBuffer.append(string);
			}
		}

		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	//
	public static byte[] getPartOneAnswerResultsOfSSRS(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[14];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;

				if (trueQuestionIndex == 10 || trueQuestionIndex == 11) {// 10, 11 题
					// 答案索引从 0 开始
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfSSRSS10And11(baseFramePageModel.getAnswerDataSource());
				} else {// 1~9 题, 题号即分数
					// 答案索引从 1 开始
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfIntegerType1(baseFramePageModel.getAnswerDataSource());
				}
			}
		}

		return dataOfTmp;
	}

	// SSRS10,11 内部使用
	private static String getSSRS10And11TextPartResult(final Object answerDataSource) {
		if (!(answerDataSource instanceof SSRSQuestion10And11UserAnswerDataSource)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return "";
		}

		final StringBuffer tmpBuffer = new StringBuffer();
		final SSRSQuestion10And11UserAnswerDataSource userAnswerDataSource = (SSRSQuestion10And11UserAnswerDataSource) answerDataSource;
		for (boolean isChecked : userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()) {
			if (isChecked) {
				tmpBuffer.append(1);
			} else {
				tmpBuffer.append(0);
			}
			tmpBuffer.append("&");
		}

		for (String userInputText : userAnswerDataSource.getOptionPartTwoInputAnswerArray()) {
			if (!TextUtils.isEmpty(userInputText)) {
				tmpBuffer.append("userInputText");
			}
			tmpBuffer.append("&");
		}
		tmpBuffer.deleteCharAt(tmpBuffer.length() - 1);
		return tmpBuffer.toString();
	}

	public static String getPartTwoAnswerResultsStringDataOfSSRS(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		// 21 ~ 25 备注部分
		infoStringBuffer.append("&");
		String[] textArray = new String[2];
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				switch (trueQuestionIndex) {
				case 10:
					textArray[0] = getSSRS10And11TextPartResult(baseFramePageModel.getAnswerDataSource());
					break;
				case 11:
					textArray[1] = getSSRS10And11TextPartResult(baseFramePageModel.getAnswerDataSource());
					break;
				default:
					break;
				}

				if (trueQuestionIndex == 11) {
					break;
				}
			}
		}

		// 10 ~ 11
		for (int i = 0; i < textArray.length; i++) {
			infoStringBuffer.append("&");
			String string = textArray[i];
			if (!TextUtils.isEmpty(string)) {
				infoStringBuffer.append(string);
			}
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	private static byte getAnswerValueResultFromAnswerDataSourceOfHDS9And10(final Object answerDataSource) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return -1;
		}

		// UI上控件选项 "答对一组" 是 "0", "答对两组" 是 "1", "都不对" 是 "2"
		final Integer answerOption = (Integer) answerDataSource;
		// 答案选项是从 0 开始的.
		int answerValue = 0;
		if (answerOption == 2) {
			answerValue = 0;
		} else {
			answerValue += 1;
		}

		return (byte) answerValue;
	}

	public static byte[] getPartOneAnswerResultsOfHDS(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[11];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 11) {// 11
					// 答案选项从 0 开始
					dataOfTmp[10] = getAnswerValueResultFromAnswerDataSourceOfDefault(baseFramePageModel.getAnswerDataSource(), -1);
				} else if (trueQuestionIndex == 9 || trueQuestionIndex == 10) {// 9, 10
					// 答案选项有变化
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfHDS9And10(baseFramePageModel.getAnswerDataSource());
				} else {// 1 ~ 8 题
					// 普通 判断题
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfBoolean(baseFramePageModel.getAnswerDataSource(), -1);
				}
			}
		}

		return dataOfTmp;
	}

	public static byte getAnswerValueResultFromAnswerDataSourceOfMUNSH(final Object answerDataSource) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return -1;
		}

		// UI上控件选项 "是" 是 "0", "不知道或者不确定" 是 "1", "否" 是 "2"
		final Integer answerOption = (Integer) answerDataSource;
		// 答案选项是从 0 开始的.
		int answerValue = 0;
		switch (answerOption) {
		case 0:
			answerValue = 2;
			break;
		case 1:
			answerValue = 1;
			break;
		case 2:
			answerValue = 0;
			break;
		default:
			break;
		}

		return (byte) answerValue;
	}

	public static byte getAnswerValueResultFromAnswerDataSourceOfLSR(final Object answerDataSource) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案类型非法, 或者用户没有选择答案, 该题就要略过
			return -1;
		}

		// UI上控件选项 和 答案 倒序
		final Integer answerOption = (Integer) answerDataSource;
		// 答案选项是从 0 开始的.
		int answerValue = 0;
		switch (answerOption) {
		case 0:
			answerValue = 5;
			break;
		case 1:
			answerValue = 4;
			break;
		case 2:
			answerValue = 3;
			break;
		case 3:
			answerValue = 2;
			break;
		case 4:
			answerValue = 1;
			break;
		default:
			break;
		}

		return (byte) answerValue;
	}

	public static String getPartTwoAnswerResultsStringDataOfSERS(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		// 14题其他症状
		infoStringBuffer.append("&");
		String userInputTextOf14 = "";
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 14) {
					userInputTextOf14 = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
				}
			}
		}
		if (!TextUtils.isEmpty(userInputTextOf14)) {
			infoStringBuffer.append(userInputTextOf14);
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static byte[] getPartOneAnswerResultsOfEMBU(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[132];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				@SuppressWarnings("unused")
				final int trueQuestionIndex = questionIndex + 1;
				int[] answerList = (int[]) baseFramePageModel.getAnswerDataSource();
				dataOfTmp[dataIndex] = (byte) answerList[0];
				dataOfTmp[dataIndex + 66] = (byte) answerList[1];
				dataIndex++;
			}
		}

		return dataOfTmp;
	}

	public static String getPartTwoAnswerResultsStringDataOfEMBU(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));
		// 扩展部分
		//
		infoStringBuffer.append("&");
		BaseFramePageModel baseFramePageModel = null;
		// 第一部分问题 : 第一个界面 : 父亲是否健在 / 母亲是否健在
		baseFramePageModel = questionnaireModel.getFrameModelList().get(2);
		EMBUPartOnePageOneUserAnswerDataSource embuPartOnePageOneUserAnswerDataSource = (EMBUPartOnePageOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource();
		if (embuPartOnePageOneUserAnswerDataSource instanceof EMBUPartOnePageOneUserAnswerDataSource) {
			// 父亲是否健在
			switch (embuPartOnePageOneUserAnswerDataSource.getIsFatherAlive()) {
			case -1:
				infoStringBuffer.append("&");
				break;
			case 0:
				infoStringBuffer.append("是&");
				break;
			case 1:
				infoStringBuffer.append("否&");
				final int ageAtDeathOfFather = embuPartOnePageOneUserAnswerDataSource.getAgeAtDeathOfFather();
				if (ageAtDeathOfFather > 0) {
					infoStringBuffer.append(Integer.valueOf(ageAtDeathOfFather).toString());
				}
				break;
			default:
				break;
			}
			infoStringBuffer.append("&");

			// 母亲是否健在
			switch (embuPartOnePageOneUserAnswerDataSource.getIsMotherAlive()) {
			case -1:
				infoStringBuffer.append("&");
				break;
			case 0:
				infoStringBuffer.append("是&");
				break;
			case 1:
				infoStringBuffer.append("否&");
				final int ageAtDeathOfMother = embuPartOnePageOneUserAnswerDataSource.getAgeAtDeathOfMother();
				if (ageAtDeathOfMother > 0) {
					infoStringBuffer.append(Integer.valueOf(ageAtDeathOfMother).toString());
				}
				break;
			default:
				break;
			}
		} else {
			// 用户忽略了这一题
			// 父亲是/否健在 & 在您几岁时去世 & 母亲是/否健在 & 在您几岁时去世
			infoStringBuffer.append("&&&");
		}

		// 第一部分问题 : 第二个界面 : 父亲是否离异
		infoStringBuffer.append("&");
		baseFramePageModel = questionnaireModel.getFrameModelList().get(3);
		EMBUPartOnePageTwoUserAnswerDataSource embuPartOnePageTwoUserAnswerDataSource = (EMBUPartOnePageTwoUserAnswerDataSource) baseFramePageModel.getAnswerDataSource();
		if (embuPartOnePageTwoUserAnswerDataSource instanceof EMBUPartOnePageTwoUserAnswerDataSource) {
			switch (embuPartOnePageTwoUserAnswerDataSource.getIsParentsDivorced()) {
			case -1:
				infoStringBuffer.append("&");
				break;
			case 0:// 否
				infoStringBuffer.append("否&");
				break;
			case 1:// 是
				infoStringBuffer.append("是&");
				final int ageOfDivorce = embuPartOnePageTwoUserAnswerDataSource.getAgeOfDivorce();
				if (ageOfDivorce > 0) {
					infoStringBuffer.append(Integer.valueOf(ageOfDivorce).toString());
				}
				break;
			default:
				break;
			}
		} else {
			// 用户忽略了这一题
			infoStringBuffer.append("&&");
		}

		// 第一部分问题 : 第三个界面 : 父亲文化程度/父亲职业
		infoStringBuffer.append("&");
		baseFramePageModel = questionnaireModel.getFrameModelList().get(4);
		EMBUPartOnePageThreeUserAnswerDataSource embuPartOnePageThreeUserAnswerDataSource = (EMBUPartOnePageThreeUserAnswerDataSource) baseFramePageModel.getAnswerDataSource();
		if (embuPartOnePageThreeUserAnswerDataSource instanceof EMBUPartOnePageThreeUserAnswerDataSource) {
			// 父亲文化程度
			String educationEnumOfFather = "";
			if (embuPartOnePageThreeUserAnswerDataSource.getEducationEnumOfFather() != null) {
				educationEnumOfFather = embuPartOnePageThreeUserAnswerDataSource.getEducationEnumOfFather().getName();
			}
			if (!TextUtils.isEmpty(educationEnumOfFather)) {
				infoStringBuffer.append(educationEnumOfFather);
			}

			// 父亲职业
			infoStringBuffer.append("&");
			String jobTypeEnumOfFather = "";
			if (embuPartOnePageThreeUserAnswerDataSource.getJobTypeEnumOfFather() != null) {
				jobTypeEnumOfFather = embuPartOnePageThreeUserAnswerDataSource.getJobTypeEnumOfFather().getName();
			}
			if (!TextUtils.isEmpty(jobTypeEnumOfFather)) {
				infoStringBuffer.append(jobTypeEnumOfFather);
			}
			infoStringBuffer.append("&");
		} else {
			// 用户忽略了这一题
			infoStringBuffer.append("&&");
		}

		// 第一部分问题 : 第四个界面 : 母亲文化程度/母亲职业
		infoStringBuffer.append("&");
		baseFramePageModel = questionnaireModel.getFrameModelList().get(5);
		EMBUPartOnePageFourUserAnswerDataSource embuPartOnePageFourUserAnswerDataSource = (EMBUPartOnePageFourUserAnswerDataSource) baseFramePageModel.getAnswerDataSource();
		if (embuPartOnePageFourUserAnswerDataSource instanceof EMBUPartOnePageFourUserAnswerDataSource) {
			// 母亲文化程度
			String educationEnumOfMother = "";
			if (embuPartOnePageFourUserAnswerDataSource.getEducationEnumOfMother() != null) {
				educationEnumOfMother = embuPartOnePageFourUserAnswerDataSource.getEducationEnumOfMother().getName();
			}
			if (!TextUtils.isEmpty(educationEnumOfMother)) {
				infoStringBuffer.append(educationEnumOfMother);
			}

			// 母亲职业
			infoStringBuffer.append("&");
			String jobTypeEnumOfMother = "";
			if (embuPartOnePageFourUserAnswerDataSource.getJobTypeEnumOfMother() != null) {
				jobTypeEnumOfMother = embuPartOnePageFourUserAnswerDataSource.getJobTypeEnumOfMother().getName();
			}
			if (!TextUtils.isEmpty(jobTypeEnumOfMother)) {
				infoStringBuffer.append(jobTypeEnumOfMother);
			}
			infoStringBuffer.append("&");
		} else {
			// 用户忽略了这一题
			infoStringBuffer.append("&&");
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static byte[] getPartOneAnswerResultsOfGWB(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[33];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				switch (trueQuestionIndex) {
				case 15:
				case 16:
				case 17:
				case 18:
					// 这4道题的分数是从 0 ~ 10
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfDefault(baseFramePageModel.getAnswerDataSource(), -1);
					break;

				default:
					// 答案题号从 1 开始
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfIntegerType1(baseFramePageModel.getAnswerDataSource());
					break;
				}
			}
		}

		return dataOfTmp;
	}

	public static byte[] getPartOneAnswerResultsOfHPII(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[196];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex >= 4 && trueQuestionIndex <= 183) {
					// 4 ~ 183
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfBoolean(baseFramePageModel.getAnswerDataSource(), -1);
				} else if (trueQuestionIndex >= 184 && trueQuestionIndex <= 195) {
					// 184 ~ 195
					@SuppressWarnings("unchecked")
					final List<String> answerDataSource = (List<String>) baseFramePageModel.getAnswerDataSource();
					for (String answerString : answerDataSource) {
						GlobalConstant.AbilityValueEnum abilityValueEnum = GlobalConstant.AbilityValueEnum.getItemByName(answerString);
						dataOfTmp[dataIndex++] = (byte) abilityValueEnum.getValue();
					}
				} else if (trueQuestionIndex >= 196 && trueQuestionIndex <= 199) {
					// 196 ~ 199
					@SuppressWarnings("unchecked")
					final List<String> answerDataSource = (List<String>) baseFramePageModel.getAnswerDataSource();
					for (String answerString : answerDataSource) {
						GlobalConstant.ProfessionalValueEnum professionalValueEnum = GlobalConstant.ProfessionalValueEnum.getItemByName(answerString);
						dataOfTmp[dataIndex++] = (byte) professionalValueEnum.getValue();
					}
				}
			}
		}

		return dataOfTmp;
	}

	public static String getPartTwoAnswerResultsStringDataOfHPII(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		// 理想工作1 & 理想工作2 & 理想工作
		String[] textArray = new String[3];
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 1) {
					@SuppressWarnings("unchecked")
					final List<String> answerDataSource = (List<String>) baseFramePageModel.getAnswerDataSource();
					for (int i = 0; i < answerDataSource.size(); i++) {
						textArray[i] = answerDataSource.get(i);
					}
					break;
				}
			}
		}

		// 1 ~ 3
		for (int i = 0; i < textArray.length; i++) {
			infoStringBuffer.append("&");
			String string = textArray[i];
			if (!TextUtils.isEmpty(string)) {
				infoStringBuffer.append(string);
			}
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}

	public static byte[] getPartOneAnswerResultsOfASLEC(final FullQuestionnaireModel questionnaireModel) {
		int dataIndex = 0;
		final byte[] dataOfTmp = new byte[27];
		// 构建用户答案部分的数据(这里都是选择题答案)
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 27) {
					// 27题
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfInputAndSingleOneUserAnswerDataSource(baseFramePageModel.getAnswerDataSource());
				} else {
					// 1 ~ 26
					dataOfTmp[dataIndex++] = getAnswerValueResultFromAnswerDataSourceOfIntegerType1(baseFramePageModel.getAnswerDataSource());
				}
			}
		}

		return dataOfTmp;
	}

	public static String getPartTwoAnswerResultsStringDataOfASLEC(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		final RespondentsInformationOfAdults respondentsInformationOfAdults = (RespondentsInformationOfAdults) questionnaireModel.getRespondentsInformation();
		// 评定时间范围
		infoStringBuffer.append("&");
		String assessmentTimeRangeString = null;
		if (respondentsInformationOfAdults.getAssessmentTimeRangeEnum() != null) {
			assessmentTimeRangeString = respondentsInformationOfAdults.getAssessmentTimeRangeEnum().getName();
		}
		infoStringBuffer.append(assessmentTimeRangeString);

		// 27题
		infoStringBuffer.append("&");
		String userInputTextOf27 = "";
		for (BaseFramePageModel baseFramePageModel : questionnaireModel.getFrameModelList()) {
			final int questionIndex = baseFramePageModel.getQuestionIndex();
			if (questionIndex != BaseFramePageModel.NonQuestionPage) {
				final int trueQuestionIndex = questionIndex + 1;
				if (trueQuestionIndex == 27) {
					userInputTextOf27 = ((InputAndSingleOneUserAnswerDataSource) baseFramePageModel.getAnswerDataSource()).getInputBoxContent();
				}
			}
		}
		if (!TextUtils.isEmpty(userInputTextOf27)) {
			infoStringBuffer.append(userInputTextOf27);
		}
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}
	
	public static String getPartTwoAnswerResultsStringDataOfMMPI(final int option, final FullQuestionnaireModel questionnaireModel) {

		// 构建 测试者信息部分和用户答案中需要用户输入字符串的数据
		final StringBuffer infoStringBuffer = new StringBuffer();
		infoStringBuffer.append(getPartTwoAnswerResultsCommonFields(option, questionnaireModel));

		// 扩展部分
		if (questionnaireModel.getQuestionnaireStateEnum() == QuestionnaireStateEnum.COMPLETED_OF_END_TEST_IN_ADVANCE) {
			// 对于MMPI, 如果提前交卷的话, 就在协议结尾 增加 "01" 标志位
			infoStringBuffer.append("&");
			infoStringBuffer.append("01");
		}
		
		//
		infoStringBuffer.append("#");

		return infoStringBuffer.toString();
	}
}


