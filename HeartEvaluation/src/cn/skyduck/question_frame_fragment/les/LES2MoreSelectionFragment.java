package cn.skyduck.question_frame_fragment.les;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class LES2MoreSelectionFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题title
	private String questionTitleString;
	// 用户答案
	private int[] answerList = new int[3];

	// 逻辑 : 第一次进入时, 只有第一部分的选项处于激活状态; 第一部分选择之后, 第二部分选项处于激活状态, 第二部分选择除"无影响"之外的选项时,
	// 第三部分的答题才变成激活状态; 如果选择了 "无影响" 可直接跳到下一题; 如果选择了 "无影响" 之外的选项,
	// 第三部分需要进行选择后, 才能跳到下一题.

	// 第一部分 性质(根据自身感受判断)：
	private RadioButton radioButton0InGroup1;// 好事
	private RadioButton radioButton1InGroup1;// 坏事
	// 第二部分 影响程度：
	private RadioButton radioButton0InGroup2;// 无影响
	private RadioButton radioButton1InGroup2;// 轻度
	private RadioButton radioButton2InGroup2;// 中等
	private RadioButton radioButton3InGroup2;// 很大
	private RadioButton radioButton4InGroup2;// 极大
	// 第三部分 影响持续时间：
	private RadioButton radioButton0InGroup3;// 3个月内
	private RadioButton radioButton1InGroup3;// 半年内
	private RadioButton radioButton2InGroup3;// 1年内
	private RadioButton radioButton3InGroup3;// 1年以上

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_les2_selection_layout, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// question title
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(questionTitleString);

		// 第一组问题
		radioButton0InGroup1 = (RadioButton) rootView.findViewById(R.id.radioGroup1_radioButton1);
		radioButton0InGroup1.setTag(0);
		radioButton1InGroup1 = (RadioButton) rootView.findViewById(R.id.radioGroup1_radioButton2);
		radioButton1InGroup1.setTag(1);

		// 第二组问题
		radioButton0InGroup2 = (RadioButton) rootView.findViewById(R.id.radioGroup2_radioButton1);
		radioButton0InGroup2.setTag(0);
		radioButton1InGroup2 = (RadioButton) rootView.findViewById(R.id.radioGroup2_radioButton2);
		radioButton1InGroup2.setTag(1);
		radioButton2InGroup2 = (RadioButton) rootView.findViewById(R.id.radioGroup2_radioButton3);
		radioButton2InGroup2.setTag(2);
		radioButton3InGroup2 = (RadioButton) rootView.findViewById(R.id.radioGroup2_radioButton4);
		radioButton3InGroup2.setTag(3);
		radioButton4InGroup2 = (RadioButton) rootView.findViewById(R.id.radioGroup2_radioButton5);
		radioButton4InGroup2.setTag(4);

		// 第三组问题
		radioButton0InGroup3 = (RadioButton) rootView.findViewById(R.id.radioGroup3_radioButton1);
		radioButton0InGroup3.setTag(0);
		radioButton1InGroup3 = (RadioButton) rootView.findViewById(R.id.radioGroup3_radioButton2);
		radioButton1InGroup3.setTag(1);
		radioButton2InGroup3 = (RadioButton) rootView.findViewById(R.id.radioGroup3_radioButton3);
		radioButton2InGroup3.setTag(2);
		radioButton3InGroup3 = (RadioButton) rootView.findViewById(R.id.radioGroup3_radioButton4);
		radioButton3InGroup3.setTag(3);

		// 第一组问题
		radioButton0InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton1InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);

		// 第二组问题
		radioButton0InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton1InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton2InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton3InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton4InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);

		// 第三组问题
		radioButton0InGroup3.setOnClickListener(radioButtonClickListenerForGroup3);
		radioButton1InGroup3.setOnClickListener(radioButtonClickListenerForGroup3);
		radioButton2InGroup3.setOnClickListener(radioButtonClickListenerForGroup3);
		radioButton3InGroup3.setOnClickListener(radioButtonClickListenerForGroup3);

		setAnswerHistory();

		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");
	}

	@Override
	public void onStop() {
		super.onStop();

		DebugLog.i(TAG, "Fragment-->onStop");
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		DebugLog.i(TAG, "Fragment-->onAttach");

		this.questionFragmentDataSource = (QuestionFragmentDataSource) getArguments().getSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
		if (this.questionFragmentDataSource != null) {
			this.questionTitleString = (String) questionFragmentDataSource.getQuestionDataSource();
			if (questionFragmentDataSource.getAnswerDataSource() != null) {
				this.answerList = (int[]) questionFragmentDataSource.getAnswerDataSource();
			} else {
				for (int i = 0; i < answerList.length; i++) {
					answerList[i] = -1;
				}
			}
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		DebugLog.i(TAG, "Fragment-->onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		DebugLog.i(TAG, "Fragment-->onResume");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		DebugLog.i(TAG, "Fragment-->onDestroy");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onActivityCreated");

	}

	private View.OnClickListener radioButtonClickListenerForGroup1 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			setAnswerGroupTwoEnabled(true);
			answerList[0] = (Integer) v.getTag();
			if (answerList[1] != -1 && answerList[2] != -1) {
				questionnaireFragmentCallback.onAnswerQuestion(answerList);
			}
		}
	};

	private View.OnClickListener radioButtonClickListenerForGroup2 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			answerList[1] = (Integer) v.getTag();

			if (v.getId() == R.id.radioGroup2_radioButton1) {// "无影响"
				answerList[2] = -1;
				questionnaireFragmentCallback.onAnswerQuestion(answerList);
			} else {
				setAnswerGroupThreeEnabled(true);
				if (answerList[2] != -1) {
					questionnaireFragmentCallback.onAnswerQuestion(answerList);
				}
			}
		}
	};

	private View.OnClickListener radioButtonClickListenerForGroup3 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			answerList[2] = (Integer) v.getTag();
			if (answerList[1] == 0) {
				answerList[1] = -1;
			}
			questionnaireFragmentCallback.onAnswerQuestion(answerList);
		}
	};

	private void setAnswerHistory() {

		switch (answerList[0]) {
		case 0:
			radioButton0InGroup1.setChecked(true);
			setAnswerGroupTwoEnabled(true);
			break;
		case 1:
			radioButton1InGroup1.setChecked(true);
			setAnswerGroupTwoEnabled(true);
			break;
		default:
			break;
		}

		switch (answerList[1]) {
		case 0:
			radioButton0InGroup2.setChecked(true);
			break;
		case 1:
			radioButton1InGroup2.setChecked(true);
			setAnswerGroupThreeEnabled(true);
			break;
		case 2:
			radioButton2InGroup2.setChecked(true);
			setAnswerGroupThreeEnabled(true);
			break;
		case 3:
			radioButton3InGroup2.setChecked(true);
			setAnswerGroupThreeEnabled(true);
			break;
		case 4:
			radioButton4InGroup2.setChecked(true);
			setAnswerGroupThreeEnabled(true);
			break;
		default:
			break;
		}

		switch (answerList[2]) {
		case 0:
			radioButton0InGroup3.setChecked(true);
			break;
		case 1:
			radioButton1InGroup3.setChecked(true);
			break;
		case 2:
			radioButton2InGroup3.setChecked(true);
			break;
		case 3:
			radioButton3InGroup3.setChecked(true);
			break;
		default:
			break;
		}
	}

	private void setAnswerGroupTwoEnabled(boolean enabled) {
		// 第二组问题
		radioButton0InGroup2.setEnabled(enabled);
		radioButton1InGroup2.setEnabled(enabled);
		radioButton2InGroup2.setEnabled(enabled);
		radioButton3InGroup2.setEnabled(enabled);
		radioButton4InGroup2.setEnabled(enabled);
	}

	private void setAnswerGroupThreeEnabled(boolean enabled) {
		// 第三组问题
		radioButton0InGroup3.setEnabled(enabled);
		radioButton1InGroup3.setEnabled(enabled);
		radioButton2InGroup3.setEnabled(enabled);
		radioButton3InGroup3.setEnabled(enabled);
	}
}
