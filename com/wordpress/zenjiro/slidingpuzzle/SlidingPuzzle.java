package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.brianborowski.software.puzzle.Algorithm;
import com.brianborowski.software.puzzle.PuzzleConfiguration;
import com.brianborowski.software.puzzle.Utility;

/**
 * スライドパズルを解く
 * http://www.brian-borowski.com/Software/Puzzle/ を使います。
 */
public class SlidingPuzzle {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 * @throws IOException 入出力例外
	 */
	public static void main(final String[] args) throws IOException {
		final Scanner scanner = new Scanner(SlidingPuzzle.class.getResourceAsStream("problems.txt"));
		final PrintWriter out = new PrintWriter(SlidingPuzzle.class.getPackage().getName()
				.replace(".", "/")
				+ "/output.txt");
		scanner.useDelimiter("[\\s,]");
		final int lx = scanner.nextInt();
		final int rx = scanner.nextInt();
		final int ux = scanner.nextInt();
		final int dx = scanner.nextInt();
		final int n = scanner.nextInt();
		Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
				"lx = {0}, rx = {1}, ux = {2}, dx = {3}, n = {4}",
				new Integer[] { lx, rx, ux, dx, n });
		while (scanner.hasNext()) {
			final int w = scanner.nextInt();
			final int h = scanner.nextInt();
			final String b = scanner.next();
			Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
					"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
			if (w == 4 && h == 4) {
				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_15,
						PuzzleConfiguration.ALGORITHM_IDASTAR, PuzzleConfiguration.HEURISTIC_PD, 1);
				PuzzleConfiguration.getAlgorithm().solve(
						Utility.arrayToLong(Utility.getArray(Util.hexToDecimal(b), 16)),
						Utility.getDefaultNumOfThreads(), Util.getWalls(b));
				out.println(Algorithm.shortestPath);
			} else if (w == 3 && h == 3) {
				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_8,
						PuzzleConfiguration.ALGORITHM_ASTAR, PuzzleConfiguration.HEURISTIC_PD, 1);
				PuzzleConfiguration.getAlgorithm().solve(
						Utility.arrayToLong(Utility.getArray(Util.hexToDecimal(b), 9)),
						Utility.getDefaultNumOfThreads(), Util.getWalls(b));
				out.println(Algorithm.shortestPath);
			} else {
				out.println();
			}
		}
		scanner.close();
		out.close();
	}

	/**
	 * ユーティリティクラス
	 */
	public static class Util {
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

		/**
		 * @param hex 16進表記
		 * @return 壁の位置
		 */
		public static BitSet getWalls(String hex) {
			BitSet ret = new BitSet(hex.length());
			for (int i = 0; i < hex.length(); i++) {
				if (hex.charAt(i) == '=') {
					ret.set(i);
				}
			}
			return ret;
		}
	}
}
