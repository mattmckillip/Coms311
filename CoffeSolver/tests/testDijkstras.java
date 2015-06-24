package homework7;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class testDijkstras {

	static int sID;
	static int v1ID;
	static int v2ID;
	static int v3ID;
	static int v4ID;
	
	static int edge1;
	static int edge2;
	static int edge3;
	static int edge4;
	static int edge5;
	static int edge6;
	static int edge7;
	
	static MattsGraph graph;
	static MattsDijkstra dijkstra;
	static MattWeighing weighing; 
	
	double cost;
	List<Integer> set;
	List<Integer> expectedSet = new ArrayList<Integer>();
	
	@BeforeClass
    public static void init() {
		graph = new MattsGraph();
		dijkstra = new MattsDijkstra();
		weighing = new MattWeighing();
		
		sID  = graph.addVertex("S");
		v1ID = graph.addVertex("V1");
		v2ID = graph.addVertex("V2");
		v3ID = graph.addVertex("V3");
		v4ID = graph.addVertex("V4");
		
		edge1 = graph.addEdge(sID, v1ID, 2.0);
		edge2 = graph.addEdge(sID, v3ID, 5.0);
		edge3 = graph.addEdge(v1ID, v3ID, 4.0);
		edge4 = graph.addEdge(v1ID, v2ID, 6.0);
		edge5 = graph.addEdge(v3ID, v2ID, 2.0);
		edge6 = graph.addEdge(v3ID, v4ID, 5.0);
		edge7 = graph.addEdge(v2ID, v4ID, 1.0);
		
		//TODO NULL POINTER STUFF

		
		dijkstra.setGraph(graph);
		dijkstra.setWeighing(weighing);
		dijkstra.setStart(sID);
		
		dijkstra.computeShortestPath();
		

    }
	
	@Test
	public void testCostV1() {
		cost = dijkstra.getCost(v1ID);
		assertEquals(true, 2.0 == cost);	
	}
	
	@Test
	public void testCostV2() {	
		cost = dijkstra.getCost(v2ID);
		assertEquals(true, 7.0 == cost);			
	}
	
	@Test
	public void testCostV3() {	
		cost = dijkstra.getCost(v3ID);
		assertEquals(true, 5.0 == cost);		
	}
	
	@Test
	public void testCostV4() {
		cost = dijkstra.getCost(v4ID);
		assertEquals(true, 8.0 == cost);		
	}
	
	
	@Test
	public void testCostVS() {
		cost = dijkstra.getCost(sID);
		assertEquals(true, 0.0 == cost);
	}
	
	
	
	
	
	
	
	@Test
	public void testPathV1() {
		set = dijkstra.getPath(v1ID);
		expectedSet.add(sID);
		expectedSet.add(v1ID);
		assertEquals(true, expectedSet.equals(set));	
	}
	
	@Test
	public void testPathV2() {	
		set = dijkstra.getPath(v2ID);
		expectedSet.clear();
		expectedSet.add(sID);
		expectedSet.add(v3ID);
		expectedSet.add(v2ID);
		assertEquals(true, expectedSet.equals(set));			
	}
	
	@Test
	public void testPathV3() {	
		set = dijkstra.getPath(v3ID);
		expectedSet.clear();
		expectedSet.add(sID);
		expectedSet.add(v3ID);
		assertEquals(true, expectedSet.equals(set));		
	}
	
	@Test
	public void testPathV4() {
		set = dijkstra.getPath(v4ID);
		expectedSet.clear();
		expectedSet.add(sID);
		expectedSet.add(v3ID);
		expectedSet.add(v2ID);
		expectedSet.add(v4ID);
		assertEquals(true, expectedSet.equals(set));		
	}
	
	
	@Test
	public void testPathS() {
		set = dijkstra.getPath(sID);
		expectedSet.clear();
		expectedSet.add(sID);
		assertEquals(true, expectedSet.equals(set));
	}
	
	
	
	
	

}
