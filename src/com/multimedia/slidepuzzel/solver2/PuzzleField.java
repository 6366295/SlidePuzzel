package com.multimedia.slidepuzzel.solver2;

public interface PuzzleField{
	public boolean equals(PuzzleField p);
	public boolean validMove(int x, int y);
	public void move(int x, int y);
	public int getNullX();
	public int getNullY();
	public int getManhattanDistance(PuzzleField ps);
	public int getTileIdx(int x, int y);
	public int getSize();
	public PuzzleField clone();
}