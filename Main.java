import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static List<Report> read(String path, int limit) {
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

		return result;
	}

	public static void main(String[] args) {
		List<Integer> amounts = Arrays.asList(
			// 1,
			// 10,
			// 100,
			// 1000
			5000
			// 10000,
			// 15000,
			// 20000
		);

		for (int i = 0; i < amounts.size(); i++) {
			List<Report> allReports = read("./data/uber.csv", amounts.get(i));

			Sort sort = new Sort(allReports);

			sort.bubbleSort(2);
			// sort.selectSort(2);

			System.out.println("Bubble:");
		
			System.out.println("Quantidade: " + Util.formatNumber(allReports.size()));
			System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
			System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
			System.out.printf("Duração: %.4f ms", sort.getDuration());

			System.out.println();
			System.out.println();
		}

		for (int i = 0; i < amounts.size(); i++) {
			List<Report> allReports = read("./data/uber.csv", amounts.get(i));

			Sort sort = new Sort(allReports);

			// sort.bubbleSort(2);
			sort.selectSort(2);

			System.out.println("Select:");

			System.out.println("Quantidade: " + Util.formatNumber(allReports.size()));
			System.out.println("Trocas: " + Util.formatNumber(sort.getSwaps()));
			System.out.println("Comparações: " + Util.formatNumber(sort.getComparisons()));
			System.out.printf("Duração: %.4f ms", sort.getDuration());

			System.out.println();
			System.out.println();
		}
	}
}