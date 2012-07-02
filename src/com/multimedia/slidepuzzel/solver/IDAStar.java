package com.multimedia.slidepuzzel.solver;

/*
 * File: IDAStar.java
 * Author: Brian Borowski
 * Date created: December 26, 2010
 * Date last modified: April 13, 2012
 */
import java.util.Queue;

public class IDAStar extends Algorithm {
    private Queue<BFSNode> queue;
    private DFSWorker[] workers;

      void solvePuzzle(final long currentState, final int numOfThreads) {
      
            solveSingleThreaded(currentState);
        
    }

   

    private void solveSingleThreaded(final long currentState) {
        initialMovesEstimate = movesRequired = Node.h(currentState);
        workers = new DFSWorker[1];
        final DFSWorker dfsWorker = new DFSWorker();
        // Add to array so GUI can poll it for the stats in real time.
        workers[0] = dfsWorker;
        do {

            dfsWorker.setConfig(currentState, "X", movesRequired, 0);
            dfsWorker.run();
            if (!solved) {
                movesRequired += 2;
            }
        } while (running);
    }

    private void completeBFS(final BFSNode node) {
        solved = true;
        shortestPath = node.getShortestPath();

    }



    public void cleanup() { }
}
