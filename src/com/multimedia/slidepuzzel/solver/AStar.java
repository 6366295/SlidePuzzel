package com.multimedia.slidepuzzel.solver;

/* 
 * File: AStar.java
 * Author: Brian Borowski
 * Date created: December 26, 2010
 * Date last modified: January 12, 2012
 */
import java.util.HashMap;

public final class AStar extends Algorithm {
    private HashMap<Long, AStarNode> openMap, closedMap;

    void solvePuzzle(final long currentState, final int numOfThreads) {
        initialMovesEstimate = NOT_APPLICABLE;

        AStarNode currentConfig = new AStarNode(currentState);
        openMap = new HashMap<Long, AStarNode>();
        closedMap = new HashMap<Long, AStarNode>();
  
        int previous = 0;

        while (running) {
            ++numberVisited;
            movesRequired = currentConfig.g();
            if (movesRequired > previous) {

                previous = movesRequired;
            }
            if (currentConfig.isGoalState()) {

                shortestPath = currentConfig.getPath();
                solved = true;
                return;
            }

            closedMap.put(currentConfig.boardConfig, currentConfig);
            for (int i = 3; i >= 0; --i) {
                AStarNode successor;
                switch (i) {
                    case 3:
                        successor = currentConfig.moveLeft();
                        break;
                    case 2:
                        successor = currentConfig.moveRight();
                        break;
                    case 1:
                        successor = currentConfig.moveUp();
                        break;
                    default:
                        successor = currentConfig.moveDown();
                }

                if (successor != null) {
                    final AStarNode
                              openNode = openMap.get(successor.boardConfig),
                              closedNode = closedMap.get(successor.boardConfig);

                    if (closedNode == null || successor.cost < closedNode.cost) {
                        
                        openMap.put(successor.boardConfig, successor);
                        ++numberExpanded;
                    } else if (openNode != null && successor.cost < openNode.cost) {
                        
                        openMap.put(successor.boardConfig, successor);
                    }
                }
            }

            
        }
    }

    public void cleanup() {
        openMap = null;
        closedMap = null;
    }
}
