package com.sssunday.common.constant;

import java.util.Map;

import com.sssunday.common.utils.ResourcesUtils;

public interface Constant {

	final Map<String, String> MAP = ResourcesUtils.getResouce("public_system").getMap();

	
	interface QiNiuFileCfg {
		/**
		 * KEY
		 */
		final String ACCESS_KEY = "pybyhhTGxxC9rbYGaWDSdY-Q7MiIYgtNwcIByX8Y";
		final String SECRET_KEY = "vqC7gP-AY_Ci_5Iuig-WZ-P1pbgKu4u2QrUrDuaa";
		/**
		 * 生产配置
		 */
		final String BUCKE_DEFAULT = "sssunday";
		/**
		 * 七牛地址
		 */
		final String FILESERVER_URL = "http://oet5bl9g5.bkt.clouddn.com";

	}

}
