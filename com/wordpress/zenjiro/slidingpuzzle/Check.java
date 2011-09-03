package com.wordpress.zenjiro.slidingpuzzle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

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
		final String inputFile = "output.txt";
		final String outputFile = "output2.txt";
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
				final String result = scanner.nextLine();
				Logger.getLogger(Check.class.getName()).log(Level.INFO, "result = {0}", result);
				if (result.isEmpty()) {
					out.println(result);
					skipped++;
				} else {
					if (isOk(result, problems.get(i).w, problems.get(i).h, problems.get(i).b)) {
						out.println(result);
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

	/**
	 * 検算します。
	 * @param result 結果
	 * @param w ボードの幅
	 * @param h ボードの高さ
	 * @param b ボードの状態
	 * @return 正解したかどうか
	 */
	public static boolean isOk(final String result, final int w, final int h, String b) {
		final String goal = Util.getGoal(b);
		for (int j = 0; j < result.length(); j++) {
			switch (result.charAt(j)) {
			case 'L':
				b = Util.move(Direction.LEFT, w, h, b);
				break;
			case 'R':
				b = Util.move(Direction.RIGHT, w, h, b);
				break;
			case 'U':
				b = Util.move(Direction.UP, w, h, b);
				break;
			case 'D':
				b = Util.move(Direction.DOWN, w, h, b);
				break;
			default:
				Logger.getLogger(Check.class.getName()).log(Level.WARNING, "不正な文字{0}が含まれています：{1}",
						new Object[] { result.charAt(j), result });
				return false;
			}
			if (b == null) {
				Logger.getLogger(Check.class.getName()).log(Level.WARNING, "不可能な動き{0}が指定されました：{1}",
						new Object[] { result.substring(0, j), b });
				return false;
			}
		}
		return b.equals(goal);
	}
}
