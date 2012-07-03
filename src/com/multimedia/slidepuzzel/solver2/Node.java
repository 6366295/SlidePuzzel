package com.multimedia.slidepuzzel.solver2;

public class Node{
	private Node parent;
	private PuzzleField state;
	private int mhdValue;
	private int stepCount;
	
	public Node(Node n, PuzzleField p, PuzzleField finalState){
		if(n == null)stepCount = 0 ;
		else stepCount = n.getStepCount() + 1;
		
		state = p;
		parent = n;
		
		mhdValue = p.getManhattanDistance(finalState);
	}
	
	public Node getParent(){
		return parent;
	}
	
	public PuzzleField getState(){
		return state;
	}
	
	public int getStepCount(){
		return stepCount;
	}
	
	public int getManhattanDistance(){
		return mhdValue;
	}
}