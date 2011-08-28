package com.wordpress.zenjiro.slidingpuzzle;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * スライドパズルを解く
 */
public class SlidingPuzzle {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 * @throws IOException 入出力例外
	 */
	public static void main(final String[] args) throws IOException {
		final Scanner scanner = new Scanner(SlidingPuzzle.class.getResourceAsStream("problems.txt"));
		while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
	}
}
