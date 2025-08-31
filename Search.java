import java.util.List;

public class Search extends Chronometer {
	private List<Report> reports;

	private long comparisons;

	public Search(List<Report> reports) {
		this.reports = reports;

		this.comparisons = 0;
	}

	public long getAmount() {
		return this.reports.size();
	}

	public long getComparisons() {
		return this.comparisons;
	}

	public Report sequentialSearch(String value) {
		this.start();

		Report current = null;

		for (int i = 0; i < this.reports.size(); i++) {
			current = this.reports.get(i);

			if (current.getNOME().equals(value)) {
				break;
			}

			this.comparisons += 1;
		}

		this.stop();

		return current;
	}

	public void binarySearch() {
		this.start();

		this.stop();
	}
}