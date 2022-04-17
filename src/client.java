import java.io.*;
import java.util.*;
interface heap<T extends Comparable<? super T>>{
	   public void add(T newEntry);
	   public T removeMax();
	   public T getMax();
	   public boolean isEmpty();
	   public int getSize();
	   public void clear();
}

class client<T> {
	class heapMethods<T extends Comparable<? super T>>  implements heap{
		T heap[];
		int lastIndex = 0;
		private boolean integrityOK = false;
		private static final int DEFAULT_CAPACITY = 25;
		private static final int MAX_CAPACITY = 10000;

		public heapMethods()
		 {
		    this(DEFAULT_CAPACITY); // Call next constructor
		 } // end default constructor
		 public heapMethods(int initialCapacity)
		   {
		      // Is initialCapacity too small?
		      if (initialCapacity < DEFAULT_CAPACITY)
		         initialCapacity = DEFAULT_CAPACITY;
		      else // Is initialCapacity too big?
		    	  if(initialCapacity > MAX_CAPACITY);
		      
		      // The cast is safe because the new array contains null entries
		      @SuppressWarnings("unchecked")
		      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
		      heap = tempHeap;
		      lastIndex = 0;
		      integrityOK = true;
		   } // end constructor
		 private void reheap(int rootIndex)
		 {
		    boolean done = false;
		    T orphan = heap[rootIndex];
		    int leftChildIndex = 2 * rootIndex;

		    while (!done && (leftChildIndex <= lastIndex) )
		    {
		       int largerChildIndex = leftChildIndex; // Assume larger
		       int rightChildIndex = leftChildIndex + 1;

		       if ( (rightChildIndex <= lastIndex) &&
		             heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
		       {
		          largerChildIndex = rightChildIndex;
		       } // end if

		       if (orphan.compareTo(heap[largerChildIndex]) < 0)
		       {
		          heap[rootIndex] = heap[largerChildIndex];
		          rootIndex = largerChildIndex;
		          leftChildIndex = 2 * rootIndex;
		       }
		       else
		          done = true;
		    } // end while

		    heap[rootIndex] = orphan;
		 } // end reheap

	private <T extends Comparable<? super T>>
	        void reheap(T[] heap, int rootIndex, int lastIndex)
	{
	   boolean done = false;
	   T orphan = heap[rootIndex];
	   int leftChildIndex = 2 * rootIndex + 1;

	   while (!done && (leftChildIndex <= lastIndex))
	   {
	      int largerChildIndex = leftChildIndex;
	      int rightChildIndex = leftChildIndex + 1;

	      if ( (rightChildIndex <= lastIndex) &&
	            heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
	      {
	         largerChildIndex = rightChildIndex;
	      } // end if

	      if (orphan.compareTo(heap[largerChildIndex]) < 0)
	      {
	         heap[rootIndex] = heap[largerChildIndex];
	         rootIndex = largerChildIndex;
	         leftChildIndex = 2 * rootIndex + 1;
	      }
	      else
	         done = true;
	   } // end while

	   heap[rootIndex] = orphan;
	} // end reheap

		@Override
		public void add(Comparable newEntry) {
			// TODO Auto-generated method stub
			int newIndex = lastIndex + 1;
			   int parentIndex = newIndex / 2;
			   while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
			   {
			      heap[newIndex] = heap[parentIndex];
			      newIndex = parentIndex;
			      parentIndex = newIndex / 2;
			   } // end while

			   heap[newIndex] = (T) newEntry;
			   lastIndex++;

		}
		@Override
		public Comparable removeMax() {
			// TODO Auto-generated method stub
			 T root = null;

			   if (!isEmpty())
			   {
			      root = heap[1];            // Return value
			      heap[1] = heap[lastIndex]; // Form a semiheap
			      lastIndex--;               // Decrease size
			      reheap(1);                 // Transform to a heap
			   } // end if

			   return root;

		}
		@Override
		public Comparable getMax() {
			// TODO Auto-generated method stub
			T root = null;
		      if (!isEmpty())
		         root = heap[1];
		      return root;
		}
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return lastIndex < 1;
		}
		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return lastIndex;
		}
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			while (lastIndex > -1)
		      {
		         heap[lastIndex] = null;
		         lastIndex--;
		      } // end while
		      lastIndex = 0;
	}
	}
protected static ArrayList<Integer> heap = new ArrayList<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFileSetArr();
	}
	static void readFileSetArr() {
		System.out.println("Enter the file name you want to use. Default file name is 'data_random.txt'. Enter nothing and press enter to use default");
		String filename = "";
		Scanner sc = new Scanner(System.in);
		filename = sc.nextLine();
		if(filename.equals("")) {
			filename = "data_random.txt";
		}
		File data = new File(filename);
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				int temp = Integer.parseInt(reader.nextLine());
				heap.add(temp);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR FILE NOT FOUND");
			//e.printStackTrace();
		}
		
		
		
	}
	static void findRoot(int arr[],int n, int i) {
		 int largest = i; // Initialize largest as root
	        int l = 2 * i + 1; // left = 2*i + 1
	        int r = 2 * i + 2; // right = 2*i + 2
	 
	        // If left child is larger than root
	        if (l < n && arr[l] > arr[largest])
	            largest = l;
	 
	        // If right child is larger than largest so far
	        if (r < n && arr[r] > arr[largest])
	            largest = r;
	 
	        // If largest is not root
	        if (largest != i) {
	            int swap = arr[i];
	            arr[i] = arr[largest];
	            arr[largest] = swap;
	 
	            // Recursively heapify the affected sub-tree
	            findRoot(arr, n, largest);
	        }
	}
	
	


	


	
}

	


