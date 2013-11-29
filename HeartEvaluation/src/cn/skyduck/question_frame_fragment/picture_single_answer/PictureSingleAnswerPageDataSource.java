package cn.skyduck.question_frame_fragment.picture_single_answer;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PictureSingleAnswerPageDataSource implements Serializable {
	// 问题圖片
	private int questionPictureResId;
	// 该问题对应的答案数量
	private int anwserTotal;

	public PictureSingleAnswerPageDataSource(int questionPictureResId, int anwserTotal) {
		this.questionPictureResId = questionPictureResId;
		this.anwserTotal = anwserTotal;
	}

	public int getQuestionPictureResId() {
		return questionPictureResId;
	}

	public int getAnwserTotal() {
		return anwserTotal;
	}

}
