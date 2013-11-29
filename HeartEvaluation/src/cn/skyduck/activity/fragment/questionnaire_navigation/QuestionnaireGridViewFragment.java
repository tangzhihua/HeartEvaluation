package cn.skyduck.activity.fragment.questionnaire_navigation;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.AnswerActivity;
import cn.skyduck.adapter.QuestionnaireGridViewAdapter;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 量表分类展现 界面
 * 
 * @author skyduck
 * 
 */
public class QuestionnaireGridViewFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	private GridView questionnairegGridView;
	private QuestionnaireGridViewAdapter questionnaireGridViewAdapter;
	private int checkedIdOfQuestionnaireTypeRadioGroup = R.id.common_test_radioButton;

	/**
	 * 设置当前问卷类型, 这是为碎片切换时准备的
	 * 
	 * @param checkedIdOfQuestionnaireTypeRadioGroup
	 */
	public void setCheckedIdOfQuestionnaireTypeRadioGroup(int checkedIdOfQuestionnaireTypeRadioGroup) {
		this.checkedIdOfQuestionnaireTypeRadioGroup = checkedIdOfQuestionnaireTypeRadioGroup;
	}

	/**
	 * 更换 GridView 数据源, 此时不会触发 碎片的生命周期
	 * 
	 * @param checkedIdOfQuestionnaireTypeRadioGroup
	 */
	public void changeGridViewDataSource(int checkedIdOfQuestionnaireTypeRadioGroup) {
		ArrayList<QuestionnaireCodeEnum> newDataSourceOfGridView = getQuestionnaireList(checkedIdOfQuestionnaireTypeRadioGroup);
		questionnaireGridViewAdapter.changeDataSource(newDataSourceOfGridView);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		final View rootView = inflater.inflate(R.layout.fragment_questionnaire_gridview_layout, container, false);

		questionnairegGridView = (GridView) rootView.findViewById(R.id.questionnaire_gridview);

		ArrayList<QuestionnaireCodeEnum> dataSourceOfGridView = getQuestionnaireList(checkedIdOfQuestionnaireTypeRadioGroup);
		questionnaireGridViewAdapter = new QuestionnaireGridViewAdapter(getActivity(), dataSourceOfGridView);
		questionnairegGridView.setAdapter(questionnaireGridViewAdapter);
		questionnaireGridViewAdapter.setOnQuestionnaireClickListener(new QuestionnaireGridViewAdapter.OnQuestionnaireClickListener() {

			@Override
			public void onQuestionnaireClick(QuestionnaireCodeEnum questionnaireCodeEnum) {
				Intent intent = new Intent(getActivity(), AnswerActivity.class);
				intent.putExtra(AnswerActivity.IntentExtraTagEnum.QUESTIONAIRE_CODE.name(), questionnaireCodeEnum);
				startActivity(intent);
			}
		});

		//
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

		// 刷新网络信息
		ToolsFunctionForThisProgect.refreshNetInfoView(getActivity(), R.id.net_info_with_wifi_layout);
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

	/**
	 * 获取对应 问卷列表
	 * 
	 * @param checkedIdOfRadioGroup
	 * @return
	 */
	private ArrayList<QuestionnaireCodeEnum> getQuestionnaireList(int checkedIdOfRadioGroup) {
		ArrayList<QuestionnaireCodeEnum> questionnaireList = null;
		Map<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> questionnaireTypeMap = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap();
		switch (checkedIdOfRadioGroup) {
		// 常用测试
		case R.id.common_test_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.COMMON_TEST);
			break;
		// 人格类测验
		case R.id.personality_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.PERSONALITY);
			break;
		// 儿童及老年
		case R.id.children_elderly_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.CHILDREN_ELDERLY);
			break;
		// 精神类测验
		case R.id.mental_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.MENTAL);
			break;
		// 情绪/应激类
		case R.id.emotional_stress_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.EMOTIONS_STRESS);
			break;
		// 家庭生活类
		case R.id.family_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.FAMILY_LIFE);
			break;
		// 综合类测验
		case R.id.comprehensive_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.COMPLEX);
			break;
		// 其它测验
		case R.id.other_radioButton:
			questionnaireList = questionnaireTypeMap.get(QuestionnaireCategoryEnum.OTHER);
			break;
		default:
			break;
		}

		return questionnaireList;
	}
}
