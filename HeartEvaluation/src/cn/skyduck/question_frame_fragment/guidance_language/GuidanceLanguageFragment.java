package cn.skyduck.question_frame_fragment.guidance_language;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;

/**
 * 问卷指导语界面
 * 
 * @author hesiming
 * 
 */
public class GuidanceLanguageFragment extends Fragment {

	private final String TAG = this.getClass().getSimpleName();

	// 外部传入的数据源
	// 指导语列表
	private List<String> guidancelLanguageStrings;

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

		View rootView = inflater.inflate(R.layout.fragment_guidance_language_layout, container, false);

		final TextView instruction1InfoTextView = (TextView) rootView.findViewById(R.id.instruction1_info_textView);
		final TextView instruction2InfoTextView = (TextView) rootView.findViewById(R.id.instruction2_info_textView);

		do {
			if (guidancelLanguageStrings == null || guidancelLanguageStrings.size() <= 0) {
				// 入参错误
				instruction1InfoTextView.setText(getResources().getString(R.string.guidagce_language_prompt));
				assert false : "入参 guidanceLanguageDataSource 错误.";
				break;
			}

			instruction1InfoTextView.setText(guidancelLanguageStrings.get(0));

			if (guidancelLanguageStrings.size() >= 2) {
				instruction2InfoTextView.setText(guidancelLanguageStrings.get(1));
				instruction2InfoTextView.setVisibility(View.VISIBLE);
			} else {
				instruction2InfoTextView.setVisibility(View.GONE);
			}
		} while (false);

		// 确认并开始测试按钮
		final Button beginAnswerButon = (Button) rootView.findViewById(R.id.begin_answer_buton);
		beginAnswerButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				questionnaireFragmentCallback.onNextPage();
			}
		});

		// 上一页
		final Button pagePreviousButon = (Button) rootView.findViewById(R.id.page_previous_buton);
		pagePreviousButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 返回到 "填写测试者个人信息界面"
				questionnaireFragmentCallback.onPreviousPage();
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

		this.guidancelLanguageStrings = getArguments().getStringArrayList(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
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
