package com.multimedia.slidepuzzel.solver;

import com.multimedia.slidepuzzel.DrawGame;

public final class PuzzleSolver {

    public PuzzleSolver(int[][] puzzleF, DrawGame drawControl) {
        int algorithm = PuzzleConfiguration.ALGORITHM_IDASTAR;
        int heuristic = PuzzleConfiguration.HEURISTIC_LC;
        byte[] tiles = null;
        int puzzleSize = puzzleF.length * puzzleF.length;
        final int numOfThreads = 1;
        tiles = Utility.getRandomArray(puzzleF,puzzleSize,true);
        final int puzzleType = tiles.length == 16 ? PuzzleConfiguration.PUZZLE_15 :
                                                        PuzzleConfiguration.PUZZLE_8;
        PuzzleConfiguration.setVerbose(true);
        PuzzleConfiguration.initialize(puzzleType, algorithm, heuristic, numOfThreads);
        PuzzleConfiguration.getAlgorithm().solve(
            Utility.arrayToLong(tiles), numOfThreads);
        Utility.displayStats(tiles, drawControl);
    }

} 
