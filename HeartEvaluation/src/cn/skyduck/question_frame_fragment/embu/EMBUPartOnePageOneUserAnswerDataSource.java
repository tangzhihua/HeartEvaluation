package cn.skyduck.question_frame_fragment.embu;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EMBUPartOnePageOneUserAnswerDataSource implements Serializable {
	// 父亲是否健在
	private int isFatherAlive = -1;
	// 母亲是否健在
	private int isMotherAlive = -1;
	// 父亲去世时的年龄
	private int ageAtDeathOfFather = -1;
	// 母亲去世时的年龄
	private int ageAtDeathOfMother = -1;

	public int getAgeAtDeathOfFather() {
		return ageAtDeathOfFather;
	}

	public void setAgeAtDeathOfFather(int ageAtDeathOfFather) {
		this.ageAtDeathOfFather = ageAtDeathOfFather;
	}

	public int getAgeAtDeathOfMother() {
		return ageAtDeathOfMother;
	}

	public void setAgeAtDeathOfMother(int ageAtDeathOfMother) {
		this.ageAtDeathOfMother = ageAtDeathOfMother;
	}

	public int getIsFatherAlive() {
		return isFatherAlive;
	}

	public void setIsFatherAlive(int isFatherAlive) {
		this.isFatherAlive = isFatherAlive;
	}

	public int getIsMotherAlive() {
		return isMotherAlive;
	}

	public void setIsMotherAlive(int isMotherAlive) {
		this.isMotherAlive = isMotherAlive;
	}

}
