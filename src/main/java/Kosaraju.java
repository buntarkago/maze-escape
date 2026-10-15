import java.util.*;

/** Mission 4. Strongly connected components of the slides (Kosaraju), no recursion. */
public class Kosaraju {
    public final int zones;       // сколько зон
    public final int[] comp;      // номер зоны для каждой клетки (зоны идут в топологическом порядке)
    public final int largest;     // размер самой большой зоны
    public final boolean isDag;   // есть ли топологический порядок всех клеток

    public Kosaraju(Input.Slides s) {
        int n = s.n(), m = s.count();

        // графы в массивах: fStart/fAdj = прямой, rStart/rAdj = обратный
        int[] fStart = new int[n + 1], fAdj = new int[m];
        int[] rStart = new int[n + 1], rAdj = new int[m];
        for (int i = 0; i < m; i++) { fStart[s.from()[i] + 1]++; rStart[s.to()[i] + 1]++; }
        for (int v = 0; v < n; v++) { fStart[v + 1] += fStart[v]; rStart[v + 1] += rStart[v]; }
        int[] fPos = Arrays.copyOf(fStart, n), rPos = Arrays.copyOf(rStart, n);
        boolean selfLoop = false;
        for (int i = 0; i < m; i++) {
            int a = s.from()[i], b = s.to()[i];
            fAdj[fPos[a]++] = b;
            rAdj[rPos[b]++] = a;
            if (a == b) selfLoop = true;
        }

        // Проход 1: DFS по прямому графу, записываем клетки в порядке завершения
        boolean[] seen = new boolean[n];
        int[] order = new int[n];
        int cnt = 0;
        int[] stack = new int[n];
        int[] pos = new int[n];                    // следующий слайд, который проверит клетка
        for (int r = 0; r < n; r++) {
            if (seen[r]) continue;
            int top = 0;
            stack[top++] = r; seen[r] = true; pos[r] = fStart[r];
            while (top > 0) {
                int v = stack[top - 1];
                if (pos[v] < fStart[v + 1]) {
                    int u = fAdj[pos[v]++];
                    if (!seen[u]) { seen[u] = true; pos[u] = fStart[u]; stack[top++] = u; }
                } else {
                    order[cnt++] = v;              // из клетки больше некуда, она закончена
                    top--;
                }
            }
        }

        // Проход 2: по порядку с конца, обход по ОБРАТНОМУ графу, каждый обход = одна зона
        int[] c = new int[n];
        Arrays.fill(c, -1);
        int z = 0, big = 0;
        for (int i = n - 1; i >= 0; i--) {
            int r = order[i];
            if (c[r] != -1) continue;
            int size = 0, top = 0;
            stack[top++] = r; c[r] = z;
            while (top > 0) {
                int v = stack[--top];
                size++;
                for (int e = rStart[v]; e < rStart[v + 1]; e++) {
                    int u = rAdj[e];
                    if (c[u] == -1) { c[u] = z; stack[top++] = u; }
                }
            }
            big = Math.max(big, size);
            z++;
        }

        this.zones = z;
        this.comp = c;
        this.largest = big;
        // порядок есть, только если нет цикла: все зоны из одной клетки и нет слайда в себя же
        this.isDag = (z == n) && !selfLoop;
    }
}