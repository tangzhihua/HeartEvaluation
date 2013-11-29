package cn.skyduck.global_data_cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;
import cn.skyduck.model.setting.SystemSetting;
import cn.skyduck.questionnaire.CreateQuestionnaireModelHelper;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.FullQuestionnaireModelOfSerializable;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;
import cn.skyduck.toolutils.DebugLog;

/**
 * 这里序列化对象的保存目录是 : /data/data/cn.skyduck.activity/files/ , 这个目录会在用户在 "应用程序管理"
 * 中点击 "清理数据" 按钮后被清理
 * 
 * @author computer
 * 
 */
public final class GlobalDataCacheForNeedSaveToFileSystem {
	private final static String TAG = GlobalDataCacheForNeedSaveToFileSystem.class.getSimpleName();

	private GlobalDataCacheForNeedSaveToFileSystem() {

	}

	/**
	 * 读取本地缓存的 常用测试列表
	 */
	@SuppressWarnings("unchecked")
	public static synchronized void readQuestionnaireTypeMap() {
		DebugLog.i(TAG, "start loading --> questionnaireTypeMap");
		LinkedHashMap<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> questionnaireTypeMap = (LinkedHashMap<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>>) deserializeObjectFromFile(CacheDataNameForSaveToFile.QUESTIONNAIRE_TYPE_MAP
				.name());
		if (questionnaireTypeMap == null) {
			// 本地没有缓存的常用测试列表数据, 这里进行默认初始化

			questionnaireTypeMap = new LinkedHashMap<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>>(8);
			// 常用测试
			ArrayList<QuestionnaireCodeEnum> commonTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 人格类测验
			ArrayList<QuestionnaireCodeEnum> personalityTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 儿童及老年
			ArrayList<QuestionnaireCodeEnum> childrenAndElderlyTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 精神类测验
			ArrayList<QuestionnaireCodeEnum> mentalTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 情绪/应激类
			ArrayList<QuestionnaireCodeEnum> emotionalStressTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 家庭生活类
			ArrayList<QuestionnaireCodeEnum> familyLifeTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 综合类测验
			ArrayList<QuestionnaireCodeEnum> comprehensiveTestList = new ArrayList<QuestionnaireCodeEnum>(64);
			// 其它测验
			ArrayList<QuestionnaireCodeEnum> otherTestList = new ArrayList<QuestionnaireCodeEnum>(64);

			// 对体验版本的支持
			String[] trialVersionConfigArray = MyApplication.getApplication().getResources().getStringArray(R.array.trial_version_config);
			List<String> trialVersionConfigList = Arrays.asList(trialVersionConfigArray);
			boolean isTrialVersion = MyApplication.getApplication().getResources().getBoolean(R.bool.is_trial_bersion);
			for (QuestionnaireCodeEnum questionnaireCodeEnum : QuestionnaireCodeEnum.values()) {

				// 判断显示哪些演示版本的量表
				if (isTrialVersion && !trialVersionConfigList.contains(questionnaireCodeEnum.getShortName())) {
					// 当前是演示版本, 并且当前量表不在演示版中出现
					continue;
				}
				// 设置默认的 常用测试
				if (questionnaireCodeEnum.isDefaultCommonTest()) {
					commonTestList.add(questionnaireCodeEnum);
				}

				// 设置 当前问卷所归属的类型
				switch (questionnaireCodeEnum.getCategoryEnum()) {
				case PERSONALITY:
					personalityTestList.add(questionnaireCodeEnum);
					break;
				case CHILDREN_ELDERLY:
					childrenAndElderlyTestList.add(questionnaireCodeEnum);
					break;
				case MENTAL:
					mentalTestList.add(questionnaireCodeEnum);
					break;
				case EMOTIONS_STRESS:
					emotionalStressTestList.add(questionnaireCodeEnum);
					break;
				case FAMILY_LIFE:
					familyLifeTestList.add(questionnaireCodeEnum);
					break;
				case COMPLEX:
					comprehensiveTestList.add(questionnaireCodeEnum);
					break;
				case OTHER:
					otherTestList.add(questionnaireCodeEnum);
					break;
				default:
					break;
				}
			}

			//
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.COMMON_TEST, commonTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.PERSONALITY, personalityTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.CHILDREN_ELDERLY, childrenAndElderlyTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.MENTAL, mentalTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.EMOTIONS_STRESS, emotionalStressTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.FAMILY_LIFE, familyLifeTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.COMPLEX, comprehensiveTestList);
			questionnaireTypeMap.put(QuestionnaireCategoryEnum.OTHER, otherTestList);

		}
		GlobalDataCacheForMemorySingleton.getInstance.setQuestionnaireTypeMap(questionnaireTypeMap);
		DebugLog.i(TAG, "questionnaireTypeMap=" + questionnaireTypeMap.toString());
	}

	/**
	 * 读取本地缓存的 用户登录信息
	 */
	public static synchronized void readUserLoginInfo() {
		// 是否需要记住密码
		DebugLog.i(TAG, "start loading --> isNeedSavePassword");
		Boolean isNeedSavePassword = (Boolean) deserializeObjectFromFile(CacheDataNameForSaveToFile.SAVE_PASSWORD_MARK.name());
		if (isNeedSavePassword == null) {
			// 如果本地没有缓存, 就默认设置
			isNeedSavePassword = true;
		}
		GlobalDataCacheForMemorySingleton.getInstance.setNeedSavePassword(isNeedSavePassword);

		// 用户最后一次成功登录时的用户名
		DebugLog.i(TAG, "start loading --> usernameForLastSuccessfulLogon");
		final String usernameForLastSuccessfulLogon = (String) deserializeObjectFromFile(CacheDataNameForSaveToFile.USERNAME_FOR_LAST_SUCCESSFUL_LOGON.name());
		if (!TextUtils.isEmpty(usernameForLastSuccessfulLogon)) {
			DebugLog.i(TAG, "usernameForLastSuccessfulLogon=" + usernameForLastSuccessfulLogon);
			if (GlobalDataCacheForMemorySingleton.getInstance.getUsernameForLastSuccessfulLogon() == null) {
				GlobalDataCacheForMemorySingleton.getInstance.setUsernameForLastSuccessfulLogon(usernameForLastSuccessfulLogon);
			}
		} else {
			DebugLog.i(TAG, "usernameForLastSuccessfulLogon is null");
		}

		// 用户最后一次成功登录时的密码
		DebugLog.i(TAG, "start loading --> passwordForLastSuccessfulLogon");
		final String passwordForLastSuccessfulLogon = (String) deserializeObjectFromFile(CacheDataNameForSaveToFile.PASSWORD_FOR_LAST_SUCCESSFUL_LOGON.name());
		if (!TextUtils.isEmpty(passwordForLastSuccessfulLogon)) {
			DebugLog.i(TAG, "passwordForLastSuccessfulLogon=" + passwordForLastSuccessfulLogon);
			if (GlobalDataCacheForMemorySingleton.getInstance.getPasswordForLastSuccessfulLogon() == null) {
				GlobalDataCacheForMemorySingleton.getInstance.setPasswordForLastSuccessfulLogon(passwordForLastSuccessfulLogon);
			}
		} else {
			DebugLog.i(TAG, "passwordForLastSuccessfulLogon is null");
		}
	}

	/**
	 * 读取本地缓存的系统设置信息
	 */
	public static synchronized void readSystemSetting() {
		//
		DebugLog.i(TAG, "start loading --> SystemSetting");
		SystemSetting systemSetting = (SystemSetting) deserializeObjectFromFile(CacheDataNameForSaveToFile.SYSTEM_SETTING.name());
		if (systemSetting == null) {
			// 如果本地没有缓存, 就进行默认初始化
			systemSetting = new SystemSetting();
		}
		DebugLog.i(TAG, systemSetting.toString());
		GlobalDataCacheForMemorySingleton.getInstance.setSystemSetting(systemSetting);
	}

	/**
	 * 读取本地缓存的 某个用户的未完成的量表集合
	 * 
	 * @param userIdString
	 */
	public static synchronized void readQuestionnaireListOfUnfinished() {

		//
		DebugLog.i(TAG, "start loading --> QuestionnaireListOfUnfinished");
		String dirPathString = MyApplication.getApplication().getFilesDir().toString() + "/" + CacheDataNameForSaveToFile.LIST_OF_UNFINISHED.name();
		File dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建1级文件夹 : LIST_OF_UNFINISHED
			dirFile.mkdir();
		}
		dirPathString += "/" + GlobalDataCacheForMemorySingleton.getInstance.getCurrentUserSpecialID();
		dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建2级文件夹
			dirFile.mkdir();
		}

		if (dirFile.listFiles() != null && dirFile.listFiles().length > 0) {

			List<FullQuestionnaireModel> questionnaireListOfUnfinished = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
			for (File file : dirFile.listFiles()) {
				try {
					FullQuestionnaireModelOfSerializable questionnaireModelOfSerializable = (FullQuestionnaireModelOfSerializable) deserializeObjectFromFile(file.getName(), dirPathString);
					if (questionnaireModelOfSerializable == null) {
						DebugLog.e(TAG, "反序列化 未完成的量表失败! filename=" + file.getName());
						assert false : "反序列化未完成的量表失败, filename=" + file.getName();
						continue;
					}

					FullQuestionnaireModel questionnaireModel = CreateQuestionnaireModelHelper.getQuestionnaireModelByQuestionnaireCodeEnum(questionnaireModelOfSerializable.getQuestionnaireCodeEnum());
					if (questionnaireModel == null) {
						DebugLog.e(TAG, "反序列化未完成的量表失败, 量表名称=" + questionnaireModelOfSerializable.getQuestionnaireCodeEnum());
						assert false : "反序列化未完成的量表失败, 量表名称=" + questionnaireModelOfSerializable.getQuestionnaireCodeEnum();
						continue;
					}
					questionnaireModel.setQuestionnaireStateEnum(questionnaireModelOfSerializable.getQuestionnaireStateEnum());
					questionnaireModel.setCurrentFrameIndex(questionnaireModelOfSerializable.getCurrentFrameIndex());
					questionnaireModel.setSpecialID(questionnaireModelOfSerializable.getSpecialID());
					questionnaireModel.setBeginTestDate(questionnaireModelOfSerializable.getBeginTestDate());
					questionnaireModel.setEndTestdDate(questionnaireModelOfSerializable.getEndTestdDate());

					for (int i = 0; i < questionnaireModel.getFrameModelList().size(); i++) {
						BaseFramePageModel framePageModel = questionnaireModel.getFrameModelList().get(i);
						framePageModel.setAnswerDataSource(questionnaireModelOfSerializable.getUserAnswerDataSourceModelList().get(i));
					}

					questionnaireListOfUnfinished.add(questionnaireModel);
				} catch (Exception e) {
					e.printStackTrace();
					// 如果反序列化量表失败的话, 就直接删除这个量表
					file.delete();
					DebugLog.e(TAG, "反序列化 未完成的量表失败! filename=" + file.getName());
					continue;
				}

			}

			// 先按照 时间顺序 排序号
			Collections.sort(questionnaireListOfUnfinished, new Comparator<FullQuestionnaireModel>() {

				@Override
				public int compare(FullQuestionnaireModel lhs, FullQuestionnaireModel rhs) {
					return rhs.getBeginTestDate().compareTo(lhs.getBeginTestDate());
				}
			});
		}

	}

	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * 保存常用测试列表到文件系统中
	 */
	public static synchronized void writeQuestionnaireTypeMap() {
		//
		final Map<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> questionnaireTypeMap = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireTypeMap();
		if (questionnaireTypeMap != null) {
			serializeObjectToFile(CacheDataNameForSaveToFile.QUESTIONNAIRE_TYPE_MAP.name(), questionnaireTypeMap);
		}
	}

	/**
	 * 保存用户登录信息到设备文件系统中
	 */
	public static synchronized void writeUserLoginInfo() {
		//
		final Boolean isNeedSavePassword = Boolean.valueOf(GlobalDataCacheForMemorySingleton.getInstance.isNeedSavePassword());
		serializeObjectToFile(CacheDataNameForSaveToFile.SAVE_PASSWORD_MARK.name(), isNeedSavePassword);

		//
		final String usernameForLastSuccessfulLogon = GlobalDataCacheForMemorySingleton.getInstance.getUsernameForLastSuccessfulLogon();
		if (!TextUtils.isEmpty(usernameForLastSuccessfulLogon)) {
			serializeObjectToFile(CacheDataNameForSaveToFile.USERNAME_FOR_LAST_SUCCESSFUL_LOGON.name(), usernameForLastSuccessfulLogon);
		}

		//
		final String passwordForLastSuccessfulLogon = GlobalDataCacheForMemorySingleton.getInstance.getPasswordForLastSuccessfulLogon();
		if (!TextUtils.isEmpty(passwordForLastSuccessfulLogon)) {
			serializeObjectToFile(CacheDataNameForSaveToFile.PASSWORD_FOR_LAST_SUCCESSFUL_LOGON.name(), passwordForLastSuccessfulLogon);
		}
	}

	/**
	 * 保存系统设置信息信息到设备文件系统中
	 */
	public static synchronized void writeSystemSetting() {
		//
		final SystemSetting systemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting();
		if (systemSetting != null) {
			serializeObjectToFile(CacheDataNameForSaveToFile.SYSTEM_SETTING.name(), systemSetting);
		}
	}

	/**
	 * 保存未完成的量表到设备文件系统中
	 */
	public static synchronized void writeQuestionnaire(final FullQuestionnaireModel questionnaireModel) {

		long beginTime = new Date().getTime();
		String dirPathString = MyApplication.getApplication().getFilesDir().toString() + "/" + CacheDataNameForSaveToFile.LIST_OF_UNFINISHED.name();
		File dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建1级文件夹 : LIST_OF_UNFINISHED
			dirFile.mkdir();
		}
		dirPathString += "/" + GlobalDataCacheForMemorySingleton.getInstance.getCurrentUserSpecialID();
		dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建2级文件夹
			dirFile.mkdir();
		}
		dirFile = null;

		// 这里使用量表专用的序列化模型, 以提升序列化效率
		FullQuestionnaireModelOfSerializable questionnaireModelOfSerializable = new FullQuestionnaireModelOfSerializable();
		questionnaireModelOfSerializable.setQuestionnaireCodeEnum(questionnaireModel.getQuestionnaireCodeEnum());
		questionnaireModelOfSerializable.setQuestionnaireStateEnum(questionnaireModel.getQuestionnaireStateEnum());
		questionnaireModelOfSerializable.setCurrentFrameIndex(questionnaireModel.getCurrentFrameIndex());
		questionnaireModelOfSerializable.setSpecialID(questionnaireModel.getSpecialID());
		questionnaireModelOfSerializable.setBeginTestDate(questionnaireModel.getBeginTestDate());
		questionnaireModelOfSerializable.setEndTestdDate(questionnaireModel.getEndTestdDate());
		for (BaseFramePageModel framePageModel : questionnaireModel.getFrameModelList()) {
			Object userAnswerDataSource = framePageModel.getAnswerDataSource();
			questionnaireModelOfSerializable.getUserAnswerDataSourceModelList().add(userAnswerDataSource);
		}
		//

		// 这里经过测试, 单体序列化对象MMPI大概是40毫秒, 使用db4o面向对象数据库, 性能很差, 有1200毫秒
		serializeObjectToFile(questionnaireModel.getSpecialID(), dirPathString, questionnaireModelOfSerializable);

		long endTime = new Date().getTime();
		DebugLog.i(TAG, "-->writeQuestionnaire 保存量表:" + questionnaireModel.getQuestionnaireCodeEnum().getShortName() + "耗时为:" + (endTime - beginTime) + "毫秒");
	}

	public static synchronized void writeAllCacheData() {
		// 保存 "用户登录信息"
		writeUserLoginInfo();

		// 保存 "系统设置"
		writeSystemSetting();

		// 保存 "测试分类列表"
		writeQuestionnaireTypeMap();

	}

	public static synchronized void deleteQuestionnaire(final FullQuestionnaireModel questionnaireModel) {

		String dirPathString = MyApplication.getApplication().getFilesDir().toString() + "/" + CacheDataNameForSaveToFile.LIST_OF_UNFINISHED.name();
		File dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建1级文件夹 : LIST_OF_UNFINISHED
			dirFile.mkdir();
		}
		dirPathString += "/" + GlobalDataCacheForMemorySingleton.getInstance.getCurrentUserSpecialID();
		dirFile = new File(dirPathString);
		if (!dirFile.exists()) {
			// 创建2级文件夹
			dirFile.mkdir();
		}
		dirPathString += "/" + questionnaireModel.getSpecialID();
		dirFile = new File(dirPathString);

		//
		dirFile.delete();
	}

	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////

	private enum CacheDataNameForSaveToFile {
		// 是否是第一次启动App
		IS_FIRST_START_APP,
		// 保存密码
		SAVE_PASSWORD_MARK,
		// 用户最后一次成功登录时的用户名
		USERNAME_FOR_LAST_SUCCESSFUL_LOGON,
		// 用户最后一次成功登录时的密码
		PASSWORD_FOR_LAST_SUCCESSFUL_LOGON,
		// 系统设置
		SYSTEM_SETTING,
		// 用户常用测试
		QUESTIONNAIRE_TYPE_MAP,
		// 未完成的量表集合
		LIST_OF_UNFINISHED,
	};

	/**
	 * 将一个对象, 序列化到文件中
	 * 
	 * @param fileName
	 * @param fileSavePathString
	 * @param object
	 */
	private static void serializeObjectToFile(final String fileName, final String fileSavePathString, final Object object) {
		File file = null;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {

			do {
				if (object == null) {
					break;
				}
				if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(fileSavePathString)) {
					break;
				}

				file = new File(fileSavePathString + "/" + fileName);
				if (file.exists()) {
					file.delete();
				}
				file = new File(fileSavePathString + "/" + fileName);
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(object);

			} while (false);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (objectOutputStream != null) {
					objectOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 从一个文件中, 反序列化一个对象
	 * 
	 * 文件保存目录示例 : /data/data/cn.skyduck.activity/files 这个目录会在用户在应用程序管理中点击 "清理数据"
	 * 按钮后被清理
	 * 
	 * @param fileName
	 * @param fileSavePathString
	 * @return
	 */
	private static Object deserializeObjectFromFile(final String fileName, final String fileSavePathString) {
		Object object = null;
		File file = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {
			do {
				if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(fileSavePathString)) {
					break;
				}

				file = new File(fileSavePathString + "/" + fileName);
				if (!file.exists()) {
					break;
				}
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				object = objectInputStream.readObject();
			} while (false);
		} catch (Exception ex) {
			object = null;
			ex.printStackTrace();
		} finally {
			try {
				if (objectInputStream != null) {
					objectInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return object;
	}

	private static Object deserializeObjectFromFile(final String fileName) {
		return deserializeObjectFromFile(fileName, MyApplication.getApplication().getFilesDir().toString());
	}

	private static void serializeObjectToFile(final String fileName, final Object object) {
		serializeObjectToFile(fileName, MyApplication.getApplication().getFilesDir().toString(), object);
	}
}
