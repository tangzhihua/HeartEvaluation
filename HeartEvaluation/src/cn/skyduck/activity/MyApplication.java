package cn.skyduck.activity;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import cn.skyduck.global_data_cache.GlobalDataCacheForNeedSaveToFileSystem;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

// 在Android中启用断言的方法： adb shell setprop debug.assert 1 
// mac系统配置 adb
// 一、终端中输入 cd ~
// 二、输入 touch .bash_profile 回车
// 三、输入 open -e .bash_profile
// 输入 : export PATH=$PATH:/Applications/developer/android-sdk-macosx/tools:/Applications/developer/android-sdk-macosx/platform-tools
/**
 * 自定义 Application
 * 
 * @author hesiming
 * 
 */
public class MyApplication extends Application {
	private final String TAG = this.getClass().getSimpleName();

	// AirizuApplication 类对外的接口
	private static MyApplication self;

	public static Application getApplication() {
		return self;
	}

	@Override
	public void onCreate() {

		DebugLog.i(TAG, "onCreate");
		super.onCreate();

		// 必须在第一个行的位置
		self = this;

		// 打开WIFI
		ToolsFunctionForThisProgect.changeWifiState(true);

		// 单例类提前实例化

		// 读取系统设置
		GlobalDataCacheForNeedSaveToFileSystem.readSystemSetting();
		// 读取问卷分类Map
		GlobalDataCacheForNeedSaveToFileSystem.readQuestionnaireTypeMap();

		// 启动一个服务, 用于预加载相关数据
		Intent intent = new Intent(this, PreLoadedDataService.class);
		// 采用 startService() 启动的服务, 必须主动调用 stopService() 来主动停止
		startService(intent);

		// /

	}

	@Override
	public void onTerminate() {
		DebugLog.d(TAG, "onTerminate");
		// 父类方法, 必须调用
		super.onTerminate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		DebugLog.i(TAG, "onConfigurationChanged");
		super.onConfigurationChanged(newConfig);

	}

	@Override
	public void onLowMemory() {
		DebugLog.i(TAG, "onLowMemory");
		super.onLowMemory();

	}
}