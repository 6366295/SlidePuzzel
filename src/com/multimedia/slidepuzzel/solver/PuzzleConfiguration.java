package com.multimedia.slidepuzzel.solver;

/*
 * File: PuzzleConfiguration.java
 * Author: Brian Borowski
 * Date created: December 27, 2010
 * Date last modified: May 13, 2011
 */

public final class PuzzleConfiguration {
    public static final int PUZZLE_8  = 0,
                            PUZZLE_15 = 1,
                            ALGORITHM_ASTAR   = 0,
                            ALGORITHM_IDASTAR = 1,
                            HEURISTIC_MD = 0x2,
                            HEURISTIC_LC = 0x4;

    
    public static final PrimitiveHashMap patternDatabase_8_puzzle =
        new PrimitiveHashMap(262144, 1.0f);

    private static int numOfTiles /* 9, 16 */,
                       dimension,
                       algorithmType,
                       heuristicType,
                       numOfThreads;
    private static long goalState, goalStatePositions;
    private static Algorithm algorithm;
    private static boolean isVerbose = false;

   

    private PuzzleConfiguration() { }

    public static void initialize(
            final int puzzleType, final int algorithmType, final int heuristicType,
            final int numOfThreads) {

        PuzzleConfiguration.numOfTiles = puzzleType == PUZZLE_15 ? 16: 9;
        PuzzleConfiguration.algorithmType = algorithmType;
        PuzzleConfiguration.algorithm =
        algorithmType == ALGORITHM_ASTAR ? null : new IDAStar();
        PuzzleConfiguration.heuristicType = heuristicType;
        PuzzleConfiguration.numOfThreads = numOfThreads;
        PuzzleConfiguration.dimension = (int)Math.sqrt(numOfTiles);
        initializeGoalState(numOfTiles);
    }

    public static void setVerbose(final boolean isVerbose) {
        PuzzleConfiguration.isVerbose = isVerbose;
    }

    public static void setNumOfThreads(final int numOfThreads) {
        PuzzleConfiguration.numOfThreads = numOfThreads;
    }

    public static boolean isVerbose() {
        return isVerbose;
    }

    public static Algorithm getAlgorithm() {
        return algorithm;
    }

    public static int getHeuristic() {
        return heuristicType;
    }

    public static int getNumOfTiles() {
        return numOfTiles;
    }

    public static int getNumOfThreads() {
        return numOfThreads;
    }

    public static int getDimension() {
        return dimension;
    }

    public static long getGoalState() {
        return goalState;
    }

    public static long getGoalStatePositions() {
        return goalStatePositions;
    }

    public static String stringRepresentation() {
        final StringBuilder builder = new StringBuilder();
        switch (PuzzleConfiguration.numOfTiles) {
            case 9:
                builder.append("8-puzzle");
                break;
            case 16:
                builder.append("15-puzzle");
                break;
            default:
                return "Unknown puzzle type";
        }
        switch (PuzzleConfiguration.algorithmType) {
            case ALGORITHM_ASTAR:
                builder.append(", A*");
                break;
            case ALGORITHM_IDASTAR:
                builder.append(", IDA*");
                break;
            default:
                return "Unknown algorithm type";
        }
        switch (PuzzleConfiguration.heuristicType) {
            case HEURISTIC_MD:
                builder.append(" / Manhattan Distance");
                break;
            case HEURISTIC_LC:
                builder.append(" / Manhattan Distance + Linear Conflict");
                break;
            default:
                return "Unknown heuristic type";
        }
        if (PuzzleConfiguration.algorithmType == ALGORITHM_IDASTAR) {
            final int numOfThreads = PuzzleConfiguration.getNumOfThreads();
            if (numOfThreads == 1) {
                builder.append(" / Single-threaded");
            } else {
                builder.append(" / Multi-threaded (" + numOfThreads + ")");
            }
        }

        return builder.toString();
    }

    public static void initializeGoalState(final int numOfTiles) {
        goalState = 0;
        final int numOfTilesMinusOne = numOfTiles - 1;
        for (int i = 0; i < numOfTiles; ++i) {
            if (i != numOfTilesMinusOne) {
                final int iPlusOne = i + 1;
                goalState |= ((long)iPlusOne << (i << 2));
            } else {
                goalState |= ((long)0 << (i << 2));
            }
        }

        goalStatePositions =
        	Utility.getPositionsAsLong(goalState, numOfTilesMinusOne);
    }

   

    
}
