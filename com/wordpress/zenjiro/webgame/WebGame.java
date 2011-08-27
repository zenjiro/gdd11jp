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
		// 16枚を順番にクリックして色を取得してみる。
		final Robot robot = new Robot();
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 8; col++) {
				final Point point = getLocation(row, col);
				robot.mouseMove(point.x, point.y);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(10);
				System.out
						.printf("[%d, %d]: %s\n", row, col, robot.getPixelColor(point.x, point.y));
			}
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
