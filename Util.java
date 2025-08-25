public class Util {
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
}