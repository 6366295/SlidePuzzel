package com.multimedia.slidepuzzel.solver2;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
	private final int stepWeight = 2;
	
	public int compare(Node n1, Node n2){
		int v1 = n1.getManhattanDistance() + (n1.getStepCount() * stepWeight);
		int v2 = n2.getManhattanDistance() + (n2.getStepCount() * stepWeight);
		if(v1 < v2){
			return -1;
		}else if(v1 > v2){
			return 1;
		}else{
			return 0;
		}
	}
	
	// Only one NodeComparator so always false
	public boolean equals(Object obj){
		return false;
	}
}