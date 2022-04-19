import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
/**
   A class that implements the ADT maxheap by using an array.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 10000;
   private int numberOfSwapsSI;
   private int numberOfSwapsOM;
   
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
   
   public MaxHeap(T[] entries)
   {
      this(entries.length); // Call other constructor
      lastIndex = entries.length;
      // Assertion: integrityOK = true

      // Copy given array to data field
      for (int index = 0; index < entries.length; index++)
         heap[index + 1] = entries[index];

      // Create heap
      for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--) {
    	  reheap(rootIndex);
      }
         
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
	      numberOfSwapsSI++;
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
         	numberOfSwapsOM++;
         }
         else
            done = true;
      } // end while

      heap[rootIndex] = orphan;
   } // end reheap
   
	private void checkIntegrity() {
		      if (!integrityOK)
		          throw new SecurityException("MaxHeap object is corrupt.");
	} // end checkIntegrity
	
	private void ensureCapacity() {
	   int capacity = heap.length - 1;
	   if (lastIndex >= capacity)
	   {
	      int newCapacity = 2 * capacity;
	      checkCapacity(newCapacity); // Is capacity too big?
	      heap = Arrays.copyOf(heap, newCapacity + 1);
	   } // end if
	} // end ensureCapacity
	
	private void checkCapacity(int initialCapacity) {
	   if (initialCapacity > MAX_CAPACITY)
	       throw new IllegalStateException("Attempt to create a heap whose capacity exceeds " +
	                                       "allowed maximum of " + MAX_CAPACITY);
	} // end checkCapacity
	
	
	// * * * PROJECT 4 CODE STARTS HERE * * *
	
	public void processHeapSI() throws IOException {
		
		// Stores the first 10 values in the heap into a string that is used to write to a file
		String result = "";
		for(int i = 1; i <= 10; i++) {
			   result = result + heap[i] + ",";
		   }
		writeToFile("=====================================================================");
		writeToFile("Heap built using sequential insertions: " + result + "...");
		writeToFile("Number of swaps in the heap creation: " + numberOfSwapsSI);
		
		// Removes 10 elements from the heap
		for(int i = 0; i < 10; i++) {
			this.removeMax();
		}
		
		// Resets the result string and stores the new first 10 values in the heap into a string that is used to write a file
		result = "";
		for(int i = 1; i <= 10; i++) {
			   result = result + heap[i] + ",";
		}
		writeToFile("Heap after 10 removals: " + result + "...");
		writeToFile("");
	} // end processHeapSI
	
	public void processHeapOM() throws IOException {
		
		  // Stores the first 10 values in the heap into a string that is used to write to a file
	      String result = "";
			for(int i = 1; i <= 10; i++) {
				   result = result + heap[i] + ",";
			}
	      writeToFile("Heap built using optimal method: " + result + "...");
	      writeToFile("Number of swaps in the heap creation: " + numberOfSwapsOM);
	      
		  // Removes 10 elements from the heap
	      for(int i = 0; i < 10; i++) {
				this.removeMax();
			}
			
			// Resets the result string and stores the new first 10 values in the heap into a string that is used to write a file
			result = "";
			for(int i = 1; i <= 10; i++) {
				   result = result + heap[i] + ",";
			}
			writeToFile("Heap after 10 removals: " + result + "...");
			writeToFile("=====================================================================");
	} // end processHeapOM
	
	public void writeToFile(String content) throws IOException {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
			bw.write(content);
			bw.write("\n");
		}
	} // end writeToFile
	
} // end MaxHeap
