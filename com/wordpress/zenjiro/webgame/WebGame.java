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
		// 1枚目をクリックしてみる
		Thread.sleep(2000);
		final Robot robot = new Robot();
		final Point point = getLocation(0, 0);
		robot.mouseMove(point.x, point.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 * @param row 行
	 * @param col 列
	 * @return カードの位置
	 */
	public static Point getLocation(final int row, final int col) {
		return new Point(40, 70);
	}
}
