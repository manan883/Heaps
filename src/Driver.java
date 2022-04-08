import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		sequentialInsertions("data_sorted.txt");
		optimalMethod("data_sorted.txt");
	}
	
	public static void sequentialInsertions(String filepath) throws IOException {
		MaxHeap<Integer> maxHeapSI = new MaxHeap<>();
		Scanner scanner = new Scanner(new File(filepath));
		while (scanner.hasNextInt()) {
			maxHeapSI.add(scanner.nextInt());
		}
		maxHeapSI.buildHeapSI();
		maxHeapSI.removeHeapSI();
	}
	
	public static void optimalMethod(String filepath) throws IOException {
		Integer[] tempArray = new Integer[100];
		Scanner scanner2 = new Scanner(new File(filepath));
		int index = 0;
		while (scanner2.hasNextInt()) {
			tempArray[index] = scanner2.nextInt();
			index++;
		}
		index = 0;
		MaxHeap<Integer> maxHeapOM = new MaxHeap<>(tempArray);
		maxHeapOM.buildHeapOM(tempArray);
		maxHeapOM.removeHeapOM();
	}
	
}
