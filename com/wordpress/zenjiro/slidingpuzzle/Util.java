package com.wordpress.zenjiro.slidingpuzzle;

import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
				final char[] chars = b.toCharArray();
				chars[index] = chars[index - 1];
				chars[index - 1] = '0';
				return new String(chars);
			}
			return null;
		case RIGHT:
			if (index % w != w - 1 && b.charAt(index + 1) != '=') {
				final char[] chars = b.toCharArray();
				chars[index] = chars[index + 1];
				chars[index + 1] = '0';
				return new String(chars);
			}
			return null;
		case UP:
			if (index / w > 0 && b.charAt(index - w) != '=') {
				final char[] chars = b.toCharArray();
				chars[index] = chars[index - w];
				chars[index - w] = '0';
				return new String(chars);
			}
			return null;
		case DOWN:
			if (index / w < h - 1 && b.charAt(index + w) != '=') {
				final char[] chars = b.toCharArray();
				chars[index] = chars[index + w];
				chars[index + w] = '0';
				return new String(chars);
			}
			return null;
		default:
			return null;
		}
	}

	/**
	 * @param b ボードの状態
	 * @return ゴールの状態
	 */
	public static String getGoal(final String b) {
		final String string = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final char[] ret = new char[b.length()];
		for (int i = 0; i < b.length() - 1; i++) {
			ret[i] = b.charAt(i) == '=' ? '=' : string.charAt(i);
		}
		ret[ret.length - 1] = '0';
		return new String(ret);
	}

	/**
	 * 検算します。
	 * @param path 結果
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボードの状態
	 * @return 正解したかどうか
	 */
	public static boolean isOk(final String path, final int w, final int h, String b) {
		final String goal = getGoal(b);
		for (int j = 0; j < path.length(); j++) {
			switch (path.charAt(j)) {
			case 'L':
				b = move(Direction.LEFT, w, h, b);
				break;
			case 'R':
				b = move(Direction.RIGHT, w, h, b);
				break;
			case 'U':
				b = move(Direction.UP, w, h, b);
				break;
			case 'D':
				b = move(Direction.DOWN, w, h, b);
				break;
			default:
				Logger.getLogger(Util.class.getName()).log(Level.WARNING, "不正な文字{0}が含まれています：{1}",
						new Object[] { path.charAt(j), path });
				return false;
			}
			if (b == null) {
				Logger.getLogger(Util.class.getName()).log(Level.WARNING, "不可能な動き{0}が指定されました：{1}",
						new Object[] { path.substring(0, j), b });
				return false;
			}
		}
		return b.equals(goal);
	}

	/**
	 * マンハッタン距離でゴールまでのヒューリスティック距離を求めます。
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボードの状態
	 * @return ヒューリスティック距離
	 */
	public static int getHeuristicDistance(final int w, final int h, final String b) {
		int ret = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				final int index = i * w + j;
				final String string = b.substring(index, index + 1);
				final int number = string.equals("=") ? index : Integer.parseInt(string, 16);
				final int row = number / w;
				final int col = number % w;
				ret += Math.abs(row - i) + Math.abs(col - j);
			}
		}
		return ret;
	}
}
