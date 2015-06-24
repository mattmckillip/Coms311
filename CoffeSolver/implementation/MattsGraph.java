package homework7;
/**
 * @author - MM
 * @date - 4/28/15
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MattsGraph implements Graph {
	private ArrayList<Vertex> vertexArray = new ArrayList<Vertex>();
	private ArrayList<Edge> edgeArray = new ArrayList<Edge>();
	private int edgeID = 0;
	private int nodeID = 0;
	
	/**
	 * Adds a new vertex to the graph
	 * 
	 * @param v Data to attach to vertex
	 * @return the ID of the vertex created
	 */
	@Override
	public int addVertex(Object v) {
		Vertex tempNode = new Vertex(v, nodeID);
		nodeID++;
		vertexArray.add(tempNode);
		
		return tempNode.getID();
	}

	/**
	 * Adds an edge to the graph.
	 * 
	 * @param srcID The ID of the source vertex
	 * @param targetID The ID of the target vertex
	 * @param attr The attribute of the edge
	 * @return The ID of the edge created
	 * @throws IllegalArgumentException If srcID or targetID are not valid vertices
	 */
	@Override
	public int addEdge(int srcID, int targetID, Object attr)
			throws IllegalArgumentException {

		boolean foundSrc = false;
		boolean foundTarget = false;
		
		Edge tempEdge = new Edge(attr, srcID, targetID, edgeID);
		edgeID++;
		
		// search for the source and the target in the node_array
		for(Vertex n: vertexArray){
			if (n.getID() == srcID){
				foundSrc = true;
			}
			else if (n.getID() == targetID){
				foundTarget = true;
			}
		}
		
		// We have found the source and the target, so add the edge
		if (foundSrc && foundTarget){
			edgeArray.add(tempEdge);
			return tempEdge.getID();
		}
		// We did not find the source or the target, return error
		else{
			return -1;
		}
	}
	
	/**
	 * Returns the vertex-set of the graph
	 */
	@Override
	public Set<Integer> getVertices() {
		Set <Integer> vertices = new HashSet<Integer>();
		for(Vertex n: vertexArray){
			vertices.add(n.getID());
		}
		return vertices;
	}

	
	/**
	 * Returns the edge-set of the graph
	 */
	@Override
	public Set<Integer> getEdges() {
		Set <Integer> edges = new HashSet<Integer>();
		for(Edge e: edgeArray){
			edges.add(e.getID());
		}
		return edges;
	}

	/**
	 * Returns the attribute of the edgeID
	 * @throws IllegalArgumentException if the id is invalid
	 */
	@Override
	public Object getAttribute(int id) throws IllegalArgumentException {
		for(Edge e: edgeArray){
			if (e.getID() == id){
				return e.getAttributes();
			}
		}
		return null;
	}

	/**
	 * Returns the data of the vertexID
	 * @throws IllegalArgumentException if the id is invalid
	 */
	@Override
	public Object getData(int id) throws IllegalArgumentException {
		for(Vertex n: vertexArray){
			if (n.getID() == id){
				return n.getData();
			}
		}
		return null;
	}

	/**
	 * Returns the source of the edge
	 * @throws IllegalArgumentException if the id is invalid
	 */
	@Override
	public int getSource(int id) throws IllegalArgumentException {
		for(Edge e: edgeArray){
			if (e.getID() == id){
				return e.getSource();
			}
		}
		return 0;
	}

	/**
	 * Returns the target of the edge
	 * @throws IllegalArgumentException if the id is invalid
	 */
	@Override
	public int getTarget(int id) throws IllegalArgumentException {
		for(Edge e: edgeArray){
			if (e.getID() == id){
				return e.getTarget();
			}
		}
		return 0;
	}

	/**
	 * Returns the outgoing edges of the vertex
	 * @throws IllegalArgumentException if the id is invalid
	 */
	@Override
	public Set<Integer> getEdgesOf(int id) throws IllegalArgumentException {
		Set <Integer> edges = new HashSet<Integer>();
		for(Edge e: edgeArray){
			if (e.getSource() == id){
				edges.add(e.getID());
			}
		}
		return edges;
	}
}
