import java.util.*;

import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;

public class CFG {
	Set<Node> nodes = new HashSet<Node>();
	Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();

	static class Node {
		int position;
		MethodNode method;
		ClassNode clazz;

		Node(int p, MethodNode m, ClassNode c) {
			position = p; method = m; clazz = c;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Node)) return false;
			Node n = (Node)o;
			return (position == n.position) &&
					method.equals(n.method) && clazz.equals(n.clazz);
		}

		public int hashCode() {
			return position + method.hashCode() + clazz.hashCode();
		}

		public String toString() {
			return clazz.name + "." +
					method.name + method.signature + ": " + position;
		}
	}

	public void addNode(int p, MethodNode m, ClassNode c) {
		// Create a new Node instance with the given parameters
		Node newNode = new Node(p, m, c);
		// Check if the nodes set already contains an equivalent node
		if (!nodes.contains(newNode)) {
			nodes.add(newNode);
			// Also initialize an entry in the edges map for the new node
			edges.put(newNode, new HashSet<Node>());
		}
	}

	public void addEdge(int p1, MethodNode m1, ClassNode c1,
						int p2, MethodNode m2, ClassNode c2) {
		// Create new Node instances for the given parameters
		Node newNode1 = new Node(p1, m1, c1);
		Node newNode2 = new Node(p2, m2, c2);

		// If node1 does not exist in the nodes set, add it and initialize its entry in the edges map
		if (!nodes.contains(newNode1)) {
			nodes.add(newNode1);
			edges.put(newNode1, new HashSet<Node>());
		}

		// If node2 does not exist in the nodes set, add it and initialize its entry in the edges map
		if (!nodes.contains(newNode2)) {
			nodes.add(newNode2);
			edges.put(newNode2, new HashSet<Node>());
		}

		// Add an edge from node1 to node2 by adding node2 to the set of node1's neighbors
		edges.get(newNode1).add(newNode2);
	}

	public void deleteNode(int p, MethodNode m, ClassNode c) {
		// Create a new Node instance with the given parameters
		Node nodeToBeDeleted = new Node (p, m , c);

		// Check if the nodes set contains the node to be deleted
		if (nodes.contains(nodeToBeDeleted)) {
			// If the node exists, remove it from the nodes set
			nodes.remove(nodeToBeDeleted);
			// Also remove its entry from the edges map
			edges.remove(nodeToBeDeleted);
			// Iterate over all entries in the edges map and remove any edges that connect other nodes to the node being deleted
			for (Set<Node> nodeSet : edges.values()) {
				nodeSet.remove(nodeToBeDeleted);
			}
		}
	}

	public void deleteEdge(int p1, MethodNode m1, ClassNode c1,
						   int p2, MethodNode m2, ClassNode c2) {
		// Create new Node instances for the given parameters
		Node newNode1 = new Node(p1, m1, c1);
		Node newNode2 = new Node(p2, m2, c2);

		// Get the set of neighbors of node1 from the edges map
		Set<Node> node1Neighbors = edges.get(newNode1);

		// If node1 exists in the graph and has an edge to node2, remove the edge
		if  (node1Neighbors != null && node1Neighbors.contains(newNode2)) {
			node1Neighbors.remove(newNode2);
		}
	}


	public boolean isReachable(int p1, MethodNode m1, ClassNode c1,
							   int p2, MethodNode m2, ClassNode c2) {
		// Create new Node instances for the given parameters
		Node start = new Node(p1, m1, c1);
		Node end = new Node(p2, m2, c2);

		// If either the start node or the end node is not in the graph, return false
		if (!nodes.contains(start) || !nodes.contains(end)) {
			return false;
		}

		// Initialize a queue to perform BFS and add the start node to it
		Queue<Node> queue = new LinkedList<>();
		queue.add(start);

		// Initialize a set to keep track of visited nodes and mark the start node as visited
		Set<Node> visited = new HashSet<>();
		visited.add(start);

		while (!queue.isEmpty()) {
			// Dequeue a node from the queue
			Node currentNode = queue.poll();

			// If the current node is the end node, return true
			if (currentNode.equals(end)) {
				return true;
			}

			// Get the neighbors of the current node
			Set<Node> nodeSet = edges.get(currentNode);

			// If the current node has neighbors, add them to the queue if they haven't been visited yet
			if (nodeSet != null) {
				for (Node node : nodeSet) {
					if (!visited.contains(node)) {
						queue.add(node);
						visited.add(node);
					}
				}
			}
		}
		return false;
	}
}
