package cn.skyduck.global_data_cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;
import cn.skyduck.global_data_cache.GlobalConstant.QuestionnaireCategoryEnum;
import cn.skyduck.model.setting.SystemSetting;
import cn.skyduck.net_service.domain_protocol.account_login.LogonNetRespondBean;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

/**
 * 需要全局缓存的数据
 * 
 * @author zhihua.tang
 */
public enum GlobalDataCacheForMemorySingleton {
	getInstance;

	// 是否是第一次启动App
	private boolean isFirstStartApp;

	public boolean isFirstStartApp() {
		return isFirstStartApp;
	}

	public void setFirstStartApp(boolean isFirstStartApp) {
		this.isFirstStartApp = isFirstStartApp;
	}

	// 客户端应用版本号
	private String clientVersion;

	public String getClientVersion() {
		return clientVersion;
	}

	public synchronized void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	// 客户端 Android 版本号
	private String clientAVersion;

	public String getClientAVersion() {
		return clientAVersion;
	}

	public synchronized void setClientAVersion(String clientAVersion) {
		this.clientAVersion = clientAVersion;
	}

	// 判断用户是否已经登录 (判断用户是否登录的依据就是 logonNetRespondBean 是否为空, 因为用户成功登录后,
	// 会获取到logonNetRespondBean)
	public boolean isUserLogged() {
		return logonNetRespondBean != null ? true : false;
	}

	// 用户登录成功后, 服务器返回的信息(判断有无此对象来判断当前用户是否已经登录)
	private LogonNetRespondBean logonNetRespondBean;

	public LogonNetRespondBean getLogonNetRespondBean() {
		return logonNetRespondBean;
	}

	public synchronized void setLogonNetRespondBean(LogonNetRespondBean logonNetRespondBean) {
		this.logonNetRespondBean = logonNetRespondBean;
	}

	// 当前用户的唯一标识ID(如果是匿名用户, 就是guest)
	public String getCurrentUserSpecialID() {

		do {
			if (logonNetRespondBean == null) {
				break;
			}
			if (TextUtils.isEmpty(logonNetRespondBean.getUserId())) {
				break;
			}

			return logonNetRespondBean.getUserId();
		} while (false);

		return "guest";
	}

	// 是否需要保存用户密码
	private boolean isNeedSavePassword;

	public boolean isNeedSavePassword() {
		return isNeedSavePassword;
	}

	public void setNeedSavePassword(boolean isNeedSavePassword) {
		this.isNeedSavePassword = isNeedSavePassword;
	}

	// 用户最后一次登录成功时的用户名
	private String usernameForLastSuccessfulLogon;

	public String getUsernameForLastSuccessfulLogon() {
		return usernameForLastSuccessfulLogon;
	}

	public synchronized void setUsernameForLastSuccessfulLogon(String usernameForLastSuccessfulLogon) {
		this.usernameForLastSuccessfulLogon = usernameForLastSuccessfulLogon;
	}

	// 用户最后一次登录成功时的密码
	private String passwordForLastSuccessfulLogon;

	public String getPasswordForLastSuccessfulLogon() {
		return passwordForLastSuccessfulLogon;
	}

	public synchronized void setPasswordForLastSuccessfulLogon(String passwordForLastSuccessfulLogon) {
		this.passwordForLastSuccessfulLogon = passwordForLastSuccessfulLogon;
	}

	// 系统设置
	private SystemSetting systemSetting;

	public SystemSetting getSystemSetting() {
		return systemSetting;
	}

	public void setSystemSetting(SystemSetting systemSetting) {
		this.systemSetting = systemSetting;
	}

	// 问卷类型列表
	private Map<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> questionnaireTypeMap;

	public Map<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> getQuestionnaireTypeMap() {
		return questionnaireTypeMap;
	}

	public void setQuestionnaireTypeMap(Map<QuestionnaireCategoryEnum, ArrayList<QuestionnaireCodeEnum>> questionnaireTypeMap) {
		this.questionnaireTypeMap = questionnaireTypeMap;
	}

	// 继续未完成中的量表(已经是完成按时间倒叙配需了)
	private List<FullQuestionnaireModel> questionnaireListOfUnfinished = new ArrayList<FullQuestionnaireModel>(110);

	public List<FullQuestionnaireModel> getQuestionnaireListOfUnfinished() {
		return questionnaireListOfUnfinished;
	}

}
