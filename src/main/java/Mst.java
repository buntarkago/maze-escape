import java.util.*;

/** Mission 2. Minimum spanning tree: Kruskal and Prim. Both return {cost, heaviest wall}. */
public class Mst {

    public static long[] kruskal(Input.Walls w) {
        int m = w.count();
        // ключ = цена в старших битах + номер стены в младших, чтобы сортировать обычный long[]
        long[] keys = new long[m];
        for (int i = 0; i < m; i++) keys[i] = ((long) w.cost()[i] << 32) | i;
        Arrays.sort(keys);

        DisjointSet dsu = new DisjointSet(w.cells());
        long cost = 0, heaviest = 0;
        for (long key : keys) {
            int i = (int) (key & 0xFFFFFFFFL);
            if (dsu.union(w.a()[i], w.b()[i])) {
                cost += w.cost()[i];
                heaviest = Math.max(heaviest, w.cost()[i]);
            }
        }
        return new long[]{cost, heaviest};
    }

    public static long[] prim(Input.Walls w) {
        int n = w.cells(), m = w.count();
        // список соседей в массивах: стена i даёт два направленных ребра 2i (a->b) и 2i+1 (b->a)
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[2 * m];
        int[] to = new int[2 * m];
        for (int i = 0; i < m; i++) {
            int a = w.a()[i], b = w.b()[i];
            to[2 * i] = b;     next[2 * i] = head[a];         head[a] = 2 * i;
            to[2 * i + 1] = a; next[2 * i + 1] = head[b];     head[b] = 2 * i + 1;
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<Long> pq = new PriorityQueue<>();   // ключ = цена << 32 | номер ребра
        long cost = 0, heaviest = 0;

        visited[0] = true;
        for (int e = head[0]; e != -1; e = next[e]) pq.add(((long) w.cost()[e / 2] << 32) | e);

        while (!pq.isEmpty()) {
            long key = pq.poll();
            int e = (int) (key & 0xFFFFFFFFL);
            int v = to[e];
            if (visited[v]) continue;          // клетка уже в пятне, это ребро не нужно
            visited[v] = true;
            int c = w.cost()[e / 2];
            cost += c;
            heaviest = Math.max(heaviest, c);
            for (int f = head[v]; f != -1; f = next[f]) {
                if (!visited[to[f]]) pq.add(((long) w.cost()[f / 2] << 32) | f);
            }
        }
        return new long[]{cost, heaviest};
    }
}