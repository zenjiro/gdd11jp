package com.wordpress.zenjiro.slidingpuzzle;

import java.util.BitSet;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

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
	public static void print(final int w, final int h, final String b) {
		if (b != null) {
			for (int i = 0; i < h; i++) {
				System.out.println(b.substring(i * w, i * w + w));
			}
		} else {
			System.out.println(b);
		}
		System.out.println();
	}

	/**
	 * 移動します。移動できないときはnullを返します。
	 * @param direction 向き
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボードの状態
	 * @return 移動後のボードの状態
	 */
	public static String move(final Direction direction, final int w, final int h, final String b) {
		final int index = b.indexOf('0');
		switch (direction) {
		case LEFT:
			if (index % w != 0 && b.charAt(index - 1) != '=') {
				char[] chars = b.toCharArray();
				chars[index] = chars[index - 1];
				chars[index - 1] = '0';
				return new String(chars);
			}
			return null;
		case RIGHT:
			return null;
		case UP:
			if (index / w > 0 && b.charAt(index - w) != '=') {
				char[] chars = b.toCharArray();
				chars[index] = chars[index - w];
				chars[index - w] = '0';
				return new String(chars);
			}
			return null;
		case DOWN:
			return null;
		default:
			return null;
		}
	}
}
