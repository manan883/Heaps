import java.io.IOException;

/**
   An interface for the ADT maxheap.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
   /** Adds a new entry to this heap.
       @param newEntry  An object to be added. */
   public void add(T newEntry);

   /** Removes and returns the largest item in this heap.
       @return  Either the largest object in the heap or,
                if the heap is empty before the operation, null. */
   public T removeMax();

   /** Retrieves the largest item in this heap.
       @return  Either the largest object in the heap or,
                if the heap is empty, null. */
   public T getMax();

   /** Detects whether this heap is empty.
       @return  True if the heap is empty, or false otherwise. */
   public boolean isEmpty();

   /** Gets the size of this heap.
       @return  The number of entries currently in the heap. */
   public int getSize();

   /** Removes all entries from this heap. */
   public void clear();
   
   /** Builds a heap using sequential insertions and counts the number of swaps,
    *  then removes 10 elements from the heap
    *  */
   public void processHeapSI() throws IOException;

   /** Builds a heap using optimal method and counts the number of swaps,
    *  then removes 10 elements from the heap 
    * @param Array that includes all of the read integers from a file
    */
   public void processHeapOM() throws IOException;

   /** Writes the inputted string into a .txt file
    * @param String that contains what is needed to be written on the file
    */
   public void writeToFile(String content) throws IOException;

} // end MaxHeapInterface
