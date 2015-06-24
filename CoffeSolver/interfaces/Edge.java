package homework7;

public class Edge {
	private int ID;
	private double attributes;
	private int source;
	private int target;
	
	public Edge(Object attr, int srcID, int targetID, int edgeID) {
		attributes = (double) attr;
		source = srcID;
		target = targetID;
		ID = edgeID;
	}
	

	public int getID(){
		return ID;
	}
	
	public Object getAttributes(){
		return attributes;
	}

	public int getSource(){
		return source;
	}
	
	public int getTarget(){
		return target;
	}
}
