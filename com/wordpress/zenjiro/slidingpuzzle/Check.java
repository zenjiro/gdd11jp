package com.wordpress.zenjiro.slidingpuzzle;

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
	 */
	public static void main(final String[] args) {
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
			int i = 0;
			int ok = 0;
			int failed = 0;
			int skipped = 0;
			final Scanner scanner = new Scanner(Check.class.getResourceAsStream("output.txt"));
			readRersult: while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				Logger.getLogger(Check.class.getName()).log(Level.INFO, "result = {0}", line);
				if (line.isEmpty()) {
					skipped++;
				} else {
					final int w = problems.get(i).w;
					final int h = problems.get(i).h;
					String b = problems.get(i).b;
					String goal = Util.getGoal(b);
					for (int j = 0; j < line.length(); j++) {
						switch (line.charAt(j)) {
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
							Logger.getLogger(Check.class.getName()).log(Level.WARNING,
									"不正な文字{0}が含まれています：{1}", new Object[] { line.charAt(j), line });
							break readRersult;
						}
					}
					if (b.equals(goal)) {
						ok++;
					} else {
						failed++;
					}
				}
				i++;
			}
			System.out.printf("ok : failed : skipped = %d : %d : %d = %.1f%% : %.1f%% : %.1f%%\n",
					ok, failed, skipped, ok / 5000.0 * 100, failed / 5000.0 * 100,
					skipped / 5000.0 * 100);
			scanner.close();
		}
	}
}
