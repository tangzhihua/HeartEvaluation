package cn.skyduck.question_frame_fragment.hpii;

import java.util.ArrayList;
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
import android.widget.PopupWindow;
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

public class HPIIPartSixFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	private TextView answerTextView1;
	private TextView answerTextView2;
	private TextView answerTextView3;
	private TextView answerTextView4;

	// 外部传入的各种数据源
	private QuestionFragmentDataSource questionFragmentDataSource;
	// 用户所做答案的集合
	private List<String> answerDataSource;
	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;
	// pop list 弹出点锚点view
	private View markViewForShowPoplistLayout;
	private ArrayAdapter<String> abilityValueAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		final View rootView = inflater.inflate(R.layout.fragment_hpii_part_six, container, false);

		// title bar
		final AnswerTitleBar titleBar = (AnswerTitleBar) rootView.findViewById(R.id.title_bar);
		titleBar.setDelegate(questionnaireFragmentCallback);
		titleBar.setDataSourceForQuestionPage(questionFragmentDataSource.getQuestionTotal(), questionFragmentDataSource.getCurrentQuestionIndex(), questionFragmentDataSource.isCanBeIgnoredThisQuestion());

		//
		markViewForShowPoplistLayout = rootView.findViewById(R.id.mark_view_for_show_poplist_layout);

		answerTextView1 = (TextView) rootView.findViewById(R.id.answer_textView1);
		answerTextView1.setText(answerDataSource.get(0).toString());

		answerTextView2 = (TextView) rootView.findViewById(R.id.answer_textView2);
		answerTextView2.setText(answerDataSource.get(1).toString());

		answerTextView3 = (TextView) rootView.findViewById(R.id.answer_textView3);
		answerTextView3.setText(answerDataSource.get(2).toString());

		answerTextView4 = (TextView) rootView.findViewById(R.id.answer_textView4);
		answerTextView4.setText(answerDataSource.get(3).toString());

		List<String> abilityValueEnumList = GlobalConstant.ProfessionalValueEnum.getNameList();
		this.abilityValueAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, abilityValueEnumList);
		answerTextView1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showRadioPopList(getResources().getString(R.string.HPII_prompt3), abilityValueAdapter, answerTextView1);
			}
		});

		answerTextView2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showRadioPopList(getResources().getString(R.string.HPII_prompt3), abilityValueAdapter, answerTextView2);
			}
		});
		answerTextView3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showRadioPopList(getResources().getString(R.string.HPII_prompt3), abilityValueAdapter, answerTextView3);
			}
		});
		answerTextView4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showRadioPopList(getResources().getString(R.string.HPII_prompt3), abilityValueAdapter, answerTextView4);
			}
		});
		// 确定 按钮
		final Button okButton = (Button) rootView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isSaveTesterInfoCompleted()) {
					questionnaireFragmentCallback.onAnswerQuestion(answerDataSource);
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
			if (questionFragmentDataSource.getAnswerDataSource() instanceof List<?> && ((List<String>) questionFragmentDataSource.getAnswerDataSource()).size() == 4) {
				this.answerDataSource = (List<String>) questionFragmentDataSource.getAnswerDataSource();
			} else {
				this.answerDataSource = new ArrayList<String>(4);
				this.answerDataSource.add("");
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
				case R.id.answer_textView1:// 第一题
					triggerView.setText(abilityValueAdapter.getItem(position));
					break;
				case R.id.answer_textView2:// 第二题
					triggerView.setText(abilityValueAdapter.getItem(position));
					break;
				case R.id.answer_textView3:// 第三题
					triggerView.setText(abilityValueAdapter.getItem(position));
					break;
				case R.id.answer_textView4:// 第四题
					triggerView.setText(abilityValueAdapter.getItem(position));
					break;
				case R.id.answer_textView5:// 第五题
					triggerView.setText(abilityValueAdapter.getItem(position));
					break;
				case R.id.answer_textView6:// 第六题
					triggerView.setText(abilityValueAdapter.getItem(position));
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

	// 判断是否全部选择了题
	private boolean isSaveTesterInfoCompleted() {

		// 第一题
		final String answer1String = answerTextView1.getText().toString();
		// 第二题
		final String answer2String = answerTextView2.getText().toString();
		// 第三题
		final String answer3String = answerTextView3.getText().toString();
		// 第四题
		final String answer4String = answerTextView4.getText().toString();

		String errorMessageString = "";
		do {
			if (TextUtils.isEmpty(answer1String)) {
				errorMessageString = getResources().getString(R.string.HPII_prompt2);
				break;
			}
			if (TextUtils.isEmpty(answer2String)) {
				errorMessageString = getResources().getString(R.string.HPII_prompt2);
				break;
			}
			if (TextUtils.isEmpty(answer3String)) {
				errorMessageString = getResources().getString(R.string.HPII_prompt2);
				break;
			}
			if (TextUtils.isEmpty(answer4String)) {
				errorMessageString = getResources().getString(R.string.HPII_prompt2);
				break;
			}
			answerDataSource = new ArrayList<String>();
			answerDataSource.add(answer1String);
			answerDataSource.add(answer2String);
			answerDataSource.add(answer3String);
			answerDataSource.add(answer4String);
			return true;
		} while (false);

		Toast.makeText(getActivity(), errorMessageString, Toast.LENGTH_SHORT).show();
		return false;
	}
}
