import java.util.List;

public class Sort extends Chronometer {
	private List<Report> reports;

	private long comparisons;
	private long swaps;

	private long startTime;
	private long duration;

	public Sort(List<Report> reports) {
		this.reports = reports;

		this.comparisons = 0;
		this.swaps = 0;
	}

	public int getAmount() {
		return this.reports.size();
	}

	public long getComparisons() {
		return this.comparisons;
	}

	public long getSwaps() {
		return this.swaps;
	}

	private void swap(int indexA, int indexB) {
		Report temporary = this.reports.get(indexA);

		this.reports.set(indexA, this.reports.get(indexB));

		this.reports.set(indexB, temporary);

		this.swaps += 1;
	}

	public void bubbleSort() {
		this.start();

		for (int i = this.reports.size() - 1; i >= 0; i--) {
			for (int k = 0; k < i; k++) {
				if (Report.compare(this.reports.get(k), this.reports.get(k + 1))) {
					this.swap(k, k + 1);
				}

				this.comparisons += 1;
			}
		}

		this.stop();
	}

	public void selectSort() {
		this.start();

		for (int i = 0; i < this.reports.size() - 1; i++) {
			int min = i;

			for (int k = i + 1; k < this.reports.size(); k++) {
				if (Report.compare(this.reports.get(k), this.reports.get(min))) {
					min = i;
				}

				this.comparisons += 1;
			}

			this.swap(i, min);
		}

		this.stop();
	}

	public void insertSort() {
		this.start();

		for (int i = 0; i < this.reports.size(); i++) {
			Report temporary = this.reports.get(i);

			int k = i;

			while (k > 0 && Report.compare(this.reports.get(k - 1), temporary)) {
				this.swap(k, k - 1);

				k -= 1;

				this.comparisons += 1;
			}

			this.comparisons += 1;

			this.reports.set(k, temporary);

			this.swaps += 1;
		}

		this.stop();
	}

	private void merge(Report[] workSpace, int left, int middle, int right) {
		int i = 0;
		int leftIndex = left;
		int middleIndex = middle - 1;
		int k = right - leftIndex + 1;

		while (left <= middleIndex && middle <= right) {
			if (Report.compare(this.reports.get(left), this.reports.get(middle))) {
				workSpace[i++] = this.reports.get(left++);
			} else {
				workSpace[i++] = this.reports.get(middle++);
			}

			this.comparisons += 1;

			this.swaps += 1;
		}

		while (left <= middleIndex) {
			workSpace[i++] = this.reports.get(left++);

			this.swaps += 1;
		}

		while (middle <= right) {
			workSpace[i++] = this.reports.get(middle++);

			this.swaps += 1;
		}

		for (i = 0; i < k; i++) {
			this.reports.set(leftIndex + i, workSpace[i]);

			this.swaps += 1;
		}
	}

	private void recursiveMergeSort(Report[] workSpace, int left, int right) {
		if (left == right) {
			return;
		}

		int middle = (left + right) / 2;

		this.recursiveMergeSort(workSpace, left, middle);
		this.recursiveMergeSort(workSpace, middle + 1, right);

		this.merge(workSpace, left, middle + 1, right);
	}

	public void mergeSort() {
		this.start();

		Report[] workSpace = new Report[this.reports.size()];

		this.recursiveMergeSort(workSpace, 0, this.reports.size() - 1);

		this.stop();
	}

	private void heapify(int k, int i) {
		int largest = i;
		int left = i * 2 + 1;
		int right = i * 2 + 2;

		if (left < k && Report.compare(this.reports.get(left), this.reports.get(largest))) {
			largest = left;
		}

		this.comparisons += 1;

		if (right < k && Report.compare(this.reports.get(left), this.reports.get(largest))) {
			largest = right;
		}

		this.comparisons += 1;

		if (largest != i) {
			this.swap(i, largest);

			this.heapify(k, largest);
		}
	}

	public void heapSort() {
		this.start();

		int k = this.reports.size();

		for (int i = k / 2 - 1; i >= 0; i--) {
			this.heapify(k, i);
		}

		for (int i = k - 1; i > 0; i--) {
			this.swap(0, i);

			this.heapify(i, 0);
		}

		this.stop();
	}

	private int partition(int left, int right) {
		Report pivot = this.reports.get(right);

		int k = left - 1;

		for (int i = left; i < right; i++) {
			if (Report.compare(this.reports.get(i), pivot)) {
				k += 1;

				this.swap(k, i);
			}

			this.comparisons += 1;
		}

		k += 1;

		this.swap(k, right);

		return k;
	}

	public void quickSort(int left, int right) {
		if (left < right) {
			int pivotIndex = this.partition(left, right);

			this.quickSort(left, pivotIndex - 1);
			this.quickSort(pivotIndex + 1, right);
		}
	}

	public void quickSort() {
		this.start();

		this.quickSort(0, this.reports.size() - 1);

		this.stop();
	}
}