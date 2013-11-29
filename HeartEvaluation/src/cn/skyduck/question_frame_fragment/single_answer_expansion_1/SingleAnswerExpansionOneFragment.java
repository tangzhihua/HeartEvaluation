package cn.skyduck.question_frame_fragment.single_answer_expansion_1;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

public class SingleAnswerExpansionOneFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	//
	private SingleAnswerExpansionOnePageDataSource pageDataSource;
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

		final View rootView = inflater.inflate(R.layout.fragment_single_answer, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		// 隐藏答题提示
		final TextView promptTextView = (TextView) rootView.findViewById(R.id.prompt_textView);
		promptTextView.setVisibility(View.GONE);

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource.getQuestionTitle());

		// 单选答案组
		final RadioGroup answerButtonRadioGroup = (RadioGroup) rootView.findViewById(R.id.answer_button_radioGroup);
		answerButtonRadioGroup.setOrientation(RadioGroup.HORIZONTAL);

		List<String> anwserList = pageDataSource.getAnwserList();
		int heightOfSpaceView = 160;
		int widthOfSpaceView = 10;

		// 左边的提示
		TextView promptLeftTextView = new TextView(getActivity());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		promptLeftTextView.setLayoutParams(layoutParams);
		promptLeftTextView.setVisibility(View.VISIBLE);
		promptLeftTextView.setText(pageDataSource.getPromptLeft());
		promptLeftTextView.setTextSize(28);
		promptLeftTextView.setTextColor(getResources().getColorStateList(R.color.TextPromptInfo));
		answerButtonRadioGroup.addView(promptLeftTextView);

		for (int i = 0; i < anwserList.size(); i++) {

			// 控件间隔
			TextView space = new TextView(getActivity());
			space.setHeight(heightOfSpaceView);
			space.setWidth(widthOfSpaceView);
			space.setVisibility(View.INVISIBLE);
			answerButtonRadioGroup.addView(space);

			RadioButton radioButton = new RadioButton(getActivity());
			radioButton.setMinimumWidth(40);
			radioButton.setGravity(Gravity.CENTER);
			radioButton.setText(anwserList.get(i));
			radioButton.setId(i);
			radioButton.setTextSize(28);

			/*
			 * 如果想代码设置RadioButton的button为空, 不能直接设置setButtonDrawable(null);
			 * 因为RadioButton setButtonDrawable方法的第一句就判断对象是否为null,那么很明显里面的不会走进来,
			 * 那既然我们看到了这点,我们就很简单的想到了.应该直接设置一个空对象. 于是,我们可以想到,直接设置一个没有内容的空对象即可 所以我就这样设置的
			 */
			Bitmap nullBitmap = null;
			radioButton.setButtonDrawable(new BitmapDrawable(nullBitmap));
			layoutParams = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			radioButton.setLayoutParams(layoutParams);
			radioButton.setBackgroundResource(R.drawable.selector_radiobutton_bg_of_blue);
			answerButtonRadioGroup.addView(radioButton);

			if (answerDataSource == i) {
				radioButton.setChecked(true);
			}

			radioButton.setOnClickListener(radioButtonClickListener);

			// 控件间隔
			space = new TextView(getActivity());
			space.setHeight(heightOfSpaceView);
			space.setWidth(widthOfSpaceView);
			space.setVisibility(View.INVISIBLE);
			answerButtonRadioGroup.addView(space);
		}

		// 右边的提示
		TextView promptRightTextView = new TextView(getActivity());
		layoutParams = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		promptRightTextView.setLayoutParams(layoutParams);
		promptRightTextView.setVisibility(View.VISIBLE);
		promptRightTextView.setText(pageDataSource.getPromptRight());
		promptRightTextView.setTextSize(28);
		promptRightTextView.setTextColor(getResources().getColorStateList(R.color.TextPromptInfo));
		answerButtonRadioGroup.addView(promptRightTextView);

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
		//
		this.pageDataSource = (SingleAnswerExpansionOnePageDataSource) questionFragmentDataSource.getQuestionDataSource();
		if (questionFragmentDataSource.getAnswerDataSource() == null) {
			this.answerDataSource = -1;
		} else {
			this.answerDataSource = (Integer) questionFragmentDataSource.getAnswerDataSource();
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
			questionnaireFragmentCallback.onAnswerQuestion(v.getId());
		}
	};
}
