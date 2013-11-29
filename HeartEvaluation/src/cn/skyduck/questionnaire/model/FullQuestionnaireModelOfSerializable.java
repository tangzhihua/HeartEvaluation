package cn.skyduck.questionnaire.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;

@SuppressWarnings("serial")
/**
 * 这是专门用于序列化量表的模型, 之前直接序列化 FullQuestionnaireModel 会有明显的性能瓶颈
 * 所以设计了双模型, 之前序列化 MMPI需要600毫秒, 优化后现在只需要40毫秒
 * 
 * @author skyduck
 *
 */
public class FullQuestionnaireModelOfSerializable implements Serializable {
	private final String TAG = this.getClass().getSimpleName();
	
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
		this.questionnaireStateEnum = questionnaireStateEnum;
	}

	// 当前量表类型
	private QuestionnaireCodeEnum questionnaireCodeEnum;

	public void setQuestionnaireCodeEnum(QuestionnaireCodeEnum questionnaireCodeEnum) {
		this.questionnaireCodeEnum = questionnaireCodeEnum;
	}

	public QuestionnaireCodeEnum getQuestionnaireCodeEnum() {
		return questionnaireCodeEnum;
	}

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

	public Date getEndTestdDate() {
		return new Date(endTestdDate);
	}

	public void setEndTestdDate(Date endTestdDate) {
		this.endTestdDate = endTestdDate.getTime();
	}

	// 当前页面帧进度索引(一定要设置成-1,这样外面计数器才会正确)
	private int currentFrameIndex;

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}

	public void setCurrentFrameIndex(int currentFrameIndex) {
		this.currentFrameIndex = currentFrameIndex;
	}

	// 所有FramePageModel中userAnswerDataSource, 这里是一一对应的
	private List<Object> userAnswerDataSourceModelList = new ArrayList<Object>();

	public List<Object> getUserAnswerDataSourceModelList() {
		return userAnswerDataSourceModelList;
	}

}
