package com.wordpress.zenjiro.slidingpuzzle;

import java.util.Random;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

/**
 * 総当りのソルバ
 */
public class BluteForceSolver implements Solver {
	@Override
	public String solve(final int w, final int h, String b, final long limitMillis) {
		final StringBuilder ret = new StringBuilder();
		final String goal = Util.getGoal(b);
		final Random random = new Random();
		while (!b.equals(goal)) {
			System.out.println(b);
			switch (random.nextInt(4)) {
			case 0: {
				final String b2 = Util.move(Direction.LEFT, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('L');
				}
				break;
			}
			case 1: {
				final String b2 = Util.move(Direction.RIGHT, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('R');
				}
				break;
			}
			case 2: {
				final String b2 = Util.move(Direction.UP, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('U');
				}
				break;
			}
			default: {
				final String b2 = Util.move(Direction.DOWN, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('D');
				}
			}
			}
		}
		System.out.println(b);
		return ret.toString();
	}
}
