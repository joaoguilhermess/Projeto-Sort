public class Tree extends Chronometer {
	private long amount;
	private long comparisons;

	Node root;

	public Tree() {
		this.amount = 0;
		this.comparisons = 0;
	}

	public long getAmount() {
		return this.amount;
	}

	public long getComparisons() {
		return this.comparisons;
	}

	public void add(Report report) {
		this.amount += 1;
		
		this.root = this.recursiveAdd(this.root, report);
	}

	public int getHeight() {
		return this.getHeight(this.root);
	}

	public int getHeight(Node node) {
		if (node == null) {
			return 0;
		}

		return node.height;
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

		return root;
	}

	private Report recursiveSearch(Node root, String value) {
		if (root == null) return null;

		int comparison = root.report.getNOME().compareTo(value);

		this.comparisons += 1;

		if (comparison == 0) {
			return root.report;
		} else if (comparison < 0) {
			return this.recursiveSearch(root.left, value);
		} else {
			return this.recursiveSearch(root.right, value);
		}
	}

	public Report search(String value) {
		this.start();

		Report report = this.recursiveSearch(this.root, value);

		this.stop();

		return report;
	}
}