import java.util.List;

public class Sort {
	private List<Report> reports;

	private long comparisons;
	private long swaps;

	private long start;
	private long duration;

	public Sort(List<Report> reports) {
		this.reports = reports;

		this.comparisons = 0;
		this.swaps = 0;
	}

	public long getComparisons() {
		return this.comparisons;
	}

	public long getSwaps() {
		return this.swaps;
	}

	private void start() {
		this.start = System.nanoTime();
	}

	private void stop() {
		this.duration = System.nanoTime() - this.start;
	}

	public double getDuration() {
		return this.duration / 1_000_000.0;
	}

	private void swap(int indexA, int indexB) {
		Report temporary = this.reports.get(indexA);

		this.reports.set(indexA, this.reports.get(indexB));

		this.reports.set(indexB, temporary);

		this.swaps += 1;
	}

	public void bubbleSort(int index) {
		this.start();

		for (int i = this.reports.size() - 1; i > 0; i--) {
			for (int k = 0; k < i; k++) {
				if (this.reports.get(k).getValue(index).compareTo(this.reports.get(k + 1).getValue(index)) > 0) {
					this.swap(k, k + 1);
				}

				this.comparisons += 1;
			}
		}

		this.stop();
	}

	public void selectSort(int index) {
		this.start();

		for (int i = 0; i < this.reports.size() - 1; i++) {
			int min = i;

			for (int k = i + 1; k < this.reports.size(); k++) {
				if (this.reports.get(k).getValue(index).compareTo(this.reports.get(min).getValue(index)) > 0) {
					min = i;
				}

				this.comparisons += 1;
			}

			this.swap(i, min);
		}

		this.stop();
	}
	
	public void insertSort() {

	}
	
	public void heapSort() {

	}
	
	public void mergeSort() {

	}
	
	public void quickSort() {

	}
}