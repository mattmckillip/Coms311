package homework7;
/**
 * @author - MM
 * @date - 4/28/15
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class MattsDijkstra implements Dijkstra<Object, Object>{
	Weighing weighing;
	Graph graph;
	int startId;
	
	Set<Vertex> vertices = new HashSet<Vertex>();
	
	/**
	 * Sets the graph to use in computation
	 */
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
		
		Set<Integer> verticesID = graph.getVertices();
		for (int id : verticesID){
			vertices.add(new Vertex(graph.getData(id), id));
		}
	}

	/**
	 * Sets the start vertex the algorithm will use in computation
	 * @throws IllegalArgumentException if the start vertex does not exist in the graph
	 * @throws IllegalStateException if no graph has been set
	 */
	@Override
	public void setStart(int startId) throws IllegalArgumentException,
			IllegalStateException {
		this.startId = startId;
		
	}

	/**
	 * Sets the weighing to be used in computing the cost of traversing an edge
	 */
	@Override
	public void setWeighing(Weighing weighing) {
		this.weighing = weighing;
	}

	/**
	 * Computes the shortest path to all vertices from the start vertex in the graph
	 * using the weighing function
	 * @throws IllegalStateException if the graph, start vertex, or weighing object have not been set
	 */
	@Override
	public void computeShortestPath() throws IllegalStateException {
		Vertex start = null;
		
		for(Vertex v : vertices){
			if (v.getID() == startId){
				start = v;
			}
			
			// reset graph
			v.setPred(Integer.MAX_VALUE);
			v.setDistance(Integer.MAX_VALUE);
			v.setVisited(false);
		}
		
		start.setDistance(0);
		
		Queue<Vertex> nodeQueue = new LinkedList<Vertex>();
	    
	    nodeQueue.add(start);

		while (!nodeQueue.isEmpty()) {
			Vertex u = (Vertex) nodeQueue.poll();
						
			// Visit each edge exiting u
			Set<Integer> outgoingEdges = graph.getEdgesOf(u.getID());

			for (int id : outgoingEdges )
			{	
				
				int target = graph.getTarget(id);
				
				Vertex v = null;
				for(Vertex vert : vertices){
					if (vert.getID() == target){
						v = vert;
						break;
					}
				}
							
				double weight = weighing.weight(graph.getAttribute(id));
				double distanceThroughU = u.getDistance() + weight;
				
				if (distanceThroughU < v.getDistance()) {
					nodeQueue.remove(v);
					v.setDistance(distanceThroughU);
					v.setPred(u.getID());
					nodeQueue.add(v);
				}
	       }
		}
		nodeQueue.clear();
	}

	/**
	 * Returns the path from the start vertex to the end vertex provided
	 * @return a list representing the shortest path. The first element is the start vertex, and the last
	 *         is the end vertex.
	 * @throws IllegalArgumentException if endId is not a vertex in the graph
	 * @throws IllegalStateException if computeShortestPath has not been called since any of the set methods
	 *                               were last called
	 */
	@Override
	public List getPath(int endId) throws IllegalArgumentException,
			IllegalStateException {
		// TODO validate input
		List<Integer> path = new ArrayList<Integer>();
		Vertex end = null;
		
		// find the end vertex
		for (Vertex v : vertices){
			if (v.getID()== endId){
				end = v;
				break;
			}
		}
		
		// Did not find end
		if (end == null){
			return null;
		}
		
		// add the end vertex to the path
		path.add(end.getID());
		
		// loop until we find the start node
		Vertex node = end;
        while (node.getID() != startId){
        	for (Vertex v : vertices){
    			if (v.getID() == node.getPred()){
    				node = v;
    				break;
    			}
    		}
        	// add the node
        	path.add(node.getID());
        }
            
        // reverse path to get from start to end
        Collections.reverse(path);
        return path;
	}	

	/**
	 * Returns the cost of the shortest path from the start vertex to the end vertex where
	 * cost is defined by the sum of the weights of all the edges that connects the path as
	 * defined by the weighing object.
	 * @throws IllegalArgumentException if endId is not a vertex in the graph
	 * @throws IllegalStateException if computeShortestPath has not been called since any of the set methods
	 *                               were last called
	 */
	@Override
	public double getCost(int endId) throws IllegalArgumentException,
			IllegalStateException {
		// TODO validate input
		List<Integer> path = getPath(endId);

        Set<Integer> edges = graph.getEdges();
        
        double pathCost = 0;
        for (int id=1; id<path.size(); id++){
        	for(int edgeID : edges){
        		if (graph.getSource(edgeID) == path.get(id-1) && graph.getTarget(edgeID) == path.get(id)){
        			pathCost+=weighing.weight(graph.getAttribute(edgeID));
        		}
        	}
        }
        return pathCost;
	}

}
