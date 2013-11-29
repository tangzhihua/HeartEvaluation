package cn.skyduck.custom_control;

import android.view.View;

public interface CustomControlDelegate {
	/**
	 * @param contorl
	 *          控件对象本身
	 * @param actionTypeEnum
	 *          动作类型枚举
	 */
	public void customControlOnAction(final View contorl, final Object actionTypeEnum);
}
