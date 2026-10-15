/** Mission 3. The maze of Mission 1 as doors between cells, and DFS on it. */
public class Maze {
    private final int n, cols;
    private final int[] doors;   // биты: 1 = вверх, 2 = вправо, 4 = вниз, 8 = влево
    private final int[] delta;   // на сколько меняется номер клетки в каждом направлении
    public int[] path;           // клетки пути от старта до выхода

    public Maze(Input.Walls w, boolean[] isBroken) {
        n = w.cells();
        cols = w.cols();
        doors = new int[n];
        delta = new int[]{-cols, 1, cols, -1};
        for (int i = 0; i < w.count(); i++) {
            if (!isBroken[i]) continue;   // стена стоит, двери нет
            int a = Math.min(w.a()[i], w.b()[i]), b = Math.max(w.a()[i], w.b()[i]);
            if (cols > 1 && b == a + 1) { doors[a] |= 2; doors[b] |= 8; }   // соседи по строке
            else { doors[a] |= 4; doors[b] |= 1; }                          // соседи по столбцу
        }
    }

    /** DFS без рекурсии. Возвращает {длина пути, сколько клеток помечено к моменту выхода}. */
    public long[] dfs() {
        int exit = n - 1;
        boolean[] seen = new boolean[n];
        int[] parent = new int[n];
        int[] nextDir = new int[n];   // какое направление этой клетки пробуем следующим
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        seen[0] = true;
        parent[0] = -1;
        long visited = 1;
        while (top > 0 && !seen[exit]) {
            int v = stack[top - 1];
            if (nextDir[v] == 4) { top--; continue; }   // все двери проверены, шаг назад
            int d = nextDir[v]++;
            if ((doors[v] & (1 << d)) == 0) continue;   // в этом направлении стена
            int u = v + delta[d];
            if (seen[u]) continue;
            seen[u] = true;
            parent[u] = v;
            visited++;
            stack[top++] = u;
        }
        // восстанавливаем путь от выхода назад к старту
        int len = 0;
        for (int v = exit; v != -1; v = parent[v]) len++;
        path = new int[len];
        int i = len - 1;
        for (int v = exit; v != -1; v = parent[v]) path[i--] = v;
        return new long[]{len - 1, visited};
    }

    /** Клетки ровно с одной открытой дверью (старт и выход тоже считаются). */
    public long deadEnds() {
        long count = 0;
        for (int v = 0; v < n; v++) if (Integer.bitCount(doors[v]) == 1) count++;
        return count;
    }
}