public class Report {
	private String[] values;

	public Report(String[] values) {
		this.values = values;
	}

	public String getValue(int index) {
		if (index >= 0 && index < this.values.length) {
			return this.values[index];
		}

		return null;
	}
}