import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static List<Report> read(String path, int limit) {
		long start = System.nanoTime();

		System.out.println("Lendo: " + limit);

		List<Report> result = new ArrayList<>();

		String line = "";

		int lines = 0;

		try {
			FileInputStream file = new FileInputStream(path);
			
			while (true) {
				int code = file.read();

				if (code == -1) {
					break;
				} else if (code == '\n') {
					if (lines > 0) {
						Report report = new Report(line.split(","));

						result.add(report);
					}

					lines += 1;

					line = "";
				} else {
					line += (char) code;
				}

				if (limit > 0 && lines > limit) break;
			}

			file.close();			
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		System.out.println("Duração: " + Util.formatTime(System.nanoTime() - start));
		System.out.println();

		return result;
	}

	public static void main(String[] args) {
		List<Integer> amounts = Arrays.asList(
			1,
			10,
			100,
			1000,
			5000,
			10000,
			20000,
			30000,
			50000,
			75000,
			100000,
			0
		);

		String path = "./data/uber.csv";

		try {
			FileWriter file = new FileWriter("./data/results.csv");

			file.append(
				"Metodo" + 
				";" +
				"Quantidade" + 
				";" +
				"Trocas" + 
				";" +
				"Comparacoes" + 
				";" +
				"Duracao" +
				"\n"
			);

			for (int i = 0; i < amounts.size(); i++) {
				List<Report> allReports = read(path, amounts.get(i));

				System.out.println("Bubble:");

				Sort sort = new Sort(allReports);

				sort.bubbleSort(2);
			
				System.out.println("Quantidade: " + Util.formatNumber(allReports.size()));
				System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
				System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
				System.out.println("Duração: " + Util.formatTime(sort.getDuration()));

				System.out.println();

				file.append(
					"Bubble" +
					";" +
					Util.formatNumber(allReports.size()) +
					";" +
					Util.formatNumber(sort.getSwaps()) +
					";" +
					Util.formatNumber(sort.getComparisons()) +
					";" +
					Util.formatTime(sort.getDuration()) +
					"\n"
				);
			}

			for (int i = 0; i < amounts.size(); i++) {
				List<Report> allReports = read(path, amounts.get(i));

				System.out.println("Select:");

				Sort sort = new Sort(allReports);

				sort.selectSort(2);

				System.out.println("Quantidade: " + Util.formatNumber(allReports.size()));
				System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
				System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
				System.out.println("Duração: " + Util.formatTime(sort.getDuration()));

				System.out.println();

				file.append(
					"Select" +
					";" +
					Util.formatNumber(allReports.size()) +
					";" +
					Util.formatNumber(sort.getSwaps()) +
					";" +
					Util.formatNumber(sort.getComparisons()) +
					";" +
					Util.formatTime(sort.getDuration()) +
					"\n"
				);
			}

			for (int i = 0; i < amounts.size(); i++) {
				List<Report> allReports = read(path, amounts.get(i));

				System.out.println("Insertion:");

				Sort sort = new Sort(allReports);

				sort.insertSort(2);
			
				System.out.println("Quantidade: " + Util.formatNumber(allReports.size()));
				System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
				System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
				System.out.println("Duração: " + Util.formatTime(sort.getDuration()));

				System.out.println();

				file.append(
					"Insertion" +
					";" +
					Util.formatNumber(allReports.size()) +
					";" +
					Util.formatNumber(sort.getSwaps()) +
					";" +
					Util.formatNumber(sort.getComparisons()) +
					";" +
					Util.formatTime(sort.getDuration()) +
					"\n"
				);
			}

			file.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}