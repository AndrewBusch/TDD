package citycameras;

import java.util.Collection;
import java.util.HashSet;

/**
 * A Node is a neighborhood. Nodes that are connected by roads are 
 * stored as neighbors in each other's classes.
 */
public class Node {

	String name;
	Collection<Node> neighbors;
	boolean hasCamera; // true if node should have camera
	
	public Node(String name){
		this.name = name;
		neighbors = new HashSet<Node>();
	}
	
	/**
	 * @return neighbors of the node
	 */
	public Collection<Node> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * @return letter name of the node
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Compares two nodes
	 * @param N node to compare to
	 * @return true if nodes are equal, false if not
	 */
	public boolean equals(Node N){
		return N.getName().equals(this.getName());
	}

	/**
	 * @param newNeighbor neighbor to add to node neighbor list
	 */
	public void addNeighbor(Node newNeighbor) {
		neighbors.add(newNeighbor);
	}
	
	/**
	 * @return whether node has camera or not
	 */
	public boolean getHasCamera() {
		return this.hasCamera;
	}
	
	/**
	 * @param hasCamera if true, node needs camera
	 */
	public void setHasCamera(boolean hasCamera) {
		this.hasCamera = hasCamera;
	}
}
