package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;

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
		//		final Scanner scanner = new Scanner(SlidingPuzzle.class.getResourceAsStream("problems.txt"));
		//		scanner.useDelimiter("[\\s,]");
		//		final int lx = scanner.nextInt();
		//		final int rx = scanner.nextInt();
		//		final int ux = scanner.nextInt();
		//		final int dx = scanner.nextInt();
		//		final int n = scanner.nextInt();
		//		Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
		//				"lx = {0}, rx = {1}, ux = {2}, dx = {3}, n = {4}",
		//				new Integer[] { lx, rx, ux, dx, n });
		//		while (scanner.hasNext()) {
		//			final int w = scanner.nextInt();
		//			final int h = scanner.nextInt();
		//			final String b = scanner.next();
		//			Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
		//					"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
		//		}
		//		scanner.close();
		// 試しに1問解いてみる。
		PuzzleConfiguration.initialize(PuzzleConfiguration.PUZZLE_15,
				PuzzleConfiguration.ALGORITHM_IDASTAR, PuzzleConfiguration.HEURISTIC_PD, 1);
		PuzzleConfiguration.getAlgorithm().solve(
				Utility.arrayToLong(Utility.getArray("4,13,15,8,3,6,7,10,11,1,12,9,14,5,2,0", 16)),
				Utility.getDefaultNumOfThreads());
		System.out.println(Algorithm.shortestPath);
	}
}
