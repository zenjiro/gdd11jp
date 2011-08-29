package com.wordpress.zenjiro.slidingpuzzle;

import java.util.Random;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

/**
 * 総当りのソルバ
 */
public class BluteForceSolver implements Solver {
	@Override
	public String solve(final int w, final int h, String b, final long limitMillis) {
		long startTimeMillis = System.currentTimeMillis();
		final StringBuilder ret = new StringBuilder();
		final String goal = Util.getGoal(b);
		final Random random = new Random();
		String startB = b;
		while (!b.equals(goal) && System.currentTimeMillis() - startTimeMillis < limitMillis) {
			switch (random.nextInt(4)) {
			case 0: {
				final String b2 = Util.move(Direction.LEFT, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('L');
					if (b.equals(startB)) {
						System.out.println("reset " + ret);
						ret.delete(0, ret.length());
					}
				}
				break;
			}
			case 1: {
				final String b2 = Util.move(Direction.RIGHT, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('R');
					if (b.equals(startB)) {
						System.out.println("reset " + ret);
						ret.delete(0, ret.length());
					}
				}
				break;
			}
			case 2: {
				final String b2 = Util.move(Direction.UP, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('U');
					if (b.equals(startB)) {
						System.out.println("reset " + ret);
						ret.delete(0, ret.length());
					}
				}
				break;
			}
			default: {
				final String b2 = Util.move(Direction.DOWN, w, h, b);
				if (b2 != null) {
					b = b2;
					ret.append('D');
					if (b.equals(startB)) {
						System.out.println("reset " + ret);
						ret.delete(0, ret.length());
					}
				}
			}
			}
		}
		System.out.println(ret.toString().substring(0, Math.min(30, ret.length())) + "...");
		return ret.toString();
	}
}
