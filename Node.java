public class Node {
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