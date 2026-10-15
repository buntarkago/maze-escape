import java.util.*;

/** Mission 5. Graph of zones in topological order: longest chain and cheapest route. */
public class ZoneGraph {
    public final long flowStages;
    public final long fastestRouteCost;   // -1, если до выхода добраться нельзя

    public ZoneGraph(Input.Slides s, Kosaraju k) {
        int z = k.zones, m = s.count();
        int[] comp = k.comp;

        // слайды между РАЗНЫМИ зонами, сгруппированные по зоне, из которой они идут
        int[] start = new int[z + 1];
        for (int i = 0; i < m; i++) {
            int a = comp[s.from()[i]], b = comp[s.to()[i]];
            if (a != b) start[a + 1]++;
        }
        for (int i = 0; i < z; i++) start[i + 1] += start[i];
        int[] pos = Arrays.copyOf(start, z);
        int[] slide = new int[start[z]];
        for (int i = 0; i < m; i++) {
            int a = comp[s.from()[i]], b = comp[s.to()[i]];
            if (a != b) slide[pos[a]++] = i;
        }

        long INF = Long.MAX_VALUE / 4;
        long[] stages = new long[z];
        long[] dist = new long[z];
        Arrays.fill(stages, 1);            // цепочка из одной зоны
        Arrays.fill(dist, INF);
        dist[comp[0]] = 0;                 // стоим в зоне старта

        // зоны идут в топологическом порядке: 0, 1, 2, ...
        for (int a = 0; a < z; a++) {
            for (int p = start[a]; p < start[a + 1]; p++) {
                int i = slide[p];
                int b = comp[s.to()[i]];
                stages[b] = Math.max(stages[b], stages[a] + 1);
                if (dist[a] < INF) dist[b] = Math.min(dist[b], dist[a] + s.time()[i]);
            }
        }

        long best = 0;
        for (long x : stages) best = Math.max(best, x);
        this.flowStages = best;
        long d = dist[comp[s.n() - 1]];
        this.fastestRouteCost = d >= INF ? -1 : d;
    }
}