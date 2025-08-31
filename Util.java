public class Util {
	public static String formatTime(long time) {
		String result = "";

		long MCS = 1000;
		long MS = MCS * 1000;
		long S = MS * 1000;
		long M = S * 60;
		long H = M * 60;

		if (time >= H) {
			long k = time / H;

			result += k + "h ";

			time -= k * H;
		}

		if (time >= M) {
			long k = time / M;

			result += k + "m ";

			time -= k * M;
		}

		if (time >= S) {
			long k = time / S;

			result += k + "s ";

			time -= k * S;
		}

		if (time >= MS) {
			long k = time / MS;

			result += k + "ms ";

			time -= k * MS;
		}

		if (time >= MCS) {
			long k = time / MCS;

			result += k + "mcs ";

			time -= k * MCS;
		}

		if (result.length() == 0 || time > 0) {
			result += time + "ns";
		}

		return result;
	}

	public static String formatNumber(long number) {
		String result = "";

		String input = String.valueOf(number);

		while (input.length() > 3) {
			result = "." + input.substring(input.length() - 3) + result;

			input = input.substring(0, input.length() - 3);
		}

		result = input + result;

		return result;
	}

	public static int parseInt(String input) {
		if (input.length() == 0) {
			return 0;
		}

		return Integer.parseInt(input);
	}

	public static float parseFloat(String input) {
		if (input.length() == 0) {
			return 0;
		}

		return Float.parseFloat(input.replaceAll(",", "."));
	}
}