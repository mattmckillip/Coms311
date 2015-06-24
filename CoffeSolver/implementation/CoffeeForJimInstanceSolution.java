package homework7;
/**
 * @author - MM
 * @date - 4/28/15
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeForJimInstanceSolution {
	
	static MattsCoffeeSolver cSolver;
	
	/**
	 * Solves the jims coffee instance on hw7
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize classes
		MattsGraph graph = new MattsGraph();
		MattsDijkstra dijkstra = new MattsDijkstra();
		MattWeighing weighing = new MattWeighing();
		cSolver = new MattsCoffeeSolver();
			
		// create a graph to find the ordering, Note the vertex data is the corresponding ames id
		int you = graph.addVertex(2893);
		int a   = graph.addVertex(1055);
		int b   = graph.addVertex(371);
		int c   = graph.addVertex(2874);
		int d   = graph.addVertex(2351);
		int e   = graph.addVertex(2956);
		int f   = graph.addVertex(1171);
		int g   = graph.addVertex(1208);
		graph.addEdge(you, a, 1.0);
		graph.addEdge(you, b, 1.0);
		graph.addEdge(you, c, 1.0);
		graph.addEdge(you, d, 1.0);	
		graph.addEdge(you, e, 1.0);
		graph.addEdge(you, f, 1.0);
		graph.addEdge(you, g, 1.0);
		graph.addEdge(a, b, 1.0);
		graph.addEdge(a, e, 1.0);
		graph.addEdge(b, c, 1.0);
		graph.addEdge(b, d, 1.0);	
		graph.addEdge(d, e, 1.0);
		graph.addEdge(d, f, 1.0);
		graph.addEdge(a, g, 1.0);
		graph.addEdge(b, g, 1.0);
		graph.addEdge(c, g, 1.0);
		graph.addEdge(d, g, 1.0);
		graph.addEdge(e, g, 1.0);
		graph.addEdge(f, g, 1.0);
					
		
		
		// This may need to be changed depending on how this is run
		String filename = System.getProperty("user.dir") + "\\src\\homework7\\AmesData.txt";
		
		System.out.println(filename);
		
		ArrayList<Integer> order = sortIngredients(graph);
		System.out.println("Valid Ordering: " + order);
		
		// Helper method to open ames file data
		MattsGraph amesGraph = openAmesData(filename);
		
		// now find the shortest path based on topological order
		ArrayList<Integer> path = (ArrayList<Integer>) cSolver.shortestPath(amesGraph, order, weighing);
		System.out.println("Shortest Path based on ordering: " + path);	
	}
	
	/**
	 * Helper method saves the topological sort by vertex data, in our case its the ames ids
	 * @param graph graph to topologically sort
	 * @return ArrayList<Integer> containing ames ids in order
	 */
	public static ArrayList<Integer> sortIngredients(MattsGraph graph){
		// topological sort based on internal ids
		ArrayList<Integer> sortedIngredientsInts = (ArrayList<Integer>) cSolver.sortVertices(graph);
		
		// we care about the data passed inside the vertex (ames id's)
		ArrayList<Integer> sortedIngredientsIDs = new ArrayList<Integer>();
		for (int vertexID: sortedIngredientsInts){
			sortedIngredientsIDs.add((Integer) graph.getData(vertexID));
		}
		
		// arraylist of ames IDs sorted topologically
		return sortedIngredientsIDs;
	}
	
	/**
	 * Given a filename, this method will output a graph populated with the ames data
	 * @param filename ames data file
	 * @return graph representing ames data
	 */
	public static MattsGraph openAmesData(String filename){
		MattsGraph amesGraph = new MattsGraph();        

		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			int numVertices = 0;
			int counter = 0;
			Scanner scanner;
			while ((line = reader.readLine()) != null){
				if (counter == 0){
					scanner = new Scanner(line);
					scanner.useDelimiter(": ");
					scanner.next();
					numVertices = scanner.nextInt();
				}
				else if(counter <=  numVertices){//numVertices){
					scanner = new Scanner(line);
					scanner.useDelimiter(",");
					if (scanner.hasNext()){
						int id = scanner.nextInt();
						double x = scanner.nextDouble();
						double y = scanner.nextDouble();
						
						// TODO do we care about the location?
						ArrayList<Double> pair = new ArrayList<Double>();
						pair.add(x);
						pair.add(y);
						
						amesGraph.addVertex(id);
					}
				}
				else if(counter >  numVertices + 1){
					//System.out.println(line);
					scanner = new Scanner(line);
					scanner.useDelimiter(",");
					if (scanner.hasNext()){
						int sourceID = scanner.nextInt();
						int targetID = scanner.nextInt();
						double weight = scanner.nextDouble();
						
						//TODO do i care about street name?
						if (scanner.hasNext()){
							String name = scanner.next();
						}
						
						amesGraph.addEdge(sourceID, targetID, weight);
					}
					
				}				
				counter++;
			}
			reader.close();
			
			return amesGraph;
		}
		catch (Exception e){
			System.err.format("Exception occurred trying to read '%s'. Make sure that AmesData.txt is in the correct spot", filename);
			e.printStackTrace();
			return null;
		}

	}

}
