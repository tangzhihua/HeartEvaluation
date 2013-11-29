package cn.skyduck.question_frame_fragment.rcbc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class RCBC30And31Fragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 界面 - 数据源
	private InputAndSingleOnePageDataSource pageDataSource;
	// 用户所做答案 - 数据源
	private RCBC30And31UserAnswerDataSource userAnswerDataSource;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	// 第二部分 答案
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioButton radioButton3;

	private EditText inputEditText;

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
			switch (checkedId) {
			case R.id.radioButton1:
				userAnswerDataSource.setOptionPartTwoRadioAnswer(0);
				break;
			case R.id.radioButton2:
				userAnswerDataSource.setOptionPartTwoRadioAnswer(1);
				break;
			case R.id.radioButton3:
				userAnswerDataSource.setOptionPartTwoRadioAnswer(2);
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

		final View rootView = inflater.inflate(R.layout.fragment_rcbc_30_31, container, false);

		// title bar
		AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource.getQuestionTitle());

		// 输入框
		inputEditText = (EditText) rootView.findViewById(R.id.input_editText);
		if (!TextUtils.isEmpty(userAnswerDataSource.getOptionPartTwoInputAnswer())) {
			inputEditText.setText(userAnswerDataSource.getOptionPartTwoInputAnswer());
		}

		//
		final RadioGroup radioGroup1 = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		radioGroup1.setOnCheckedChangeListener(onCheckedChangeListener);

		// 第二部分 答案
		radioButton1 = (RadioButton) rootView.findViewById(R.id.radioButton1);
		radioButton1.setText(pageDataSource.getAnwserList().get(0));
		radioButton2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
		radioButton2.setText(pageDataSource.getAnwserList().get(1));
		radioButton3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
		radioButton3.setText(pageDataSource.getAnwserList().get(2));
		switch (userAnswerDataSource.getOptionPartTwoRadioAnswer()) {
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

		//
		okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				userAnswerDataSource.setOptionPartTwoInputAnswer(inputEditText.getText().toString());
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
			this.pageDataSource = (InputAndSingleOnePageDataSource) questionFragmentDataSource.getQuestionDataSource();
			//
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.userAnswerDataSource = new RCBC30And31UserAnswerDataSource();
			} else {
				this.userAnswerDataSource = (RCBC30And31UserAnswerDataSource) questionFragmentDataSource.getAnswerDataSource();
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

		inputEditText.setEnabled(enabled);

		okButton.setEnabled(enabled);
	}
}
