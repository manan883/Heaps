import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		sequentialInsertions("data_sorted.txt");
	}
	
	public static void sequentialInsertions(String filepath) throws IOException {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();
		Scanner scanner = new Scanner(new File(filepath));
		while (scanner.hasNextInt()) {
			maxHeap.add(scanner.nextInt());
		}
		maxHeap.buildHeapSI();
		maxHeap.removeHeapSI();
	}
	
}
