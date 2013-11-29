package cn.skyduck.activity.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.domain_protocol.print_report.PrintReportNetRequestBean;
import cn.skyduck.net_service.domain_protocol.print_report.PrintReportNetService;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 打印报告界面
 * 
 * @author skyduck
 * 
 */
@SuppressLint("HandlerLeak")
public class PrintReportActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	private List<FullQuestionnaireModel> questionnaireModels = new ArrayList<FullQuestionnaireModel>();
	private GlobalConstant.FunctionOptionsEnum optionsEnum;

	public static enum IntentExtraTagEnum {
		// 目标量表集合在 "未完成" 集合中的索引集合
		QUESTIONAIRE_INDEX_LIST_IN_UNFINISHED_LIST,
		// 要对此量表进行的操作类型
		OPERATION_COMMAND;
	};

	private static enum HandlerExtraDataTypeEnum {
		//
		NET_ERROR_MESSAGE,
		// 已打印完成的量表对象
		QUESTIONNAIRE_MODEL_OBJECT
	};

	// 已完成 数量
	private int printCompletedInt = 0;
	// 未完成 数量
	private int printUnfinishedInt = 0;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == PrintReportNetService.NetEventEnum.NET_CONNECT_SUCCESS.ordinal()) {
				net_state_textView.setText("跟服务器建立连接成功.");
			} else if (msg.what == PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_BEGIN.ordinal()) {
				// 开始打印一个新量表
				@SuppressWarnings("unused")
				final FullQuestionnaireModel questionnaireModelOfPrintCompleted = (FullQuestionnaireModel) msg.getData().getSerializable(HandlerExtraDataTypeEnum.QUESTIONNAIRE_MODEL_OBJECT.name());

			} else if (msg.what == PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_END.ordinal()) {
				// 删除一个已经完成打印的量表
				final FullQuestionnaireModel questionnaireModelOfPrintCompleted = (FullQuestionnaireModel) msg.getData().getSerializable(HandlerExtraDataTypeEnum.QUESTIONNAIRE_MODEL_OBJECT.name());
				ToolsFunctionForThisProgect.deleteQuestionnaireFormUnfinishedListAndDeleteFile(questionnaireModelOfPrintCompleted);

				// 已完成 数量
				print_completed_textView.setText("已完成 : " + (++printCompletedInt));
				// 未完成 数量
				print_unfinished_textView.setText("未完成" + (--printUnfinishedInt));

			} else if (msg.what == PrintReportNetService.NetEventEnum.PRINT_ALL_QUESTIONNAIRE_COMPLETED.ordinal()) {
				// 所有量表打印完成, 返回
				setResult(RESULT_OK);
				finish();
			} else if (msg.what == PrintReportNetService.NetEventEnum.NET_ERROR.ordinal()) {
				final String netErrorMessage = msg.getData().getString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name());
				Toast.makeText(PrintReportActivity.this, netErrorMessage, Toast.LENGTH_SHORT).show();
			}
		}
	};

	// 打印问卷的网络服务接口
	private INetServiceOptions printReportNetService = new PrintReportNetService();

	private IDomainNetRespondCallback netRespondCallback = new IDomainNetRespondCallback() {

		@Override
		public void domainNetRespondHandleInNonUIThread(Enum<?> event, Object respondData) {
			if (event == PrintReportNetService.NetEventEnum.NET_CONNECT_SUCCESS) {
				final Message msg = new Message();
				msg.what = PrintReportNetService.NetEventEnum.NET_CONNECT_SUCCESS.ordinal();
				handler.sendMessage(msg);

			} else if (event == PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_BEGIN) {
				final Message msg = new Message();
				msg.what = PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_BEGIN.ordinal();
				msg.getData().putSerializable(HandlerExtraDataTypeEnum.QUESTIONNAIRE_MODEL_OBJECT.name(), (Serializable) respondData);
				handler.sendMessage(msg);

			} else if (event == PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_END) {
				final Message msg = new Message();
				msg.what = PrintReportNetService.NetEventEnum.PRINT_ONE_QUESTIONNAIRE_END.ordinal();
				msg.getData().putSerializable(HandlerExtraDataTypeEnum.QUESTIONNAIRE_MODEL_OBJECT.name(), (Serializable) respondData);
				handler.sendMessage(msg);

			} else if (event == PrintReportNetService.NetEventEnum.PRINT_ALL_QUESTIONNAIRE_COMPLETED) {
				final Message msg = new Message();
				msg.what = PrintReportNetService.NetEventEnum.PRINT_ALL_QUESTIONNAIRE_COMPLETED.ordinal();
				handler.sendMessage(msg);

			} else if (event == PrintReportNetService.NetEventEnum.NET_ERROR) {
				final Message msg = new Message();
				msg.what = PrintReportNetService.NetEventEnum.NET_ERROR.ordinal();
				msg.getData().putString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name(), printReportNetService.getNetErrorMessage());
				handler.sendMessage(msg);
			}

		}
	};
	// 服务器连接状态
	private TextView net_state_textView;
	// 当前正在打印的量表名称
	private TextView current_printing_name_textView;
	// 打印类型
	private TextView current_printing_mode_textView;
	// 需要打印的量表总数
	private TextView print_total_textView;
	// 已打印完成的量表数量
	private TextView print_completed_textView;
	// 还未完成打印的量表数量
	private TextView print_unfinished_textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_print_report_layout);

		final ArrayList<Integer> questionnaireIndexListOfUnfinishedList = getIntent().getIntegerArrayListExtra(IntentExtraTagEnum.QUESTIONAIRE_INDEX_LIST_IN_UNFINISHED_LIST.name());
		final List<FullQuestionnaireModel> questionnaireListOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		for (Integer integer : questionnaireIndexListOfUnfinishedList) {
			questionnaireModels.add(questionnaireListOfUnfinished.get(integer));
		}
		this.optionsEnum = (GlobalConstant.FunctionOptionsEnum) getIntent().getSerializableExtra(IntentExtraTagEnum.OPERATION_COMMAND.name());

		net_state_textView = (TextView) findViewById(R.id.net_state_textView);
		current_printing_name_textView = (TextView) findViewById(R.id.current_printing_name_textView);
		current_printing_name_textView.setText("当前正在打印的量表名称 : " + questionnaireModels.get(0).getQuestionnaireCodeEnum().getShortName());
		current_printing_mode_textView = (TextView) findViewById(R.id.current_printing_mode_textView);
		switch (this.optionsEnum) {
		case normal_print:
			current_printing_mode_textView.setText("打印类型 : 普通打印");
			break;
		case detail_print:
			current_printing_mode_textView.setText("打印类型 : 详细打印");
			break;
		case full_print:
			current_printing_mode_textView.setText("打印类型 : 全面打印");
			break;
		case upload:
			current_printing_mode_textView.setText("打印类型 : 只上传不打印");
			break;
		case default_print:
			current_printing_mode_textView.setText("打印类型 : 默认打印");
			break;
		default:
			break;
		}

		print_total_textView = (TextView) findViewById(R.id.print_total_textView);
		print_total_textView.setText("本次需要打印的量表总数 : " + questionnaireModels.size());
		print_completed_textView = (TextView) findViewById(R.id.print_completed_textView);
		print_completed_textView.setText("已完成 : 0");
		print_unfinished_textView = (TextView) findViewById(R.id.print_unfinished_textView);
		printUnfinishedInt = questionnaireModels.size();
		print_unfinished_textView.setText("未完成 : " + printUnfinishedInt);

		// 开始打印报告
		requestPrintReport();
	}

	@Override
	protected void onDestroy() {
		DebugLog.i(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();

		printReportNetService.stop();
	}

	@Override
	protected void onResume() {
		DebugLog.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		DebugLog.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		DebugLog.i(TAG, "onStop");
		super.onStop();
	}

	private void requestPrintReport() {
		final PrintReportNetRequestBean printReportNetRequestBean = new PrintReportNetRequestBean(questionnaireModels, optionsEnum);
		printReportNetService.setNetRequestBean(printReportNetRequestBean);
		printReportNetService.start(netRespondCallback);
	}
}
