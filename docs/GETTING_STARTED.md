# Getting started

## 1. Repository

Create an empty public repository on GitHub, for example `maze-escape`. Then, in the folder with this project:

    git init
    git add .
    git commit -m "Add starter project"
    git branch -M main
    git remote add origin https://github.com/YOUR_NAME/maze-escape.git
    git push -u origin main

## 2. Your data

    java tools/MakeVariant.java YOUR_ID data
    git add data
    git commit -m "Add my data files"
    git push

## 3. Work in branches

One branch for each stage of the assignment:

    git checkout -b stage-1      missions 0 to 2
    git checkout -b stage-2      missions 3 to 5 (from main, after stage-1 is merged)
    git checkout -b stage-3      missions 6 and 7

Commit often and say what changed: "Implement DSU with path compression", "Add Kruskal", "Fix wrong cost in Prim". Messages like "update" or "fix" do not help anyone.

    git add -A
    git commit -m "Add Kruskal"
    git push -u origin stage-1

When a stage works, open a pull request into main on GitHub and merge it. Then:

    git checkout main
    git pull

main must always compile.

## 4. Check yourself

Tiny example, the right output is in `examples/tiny/expected.txt`:

    javac -d out src/main/java/*.java
    java -cp out Main examples/tiny/walls.txt examples/tiny/slides.txt

Tests: `mvn test`, or run them from your IDE.

## 5. See your maze

Your program writes `maze.txt` and `path.txt`. Open `tools/maze_viewer.html` in a browser and load `data/walls.txt`, `maze.txt` and `path.txt`. Save the picture as `maze.png` and put it in the repository.

## 6. Submit

Fill in this repository's README.md, merge everything into main and attach the link to the repository in Moodle. Open the link in a private browser window to check that it works without your login.
