package week7;

// demonstrates partitioning an array
// to run this program: C>java PartitionApp
////////////////////////////////////////////////////////////////
class ArrayPar
{
	private long[] theArray; // ref to array theArray
	private int nElems; // number of data items
	//--------------------------------------------------------------
	public ArrayPar(int max) // constructor
	{
		theArray = new long[max]; // create the array
		nElems = 0; // no items yet
	}
	//--------------------------------------------------------------
	public void insert(long value) // put element into array
	{
		theArray[nElems] = value; // insert it
		nElems++; // increment size
	}
	//--------------------------------------------------------------
	public int size() // return number of items
	{ return nElems; }
	//--------------------------------------------------------------
	public void display() // displays array contents
	{
		System.out.print("Array => ");
		for(int j=0; j<nElems; j++) // for each element,
			System.out.print(theArray[j] + " "); // display it
		System.out.println("");
	}
	//--------------------------------------------------------------
	public void findMedian() {
		recMedian(0, nElems - 1, nElems / 2);
	}

	public void recMedian(int left, int right, int middle) {
		int partition = partitionIt(left, right);
		System.out.println("Partition: " + partition + " value: " + theArray[partition]);
		if (partition == middle) {
			System.out.println("Median: " + theArray[middle]);
		} else if (partition < middle){
			recMedian(partition+1, right, middle);
		} else {
			recMedian(left, partition-1, middle);
		}
	}
	public int partitionIt(int left, int right) {
		long pivot = theArray[right];
		int leftPtr = left - 1;
		int rightPtr = right;

		while (true) {
			while (theArray[++leftPtr] < pivot)
				;
			while (rightPtr > left && theArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right);
		return leftPtr;
	}
	//--------------------------------------------------------------
	public void swap(int dex1, int dex2) // swap two elements
	{
		long temp;
		temp = theArray[dex1]; // A into temp
		theArray[dex1] = theArray[dex2]; // B into A
		theArray[dex2] = temp; // temp into B

	} // end swap()
	//--------------------------------------------------------------
} // end class ArrayPar
////////////////////////////////////////////////////////////////
class SevenPointThree
{
	public static void main(String[] args)
	{
		int maxSize = 16; // array size
		ArrayPar arr; // reference to array
		arr = new ArrayPar(maxSize); // create the array
		for(int j=0; j<maxSize; j++) // fill array with random numbers
		{
			long n = (int)(java.lang.Math.random()*199);
			arr.insert(n);
		}
		arr.display(); // display unsorted array
		arr.findMedian();
		arr.display();
	} // end main()
}