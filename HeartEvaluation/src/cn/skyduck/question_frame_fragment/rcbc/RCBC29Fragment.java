package cn.skyduck.question_frame_fragment.rcbc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class RCBC29Fragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 用户所做答案 - 数据源
	private RCBC29UserAnswerDataSource userAnswerDataSource;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	// 第二部分 答案
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;

	private RadioButton radioButton4;
	private RadioButton radioButton5;
	private RadioButton radioButton6;

	private RadioButton radioButton7;
	private RadioButton radioButton8;
	private RadioButton radioButton9;

	// 确定按钮
	private Button okButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (group.getId()) {
			case R.id.radioGroup1:
				switch (checkedId) {
				case R.id.radioButton1:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0] = 0;
					break;
				case R.id.radioButton2:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0] = 1;
					break;
				case R.id.radioButton3:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0] = 2;
					break;
				default:
					break;
				}

				break;
			case R.id.radioGroup2:
				switch (checkedId) {
				case R.id.radioButton4:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1] = 0;
					break;
				case R.id.radioButton5:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1] = 1;
					break;
				case R.id.radioButton6:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1] = 2;
					break;
				default:
					break;
				}
				break;
			case R.id.radioGroup3:
				switch (checkedId) {
				case R.id.radioButton7:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2] = 0;
					break;
				case R.id.radioButton8:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2] = 1;
					break;
				case R.id.radioButton9:
					userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2] = 2;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}

		}
	};

	private View.OnClickListener radioOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.option_one_radioButton:
				userAnswerDataSource.setOptionPartOneRadioAnswer(0);
				userAnswerDataSource.resetPartTwoAnswer();
				questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
				break;
			case R.id.option_two_radioButton:
				userAnswerDataSource.setOptionPartOneRadioAnswer(1);
				setPartTwoAnswerEnabled(true);
				break;
			case R.id.option_three_radioButton:
				userAnswerDataSource.setOptionPartOneRadioAnswer(2);
				setPartTwoAnswerEnabled(true);
				break;
			default:
				break;
			}

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_rcbc_29, container, false);

		// title bar
		AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		//
		final RadioGroup radioGroup1 = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		radioGroup1.setOnCheckedChangeListener(onCheckedChangeListener);
		final RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.radioGroup2);
		radioGroup2.setOnCheckedChangeListener(onCheckedChangeListener);
		final RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.radioGroup3);
		radioGroup3.setOnCheckedChangeListener(onCheckedChangeListener);

		// 第二部分 答案
		radioButton1 = (RadioButton) rootView.findViewById(R.id.radioButton1);
		radioButton2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
		radioButton3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
		switch (userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0]) {
		case 0:
			radioButton1.setChecked(true);
			break;
		case 1:
			radioButton2.setChecked(true);
			break;
		case 2:
			radioButton3.setChecked(true);
			break;
		default:
			break;
		}

		radioButton4 = (RadioButton) rootView.findViewById(R.id.radioButton4);
		radioButton5 = (RadioButton) rootView.findViewById(R.id.radioButton5);
		radioButton6 = (RadioButton) rootView.findViewById(R.id.radioButton6);
		switch (userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1]) {
		case 0:
			radioButton4.setChecked(true);
			break;
		case 1:
			radioButton5.setChecked(true);
			break;
		case 2:
			radioButton6.setChecked(true);
			break;
		default:
			break;
		}

		radioButton7 = (RadioButton) rootView.findViewById(R.id.radioButton7);
		radioButton8 = (RadioButton) rootView.findViewById(R.id.radioButton8);
		radioButton9 = (RadioButton) rootView.findViewById(R.id.radioButton9);
		switch (userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2]) {
		case 0:
			radioButton7.setChecked(true);
			break;
		case 1:
			radioButton8.setChecked(true);
			break;
		case 2:
			radioButton9.setChecked(true);
			break;
		default:
			break;
		}

		//
		okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
			}
		});

		// 单选答案组
		final RadioButton optionOneRadioButton = (RadioButton) rootView.findViewById(R.id.option_one_radioButton);
		optionOneRadioButton.setOnClickListener(radioOnClickListener);
		final RadioButton optionTwoRadioButton = (RadioButton) rootView.findViewById(R.id.option_two_radioButton);
		optionTwoRadioButton.setOnClickListener(radioOnClickListener);
		final RadioButton optionThreeRadioButton = (RadioButton) rootView.findViewById(R.id.option_three_radioButton);
		optionThreeRadioButton.setOnClickListener(radioOnClickListener);

		setPartTwoAnswerEnabled(false);
		if (userAnswerDataSource.getOptionPartOneRadioAnswer() == 0) {
			optionOneRadioButton.setChecked(true);
		} else if (userAnswerDataSource.getOptionPartOneRadioAnswer() == 1) {
			optionTwoRadioButton.setChecked(true);
			setPartTwoAnswerEnabled(true);
		} else if (userAnswerDataSource.getOptionPartOneRadioAnswer() == 2) {
			optionThreeRadioButton.setChecked(true);
			setPartTwoAnswerEnabled(true);
		}

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
			//
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.userAnswerDataSource = new RCBC29UserAnswerDataSource();
			} else {
				this.userAnswerDataSource = (RCBC29UserAnswerDataSource) questionFragmentDataSource.getAnswerDataSource();
			}
		} else {
			assert false : "外部传入的数据源 questionFragmentDataSource 为空.";
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

	private void setPartTwoAnswerEnabled(boolean enabled) {
		radioButton1.setEnabled(enabled);
		radioButton2.setEnabled(enabled);
		radioButton3.setEnabled(enabled);
		radioButton4.setEnabled(enabled);
		radioButton5.setEnabled(enabled);
		radioButton6.setEnabled(enabled);
		radioButton7.setEnabled(enabled);
		radioButton8.setEnabled(enabled);
		radioButton9.setEnabled(enabled);

		okButton.setEnabled(enabled);
	}
}
