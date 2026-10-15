# Assignment 3. Maze Escape

Student ID:
Name:
Group:
Maze size (rows x columns):

Design and Analysis of Algorithms, AITU. Java 17 or newer.
The task is in `docs/Assignment3.pdf`. Setup and git steps are in `docs/GETTING_STARTED.md`.
This file is the report. Fill it in as you go and keep it in the repository.

## How to run

(The commands you use to compile and run your program.)

    javac -d out src/main/java/*.java
    java -cp out Main data/walls.txt data/slides.txt

## Program output

Paste the 15 lines your program prints for your data:

    Broken:
    Kept:
    Last broken wall:
    MST cost:
    Heaviest broken wall:
    Path length:
    DFS visited:
    Dead ends:
    Zones:
    Largest zone:
    Is DAG:
    Flow stages:
    Fastest route cost:
    Best shortcut gain:
    Shortcut wall:

My maze:

![maze](maze.png)

## Mission 0. DSU

What do find and union do, and what makes them fast in your code?

## Mission 1. Build the maze

Why is Broken always R*C - 1?

Why is Kept always (R-1)*(C-1)?

## Mission 2. The cheapest maze

My MST cost from Kruskal and from Prim (they must be equal):

## Mission 3. Escape

Why is there exactly one path between two cells of the maze?

How did you avoid a stack overflow on a huge maze?

## Mission 4. Zones

Why does the second pass of Kosaraju go on the reversed graph?

How does your program decide that a topological order does not exist?

## Mission 5. Fastest route

Why is it enough to process the zones in topological order? What does the pass cost?

## Mission 6. Shortcut

Time of the fast way (two BFS) and of the simple way (one BFS for every wall), maze of 300 by 300 cells, 5 runs, in milliseconds:

| way | run 1 | run 2 | run 3 | run 4 | run 5 | average |
|---|---|---|---|---|---|---|
| fast | | | | | | |
| simple | | | | | | |

## Mission 7. Timing and complexity

Time of Kruskal and Prim in milliseconds, 5 runs each (System.nanoTime):

| maze | Kruskal | Prim |
|---|---|---|
| 100 x 100 | | |
| 300 x 300 | | |
| 1000 x 1000 | | |

![plot](plot.png)

Complexity of every algorithm you wrote. Say what n and m are.

| algorithm | time | space | assumptions |
|---|---|---|---|
| | | | |

## Files

    src/main/java/   Main.java, Input.java (ready), DisjointSet.java and your classes
    src/test/java/   tests
    examples/tiny/   a tiny example with the right output
    data/            your walls.txt and slides.txt
    tools/           MakeVariant.java, maze_viewer.html
    docs/            the assignment and the getting started notes

Do not use ready-made graph or DSU libraries.

## Before you submit

- data/walls.txt and data/slides.txt are in the repository and made from your ID.
- The program compiles with javac, prints the 15 lines and does not wait for the keyboard.
- maze.png and plot.png are in the repository.
- This README is filled in, the program output block included.
- The work is merged into main and the repository is public.
- The link is attached in Moodle before the deadline.

Defense: 5 minutes, one mini task and two questions. Come alone, with your laptop and your code open.
