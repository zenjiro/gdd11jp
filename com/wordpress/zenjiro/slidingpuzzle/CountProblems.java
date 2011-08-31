package com.wordpress.zenjiro.slidingpuzzle;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ボードのサイズごとに問題数を数えるツール
 */
public class CountProblems {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(SlidingPuzzle.class.getResourceAsStream("problems.txt"));
		scanner.useDelimiter("[\\s,]");
		final int lx = scanner.nextInt();
		final int rx = scanner.nextInt();
		final int ux = scanner.nextInt();
		final int dx = scanner.nextInt();
		final int n = scanner.nextInt();
		Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
				"lx = {0}, rx = {1}, ux = {2}, dx = {3}, n = {4}",
				new Integer[] { lx, rx, ux, dx, n });
		final Map<Dimension, Integer> results = new HashMap<Dimension, Integer>();
		while (scanner.hasNext()) {
			final int w = scanner.nextInt();
			final int h = scanner.nextInt();
			final String b = scanner.next();
			Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
					"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
			final Dimension size = new Dimension(w, h);
			if (results.containsKey(size)) {
				results.put(size, results.get(size) + 1);
			} else {
				results.put(size, 1);
			}
		}
		System.out.println(results);
		scanner.close();
	}
}
