package homework7;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class testMattsCoffeeSolver {

	static int a;
	static int b;
	static int c;
	static int d;
	static int e;
	
	static int edge1;
	static int edge2;
	static int edge3;
	static int edge4;
	static int edge5;
	static int edge6;
	
	static MattsGraph graph;
	static MattsDijkstra dijkstra;
	static MattWeighing weighing; 
	static MattsCoffeeSolver cSolver;
	
	double cost;
	List<Integer> set;
	List<Integer> expectedSet = new ArrayList<Integer>();
	
	@BeforeClass
    public static void init() {
		graph = new MattsGraph();
		dijkstra = new MattsDijkstra();
		weighing = new MattWeighing();
		cSolver = new MattsCoffeeSolver();
		
		a  = graph.addVertex("a");
		b = graph.addVertex("b");
		c = graph.addVertex("c");
		d = graph.addVertex("d");
		e = graph.addVertex("e");
		
		edge1 = graph.addEdge(a, b, 1.0);
		edge2 = graph.addEdge(a, d, 1.0);
		edge3 = graph.addEdge(b, c, 1.0);
		edge4 = graph.addEdge(c, d, 1.0);
		edge5 = graph.addEdge(c, e, 1.0);
		edge6 = graph.addEdge(d, e, 1.0);
		
		
		dijkstra.setGraph(graph);
		dijkstra.setWeighing(weighing);
		dijkstra.setStart(a);
    }
	
	@Test
	public void testCostV1() {
		set = cSolver.sortVertices(graph);
		expectedSet.add(a);
		expectedSet.add(b);
		expectedSet.add(c);
		expectedSet.add(d);
		expectedSet.add(e);
		assertEquals(expectedSet,set);	
	}
}
