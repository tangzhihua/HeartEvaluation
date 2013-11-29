package cn.skyduck.toolutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.PreLoadedDataService;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.GlobalDataCacheForNeedSaveToFileSystem;
import cn.skyduck.model.setting.SystemSetting;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;
import cn.skyduck.net_service.domain_protocol.account_login.LogonNetRespondBean;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

/**
 * 这里只放置, 在当前项目中会被用到的方法
 * 
 * @author zhihua.tang
 */
@SuppressLint("SimpleDateFormat")
public final class ToolsFunctionForThisProgect {
	private static String TAG = "ToolsFunctionForThisProgect";

	private ToolsFunctionForThisProgect() {

	}

	/**
	 * 记录用户登录成功后的重要信息
	 * 
	 * @param logonNetRespondBean
	 * @param usernameForLastSuccessfulLogon
	 * @param passwordForLastSuccessfulLogon
	 */
	public static synchronized void noteLogonSuccessfulInfo(LogonNetRespondBean logonNetRespondBean, String usernameForLastSuccessfulLogon, String passwordForLastSuccessfulLogon) {

		if (logonNetRespondBean == null) {
			throw new IllegalArgumentException("logonNetRespondBean is null !");
		}

		if (TextUtils.isEmpty(usernameForLastSuccessfulLogon) || TextUtils.isEmpty(passwordForLastSuccessfulLogon)) {
			throw new IllegalArgumentException("username or password is empty ! ");
		}

		DebugLog.i(TAG, "logonRespond ---> " + logonNetRespondBean.toString());
		DebugLog.i(TAG, "username ---> " + usernameForLastSuccessfulLogon);
		DebugLog.i(TAG, "password ---> " + passwordForLastSuccessfulLogon);

		// 设置Cookie
		SimpleCookieSingleton.getInstance().add("JSESSIONID", logonNetRespondBean.getJSESSIONID());

		// 保用用户登录成功的信息
		GlobalDataCacheForMemorySingleton.getInstance.setLogonNetRespondBean(logonNetRespondBean);

		// 保留用户最后一次登录成功时的 用户名
		GlobalDataCacheForMemorySingleton.getInstance.setUsernameForLastSuccessfulLogon(usernameForLastSuccessfulLogon);

		// 保留用户最后一次登录成功时的 密码
		GlobalDataCacheForMemorySingleton.getInstance.setPasswordForLastSuccessfulLogon(passwordForLastSuccessfulLogon);

	}

	/**
	 * 清空登录相关信息
	 */
	public static synchronized void clearLogonInfo() {
		SimpleCookieSingleton.getInstance().remove("JSESSIONID");

		GlobalDataCacheForMemorySingleton.getInstance.setLogonNetRespondBean(null);
		// GlobalDataCacheForMemorySingleton.getInstance().setAccountIndexNetRespondBean(null);
	}

	public static synchronized void quitApp(final Activity activity) {
		activity.finish();

		// 停止和当前App相关的所有服务
		ToolsFunctionForThisProgect.stopServiceWithThisApp();

		// TODO:目前只在这里保存数据
		GlobalDataCacheForNeedSaveToFileSystem.writeAllCacheData();

		// 杀死当前app进程
		int nPid = android.os.Process.myPid();
		android.os.Process.killProcess(nPid);
	}

	/**
	 * 当前App文件在SDCard上, 数据缓存文件夹名称
	 */
	public static final String FiledataCacheFolderNameInSDCard = "airizu";

	/**
	 * 返回当前App缓存文件数据的完整路径, 如果有SD卡, 就返回SD卡上的路径, 如果没有, 就返回设备上的files目录
	 * 
	 * @return
	 */
	public static synchronized String getFiledataCachePath() {
		String pathString = "";
		// 判断SDCARD是否存在于手机上，并且可以进行读写访问
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			pathString = Environment.getExternalStorageDirectory().getPath() + "/" + FiledataCacheFolderNameInSDCard;
		} else {
			pathString = MyApplication.getApplication().getApplicationContext().getFilesDir().getPath();
		}

		return pathString;
	}

	/**
	 * 保存文件数据到缓存文件夹中(SD卡优先, 如果没有SD卡, 就保存到设备存储中)
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static synchronized FileOutputStream openFileOutputByFiledataCachePath(String fileName) throws Exception {
		FileOutputStream fileOutputStream = null;
		// 判断SDCARD是否存在于手机上，并且可以进行读写访问
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			String filePathForSDCard = Environment.getExternalStorageDirectory() + "/" + FiledataCacheFolderNameInSDCard;
			File file = new File(filePathForSDCard, fileName);
			fileOutputStream = new FileOutputStream(file);
		} else {
			fileOutputStream = MyApplication.getApplication().getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
		}

		return fileOutputStream;
	}

	/**
	 * 从缓存目录中读取文件数据 (这里已经考虑了SD卡设备存储的双支持)
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static synchronized FileInputStream openFileInputByFiledataCachePath(String fileName) throws Exception {
		FileInputStream fileInputStream = null;

		do {
			if (!fileIsExist(fileName)) {
				break;
			}
			String fileFullPath = MyApplication.getApplication().getApplicationContext().getFilesDir().getPath() + "/" + fileName;
			if (FileTools.fileIsExist(fileFullPath)) {
				fileInputStream = MyApplication.getApplication().getApplicationContext().openFileInput(fileName);
				break;
			}
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				fileFullPath = Environment.getExternalStorageDirectory() + "/" + FiledataCacheFolderNameInSDCard + "/" + fileName;
				if (FileTools.fileIsExist(fileFullPath)) {
					File file = new File(fileFullPath);
					fileInputStream = new FileInputStream(file);
					break;
				}
			}
		} while (false);

		return fileInputStream;
	}

	public static synchronized void createFiledataCacheFolderOnSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File fileFoler = new File(Environment.getExternalStorageDirectory() + "/" + FiledataCacheFolderNameInSDCard);
			if (!fileFoler.exists()) {
				fileFoler.mkdir();
			}
		}
	}

	/**
	 * 这里已经考虑了SD卡设备存储的双支持
	 * 
	 * @param fileName
	 * @return
	 */
	public static synchronized boolean fileIsExist(String fileName) {

		do {
			String fileFullPath = MyApplication.getApplication().getApplicationContext().getFilesDir().getPath() + "/" + fileName;
			if (FileTools.fileIsExist(fileFullPath)) {
				break;
			}
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				fileFullPath = Environment.getExternalStorageDirectory() + "/" + FiledataCacheFolderNameInSDCard + "/" + fileName;
				if (FileTools.fileIsExist(fileFullPath)) {
					break;
				}
			}

			return false;
		} while (false);

		return true;
	}

	public static synchronized String getApplicationVersion() {
		String version = "";

		InputStream inputStream = null;

		try {
			do {
				inputStream = MyApplication.getApplication().getResources().openRawResource(R.raw.build_revision);
				if (inputStream == null) {
					break;
				}
				final byte[] data = StreamTools.readInputStream(inputStream);
				if (data == null || data.length <= 0) {
					break;
				}
				version = new String(data, "utf-8");
			} while (false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
		}

		return version;
	}

	public static synchronized void stopServiceWithThisApp() {
		Intent intent = new Intent(MyApplication.getApplication(), PreLoadedDataService.class);
		MyApplication.getApplication().stopService(intent);
	}

	/**
	 * 开关WIFI
	 * 
	 * @param enabled
	 */
	public static void changeWifiState(boolean enabled) {
		WifiManager wifiManager = (WifiManager) MyApplication.getApplication().getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(enabled);
	}

	/**
	 * 设置当前WIFI标签
	 * 
	 * @return
	 */
	private static void setWifiStateTextView(final TextView wifiStateTextView) {
		NetConnectionManageTools netConnectionManageTools = new NetConnectionManageTools();
		if (!netConnectionManageTools.isNetAvailable()) {
			wifiStateTextView.setText(String.format(MyApplication.getApplication().getResources().getString(R.string.wifi_state), "网络未连接"));
			wifiStateTextView.setTextColor(MyApplication.getApplication().getResources().getColor(R.color.TextPromptInfo));
		} else {
			WifiManager wifiManager = (WifiManager) MyApplication.getApplication().getSystemService(Context.WIFI_SERVICE);
			if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
				wifiStateTextView.setText(String.format(MyApplication.getApplication().getResources().getString(R.string.wifi_state), wifiManager.getConnectionInfo().getSSID()));
				wifiStateTextView.setTextColor(MyApplication.getApplication().getResources().getColor(R.color.green));
			} else {
				wifiStateTextView.setText(String.format(MyApplication.getApplication().getResources().getString(R.string.wifi_state), "不可用"));
				wifiStateTextView.setTextColor(MyApplication.getApplication().getResources().getColor(R.color.TextPromptInfo));
			}
		}
	}

	/**
	 * 获取本机IP
	 * 
	 */
	public static String getDeviceIP() {
		String ipaddress = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
						ipaddress = inetAddress.getHostAddress().toString();
						break;
					}
				}

				if (!TextUtils.isEmpty(ipaddress)) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipaddress;
	}

	/**
	 * 刷新网络信息
	 */
	public static void refreshNetInfoView(Activity activity, int netInfoLayoutResID) {
		if (activity == null) {
			assert false : "入参 activity 为空!";
			return;
		}

		// 如果 wifi/服务器IP/本机编号/本机IP 都需要显示的话, 就隐藏 网络信息视图
		boolean isNeedShowNetInfoView = false;

		final SystemSetting systemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting();

		// WIFI状态
		final TextView wifiStateTextView = (TextView) activity.findViewById(R.id.wifi_state_textView);
		if (wifiStateTextView != null) {
			if (systemSetting.getDeviceInfo().isDisplayWifiState()) {
				isNeedShowNetInfoView = true;
				wifiStateTextView.setVisibility(View.VISIBLE);

				setWifiStateTextView(wifiStateTextView);

			} else {
				wifiStateTextView.setVisibility(View.INVISIBLE);
			}
		}
		// 服务器IP 标签
		final TextView serverIPTextView = (TextView) activity.findViewById(R.id.servicer_ip_textView);
		if (serverIPTextView != null) {
			if (systemSetting.getDeviceInfo().isDisplayServerIP()) {
				isNeedShowNetInfoView = true;
				serverIPTextView.setVisibility(View.VISIBLE);

				final int defaultServerIPIndex = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getDefaultServerIPIndex();
				final ServerIPForSystemSetting serverIP = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getServerIPCloneByIndex(defaultServerIPIndex);
				if (serverIP != null && !TextUtils.isEmpty(serverIP.getServerIP())) {
					serverIPTextView.setText(String.format(activity.getResources().getString(R.string.servicer_ip_string), serverIP.getServerIP()));
				} else {
					serverIPTextView.setText(String.format(activity.getResources().getString(R.string.servicer_ip_string), activity.getResources().getString(R.string.not_set)));
				}
			} else {
				serverIPTextView.setVisibility(View.INVISIBLE);
			}

		}
		// 本机编号 标签
		final TextView deviceNumberTextView = (TextView) activity.findViewById(R.id.device_number_textView);
		if (deviceNumberTextView != null) {
			if (systemSetting.getDeviceInfo().isDisplayDeviceNumber()) {
				isNeedShowNetInfoView = true;
				deviceNumberTextView.setVisibility(View.VISIBLE);

				deviceNumberTextView.setText(String.format(activity.getResources().getString(R.string.device_number_string), GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting()
						.getAdminSettings().getDeviceId()));
			} else {
				deviceNumberTextView.setVisibility(View.INVISIBLE);
			}

		}
		// 本机IP 标签
		final TextView deviceIpTextView = (TextView) activity.findViewById(R.id.device_ip_textView);
		if (deviceIpTextView != null) {
			if (systemSetting.getDeviceInfo().isDisplayDeviceIP()) {
				deviceIpTextView.setVisibility(View.VISIBLE);

				isNeedShowNetInfoView = true;

				final String deviceIP = ToolsFunctionForThisProgect.getDeviceIP();
				if (!TextUtils.isEmpty(deviceIP)) {
					deviceIpTextView.setText(String.format(activity.getResources().getString(R.string.device_ip_string), deviceIP));
				} else {
					deviceIpTextView.setText(activity.getResources().getString(R.string.get_device_ip_fail));
				}
			} else {
				deviceIpTextView.setVisibility(View.INVISIBLE);
			}

		}

		// 如果全部网络信息都不需要显示了, 就隐藏网络信息布局
		View netInfoLayout = activity.findViewById(netInfoLayoutResID);
		if (netInfoLayout != null) {
			if (isNeedShowNetInfoView) {
				netInfoLayout.setVisibility(View.VISIBLE);
			} else {
				netInfoLayout.setVisibility(View.INVISIBLE);
			}
		} else {
			assert false : "没有找到netInfoLayoutResID对应的View.";
		}

	}

	/**
	 * 获取用户登录状态
	 * 
	 * @return
	 */
	public static String getUserLoginState() {
		String userLoginStateString = "";
		if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {
			userLoginStateString = String.format(MyApplication.getApplication().getResources().getString(R.string.user_login_state_string), GlobalDataCacheForMemorySingleton.getInstance
					.getLogonNetRespondBean().getUserName());
		} else {
			userLoginStateString = MyApplication.getApplication().getResources().getString(R.string.unlogin_state);
		}

		return userLoginStateString;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dipToPx(float dpValue) {
		final float scale = MyApplication.getApplication().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int pxToDip(float pxValue) {
		final float scale = MyApplication.getApplication().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取当前分辨率下指定单位对应的像素大小（根据设备信息） px,dip,sp -> px
	 * 
	 * Paint.setTextSize()单位为px
	 * 
	 * 代码摘自：TextView.setTextSize()
	 * 
	 * @param unit
	 *          TypedValue.COMPLEX_UNIT_*
	 * @param size
	 * @return
	 */
	public static float getRawSize(int unit, float size) {
		Context c = MyApplication.getApplication();
		Resources r;

		if (c == null)
			r = Resources.getSystem();
		else
			r = c.getResources();

		return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
	}

	/**
	 * 从 "文完成列表" 中删除指定索引的量表, 并且删除该量表的文件
	 * 
	 * @param location
	 */
	public static synchronized void deleteQuestionnaireFormUnfinishedListAndDeleteFile(final int location) {
		List<FullQuestionnaireModel> questionnaireListOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		if (location < 0 || location >= questionnaireListOfUnfinished.size()) {
			return;
		}

		FullQuestionnaireModel questionnaireModel = questionnaireListOfUnfinished.remove(location);
		GlobalDataCacheForNeedSaveToFileSystem.deleteQuestionnaire(questionnaireModel);
	}

	public static synchronized void deleteQuestionnaireFormUnfinishedListAndDeleteFile(final FullQuestionnaireModel questionnaireModel) {
		if (!(questionnaireModel instanceof FullQuestionnaireModel)) {
			return;
		}
		List<FullQuestionnaireModel> questionnaireListOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		questionnaireListOfUnfinished.remove(questionnaireModel);
		GlobalDataCacheForNeedSaveToFileSystem.deleteQuestionnaire(questionnaireModel);
	}

	/**
	 * 将一个未完成的量表保存到文件系统中
	 * 
	 * @param questionnaireModel
	 */
	public static synchronized void saveQuestionnaireToFileSystem(FullQuestionnaireModel questionnaireModel) {
		GlobalDataCacheForNeedSaveToFileSystem.writeQuestionnaire(questionnaireModel);
	}

	/**
	 * 从文件系统中删除一个量表的缓存文件
	 * 
	 * @param questionnaireModel
	 */
	private static synchronized void deleteQuestionnaireFromFileSystem(FullQuestionnaireModel questionnaireModel) {
		GlobalDataCacheForNeedSaveToFileSystem.deleteQuestionnaire(questionnaireModel);
	}

	/**
	 * 从 "未完成列表" 中删除一组数据, 并且在文件系统中也删除这组文件
	 * 
	 * @param questionnaireModelsOfBatch
	 */
	public static synchronized void deleteQuestionnairesFromUnfinishedListAndDeleteFiles(List<FullQuestionnaireModel> questionnaireModelsOfBatch) {
		List<FullQuestionnaireModel> listOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		listOfUnfinished.removeAll(questionnaireModelsOfBatch);
		for (FullQuestionnaireModel questionnaireModel : questionnaireModelsOfBatch) {
			ToolsFunctionForThisProgect.deleteQuestionnaireFromFileSystem(questionnaireModel);
		}
	}

	public static String getDescriptionForWifiState(final int state) {
		String wifiStateString = "";
		switch (state) {
		case WifiManager.WIFI_STATE_DISABLED:
			wifiStateString = "已关闭";
			break;
		case WifiManager.WIFI_STATE_DISABLING:
			wifiStateString = "正在关闭中";
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			wifiStateString = "已开启";
			break;
		case WifiManager.WIFI_STATE_ENABLING:
			wifiStateString = "正在打开...";
			break;
		case WifiManager.WIFI_STATE_UNKNOWN:
			wifiStateString = "网卡状态未知";
			break;
		default:
			wifiStateString = "未知";
			break;
		}

		return wifiStateString;
	}

	public static byte[] stringToBytesOfQuestionnaireResults(final String string) {

		if (TextUtils.isEmpty(string)) {
			return new byte[0];
		}

		byte[] data = new byte[string.length() * 2];
		byte[] byteTemp = null;// 定义一个字符串解码临时字节数组
		byte[] UTF16ByteTemp = new byte[2];// 定义一个UTF-16字符的双字节临时字节数组

		// 整个字符串解码为字节数组
		try {
			byteTemp = string.getBytes("UTF-16BE");

			// 每个字符依次判断，是ASC-II码的直接用UTF-16解码加到数据包，是汉字的则用GBK解码
			for (int i = 0; i < byteTemp.length; i += 2) {
				UTF16ByteTemp[0] = byteTemp[i];
				UTF16ByteTemp[1] = byteTemp[i + 1];

				if (UTF16ByteTemp[0] == 0) {
					// 如果字符为ASC-II字符直接用UTF-16解码并添加到数据包
				} else {

					// 如果字符为汉字或其他用GBK解码
					String tempStr = new String(UTF16ByteTemp, "UTF-16BE");
					UTF16ByteTemp = tempStr.getBytes("GBK");
				}

				data[i] = UTF16ByteTemp[0];
				data[i + 1] = UTF16ByteTemp[1];
			}
		} catch (Exception e) {
			data = new byte[0];
		}

		return data;
	}
}
