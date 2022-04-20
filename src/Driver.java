import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		// Clears whatever is in output.txt
		Path path = Paths.get("output.txt");
		Files.delete(path);
				
		// Calls the two methods that build a max-heap in different ways
		sequentialInsertions("data_sorted.txt");
		optimalMethod("data_sorted.txt");
		
		sequentialInsertions("data_random.txt");
		optimalMethod("data_random.txt");
	}
	
	// Sets up the heap and calls two methods used to create/modify the heap with sequential instructions
	public static void sequentialInsertions(String filepath) throws IOException {
		// Creates a MaxHeap object that uses the default constructor
		MaxHeap<Integer> maxHeapSI = new MaxHeap<>();
		
		// Reads all integers from the .txt file and adds them into a heap
		Scanner scanner = new Scanner(new File(filepath));
		while (scanner.hasNextInt()) {
			maxHeapSI.add(scanner.nextInt());
		}
		
		// Calls a method that creates and modifies the heap and writes the results to a file
		maxHeapSI.processHeapSI();
	}
	
	// Sets up the heap and calls two methods used to create/modify the heap with optimal method
	public static void optimalMethod(String filepath) throws IOException {
		
		// Reads all integers from the .txt file and stores them into a temporary array
		Integer[] tempArray = new Integer[100];
		Scanner scanner2 = new Scanner(new File(filepath));
		int index = 0;
		while (scanner2.hasNextInt()) {
			tempArray[index] = scanner2.nextInt();
			index++;
		}

		// Creates a MaxHeap object that uses the constructor with an array
		MaxHeap<Integer> maxHeapOM = new MaxHeap<>(tempArray);
		
		// Calls a method that creates and modifies the heap and writes the results to a file
		maxHeapOM.processHeapOM();
	}
	
}
