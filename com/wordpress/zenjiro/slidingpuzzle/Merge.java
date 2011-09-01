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
				+ "/output2.txt");
		final String[] results = new String[5000];
		for (String file : new String[]{"4.11", "Copy of output.txt"}) {
			final Scanner scanner = new Scanner(
					Merge.class.getResourceAsStream(file));
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
