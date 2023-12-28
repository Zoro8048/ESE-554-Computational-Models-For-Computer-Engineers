package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class GameShowHost {
	public static void main(String args[]) throws IOException {
		System.out.print("Enter input: ");
		String input = getString();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String elements[] = input.split(";");
		for(int i = 0; i < elements.length; i++) {
			String details[] = elements[i].split(",");
			String key = details[0];
			int score = Integer.parseInt(details[1]);
			map.put(key, map.getOrDefault(key, 0) + score);
		}
		int maxScore = -1;
		String result = "";
		for(Map.Entry<String, Integer> entry: map.entrySet()) {
			if(maxScore < entry.getValue()) {
				result = entry.getKey()+","+entry.getValue();
				maxScore = entry.getValue();
			}
		}
		System.out.println("Output: " + result);
	}
	
	public static String getString() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
}