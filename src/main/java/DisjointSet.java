public class DisjointSet {
    private final int[] parent;
    private final int[] size;
    private int count;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;   // каждая клетка сама себе корень
            size[i] = 1;
        }
    }

    /** Корень группы x. Заодно сжимаем путь: все узлы по дороге вешаем прямо на корень. */
    public int find(int x) {
        int root = x;
        while (parent[root] != root) root = parent[root];
        while (parent[x] != root) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }
        return root;
    }

    /** Объединяет группы. false, если уже в одной группе. */
    public boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (size[ra] < size[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;          // маленькое дерево вешаем под большое
        size[ra] += size[rb];
        count--;
        return true;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public int count() {
        return count;
    }
}