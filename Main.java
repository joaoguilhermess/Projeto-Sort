import java.io.FileWriter;
import java.io.File;
import java.util.List;

public class Main {
	private static String path = "./data/sisu 2023.csv";
	private static int limit = 0;
	private static File file;
	private static FileWriter writer;

	public static void main(String[] args) {
		int[] amounts = {
			1_000,
			10_000,
			50_000,
			100_000,
			200_000,
			300_000,
			400_000,
			500_000,
			1_000_000,
			2_000_000,
			0
		};

		try {
			file = new File("./data/results.csv");

			writer = new FileWriter(file, true);

			if (!file.exists()) {
				writer.append(
					"Método" +
					";" +
					"Quantidade" +
					";" +
					"Trocas" +
					";" +
					"Comparações" +
					";" +
					"Duração" +
					";" +
					"Duração MS" +
					"\n"
				);
			}

			for (int i = 0; i < amounts.length; i++) {
				limit = amounts[i];

				// bubbleSort();
				// selectSort();
				// insertSort();
				// mergeSort();

				// mergeSort();
				// heapSort();
				// quickSort();

				sequentialSearch();
				binarySearch();

				tree();
				balancedTree();
			}

			writer.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void bubbleSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.bubbleSort();

		printResults("bubbleSort", sort);
	}

	private static void selectSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.selectSort();

		printResults("selectSort", sort);
	}

	private static void insertSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.insertSort();

		printResults("insertSort", sort);
	}

	private static void mergeSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.mergeSort();

		printResults("mergeSort", sort);
	}

	private static void heapSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.heapSort();

		printResults("heapSort", sort);
	}

	private static void quickSort() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.quickSort();

		printResults("quickSort", sort);
	}

	private static void sequentialSearch() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.mergeSort();

		Search search = new Search(list);

		Report result = search.sequentialSearch("IVES RAYLLAN DO NASCIMENTO SOUZA");

		printResults("sequentialSearch", search);

		if (result != null) {
			System.out.println(result.getNOME());
			System.out.println(result.getNOTA_FINAL());
		} else {
			System.out.println("Não Encontrado");
		}
	
		System.out.println("");
	}

	private static void binarySearch() {
		List<Report> list = Report.readReportsList(path, limit);

		Sort sort = new Sort(list);

		sort.mergeSort();

		Search search = new Search(list);

		Report result = search.binarySearch("IVES RAYLLAN DO NASCIMENTO SOUZA");

		printResults("binarySearch", search);

		if (result != null) {
			System.out.println(result.getNOME());
			System.out.println(result.getNOTA_FINAL());
		} else {
			System.out.println("Não Encontrado");
		}
	
		System.out.println("");
	}

	private static void tree() {
		Tree tree = Report.readReportsTree(path, limit);

		Report result = tree.search("IVES RAYLLAN DO NASCIMENTO SOUZA");

		printResults("tree", tree);

		if (result != null) {
			System.out.println(result.getNOME());
			System.out.println(result.getNOTA_FINAL());
		} else {
			System.out.println("Não Encontrado");
		}
	
		System.out.println("");
	}

	private static void balancedTree() {
		BalancedTree tree = Report.readReportsBalancedTree(path, limit);

		Report result = tree.search("IVES RAYLLAN DO NASCIMENTO SOUZA");

		printResults("balancedTree", tree);

		if (result != null) {
			System.out.println(result.getNOME());
			System.out.println(result.getNOTA_FINAL());
		} else {
			System.out.println("Não Encontrado");
		}
	
		System.out.println("");
	}

	private static void writeResults(String method, BalancedTree balancedTree) {
		try {
			writer.append(
				method +
				";" +
				Util.formatNumber(balancedTree.getAmount()) +
				";" +
				"" +
				";" +
				Util.formatNumber(balancedTree.getComparisons()) +
				";" +
				Util.formatTime(balancedTree.getDuration()) +
				";" +
				balancedTree.getDuration() +
				"\n"
			);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void writeResults(String method, Tree tree) {
		try {
			writer.append(
				method +
				";" +
				Util.formatNumber(tree.getAmount()) +
				";" +
				"" +
				";" +
				Util.formatNumber(tree.getComparisons()) +
				";" +
				Util.formatTime(tree.getDuration()) +
				";" +
				tree.getDuration() +
				"\n"
			);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void writeResults(String method, Search search) {
		try {
			writer.append(
				method +
				";" +
				Util.formatNumber(search.getAmount()) +
				";" +
				"" +
				";" +
				Util.formatNumber(search.getComparisons()) +
				";" +
				Util.formatTime(search.getDuration()) +
				";" +
				search.getDuration() +
				"\n"
			);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void writeResults(String method, Sort sort) {
		try {
			writer.append(
				method +
				";" +
				Util.formatNumber(sort.getAmount()) +
				";" +
				Util.formatNumber(sort.getSwaps()) +
				";" +
				Util.formatNumber(sort.getComparisons()) +
				";" +
				Util.formatTime(sort.getDuration()) +
				";" +
				sort.getDuration() +
				"\n"
			);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void printResults(String method, BalancedTree balancedTree) {
		System.out.println("Método: " + method);
		System.out.println("Comparações: " + Util.formatNumber(balancedTree.getComparisons()));
		System.out.println("Duração: " + Util.formatTime(balancedTree.getDuration()));
		System.out.println();

		writeResults(method, balancedTree);
	}

	private static void printResults(String method, Tree tree) {
		System.out.println("Método: " + method);
		System.out.println("Comparações: " + Util.formatNumber(tree.getComparisons()));
		System.out.println("Duração: " + Util.formatTime(tree.getDuration()));
		System.out.println();

		writeResults(method, tree);
	}

	private static void printResults(String method, Search search) {
		System.out.println("Método: " + method);
		System.out.println("Comparações: " + Util.formatNumber(search.getComparisons()));
		System.out.println("Duração: " + Util.formatTime(search.getDuration()));
		System.out.println();

		writeResults(method, search);
	}

	private static void printResults(String method, Sort sort) {
		System.out.println("Método: " + method);
		System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
		System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
		System.out.println("Duração: " + Util.formatTime(sort.getDuration()));
		System.out.println();

		writeResults(method, sort);
	}
}