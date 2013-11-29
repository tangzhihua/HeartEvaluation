package cn.skyduck.question_frame_fragment.aslec;

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

public class ASLECPictureFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();
	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题数据源
	private ASLECPicturePageDataSource pageDataSource;
	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;
	private int answerDataSource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_aslec_picture_layout, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 加载单选按钮
		final RadioButton radioButton0 = (RadioButton) rootView.findViewById(R.id.never_radioButton);
		radioButton0.setTag(0);
		radioButton0.setOnClickListener(radioButtonClickListener);

		final RadioButton radioButton1 = (RadioButton) rootView.findViewById(R.id.no_radioButton);
		radioButton1.setTag(1);
		radioButton1.setOnClickListener(radioButtonClickListener);

		final RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.mild_radioButton);
		radioButton2.setTag(2);
		radioButton2.setOnClickListener(radioButtonClickListener);

		final RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.moderate_radioButton);
		radioButton3.setTag(3);
		radioButton3.setOnClickListener(radioButtonClickListener);

		final RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.severe_radioButton);
		radioButton4.setTag(4);
		radioButton4.setOnClickListener(radioButtonClickListener);

		final RadioButton radioButton5 = (RadioButton) rootView.findViewById(R.id.heavy_radioButton);
		radioButton5.setTag(5);
		radioButton5.setOnClickListener(radioButtonClickListener);

		if (answerDataSource == (Integer) radioButton0.getTag()) {
			radioButton0.setChecked(true);
		} else if (answerDataSource == (Integer) radioButton1.getTag()) {
			radioButton1.setChecked(true);
		} else if (answerDataSource == (Integer) radioButton2.getTag()) {
			radioButton2.setChecked(true);
		} else if (answerDataSource == (Integer) radioButton3.getTag()) {
			radioButton3.setChecked(true);
		} else if (answerDataSource == (Integer) radioButton4.getTag()) {
			radioButton4.setChecked(true);
		} else if (answerDataSource == (Integer) radioButton5.getTag()) {
			radioButton5.setChecked(true);
		}

		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource.getQuestionTitleString());

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
			this.pageDataSource = (ASLECPicturePageDataSource) questionFragmentDataSource.getQuestionDataSource();
			//
			if (questionFragmentDataSource.getAnswerDataSource() instanceof Integer) {
				this.answerDataSource = (Integer) questionFragmentDataSource.getAnswerDataSource();
			} else {
				this.answerDataSource = -1;
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

	private View.OnClickListener radioButtonClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			questionnaireFragmentCallback.onAnswerQuestion((Integer) v.getTag());
		}
	};

}
