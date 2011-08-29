package com.wordpress.zenjiro.slidingpuzzle;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

/**
 * 総当りのソルバ
 */
public class BluteForceSolver implements Solver {
	@Override
	public String solve(final int w, final int h, String b, final long limitMillis) {
		Util.print(w, h, b);
		b = Util.move(Direction.LEFT, w, h, b);
		Util.print(w, h, b);
		b = Util.move(Direction.RIGHT, w, h, b);
		Util.print(w, h, b);
		b = Util.move(Direction.RIGHT, w, h, b);
		Util.print(w, h, b);
		return null;
	}
}
