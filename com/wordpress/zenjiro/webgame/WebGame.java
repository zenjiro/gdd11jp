package com.wordpress.zenjiro.webgame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Web Gameを解く
 */
public class WebGame {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 * @throws AWTException AWT例外
	 * @throws InterruptedException 割り込み例外
	 */
	public static void main(final String[] args) throws AWTException, InterruptedException {
		Thread.sleep(2000);
		final int rows = 128;
		final int cols = 8;
		for (int count = 0; count < 5; count++) {
			final boolean[][] isDone = new boolean[rows][cols];
			final Color[][] colors = new Color[rows][cols];
			final Robot robot = new Robot();
			for (int row = 0; row < colors.length; row++) {
				for (int column = 0; column < colors[row].length; column++) {
					final Point point = getLocation3(row, column);
					if (robot.getPixelColor(point.x, point.y).equals(new Color(221, 221, 221))) {
						click(point, robot);
						colors[row][column] = robot.getPixelColor(point.x, point.y);
					} else {
						isDone[row][column] = true;
					}
				}
			}
			Thread.sleep(2000);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (!isDone[i][j]) {
						inner: for (int k = 0; k < rows; k++) {
							for (int l = 0; l < cols; l++) {
								if (!isDone[k][l]) {
									if (i * cols + j < k * cols + l) {
										if (colors[i][j].equals(colors[k][l])) {
											System.out.printf("[%d, %d] == [%d, %d]: %s\n", i, j,
													k, l, colors[i][j]);
											click(getLocation3(i, j), robot);
											click(getLocation3(k, l), robot);
											break inner;
										}
									}
								}
							}
						}
					}
				}
			}
			Thread.sleep(10000);
		}
	}

	/**
	 * クリックします。
	 * @param point 点
	 * @param robot ロボット
	 * @throws InterruptedException 割り込み例外
	 */
	public static void click(final Point point, final Robot robot) throws InterruptedException {
		robot.mouseMove(point.x, point.y);
		Thread.sleep(10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(10);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(30);
	}

	/**
	 * Chrome 100%
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation(final int row, final int col) {
		return new Point(40 + col * 84, 80 + row * 82);
	}

	/**
	 * Firefox 30%
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation2(final int row, final int col) {
		return new Point(15 + col * 25, (int) (30 + row * 24.6));
	}

	/**
	 * Firefox 5%
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation3(final int row, final int col) {
		return new Point(3 + col * 4, 10 + (int) (row * 5.2));
	}
}
