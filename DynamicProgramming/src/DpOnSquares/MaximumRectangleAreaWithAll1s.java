package DpOnSquares;

import java.util.Stack;

public class MaximumRectangleAreaWithAll1s {

	public static void main(String[] args) {
		int[][] mat = { { 1, 0, 1, 0, 0 },
						{ 1, 0, 1, 1, 1 }, 
						{ 1, 1, 1, 1, 1 }, 
						{ 1, 0, 0, 1, 0 } };
		System.out.println(maximalAreaOfSubMatrixOfAll1(mat,mat.length,mat[0].length));
	}

	public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
		// Write your code here.
		int maxArea = 0;
		int[] heights = new int[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == 1) {
					heights[j]++;
				} else {
					heights[j] = 0;
				}
			}
			int area = largestRectangleArea(heights);
			maxArea = Math.max(maxArea, area);
		}
		return maxArea;
	}

	static <E> int largestRectangleArea(int histo[]) {
		Stack<Integer> st = new Stack<Integer>();
		int maxA = 0;
		int n = histo.length;
		for (int i = 0; i <= n; i++) {
			while (!st.empty() && (i == n || histo[st.peek()] >= histo[i])) {
				int height = histo[st.peek()];
				st.pop();
				int width;
				if (st.empty())
					width = i;
				else
					width = i - st.peek() - 1;
				maxA = Math.max(maxA, width * height);
			}
			st.push(i);
		}
		return maxA;
	}

}
