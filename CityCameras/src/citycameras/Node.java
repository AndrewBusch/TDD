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

	Collection<Node> neighbors;
	
	public Node(){
	}
	
	public Collection<Node> getNeighbors() {
		return neighbors;
	}
}
