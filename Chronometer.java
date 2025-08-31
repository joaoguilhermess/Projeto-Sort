public class Chronometer {
	private long startTime;
	private long duration;

	public void start() {
		this.startTime = System.nanoTime();
	}

	public void stop() {
		this.duration = System.nanoTime() - this.startTime;
	}

	public long getDuration() {
		return this.duration;
	}
}