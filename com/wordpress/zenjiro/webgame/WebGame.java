package com.wordpress.zenjiro.webgame;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
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
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 60000) {
			int row = new Random().nextInt(8);
			int col = new Random().nextInt(8);
			final Point point = getLocation(row, col);
			robot.mouseMove(point.x, point.y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
}
