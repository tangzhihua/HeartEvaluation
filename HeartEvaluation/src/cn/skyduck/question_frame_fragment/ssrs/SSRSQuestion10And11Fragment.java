package cn.skyduck.question_frame_fragment.ssrs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class SSRSQuestion10And11Fragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 界面 - 数据源
	private String pageDataSource;
	// 用户所做答案 - 数据源
	private SSRSQuestion10And11UserAnswerDataSource userAnswerDataSource;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	// 第二部分 答案
	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private CheckBox checkBox3;
	private CheckBox checkBox4;
	private CheckBox checkBox5;
	private CheckBox checkBox6;
	private CheckBox checkBox7;
	private CheckBox checkBox8;

	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;

	// 确定按钮
	private Button okButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

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
			default:
				break;
			}

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_ssrs_10_and_11, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource);

		//
		checkBox1 = (CheckBox) rootView.findViewById(R.id.check1);
		checkBox1.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0]);
		checkBox2 = (CheckBox) rootView.findViewById(R.id.check2);
		checkBox2.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1]);
		checkBox3 = (CheckBox) rootView.findViewById(R.id.check3);
		checkBox3.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2]);
		checkBox4 = (CheckBox) rootView.findViewById(R.id.check4);
		checkBox4.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[3]);
		checkBox5 = (CheckBox) rootView.findViewById(R.id.check5);
		checkBox5.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[4]);
		checkBox6 = (CheckBox) rootView.findViewById(R.id.check6);
		checkBox6.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[5]);
		checkBox7 = (CheckBox) rootView.findViewById(R.id.check7);
		checkBox7.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[6]);
		checkBox8 = (CheckBox) rootView.findViewById(R.id.check8);
		checkBox8.setChecked(userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[7]);

		//
		editText1 = (EditText) rootView.findViewById(R.id.input_1_editText);
		editText1.setText(userAnswerDataSource.getOptionPartTwoInputAnswerArray()[0]);
		editText2 = (EditText) rootView.findViewById(R.id.input_2_editText);
		editText2.setText(userAnswerDataSource.getOptionPartTwoInputAnswerArray()[1]);
		editText3 = (EditText) rootView.findViewById(R.id.input_3_editText);
		editText3.setText(userAnswerDataSource.getOptionPartTwoInputAnswerArray()[2]);
		editText4 = (EditText) rootView.findViewById(R.id.input_4_editText);
		editText4.setText(userAnswerDataSource.getOptionPartTwoInputAnswerArray()[3]);
		editText5 = (EditText) rootView.findViewById(R.id.input_5_editText);
		editText5.setText(userAnswerDataSource.getOptionPartTwoInputAnswerArray()[4]);

		//
		okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[0] = checkBox1.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[1] = checkBox2.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[2] = checkBox3.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[3] = checkBox4.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[4] = checkBox5.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[5] = checkBox6.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[6] = checkBox7.isChecked();
				userAnswerDataSource.getOptionPartTwoCheckBoxAnswerArray()[7] = checkBox8.isChecked();

				userAnswerDataSource.getOptionPartTwoInputAnswerArray()[0] = editText1.getText().toString();
				userAnswerDataSource.getOptionPartTwoInputAnswerArray()[1] = editText2.getText().toString();
				userAnswerDataSource.getOptionPartTwoInputAnswerArray()[2] = editText3.getText().toString();
				userAnswerDataSource.getOptionPartTwoInputAnswerArray()[3] = editText4.getText().toString();
				userAnswerDataSource.getOptionPartTwoInputAnswerArray()[4] = editText5.getText().toString();

				questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
			}
		});

		// 单选答案组
		final RadioButton optionOneRadioButton = (RadioButton) rootView.findViewById(R.id.option_one_radioButton);
		optionOneRadioButton.setOnClickListener(radioOnClickListener);
		final RadioButton optionTwoRadioButton = (RadioButton) rootView.findViewById(R.id.option_two_radioButton);
		optionTwoRadioButton.setOnClickListener(radioOnClickListener);
		if (userAnswerDataSource.getOptionPartOneRadioAnswer() == 0) {
			optionOneRadioButton.setChecked(true);
		} else if (userAnswerDataSource.getOptionPartOneRadioAnswer() == 1) {
			optionTwoRadioButton.setChecked(true);
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
			this.pageDataSource = (String) questionFragmentDataSource.getQuestionDataSource();
			//
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.userAnswerDataSource = new SSRSQuestion10And11UserAnswerDataSource();
			} else {
				this.userAnswerDataSource = (SSRSQuestion10And11UserAnswerDataSource) questionFragmentDataSource.getAnswerDataSource();
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
		checkBox1.setEnabled(enabled);
		checkBox2.setEnabled(enabled);
		checkBox3.setEnabled(enabled);
		checkBox4.setEnabled(enabled);
		checkBox5.setEnabled(enabled);
		checkBox6.setEnabled(enabled);
		checkBox7.setEnabled(enabled);
		checkBox8.setEnabled(enabled);

		editText1.setEnabled(enabled);
		editText2.setEnabled(enabled);
		editText3.setEnabled(enabled);
		editText4.setEnabled(enabled);
		editText5.setEnabled(enabled);

		okButton.setEnabled(enabled);
	}
}
