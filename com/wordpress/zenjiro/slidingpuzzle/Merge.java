package com.wordpress.zenjiro.slidingpuzzle;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
				+ "/merged.txt");
		final String[] results = new String[5000];
		for (final File file : new File(Merge.class.getPackage().getName().replace(".", "/"))
				.listFiles(new FileFilter() {
					@Override
					public boolean accept(final File file) {
						return file.getName().matches(
								"18.02-0907|18.22-0908");
					}
				})) {
			Logger.getLogger(Merge.class.getName()).log(Level.INFO, "file = {0}", file);
			final Scanner scanner = new Scanner(file);
			for (int i = 0; i < 5000; i++) {
				if (scanner.hasNextLine()) {
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
			}
			scanner.close();
		}
		for (final String line : results) {
			out.println(line);
		}
		out.close();
	}
}
