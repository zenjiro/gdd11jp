package com.wordpress.zenjiro.slidingpuzzle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 回答をマージするツール
 */
public class Merge {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 * @throws FileNotFoundException ファイル未検出例外
	 */
	public static void main(final String[] args) throws FileNotFoundException {
		final PrintWriter out = new PrintWriter(Merge.class.getPackage().getName()
				.replace(".", "/")
				+ "/output.txt");
		final String[] results = new String[5000];
		for (int i = 1; i < 11; i++) {
			final Scanner scanner = new Scanner(
					Merge.class.getResourceAsStream(Integer.toString(i)));
			for (int j = 0; j < 5000; j++) {
				final String line = scanner.nextLine();
				if (results[j] == null) {
					results[j] = line;
				} else {
					if (results[j].isEmpty() || line.length() > 0
							&& line.length() < results[j].length()) {
						results[j] = line;
					}
				}
			}
			scanner.close();
		}
		for (final String line : results) {
			out.println(line);
		}
		out.close();
	}
}
