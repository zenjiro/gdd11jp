package com.wordpress.zenjiro.webgame;

import java.awt.AWTException;
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
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 32; k++) {
					for (int l = 0; l < 8; l++) {
						final Point point1 = getLocation2(i, j);
						click(point1.x, point1.y);
						final Point point2 = getLocation2(k, l);
						click(point2.x, point2.y);
					}
				}
			}
		}
	}

	/**
	 * クリックします。
	 * @param x x座標
	 * @param y y座標
	 * @throws AWTException AWT例外
	 * @throws InterruptedException 割り込み例外
	 */
	public static void click(int x, int y) throws AWTException, InterruptedException {
		final Robot robot = new Robot();
		robot.mouseMove(x, y);
		Thread.sleep(1);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(1);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1);
	}

	/**
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation(final int row, final int col) {
		return new Point(40 + col * 84, 80 + row * 82);
	}

	/**
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation2(final int row, final int col) {
		return new Point(15 + col * 25, (int) (30 + row * 24.6));
	}
}
