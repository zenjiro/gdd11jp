package com.wordpress.zenjiro.slidingpuzzle;

/**
 * 自作スライドパズルソルバのインターフェイス
 */
public interface Solver {
	/**
	 * スライドパズルを解きます。
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボード初期状態
	 * @param limitMillis 制限時間[ms]
	 * @return 手順
	 */
	public String solve(final int w, final int h, final String b, final long limitMillis);
}
