package week10;
// hash.java
// demonstrates hash table with linear probing
// to run this program: C:>java HashTableApp
import java.io.*;
////////////////////////////////////////////////////////////////
class DataItem2
{ // (could have more data)
	private int iData; // data item (key)
	private int frequency = 0;
	//--------------------------------------------------------------
	public DataItem2(int ii) // constructor
	{ 
		iData = ii;
		frequency = 1;
	}
	//--------------------------------------------------------------
	public int getKey()
	{ return iData; }
	//--------------------------------------------------------------
	public int getFrequncy() {
		return frequency;
	}
	//--------------------------------------------------------------
	public void incrementFrequency() {
		frequency++;
	}
	//--------------------------------------------------------------
} // end class DataItem
////////////////////////////////////////////////////////////////
class HashTable2
{
	private DataItem2[] hashArray; // array holds hash table
	private int arraySize;
	private DataItem2 nonItem; // for deleted items
	//-------------------------------------------------------------
	public HashTable2(int size) // constructor
	{
		arraySize = size;
		hashArray = new DataItem2[arraySize];
		nonItem = new DataItem2(-1); // deleted item key is -1
	}
	//-------------------------------------------------------------
	public void displayTable()
	{
		System.out.print("Table: ");
		for(int j=0; j<arraySize; j++)
		{
			if(hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}
	//-------------------------------------------------------------
	public int hashFunc(int key)
	{
		return key % arraySize; // hash function
	}
	//-------------------------------------------------------------
	public void insert(DataItem2 item) // insert a DataItem
	//(assumes table not full)
	{
		int key = item.getKey(); // extract key
		int hashVal = hashFunc(key); // hash the key
		//until empty cell or -1,
		while(hashArray[hashVal] != null &&
				hashArray[hashVal].getKey() != -1)
		{
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		hashArray[hashVal] = item; // insert item
	} // end insert()
	//-------------------------------------------------------------
	public DataItem2 delete(int key) // delete a DataItem
	{
		int hashVal = hashFunc(key); // hash the key
		while(hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if(hashArray[hashVal].getKey() == key)
			{
				DataItem2 temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem; // delete item
				return temp; // return item
			}
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can’t find item
	} // end delete()
	//-------------------------------------------------------------
	public DataItem2 find(int key) // find item with key
	{
		int hashVal = hashFunc(key); // hash the key
		while(hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if(hashArray[hashVal].getKey() == key)
				return hashArray[hashVal]; // yes, return item
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can’t find item
	}
	//-------------------------------------------------------------
	public void printEvenElement() {
		String result = "";
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i] != null && hashArray[i].getFrequncy() % 2 == 0) {
				result += hashArray[i].getKey() + " ";
			}
		}
		if(result.isEmpty()) {
			System.out.println("Output: NONE");
		} else {
			System.out.println("Output: " + result);
		}
	}
	//-------------------------------------------------------------
} // end class HashTable
////////////////////////////////////////////////////////////////
class FindEvenElements
{
	public static void main(String[] args) throws IOException
	{
		DataItem2 aDataItem;
		System.out.print("Enter input: ");
		String input = getString();
		int n = input.length();
		HashTable2 theHashTable = new HashTable2((n + 1)/2);
		for(int i = 0; i < n; i++) {
			if(input.charAt(i) == ' ') {
				continue;
			}
			int element = (int)input.charAt(i) - '0';
			aDataItem = theHashTable.find(element);
			if(aDataItem == null) {
				aDataItem = new DataItem2(element);
				theHashTable.insert(aDataItem);
			} else {
				aDataItem.incrementFrequency();
			}
		}
		theHashTable.printEvenElement();
	} // end main()
	//--------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	//--------------------------------------------------------------
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	//-------------------------------------------------------------
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
	//--------------------------------------------------------------
} // end class HashTableApp
////////////////////////////////////////////////////////////////