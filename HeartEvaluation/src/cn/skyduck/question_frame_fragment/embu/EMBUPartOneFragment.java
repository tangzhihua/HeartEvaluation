package cn.skyduck.question_frame_fragment.embu;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.CustomControlDelegate;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.custom_control.pop_list.RadioPopList;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class EMBUPartOneFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	private EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum fragmentStyleEnum;
	// 用户所做答案 - 数据源
	private Object userAnswerDataSource;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	// page 1
	// 父亲去世时, 您多大
	private EditText ageAtDeathOfFatherEditText;
	// 母亲去世时, 您多大
	private EditText ageAtDeathOfMotherEditText;
	private RadioButton firstQuestionYesRadioButton;
	private RadioButton firstQuestionNoRadioButton;
	private RadioButton secondQuestionYesRadioButton;
	private RadioButton secondQuestionNoRadioButton;

	// page 2
	private RadioButton yesRadioButton;
	private RadioButton noRadioButton;
	private EditText ageOfDivorceEditText;

	// page 3
	// page 4
	private TextView educationRadioListTextView;
	private TextView jobTypeRadioListTextView;

	// 适配器
	private ArrayAdapter<String> educationAdapter;// 文化程度
	private ArrayAdapter<String> jobTypeAdapter;// 工作类型

	// pop list 弹出点锚点view
	private View markViewForShowPoplistLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		View rootView = null;

		switch (this.fragmentStyleEnum) {
		case page_1:
			rootView = inflater.inflate(R.layout.fragment_embu_part_one_page_one, container, false);
			final EMBUPartOnePageOneUserAnswerDataSource userAnswerDataSourceOfPageOne = (EMBUPartOnePageOneUserAnswerDataSource) userAnswerDataSource;

			// 父亲去世时间
			ageAtDeathOfFatherEditText = (EditText) rootView.findViewById(R.id.ageAtDeathOfFather_editText);
			if (userAnswerDataSourceOfPageOne.getAgeAtDeathOfFather() > 0) {
				ageAtDeathOfFatherEditText.setText(Integer.valueOf(userAnswerDataSourceOfPageOne.getAgeAtDeathOfFather()).toString());
			}

			// 父亲是否健在
			firstQuestionYesRadioButton = (RadioButton) rootView.findViewById(R.id.first_question_yes_radioButton);
			firstQuestionYesRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageAtDeathOfFatherEditText.setText("");
						ageAtDeathOfFatherEditText.setEnabled(false);
					}
				}
			});
			firstQuestionNoRadioButton = (RadioButton) rootView.findViewById(R.id.first_question_no_radioButton);
			firstQuestionNoRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageAtDeathOfFatherEditText.setEnabled(true);
					}
				}
			});
			switch (userAnswerDataSourceOfPageOne.getIsFatherAlive()) {
			case 0:
				firstQuestionYesRadioButton.setChecked(true);
				ageAtDeathOfFatherEditText.setText("");
				ageAtDeathOfFatherEditText.setEnabled(false);
				break;
			case 1:
				firstQuestionNoRadioButton.setChecked(true);
				ageAtDeathOfFatherEditText.setEnabled(true);
				break;
			default:
				break;
			}

			// 母亲去世时间
			ageAtDeathOfMotherEditText = (EditText) rootView.findViewById(R.id.ageAtDeathOfMother_editText);
			if (userAnswerDataSourceOfPageOne.getAgeAtDeathOfMother() > 0) {
				ageAtDeathOfMotherEditText.setText(Integer.valueOf(userAnswerDataSourceOfPageOne.getAgeAtDeathOfMother()).toString());
			}
			// 母亲是否健在
			secondQuestionYesRadioButton = (RadioButton) rootView.findViewById(R.id.second_question_yes_radioButton);

			secondQuestionYesRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageAtDeathOfMotherEditText.setText("");
						ageAtDeathOfMotherEditText.setEnabled(false);
					}
				}
			});
			secondQuestionNoRadioButton = (RadioButton) rootView.findViewById(R.id.second_question_no_radioButton);
			secondQuestionNoRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageAtDeathOfMotherEditText.setEnabled(true);
					}
				}
			});
			switch (userAnswerDataSourceOfPageOne.getIsMotherAlive()) {
			case 0:
				secondQuestionYesRadioButton.setChecked(true);
				ageAtDeathOfMotherEditText.setText("");
				ageAtDeathOfMotherEditText.setEnabled(false);
				break;
			case 1:
				secondQuestionNoRadioButton.setChecked(true);
				ageAtDeathOfMotherEditText.setEnabled(true);
				break;
			default:
				break;
			}

			break;

		case page_2:
			rootView = inflater.inflate(R.layout.fragment_embu_part_one_page_two, container, false);
			final EMBUPartOnePageTwoUserAnswerDataSource userAnswerDataSourceOfPageTwo = (EMBUPartOnePageTwoUserAnswerDataSource) userAnswerDataSource;
			// 父母离异时间
			ageOfDivorceEditText = (EditText) rootView.findViewById(R.id.ageOfDivorce_editText);
			if (userAnswerDataSourceOfPageTwo.getAgeOfDivorce() > 0) {
				ageOfDivorceEditText.setText(Integer.valueOf(userAnswerDataSourceOfPageTwo.getAgeOfDivorce()).toString());
			}

			// 父母是否离异
			yesRadioButton = (RadioButton) rootView.findViewById(R.id.yes_radioButton);
			yesRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageOfDivorceEditText.setText("");
						ageOfDivorceEditText.setEnabled(false);
					}
				}
			});
			noRadioButton = (RadioButton) rootView.findViewById(R.id.no_radioButton);
			noRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						ageOfDivorceEditText.setEnabled(true);
					}
				}
			});
			switch (userAnswerDataSourceOfPageTwo.getIsParentsDivorced()) {
			case 0:
				yesRadioButton.setChecked(true);
				ageOfDivorceEditText.setText("");
				ageOfDivorceEditText.setEnabled(false);
				break;
			case 1:
				noRadioButton.setChecked(true);
				ageOfDivorceEditText.setEnabled(true);
				break;
			default:
				break;
			}
			break;
		case page_3:
		case page_4:
			rootView = inflater.inflate(R.layout.fragment_embu_part_one_page_three, container, false);
			//
			markViewForShowPoplistLayout = rootView.findViewById(R.id.mark_view_for_show_poplist_layout);

			// 文化程度
			final TextView educationTextView = (TextView) rootView.findViewById(R.id.education_textView);
			// 工作类型
			final TextView jobTypeTextView = (TextView) rootView.findViewById(R.id.job_type_textView);
			if (fragmentStyleEnum == EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_3) {
				educationTextView.setText("父亲文化程度 : ");
				jobTypeTextView.setText("父亲职业 : ");
			} else {
				educationTextView.setText("母亲文化程度 : ");
				jobTypeTextView.setText("母亲职业 : ");
			}

			educationRadioListTextView = (TextView) rootView.findViewById(R.id.education_radio_list_textView);
			educationRadioListTextView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showRadioPopList("请选择文化程度", educationAdapter, educationRadioListTextView);
				}
			});

			jobTypeRadioListTextView = (TextView) rootView.findViewById(R.id.job_type_radio_list_textView);
			jobTypeRadioListTextView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showRadioPopList("请选择职业", jobTypeAdapter, jobTypeRadioListTextView);
				}
			});

			if (fragmentStyleEnum == EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_3) {
				final EMBUPartOnePageThreeUserAnswerDataSource userAnswerDataSourceOfPageThree = (EMBUPartOnePageThreeUserAnswerDataSource) userAnswerDataSource;
				if (userAnswerDataSourceOfPageThree.getEducationEnumOfFather() != null) {
					educationRadioListTextView.setText(userAnswerDataSourceOfPageThree.getEducationEnumOfFather().getName());
				}
				if (userAnswerDataSourceOfPageThree.getJobTypeEnumOfFather() != null) {
					jobTypeRadioListTextView.setText(userAnswerDataSourceOfPageThree.getJobTypeEnumOfFather().getName());
				}
			} else {
				final EMBUPartOnePageFourUserAnswerDataSource userAnswerDataSourceOfPageFour = (EMBUPartOnePageFourUserAnswerDataSource) userAnswerDataSource;
				if (userAnswerDataSourceOfPageFour.getEducationEnumOfMother() != null) {
					educationRadioListTextView.setText(userAnswerDataSourceOfPageFour.getEducationEnumOfMother().getName());
				}
				if (userAnswerDataSourceOfPageFour.getJobTypeEnumOfMother() != null) {
					jobTypeRadioListTextView.setText(userAnswerDataSourceOfPageFour.getJobTypeEnumOfMother().getName());
				}
			}

			break;
		default:
			break;
		}

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForTransitionPage();

		// 确定 按钮
		final Button okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (fragmentStyleEnum) {
				case page_1:
					final EMBUPartOnePageOneUserAnswerDataSource userAnswerDataSourceOfPageOne = (EMBUPartOnePageOneUserAnswerDataSource) userAnswerDataSource;
					//
					if (!firstQuestionYesRadioButton.isChecked() && !firstQuestionNoRadioButton.isChecked()) {
						userAnswerDataSourceOfPageOne.setIsFatherAlive(-1);
					} else if (firstQuestionYesRadioButton.isChecked()) {
						userAnswerDataSourceOfPageOne.setIsFatherAlive(0);
						userAnswerDataSourceOfPageOne.setAgeAtDeathOfFather(0);
					} else {
						userAnswerDataSourceOfPageOne.setIsFatherAlive(1);
						if (!TextUtils.isEmpty(ageAtDeathOfFatherEditText.getText())) {
							int ageAtDeathOfFather = 0;
							try {
								ageAtDeathOfFather = Integer.valueOf(ageAtDeathOfFatherEditText.getText().toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (ageAtDeathOfFather > 0) {
								userAnswerDataSourceOfPageOne.setAgeAtDeathOfFather(ageAtDeathOfFather);
							}
						}
					}
					//
					if (!secondQuestionYesRadioButton.isChecked() && !secondQuestionNoRadioButton.isChecked()) {
						userAnswerDataSourceOfPageOne.setIsMotherAlive(-1);
					} else if (secondQuestionYesRadioButton.isChecked()) {
						userAnswerDataSourceOfPageOne.setIsMotherAlive(0);
						userAnswerDataSourceOfPageOne.setAgeAtDeathOfMother(0);
					} else {
						userAnswerDataSourceOfPageOne.setIsMotherAlive(1);
						if (!TextUtils.isEmpty(ageAtDeathOfMotherEditText.getText())) {
							int ageAtDeathOfMother = 0;
							try {
								ageAtDeathOfMother = Integer.valueOf(ageAtDeathOfMotherEditText.getText().toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (ageAtDeathOfMother > 0) {
								userAnswerDataSourceOfPageOne.setAgeAtDeathOfMother(ageAtDeathOfMother);
							}
						}
					}
					break;
				case page_2:
					final EMBUPartOnePageTwoUserAnswerDataSource userAnswerDataSourceOfPageTwo = (EMBUPartOnePageTwoUserAnswerDataSource) userAnswerDataSource;
					//
					if (!yesRadioButton.isChecked() && !noRadioButton.isChecked()) {
						userAnswerDataSourceOfPageTwo.setIsParentsDivorced(-1);
					} else if (yesRadioButton.isChecked()) {
						userAnswerDataSourceOfPageTwo.setIsParentsDivorced(0);
						userAnswerDataSourceOfPageTwo.setAgeOfDivorce(0);
					} else {
						userAnswerDataSourceOfPageTwo.setIsParentsDivorced(1);
						if (!TextUtils.isEmpty(ageOfDivorceEditText.getText())) {
							int ageOfDivorce = 0;
							try {
								ageOfDivorce = Integer.valueOf(ageOfDivorceEditText.getText().toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (ageOfDivorce > 0) {
								userAnswerDataSourceOfPageTwo.setAgeOfDivorce(ageOfDivorce);
							}
						}
					}
					break;
				case page_3:
				case page_4:
					GlobalConstant.EducationEnum educationEnum = GlobalConstant.EducationEnum.getItemByName(educationRadioListTextView.getText().toString());
					GlobalConstant.JobTypeEnum jobTypeEnum = GlobalConstant.JobTypeEnum.getItemByName(jobTypeRadioListTextView.getText().toString());
					if (fragmentStyleEnum == EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum.page_3) {
						final EMBUPartOnePageThreeUserAnswerDataSource userAnswerDataSourceOfPageThree = (EMBUPartOnePageThreeUserAnswerDataSource) userAnswerDataSource;
						userAnswerDataSourceOfPageThree.setEducationEnumOfFather(educationEnum);
						userAnswerDataSourceOfPageThree.setJobTypeEnumOfFather(jobTypeEnum);
					} else {
						final EMBUPartOnePageFourUserAnswerDataSource userAnswerDataSourceOfPageFour = (EMBUPartOnePageFourUserAnswerDataSource) userAnswerDataSource;
						userAnswerDataSourceOfPageFour.setEducationEnumOfMother(educationEnum);
						userAnswerDataSourceOfPageFour.setJobTypeEnumOfMother(jobTypeEnum);
					}

					break;
				default:
					break;
				}

				boolean isOK = true;
				switch (fragmentStyleEnum) {
				case page_1:
					final EMBUPartOnePageOneUserAnswerDataSource userAnswerDataSourceOfPageOne = (EMBUPartOnePageOneUserAnswerDataSource) userAnswerDataSource;
					if (userAnswerDataSourceOfPageOne.getIsFatherAlive() == -1 || userAnswerDataSourceOfPageOne.getAgeAtDeathOfMother() == -1) {
						isOK = false;
					}
					break;
				case page_2:
					final EMBUPartOnePageTwoUserAnswerDataSource userAnswerDataSourceOfPageTwo = (EMBUPartOnePageTwoUserAnswerDataSource) userAnswerDataSource;
					if (userAnswerDataSourceOfPageTwo.getIsParentsDivorced() == -1) {
						isOK = false;
					}
					break;
				case page_3:
					final EMBUPartOnePageThreeUserAnswerDataSource userAnswerDataSourceOfPageThree = (EMBUPartOnePageThreeUserAnswerDataSource) userAnswerDataSource;
					if (userAnswerDataSourceOfPageThree.getEducationEnumOfFather() == null || userAnswerDataSourceOfPageThree.getJobTypeEnumOfFather() == null) {
						isOK = false;
					}
					break;
				case page_4:
					final EMBUPartOnePageFourUserAnswerDataSource userAnswerDataSourceOfPageFour = (EMBUPartOnePageFourUserAnswerDataSource) userAnswerDataSource;
					if (userAnswerDataSourceOfPageFour.getEducationEnumOfMother() == null || userAnswerDataSourceOfPageFour.getJobTypeEnumOfMother() == null) {
						isOK = false;
					}
					break;
				default:
					break;
				}

				if (isOK) {
					questionnaireFragmentCallback.onAnswerQuestion(userAnswerDataSource);
				} else {
					Toast.makeText(EMBUPartOneFragment.this.getActivity(), "请做出合适的回答.", Toast.LENGTH_SHORT).show();
				}
			}
		});

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

		setPopListAdapter();
		this.questionFragmentDataSource = (QuestionFragmentDataSource) getArguments().getSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
		if (this.questionFragmentDataSource != null) {
			this.fragmentStyleEnum = (EMBUPartOneFragmentFactoryMethod.FragmentStyleEnum) questionFragmentDataSource.getFragmentStyle();
			//
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				switch (this.fragmentStyleEnum) {
				case page_1:
					this.userAnswerDataSource = new EMBUPartOnePageOneUserAnswerDataSource();
					break;
				case page_2:
					this.userAnswerDataSource = new EMBUPartOnePageTwoUserAnswerDataSource();
					break;
				case page_3:
					this.userAnswerDataSource = new EMBUPartOnePageThreeUserAnswerDataSource();
					break;
				case page_4:
					this.userAnswerDataSource = new EMBUPartOnePageFourUserAnswerDataSource();
					break;
				default:
					break;
				}

			} else {
				this.userAnswerDataSource = questionFragmentDataSource.getAnswerDataSource();
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

	/**
	 * 设置 poplist 适配器
	 */
	private void setPopListAdapter() {

		// 教育程度
		List<String> seducationEnumList = GlobalConstant.EducationEnum.getNameList();
		this.educationAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, seducationEnumList);

		// 工作类型
		List<String> jobTypeList = GlobalConstant.JobTypeEnum.getNameList();
		this.jobTypeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, jobTypeList);

	}

	private PopupWindow popupWindow;
	private RadioPopList radioPopList;

	// 激活一个单选弹出对话框
	private void showRadioPopList(String title, ArrayAdapter<String> adapter, View triggerView) {

		if (popupWindow == null) {

			// 加载popupWindow的布局文件
			radioPopList = new RadioPopList(getActivity(), radioPopListDelegate);

			// 声明一个弹出框
			popupWindow = new PopupWindow(radioPopList, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setOutsideTouchable(true);
			popupWindow.setAnimationStyle(R.style.PopupAnimation);
			popupWindow.setFocusable(true);
		}

		radioPopList.setTitle(title);
		radioPopList.setTag(triggerView);
		radioPopList.setAdapter(adapter);
		popupWindow.showAsDropDown(markViewForShowPoplistLayout);
	}

	private CustomControlDelegate radioPopListDelegate = new CustomControlDelegate() {

		@Override
		public void customControlOnAction(final View contorl, final Object actionTypeEnum) {
			RadioPopList.ActionEnum actionEnum = (RadioPopList.ActionEnum) actionTypeEnum;
			switch (actionEnum) {
			case LIST_ITEM_CLICK:
				popupWindow.dismiss();
				TextView triggerView = (TextView) contorl.getTag();
				final int position = ((RadioPopList) contorl).getPositionOfSelectedItem();

				switch (triggerView.getId()) {
				case R.id.job_type_radio_list_textView:// 工作类别
					triggerView.setText(jobTypeAdapter.getItem(position));
					break;
				case R.id.education_radio_list_textView:// 文化程度
					triggerView.setText(educationAdapter.getItem(position));
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
}
