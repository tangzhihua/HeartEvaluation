package cn.skyduck.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.toolutils.DebugLog;

public class SettingCommenExpandableListAdapter extends BaseExpandableListAdapter {
	private final String TAG = this.getClass().getSimpleName();
	private Context context;

	// 组 数据源
	private List<QuestionnaireCategoryEnum> groupDataSource;
	// 常用测试 数据源
	private Set<QuestionnaireCodeEnum> commonTestSet = new HashSet<QuestionnaireCodeEnum>(64);

	// 获取用户最新设置的 "常用测试"
	public List<QuestionnaireCodeEnum> getLatestCommonTestList() {
		return new ArrayList<QuestionnaireCodeEnum>(commonTestSet);
	}

	public SettingCommenExpandableListAdapter(Context context) {
		this.context = context;

		Set<QuestionnaireCategoryEnum> keySetOfQuestionnaireCategory = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap().keySet();
		groupDataSource = new ArrayList<GlobalConstant.QuestionnaireCategoryEnum>(keySetOfQuestionnaireCategory);
		// 去掉 "常用测试" 这组数据
		groupDataSource.remove(QuestionnaireCategoryEnum.COMMON_TEST);

		// 添加 常用测试 数据源
		List<QuestionnaireCodeEnum> commonTestList = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap().get(QuestionnaireCategoryEnum.COMMON_TEST);
		commonTestSet.addAll(commonTestList);

	}

	// 得到大组成员的view
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.expandablelist_cell_parent_of_questionnaire, null);
		}
		final TextView queationnaireTypeNameTextView = (TextView) view.findViewById(R.id.queationnaire_type_name_textView);
		final QuestionnaireCategoryEnum questionnaireCategoryEnum = (QuestionnaireCategoryEnum) getGroup(groupPosition);
		queationnaireTypeNameTextView.setText(questionnaireCategoryEnum.getName());
		return view;
	}

	// 得到大组成员的id
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 得到大组成员数据
	public Object getGroup(int groupPosition) {
		QuestionnaireCategoryEnum questionnaireCategoryEnum = groupDataSource.get(groupPosition);
		return questionnaireCategoryEnum;
	}

	// 得到大组成员总数
	public int getGroupCount() {
		return groupDataSource.size();

	}

	// 得到小组成员的view
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.expandablelist_cell_child_of_questionnaire, null);
		}

		final QuestionnaireCodeEnum questionnaireCodeEnum = (QuestionnaireCodeEnum) getChild(groupPosition, childPosition);

		// 问卷 标题
		final TextView questionnaireNameTextView = (TextView) view.findViewById(R.id.questionnaire_name_textView);
		questionnaireNameTextView.setText(questionnaireCodeEnum.getFullName());
		// 设置为 "常用测试" 复选框
		final CheckBox settingCommenTestCheckBox = (CheckBox) view.findViewById(R.id.set_commen_checkBox);
		settingCommenTestCheckBox.setTag(questionnaireCodeEnum);
		if (commonTestSet.contains(questionnaireCodeEnum)) {
			settingCommenTestCheckBox.setChecked(true);
		} else {
			settingCommenTestCheckBox.setChecked(false);
		}
		settingCommenTestCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				QuestionnaireCodeEnum questionnaireCodeEnum = (QuestionnaireCodeEnum) buttonView.getTag();
				if (isChecked) {
					commonTestSet.add(questionnaireCodeEnum);
				} else {
					commonTestSet.remove(questionnaireCodeEnum);
				}

				DebugLog.i(TAG, "questionnaireCodeEnum=" + questionnaireCodeEnum.name());
				DebugLog.i(TAG, "commonTestSet=" + commonTestSet.toString());
			}
		});

		return view;
	}

	// 得到小组成员id
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// 得到子列表item数据
	public Object getChild(int groupPosition, int childPosition) {
		QuestionnaireCategoryEnum questionnaireCategoryEnum = groupDataSource.get(groupPosition);
		return GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap().get(questionnaireCategoryEnum).get(childPosition);
	}

	// 得到小组成员的数量
	public int getChildrenCount(int groupPosition) {
		QuestionnaireCategoryEnum questionnaireCategoryEnum = groupDataSource.get(groupPosition);
		return GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap().get(questionnaireCategoryEnum).size();
	}

	/**
	 * Indicates whether the child and group IDs are stable across changes to the
	 * underlying data. 表明大組和小组id是否稳定的更改底层数据。
	 * 
	 * @return whether or not the same ID always refers to the same object
	 * @see Adapter#hasStableIds()
	 */
	public boolean hasStableIds() {
		return true;
	}

	// 得到小组成员是否被选择
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
