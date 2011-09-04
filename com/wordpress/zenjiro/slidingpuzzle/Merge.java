package com.wordpress.zenjiro.slidingpuzzle;

import java.io.File;
import java.io.FileFilter;
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
		for (final File file : new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(final File file) {
				return file.getName().matches("output[-0-9]+.txt");
			}
		})) {
			final Scanner scanner = new Scanner(file);
			for (int i = 0; i < 5000; i++) {
				final String line = scanner.nextLine();
				if (results[i] == null) {
					results[i] = line;
				} else {
					if (results[i].isEmpty() || line.length() > 0
							&& line.length() < results[i].length()) {
						results[i] = line;
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
