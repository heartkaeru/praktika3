import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // зависимость (dependency)
            int v = sc.nextInt(); // пакет (package)
            adj.get(u).add(v);
            inDegree[v]++;
        }

        // Алгоритм Кана (топологическая сортировка)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);

            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // Если удалось обработать все n вершин, цикла нет
        if (order.size() == n) {
            for (int i = 0; i < order.size(); i++) {
                System.out.print(order.get(i) + (i == order.size() - 1 ? "" : " "));
            }
            System.out.println();
        } else {
            // Обнаружена циклическая зависимость
            System.out.println("CYCLE");
        }
    }
}
