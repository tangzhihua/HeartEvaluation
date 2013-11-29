package cn.skyduck.question_frame_fragment.lsas;

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

public class LSASFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题title
	private String questionTitleString;
	// 用户答案
	private int[] answerList = new int[2];

	// 第一部分 害怕/焦虑
	private RadioButton radioButton0InGroup1;
	private RadioButton radioButton1InGroup1;
	private RadioButton radioButton2InGroup1;
	private RadioButton radioButton3InGroup1;

	// 第二组 问题因为布局的特殊, 所以这里要分为两个 radioGroup
	private RadioGroup part_one_radioGroup2;
	private RadioGroup part_two_radioGroup2;

	// 第二部分 回避程度
	private RadioButton radioButton0InGroup2;
	private RadioButton radioButton1InGroup2;
	private RadioButton radioButton2InGroup2;
	private RadioButton radioButton3InGroup2;

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

		final View rootView = inflater.inflate(R.layout.fragment_composite_twoway_selection_layout, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(questionTitleString);

		// 第一组问题
		radioButton0InGroup1 = (RadioButton) rootView.findViewById(R.id.no_radioButton);
		radioButton0InGroup1.setTag(0);
		radioButton1InGroup1 = (RadioButton) rootView.findViewById(R.id.mild_radioButton);
		radioButton1InGroup1.setTag(1);
		radioButton2InGroup1 = (RadioButton) rootView.findViewById(R.id.moderate_radioButton);
		radioButton2InGroup1.setTag(2);
		radioButton3InGroup1 = (RadioButton) rootView.findViewById(R.id.severe_radioButton);
		radioButton3InGroup1.setTag(3);

		//
		part_one_radioGroup2 = (RadioGroup) rootView.findViewById(R.id.part_one_radioGroup2);
		part_two_radioGroup2 = (RadioGroup) rootView.findViewById(R.id.part_two_radioGroup2);

		// 第二组问题
		radioButton0InGroup2 = (RadioButton) rootView.findViewById(R.id.never_shy_away_radioButton);// 从未回避
		radioButton0InGroup2.setTag(0);
		radioButton1InGroup2 = (RadioButton) rootView.findViewById(R.id.occasionally_avoided_radioButton);// 偶尔回避
		radioButton1InGroup2.setTag(1);
		radioButton2InGroup2 = (RadioButton) rootView.findViewById(R.id.often_avoided_radioButton);// 时常回避
		radioButton2InGroup2.setTag(2);
		radioButton3InGroup2 = (RadioButton) rootView.findViewById(R.id.frequently_avoided_radioButton);// 经常回避
		radioButton3InGroup2.setTag(3);

		// 第一组问题
		radioButton0InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton1InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton2InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);
		radioButton3InGroup1.setOnClickListener(radioButtonClickListenerForGroup1);

		// 第二组问题
		radioButton0InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton1InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton2InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);
		radioButton3InGroup2.setOnClickListener(radioButtonClickListenerForGroup2);

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
			// 第一组和第二组必须都做出选择后, 才能跳到下一题
			answerList[0] = (Integer) v.getTag();
			if (answerList[1] != -1) {
				questionnaireFragmentCallback.onAnswerQuestion(answerList);
			}
		}
	};

	private View.OnClickListener radioButtonClickListenerForGroup2 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// 这里实现 第一部分的 两组 radioGroup 实现互斥
			if (v.getId() == R.id.often_avoided_radioButton || v.getId() == R.id.frequently_avoided_radioButton) {// "时常回避(34-67%)"
																																																						// "经常回避(68-100%)"
				if (part_one_radioGroup2.getCheckedRadioButtonId() != -1) {
					part_one_radioGroup2.clearCheck();
				}
			} else {
				if (part_two_radioGroup2.getCheckedRadioButtonId() != -1) {
					part_two_radioGroup2.clearCheck();
				}
			}

			// 第一组和第二组必须都做出选择后, 才能跳到下一题
			answerList[1] = (Integer) v.getTag();
			if (answerList[0] != -1) {
				questionnaireFragmentCallback.onAnswerQuestion(answerList);
			}
		}
	};

	private void setAnswerHistory() {

		switch (answerList[0]) {
		case 0:
			radioButton0InGroup1.setChecked(true);
			break;
		case 1:
			radioButton1InGroup1.setChecked(true);
			break;
		case 2:
			radioButton2InGroup1.setChecked(true);
			break;
		case 3:
			radioButton3InGroup1.setChecked(true);
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
			break;
		case 2:
			radioButton2InGroup2.setChecked(true);
			break;
		case 3:
			radioButton3InGroup2.setChecked(true);
			break;
		default:
			break;
		}
	}
}
