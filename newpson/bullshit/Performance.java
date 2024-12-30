package newpson.bullshit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Performance
{
	private interface Testable
	{
		public void test(List<Integer> list, int i);
	}

	private static void swap(int[] arr, int i, int j)
	{
		if (i != j)
		{
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	private static int numCases;
	private static int sfrZeroi;
	private static int[] sequenceRandomAccess;
	private static int[] sequenceRandomRemoval;
	private static int[] sequenceFifoRemoval;

	private static void generateSequences(int numCases)
	{
		Performance.numCases = numCases;
		Random random = new Random();

		sequenceRandomAccess = new int[numCases];
		sequenceRandomRemoval = new int[numCases];
		sequenceFifoRemoval = new int[numCases];

		for (int i = 0; i < numCases; ++i)
		{
			sequenceRandomAccess[i] = i;
			sequenceFifoRemoval[i] = numCases-i-1;
			sequenceRandomRemoval[i] = random.nextInt(numCases-i);
		}
		sfrZeroi = numCases-1;
		for (int i = 0; i < numCases; ++i)
			swap(sequenceRandomAccess, random.nextInt(numCases), random.nextInt(numCases));
	}

	private static HashMap<String, Testable> cases;
	static {
		cases = new HashMap<>();
		cases.put("addition", new Testable() {
			@Override
			public void test(List<Integer> list, int i) { list.add(sequenceRandomAccess[i]); }
		});
		cases.put("random_access", new Testable() {
			@Override
			public void test(List<Integer> list, int i) { list.get(sequenceRandomAccess[i]); }
		});
		cases.put("removal_random", new Testable() {
			@Override
			public void test(List<Integer> list, int i) { list.remove(sequenceRandomRemoval[i]); }
		});
		cases.put("removal_filo", new Testable() {
			@Override
			public void test(List<Integer> list, int i) { list.remove(sequenceFifoRemoval[sfrZeroi]); }
		});
		cases.put("removal_fifo", new Testable() {
			@Override
			public void test(List<Integer> list, int i) { list.remove(sequenceFifoRemoval[i]); }
		});
	}

	private static HashMap<String, List<Integer>> subjects;
	static {
		subjects = new HashMap<>();
		subjects.put("ArrayList", new ArrayList<Integer>());
		subjects.put("LinkedList", new LinkedList<Integer>());
	}
	
	private static void bench(String list, String[] methods)
	{
		long timeStart, timeEnd;
		Testable tcase;
		List<Integer> tsubject;
		for (String method: methods)
		{
			tcase = cases.get(method);
			tsubject = subjects.get(list);
			timeStart = System.nanoTime();
			for (int i = 0; i < numCases; ++i)
				tcase.test(tsubject, i);
			timeEnd = System.nanoTime();
			System.out.printf("%-20s  %.2f\n", method, (timeEnd - timeStart)/1000.0);
		}
	}

	
	public static void main(String[] args)
	{
		// TODO check arguments
		generateSequences(Integer.valueOf(args[0]));
		bench(args[1], Arrays.copyOfRange(args, 2, args.length));
	}
}
