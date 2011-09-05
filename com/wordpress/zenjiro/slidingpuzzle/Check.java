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
		final String inputFile = "merged.txt";
		final String outputFile = "checked.txt";
		final List<Board> problems = new ArrayList<Board>();
		final int[][] counts = new int[7][7];
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
				counts[h][w]++;
			}
			scanner.close();
		}
		int ok = 0;
		int failed = 0;
		int skipped = 0;
		final int[][] oks = new int[7][7];
		{
			final PrintWriter out = new PrintWriter(SlidingPuzzle.class.getPackage().getName()
					.replace(".", "/")
					+ "/" + outputFile);
			int i = 0;
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
						oks[problems.get(i).h][problems.get(i).w]++;
					} else {
						out.println();
						failed++;
					}
				}
				i++;
			}
			scanner.close();
			out.close();
		}
		System.out.printf("ok : failed : skipped = %d : %d : %d = %.1f%% : %.1f%% : %.1f%%\n", ok,
				failed, skipped, ok / 5000.0 * 100, failed / 5000.0 * 100, skipped / 5000.0 * 100);
		System.out.println("h, w\t3\t\t4\t\t5\t\t6");
		for (int i = 3; i < 7; i++) {
			System.out.print(i);
			for (int j = 3; j < 7; j++) {
				System.out.printf("\t%d/%d", oks[i][j], counts[i][j]);
			}
			System.out.println();
		}
	}
}
