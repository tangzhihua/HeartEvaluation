package cn.skyduck.question_frame_fragment.embu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.questionnaire.EMBU.QuestionnaireModelOfEMBU;
import cn.skyduck.questionnaire.questionnaire.EMBU.SingleParentFamiliesQuestionnaireAdjust;
import cn.skyduck.toolutils.DebugLog;

public class EMBUQuestionFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 用户所做答案 - 数据源
	private EMBUQuestionUserAnswerDataSource userAnswerDataSource;
	private SingleParentFamiliesQuestionnaireAdjust singleParentFamiliesQuestionnaireAdjust;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	private RadioGroup fatherQuestionRadioGroup;
	private RadioGroup motherQuestionRadioGroup;

	// 父亲选项
	private RadioButton left_0_radioButton;
	private RadioButton left_1_radioButton;
	private RadioButton left_2_radioButton;
	private RadioButton left_3_radioButton;
	private RadioButton left_4_radioButton;

	// 母亲选项
	private RadioButton right_0_radioButton;
	private RadioButton right_1_radioButton;
	private RadioButton right_2_radioButton;
	private RadioButton right_3_radioButton;
	private RadioButton right_4_radioButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	private View.OnClickListener radioButtonClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			checkAnswer();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_embu_question_frame, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText((String) questionFragmentDataSource.getQuestionDataSource());

		fatherQuestionRadioGroup = (RadioGroup) rootView.findViewById(R.id.left_question_radioGroup);
		motherQuestionRadioGroup = (RadioGroup) rootView.findViewById(R.id.right_question_radioGroup);
		// 父亲
		left_0_radioButton = (RadioButton) rootView.findViewById(R.id.left_0_radioButton);
		left_0_radioButton.setOnClickListener(radioButtonClickListener);
		left_1_radioButton = (RadioButton) rootView.findViewById(R.id.left_1_radioButton);
		left_1_radioButton.setOnClickListener(radioButtonClickListener);
		left_2_radioButton = (RadioButton) rootView.findViewById(R.id.left_2_radioButton);
		left_2_radioButton.setOnClickListener(radioButtonClickListener);
		left_3_radioButton = (RadioButton) rootView.findViewById(R.id.left_3_radioButton);
		left_3_radioButton.setOnClickListener(radioButtonClickListener);
		left_4_radioButton = (RadioButton) rootView.findViewById(R.id.left_4_radioButton);
		left_4_radioButton.setOnClickListener(radioButtonClickListener);
		// 母亲
		right_0_radioButton = (RadioButton) rootView.findViewById(R.id.right_0_radioButton);
		right_0_radioButton.setOnClickListener(radioButtonClickListener);
		right_1_radioButton = (RadioButton) rootView.findViewById(R.id.right_1_radioButton);
		right_1_radioButton.setOnClickListener(radioButtonClickListener);
		right_2_radioButton = (RadioButton) rootView.findViewById(R.id.right_2_radioButton);
		right_2_radioButton.setOnClickListener(radioButtonClickListener);
		right_3_radioButton = (RadioButton) rootView.findViewById(R.id.right_3_radioButton);
		right_3_radioButton.setOnClickListener(radioButtonClickListener);
		right_4_radioButton = (RadioButton) rootView.findViewById(R.id.right_4_radioButton);
		right_4_radioButton.setOnClickListener(radioButtonClickListener);

		// 先查看是否需要隐藏 父亲问题 / 母亲问题
		if (singleParentFamiliesQuestionnaireAdjust.isHideFatherOptions()) {
			// 隐藏父亲问题
			fatherQuestionRadioGroup.setVisibility(View.INVISIBLE);
			right_4_radioButton.setVisibility(View.INVISIBLE);
		} else if (singleParentFamiliesQuestionnaireAdjust.isHideMotherOptions()) {
			// 隐藏母亲问题
			motherQuestionRadioGroup.setVisibility(View.INVISIBLE);
			left_4_radioButton.setVisibility(View.INVISIBLE);
		} else if (singleParentFamiliesQuestionnaireAdjust.isFatherAndMotherAlwaysDisplayed()) {
			// 二者都不隐藏
			right_4_radioButton.setVisibility(View.GONE);
			left_4_radioButton.setVisibility(View.GONE);
		}

		// 父亲问题
		if (!singleParentFamiliesQuestionnaireAdjust.isHideFatherOptions()) {
			switch (userAnswerDataSource.getOptionOfFather()) {
			case 0:
				left_0_radioButton.setChecked(true);
				break;
			case 1:
				left_1_radioButton.setChecked(true);
				break;
			case 2:
				left_2_radioButton.setChecked(true);
				break;
			case 3:
				left_3_radioButton.setChecked(true);
				break;
			case 4:
				left_4_radioButton.setChecked(true);
				break;
			default:
				break;
			}
		}

		// 母亲问题
		if (!singleParentFamiliesQuestionnaireAdjust.isHideMotherOptions()) {
			switch (userAnswerDataSource.getOptionOfMother()) {
			case 0:
				right_0_radioButton.setChecked(true);
				break;
			case 1:
				right_1_radioButton.setChecked(true);
				break;
			case 2:
				right_2_radioButton.setChecked(true);
				break;
			case 3:
				right_3_radioButton.setChecked(true);
				break;
			case 4:
				right_4_radioButton.setChecked(true);
				break;
			default:
				break;
			}
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
			singleParentFamiliesQuestionnaireAdjust = ((QuestionnaireModelOfEMBU) questionFragmentDataSource.getFullQuestionnaireModel()).getSingleParentFamiliesQuestionnaireAdjust();
			//
			if (questionFragmentDataSource.getAnswerDataSource() instanceof EMBUQuestionUserAnswerDataSource) {
				this.userAnswerDataSource = (EMBUQuestionUserAnswerDataSource) questionFragmentDataSource.getAnswerDataSource();
			} else {
				this.userAnswerDataSource = new EMBUQuestionUserAnswerDataSource();
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

	private void checkAnswer() {
		do {
			// 父亲选项
			if (!singleParentFamiliesQuestionnaireAdjust.isHideFatherOptions()) {
				if (left_0_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfFather(0);
				} else if (left_1_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfFather(1);
				} else if (left_2_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfFather(2);
				} else if (left_3_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfFather(3);
				} else if (left_4_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfFather(4);
				} else {
					break;
				}
			}
			// 母亲选项
			if (!singleParentFamiliesQuestionnaireAdjust.isHideMotherOptions()) {
				if (right_0_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfMother(0);
				} else if (right_1_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfMother(1);
				} else if (right_2_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfMother(2);
				} else if (right_3_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfMother(3);
				} else if (right_4_radioButton.isChecked()) {
					userAnswerDataSource.setOptionOfMother(4);
				} else {
					break;
				}
			}

			if (!singleParentFamiliesQuestionnaireAdjust.isHideFatherOptions() && !singleParentFamiliesQuestionnaireAdjust.isHideMotherOptions()) {
				if (left_4_radioButton.isChecked() && right_4_radioButton.isChecked()) {
					// 父母选项不能同时选择 "不适合选择"
					Toast.makeText(getActivity(), "父母选项不能同时选择 \"不适合选择\" 选项.", Toast.LENGTH_SHORT).show();
					break;
				}
			}

			singleParentFamiliesQuestionnaireAdjust.addFatherNotChoice(questionFragmentDataSource.getCurrentQuestionIndex(), left_4_radioButton.isChecked());
			singleParentFamiliesQuestionnaireAdjust.addMotherNotChoice(questionFragmentDataSource.getCurrentQuestionIndex(), right_4_radioButton.isChecked());

			// 是否需要显示 "您只想回答和母亲/父亲相关的选项?" 提示框
			if (singleParentFamiliesQuestionnaireAdjust.isNeedShowAskDialog(questionFragmentDataSource.getCurrentQuestionIndex())) {
				String infoString = "";
				if (singleParentFamiliesQuestionnaireAdjust.isHideFatherOptions()) {
					infoString = "母亲";
				} else {
					infoString = "父亲";
				}
				final AlertDialog askDialog = new AlertDialog.Builder(EMBUQuestionFragment.this.getActivity()).setTitle("提示").setMessage("您只想回答和" + infoString + "相关的选项?")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								singleParentFamiliesQuestionnaireAdjust.setNeedFatherAndMotherAlwaysDisplayed();
								questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
							}
						}).setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
							}
						}).show();
				askDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {
						singleParentFamiliesQuestionnaireAdjust.setNeedFatherAndMotherAlwaysDisplayed();
						questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
					}
				});
			} else {
				//
				questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
			}
		} while (false);

	}
}
