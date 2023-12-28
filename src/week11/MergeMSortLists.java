package week11;

public class MergeMSortLists {
	public static int[] mergeSortedLists(int[][] sortedLists) {
		int totalSize = 0;
		for (int[] list : sortedLists) {
			totalSize = totalSize + list.length;
		}

		int[] mergedList = new int[totalSize];
		int[] currentIndex = new int[sortedLists.length];
		int mergedIndex = 0;
		while (mergedIndex < totalSize) {
			int minValue = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < sortedLists.length; i++) {
				if (currentIndex[i] < sortedLists[i].length && sortedLists[i][currentIndex[i]] < minValue) {
					minValue = sortedLists[i][currentIndex[i]];
					minIndex = i;
				}
			}
			if (minIndex == -1) {
				break;
			}
			mergedList[mergedIndex++] = minValue;
			currentIndex[minIndex]++;
		}
		return mergedList;
	}

	public static void main(String[] args) {
		int[][] inputLists = { {10, 20, 30, 40}, {15, 25, 35}, {27, 29, 37, 48, 93}, {32, 33} };
		int[] sortedList = mergeSortedLists(inputLists);
		for (int value : sortedList) {
			System.out.print(value + " ");
		}
	}
}