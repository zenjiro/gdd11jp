package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;
import java.io.PrintWriter;
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
		int ok = 0;
		int count = 0;
		while (scanner.hasNext()) {
			final int w = scanner.nextInt();
			final int h = scanner.nextInt();
			final String b = scanner.next();
			Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
					"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
			if (w == 4 && h == 4) {
				//				final String result = new RandomSolver().solve(w, h, b, 30000);
				//				out.println(result);
				//				if (result.length() > 0) {
				//					ok++;
				//				}
				out.println();
			} else if (w == 3 && h == 3) {
				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_8,
						PuzzleConfiguration.ALGORITHM_ASTAR, PuzzleConfiguration.HEURISTIC_MD, 1);
				PuzzleConfiguration.getAlgorithm().solve(
						Utility.arrayToLong(Utility.getArray(Util.hexToDecimal(b), 9)),
						Utility.getDefaultNumOfThreads(), Util.getWalls(b));
				if (Util.isOk(Algorithm.shortestPath, w, h, b)) {
					out.println(Algorithm.shortestPath);
					ok++;
				} else {
					Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.WARNING,
							"結果が間違っていました：{0}", Algorithm.shortestPath);
					out.println();
				}
				//			} else if (w < 7 && h < 7) {
				//				final String result = new RandomSolver().solve(w, h, b, 30000);
				//				out.println(result);
				//				if (result.length() > 0) {
				//					ok++;
				//				}
			} else {
				out.println();
			}
			count++;
			System.err.printf("%d / %d = %.1f%%\n", ok, count, (double) ok / count * 100);
		}
		scanner.close();
		out.close();
	}
}
