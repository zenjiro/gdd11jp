package com.wordpress.zenjiro.slidingpuzzle;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.wordpress.zenjiro.slidingpuzzle.Const.Direction;

/**
 * 総当たり探索するソルバ
 */
public class BruteForceSolver implements Solver {
	/**
	 * ノード
	 */
	static class Node implements Comparable<Node> {
		/**
		 * ボードの状態
		 */
		final String b;
		/**
		 * ここまでの手順
		 */
		final String path;
		/**
		 * ヒューリスティック距離
		 */
		final int heuristic;

		/**
		 * ノードを初期化します。
		 * @param b ボードの状態
		 * @param path ここまでの手順
		 * @param heuristic ヒューリスティック距離
		 */
		public Node(final String b, final String path, final int heuristic) {
			this.b = b;
			this.path = path;
			this.heuristic = heuristic;
		}

		@Override
		public String toString() {
			return this.b + ", " + this.path + ", " + (this.heuristic + this.path.length());
		}

		@Override
		public int compareTo(final Node node) {
			if (this.heuristic + this.path.length() == node.heuristic + node.path.length()) {
				return this.path.length() - node.path.length();
			} else {
				return (int) Math.signum(this.heuristic + this.path.length() - node.heuristic
						- node.path.length());
			}
		}

		@Override
		public boolean equals(final Object object) {
			if (object instanceof Node) {
				return this.b.equals(((Node) object).b);
			} else {
				return false;
			}
		}
	}

	@Override
	public String solve(final int w, final int h, final String b, final long limitMillis) {
		final long startTimeMillis = System.currentTimeMillis();
		final String goal = Util.getGoal(b);
		final String startB = b;
		Node currentNode = new Node(b, "", Util.getHeuristicDistance(w, h, b));
		final Queue<Node> queue = new PriorityQueue<Node>();
		final Set<String> visited = new HashSet<String>();
		while (!currentNode.b.equals(goal)
				&& System.currentTimeMillis() - startTimeMillis < limitMillis) {
			visited.add(currentNode.b);
			final String left = Util.move(Direction.LEFT, w, h, currentNode.b);
			if (left != null && !left.equals(startB) && !visited.contains(left)) {
				queue.add(new Node(left, currentNode.path + "L", Util.getHeuristicDistance(w, h,
						left)));
			}
			final String right = Util.move(Direction.RIGHT, w, h, currentNode.b);
			if (right != null && !right.equals(startB) && !visited.contains(right)) {
				queue.add(new Node(right, currentNode.path + "R", Util.getHeuristicDistance(w, h,
						right)));
			}
			final String up = Util.move(Direction.UP, w, h, currentNode.b);
			if (up != null && !up.equals(startB) && !visited.contains(up)) {
				queue.add(new Node(up, currentNode.path + "U", Util.getHeuristicDistance(w, h, up)));
			}
			final String down = Util.move(Direction.DOWN, w, h, currentNode.b);
			if (down != null && !down.equals(startB) && !visited.contains(down)) {
				queue.add(new Node(down, currentNode.path + "D", Util.getHeuristicDistance(w, h,
						down)));
			}
			currentNode = queue.remove();
		}
		return currentNode.b.equals(goal) ? currentNode.path : "";
	}
}
