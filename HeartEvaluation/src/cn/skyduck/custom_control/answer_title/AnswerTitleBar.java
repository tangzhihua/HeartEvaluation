package cn.skyduck.custom_control.answer_title;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;

/**
 * 答题界面上面的 title bar
 * 
 * @author skyduck
 * 
 */
public class AnswerTitleBar extends LinearLayout {

	private IQuestionnaireFragmentCallback delegate;

	private TextView questionNumberTextView;
	private TextView questionPercentCompleteTextView;
	private ProgressBar questionNumberProgressBar;

	private Button nextButton;

	public AnswerTitleBar(Context context) {
		super(context);
	}

	public AnswerTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.view_answer_title, this, true);

		// 当前题号
		questionNumberTextView = (TextView) findViewById(R.id.question_number_textView);

		// 完成进度 百分比标签
		questionPercentCompleteTextView = (TextView) findViewById(R.id.question_percent_complete_textView);

		// 完成进度 进度条
		questionNumberProgressBar = (ProgressBar) findViewById(R.id.question_number_progressBar);

		// 上一题 按钮
		final Button previousButton = (Button) findViewById(R.id.previous_button);
		previousButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (delegate == null) {
					assert false : "delegate is null.";
					return;
				}
				delegate.onPreviousPage();
			}
		});

		// 下一题 按钮
		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (delegate == null) {
					assert false : "delegate is null.";
					return;
				}
				delegate.onNextPage();
			}
		});

		// 结束测试
		final Button endTestButton = (Button) findViewById(R.id.end_test_button);
		endTestButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (delegate == null) {
					assert false : "delegate is null.";
					return;
				}
				delegate.onEndTest();
			}
		});
	}

	public void setDelegate(IQuestionnaireFragmentCallback delegate) {
		this.delegate = delegate;
	}

	// 如果当前界面是 过渡界面, 就调用这个方法
	public void setDataSourceForTransitionPage() {
		questionNumberTextView.setText(getResources().getString(R.string.continue_to_answer));
		questionNumberProgressBar.setVisibility(View.INVISIBLE);
		questionPercentCompleteTextView.setVisibility(View.INVISIBLE);
	}

	// 如果当前界面是普通答题界面, 就调用这个方法
	public void setDataSourceForQuestionPage(final int questionTotal, final int currentQuestionIndex, final boolean isCanBeIgnoredThisQuestion) {
		// 当前题号(入参题号是从0开始, 所以这里要+1)
		questionNumberTextView.setText(String.format(getResources().getString(R.string.currently_question_number), currentQuestionIndex + 1));

		// 完成进度百分比
		int percentCompleteInt = 0;
		try {
			percentCompleteInt = (currentQuestionIndex + 1) * 100 / questionTotal;
		} catch (Exception e) {
			percentCompleteInt = 0;
		}
		questionPercentCompleteTextView.setText(percentCompleteInt + "%");

		// 完成进度 进度条
		questionNumberProgressBar.setProgress(percentCompleteInt);

		if (currentQuestionIndex + 1 >= questionTotal) {
			// 已经到了最后一题了, 隐藏 "下一题" 按钮
			nextButton.setVisibility(View.INVISIBLE);
		} else {
			if (!isCanBeIgnoredThisQuestion) {
				nextButton.setEnabled(false);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
}
