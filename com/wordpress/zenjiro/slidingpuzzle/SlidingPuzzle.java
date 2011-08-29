package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
				//				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_15,
				//						PuzzleConfiguration.ALGORITHM_IDASTAR, PuzzleConfiguration.HEURISTIC_PD, 1);
				//				PuzzleConfiguration.getAlgorithm().solve(
				//						Utility.arrayToLong(Utility.getArray(Util.hexToDecimal(b), 16)),
				//						Utility.getDefaultNumOfThreads(), Util.getWalls(b));
				//				out.println(Algorithm.shortestPath);
				out.println();
			} else if (w == 3 && h == 3) {
				//				out.println(new BluteForceSolver().solve(w, h, b, 2000));
				//				PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_8,
				//						PuzzleConfiguration.ALGORITHM_ASTAR, PuzzleConfiguration.HEURISTIC_PD, 1);
				//				PuzzleConfiguration.getAlgorithm().solve(
				//						Utility.arrayToLong(Utility.getArray(Util.hexToDecimal(b), 9)),
				//						Utility.getDefaultNumOfThreads(), Util.getWalls(b));
				//				out.println(Algorithm.shortestPath);
				out.println();
			} else if (w == 4 && h == 3) {
				out.println(new BluteForceSolver().solve(w, h, b, 1000));
			} else {
				out.println();
			}
		}
		scanner.close();
		out.close();
	}
}
