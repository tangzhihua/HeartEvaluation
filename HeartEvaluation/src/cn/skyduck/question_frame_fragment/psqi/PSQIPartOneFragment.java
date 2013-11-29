package cn.skyduck.question_frame_fragment.psqi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.question_frame_fragment.psqi.PSQIPartOneFragmentFactoryMethod.FragmentStyleEnum;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

@SuppressLint("SimpleDateFormat")
public class PSQIPartOneFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 用户答案
	private String answerString;
	// 設置是否顯示 24小時制輸入
	private FragmentStyleEnum fragmentStyleEnum;

	// 问题数据源
	private PSQIPartOnePageDataSource pageDataSource;
	private List<String> questionList;
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

		final View rootView = inflater.inflate(R.layout.fragment_psqi_part_one_layout, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		final TextView leftTextView = (TextView) rootView.findViewById(R.id.left_textView);
		final TextView rightTextView = (TextView) rootView.findViewById(R.id.right_textView);
		for (int i = 0; i < questionList.size(); i++) {
			if (i == 0) {
				leftTextView.setText(questionList.get(i).toString());
			} else {
				rightTextView.setText(questionList.get(i).toString());
			}

		}

		// 时间控件
		final EditText timeEditText = (EditText) rootView.findViewById(R.id.time_editText);

		if (!TextUtils.isEmpty(answerString)) {
			timeEditText.setText(answerString);
		}
		if (fragmentStyleEnum.equals(FragmentStyleEnum.clicked_open_time_selector)) {// 弹出时间选择器
			timeEditText.setHint("按24小时制输入");
			timeEditText.setInputType(InputType.TYPE_NULL);
			timeEditText.requestFocus();
			timeEditText.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
							timeEditText.setText(simpleDateFormat.format(new Date(0, 0, 0, hourOfDay, minute)));
						}
					}, 20, 0, true).show();// 设置 TimePickerDialog 的默认弹出时间是 20点00分

				}
			});
		} else {
			timeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
			timeEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
		}

		// 确定按钮
		final Button confirmButton = (Button) rootView.findViewById(R.id.confirm_button);

		confirmButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String editTextString = timeEditText.getText().toString();
				if (!TextUtils.isEmpty(editTextString)) {
					questionnaireFragmentCallback.onAnswerQuestion(editTextString);

				} else {
					Toast.makeText(getActivity(), "请输入数据！", Toast.LENGTH_SHORT).show();
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

		this.questionFragmentDataSource = (QuestionFragmentDataSource) getArguments().getSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
		if (this.questionFragmentDataSource != null) {
			//
			this.pageDataSource = (PSQIPartOnePageDataSource) questionFragmentDataSource.getQuestionDataSource();
			questionList = pageDataSource.getQuestionList();
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.answerString = "";
			} else {
				this.answerString = (String) questionFragmentDataSource.getAnswerDataSource();
			}
			if (this.questionFragmentDataSource.getFragmentStyle() != null) {
				this.fragmentStyleEnum = (FragmentStyleEnum) this.questionFragmentDataSource.getFragmentStyle();
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

}
