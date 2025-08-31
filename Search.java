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

		Report current;

		for (int i = 0; i < this.reports.size(); i++) {
			current = this.reports.get(i);

			this.comparisons += 1;
			
			if (current.getNOME().equals(value)) {				
				this.stop();

				return current;
			}
		}

		this.stop();

		return null;
	}

	public Report binarySearch(String value) {
		this.start();

		int left = 0;
		int right = this.reports.size() - 1;

		Report current;

		while (left <= right) {
			int middle = left + (right - left) / 2;

			current = this.reports.get(middle);

			this.comparisons += 1;
			
			if (current.getNOME().equals(value)) {
				this.stop();

				return current;
			} else if (current.getNOME().compareTo(value) > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		
			this.comparisons += 1;
		}

		this.stop();

		return null;
	}
}