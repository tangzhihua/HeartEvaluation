package cn.skyduck.question_frame_fragment.embu;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EMBUPartOnePageTwoUserAnswerDataSource implements Serializable {
	// 父母在您?岁时离异时间
	private int ageOfDivorce = -1;

	// 父母是否离异
	private int isParentsDivorced = -1;

	public int getAgeOfDivorce() {
		return ageOfDivorce;
	}

	public void setAgeOfDivorce(int ageOfDivorce) {
		this.ageOfDivorce = ageOfDivorce;
	}

	public int getIsParentsDivorced() {
		return isParentsDivorced;
	}

	public void setIsParentsDivorced(int isParentsDivorced) {
		this.isParentsDivorced = isParentsDivorced;
	}

}
