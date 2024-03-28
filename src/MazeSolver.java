/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
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
        // Should be from start to end cells

        // Complete the function getSolution() that returns the solution to the maze as
        // an ArrayList of MazeCells in order from start to end.

        //To get the solution to the maze, remember that you have to start at the
        // END of the maze and follow each cell’s parent back to the start. This will
        // get you the solution in reverse order, so you must then reverse it to get an
        // inorder solution.
        Stack<MazeCell> s = new Stack<>();
        MazeCell temp = maze.getEndCell();
        while (temp != maze.getStartCell().getParent()) {
            s.push(temp);
            temp = temp.getParent();
        }


        ArrayList<MazeCell> grid = new ArrayList<>();
        while (!s.isEmpty()) {
            grid.add(s.pop());
        }

        return grid;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        Stack<MazeCell> cellsToVist = new Stack<>();
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        ArrayList<MazeCell> exploredCells = new ArrayList<>();
        MazeCell currentCell = maze.getStartCell();
        // Push the start cell onto the stack
        cellsToVist.push(currentCell);
        exploredCells.add(currentCell);

        while(!cellsToVist.empty()){
            currentCell = cellsToVist.peek();
//            exploredCells.add(currentCell);

            // Check if the current cell is the end cell
            if (currentCell == maze.getEndCell()) {
                return getSolution();
            }

            MazeCell neighbor;
            // put everything into cellsTo Vist
            // north


        }

        // inizalize the current cell and look at the neighbors and hass to cells to vists

        //Think carefully about the data structure required to perform a depth-first search.
        //Explore your neighbors in the order NORTH, EAST, SOUTH, WEST
        //Return the ArrayList of MazeCells in the proper order
        // (they should be listed from start to end). You should call
        // getSolution() to help you do this.

        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        Queue<MazeCell> cellsToVist = new LinkedList<>();
        // first add the start
        cellsToVist.add(maze.getStartCell());

        // the visted cells
        ArrayList<MazeCell> vistedCells = new ArrayList<>();
        vistedCells.add(maze.getStartCell());

        while(!cellsToVist.isEmpty()){
            for (int i = 0; i < cellsToVist.size(); i++){
                // Remove the first cell from the queue
                MazeCell currentCell = cellsToVist.remove();
                MazeCell neighbor;

                // Check if the current cell is the end cell
                if (currentCell.equals(maze.getEndCell())) {
                    // If yes, return the solution
                    return getSolution();
                }
                // north
                if (maze.isValidCell(currentCell.getRow() -1, currentCell.getCol())){
                    neighbor = maze.getCell(currentCell.getRow() -1,currentCell.getCol());
                    cellsToVist.add(neighbor);
                }
                //east
                else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)){
                    neighbor = maze.getCell(currentCell.getRow(),currentCell.getCol()+1);
                    cellsToVist.add(neighbor);
                }
                // south
                else if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())){
                    neighbor = maze.getCell(currentCell.getRow() +1,currentCell.getCol());
                    cellsToVist.add(neighbor);
                }
                //west
                else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol()- 1)){
                    neighbor = maze.getCell(currentCell.getRow(),currentCell.getCol()-1);
                    cellsToVist.add(neighbor);
                }

                // check all of ite neighbors to see where we could go next
              // Save all the neighbors so that we know where we can go next.
                // Move to the next cell to visit.
            }
        }

        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return vistedCells;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
