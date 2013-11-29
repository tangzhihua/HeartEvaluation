package cn.skyduck.activity.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.PrintTypeEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.domain_protocol.preview.PreviewNetRequestBean;
import cn.skyduck.net_service.domain_protocol.preview.PreviewNetService;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.SimpleProgressDialog;

@SuppressLint("HandlerLeak")
public class PreviewReportActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	private int questionnaireIndexInUnfinishedList;
	private FullQuestionnaireModel questionnaireModel;
	private GlobalConstant.FunctionOptionsEnum currentlyOptionEnum = GlobalConstant.FunctionOptionsEnum.normal_preview;
	private Map<String, Bitmap> imageCacheMap = new HashMap<String, Bitmap>(3);

	public static enum IntentExtraTagEnum {
		// 目标量表在 "未完成" 集合中的索引
		QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST
	};

	// 信息文本框
	private TextView infoTextView;
	// 报告图片
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_report_layout);

		//
		final int questionnaireIndexInUnfinishedList = getIntent().getIntExtra(IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), -1);
		if (questionnaireIndexInUnfinishedList != -1) {
			final List<FullQuestionnaireModel> questionnaireListOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
			questionnaireModel = questionnaireListOfUnfinished.get(questionnaireIndexInUnfinishedList);
		}

		//
		infoTextView = (TextView) findViewById(R.id.info_textView);
		//
		imageView = (ImageView) findViewById(R.id.image_imageView);
		//
		final RadioGroup functionRadioGroup = (RadioGroup) findViewById(R.id.preview_button_radioGroup);
		functionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.nomal_report_radioButton:
					switchReport(GlobalConstant.FunctionOptionsEnum.normal_preview);
					break;
				case R.id.detailed_report_radioButton:
					switchReport(GlobalConstant.FunctionOptionsEnum.detail_preview);
					break;
				case R.id.full_report_radioButton:
					switchReport(GlobalConstant.FunctionOptionsEnum.full_preview);
					break;
				default:
					break;
				}
			}
		});

		final RadioButton nomalReportRadioButton = (RadioButton) findViewById(R.id.nomal_report_radioButton);
		final RadioButton detailedReportRadioButton = (RadioButton) findViewById(R.id.detailed_report_radioButton);
		final RadioButton fullReportRadioButton = (RadioButton) findViewById(R.id.full_report_radioButton);
		// 普通打印
		if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.NORMAL_PRINT)) {
			nomalReportRadioButton.setEnabled(true);
		} else {
			nomalReportRadioButton.setEnabled(false);
		}
		// 详细打印
		if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.DETAIL_PRINT)) {
			detailedReportRadioButton.setEnabled(true);
		} else {
			detailedReportRadioButton.setEnabled(false);
		}
		// 全面打印
		if (questionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.FULL_PRINT)) {
			fullReportRadioButton.setEnabled(true);
		} else {
			fullReportRadioButton.setEnabled(false);
		}

		switch (questionnaireModel.getQuestionnaireCodeEnum().getDefaultPrintType()) {
		case NORMAL_PRINT:
			nomalReportRadioButton.setChecked(true);
			break;
		case DETAIL_PRINT:
			detailedReportRadioButton.setChecked(true);
			break;
		case FULL_PRINT:
			fullReportRadioButton.setChecked(true);
			break;
		default:
			break;
		}

		final Button printButton = (Button) findViewById(R.id.print_button);
		printButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				gotoPrintActivity();
				finish();
			}
		});

		final Button quitButton = (Button) findViewById(R.id.quit_button);
		quitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

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
		SimpleProgressDialog.resetByThisContext(this);
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

	private static enum HandlerExtraDataTypeEnum {
		//
		NET_ERROR_MESSAGE,
		// 已下载完的 量表报告图片
		QUESTIONNAIRE_REPORT_IMAGE
	};

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == PreviewNetService.NetEventEnum.NET_CONNECT_SUCCESS.ordinal()) {
				infoTextView.setText("网络连接成功");
			} else if (msg.what == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_BEGIN.ordinal()) {

			} else if (msg.what == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_PROGRESS.ordinal()) {

			} else if (msg.what == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_END.ordinal()) {
				SimpleProgressDialog.dismiss(PreviewReportActivity.this);

				Bitmap image = imageCacheMap.get(currentlyOptionEnum.name());
				imageView.setImageBitmap(image);
			} else if (msg.what == PreviewNetService.NetEventEnum.NET_ERROR.ordinal()) {
				final String netErrorMessage = msg.getData().getString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name());
				Toast.makeText(PreviewReportActivity.this, netErrorMessage, Toast.LENGTH_SHORT).show();
			}
		}
	};

	// 打印问卷的网络服务接口
	private INetServiceOptions printReportNetService = new PreviewNetService();

	private IDomainNetRespondCallback netRespondCallback = new IDomainNetRespondCallback() {

		@Override
		public void domainNetRespondHandleInNonUIThread(Enum<?> event, Object respondData) {
			if (event == PreviewNetService.NetEventEnum.NET_CONNECT_SUCCESS) {
				final Message msg = new Message();
				msg.what = PreviewNetService.NetEventEnum.NET_CONNECT_SUCCESS.ordinal();
				handler.sendMessage(msg);

			} else if (event == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_BEGIN) {
				final Message msg = new Message();
				msg.what = PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_BEGIN.ordinal();
				handler.sendMessage(msg);

			} else if (event == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_PROGRESS) {
				final Message msg = new Message();
				msg.what = PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_PROGRESS.ordinal();

				handler.sendMessage(msg);

			} else if (event == PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_END) {
				imageCacheMap.put(currentlyOptionEnum.name(), (Bitmap) respondData);

				final Message msg = new Message();
				msg.what = PreviewNetService.NetEventEnum.DOWNLOAD_IMAGE_END.ordinal();
				handler.sendMessage(msg);

			} else if (event == PreviewNetService.NetEventEnum.NET_ERROR) {
				SimpleProgressDialog.dismiss(PreviewReportActivity.this);

				final Message msg = new Message();
				msg.what = PreviewNetService.NetEventEnum.NET_ERROR.ordinal();
				msg.getData().putString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name(), printReportNetService.getNetErrorMessage());
				handler.sendMessage(msg);
			}

		}
	};

	private GlobalConstant.FunctionOptionsEnum conversionPreviewTypeToPrintType(GlobalConstant.FunctionOptionsEnum previewType) {
		GlobalConstant.FunctionOptionsEnum printType = null;

		switch (previewType) {
		case normal_preview:
			printType = GlobalConstant.FunctionOptionsEnum.normal_print;
			break;
		case detail_preview:
			printType = GlobalConstant.FunctionOptionsEnum.detail_print;
			break;
		case full_preview:
			printType = GlobalConstant.FunctionOptionsEnum.full_print;
			break;
		default:
			break;
		}

		return printType;
	}

	/**
	 * 去往 打印界面
	 */
	private void gotoPrintActivity() {
		GlobalConstant.FunctionOptionsEnum printType = conversionPreviewTypeToPrintType(currentlyOptionEnum);
		if (printType == null) {
			Toast.makeText(this, "操作类型无效!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		final ArrayList<Integer> questionnaireIndexListOfUnfinishedList = new ArrayList<Integer>();
		questionnaireIndexListOfUnfinishedList.add(questionnaireIndexInUnfinishedList);
		Intent intent = new Intent(this, PrintReportActivity.class);
		intent.putIntegerArrayListExtra(PrintReportActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_LIST_IN_UNFINISHED_LIST.name(), questionnaireIndexListOfUnfinishedList);
		intent.putExtra(PrintReportActivity.IntentExtraTagEnum.OPERATION_COMMAND.name(), printType);
		startActivity(intent);
	}

	private void switchReport(final GlobalConstant.FunctionOptionsEnum optionEnum) {
		SimpleProgressDialog.dismiss(this);
		printReportNetService.stop();

		currentlyOptionEnum = optionEnum;
		Bitmap image = imageCacheMap.get(optionEnum.name());
		if (image == null) {
			imageView.setImageBitmap(null);

			PreviewNetRequestBean previewNetRequestBean = new PreviewNetRequestBean(questionnaireModel, optionEnum);
			printReportNetService.setNetRequestBean(previewNetRequestBean);
			if (printReportNetService.start(netRespondCallback)) {
				SimpleProgressDialog.show(PreviewReportActivity.this, new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {
						printReportNetService.stop();
					}
				});
			}
		} else {
			imageView.setImageBitmap(image);
		}
	}

}
