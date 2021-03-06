package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
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
		final Queue<Boolean> isDone = new ArrayDeque<Boolean>();
		final Scanner resultsScanner = new Scanner(
				SlidingPuzzle.class.getResourceAsStream("43.66-ok"));
		while (resultsScanner.hasNextLine()) {
			if (resultsScanner.nextLine().length() > 0) {
				isDone.add(true);
			} else {
				isDone.add(false);
			}
		}
		resultsScanner.close();
		final Scanner problemsScanner = new Scanner(
				SlidingPuzzle.class.getResourceAsStream("problems.txt"));
		final PrintWriter out = new PrintWriter(SlidingPuzzle.class.getPackage().getName()
				.replace(".", "/")
				+ "/output.txt");
		problemsScanner.useDelimiter("[\\s,]");
		final int lx = problemsScanner.nextInt();
		final int rx = problemsScanner.nextInt();
		final int ux = problemsScanner.nextInt();
		final int dx = problemsScanner.nextInt();
		final int n = problemsScanner.nextInt();
		Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
				"lx = {0}, rx = {1}, ux = {2}, dx = {3}, n = {4}",
				new Integer[] { lx, rx, ux, dx, n });
		int ok = 0;
		int failed = 0;
		int count = 0;
		while (problemsScanner.hasNext()) {
			final int w = problemsScanner.nextInt();
			final int h = problemsScanner.nextInt();
			final String b = problemsScanner.next();
			count++;
			if (!isDone.poll() && !(w == 3 && h == 3 || w == 4 && h == 4)) {
				Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.INFO,
						"w = {0}, h = {1}, b = {2}", new Object[] { w, h, b });
				final String result = new BruteForceSolver().solve(w, h, b, 30000);
				if (result.length() > 0) {
					if (Util.isOk(result, w, h, b)) {
						out.println(result);
						out.flush();
						ok++;
						System.out.println("OK!");
					} else {
						Logger.getLogger(SlidingPuzzle.class.getName()).log(Level.WARNING,
								"結果が間違っていました：{0}", result);
						out.println();
						failed++;
					}
				} else {
					out.println();
				}
				System.err.printf("ok / count = %d / %d = %.1f%%, failed = %d\n", ok, count,
						(double) ok / count * 100, failed);
			} else {
				out.println();
			}
		}
		problemsScanner.close();
		out.close();
	}
}
