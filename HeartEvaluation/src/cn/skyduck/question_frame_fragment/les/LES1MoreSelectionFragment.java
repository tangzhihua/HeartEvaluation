package cn.skyduck.question_frame_fragment.les;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class LES1MoreSelectionFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题title
	private String questionTitleString;
	// 用户答案
	private int[] answerList = new int[2];

	// 逻辑 : 第一次进入界面时, 第二部分问题按钮都是禁用的, 如果用户选择了 "未发生", 就跳到下一题; 如果用户选择了 "未发生"之外的选项,
	// 第二部分 "次数"的按钮才处于激活状态, 然后选择次数后, 跳到下一题;

	// 第一组 问题因为布局的特殊, 所以这里要分为两个 radioGroup
	private RadioGroup part_one_radioGroup1;
	private RadioGroup part_two_radioGroup1;

	// 第一部分 发生时间
	private RadioButton radioButton0InGroup1;// 未发生
	private RadioButton radioButton1InGroup1;// 1年内
	private RadioButton radioButton2InGroup1;// 1年前
	private RadioButton radioButton3InGroup1;// 发生时间较早，但影响深远至今
	// 第二部分 次数
	private RadioButton radioButton0InGroup2;// 1次
	private RadioButton radioButton1InGroup2;// 2次
	private RadioButton radioButton2InGroup2;// 3次以上

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

		final View rootView = inflater.inflate(R.layout.fragment_les1_selection_layout, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// question title
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(questionTitleString);

		//
		part_one_radioGroup1 = (RadioGroup) rootView.findViewById(R.id.part_one_radioGroup1);
		part_two_radioGroup1 = (RadioGroup) rootView.findViewById(R.id.part_two_radioGroup1);

		// 第一组问题 : 发生时间
		radioButton0InGroup1 = (RadioButton) rootView.findViewById(R.id.never_radioButton);
		radioButton0InGroup1.setTag(0);
		radioButton1InGroup1 = (RadioButton) rootView.findViewById(R.id.under_year_radioButton);
		radioButton1InGroup1.setTag(1);
		radioButton2InGroup1 = (RadioButton) rootView.findViewById(R.id.more_year_radioButton);
		radioButton2InGroup1.setTag(2);
		radioButton3InGroup1 = (RadioButton) rootView.findViewById(R.id.earlier_radioButton);
		radioButton3InGroup1.setTag(3);

		// 第二组问题 : 次数
		radioButton0InGroup2 = (RadioButton) rootView.findViewById(R.id.once_radioButton);
		radioButton0InGroup2.setTag(0);
		radioButton1InGroup2 = (RadioButton) rootView.findViewById(R.id.twice_radioButton);
		radioButton1InGroup2.setTag(1);
		radioButton2InGroup2 = (RadioButton) rootView.findViewById(R.id.more_than_3_times_radioButton);
		radioButton2InGroup2.setTag(2);

		// 第一组问题
		radioButton0InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton1InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton2InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton3InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);

		// 第二组问题
		radioButton0InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton1InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton2InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);

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
				this.answerList[0] = -1;
				this.answerList[1] = -1;
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
			// 这里实现 第一部分的 两组 radioGroup 实现互斥
			if (v.getId() == R.id.earlier_radioButton) {// "发生时间较早, 但是影响深远至今"
				if (part_one_radioGroup1.getCheckedRadioButtonId() != -1) {
					part_one_radioGroup1.clearCheck();
				}
			} else {
				if (part_two_radioGroup1.getCheckedRadioButtonId() != -1) {
					part_two_radioGroup1.clearCheck();
				}
			}

			if (v.getId() == R.id.never_radioButton) {// 选中了 "未发生", 可以直接跳到下一题
				answerList[0] = 0;
				answerList[1] = -1;
				questionnaireFragmentCallback.onAnswerQuestion(answerList);
			} else {

				// 如果选中了 "未发生" 之外的选项后, 可以激活第二组答案的选项
				setAnswerGroupOneAndTwoEnabled(true);
				answerList[0] = (Integer) v.getTag();
				if (answerList[1] != -1) {
					questionnaireFragmentCallback.onAnswerQuestion(answerList);
				}
			}

		}
	};

	private View.OnClickListener radioButtonClickListenerForGroup2 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			if (radioButton0InGroup1.isChecked()) {
				// 如果 "未发生" 处于选中状态, 用户选择第二组答案后, 要复位第一组答案
				answerList[0] = -1;
			}
			answerList[1] = (Integer) v.getTag();
			questionnaireFragmentCallback.onAnswerQuestion(answerList);
		}
	};

	private void setAnswerHistory() {

		// 第一组 答案
		switch (answerList[0]) {
		case 0:
			radioButton0InGroup1.setChecked(true);
			break;
		case 1:
			setAnswerGroupOneAndTwoEnabled(true);
			radioButton1InGroup1.setChecked(true);
			break;
		case 2:
			setAnswerGroupOneAndTwoEnabled(true);
			radioButton2InGroup1.setChecked(true);
			break;
		case 3:
			setAnswerGroupOneAndTwoEnabled(true);
			radioButton3InGroup1.setChecked(true);
			break;
		default:
			break;
		}

		// 第二组 答案
		switch (answerList[1]) {
		case 0:
			radioButton0InGroup2.setChecked(true);
			break;
		case 1:
			radioButton1InGroup2.setChecked(true);
			break;
		case 2:
			radioButton2InGroup2.setChecked(true);
			break;
		default:
			break;
		}
	}

	private void setAnswerGroupOneAndTwoEnabled(boolean enabled) {
		// 第二组问题
		radioButton0InGroup2.setEnabled(enabled);
		radioButton1InGroup2.setEnabled(enabled);
		radioButton2InGroup2.setEnabled(enabled);
	}
}
