import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int h = sc.nextInt();
        int w = sc.nextInt();

        String[] map = new String[h];
        for (int i = 0; i < h; i++) {
            map[i] = sc.next();
        }

        int sr = sc.nextInt();
        int scCol = sc.nextInt();
        int tr = sc.nextInt();
        int tc = sc.nextInt();

        if (map[sr].charAt(scCol) == '#' || map[tr].charAt(tc) == '#') {
            System.out.println("NO 0 0");
            return;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Stack<int[]> stack = new Stack<>();
        boolean[][] visitedStack = new boolean[h][w];
        stack.push(new int[]{sr, scCol});
        visitedStack[sr][scCol] = true;
        int countStack = 0;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            countStack++;

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (map[nr].charAt(nc) == '.' && !visitedStack[nr][nc]) {
                        visitedStack[nr][nc] = true;
                        stack.push(new int[]{nr, nc});
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visitedQueue = new boolean[h][w];
        queue.add(new int[]{sr, scCol});
        visitedQueue[sr][scCol] = true;
        int countQueue = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            countQueue++;

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (map[nr].charAt(nc) == '.' && !visitedQueue[nr][nc]) {
                        visitedQueue[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }

        if (visitedQueue[tr][tc]) {
            System.out.println("YES " + countStack + " " + countQueue);
        } else {
            System.out.println("NO 0 0");
        }
    }
}
