import java.util.Arrays;
public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
   
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
   public MaxHeap(int initialCapacity)
   {
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      lastIndex = 0;
      integrityOK = true;
   } // end constructor

   public void add(T newEntry)
   {
    checkIntegrity();        // Ensure initialization of data fields
    int newIndex = lastIndex + 1;
    int parentIndex = newIndex / 2;
    while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
    {
       heap[newIndex] = heap[parentIndex];
       newIndex = parentIndex;
       parentIndex = newIndex / 2;
    } // end while
 
    heap[newIndex] = newEntry;
    lastIndex++;
    ensureCapacity();
   } // end add

   public T removeMax()
   {
    checkIntegrity();             // Ensure initialization of data fields
   T root = null;

   if (!isEmpty())
   {
      root = heap[1];            // Return value
      heap[1] = heap[lastIndex]; // Form a semiheap
      lastIndex--;               // Decrease size
      reheap(1);                 // Transform to a heap
   } // end if

   return root; 
   } // end removeMax

   public T getMax()
   {
		checkIntegrity();
      T root = null;
      if (!isEmpty())
         root = heap[1];
      return root;
   } // end getMax

   public boolean isEmpty()
   {
      return lastIndex < 1;
   } // end isEmpty

   public int getSize()
   {
      return lastIndex;
   } // end getSize

   public void clear()
   {
		checkIntegrity();
      while (lastIndex > -1)
      {
         heap[lastIndex] = null;
         lastIndex--;
      } // end while
      lastIndex = 0;
   } // end clear
   
// Private methods
// . . .
} // end MaxHeap
