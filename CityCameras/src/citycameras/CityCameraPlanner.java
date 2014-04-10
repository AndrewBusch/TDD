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
 * @author Randy Acheson & Andrew Busch
 */
public class CityCameraPlanner
{
	Collection<String> cameras;
	/**
	 * the city in node format
	 */
	private Collection<Node> rNodes;
	
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
	
	
	/**
	 * Fills out the camera array with the names of neighbors that should have cameras
	 */
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

	/**
	 * Checks if a given node needs a camera or not
	 * @param mainNode the node that will be checked for needing a camera
	 * @return true if the node will need a camera, false if it does not
	 */
	private boolean checkForCamera(Node mainNode) {
		Node currentNode = mainNode.getNeighbors().iterator().next();
		Collection<Node> visited = new HashSet<Node>();
		return checkNeighbors(mainNode, currentNode, visited);
	}
	
	/**
	 * checks the neighbors of an original node to see if the node needs a camera
	 * @param mainNode the node to be given a camera or not
	 * @param currentNode a node that is a neighbor of mainNode used to determine if a path can be created
	 * from currentNode to every node in the graph without going through mainNode
	 * @param visited collection of nodes that have already been added to the path
	 * @return true if mainNode needs a camera, false if mainNode does not need a camera
	 */
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
	
	/**
	 * assigns the appropriate neighbors to the nodes in rNode
	 * @param roads the city used to create figure out correct neighbors
	 */
	private void fillNodeCity(Collection<Road> roads){
		for(Node N : rNodes) {
			for(Road R : roads) {
				decideNeighbors(N, R);
			}
		}
	}

	/**
	 * Decides if any of the nodes in road R need to be added to N a neighbor
	 * @param N the node being filled out and given neighbors
	 * @param R the road that may have its neighbors added as nodes
	 */
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

	/**
	 * creates a unique node for every neighbor in the city, excluding duplicates
	 * @param roads the city from which neighbors will be turned into nodes
	 */
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

	/**
	 * @param newNode checks if rNode contains a node with the same name as newNode
	 * @return true if rNode does contain a node with the same name as newNode, and
	 * false if it does not
	 */
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
