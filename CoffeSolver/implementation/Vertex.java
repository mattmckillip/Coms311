package homework7;
/**
 * @author - MM
 * @date - 4/28/15
 */
public class Vertex {
	private int ID;
	private Object data;
	private double distance;
	private int pred;
	private boolean visited;
	
	public Vertex(Object v, int nodeID) {
		data = v;
		ID = nodeID;
		distance = Double.MAX_VALUE;
		pred = Integer.MAX_VALUE;
		visited = false;
	}

	public int getID() {
		return ID;
	}
	
	
	public Object getData(){
		return data;
	}
	
	public void setDistance(double distanceThroughU){
		distance = distanceThroughU;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public void setPred(int id){
		pred = id;
	}
	
	public int getPred(){
		return pred;
	}
	
	public void setVisited(boolean v){
		visited = v;
	}
	
	public boolean getVisited(){
		return visited;
	}
}
