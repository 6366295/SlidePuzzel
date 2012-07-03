package com.multimedia.slidepuzzel.solver2;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.multimedia.slidepuzzel.DrawGame;
import com.multimedia.slidepuzzel.gamelogic.Game;

public class PuzzleSolver{ 
	private Node rootNode;
	private PriorityQueue<Node> queue;
	private PuzzleField startState;
	private PuzzleField state;
	private Comparator<Node> comp;
 
	public PuzzleSolver(Game game, DrawGame drawControl){ 	
		// Start state, solved puzzle
		startState = game.getDefaultField();
		
		// End state, shuffled puzzle
		state = game.getField();
		// Keep rootNode in memory to prevent gc of tree 
		rootNode = new Node(null, startState, state);
		
		comp = new NodeComparator();
		queue = new PriorityQueue<Node> (100, comp);
		queue.add(rootNode);
		
		Node n;
		PuzzleField p;
		
		PuzzleField pn;
		Node nn;
		
		Node foundNode = null;
		while(true){
			n = queue.poll();
			p = n.getState();
			
			if(p.validMove(p.getNullX(), p.getNullY() - 1)){
				pn = p.clone();
				pn.move(p.getNullX(), p.getNullY() - 1);
				if(n.getParent() == null || !pn.equals(n.getParent().getState())){				
					nn = new Node(n, pn, state);
					if(pn.equals(state)){
						foundNode = nn;
						break;
					}
					queue.add(nn);
				}
			}	
			
			if(p.validMove(p.getNullX(), p.getNullY() + 1)){
				pn = p.clone();
				pn.move(p.getNullX(), p.getNullY() + 1);
				if(n.getParent() == null || !pn.equals(n.getParent().getState())){
					nn = new Node(n, pn, state);
					if(pn.equals(state)){
						foundNode = nn;
						break;
					}
					queue.add(nn);
				}
			}	
			
			if(p.validMove(p.getNullX() - 1, p.getNullY())){
				pn = p.clone();
				pn.move(p.getNullX() - 1, p.getNullY());
				if(n.getParent() == null || !pn.equals(n.getParent().getState())){
					nn = new Node(n, pn, state);										
					if(pn.equals(state)){
						foundNode = nn;
						break;
					}
					queue.add(nn);
				}
			}	
			
			if(p.validMove(p.getNullX() + 1, p.getNullY())){
				pn = p.clone();
				pn.move(p.getNullX() + 1, p.getNullY());
				if(n.getParent() == null || !pn.equals(n.getParent().getState())){
					nn = new Node(n, pn, state);								
					if(pn.equals(state)){
						foundNode = nn;
						break;
					}
					queue.add(nn);
				}
			}
		}
		queue.clear();
		android.util.Log.d("Solution", "Found " + foundNode.getParent().getState().getNullX() + ", "
				+ foundNode.getParent().getState().getNullY());
		drawControl.startSwapAnim(foundNode.getParent().getState().getNullX(),
				foundNode.getParent().getState().getNullY());
		
	}
	
	// Count length of path
	public int getNodeCount(Node n){
		int c = 1;
		while(n != null && n.getParent() != null){
			c++;
			n = n.getParent();
		}
		return c;
	}
} 
