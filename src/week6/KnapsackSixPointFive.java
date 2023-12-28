package week6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Q) 6.5
public class KnapsackSixPointFive {
	public static void showTeams(int n, int k, int[] currentTeam, int currentItem, int teamSize) {
		if (teamSize == k) {
			for (int i = 0; i < k; i++) {
				System.out.print(currentTeam[i] + " ");
			}
			System.out.println();
			return;
		}
		if (currentItem >= n) {
			return;
		}
		currentTeam[teamSize] = currentItem + 1;
		showTeams(n, k, currentTeam, currentItem + 1, teamSize + 1);
		showTeams(n, k, currentTeam, currentItem + 1, teamSize);
	}

	public static void main(String[] args)  {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			//	Group Size
			int n = Integer.parseInt(reader.readLine());
			//	Team Size
			int k = Integer.parseInt(reader.readLine());

			int[] currentTeam = new int[k];
			showTeams(n, k, currentTeam, 0, 0);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
