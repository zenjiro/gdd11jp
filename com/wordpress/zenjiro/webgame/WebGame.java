package com.wordpress.zenjiro.webgame;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;

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
		final Robot robot = new Robot();
		final Random random = new Random();
		final long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 60000) {
			final Point point = getLocation2(random.nextInt(32), random.nextInt(8));
			robot.mouseMove(point.x, point.y);
			Thread.sleep(1);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(1);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(1);
		}
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
