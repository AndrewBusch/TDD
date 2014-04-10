/*******************************************************************************
 * Copyright (c) 2014 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Used in CS3733, Software Engineering at Worcester Polytechnic Institute
 *******************************************************************************/

package citycameras;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class implements the algorithms for planning the city security cameras
 * as described in the TDD assignment for CS3733. The idea is taken from the
 * <a href="http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=111006&format=html">
 * <em>Tourist Guide</em></a> problem at the Programming Challenges website.
 * 
 * @version Mar 30, 2014
 * @author <<Your Name Here>>
 */
public class CityCameraPlanner
{
	Collection<String> cameras;
	Collection<Node> rNodes;
	
	/**
	 * The constructor takes a collection of all of the roads in the city that
	 * connect neighborhoods and initializes the instance so that it can provide
	 * the locations of the cameras via a couple of query methods.
	 * @param roads the collection of roads that connect neighborhoods in the city
	 */
	public CityCameraPlanner(Collection<Road> roads)
	{
		rNodes = new HashSet<Node>();
		
		buildNodeCity(roads);

	}
	
	public void buildNodeCity(Collection<Road> roads){
		for(Road R : roads) {
			Node newNode1 = new Node(R.getNeighborhood1());
			Node newNode2 = new Node(R.getNeighborhood1());
			if(!rNodeContains(newNode1)) {
				rNodes.add(newNode1);
			}
			if(!rNodeContains(newNode2)) {
				rNodes.add(newNode2);
			}
		}
	}

	public boolean rNodeContains(Node newNode) {
		for(Node B : rNodes) {
			if(B.equals(newNode)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean areNeigbors(Node a, Node b) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return a collection of all neighborhoods containing cameras
	 */
	public Collection<String> getCameras()
	{
		return cameras;
	}
	
	/**
	 * @param neighborhood the neighborhood under consideration
	 * @return true if the neighborhood has a camera
	 */
	public boolean hasCamera(String neighborhood) {
		return cameras.contains(neighborhood);
	}
	
	/**
	 * @return the list of nodes
	 */
	public Collection<Node> getrNodes() {
		return this.rNodes;
	}
}
