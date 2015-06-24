package homework7;
/**
 * @author - MM
 * @date - 4/28/15
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class MattsCoffeeSolver implements CoffeeSolver{
	Stack<Integer> stack = new Stack<Integer>();
	Set<Vertex> vertices = new HashSet<Vertex>();
	
	/**
	 * Sorts the vertices of the graph in a topological fashion.
	 * @param graph An arbitrary directed graph
	 * @return An ordered topological list of vertex IDs of the graph or NULL if
	 * a topological sort does not exist.
	 */
	@Override
	public List sortVertices(Graph graph) {
		// initialize
		stack.clear();
		
		// get all of the vertices of the graph
		Set<Integer> verticesID = graph.getVertices();
		
		boolean isStart = true;
		int startID = 0;
		for (int id : verticesID){
			vertices.add(new Vertex(graph.getData(id), id));
			if (isStart){
				startID = id;
				isStart = false;
			}
		}
		
		int numVertices = vertices.size();	
		
		// run topological sort
		Vertex start = null;
		for (Vertex s : vertices){
			if (s.getID() == startID){
				start = s;
				break;
			}		}
		dfs(start, graph);
		
		// create array list of the tsort
		List<Integer> ret = new ArrayList<Integer>();
		while(!stack.empty()){
			ret.add(stack.pop());
		}
		
		// only add to valid sort if the sort includes all vertices
		// TODO handle vertices with no edges
		if(ret.size() == numVertices){
			return ret;
		}	
		return null;
	}
	
	/**
	 * Computes the shortest path that visits all the locations in
	 * the graph in the order specified.
	 * @param graph An arbitrary directed graph
	 * @param locations An ordered list of vertex ID locations to be visited.
	 * @param weigh A object to determine the weight of each edge in the graph
	 * @return An ordered list of vertex IDs representing the shortest path
	 * that visits all the locations in the given order
	 */
	@Override
	public List shortestPath(Graph graph, List locations, Weighing weigh) {
		MattsDijkstra dijkstra = new MattsDijkstra();
		dijkstra.setGraph(graph);
		dijkstra.setWeighing(weigh);

		ArrayList<Integer> path = new ArrayList<Integer>();
		
		// set the start
		dijkstra.setStart((int) locations.get(0));
		
		ArrayList<Integer> intPath = new ArrayList<Integer>();

		// find the shortest path
		for (int id : (ArrayList<Integer>)locations){
			// find the shortest path from one location to the next
			dijkstra.computeShortestPath();

			// shortest path from start to end
			intPath = (ArrayList<Integer>) dijkstra.getPath(id);
			
			// set start id for next iteration
			dijkstra.setStart(id);

			// add intermediate path to total path
			for( int i : intPath){
				path.add(i);
			}	
		}
		return path;
	}

	/**
	 * Computes all possible topological sorts of the graph.
	 * @param graph An arbitrary directed graph
	 * @return A collection of all possible topological sorts of the graph.
	 * Note that if a topological sort does not exist, it returns an
	 * empty collection.
	 */
	@Override
	public Collection generateValidSortS(Graph graph) {
		return null;
	}
	
	/**
	 * Private helper method that will push the topological sort onto stack
	 * @param v start vertex
	 * @param graph graph to sort
	 */
	private void dfs(Vertex v, Graph graph) {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		//mark v visited
		v.setVisited(true);
		
		Set<Integer> outgoingEdges = graph.getEdgesOf(v.getID());
		//System.out.println(outgoingEdges);
		for (int id : outgoingEdges )
		{
			int target = graph.getTarget(id);
			Vertex w = null;
			for (Vertex vert : vertices){
				if (vert.getID() == target){
					w = vert;
					break;
				}
			}
			//Vertex w = graph.getVertex(target);

			//if w is unvisited then dfs(w)
			if(!w.getVisited()) {
				dfs(w, graph);
			}
		}
		//push(v) into STACK
		stack.push(v.getID());
	}
	
	/**private void setVisitedToFalse(MattsGraph graph){
		Set<Integer> vertices = graph.getVertices();
		for(int vertexID : vertices){
			graph.getVertex(vertexID).setVisited(false);
		}
	}*/
}
