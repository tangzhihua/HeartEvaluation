package cn.skyduck.questionnaire.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.RespondentsInformationBase;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.MD5Util;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 一份完整问卷 模型类(目前一份完整的问卷包括3部分 : 1.受访者信息录入界面; 2.答题指导语界面; 3.问卷界面.)
 * 
 * 完整的一份问卷, 其实是由每一帧 FramePageModel 组成的, 这就是设计思路
 * 
 * @author skyduck
 * 
 */
@SuppressWarnings("serial")
public abstract class FullQuestionnaireModel implements Serializable {
	private final String TAG = this.getClass().getSimpleName();

	public FullQuestionnaireModel(QuestionnaireCodeEnum questionnaireCodeEnum) {
		this.questionnaireCodeEnum = questionnaireCodeEnum;

		Date date = new Date();
		String tmpIdString = questionnaireCodeEnum.getShortName() + date.getTime();
		this.specialID = MD5Util.getMD5String(tmpIdString);
	}

	// 当前量表的唯一标识符(用于保存量表时使用)
	private String specialID;

	// 如果是从序列化中恢复时, 就需要用到这个 访问器方法
	public void setSpecialID(String specialID) {
		this.specialID = specialID;
	}

	public String getSpecialID() {
		return specialID;
	}

	// 当前量表答题状态(1.未开始; 2.正在进行; 3.已完成)
	private QuestionnaireStateEnum questionnaireStateEnum = QuestionnaireStateEnum.NOT_STARTED;

	public QuestionnaireStateEnum getQuestionnaireStateEnum() {
		return questionnaireStateEnum;
	}

	public void setQuestionnaireStateEnum(QuestionnaireStateEnum questionnaireStateEnum) {
		if (this.questionnaireStateEnum.compareTo(QuestionnaireStateEnum.COMPLETED) >= 0) {
			// 如果量表状态, 已经变成 "已完成" 时, 是不能更改量表状态为 "进行中" 后者 "未开始" 的

			// 保存 量表答题答题结束时间
			setEndTestdDate(new Date());
			return;
		}
		this.questionnaireStateEnum = questionnaireStateEnum;
	}

	// 当前量表类型
	protected QuestionnaireCodeEnum questionnaireCodeEnum;

	public QuestionnaireCodeEnum getQuestionnaireCodeEnum() {
		return questionnaireCodeEnum;
	}

	// 对于 Date对象, 内部最好使用 Date.getTime() 返回的整形数据, 因为这是不可变的数据, 而Date对象是可变的, 还需要做数据保护
	// 开始测试时间(要给默认值, 不能为空)
	private long beginTestDate;
	// 结束测试时间(要给默认值, 不能为空)
	private long endTestdDate;

	public Date getBeginTestDate() {
		return new Date(beginTestDate);
	}

	public void setBeginTestDate(Date beginTestDate) {
		this.beginTestDate = beginTestDate.getTime();
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getBeginTestDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date timeDate = new Date(beginTestDate);
		String timeString = dateFormat.format(timeDate);
		return timeString;
	}
	
	public Date getEndTestdDate() {
		return new Date(endTestdDate);
	}

	public void setEndTestdDate(Date endTestdDate) {
		// 要进行数据保护
		this.endTestdDate = endTestdDate.getTime();
	}

	@SuppressLint("SimpleDateFormat")
	public String getTimeConsumingString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
		Date timeDate = new Date(endTestdDate - beginTestDate);
		String timeString = dateFormat.format(timeDate);
		return timeString;
	}

	/**
	 * 获取考试耗时时间(单位 : 秒)
	 * 
	 * @return 返回考试耗时时间, 如果返回 -1 ,证明未设置考试开始时间或者考试结束时间
	 */
	public long getTimeForTest() {
		do {
			if (beginTestDate <= 0 || endTestdDate <= 0) {
				assert false : "beginTestDate || endTestdDate 为空.";
				break;
			}

			if (beginTestDate < endTestdDate) {
				assert false : "beginTestDate < endTestdDate";
				break;
			}

			return beginTestDate - endTestdDate;
		} while (false);

		return -1;
	}

	// 问卷问题总数
	protected int questionTotal;

	public void setQuestionTotal(int questionTotal) {
		this.questionTotal = questionTotal;
	}

	public int getQuestionTotal() {
		return questionTotal;
	}

	// 当前页面帧进度索引(一定要设置成-1,这样外面计数器才会正确)
	protected int currentFrameIndex = -1;

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}

	public void setCurrentFrameIndex(int currentFrameIndex) {
		if (currentFrameIndex < 0) {
			this.currentFrameIndex = 0;
		} else if (currentFrameIndex >= frameModelList.size()) {
			this.currentFrameIndex = frameModelList.size() - 1;
		} else {
			this.currentFrameIndex = currentFrameIndex;
		}
	}

	// 界面Model集合
	protected List<BaseFramePageModel> frameModelList = new ArrayList<BaseFramePageModel>();

	public List<BaseFramePageModel> getFrameModelList() {
		return frameModelList;
	}

	// 通过索引获取某个frame model
	public BaseFramePageModel getFrameModel(final int location) {
		if (location < 0 || location >= frameModelList.size()) {
			assert false : "入参 location 无效";
			return null;
		}

		return frameModelList.get(location);
	}

	/**
	 * 获取帧页面总数
	 * 
	 * @return
	 */
	public int getFramePageTotal() {
		return frameModelList.size();
	}

	/**
	 * 获取未回答的问题题号列表
	 * 
	 * @return
	 */
	public List<Integer> getUnansweredQuestionNumberList() {
		List<Integer> unansweredQuestionNumberList = new ArrayList<Integer>();
		for (Object frameModel : frameModelList) {
			if (frameModel instanceof QuestionFramePageModel) {
				QuestionFramePageModel questionFrameModel = (QuestionFramePageModel) frameModel;
				if (questionFrameModel.getQuestionIndex() == QuestionFramePageModel.NonQuestionPage) {
					continue;
				}

				if (questionFrameModel.getAnswerDataSource() == null) {
					// 题目索引从 0 开始, 所以这里要 + 1
					unansweredQuestionNumberList.add(questionFrameModel.getQuestionIndex() + 1);
				}
			}

		}

		return unansweredQuestionNumberList;
	}

	/**
	 * 检测当前问题是否能够被忽略(能被忽略的问题有很多种情况 : 1.此问题已经回答过; 2.当前帧不是问题帧; 3.已经回答的题目数量已经达到一定数量;
	 * 4.特殊设定为可以忽略)
	 * 
	 * @return
	 */
	public boolean isCanBeIgnoredCurrentQuestion() {
		do {
			if (currentFrameIndex < 0 || frameModelList.size() <= 0) {
				assert false : "内部分参数错误 : currentFrameIndex < 0, questionFrameList.size <= 0";
				break;
			}

			Object frameModel = frameModelList.get(currentFrameIndex);
			if (!(frameModel instanceof QuestionFramePageModel)) {
				// 只有 "问卷模型" 才有效
				break;
			}

			QuestionFramePageModel questionFrameModel = (QuestionFramePageModel) frameModel;
			if (questionFrameModel.getAnswerDataSource() == null) {
				// 如果当前问题已经回答过了, 就可以忽略
				// 子类可以复写本方法, 好增加额外的逻辑
				break;
			}

			return true;
		} while (false);

		return false;
	}

	/**
	 * 检测本次测试是否有效
	 * 
	 * @return
	 */
	public boolean isTestEffectively() {
		// 如果全部问题都已经回答了, 当然就是有效了, 子类可以复写本方法, 好增加额外的逻辑
		return getUnansweredQuestionNumberList().size() <= 0 ? true : false;
	}

	/**
	 * 是否可以提前结束测试(当用户点击了 "结束测试" 按钮后, 正常都是不可以提前结束测试的, 而有一些量表将拥有此特权, 如 : CMMI)
	 * 
	 * @return
	 */
	public boolean isCanEndTestInAdvance() {
		return false;
	}

	/**
	 * 获取受访者信息
	 * 
	 * @return
	 */
	public RespondentsInformationBase getRespondentsInformation() {
		RespondentsInformationBase respondentsInformationBase = null;
		for (BaseFramePageModel baseFramePageModel : frameModelList) {
			if (baseFramePageModel.getAnswerDataSource() != null && (baseFramePageModel.getAnswerDataSource() instanceof RespondentsInformationBase)) {
				respondentsInformationBase = (RespondentsInformationBase) baseFramePageModel.getAnswerDataSource();
				break;
			}
		}

		return respondentsInformationBase;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 根据 问题索引 和 答案数据源 得到该问题的答案值
	 * 
	 * @param answerDataSource
	 * @param questionIndex
	 * @return
	 */
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfDefault(answerDataSource, -1);
	}

	/**
	 * 获取第一部分数据的"字节数据"
	 * 
	 * @return
	 */
	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfDefault(this);
	}

	/**
	 * 获取第二部分数据的"字符串数据", 会在 getResultsOfQuestionnaire 中将字符串数据转成字节数据
	 * 
	 * @param option
	 * @return
	 */
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfDefault(option, this);
	}

	/**
	 * 获取受访者回答问卷结果数据 : 1:普通 2:详细 3:全面 4:只上传数据不打印 5:普通预览 6:详细预览 7:全面预览
	 * 
	 * @return
	 */
	public byte[] getResultsOfQuestionnaire(final int option) {
		int dataIndex = 0;
		final byte[] dataOfSendToServer = new byte[700];

		// 获取第一部分数据流
		final byte[] dataOfPartOneAnswerResults = getPartOneAnswerResults();
		System.arraycopy(dataOfPartOneAnswerResults, 0, dataOfSendToServer, 0, dataOfPartOneAnswerResults.length);
		dataIndex = dataOfPartOneAnswerResults.length;

		DebugLog.i("FullQuestionnaireModel",
				"量表:" + getQuestionnaireCodeEnum().getShortName() + ", 第一部分数据[length=" + dataOfPartOneAnswerResults.length + "]" + ", data=[" + Arrays.toString(dataOfPartOneAnswerResults) + "]");

		// 获取第二部分数据流
		final String partTwoAnswerResultsString = getPartTwoAnswerResultsString(option);
		DebugLog.i("FullQuestionnaireModel", "量表:" + getQuestionnaireCodeEnum().getShortName() + ", 第二部分数据:[" + partTwoAnswerResultsString + "]");
		final byte[] dataOfPartTwoAnswerResults = ToolsFunctionForThisProgect.stringToBytesOfQuestionnaireResults(partTwoAnswerResultsString.toString());
		System.arraycopy(dataOfPartTwoAnswerResults, 0, dataOfSendToServer, dataIndex, dataOfPartTwoAnswerResults.length);

		return dataOfSendToServer;
	}

}
