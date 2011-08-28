package com.wordpress.zenjiro.slidingpuzzle;

/**
 * ユーティリティクラス
 */
public class Util {
	/**
	 * "ABC"を"16,17,18"に変換します。変換できない文字は、その位置に対応する数字に変換します。
	 * @param hex 16進表記
	 * @return 10進表記
	 */
	public static String hexToDecimal(String hex) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < hex.length(); i++) {
			try {
				int value = Integer.parseInt(hex.substring(i, i + 1), 16);
				builder.append(value + ",");
			} catch (NumberFormatException e) {
				builder.append((i + 1) + ",");
			}
		}
		return builder.toString().replaceFirst(",$", "");
	}
}
