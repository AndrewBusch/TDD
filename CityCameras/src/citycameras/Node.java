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
	
	public Node(String name){
		this.name = name;
	}
	
	public Collection<Node> getNeighbors() {
		return neighbors;
	}
}
