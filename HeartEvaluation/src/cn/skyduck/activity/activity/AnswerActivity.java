package cn.skyduck.activity.activity;

import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireStateEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.questionnaire.CreateQuestionnaireModelHelper;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 问卷答题界面
 * 
 * @author hesiming
 * 
 */
public class AnswerActivity extends FragmentActivity implements IQuestionnaireFragmentCallback {

	private final String TAG = this.getClass().getSimpleName();

	public static enum IntentExtraTagEnum {
		// 要进行测试的问卷类型(如果入参是这个, 证明是要开启一个新的测试)
		QUESTIONAIRE_CODE,
		// 如果是从 "继续未完成" 进入答题界面, 就传递目标问卷在 questionnaireListOfUnfinished
		// 中的索引(证明当前是一个旧的测试)
		QUESTIONAIRE_MODEL_INDEX_FROM_CONTINUE_ACTIVITY
	};

	private QuestionnaireCodeEnum questionnaireCodeEnum = null;

	private FullQuestionnaireModel fullQuestionnaireModel = null;

	// 判断当前测试是一个全新的还是从 继续为完成 界面跳转过来的一个未完成的测试
	private boolean isNewTest = true;
	// 当前测试在未完成列表中的索引
	private int indexOfQuestionnaireListOfUnfinished;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_layout);

		final List<FullQuestionnaireModel> unfinishedList = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		this.questionnaireCodeEnum = (QuestionnaireCodeEnum) getIntent().getSerializableExtra(IntentExtraTagEnum.QUESTIONAIRE_CODE.name());
		if (this.questionnaireCodeEnum != null) {
			// 新开始一个测试
			this.isNewTest = true;
			try {
				fullQuestionnaireModel = CreateQuestionnaireModelHelper.getQuestionnaireModelByQuestionnaireCodeEnum(this.questionnaireCodeEnum);
				// 将当前测试加入"未完成"列表中(默认是按时间倒排, 新的排在前面)
				unfinishedList.add(0, fullQuestionnaireModel);

				// TODO : 继续未完成 中缓存的试题数量目前暂定100, 如果超出就删除最后一条记录(记录是按时间倒叙排列的,
				// 也就是删除最老的一跳记录)
				if (unfinishedList.size() > 100) {
					unfinishedList.remove(unfinishedList.size() - 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			// 从继续未完成界面跳过来的一个未完成测试
			this.isNewTest = false;
			this.indexOfQuestionnaireListOfUnfinished = getIntent().getIntExtra(IntentExtraTagEnum.QUESTIONAIRE_MODEL_INDEX_FROM_CONTINUE_ACTIVITY.name(), -1);
			if (indexOfQuestionnaireListOfUnfinished >= 0 && indexOfQuestionnaireListOfUnfinished < unfinishedList.size()) {
				fullQuestionnaireModel = unfinishedList.get(indexOfQuestionnaireListOfUnfinished);
				// 一定要倒退下索引, 因为下面会调用 onNextPage()
				fullQuestionnaireModel.setCurrentFrameIndex(fullQuestionnaireModel.getCurrentFrameIndex() - 1);
			}
		}

		if (fullQuestionnaireModel == null) {
			assert false : "出现错误!";
			// 出现错误后, 立刻返回上一界面
			finish();
			return;
		} else {

			// 记录测试开始时间
			if (isNewTest) {
				fullQuestionnaireModel.setBeginTestDate(new Date());
			}
			onNextPage();
		}

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
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		DebugLog.i(TAG, "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onEndTest();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onPreviousPage() {
		hideSoftKeyboard();

		// 保存量表到文件系统中
		ToolsFunctionForThisProgect.saveQuestionnaireToFileSystem(fullQuestionnaireModel);

		// 跳转到上一个问卷界面
		int previousFrameIndex = fullQuestionnaireModel.getCurrentFrameIndex() - 1;
		if (previousFrameIndex < 0) {
			// 已经到第一帧, 不能在回退了
			previousFrameIndex = 0;

			showEndTestDialog();
		} else {
			changeFragment(previousFrameIndex);
		}
	}

	/**
	 * 正在显示 完成测试对话框, 这个是为了防止用户快速点击答案选项时, 到最后一题会弹出两个对话框
	 */
	private boolean isShowingFinishTestDialog = false;

	@Override
	public void onNextPage() {
		hideSoftKeyboard();

		if (isShowingFinishTestDialog) {
			return;
		}

		// 保存量表到文件系统中
		ToolsFunctionForThisProgect.saveQuestionnaireToFileSystem(fullQuestionnaireModel);

		// 跳转到下一个问卷界面
		int nextFrameIndex = fullQuestionnaireModel.getCurrentFrameIndex() + 1;
		if (nextFrameIndex >= fullQuestionnaireModel.getFramePageTotal()) {
			// 已经到最后一帧, 提示用户答题结束, 首先要判断本次答题是否有效, 如果无效就提示用户继续答题
			if (fullQuestionnaireModel.isTestEffectively()) {

				// 更新量表状态为 "已完成"
				fullQuestionnaireModel.setQuestionnaireStateEnum(QuestionnaireStateEnum.COMPLETED);

				// 本次答题有效
				showFinishTestDialog();
			} else {
				// TODO : 本次答题无效, 先不做任何提示操作, 就是不触发测试成功提示框.
			}
		} else {
			if (changeFragment(nextFrameIndex)) {
				// 更新量表状态为 "正在进行中"
				if (nextFrameIndex >= 2) {
					// 我们固定设计量表 第一页是患者信息录入界面 第二页是指导语界面 第三页开始就是问卷答题界面了
					fullQuestionnaireModel.setQuestionnaireStateEnum(QuestionnaireStateEnum.TESTING);
				}
			}
		}
	}

	@Override
	public void onEndTest() {
		// 用户按下了 "结束测试" 按钮, 想要提前结束测试
		if (fullQuestionnaireModel.isCanEndTestInAdvance() && fullQuestionnaireModel.getQuestionnaireStateEnum() != QuestionnaireStateEnum.NOT_STARTED) {
			// 但是对于有的测试, 用户只需要回答一部分问题就可以了, 所以这里会判断用户是否可以提前交卷(这只对应特殊量表, 如MMPI)
			// 更新量表状态为 "已完成"
			fullQuestionnaireModel.setQuestionnaireStateEnum(QuestionnaireStateEnum.COMPLETED_OF_END_TEST_IN_ADVANCE);
			showEarlyTerminationOfTheTestDialog();
		} else {
			// 显示 "用户退出测试" 的提示框
			showEndTestDialog();
		}

	}

	@Override
	public void onAnswerQuestion(Object userAnswer) {
		// 先保存用户的答案
		BaseFramePageModel frameModel = fullQuestionnaireModel.getFrameModel(fullQuestionnaireModel.getCurrentFrameIndex());
		if (frameModel != null) {
			frameModel.setAnswerDataSource(userAnswer);
		}

		onNextPage();
	}

	/**
	 * 切换问题界面碎片
	 * 
	 * @param newFrameIndex
	 */
	private boolean changeFragment(final int newFrameIndex) {

		do {

			BaseFramePageModel frameModel = fullQuestionnaireModel.getFrameModel(newFrameIndex);
			if (frameModel == null) {
				assert false : "入参 newFrameIndex 无效.";
				break;
			}

			// 保存最新的碎片索引
			fullQuestionnaireModel.setCurrentFrameIndex(newFrameIndex);

			// 创建新的碎片界面
			Fragment newFragment = frameModel.createQuestionnaireFramePageFragment(fullQuestionnaireModel);

			if (newFragment == null) {
				assert false : "创建问卷碎片失败!";
				fullQuestionnaireModel.setCurrentFrameIndex(newFrameIndex - 1);
				break;
			}

			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.fragment_container_layout, newFragment);
			// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();

			// 固化问卷到文件系统中, 只有发生界面切换时, 才需要固话保存
			saveQuestionnaireToFileSystem();

			return true;
		} while (false);

		return false;
	}

	/**
	 * 固化问卷到文件系统中
	 */
	private void saveQuestionnaireToFileSystem() {
		// 记录考试结束时间
		fullQuestionnaireModel.setEndTestdDate(new Date());
	}

	/**
	 * 显示用户答题完成对话框(此时用户答题完成, 可以跳转 "发给医生界面")
	 */
	private synchronized void showFinishTestDialog() {
		isShowingFinishTestDialog = true;

		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_end_test_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final TextView titleTextView = (TextView) dialogView.findViewById(R.id.title_textView);
		titleTextView.setText(getResources().getString(R.string.finish_test));
		final TextView infoTextView = (TextView) dialogView.findViewById(R.id.info_textView);

		List<Integer> unansweredQuestionNumberList = fullQuestionnaireModel.getUnansweredQuestionNumberList();
		if (unansweredQuestionNumberList.size() > 0) {
			// 提示用户 : 你还有以下试题没有完成\n%s\n您确认完成本次测试吗?确认请点确定按钮,否则点取消按钮继续答题.
			String unansweredQuestionInfoString = String.format(getResources().getString(R.string.unanswered_question_info_string), unansweredQuestionNumberList.toString());
			infoTextView.setText(unansweredQuestionInfoString);
		} else {
			// 提示用户 : 您已经完成本问卷所有问题, 点击确定完成, 即可完成本次测试.
			infoTextView.setText(getResources().getString(R.string.info_for_finish_test));
		}

		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isShowingFinishTestDialog = false;
				dialog.dismiss();

				// 进入 "交给医生界面"
				gotoSendToDoctorActivity();
			}
		});

		final Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isShowingFinishTestDialog = false;

				dialog.dismiss();

				// TODO : 这里也可以做成直接跳转到, 用户第一个未完成的问题界面
			}
		});
	}

	/**
	 * 显示 结束测试 提示框(此时用户答题未完成)
	 */
	private void showEndTestDialog() {
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_end_test_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final TextView titleTextView = (TextView) dialogView.findViewById(R.id.title_textView);
		titleTextView.setText(getResources().getString(R.string.end_test));
		final TextView infoTextView = (TextView) dialogView.findViewById(R.id.info_textView);
		if (fullQuestionnaireModel.getQuestionnaireStateEnum() == QuestionnaireStateEnum.NOT_STARTED) {
			// 测试还未开始, 此时退出, 不保存数据
			infoTextView.setText("用户没有保存基本信息, 本次测试无效, 退出后将不会被保存!");
		} else {
			// 测试已经开始
			infoTextView.setText(R.string.info_for_early_termination_of_the_test);
		}

		// 确定 按钮
		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// 保存本次测试结果后, 跳转回 "主界面"
				saveAndExitThisActivity();

				dialog.dismiss();
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

	/**
	 * 显示 提前结束测试 提示框(此时用户答题未完成, 但是只要输入系统管理员密码, 就可以交卷了, 这只对应特殊的量表)
	 */
	private void showEarlyTerminationOfTheTestDialog() {
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_end_test_with_password_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		// 结束测试 按钮
		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 直接跳转到 SendToDoctorActivity
				gotoSendToDoctorActivity();

				dialog.dismiss();
			}
		});

		// 系统管理员密码 输入框
		final EditText passwordEditText = (EditText) dialogView.findViewById(R.id.password_editText);
		passwordEditText.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				String passwordOfUserInput = passwordEditText.getText().toString();
				final String adminPassword = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting().getAdminPassword();
				final boolean isPasswordRight = adminPassword.equals(passwordOfUserInput);

				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						if (isPasswordRight) {
							okButton.setEnabled(true);
						} else {
							okButton.setEnabled(false);
							Toast.makeText(AnswerActivity.this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
						}
						return true;
					}
				}
				return false;
			}
		});

		// 保存并退出 按钮
		final Button saveButton = (Button) dialogView.findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveAndExitThisActivity();
				dialog.dismiss();
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

	/**
	 * 保存并退出当前Activity
	 */
	private void saveAndExitThisActivity() {

		if (isNewTest) {
			if (fullQuestionnaireModel.getQuestionnaireStateEnum() == QuestionnaireStateEnum.NOT_STARTED) {
				// 如果测试还未开始, 就不保存当前数据
				ToolsFunctionForThisProgect.deleteQuestionnaireFormUnfinishedListAndDeleteFile(0);
			} else {
				// 保存量表到文件系统中
				ToolsFunctionForThisProgect.saveQuestionnaireToFileSystem(fullQuestionnaireModel);
			}

			// 返回 "主界面"
			Intent intent = new Intent(AnswerActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {

			// 保存量表到文件系统中
			ToolsFunctionForThisProgect.saveQuestionnaireToFileSystem(fullQuestionnaireModel);

			// 返回 "继续未完成"
			finish();
		}
	}

	/**
	 * 跳转到 "发送给医生" Activity
	 */
	private void gotoSendToDoctorActivity() {

		// 保存量表到文件系统中
		ToolsFunctionForThisProgect.saveQuestionnaireToFileSystem(fullQuestionnaireModel);

		Intent intent = new Intent(AnswerActivity.this, SendToDoctorActivity.class);
		if (isNewTest) {
			intent.putExtra(SendToDoctorActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), 0);
		} else {
			intent.putExtra(SendToDoctorActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), indexOfQuestionnaireListOfUnfinished);
		}
		startActivity(intent);
		finish();
	}

	// 隐藏软键盘
	private void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
	}
}
