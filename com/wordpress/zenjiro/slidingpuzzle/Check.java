package com.wordpress.zenjiro.slidingpuzzle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 結果を検算するツール
 */
public class Check {
	/**
	 * ボード
	 */
	static class Board {
		/**
		 * ボードの幅
		 */
		final int w;
		/**
		 * ボードの高さ
		 */
		final int h;
		/**
		 * ボードの状態
		 */
		final String b;

		/**
		 * ボードを初期化します。
		 * @param w ボードの幅
		 * @param h ボードの高さ
		 * @param b ボードの状態
		 */
		public Board(final int w, final int h, final String b) {
			this.w = w;
			this.h = h;
			this.b = b;
		}

		@Override
		public String toString() {
			return new Formatter().format("w = %d, h = %d, b = %s", this.w, this.h, this.b)
					.toString();
		}
	}

	/**
	 * メインメソッド
	 * @param args コマンドラン引数
	 * @throws FileNotFoundException ファイル未検出例外
	 */
	public static void main(final String[] args) throws FileNotFoundException {
		final String inputFile = "bruteforce-2000.txt";
		final String outputFile = "output.txt";
		final List<Board> problems = new ArrayList<Board>();
		{
			final Scanner scanner = new Scanner(Check.class.getResourceAsStream("problems.txt"));
			scanner.useDelimiter("[\\s,]");
			final int lx = scanner.nextInt();
			final int rx = scanner.nextInt();
			final int ux = scanner.nextInt();
			final int dx = scanner.nextInt();
			final int n = scanner.nextInt();
			Logger.getLogger(Check.class.getName()).log(Level.INFO,
					"lx = {0}, rx = {1}, ux = {2}, dx = {3}, n = {4}",
					new Integer[] { lx, rx, ux, dx, n });
			while (scanner.hasNext()) {
				final int w = scanner.nextInt();
				final int h = scanner.nextInt();
				final String b = scanner.next();
				final Board board = new Board(w, h, b);
				Logger.getLogger(Check.class.getName()).log(Level.INFO, "board = {0}", board);
				problems.add(board);
			}
			scanner.close();
		}
		{
			final PrintWriter out = new PrintWriter(SlidingPuzzle.class.getPackage().getName()
					.replace(".", "/")
					+ "/" + outputFile);
			int i = 0;
			int ok = 0;
			int failed = 0;
			int skipped = 0;
			final Scanner scanner = new Scanner(Check.class.getResourceAsStream(inputFile));
			while (scanner.hasNextLine()) {
				final String path = scanner.nextLine();
				Logger.getLogger(Check.class.getName()).log(Level.INFO, "path = {0}", path);
				if (path.isEmpty()) {
					out.println(path);
					skipped++;
				} else {
					if (Util.isOk(path, problems.get(i).w, problems.get(i).h, problems.get(i).b)) {
						out.println(path);
						ok++;
					} else {
						out.println();
						failed++;
					}
				}
				i++;
			}
			System.out.printf("ok : failed : skipped = %d : %d : %d = %.1f%% : %.1f%% : %.1f%%\n",
					ok, failed, skipped, ok / 5000.0 * 100, failed / 5000.0 * 100,
					skipped / 5000.0 * 100);
			scanner.close();
			out.close();
		}
	}
}
