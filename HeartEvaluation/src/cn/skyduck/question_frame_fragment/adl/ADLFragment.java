package cn.skyduck.question_frame_fragment.adl;

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
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class ADLFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题数据源
	private SingleAnswerPageDataSource pageDataSource;
	private int answerDataSource;

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

		final View rootView = inflater.inflate(R.layout.fragment_adl, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource.getQuestionTitle());

		// 单选答案组
		final RadioButton option1RadioButton = (RadioButton) rootView.findViewById(R.id.option_1_radioButton);
		option1RadioButton.setTag(0);
		option1RadioButton.setOnClickListener(radioButtonClickListener);
		final RadioButton option2RadioButton = (RadioButton) rootView.findViewById(R.id.option_2_radioButton);
		option2RadioButton.setTag(1);
		option2RadioButton.setOnClickListener(radioButtonClickListener);
		final RadioButton option3RadioButton = (RadioButton) rootView.findViewById(R.id.option_3_radioButton);
		option3RadioButton.setTag(2);
		option3RadioButton.setOnClickListener(radioButtonClickListener);
		final RadioButton option4RadioButton = (RadioButton) rootView.findViewById(R.id.option_4_radioButton);
		option4RadioButton.setTag(3);
		option4RadioButton.setOnClickListener(radioButtonClickListener);
		final RadioButton option5RadioButton = (RadioButton) rootView.findViewById(R.id.option_5_radioButton);
		option5RadioButton.setTag(4);
		option5RadioButton.setOnClickListener(radioButtonClickListener);

		switch (this.answerDataSource) {
		case 0:
			option1RadioButton.setChecked(true);
			break;
		case 1:
			option2RadioButton.setChecked(true);
			break;
		case 2:
			option3RadioButton.setChecked(true);
			break;
		case 3:
			option4RadioButton.setChecked(true);
			break;
		case 4:
			option5RadioButton.setChecked(true);
			break;
		default:
			break;
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
			this.pageDataSource = (SingleAnswerPageDataSource) questionFragmentDataSource.getQuestionDataSource();
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
			questionnaireFragmentCallback.onAnswerQuestion(v.getTag());
		}
	};
}
