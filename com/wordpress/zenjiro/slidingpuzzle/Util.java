package com.wordpress.zenjiro.slidingpuzzle;

import java.util.BitSet;

/**
 * ユーティリティクラス
 */
public class Util {
	/**
	 * "ABC"を"16,17,18"に変換します。変換できない文字は、その位置に対応する数字に変換します。
	 * @param hex 16進表記
	 * @return 10進表記
	 */
	public static String hexToDecimal(final String hex) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < hex.length(); i++) {
			try {
				final int value = Integer.parseInt(hex.substring(i, i + 1), 16);
				builder.append(value + ",");
			} catch (final NumberFormatException e) {
				builder.append((i + 1) + ",");
			}
		}
		return builder.toString().replaceFirst(",$", "");
	}

	/**
	 * @param hex 16進表記
	 * @return 壁の位置
	 */
	public static BitSet getWalls(final String hex) {
		final BitSet ret = new BitSet(hex.length());
		for (int i = 0; i < hex.length(); i++) {
			if (hex.charAt(i) == '=') {
				ret.set(i);
			}
		}
		return ret;
	}

	/**
	 * ボードの状態を表示します。
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボードの状態
	 */
	public static void print(final int w, final int h, final int b) {
		// TODO 実装する。
	}
}