package cn.skyduck.question_frame_fragment.hpii;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class HPIIPartOneFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 用户所做答案的集合
	private List<String> answerDataSource;
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

		final View rootView = inflater.inflate(R.layout.fragment_hpii_part_one, container, false);

		// title bar
		AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 第一个输入框
		final EditText input1EditText = (EditText) rootView.findViewById(R.id.input1_editText);

		// 第二个输入框
		final EditText input2EditText = (EditText) rootView.findViewById(R.id.input2_editText);

		// 第三个输入框
		final EditText input3EditText = (EditText) rootView.findViewById(R.id.input3_editText);

		if (!TextUtils.isEmpty(answerDataSource.get(0))) {
			input1EditText.setText(answerDataSource.get(0));
		}

		if (!TextUtils.isEmpty(answerDataSource.get(1))) {
			input2EditText.setText(answerDataSource.get(1));
		}

		if (!TextUtils.isEmpty(answerDataSource.get(2))) {
			input3EditText.setText(answerDataSource.get(2));
		}

		// 确定 按钮
		final Button okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String answer1 = input1EditText.getText().toString();
				String answer2 = input2EditText.getText().toString();
				String answer3 = input3EditText.getText().toString();
				if (!TextUtils.isEmpty(answer1) && !TextUtils.isEmpty(answer2) && !TextUtils.isEmpty(answer3)) {
					List<String> answerList = new ArrayList<String>();
					answerList.add(answer1);
					answerList.add(answer2);
					answerList.add(answer3);
					questionnaireFragmentCallback.onAnswerQuestion(answerList);
				} else {
					Toast.makeText(getActivity(), "请填写答案", Toast.LENGTH_SHORT).show();
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

	@SuppressWarnings("unchecked")
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		DebugLog.i(TAG, "Fragment-->onAttach");

		this.questionFragmentDataSource = (QuestionFragmentDataSource) getArguments().getSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
		if (this.questionFragmentDataSource != null) {

			// 获取用户的答案集合
			if (questionFragmentDataSource.getAnswerDataSource() instanceof List<?> && ((List<String>) questionFragmentDataSource.getAnswerDataSource()).size() == 3) {
				this.answerDataSource = (List<String>) questionFragmentDataSource.getAnswerDataSource();
			} else {
				this.answerDataSource = new ArrayList<String>(3);
				this.answerDataSource.add("");
				this.answerDataSource.add("");
				this.answerDataSource.add("");
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
