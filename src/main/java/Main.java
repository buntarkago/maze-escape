import java.io.*;
import java.util.*;

/**
 * Assignment 3, Maze Escape.
 * Run: java -cp out Main data/walls.txt data/slides.txt
 * The program prints the 15 lines of the assignment, in this order. Put your
 * algorithms in their own classes
 * (for example Maze, Kosaraju, ZoneGraph) and call them from here.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Input.Walls walls = Input.readWalls(args[0]);
        Input.Slides slides = Input.readSlides(args[1]);

        // Mission 1. The maze: take the walls in the order of the file, break a wall
        // when union returns true.
        long broken = 0, kept = 0, lastBrokenWall = 0;
        DisjointSet dsu = new DisjointSet(walls.cells());
        boolean[] isBroken = new boolean[walls.count()];
        for (int i = 0; i < walls.count(); i++) {
            if (dsu.union(walls.a()[i], walls.b()[i])) {
                isBroken[i] = true; // клетки были в разных группах, ломаем стену
                broken++;
                lastBrokenWall = i + 1; // номера строк в файле с 1
            } else {
                kept++; // клетки уже связаны, стена остаётся
            }
        }

        // записываем номера сломанных стен, чтобы потом нарисовать лабиринт
        try (PrintWriter pw = new PrintWriter("maze.txt")) {
            for (int i = 0; i < walls.count(); i++) {
                if (isBroken[i])
                    pw.println(i + 1);
            }
        }

        // Mission 2. The cheapest maze: Kruskal with your DisjointSet, and Prim with a
        // priority queue (the two costs must be equal).
        long mstCost = 0, heaviestBrokenWall = 0;
        long[] kr = Mst.kruskal(walls);
        long[] pr = Mst.prim(walls);
        mstCost = kr[0];
        heaviestBrokenWall = kr[1];
        System.out.println("Kruskal cost: " + kr[0] + ", Prim cost: " + pr[0]);

        // Mission 3. Escape: DFS from cell 0 to the last cell in the maze of Mission 1
        // (up, right, down, left).
        long pathLength = 0, dfsVisited = 0, deadEnds = 0;
        Maze maze = new Maze(walls, isBroken);
        long[] dfs = maze.dfs();
        pathLength = dfs[0];
        dfsVisited = dfs[1];
        deadEnds = maze.deadEnds();
        try (PrintWriter pw = new PrintWriter("path.txt")) {
            for (int c : maze.path)
                pw.println(c);
        }

        // Mission 4. Zones of the slides: Kosaraju. Topological order of all cells:
        // possible or not.
        long zones = 0, largestZone = 0, isDag = 0;
        // TODO

        // Mission 5. The graph of zones in topological order: the longest chain and the
        // cheapest route to the exit.
        long flowStages = 0, fastestRouteCost = 0;
        // TODO

        // Mission 6. The best extra wall in the maze of Mission 1: BFS from the start,
        // BFS from the exit.
        long bestShortcutGain = 0, shortcutWall = -1;
        // TODO

        // Mission 7. Write maze.txt (numbers of the broken walls of Mission 1, one per
        // line, in the order of breaking)
        // and path.txt (cells of the way out, one per line) for tools/maze_viewer.html.
        // TODO

        print("Broken", broken);
        print("Kept", kept);
        print("Last broken wall", lastBrokenWall);
        print("MST cost", mstCost);
        print("Heaviest broken wall", heaviestBrokenWall);
        print("Path length", pathLength);
        print("DFS visited", dfsVisited);
        print("Dead ends", deadEnds);
        print("Zones", zones);
        print("Largest zone", largestZone);
        print("Is DAG", isDag);
        print("Flow stages", flowStages);
        print("Fastest route cost", fastestRouteCost);
        print("Best shortcut gain", bestShortcutGain);
        print("Shortcut wall", shortcutWall);
    }

    static void print(String name, long value) {
        System.out.println(name + ": " + value);
    }
}
