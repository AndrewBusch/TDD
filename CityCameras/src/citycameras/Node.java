/**
 * 
 */
package citycameras;

import java.util.Collection;

/**
 * @author andrew
 *
 */
public class Node {

	String name;
	Collection<Node> neighbors;
	boolean hasCamera;
	
	public Node(String name){
		this.name = name;
	}
	
	public Collection<Node> getNeighbors() {
		return neighbors;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean equals(Node N){
		return N.getName().equals(this.getName());
	}

	public void addNeighbor(Node newNeighbor) {
		neighbors.add(newNeighbor);
	}
	
	public boolean getHasCamera() {
		return this.hasCamera;
	}
	
	public void setHasCamera(boolean hasCamera) {
		this.hasCamera = hasCamera;
	}
}
