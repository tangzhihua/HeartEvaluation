package cn.skyduck.question_frame_fragment.fill_tester_personal_information.children;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.activity.activity.IQuestionnaireFragmentCallback;
import cn.skyduck.custom_control.CustomControlDelegate;
import cn.skyduck.custom_control.pop_list.RadioPopList;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.NormalModeTypeEnum;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 填写受访者个人信息界面 - 孩子
 * 
 * @author skyduck
 * 
 */
public class FillTesterPersonalInformationFragmentOfChildren extends Fragment {

	private final String TAG = this.getClass().getSimpleName();

	// 外部传入数据源
	private RespondentsInformationOfChildren dataSourceOfRespondentsInfo;

	// 跟 AnswerActivity 通信的接口
	private IQuestionnaireFragmentCallback questionnaireFragmentCallback;

	// 用户输入信息控件
	private EditText cardIdEditText;
	private TextView sexTextView;
	private EditText areaEditText;
	private EditText nameEditText;
	private EditText ageEditText;
	private TextView changmoTextView;
	private TextView whoWillReplaceTheAnswerTextView;
	private EditText otherEditText;

	// 适配器
	private ArrayAdapter<String> sexAdapter;// 性别
	private ArrayAdapter<String> changmoAdapter;// 常模选择
	private ArrayAdapter<String> whoWillReplaceTheAnswerAdapter;// 填表人

	// pop list 弹出点锚点view
	private View markViewForShowPoplistLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		questionnaireFragmentCallback = (IQuestionnaireFragmentCallback) getActivity();

		View rootView = inflater.inflate(R.layout.fragment_fill_tester_personal_information_for_children_layout, container, false);

		//
		markViewForShowPoplistLayout = rootView.findViewById(R.id.mark_view_for_show_poplist_layout);

		// 卡号
		cardIdEditText = (EditText) rootView.findViewById(R.id.card_id_editText);
		cardIdEditText.requestFocus();// 一定要设置让一个控件得到焦点, 否则调用
		// 性别
		sexTextView = (TextView) rootView.findViewById(R.id.sex_textView);
		// 年龄
		ageEditText = (EditText) rootView.findViewById(R.id.age_editText);
		// 所属地区
		areaEditText = (EditText) rootView.findViewById(R.id.area_editText);
		// 姓名
		nameEditText = (EditText) rootView.findViewById(R.id.name_editText);
		// 常模
		changmoTextView = (TextView) rootView.findViewById(R.id.changmo_textView);
		// 填表人
		whoWillReplaceTheAnswerTextView = (TextView) rootView.findViewById(R.id.who_will_replace_the_answer_textView);
		// 其他
		otherEditText = (EditText) rootView.findViewById(R.id.other_editText);

		// 设置用户信息
		initControlDatasource();

		// 添加控件监听器
		addControlListener();

		// 界面中的三个按钮
		// 退出检测
		final Button quitAnswerButon = (Button) rootView.findViewById(R.id.quit_answer_buton);
		quitAnswerButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				questionnaireFragmentCallback.onEndTest();
			}
		});

		// 上一页
		final Button pagePreviousButon = (Button) rootView.findViewById(R.id.page_previous_buton);
		pagePreviousButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				questionnaireFragmentCallback.onPreviousPage();
			}
		});

		// 下一页
		final Button pageNextButon = (Button) rootView.findViewById(R.id.page_next_buton);
		pageNextButon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// 需要判断必选项是否填写完成
				if (isSaveTesterInfoCompleted()) {
					questionnaireFragmentCallback.onAnswerQuestion(dataSourceOfRespondentsInfo);
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

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		DebugLog.i(TAG, "Fragment-->onAttach");

		this.dataSourceOfRespondentsInfo = (RespondentsInformationOfChildren) getArguments().getSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey);
		setPopListAdapter();
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

	private void initControlDatasource() {
		// 卡号
		if (!TextUtils.isEmpty(dataSourceOfRespondentsInfo.getCardNumber())) {
			cardIdEditText.setText(dataSourceOfRespondentsInfo.getCardNumber());
		}
		// 性别
		if (dataSourceOfRespondentsInfo.getSexEnum() != null) {
			sexTextView.setText(dataSourceOfRespondentsInfo.getSexEnum().getName());
		} else {
			sexTextView.setText(getResources().getString(R.string.please_select));
		}
		// 年龄
		if (dataSourceOfRespondentsInfo.getAge() >= 1) {
			ageEditText.setText("" + dataSourceOfRespondentsInfo.getAge());
		}
		// 所属地区
		if (!TextUtils.isEmpty(dataSourceOfRespondentsInfo.getArea())) {
			areaEditText.setText(dataSourceOfRespondentsInfo.getArea());
		}
		// 姓名
		if (!TextUtils.isEmpty(dataSourceOfRespondentsInfo.getName())) {
			nameEditText.setText(dataSourceOfRespondentsInfo.getName());
		}
		// 常模
		if (dataSourceOfRespondentsInfo.getNormalModeTypeEnum() != null) {
			changmoTextView.setText(dataSourceOfRespondentsInfo.getNormalModeTypeEnum().getName());
		} else {
			changmoTextView.setText(getResources().getString(R.string.please_select));
		}
		// 填表人
		if (dataSourceOfRespondentsInfo.getWhoWillReplaceTheAnswerEnum() != null) {
			whoWillReplaceTheAnswerTextView.setText(dataSourceOfRespondentsInfo.getWhoWillReplaceTheAnswerEnum().getName());
		} else {
			whoWillReplaceTheAnswerTextView.setText(getResources().getString(R.string.please_select));
		}
		// 其他
		if (!TextUtils.isEmpty(dataSourceOfRespondentsInfo.getRemark())) {
			otherEditText.setText(dataSourceOfRespondentsInfo.getRemark());
		}

	}

	/**
	 * 
	 */
	private void addControlListener() {

		// 激活顺序是 从上到下, 从左到右
		/*************** 左边 ******************/

		// 卡号

		// 性别
		sexTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				hideSoftInput();

				showRadioPopList("请选择性别", sexAdapter, sexTextView);
			}
		});

		// 年龄

		// 所属地区

		/*************** 右边 ******************/
		// 姓名

		// 常模
		changmoTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hideSoftInput();

				showRadioPopList("请选择常模", changmoAdapter, changmoTextView);
			}
		});

		// 填表人
		whoWillReplaceTheAnswerTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hideSoftInput();

				showRadioPopList("请选择填表人", whoWillReplaceTheAnswerAdapter, whoWillReplaceTheAnswerTextView);
			}
		});

		// 其他

	}

	/**
	 * 设置 poplist 适配器
	 */
	private void setPopListAdapter() {

		// 性别
		List<String> sexEnumList = GlobalConstant.SexEnum.getNameList();
		this.sexAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, sexEnumList);

		// 常模
		List<NormalModeTypeEnum> normalModeTypeList = dataSourceOfRespondentsInfo.getQuestionnaireCodeEnum().getNormalModeTypeList();
		List<String> changmoEnumList = new ArrayList<String>();
		for (NormalModeTypeEnum item : normalModeTypeList) {
			changmoEnumList.add(item.getName());
		}
		this.changmoAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, changmoEnumList);

		// 填表人
		List<String> whoWillReplaceTheAnswerEnumList = GlobalConstant.WhoWillReplaceTheAnswerEnum.getNameList();
		this.whoWillReplaceTheAnswerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.poplist_item_layout, whoWillReplaceTheAnswerEnumList);

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
				case R.id.sex_textView:// 性别
					triggerView.setText(sexAdapter.getItem(position));
					break;
				case R.id.changmo_textView:// 常模选择
					triggerView.setText(changmoAdapter.getItem(position));

					break;
				case R.id.who_will_replace_the_answer_textView:// 填表人
					triggerView.setText(whoWillReplaceTheAnswerAdapter.getItem(position));

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

	// 隐藏和目标EditText控件绑定的软键盘界面
	private void hideSoftInput() {
		if (getActivity().getCurrentFocus() != null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
		}
	}

	private boolean isSaveTesterInfoCompleted() {

		// 卡号
		final String cardIdString = cardIdEditText.getText().toString();
		dataSourceOfRespondentsInfo.setCardNumber(cardIdString);

		// 性别 (必选)
		final String sexString = sexTextView.getText().toString();
		dataSourceOfRespondentsInfo.setSexEnum(GlobalConstant.SexEnum.fromName(sexString));

		// 年龄 (必选)
		final String ageString = ageEditText.getText().toString();
		int age = 0;
		if (!TextUtils.isEmpty(ageString)) {
			age = Integer.valueOf(ageString);
		}
		if (dataSourceOfRespondentsInfo.isWithinTheSpecifiedAgeRange(age)) {
			// 需要判断用户输入的年龄是否在规定的年龄范围内
			dataSourceOfRespondentsInfo.setAge(age);
		}

		// 所属地区
		final String areaString = areaEditText.getText().toString();
		dataSourceOfRespondentsInfo.setArea(areaString);

		// 姓名 (必选)
		final String nameString = nameEditText.getText().toString();
		dataSourceOfRespondentsInfo.setName(nameString);

		// 常模
		final String changmoString = changmoTextView.getText().toString();
		dataSourceOfRespondentsInfo.setNormalModeTypeEnum(GlobalConstant.NormalModeTypeEnum.getItemByName(changmoString));

		// 填表人
		final String whoWillReplaceTheAnswerString = whoWillReplaceTheAnswerTextView.getText().toString();
		dataSourceOfRespondentsInfo.setWhoWillReplaceTheAnswerEnum(GlobalConstant.WhoWillReplaceTheAnswerEnum.getItemByName(whoWillReplaceTheAnswerString));

		// 其他
		final String otherString = otherEditText.getText().toString();
		dataSourceOfRespondentsInfo.setRemark(otherString);

		String errorMessageString = "";
		do {
			if (TextUtils.isEmpty(nameString)) {
				errorMessageString = "名字不能为空.";
				break;
			}

			if (dataSourceOfRespondentsInfo.getSexEnum() == null) {
				errorMessageString = "性别不能为空.";
				break;
			}

			if (TextUtils.isEmpty(ageString)) {
				errorMessageString = "年龄不能为空.";
				break;
			}

			if (!dataSourceOfRespondentsInfo.isWithinTheSpecifiedAgeRange(age)) {
				errorMessageString = "年龄范围是" + dataSourceOfRespondentsInfo.getAgeRangeString();
				break;
			}

			return true;
		} while (false);

		Toast.makeText(getActivity(), errorMessageString, Toast.LENGTH_SHORT).show();
		return false;
	}

}
