package week5;

//linkList.java
//demonstrates linked list
//to run this program: C>java LinkListApp
////////////////////////////////////////////////////////////////
class Link1
{
	public int iData; // data item (key)
	public double dData; // data item
	public Link1 next; // next link in list
	//-------------------------------------------------------------

	public Link1(int id, double dd) // constructor
	{
		iData = id; // initialize data
		dData = dd; // (‘next’ is automatically
	} // set to null)
	//-------------------------------------------------------------
	public void displayLink() // display ourself
	{
		System.out.print("{" + iData + ", " + dData + "} ");
	}
} // end class Link
////////////////////////////////////////////////////////////////
class LinkList1
{
	private Link1 first; // ref to first link on list
	//-------------------------------------------------------------
	public LinkList1() // constructor
	{
		first = null; // no items on list yet
	}
	//-------------------------------------------------------------
	public boolean isEmpty() // true if list is empty
	{
		return (first==null);
	}
	//-------------------------------------------------------------
	//insert at start of list
	public void insertFirst(int id, double dd)
	{ // make new link
		Link1 newLink = new Link1(id, dd);
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}
	//-------------------------------------------------------------
	public Link1 deleteFirst() // delete first item
	{ // (assumes list not empty)
		Link1 temp = first; // save reference to link
		first = first.next; // delete it: first-->old next
		return temp; // return deleted link
	}

	//-------------------------------------------------------------
	public void displayList()
	{
		System.out.print("List (first-->last): ");
		Link1 current = first; // start at beginning of list
		while(current != null) // until end of list,
		{
			current.displayLink(); // print data
			current = current.next; // move to next link
		}
		System.out.println("");
	}

	public boolean hasCycle() {
		Link1 slow = first;
		Link1 fast = first;

		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if(slow == fast) {
				return true;
			}
		}
		return false;
	}

	//-------------------------------------------------------------
} // end class LinkList
////////////////////////////////////////////////////////////////
class FindACyleInLinkedList
{
	public static void main(String[] args)
	{
		LinkList1 theList = new LinkList1(); // make new list
		theList.insertFirst(22, 2.99); // insert four items
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);
		theList.displayList(); // display list
		while( !theList.isEmpty() ) // until it’s empty,
		{
			Link1 aLink = theList.deleteFirst(); // delete link
			System.out.print("Deleted "); // display it
			aLink.displayLink();
			System.out.println("");
		}
		theList.displayList(); // display list
		theList.displayList();
		System.out.print( "Has Loop : " + theList.hasCycle());
	} // end main()
} // end class LinkListApp
////////////////////////////////////////////////////////////////