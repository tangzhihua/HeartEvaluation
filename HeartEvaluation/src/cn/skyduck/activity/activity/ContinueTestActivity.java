package cn.skyduck.activity.activity;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.adapter.QuestionnaireListViewAdapter;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.FunctionOptionsEnum;
import cn.skyduck.global_data_cache.GlobalConstant.PrintTypeEnum;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.domain_protocol.account_login.LoginNetService;
import cn.skyduck.net_service.domain_protocol.account_login.LogonNetRequestBean;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.SimpleProgressDialog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * "继续未完成" 界面
 * 
 * @author hesiming
 * 
 */
@SuppressLint({ "SimpleDateFormat", "HandlerLeak" })
public class ContinueTestActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	private static enum IntentRequestCodeEnum {
		// 去 打印 界面
		TO_PRINT_ACTIVITY,
		// 去 预览 界面
		TO_PREVIEW_ACTIVITY
	};

	// 用户登录状态 标签
	private TextView userLoginStateTextView;

	// 开始时间 按钮
	private Button beginTimeButton;
	// 结束时间 按钮
	private Button endTimeButton;
	// 卡号
	private EditText cardNumberEditText;
	// 名字
	private EditText nameEditText;

	// 未完成的量表 适配器
	private QuestionnaireListViewAdapter listViewAdapter;

	// 经过筛选之后的列表
	private List<FilterQuestionnaireListItem> listOfFilter = new ArrayList<FilterQuestionnaireListItem>();

	// 默认排序
	private RadioButton defaultSortRadioButton;

	// 最后的搜索条件
	private FilterCriteria lastFilterCriteria = new FilterCriteria();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_continue_test_layout);

		// 默认才进入界面时, 展现全部数据
		List<FullQuestionnaireModel> questionnaireModels = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		for (int i = 0; i < questionnaireModels.size(); i++) {
			FilterQuestionnaireListItem filterQuestionnaireListItem = new FilterQuestionnaireListItem(i, questionnaireModels.get(i));
			listOfFilter.add(filterQuestionnaireListItem);
		}

		// 用户登录状态
		userLoginStateTextView = (TextView) findViewById(R.id.user_login_state_textView);
		userLoginStateTextView.setText(ToolsFunctionForThisProgect.getUserLoginState());

		// 全选 按钮
		final CheckBox selectAllCheckBox = (CheckBox) findViewById(R.id.select_all_checkBox);
		selectAllCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (listViewAdapter != null) {
					listViewAdapter.setSelectAll(isChecked);
				}
			}
		});

		// 开始时间 按钮
		beginTimeButton = (Button) findViewById(R.id.begin_time_button);
		beginTimeButton.setText("");
		beginTimeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(beginTimeButton);
			}
		});

		// 结束时间 按钮
		endTimeButton = (Button) findViewById(R.id.end_time_button);
		endTimeButton.setText("");
		endTimeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(endTimeButton);
			}
		});

		// 批量处理 按钮
		final Button batchButton = (Button) findViewById(R.id.batch_button);
		batchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showFunctionOptionsForMultipleQuestionnaire(listViewAdapter.getCheckedIndexArray());
			}
		});

		// 卡号
		cardNumberEditText = (EditText) findViewById(R.id.card_id_editText);
		// 名字
		nameEditText = (EditText) findViewById(R.id.name_editText);

		// 查询 按钮
		final Button searchButton = (Button) findViewById(R.id.search_button);
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date beginTimeDate = null;
				if (!TextUtils.isEmpty(beginTimeButton.getText())) {
					try {
						beginTimeDate = simpleDateFormat.parse(beginTimeButton.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
						beginTimeDate = null;
					}
				}
				Date endTimeDate = null;
				if (!TextUtils.isEmpty(endTimeButton.getText())) {
					try {
						endTimeDate = simpleDateFormat.parse(endTimeButton.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
						endTimeDate = null;
					}
				}
				// 卡号
				String cardNumberString = null;
				if (!TextUtils.isEmpty(cardNumberEditText.getText())) {
					cardNumberString = cardNumberEditText.getText().toString();
				}
				// 姓名
				String nameString = null;
				if (!TextUtils.isEmpty(nameEditText.getText())) {
					nameString = nameEditText.getText().toString();
				}

				// 搜索条件
				FilterCriteria newFilterCriteria = new FilterCriteria();
				newFilterCriteria.beginTimeDate = beginTimeDate;
				newFilterCriteria.endTimeDate = endTimeDate;
				newFilterCriteria.cardNumberString = cardNumberString;
				newFilterCriteria.nameString = nameString;

				// 清空数据源
				listOfFilter.clear();
				listOfFilter.addAll(searchQuestionnaireListByFilterCriteria(newFilterCriteria));

				// 每次重新筛选完数据后, 要重新设置排序方式.
				defaultSortRadioButton.setChecked(true);
				changeQuestionnaireListViewDataSource(listOfFilter);

				// 记录最新的搜索条件
				lastFilterCriteria = newFilterCriteria;
			}
		});

		this.listViewAdapter = new QuestionnaireListViewAdapter(this, new QuestionnaireListViewAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(int position) {
				showFunctionOptionsForSingleQuestionnaire(position);
			}
		});

		final RadioGroup sortButtonRadioGroup = (RadioGroup) findViewById(R.id.sort_button_radioGroup);
		sortButtonRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.questionnaire_name_radioButton:// 试题名称
					Collections.sort(listOfFilter, testNameComparator);
					break;
				case R.id.name_radioButton:// 姓名
					Collections.sort(listOfFilter, patientNameComparator);
					break;
				case R.id.sex_radioButton:// 性别
					Collections.sort(listOfFilter, patientSexComparator);
					break;
				case R.id.age_radioButton:// 年龄
					Collections.sort(listOfFilter, patientAgeComparator);
					break;
				case R.id.default_print_radioButton:// 默认打印
					Collections.sort(listOfFilter, defaultPrintComparator);
					break;
				case R.id.begin_time_radioButton:// 开始时间
					Collections.sort(listOfFilter, beginTestTimeComparator);
					break;
				case R.id.card_number_radioButton:// 卡号
					Collections.sort(listOfFilter, cardNumberComparator);
					break;
				case R.id.state_radioButton:// 测试题状态
					Collections.sort(listOfFilter, testStateComparator);
					break;
				default:
					return;
				}

				changeQuestionnaireListViewDataSource(listOfFilter);
			}
		});

		// 默认是按 时间 排序的
		defaultSortRadioButton = (RadioButton) findViewById(R.id.begin_time_radioButton);
		defaultSortRadioButton.setChecked(true);

		// 未完成测试 列表
		final ListView listView = (ListView) findViewById(R.id.questionnaire_listView);
		// 表格body部分
		listView.setAdapter(listViewAdapter);
		listViewAdapter.changeDataSource(GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished());

	}

	@Override
	protected void onStart() {
		DebugLog.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		DebugLog.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		DebugLog.i(TAG, "onResume");
		super.onResume();

		// 当再次进入界面时, 要进行刷新操作
		listViewAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();

		// 取消网络请求
		loginNetService.stop();
	}

	@Override
	protected void onStop() {
		DebugLog.i(TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		DebugLog.i(TAG, "onDestroy");

		super.onDestroy();
	}

	// 日期和时间选择对话框，首先是要获得当前时间，这里用
	// java类中的Calendar来获得日期和时间(也可以用Date，但是不提倡，Date部分方法已经注释为过时),
	// Calendar是一个抽象类，是通过getInstance()来获得实例
	private void showDatePickerDialog(final Button timeButton) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		if (!TextUtils.isEmpty(timeButton.getText())) {
			try {
				Date date = simpleDateFormat.parse(timeButton.getText().toString());
				calendar.setTime(date);
			} catch (ParseException e) {
				DebugLog.e(TAG, "转换时间失败(simpleDateFormat.parse)!");
				e.printStackTrace();
				calendar.setTime(new Date());
			}
		}
		new DatePickerDialog(ContinueTestActivity.this, new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// 这里
				timeButton.setText(simpleDateFormat.format(new Date(year - 1900, monthOfYear, dayOfMonth)));
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MARCH), calendar.get(Calendar.DAY_OF_MONTH)).show();
	}

	private boolean isCompliantFilterCriteria(final FullQuestionnaireModel questionnaireModel, final FilterCriteria queryCriteria) {
		do {
			//
			if (queryCriteria.beginTimeDate != null) {
				if (questionnaireModel.getBeginTestDate().before(queryCriteria.beginTimeDate)) {
					break;
				}
			}
			//
			if (queryCriteria.endTimeDate != null) {
				if (questionnaireModel.getBeginTestDate().after(queryCriteria.beginTimeDate)) {
					break;
				}
			}
			//
			if (!TextUtils.isEmpty(queryCriteria.nameString)) {
				if (!queryCriteria.nameString.equals(questionnaireModel.getRespondentsInformation().getName())) {
					break;
				}
			}
			//
			if (!TextUtils.isEmpty(queryCriteria.cardNumberString)) {
				if (!queryCriteria.cardNumberString.equals(questionnaireModel.getRespondentsInformation().getCardNumber())) {
					break;
				}
			}
			return true;
		} while (false);
		return false;
	}

	/**
	 * 筛选条件
	 * 
	 * @author hesiming
	 * 
	 */
	private final class FilterCriteria {
		private Date beginTimeDate = null;
		private Date endTimeDate = null;
		private String cardNumberString = null;
		private String nameString = null;

		private boolean isNotSetQueryCriteria() {
			do {
				if (beginTimeDate != null) {
					break;
				}
				if (endTimeDate != null) {
					break;
				}
				if (!TextUtils.isEmpty(cardNumberString)) {
					break;
				}
				if (!TextUtils.isEmpty(nameString)) {
					break;
				}
				return true;
			} while (false);

			return false;
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 排序算法实现

	// 试题名称
	private Comparator<FilterQuestionnaireListItem> testNameComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			Collator collator = Collator.getInstance(java.util.Locale.ENGLISH);
			return collator.compare(lhs.questionnaireModel.getQuestionnaireCodeEnum().getShortName(), rhs.questionnaireModel.getQuestionnaireCodeEnum().getShortName());
		}
	};

	// 患者姓名
	private Comparator<FilterQuestionnaireListItem> patientNameComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			Collator collator = Collator.getInstance(java.util.Locale.CHINA);
			return collator.compare(lhs.questionnaireModel.getRespondentsInformation().getName(), rhs.questionnaireModel.getRespondentsInformation().getName());
		}
	};

	// 患者性别
	private Comparator<FilterQuestionnaireListItem> patientSexComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			return rhs.questionnaireModel.getRespondentsInformation().getSexEnum().compareTo(lhs.questionnaireModel.getRespondentsInformation().getSexEnum());
		}
	};

	// 患者年龄
	private Comparator<FilterQuestionnaireListItem> patientAgeComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			Integer lhsInteger = lhs.questionnaireModel.getRespondentsInformation().getAge();
			return lhsInteger.compareTo(rhs.questionnaireModel.getRespondentsInformation().getAge());
		}
	};

	// 默认打印
	private Comparator<FilterQuestionnaireListItem> defaultPrintComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			return lhs.questionnaireModel.getQuestionnaireCodeEnum().getDefaultPrintType().compareTo(rhs.questionnaireModel.getQuestionnaireCodeEnum().getDefaultPrintType());
		}
	};

	// 开始时间
	private Comparator<FilterQuestionnaireListItem> beginTestTimeComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			return rhs.questionnaireModel.getBeginTestDate().compareTo(lhs.questionnaireModel.getBeginTestDate());
		}
	};

	// 卡号
	private Comparator<FilterQuestionnaireListItem> cardNumberComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			Collator collator = Collator.getInstance(java.util.Locale.ENGLISH);
			return collator.compare(lhs.questionnaireModel.getRespondentsInformation().getCardNumber(), rhs.questionnaireModel.getRespondentsInformation().getCardNumber());
		}
	};

	// 状态
	private Comparator<FilterQuestionnaireListItem> testStateComparator = new Comparator<FilterQuestionnaireListItem>() {

		@Override
		public int compare(FilterQuestionnaireListItem lhs, FilterQuestionnaireListItem rhs) {
			return lhs.questionnaireModel.getQuestionnaireStateEnum().compareTo(rhs.questionnaireModel.getQuestionnaireStateEnum());
		}
	};

	/**
	 * 这里最重要的是 indexOfQuestionnaireListOfUnfinished , 这个变量保存的是当前模型在
	 * GlobalDataCacheForMemorySingleton
	 * .getInstance.getQuestionnaireListOfUnfinished().getQuestionnaireList()
	 * 的真实索引
	 * 
	 * @author hesiming
	 * 
	 */
	private final class FilterQuestionnaireListItem {
		private final int indexOfQuestionnaireListOfUnfinished;
		private final FullQuestionnaireModel questionnaireModel;

		public FilterQuestionnaireListItem(int indexOfQuestionnaireListOfUnfinished, FullQuestionnaireModel questionnaireModel) {
			this.indexOfQuestionnaireListOfUnfinished = indexOfQuestionnaireListOfUnfinished;
			this.questionnaireModel = questionnaireModel;
		}
	}

	private void changeQuestionnaireListViewDataSource(List<FilterQuestionnaireListItem> filterQuestionnaireListItems) {
		List<FullQuestionnaireModel> questionnaireModels = new ArrayList<FullQuestionnaireModel>();
		for (FilterQuestionnaireListItem filterQuestionnaireListItem : filterQuestionnaireListItems) {
			questionnaireModels.add(filterQuestionnaireListItem.questionnaireModel);
		}
		listViewAdapter.changeDataSource(questionnaireModels);
	}

	private void gotoAnswerActivity(int indexOfQuestionnaireListOfUnfinished) {
		Intent intent = new Intent(ContinueTestActivity.this, AnswerActivity.class);
		intent.putExtra(AnswerActivity.IntentExtraTagEnum.QUESTIONAIRE_MODEL_INDEX_FROM_CONTINUE_ACTIVITY.name(), indexOfQuestionnaireListOfUnfinished);
		startActivity(intent);
	}

	/**
	 * 点击单个试题时, 弹出的 "功能菜单"
	 * 
	 * @param indexOfQuestionnaireListOfUnfinished
	 */
	private void showFunctionOptionsForSingleQuestionnaire(final int indexOfCurrentlyListView) {
		// 获取当前试题的真实索引
		final int indexOfQuestionnaireListOfUnfinished = listOfFilter.get(indexOfCurrentlyListView).indexOfQuestionnaireListOfUnfinished;
		final List<FullQuestionnaireModel> questionnaireModels = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		if (indexOfQuestionnaireListOfUnfinished < 0 || indexOfQuestionnaireListOfUnfinished >= questionnaireModels.size()) {
			// 入参无效
			return;
		}

		final FullQuestionnaireModel questionnaireModel = questionnaireModels.get(indexOfQuestionnaireListOfUnfinished);
		int indexOfOption = 0;
		final Map<Integer, FunctionOptionsEnum> functionOptionsMap = new LinkedHashMap<Integer, GlobalConstant.FunctionOptionsEnum>();
		// 返回
		functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.back);
		// 删除
		functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.delete);

		if (questionnaireModel.getQuestionnaireStateEnum() == QuestionnaireStateEnum.COMPLETED) {// 已完成的试题
			// 上传
			functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.upload);
			// 预览
			functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.preview);
			// 普通打印
			if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.NORMAL_PRINT)) {
				functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.normal_print);
			}

			// 详细打印
			if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.DETAIL_PRINT)) {
				functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.detail_print);
			}

			// 全面打印
			if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.FULL_PRINT)) {
				functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.full_print);
			}
		} else {// 未完成的试题
			functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.continue_test);
		}

		// 构建 简单列表提示框 的数据源
		final String[] optionStrings = new String[functionOptionsMap.size()];
		final Collection<FunctionOptionsEnum> optionsEnums = functionOptionsMap.values();
		final Iterator<FunctionOptionsEnum> iterator = optionsEnums.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			FunctionOptionsEnum optionEnum = (FunctionOptionsEnum) iterator.next();
			optionStrings[i++] = optionEnum.getName();
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(" 操 作 : " + questionnaireModel.getRespondentsInformation().getName());
		builder.setItems(optionStrings, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				FunctionOptionsEnum optionEnum = functionOptionsMap.get(Integer.valueOf(which));
				switch (optionEnum) {
				case back:
					break;
				case delete:
					showDeleteDialog(DeleteTypeEnum.DELETE_ONE, indexOfQuestionnaireListOfUnfinished);
					break;
				case continue_test:
					gotoAnswerActivity(indexOfQuestionnaireListOfUnfinished);
					break;
				case upload:
				case normal_print:
				case detail_print:
				case full_print:
					final ArrayList<Integer> questionnaireIndexListOfUnfinishedList = new ArrayList<Integer>();
					questionnaireIndexListOfUnfinishedList.add(indexOfCurrentlyListView);
					if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {
						gotoPrintActivity(optionEnum, questionnaireIndexListOfUnfinishedList);
					} else {
						showLoginDialog(optionEnum, questionnaireIndexListOfUnfinishedList);
					}
					break;
				case preview:
					if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {
						gotoPreviewActivity(indexOfQuestionnaireListOfUnfinished);
					} else {
						showLoginDialog(optionEnum, indexOfQuestionnaireListOfUnfinished);
					}
					break;
				default:
					break;
				}
			}

		});
		builder.show();
	}

	/**
	 * 点击 "批量处理" 按钮后弹出的 "功能菜单"
	 * 
	 * @param checkedIndexArrayOfCurrentlyListView
	 */
	private void showFunctionOptionsForMultipleQuestionnaire(final List<Integer> checkedIndexArrayOfCurrentlyListView) {
		if (checkedIndexArrayOfCurrentlyListView == null || checkedIndexArrayOfCurrentlyListView.size() <= 0) {
			// 入参无效
			return;
		}

		// 要进行批量处理的试题集合
		final List<FullQuestionnaireModel> questionnaireModelsOfBatch = new ArrayList<FullQuestionnaireModel>();
		// 状态是 "已完成" 的量表集合
		final List<FullQuestionnaireModel> completedQuestionnaireModels = new ArrayList<FullQuestionnaireModel>();
		for (Integer index : checkedIndexArrayOfCurrentlyListView) {
			FullQuestionnaireModel questionnaireModel = listOfFilter.get(index).questionnaireModel;
			questionnaireModelsOfBatch.add(questionnaireModel);
			if (questionnaireModel.getQuestionnaireStateEnum() == QuestionnaireStateEnum.COMPLETED) {
				completedQuestionnaireModels.add(questionnaireModel);
			}
		}

		int indexOfOption = 0;
		final Map<Integer, FunctionOptionsEnum> functionOptionsMap = new LinkedHashMap<Integer, GlobalConstant.FunctionOptionsEnum>();
		functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.back);
		functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.delete_all);
		if (completedQuestionnaireModels.size() > 0) {
			// 只有状态是 "已经完成的试题" 才可以进行上传和默认打印操作
			functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.upload);
			functionOptionsMap.put(Integer.valueOf(indexOfOption++), FunctionOptionsEnum.default_print);
		}

		// 构建 简单列表提示框 的数据源
		final String[] optionStrings = new String[functionOptionsMap.size()];
		final Collection<FunctionOptionsEnum> optionsEnums = functionOptionsMap.values();
		final Iterator<FunctionOptionsEnum> iterator = optionsEnums.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			FunctionOptionsEnum optionEnum = (FunctionOptionsEnum) iterator.next();
			optionStrings[i++] = optionEnum.getName();
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(" 批量操作 ");
		builder.setItems(optionStrings, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				FunctionOptionsEnum optionEnum = functionOptionsMap.get(Integer.valueOf(which));
				switch (optionEnum) {
				case back:
					break;
				case delete_all:
					showDeleteDialog(DeleteTypeEnum.DELETE_ALL, questionnaireModelsOfBatch);
					break;
				case upload:
				case default_print:
					if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {
						gotoPrintActivity(optionEnum, (ArrayList<Integer>) checkedIndexArrayOfCurrentlyListView);
					} else {
						showLoginDialog(optionEnum, checkedIndexArrayOfCurrentlyListView);
					}
					break;
				default:
					break;
				}
			}

		});
		builder.show();
	}

	/**
	 * 使用 "最后的搜索条件" 来刷新 ListView
	 */
	private void refreshListViewByLastFilterCriteria() {
		//
		
		// 然后重新构建数据源
		listOfFilter.clear();
		listOfFilter.addAll(searchQuestionnaireListByFilterCriteria(lastFilterCriteria));
		// 每次重新筛选完数据后, 要重新设置排序方式.
		defaultSortRadioButton.setChecked(true);
		changeQuestionnaireListViewDataSource(listOfFilter);
	}

	/**
	 * 根据 搜索条件 进行试题筛选
	 * 
	 * @param filterCriteria
	 * @return
	 */
	private List<FilterQuestionnaireListItem> searchQuestionnaireListByFilterCriteria(FilterCriteria filterCriteria) {
		List<FilterQuestionnaireListItem> listOfFilter = new ArrayList<ContinueTestActivity.FilterQuestionnaireListItem>();
		final List<FullQuestionnaireModel> listOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		if (filterCriteria.isNotSetQueryCriteria()) {// 没有设置 搜索条件
			for (int i = 0; i < listOfUnfinished.size(); i++) {
				FilterQuestionnaireListItem filterQuestionnaireListItem = new FilterQuestionnaireListItem(i, listOfUnfinished.get(i));
				listOfFilter.add(filterQuestionnaireListItem);
			}

		} else {// 设置了 搜索条件

			for (int i = 0; i < listOfUnfinished.size(); i++) {
				FullQuestionnaireModel questionnaireModel = listOfUnfinished.get(i);
				if (isCompliantFilterCriteria(questionnaireModel, filterCriteria)) {
					FilterQuestionnaireListItem filterQuestionnaireListItem = new FilterQuestionnaireListItem(i, questionnaireModel);
					listOfFilter.add(filterQuestionnaireListItem);
				}
			}
		}

		return listOfFilter;
	}

	private static enum HandlerMsgTypeEnum {
		//
		SHOW_NET_ERROR_MESSAGE,
		//
		USER_LOGIN_SUCCESSFULLY
	};

	private static enum HandlerExtraDataTypeEnum {
		//
		NET_ERROR_MESSAGE
	};

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			//
			SimpleProgressDialog.dismiss(ContinueTestActivity.this);

			if (msg.what == HandlerMsgTypeEnum.SHOW_NET_ERROR_MESSAGE.ordinal()) {
				final String netErrorMessage = msg.getData().getString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name());
				Toast.makeText(ContinueTestActivity.this, netErrorMessage, Toast.LENGTH_SHORT).show();
			} else if (msg.what == HandlerMsgTypeEnum.USER_LOGIN_SUCCESSFULLY.ordinal()) {

				if (dialogOfLogin != null) {
					dialogOfLogin.dismiss();
				}

				if (optionsEnumOfAfterLoggingIn == null || dataSourceForOptionOfAfterLoggingIn == null) {
					assert false : "数据非法 : optionsEnumOfAfterLoggingIn == null || dataSourceForOptionOfAfterLoggingIn == null";
				} else {
					if (optionsEnumOfAfterLoggingIn == FunctionOptionsEnum.preview) {
						// 预览
						if (dataSourceForOptionOfAfterLoggingIn instanceof Integer) {
							final int indexOfQuestionnaireListOfUnfinished = (Integer) dataSourceForOptionOfAfterLoggingIn;
							gotoPreviewActivity(indexOfQuestionnaireListOfUnfinished);
						}
					} else {
						// 打印
						if (dataSourceForOptionOfAfterLoggingIn instanceof List<?>) {
							@SuppressWarnings("unchecked")
							final ArrayList<Integer> questionnaireIndexListOfUnfinishedList = (ArrayList<Integer>) dataSourceForOptionOfAfterLoggingIn;
							gotoPrintActivity(optionsEnumOfAfterLoggingIn, questionnaireIndexListOfUnfinishedList);
						}
					}
				}
			}
		}
	};

	// 登录网络接口
	private INetServiceOptions loginNetService = new LoginNetService();

	private IDomainNetRespondCallback netRespondCallback = new IDomainNetRespondCallback() {

		@Override
		public void domainNetRespondHandleInNonUIThread(Enum<?> event, Object respondData) {
			loginNetService.stop();

			if (event == LoginNetService.NetEventEnum.NET_REQUEST_SUCCESS) {
				final Message msg = new Message();
				msg.what = HandlerMsgTypeEnum.USER_LOGIN_SUCCESSFULLY.ordinal();
				handler.sendMessage(msg);
			} else {
				final Message msg = new Message();
				msg.what = HandlerMsgTypeEnum.SHOW_NET_ERROR_MESSAGE.ordinal();
				msg.getData().putString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name(), loginNetService.getNetErrorMessage());
				handler.sendMessage(msg);
			}
		}
	};

	// guest 账户, 需要先登录, 然后在进行操作.
	private GlobalConstant.FunctionOptionsEnum optionsEnumOfAfterLoggingIn;
	private Object dataSourceForOptionOfAfterLoggingIn;

	private Dialog dialogOfLogin;

	private synchronized void showLoginDialog(final GlobalConstant.FunctionOptionsEnum optionsEnum, final Object dataSourceForOption) {
		if (optionsEnum == null || dataSourceForOption == null) {
			assert false : "入参非法 : optionsEnum == null || dataSourceForOption == null";
			return;
		}

		this.optionsEnumOfAfterLoggingIn = optionsEnum;
		this.dataSourceForOptionOfAfterLoggingIn = dataSourceForOption;

		// 创建window
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login_layout, null);
		dialogOfLogin = new Dialog(this, R.style.AlertDialog);
		dialogOfLogin.show();
		Window window = dialogOfLogin.getWindow();
		window.setContentView(dialogView);

		final EditText usernameEditText = (EditText) dialogView.findViewById(R.id.username_editText);
		final EditText passwordEditText = (EditText) dialogView.findViewById(R.id.password_editText);
		final Button okButton = (Button) dialogView.findViewById(R.id.login_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String errorMessageString = "";
				String username = "";
				String password = "";

				do {
					username = usernameEditText.getText().toString();
					if (TextUtils.isEmpty(username)) {
						errorMessageString = getResources().getString(R.string.no_network_connection);
						break;
					}

					password = passwordEditText.getText().toString();
					if (TextUtils.isEmpty(password)) {
						errorMessageString = getResources().getString(R.string.password_can_not_be_blank);
						break;
					}

					LogonNetRequestBean logonNetRequestBean = new LogonNetRequestBean(username, password);
					loginNetService.setNetRequestBean(logonNetRequestBean);
					if (!loginNetService.start(netRespondCallback)) {
						break;
					}
					SimpleProgressDialog.show(ContinueTestActivity.this, new DialogInterface.OnCancelListener() {

						@Override
						public void onCancel(DialogInterface dialog) {
							optionsEnumOfAfterLoggingIn = null;
							dataSourceForOptionOfAfterLoggingIn = null;
							loginNetService.stop();
						}
					});

					// 一切OK
					return;
				} while (false);

				// 清理数据
				optionsEnumOfAfterLoggingIn = null;
				dataSourceForOptionOfAfterLoggingIn = null;

				// 用户输入的信息错误
				Toast.makeText(ContinueTestActivity.this, errorMessageString, Toast.LENGTH_LONG).show();

			}
		});

		final Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogOfLogin.dismiss();

				// 清理数据
				optionsEnumOfAfterLoggingIn = null;
				dataSourceForOptionOfAfterLoggingIn = null;
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		DebugLog.i(TAG, "onActivityResult");

		// 从其他界面返回时, 一定要刷新下 ListView
		refreshListViewByLastFilterCriteria();
	}

	/**
	 * 去往 打印界面
	 * 
	 * @param optionEnum
	 * @param questionnaireIndexListOfUnfinishedList
	 */
	private void gotoPrintActivity(final FunctionOptionsEnum optionEnum, final ArrayList<Integer> questionnaireIndexListOfUnfinishedList) {
		Intent intent = new Intent(ContinueTestActivity.this, PrintReportActivity.class);
		intent.putIntegerArrayListExtra(PrintReportActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_LIST_IN_UNFINISHED_LIST.name(), questionnaireIndexListOfUnfinishedList);
		intent.putExtra(PrintReportActivity.IntentExtraTagEnum.OPERATION_COMMAND.name(), optionEnum);
		startActivityForResult(intent, IntentRequestCodeEnum.TO_PRINT_ACTIVITY.ordinal());
	}

	/**
	 * 去往 预览界面
	 * 
	 * @param optionsEnum
	 * @param indexOfQuestionnaireListOfUnfinished
	 */
	private void gotoPreviewActivity(final int indexOfQuestionnaireListOfUnfinished) {
		Intent intent = new Intent(ContinueTestActivity.this, PreviewReportActivity.class);
		intent.putExtra(PreviewReportActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), indexOfQuestionnaireListOfUnfinished);
		startActivityForResult(intent, IntentRequestCodeEnum.TO_PREVIEW_ACTIVITY.ordinal());
	}

	private static enum DeleteTypeEnum {
		// 删除1个
		DELETE_ONE,
		// 删除全部
		DELETE_ALL
	};

	private synchronized void showDeleteDialog(final DeleteTypeEnum deleteTypeEnum, final Object dataSourceForDelete) {
		if (deleteTypeEnum == null || dataSourceForDelete == null) {
			assert false : "入参非法 : deleteTypeEnum == null || dataSourceForDelete == null";
			return;
		}

		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_end_test_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final TextView titleTextView = (TextView) dialogView.findViewById(R.id.title_textView);
		titleTextView.setText("会芯心理测试系统提示您:");
		final TextView infoTextView = (TextView) dialogView.findViewById(R.id.info_textView);
		infoTextView.setText("您确认要删除吗?删除后将无法恢复!");

		// 确定 按钮
		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				dialog.dismiss();

				switch (deleteTypeEnum) {
				case DELETE_ONE:// 删除1个
					// 先删除目标试题
					if (dataSourceForDelete instanceof Integer) {
						final int indexOfQuestionnaireListOfUnfinished = (Integer) dataSourceForDelete;
						ToolsFunctionForThisProgect.deleteQuestionnaireFormUnfinishedListAndDeleteFile(indexOfQuestionnaireListOfUnfinished);
						refreshListViewByLastFilterCriteria();
					}
					break;
				case DELETE_ALL:// 删除全部
					if (dataSourceForDelete instanceof List<?>) {
						List<FullQuestionnaireModel> questionnaireModelsOfBatch = (List<FullQuestionnaireModel>) dataSourceForDelete;
						ToolsFunctionForThisProgect.deleteQuestionnairesFromUnfinishedListAndDeleteFiles(questionnaireModelsOfBatch);
						refreshListViewByLastFilterCriteria();
					}
					break;
				default:
					break;
				}
			}
		});

		// 取消 按钮
		final Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
}
