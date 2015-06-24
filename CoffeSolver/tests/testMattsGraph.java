package homework7;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class testMattsGraph {
	static int aID;
	static int bID;
	static int cID;
	static int dID;
	static int edge1;
	static int edge2;
	static int edge3;
	static int edge4;
	static int edge5;
	static MattsGraph graph;
	
	@BeforeClass
    public static void init() {
		graph = new MattsGraph();
		
		aID = graph.addVertex("A");
		bID = graph.addVertex("B");
		cID = graph.addVertex("C");
		dID = graph.addVertex("D");
		
		edge1 = graph.addEdge(aID, bID, 1.0);
		edge2 = graph.addEdge(bID, cID, 2.0);
		edge3 = graph.addEdge(cID, dID, 3.0);
		edge4 = graph.addEdge(dID, aID, 4.0);
		edge5 = graph.addEdge(aID, cID, 5.0);
    }
	
	@Test
	public void testVertexIDs() {
		assertEquals(0, aID);
		assertEquals(1, bID);
		assertEquals(2, cID);
		assertEquals(3, dID);
	}
	
	@Test
	public void testEdgeIDs() {
		assertEquals(0, edge1);
		assertEquals(1, edge2);
		assertEquals(2, edge3);
		assertEquals(3, edge4);
	}
	
	@Test
	public void testEdgeSource(){
		assertEquals(aID, graph.getSource(edge1));
		assertEquals(bID, graph.getSource(edge2));
		assertEquals(cID, graph.getSource(edge3));
		assertEquals(dID, graph.getSource(edge4));
	}
	
	@Test
	public void testEdgeTarget(){
		assertEquals(bID, graph.getTarget(edge1));
		assertEquals(cID, graph.getTarget(edge2));
		assertEquals(dID, graph.getTarget(edge3));
		assertEquals(aID, graph.getTarget(edge4));
	}
	
	@Test
	public void testGetAttribute(){
		assertEquals(1.0, graph.getAttribute(edge1));
		assertEquals(2.0, graph.getAttribute(edge2));
		assertEquals(3.0, graph.getAttribute(edge3));
		assertEquals(4.0, graph.getAttribute(edge4));
	}
	
	@Test
	public void testGetData(){
		assertEquals("A", graph.getData(aID));
		assertEquals("B", graph.getData(bID));
		assertEquals("C", graph.getData(cID));
		assertEquals("D", graph.getData(dID));
	}
	
	@Test
	public void testGetEdgesOf(){
		Set<Integer> edges = graph.getEdgesOf(aID);
		for(int i : edges){
			assertEquals(true, edge1==i || edge5==i);
		}
		
		edges = graph.getEdgesOf(bID);
		for(int i : edges){
			assertEquals(edge2, i);
		}
		
		edges = graph.getEdgesOf(cID);
		for(int i : edges){
			assertEquals(edge3, i);
		}
		
		edges = graph.getEdgesOf(dID);
		for(int i : edges){
			assertEquals(edge4, i);
		}
		
	}
	

}
