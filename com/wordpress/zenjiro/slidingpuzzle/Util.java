package com.wordpress.zenjiro.slidingpuzzle;

/**
 * ユーティリティクラス
 */
public class Util {
	/**
	 * "ABC"を"16,17,18"に変換します。
	 * @param hex 16進表記
	 * @return 10進表記
	 */
	public static String hexToDecimal(String hex) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < hex.length(); i++) {
			int value = Integer.parseInt(hex.substring(i, i + 1), 16);
			builder.append(value + ",");
		}
		return builder.toString().replaceFirst(",$", "");
	}
}
