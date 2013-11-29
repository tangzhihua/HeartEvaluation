package cn.skyduck.activity.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cn.skyduck.activity.R;
import cn.skyduck.activity.fragment.questionnaire_navigation.QuestionnaireGridViewFragment;
import cn.skyduck.activity.fragment.questionnaire_navigation.SettingCommonTestFragment;
import cn.skyduck.toolutils.DebugLog;

/**
 * 问卷导航界面
 * 
 * @author hesiming
 * 
 */
public class QuestionnaireNavigationActivity extends FragmentActivity {
	private final String TAG = this.getClass().getSimpleName();

	// 其他量表界面对应的碎片
	private final QuestionnaireGridViewFragment questionnaireGridViewFragment = new QuestionnaireGridViewFragment();
	// "自定义常用测试" 界面对应的碎片
	private final SettingCommonTestFragment settingCommonTestFragment = new SettingCommonTestFragment();

	// 当前是否是 "问卷 GridView 碎片"
	private boolean isQuestionnaireGridViewFragment = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire_navigation_layout);

		final RadioGroup functionRadioGroup = (RadioGroup) findViewById(R.id.navigation_button_radioGroup);
		functionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (checkedId == R.id.back_to_main_activity_radioButton) {
					// 点击了 "返回主菜单" 按钮
					finish();
				} else {
					fragmentSwitch(checkedId);
				}

			}
		});

		// 第一个显示的 "碎片" 是 "常用设置"
		final RadioButton firstRadioButton = (RadioButton) findViewById(R.id.common_test_radioButton);
		firstRadioButton.setChecked(true);
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

	/**
	 * 切换 "碎片"
	 * 
	 * @param checkedIdOfRadioGroup
	 */
	private void fragmentSwitch(int checkedIdOfRadioGroup) {
		Fragment newFragment = null;
		if (checkedIdOfRadioGroup == R.id.user_defined_radioButton) {
			// 选中了 "自定义常用测试" 按钮
			newFragment = settingCommonTestFragment;
			isQuestionnaireGridViewFragment = false;
		} else {
			if (!isQuestionnaireGridViewFragment) {
				// 如果走这个分支, 证明要不是第一次进入当前Activity的初始化操作, 就是用户从
				// "自定义常用测试"碎片返回到普通测试类型碎片
				newFragment = questionnaireGridViewFragment;
				questionnaireGridViewFragment.setCheckedIdOfQuestionnaireTypeRadioGroup(checkedIdOfRadioGroup);
			} else {
				// 此时不需要切换碎片, 只需要更换 GridView dataSource
				questionnaireGridViewFragment.changeGridViewDataSource(checkedIdOfRadioGroup);
			}

			isQuestionnaireGridViewFragment = true;
		}

		if (newFragment != null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.fragment_container_layout, newFragment);

			// TODO : 因为 碎片切换时是有些耗时操作的, 所以不能增加切换过渡特效, 否则会照成过快切换 "自定义常用测试" 和 "普通分类"
			// 碎片切换时发生错乱
			// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();

			setClickableForRadioButtons(false);

			timerForSetClickableTrueForRadioButtons.schedule(new TimerTask() {

				@Override
				public void run() {
					setClickableForRadioButtons(true);
				}
			}, 250);
		}
	}

	// TODO : 为了防止用户快速点击 "自定义常用测试" 和 "普通分类" 出现界面错乱
	private Timer timerForSetClickableTrueForRadioButtons = new Timer();

	private void setClickableForRadioButtons(boolean clickable) {
		RadioButton functionRadioGroup = (RadioButton) findViewById(R.id.back_to_main_activity_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.common_test_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.personality_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.children_elderly_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.mental_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.emotional_stress_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.family_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.comprehensive_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.other_radioButton);
		functionRadioGroup.setClickable(clickable);
		functionRadioGroup = (RadioButton) findViewById(R.id.user_defined_radioButton);
		functionRadioGroup.setClickable(clickable);
	}
}
