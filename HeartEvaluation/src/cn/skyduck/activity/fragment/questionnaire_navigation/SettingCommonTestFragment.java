package cn.skyduck.activity.fragment.questionnaire_navigation;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import cn.skyduck.activity.R;
import cn.skyduck.adapter.SettingCommenExpandableListAdapter;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.toolutils.DebugLog;

/**
 * 设置 常用量表 界面
 * 
 * @author skyduck
 * 
 */
public class SettingCommonTestFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();
	private ExpandableListView settingCommonExpandableListView;
	private SettingCommenExpandableListAdapter settingCommenExpandableListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		final View rootView = inflater.inflate(R.layout.fragment_setting_common_test_layout, container, false);
		settingCommonExpandableListView = (ExpandableListView) rootView.findViewById(R.id.setting_common_expandableListView);
		settingCommenExpandableListAdapter = new SettingCommenExpandableListAdapter(getActivity());
		settingCommonExpandableListView.setAdapter(settingCommenExpandableListAdapter);
		// 用到ExpandableListView时有个箭头图标系统自带的在你自定义布局也不能去掉只要设置一个属性即可
		// settingCommonExpandableListView.setGroupIndicator(null);//不设置大组指示器图标，因为我们自定义设置了
		// settingCommonExpandableListView.setDivider(null);//设置图片可拉伸的

		// 遍历所有group,将所有项设置成默认展开
		int groupCount = settingCommonExpandableListView.getCount();
		for (int i = 0; i < groupCount; i++) {
			settingCommonExpandableListView.expandGroup(i);
		}
		//
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");

		List<QuestionnaireCodeEnum> commonTestList = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap().get(QuestionnaireCategoryEnum.COMMON_TEST);
		if (commonTestList != null) {
			commonTestList.clear();
			List<QuestionnaireCodeEnum> latestCommonTestList = settingCommenExpandableListAdapter.getLatestCommonTestList();
			commonTestList.addAll(latestCommonTestList);
		}

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
