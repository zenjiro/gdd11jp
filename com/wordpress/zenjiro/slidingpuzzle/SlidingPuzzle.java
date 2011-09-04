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
		int failed = 0;
		int count = 0;
		while (scanner.hasNext()) {
			final int w = scanner.nextInt();
			final int h = scanner.nextInt();
			final String b = scanner.next();
			Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
					"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
			if (w == 3 && h == 3) {
				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_8,
						PuzzleConfiguration.ALGORITHM_IDASTAR, PuzzleConfiguration.HEURISTIC_MD,
						Utility.getDefaultNumOfThreads());
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
					failed++;
					// test
					break;
				}
				//						} else 
				//			if (w == 3 && h == 4 || w == 4 && h == 3) {
				//				final String result = new BruteForceSolver().solve(w, h, b, 1000);
				//				if (result.length() > 0) {
				//					if (Util.isOk(result, w, h, b)) {
				//						out.println(result);
				//						ok++;
				//					} else {
				//						Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.WARNING,
				//								"結果が間違っていました：{0}", result);
				//						out.println();
				//						failed++;
				//					}
				//				} else {
				//					out.println();
				//				}
			} else {
				out.println();
			}
			count++;
			System.err.printf("ok / count = %d / %d = %.1f%%, failed = %d\n", ok, count,
					(double) ok / count * 100, failed);
		}
		scanner.close();
		out.close();
	}
}
