package connected.city;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityRoutes {
	
	private Logger log = LoggerFactory.getLogger(CityRoutes.class);
	
	private Map<String, LinkedHashSet<String>> map = new HashMap<>();
	private static boolean flag;
	private boolean found;

	public void addEdge(String node1, String node2) {
		LinkedHashSet<String> adjacent = map.get(node1);
		if (adjacent == null) {
			adjacent = new LinkedHashSet<>();
			map.put(node1, adjacent);
		}
		adjacent.add(node2);
	}

	public void addTwoWayVertex(String node1, String node2) {
		addEdge(node1, node2);
		addEdge(node2, node1);
	}

	public boolean isConnected(String node1, String node2) {
		Set<String> adjacent = map.get(node1);
		if (adjacent == null) {
			return false;
		}
		return adjacent.contains(node2);
	}

	public LinkedList<String> adjacentNodes(String last) {
		LinkedHashSet<String> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<>();
		}
		return new LinkedList<String>(adjacent);
	}
	
	public boolean isRouteAvailable(String origin, String destination) {
		this.found = false;
		LinkedList<String> visited = new LinkedList<>();
		visited.add(origin);
		breadthFirst(origin, destination, this, visited);
		return this.found;
	}

	private void breadthFirst(String origin, String destination, 
			CityRoutes graph, LinkedList<String> visited) {
		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

		for (String node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(destination)) {
				visited.add(node);
				printPath(origin, destination, visited);
				flag = true;
				this.found = true;
				visited.removeLast();
				break;
			}
		}

		for (String node : nodes) {
			if (visited.contains(node) || node.equals(destination)) {
				continue;
			}
			visited.addLast(node);
			breadthFirst(origin, destination, graph, visited);
			visited.removeLast();
		}
		
		if (flag == false) {
			log.info("No path Exists between {} and {}", origin, destination);
			flag = true;
		}

	}

	private void printPath(String origin, String destination, LinkedList<String> visited) {
		if (flag == false)
			log.info("Yes there exists a path between {} and {} ", origin, destination);
		for (String node : visited) {
			log.info(node + " ");
		}
	}
}