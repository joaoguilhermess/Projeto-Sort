import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static String path = "./data/sisu 2023.csv";
	private static int limit = 0;
	private static File file;
	private static FileWriter writer;

	public static void main(String[] args) {
		int[] amounts = {
			400_000,
			500_000,
			600_000,
			700_000,
			800_000,
			900_000,
			1_000_000
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
					"\n"
				);
			}

			for (int i = 0; i < amounts.length; i++) {
				limit = amounts[i];

				bubbleSort();
				selectSort();
				insertSort();
				mergeSort();
			}

			writer.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void bubbleSort() {
		List<Report> list = readInput();

		Sort sort = new Sort(list);

		sort.bubbleSort();

		printResults("bubbleSort", sort);
	}

	public static void selectSort() {
		List<Report> list = readInput();

		Sort sort = new Sort(list);

		sort.selectSort();

		printResults("selectSort", sort);
	}

	public static void insertSort() {
		List<Report> list = readInput();

		Sort sort = new Sort(list);

		sort.insertSort();

		printResults("insertSort", sort);
	}

	public static void mergeSort() {
		List<Report> list = readInput();

		Sort sort = new Sort(list);

		sort.mergeSort();

		printResults("mergeSort", sort);
	}

	public static void writerResults(String method, Sort sort) {
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
				"\n"
			);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void printResults(String method, Sort sort) {
		System.out.println("Método: " + method);
		System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
		System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
		System.out.println("Duração: " + Util.formatTime(sort.getDuration()));
		System.out.println();

		writerResults(method, sort);
	}

	public static List<Report> readInput() {
		long start = System.nanoTime();

		System.out.println("Lendo: " + ((limit == 0) ? "Todos" : limit));

		List<Report> result = new ArrayList<>();

		int lines = 0;

		String line;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf8"), 1024 * 64);

			while (true) {
				line = reader.readLine();

				if (line == null) break;

				result.add(new Report(line.split("\\|")));

				lines += 1;

				if (lines >= limit && limit > 0) {
					break;
				}

				if (lines % 1000 == 0) Thread.sleep(1);
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Lidos: " + lines);
		System.out.println("Duração: " + Util.formatTime(System.nanoTime() - start));
		System.out.println();

		return result;
	}
}