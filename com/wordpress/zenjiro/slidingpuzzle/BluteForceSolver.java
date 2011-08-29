package com.wordpress.zenjiro.slidingpuzzle;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

/**
 * 総当りのソルバ
 */
public class BluteForceSolver implements Solver {
	@Override
	public String solve(final int w, final int h, final String b, final long limitMillis) {
		Util.print(w, h, b);
		final String b2 = Util.move(Direction.RIGHT, w, h, b);
		Util.print(w, h, b2);
		return null;
	}
}
