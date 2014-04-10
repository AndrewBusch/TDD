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

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

/**
 * Sample tests for the TDD assignment in CS3733.
 * @version Mar 30, 2014
 * @author gpollice
 */
public class CityCameraPlannerTest
{
	@Test
	public void testNodeName() 
	{
		final Node node = new Node("A");
		assertEquals("A", node.getName());
	}
	
	@Test
	public void testUniqueNodes()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C")	
			};
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}

		final Node nodeA = new Node("A");
		final Node nodeB = new Node("B");

		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city);
		
		int nodeACount = 0;
		int nodeBCount = 0;
		int nodeCCount = 0;
		for (Node node : cameraPlanner.getrNodes())
		{
			if (node.equals(nodeB)) nodeBCount++;
			else if (node.equals(nodeA)) nodeACount++;
			else nodeCCount++;
		}
		
		assertEquals(1, nodeACount);
		assertEquals(1, nodeBCount);
		assertEquals(1, nodeCCount);

		
	}
	
	@Test
	public void testStraightLineOfThree()
	{
		final Road[] roads = {
			new Road("A", "B"), new Road("B", "C")	
		};
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		assertEquals(1, cameraPlanner.getCameras().size());
		assertTrue(cameraPlanner.getCameras().contains("B"));
		assertTrue(cameraPlanner.hasCamera("B"));
		assertFalse(cameraPlanner.hasCamera("A"));
	}

	@Test
	public void testTriangle()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("A", "C")	
			};
			
			Collection<Road> city = new HashSet<Road>();
			for (Road r : roads) {
				city.add(r);
			}
			final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
			assertEquals(0, cameraPlanner.getCameras().size());
			assertFalse(cameraPlanner.getCameras().contains("B"));
	}
	
	@Test
	public void testCurlyQueue()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("C", "D"),
				new Road("D", "A"), new Road("D", "E"), new Road("E", "F")
		};			
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		Collection<String> cameras = cameraPlanner.getCameras();
		
		assertEquals(2, cameraPlanner.getCameras().size());
		assertFalse(cameras.contains("A"));
		assertFalse(cameras.contains("B"));
		assertFalse(cameras.contains("C"));
		assertTrue(cameras.contains("D"));
		assertTrue(cameras.contains("E"));
		assertFalse(cameras.contains("F"));
	}
	
	@Test
	public void testDoubleLoop()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("C", "D"),
				new Road("D", "A"), new Road("D", "E"), new Road("E", "F"),
				new Road("F", "G"), new Road("G", "D")
		};			
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		Collection<String> cameras = cameraPlanner.getCameras();
		
		assertEquals(1, cameraPlanner.getCameras().size());
		assertFalse(cameras.contains("A"));
		assertFalse(cameras.contains("B"));
		assertFalse(cameras.contains("C"));
		assertTrue(cameras.contains("D"));
		assertFalse(cameras.contains("E"));
		assertFalse(cameras.contains("F"));
		assertFalse(cameras.contains("G"));
		assertTrue(cameraPlanner.hasCamera("D"));
		assertFalse(cameraPlanner.hasCamera("G"));
	}
	
	@Test
	public void testPretzel()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("C", "D"),
				new Road("D", "A"), new Road("D", "E"), new Road("E", "F"),
				new Road("F", "G"), new Road("G", "D"), new Road("A", "E")
		};			
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		Collection<String> cameras = cameraPlanner.getCameras();
		
		assertEquals(0, cameraPlanner.getCameras().size());
		assertFalse(cameras.contains("A"));
		assertFalse(cameras.contains("B"));
		assertFalse(cameras.contains("C"));
		assertFalse(cameras.contains("D"));
		assertFalse(cameras.contains("E"));
		assertFalse(cameras.contains("F"));
		assertFalse(cameras.contains("G"));
	}
	
	@Test
	public void testEquals() {
		Road a = new Road("A", "B");
		Road b = new Road("A", "B");
		Road c = new Road("B", "C");
		Road d = new Road("B", null);
		Road e = new Road(null, "G");
		Road f = new Road(null, "G");
		Road g = new Road("B", null);

		assertTrue(a.equals(b));
		assertFalse(b.equals(c));
		assertFalse(d.equals(c));
		assertTrue(f.equals(e));
		assertTrue(g.equals(d));
	}
		
	@Test
	public void testBridgeToTerabithia()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("C", "D"),
				new Road("D", "A"), new Road("D", "E"), new Road("E", "F"),
				new Road("F", "G"), new Road("G", "H"), new Road("H", "E")
		};			
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		Collection<String> cameras = cameraPlanner.getCameras();
		
		assertEquals(2, cameraPlanner.getCameras().size());
		assertFalse(cameras.contains("A"));
		assertFalse(cameras.contains("B"));
		assertFalse(cameras.contains("C"));
		assertTrue(cameras.contains("D"));
		assertTrue(cameras.contains("E"));
		assertFalse(cameras.contains("F"));
		assertFalse(cameras.contains("G"));
		assertFalse(cameras.contains("H"));		
	}
	
	@Test
	public void testStarOfAwesomeness()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("A", "C"), new Road("A", "D"),
				new Road("A", "E"), new Road("A", "F"), new Road("A", "F")
		};			
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		Collection<String> cameras = cameraPlanner.getCameras();
		
		assertEquals(1, cameraPlanner.getCameras().size());
		assertTrue(cameras.contains("A"));
		assertFalse(cameras.contains("B"));
		assertFalse(cameras.contains("C"));
		assertFalse(cameras.contains("D"));
		assertFalse(cameras.contains("E"));
		assertFalse(cameras.contains("F"));		
	}
}
