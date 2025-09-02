public class Tree extends Chronometer {
	private class Node {
		Report report;
		Node left;
		Node right;

		Node(Report report) {
			this.report = report;

			this.left = null;
			this.right = null;
		}
	}

	Node root;

	public void add(Report report) {
		this.root = this.recursiveAdd(this.root, report);
	}

	private Node recursiveAdd(Node root, Report report) {
		if (root == null) return new Node(report);

		int comparison = root.report.getNOME().compareTo(report.getNOME());

		if (comparison <= 0) {
			root.left = this.recursiveAdd(root.left, report);
		} else {
			root.right = this.recursiveAdd(root.right, report);
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