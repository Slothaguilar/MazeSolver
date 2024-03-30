/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam and Sofia Aguilar
 * @version 03/29/2024
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze

        // will get the solution in reverse order
        Stack<MazeCell> reverseOrderPath = new Stack<>();

        // start at the end of the maze
        MazeCell mazeCellParent = maze.getEndCell();

        // follow's each cell's parent back to the start and push into the stack
        while (mazeCellParent != maze.getStartCell().getParent()) {
            reverseOrderPath.push(mazeCellParent);
            mazeCellParent = mazeCellParent.getParent();
        }

        // will get the solution from start to end
        ArrayList<MazeCell> solutionPath = new ArrayList<>();

        // from the stack that has the path in reverse order, pop everything out, until empty, into the solutionPath-
        // and get the solution inorder
        while (!reverseOrderPath.isEmpty()) {
            solutionPath.add(reverseOrderPath.pop());
        }

        return solutionPath;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze

        // stack of the cells to possibly go to next
        Stack<MazeCell> cellsToVisit = new Stack<>();

        // start the current cell at the start
        MazeCell currentCell = maze.getStartCell();

        // continue through the maze until the current cell reaches the end
        while(currentCell != maze.getEndCell()){
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // first check the north neighbor if it is valid to possibly go and if so adding to cellsToVist, and setting the
            // parent of the neighbor and making it a cell explored
            if (maze.isValidCell(row -1, col)){
                cellsToVisit.push(maze.getCell(row -1, col));
                maze.getCell(row -1, col).setParent(currentCell);
                maze.getCell(row -1, col).setExplored(true);
            }

            // next check the east neighbor to see if valid and explore it if so(same as the north explanation)
            if (maze.isValidCell(row, col + 1)){
                cellsToVisit.push(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setParent(currentCell);
                maze.getCell(row, col+1).setExplored(true);
            }

            // next check the south neighbor to see if valid and explore it if so
            if (maze.isValidCell(row +1, col)){
                cellsToVisit.push(maze.getCell(row +1, col));
                maze.getCell(row +1, col).setParent(currentCell);
                maze.getCell(row +1, col).setExplored(true);
            }

            // next check the west neighbor to see if valid and explore it if so
            if (maze.isValidCell(row, col-1)){
                cellsToVisit.push(maze.getCell(row , col-1));
                maze.getCell(row, col-1).setParent(currentCell);
                maze.getCell(row, col-1).setExplored(true);
            }

            // change the currentcell to the top of the stack
            currentCell = cellsToVisit.pop();
        }

        // return the solution path
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS(){
        // TODO: Use BFS to solve the maze

        // Queue to hold all of the cells to go to next
        Queue<MazeCell> cellsToVist = new LinkedList<>();

        // start the current cell at the start
        MazeCell currentCell = maze.getStartCell();

        // continue through the maze until the current cell reaches the end
        while(currentCell != maze.getEndCell()){
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // first check the north neighbor if it is valid to possibly go and if so adding to cellsToVist, and setting the
            // parent of the neighbor and making it a cell explored
            if (maze.isValidCell(row -1, col)){
                cellsToVist.add(maze.getCell(row -1, col));
                maze.getCell(row -1, col).setParent(currentCell);
                maze.getCell(row -1, col).setExplored(true);
            }
            // next check the east neighbor to see if valid and explore it if so(same as the north explanation)
            if (maze.isValidCell(row, col + 1)){
                cellsToVist.add(maze.getCell(row, col+1));
                maze.getCell(row , col+1).setParent(currentCell);
                maze.getCell(row , col+1).setExplored(true);
            }
            // next check the south neighbor if it is valid to possibly go to and explore it if so
            if (maze.isValidCell(row +1, col)){
                cellsToVist.add(maze.getCell(row +1, col));
                maze.getCell(row +1, col).setParent(currentCell);
                maze.getCell(row +1, col).setExplored(true);
            }
            // lastly check the west neighbor if it is valid to possibly go to and explore it if so
            if (maze.isValidCell(row, col-1)){
                cellsToVist.add(maze.getCell(row , col-1));
                maze.getCell(row , col-1).setParent(currentCell);
                maze.getCell(row , col-1).setExplored(true);
            }

            // change the currentcell to the first thing in the queue
            currentCell = cellsToVist.remove();
        }

        // return the solution path
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        //  Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
