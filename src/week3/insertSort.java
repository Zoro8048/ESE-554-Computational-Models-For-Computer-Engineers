package week3;

// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns
{
	private long[] a; // ref to array a
	private int nElems; // number of data items
	//--------------------------------------------------------------
	public ArrayIns(int max) // constructor
	{
		a = new long[max]; // create the array
		nElems = 0; // no items yet
	}
	//--------------------------------------------------------------
	public void insert(long value) // put element into array
	{
		a[nElems] = value; // insert it
		nElems++; // increment size
	}
	//--------------------------------------------------------------
	public void display() // displays array contents
	{
		for(int j=0; j<nElems; j++) // for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}
	//--------------------------------------------------------------
	public void insertionSort()
	{
		int in, out;
		for(out=1; out<nElems; out++) // out is dividing line
		{
			long temp = a[out]; // remove marked item
			in = out; // start shifts at out
			while(in>0 && a[in-1] >= temp) // until one is smaller,
			{
				a[in] = a[in-1]; // shift item to right
				--in; // go left one position
			}
			//Insertion Sort 101

			a[in] = temp; // insert marked item
		} // end for
	} // end insertionSort()


	public void noDups() {
		insertionSort();
		int lateIndex = 0;
		int currentIndex = 1;

		while(currentIndex < nElems) {
			if(a[lateIndex] != a[currentIndex]) {
				lateIndex++;
				a[lateIndex] = a[currentIndex];
			}
			currentIndex++;
		}
		nElems = lateIndex + 1;
	}

	public void insertionSortWithoutDuplicates() {
		int in, out;
		int duplicateCount = 0;
		for(out = 1; out < nElems; out++) {
			long temp = a[out];
			in = out;
			while(in > 0 && a[in - 1] >= temp) {
				if(a[in - 1] == temp && temp != -1) {
					temp = -1;
					++duplicateCount;
				}
				a[in] = a[in - 1];
				--in;
			}
			a[in] = temp;
		}
		if(duplicateCount > 0) {
			for(int i = duplicateCount; i < nElems; i++) {
				a[i - duplicateCount] = a[i];
			}
		}
		nElems = nElems - duplicateCount;
	}

	//--------------------------------------------------------------
} // end class ArrayIns
////////////////////////////////////////////////////////////////
class InsertSortApp
{
	public static void main(String[] args)
	{
		int maxSize = 100; // array size
		ArrayIns arr; // reference to array
		arr = new ArrayIns(maxSize); // create the array
		arr.insert(77); // insert 10 items
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		arr.insert(33);
		arr.insert(33);
		arr.insert(33);
		arr.display(); // display items
		arr.insertionSort(); // insertion-sort them
		arr.display(); // display them again
		//		arr.noDups();
		//		arr.insert(33);
		//		arr.insert(33);
		//		arr.insert(33);
		//		arr.display();
		//		arr.insertionSortWithoutDuplicates();
		//		arr.display();
	} // end main()
} // end class InsertSortApp
////////////////////////////////////////////////////////////////