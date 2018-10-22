package com.yunshuju.domain.enums;

/**
 * 功能：资源类型枚举
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-12 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
public enum SysResourceTypeEnum {
	/**
	 * 目录
	 */
	DIR(0),
	/**
	 * 菜单
	 */
	MENU(1),
	/**
	 * 按钮
	 */
	BUTTON(2),
	/**
	 * API
	 */
	API(3);

	private int value;

	SysResourceTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
