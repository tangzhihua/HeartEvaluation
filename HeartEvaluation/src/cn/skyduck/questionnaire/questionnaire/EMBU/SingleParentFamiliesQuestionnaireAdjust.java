package cn.skyduck.questionnaire.questionnaire.EMBU;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * 目前的逻辑是, 只判断前3道题
 * @author skyduck
 *
 */
public class SingleParentFamiliesQuestionnaireAdjust implements Serializable {

	public SingleParentFamiliesQuestionnaireAdjust() {
		for (int i = 0; i < fatherNotChoiceQuestionIndexArray.length; i++) {
			fatherNotChoiceQuestionIndexArray[i] = false;
		}

		for (int i = 0; i < motherNotChoiceQuestionIndexArray.length; i++) {
			motherNotChoiceQuestionIndexArray[i] = false;
		}
	}

	private boolean isShowedAskDialog = false;
	private boolean isHideFatherOptions = false;
	private boolean isHideMotherOptions = false;

	// 目前逻辑设计成 只要需要监控用户前三道题是否有 "不适合选择" 的操作的, 如果连续3道题都选择了 父亲/母亲 选项中的 "不适合选择" 选项,
	// 就隐藏 父亲/母亲相关的问题选项.
	// 否则就不做任何控制.
	private boolean[] fatherNotChoiceQuestionIndexArray = new boolean[3];
	private boolean[] motherNotChoiceQuestionIndexArray = new boolean[3];

	// 父母选项是否将一直同时显示下去, 只要前3道题用户没有一直做出 "不适合选择" , 就认为用户是父母健全的, 所以要一直同时显示父母选项
	private boolean isFatherAndMotherAlwaysDisplayed = false;

	public boolean isFatherAndMotherAlwaysDisplayed() {
		return isFatherAndMotherAlwaysDisplayed;
	}

	public void addFatherNotChoice(final int currentQuestionIndex, boolean isNotChoice) {
		if (isFatherAndMotherAlwaysDisplayed) {
			return;
		}
		if (isHideFatherOptions || isHideMotherOptions) {
			return;
		}

		if (currentQuestionIndex < 3) {
			fatherNotChoiceQuestionIndexArray[currentQuestionIndex] = isNotChoice;
			isHideFatherOptions = true;
			for (int i = 0; i < fatherNotChoiceQuestionIndexArray.length; i++) {
				if (!fatherNotChoiceQuestionIndexArray[i]) {
					isHideFatherOptions = false;
					break;
				}
			}
		}

		if (currentQuestionIndex >= 2) {// 第4题之后
			if (!isHideFatherOptions && !isHideMotherOptions) {
				isFatherAndMotherAlwaysDisplayed = true;
			}
			return;
		}

	}

	public void addMotherNotChoice(final int currentQuestionIndex, boolean isNotChoice) {
		if (isFatherAndMotherAlwaysDisplayed) {
			return;
		}
		if (isHideFatherOptions || isHideMotherOptions) {
			return;
		}
		if (currentQuestionIndex >= 2) {// 第4题之后
			if (!isHideFatherOptions && !isHideMotherOptions) {
				isFatherAndMotherAlwaysDisplayed = true;
			}
			return;
		}

		motherNotChoiceQuestionIndexArray[currentQuestionIndex] = isNotChoice;
		isHideMotherOptions = true;
		for (int i = 0; i < motherNotChoiceQuestionIndexArray.length; i++) {
			if (!motherNotChoiceQuestionIndexArray[i]) {
				isHideMotherOptions = false;
				break;
			}
		}
	}

	// 是否需要隐藏父亲相关选项
	public boolean isHideFatherOptions() {
		return isHideFatherOptions;
	}

	// 是否需要隐藏母亲相关选项
	public boolean isHideMotherOptions() {
		return isHideMotherOptions;
	}

	// 希望父亲选项和母亲选项同时存在
	public void setNeedFatherAndMotherAlwaysDisplayed() {
		this.isHideFatherOptions = false;
		this.isHideMotherOptions = false;
		this.isFatherAndMotherAlwaysDisplayed = true;
	}

	public boolean isNeedShowAskDialog(final int currentQuestionIndex) {

		do {
			if (isFatherAndMotherAlwaysDisplayed) {
				break;
			}
			
			if (isShowedAskDialog && (isHideFatherOptions || isHideMotherOptions)) {
				break;
			}

			if (currentQuestionIndex != 2) {
				break;
			}
			
			isShowedAskDialog = true;
			return true;
		} while (false);
		
		return false;
	}
}
