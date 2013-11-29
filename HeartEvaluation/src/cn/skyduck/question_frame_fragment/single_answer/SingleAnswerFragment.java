package cn.skyduck.question_frame_fragment.single_answer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
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
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod.FragmentStyleEnum;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

public class SingleAnswerFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题数据源
	private SingleAnswerPageDataSource pageDataSource;
	private SingleAnswerFragmentFactoryMethod.FragmentStyleEnum fragmentStyleEnum = SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical;
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

		// 答题提示 (如果没有, 就隐藏这个控件)
		final TextView promptTextView = (TextView) rootView.findViewById(R.id.prompt_textView);
		if (TextUtils.isEmpty(pageDataSource.getPrompt())) {
			promptTextView.setVisibility(View.GONE);
		} else {
			promptTextView.setText(pageDataSource.getPrompt());
		}

		// 问题标题
		final TextView questionTitleTextView = (TextView) rootView.findViewById(R.id.question_title_textView);
		questionTitleTextView.setText(pageDataSource.getQuestionTitle());

		// 单选答案组
		final RadioGroup answerButtonRadioGroup = (RadioGroup) rootView.findViewById(R.id.answer_button_radioGroup);
		int orientationOfRadioGroup = RadioGroup.HORIZONTAL;

		List<String> anwserList = pageDataSource.getAnwserList();
		int widthOfOptionView = LayoutParams.WRAP_CONTENT;
		int heightOfOptionView = LayoutParams.WRAP_CONTENT;
		int heightOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 10);
		int widthOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 30);
		int gravityOfOptionView = Gravity.CENTER;
		int backgroundResourceOfRadioButton = R.drawable.selector_radiobutton_bg_of_blue;
		int textColorResId = R.drawable.selector_button_text_color;
		int padding = 0;
		switch (fragmentStyleEnum) {
		case vertical:// 垂直
			widthOfOptionView = LayoutParams.FILL_PARENT;
			orientationOfRadioGroup = RadioGroup.VERTICAL;
			gravityOfOptionView = Gravity.CENTER_VERTICAL;
			backgroundResourceOfRadioButton = R.drawable.selector_radiobutton_bg_of_gray;
			textColorResId = R.drawable.selector_radiobutton_text_color_of_gray;
			padding = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 10);
			break;
		case horizontal:// 水平
			heightOfOptionView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 60);
			
			heightOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 300);
			int widthOfSpaceViewTmp = 0;
			int widthOfOptionViewTmp = 0;
			if (anwserList.size() >= 5) {
				widthOfSpaceViewTmp = 20;
				widthOfOptionViewTmp = 160;
			} else if (anwserList.size() >= 4) {
				widthOfSpaceViewTmp = 40;
				widthOfOptionViewTmp = 190;
			} else if (anwserList.size() >= 3) {
				widthOfSpaceViewTmp = 60;
				widthOfOptionViewTmp = 250;
			} else {
				widthOfSpaceViewTmp = 300;
				widthOfOptionViewTmp = 160;
			}
			widthOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, widthOfSpaceViewTmp);
			widthOfOptionView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, widthOfOptionViewTmp);
			break;
		case true_or_false:// 判断题
			if (anwserList == null) {// 这里设计的是考虑哪些判断题, 但是不是 "是" 和 "否" 时,
																// 是需要在Model中自行设置的.
				anwserList = new ArrayList<String>(2);
				anwserList.add(getResources().getString(R.string.yes));
				anwserList.add(getResources().getString(R.string.no));
			}

			widthOfOptionView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 160);
			heightOfOptionView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 60);
			heightOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 300);
			widthOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 300);
			break;
		default:
			break;
		}

		answerButtonRadioGroup.setOrientation(orientationOfRadioGroup);
		for (int i = 0; i < anwserList.size(); i++) {
			RadioButton radioButton = new RadioButton(getActivity());
			radioButton.setGravity(gravityOfOptionView);
			ColorStateList colorStateList = (ColorStateList) getActivity().getResources().getColorStateList(textColorResId);
			radioButton.setTextColor(colorStateList);
			radioButton.setText(anwserList.get(i));
			radioButton.setId(i);
			radioButton.setTextSize(30);

			/*
			 * 如果想代码设置RadioButton的button为空, 不能直接设置setButtonDrawable(null);
			 * 因为RadioButton setButtonDrawable方法的第一句就判断对象是否为null,那么很明显里面的不会走进来,
			 * 那既然我们看到了这点,我们就很简单的想到了.应该直接设置一个空对象. 于是,我们可以想到,直接设置一个没有内容的空对象即可 所以我就这样设置的
			 */
			Bitmap nullBitmap = null;
			radioButton.setButtonDrawable(new BitmapDrawable(nullBitmap));
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(widthOfOptionView, heightOfOptionView);
			radioButton.setLayoutParams(layoutParams);
			// 在Layout中指定好background和padding以后，程序里面动态修改background之后padding就失效了，貌似是一个BUG，这里找到了一篇英文文章，简单翻译分享一下。
			// 也就是说, 只能在 setBackgroundResource 之后调用 setPadding
			radioButton.setBackgroundResource(backgroundResourceOfRadioButton);
			radioButton.setPadding(padding, padding, 0, padding);
			answerButtonRadioGroup.addView(radioButton);

			if (answerDataSource == i) {
				radioButton.setChecked(true);
			}

			radioButton.setOnClickListener(radioButtonClickListener);

			// 最后一个option后面不需要增加占位View了
			if (i == anwserList.size() - 1) {
				break;
			}

			// 控件间隔
			TextView space = new TextView(getActivity());
			space.setHeight(heightOfSpaceView);
			space.setWidth(widthOfSpaceView);
			space.setVisibility(View.INVISIBLE);
			answerButtonRadioGroup.addView(space);
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
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.answerDataSource = -1;
			} else {
				this.answerDataSource = (Integer) questionFragmentDataSource.getAnswerDataSource();
			}
			//
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

	private View.OnClickListener radioButtonClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			questionnaireFragmentCallback.onAnswerQuestion(v.getId());
		}
	};
}
