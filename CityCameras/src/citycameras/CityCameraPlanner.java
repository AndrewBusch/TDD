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
	
	/**
	 * The constructor takes a collection of all of the roads in the city that
	 * connect neighborhoods and initializes the instance so that it can provide
	 * the locations of the cameras via a couple of query methods.
	 * @param roads the collection of roads that connect nighborhoods in the city
	 */
	public CityCameraPlanner(Collection<Road> roads)
	{
		Collection<Node> rNodes = new HashSet<Node>();
		
		for(Road a : roads) {
			Node currentRoad = new Node();
			for(Road b : roads) {
				
				if(areNeigbors(a, b)){
					
				}
			}
		}
	}
	
	private boolean areNeigbors(Road a, Road b) {
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
}
