public class BalancedTree extends Chronometer {
	private class Node {
		Report report;
		Node left;
		Node right;
		int height;

		Node(Report report) {
			this.report = report;
			
			this.left = null;
			this.right = null;

			this.height = 1;
		}
	}

	Node root;

	public void add(Report report) {
		this.root = this.recursiveAdd(this.root, report);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}

		return node.height;
	}

	private int getBalance(Node node) {
		if (node == null) {
			return 0;
		}

		return this.getHeight(node.left) - this.getHeight(node.right);
	}

	private Node rotateLeft(Node nodeA) {
		if (nodeA == null || nodeA.right == null) return nodeA;

		Node nodeB = nodeA.right;
		Node nodeT = nodeB.left;

		nodeA.right = nodeT;
		nodeB.left = nodeA;

		nodeA.height = Math.max(this.getHeight(nodeA.left), this.getHeight(nodeA.right)) + 1;
		nodeB.height = Math.max(this.getHeight(nodeB.left), this.getHeight(nodeB.right)) + 1;

		return nodeB;	
	}

	private Node rotateRight(Node nodeA) {
		if (nodeA == null || nodeA.left == null) return nodeA;

		Node nodeB = nodeA.left;
		Node nodeT = nodeB.right;

		nodeA.left = nodeT;
		nodeB.right = nodeA;

		nodeA.height = Math.max(this.getHeight(nodeA.left), this.getHeight(nodeA.right)) + 1;
		nodeB.height = Math.max(this.getHeight(nodeB.left), this.getHeight(nodeB.right)) + 1;

		return nodeB;
	}

	public Node recursiveAdd(Node root, Report report) {
		if (root == null) return new Node(report);

		int comparison = root.report.getNOME().compareTo(report.getNOME());

		if (comparison <= 0) {
			root.left = this.recursiveAdd(root.left, report);
		} else {
			root.right = this.recursiveAdd(root.right, report);
		}

		root.height = Math.max(this.getHeight(root.left), this.getHeight(root.right)) + 1;

		int balance = this.getBalance(root);

		if (balance > 1) {
			if (report.getNOME().compareTo(root.left.report.getNOME()) > 0) {
				root.left = this.rotateLeft(root.left);
			}

			return this.rotateRight(root);
		}

		if (balance < -1) {
			if (report.getNOME().compareTo(root.right.report.getNOME()) <= 0) {
				root.right = this.rotateRight(root.right);
			}

			return this.rotateLeft(root);
		}

		return root;
	}

	private Report recursiveSearch(Node root, String value) {
		if (root == null) return null;

		int comparison = root.report.getNOME().compareTo(value);

		if (comparison == 0) {
			return root.report;
		} else if (comparison < 0) {
			return this.recursiveSearch(root.left, value);
		} else {
			return this.recursiveSearch(root.right, value);
		}
	}

	public Report search(String value) {
		return this.recursiveSearch(this.root, value);
	}
}