package cn.skyduck.question_frame_fragment.picture_single_answer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.answer_title.AnswerTitleBar;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

public class PictureSingleAnswerFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();
	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 问题数据源
	private PictureSingleAnswerPageDataSource pageDataSource;
	private int answerDataSource;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	private int imageViewResId = 0;

	private int anwserTotal = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_picture_single_answer_layout, container, false);

		// title bar
		AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		final ImageView questionImageView = (ImageView) rootView.findViewById(R.id.question_imageView);
		questionImageView.setImageResource(imageViewResId);

		initAnswerButton(rootView);
		return rootView;
	}

	private void initAnswerButton(View rootView) {
		final LinearLayout leftLayout = (LinearLayout) rootView.findViewById(R.id.left_layout);
		final LinearLayout rightLayout = (LinearLayout) rootView.findViewById(R.id.right_layout);
		int heightOfSpaceView = 0;// 控件间隔
		int widthOfSpaceView = 0;// 控件间隔
		if (anwserTotal == 6) {
			heightOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 65);
			widthOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 65);
		} else {
			heightOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 25);
			widthOfSpaceView = (int) ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 25);
		}
		for (int i = 0; i < anwserTotal; i++) {
			ViewGroup layoutViewGroupTemp = null;
			if (i >= (anwserTotal / 2)) {// 大于长度的一半
				layoutViewGroupTemp = rightLayout;
			} else {// 不大于长度的一半
				layoutViewGroupTemp = leftLayout;
			}

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 120)), (int) (ToolsFunctionForThisProgect.getRawSize(TypedValue.COMPLEX_UNIT_DIP, 55)));

			RadioButton radioButton = new RadioButton(getActivity());
			radioButton.setGravity(Gravity.CENTER);
			radioButton.setText("" + (i + 1));
			radioButton.setId(i);
			radioButton.setTextSize(38);
			/*
			 * 如果想代码设置RadioButton的button为空, 不能直接设置setButtonDrawable(null);
			 * 因为RadioButton setButtonDrawable方法的第一句就判断对象是否为null,那么很明显里面的不会走进来,
			 * 那既然我们看到了这点,我们就很简单的想到了.应该直接设置一个空对象. 于是,我们可以想到,直接设置一个没有内容的空对象即可 所以我就这样设置的
			 */
			Bitmap nullBitmap = null;
			radioButton.setButtonDrawable(new BitmapDrawable(nullBitmap));
			radioButton.setLayoutParams(layoutParams);
			radioButton.setBackgroundResource(R.drawable.selector_radiobutton_bg_of_blue);
			layoutViewGroupTemp.addView(radioButton);

			if (answerDataSource == i) {
				radioButton.setChecked(true);
			}

			radioButton.setOnClickListener(radioButtonClickListener);

			// 最后一个option后面不需要增加占位View了
			if (i == (anwserTotal / 2) - 1 || i == anwserTotal - 1) {
				continue;
			}

			// 控件间隔
			TextView space = new TextView(getActivity());
			space.setHeight(heightOfSpaceView);
			space.setWidth(widthOfSpaceView);
			space.setVisibility(View.INVISIBLE);
			layoutViewGroupTemp.addView(space);
		}
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
			this.pageDataSource = (PictureSingleAnswerPageDataSource) questionFragmentDataSource.getQuestionDataSource();
			imageViewResId = pageDataSource.getQuestionPictureResId();
			anwserTotal = pageDataSource.getAnwserTotal();
			if (questionFragmentDataSource.getAnswerDataSource() == null) {
				this.answerDataSource = -1;
			} else {
				this.answerDataSource = (Integer) questionFragmentDataSource.getAnswerDataSource();
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
