package week7;


class Solution {

	private long[] theArray;
	private int nElems;

	public Solution(int max) {
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value) {
		theArray[nElems] = value;
		nElems++;
	}

	public void display() {
		System.out.print("Array => ");
		for (int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println("");
	}

	public long getK(int k) {
		return getKthElement(0, nElems - 1, k);
	}

	public long getKthElement(int left, int right, int k) {
		int size = right - left + 1;
		if (size <= 3) {
			manualSort(left, right);
			return theArray[k];
		} else {
			long median = medianOf3(left, right);
			int partition = partitionIt(left, right, median);
			if (partition == k) {
				return theArray[k];
			} else if (partition > k) {
				return getKthElement(left, partition - 1, k);
			} else {
				return getKthElement(partition + 1, right, k);
			}
		}
	}

	public long medianOf3(int left, int right) {
		int middle = (left + right) / 2;

		if (theArray[left] > theArray[middle])
			swap(left, middle);

		if (theArray[left] > theArray[right])
			swap(left, right);

		if (theArray[middle] > theArray[right])
			swap(middle, right);

		swap(middle, right - 1);
		return theArray[right - 1];
	}

	public void swap(int a, int b) {
		long temp = theArray[a];
		theArray[a] = theArray[b];
		theArray[b] = temp;
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;

		while (true) {
			while (theArray[++leftPtr] < pivot) ;

			while (theArray[--rightPtr] > pivot) ;

			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right - 1);
		return leftPtr;
	}

	public void manualSort(int left, int right) {
		int size = right - left + 1;

		if (size <= 1)
			return;

		if (size == 2) {

			if (theArray[left] > theArray[right])
				swap(left, right);
			return;
		} else {

			if (theArray[left] > theArray[right - 1])
				swap(left, right - 1);
			if (theArray[left] > theArray[right])
				swap(left, right);
			if (theArray[right - 1] > theArray[right])
				swap(right - 1, right);
		}
	}

}


class SevenPointFour {

	public static void main(String[] args) {
		int maxSize = 16;
		Solution arr;
		arr = new Solution(maxSize);
		for(int i = 0; i< maxSize; i++) {
			long n = (int)(java.lang.Math.random()*199);
			arr.insert(n);
		}
		int k = 5;
		arr.display();
		System.out.println("Kth element (k=" + k +") => " + arr.getK(k));
		arr.display();
	}
}




