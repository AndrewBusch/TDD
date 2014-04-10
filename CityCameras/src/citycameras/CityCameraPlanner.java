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
		cameras = new HashSet<String>();
		buildNodeCity(roads);
		fillNodeCity(roads);
		createCameraList();
		
	}
	
	private void createCameraList() {
		for (Node node : rNodes){
			node.setHasCamera(checkForCamera(node));
		}
		
		for (Node node : rNodes) {
			if(node.getHasCamera()) {
				cameras.add(node.getName());
			}
		}
	}

	private boolean checkForCamera(Node mainNode) {
		Node currentNode = mainNode.getNeighbors().iterator().next();
		Collection<Node> visited = new HashSet<Node>();
		return checkNeighbors(mainNode, currentNode, visited);
	}
	
	private boolean checkNeighbors(Node mainNode, Node currentNode, Collection<Node> visited) {
		if (!visited.contains(currentNode)) {
			if (!currentNode.equals(mainNode)) {
				visited.add(currentNode);
				if ((rNodes.size() - 1) == visited.size()) return false;
				else {
					boolean status = true;
					for (Node goNode : currentNode.getNeighbors()) {
						status = status && checkNeighbors(mainNode, goNode, visited);
					}
					return status;
				}
			}
		}
		return true;
	}
	
	private void fillNodeCity(Collection<Road> roads){
		for(Node N : rNodes) {
			for(Road R : roads) {
				decideNeighbors(N, R);
			}
		}
	}

	private void decideNeighbors(Node N, Road R) {
		if(N.getName().equals(R.getNeighborhood1())){
			for(Node B : rNodes) {
				if(B.getName().equals(R.getNeighborhood2())) {
					N.addNeighbor(B);
				}
			}
		}
		if(N.getName().equals(R.getNeighborhood2())){
			for(Node B : rNodes) {
				if(B.getName().equals(R.getNeighborhood1())) {
					N.addNeighbor(B);
				}
			}
		}
	}

	private void buildNodeCity(Collection<Road> roads){
		for(Road R : roads) {
			Node newNode1 = new Node(R.getNeighborhood1());
			Node newNode2 = new Node(R.getNeighborhood2());
			if(!rNodeContains(newNode1)) {
				rNodes.add(newNode1);
			}
			if(!rNodeContains(newNode2)) {
				rNodes.add(newNode2);
			}
		}
	}

	private boolean rNodeContains(Node newNode) {
		for(Node B : rNodes) {
			if(B.equals(newNode)) {
				return true;
			}
		}
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
