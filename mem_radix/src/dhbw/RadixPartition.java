
package dhbw;


public class RadixPartition
{
	final static int N_MASK_BITS = 1;
	final static int N_WORKER = (1 << N_MASK_BITS);
	final static int N_DATA = 6*N_WORKER;	// must be multiple of N_WORKER
	
	final static int BITS_VALUE = 6;  // number of bits for our test data
	final static int MAX_VALUE = (1 << BITS_VALUE);
	
	int[] mInputData = new int[N_DATA];
	int[][] mOutputData = new int[N_WORKER][N_DATA];
	int[] mOutputDataSize = new int[N_WORKER];
		
	Worker[] mWorkers = new Worker[N_WORKER];

			
	class Worker extends Thread
	{
		private int mIndex;
		private int[] mHistogram = new int[N_WORKER];
		private int[] mWriteIndex = new int[N_WORKER];
		
		Worker(int index) {
			mIndex = index;
		}

		@Override
		public void run() {
		}
	}


	public static void main(String[] args) {
		RadixPartition rp = new RadixPartition();
		rp.partition();
		System.out.println("done.");
	}

	private void partition() {
		createInputData();
		dumpArray("input", mInputData, mInputData.length);

		for (int i=0; i<N_WORKER; i++)
			dumpArray("output " + i, mOutputData[i], mOutputDataSize[i]);
	}

	private void createInputData() {
		for (int i=0; i<N_DATA; i++)
			mInputData[i] = randVal();
	}

	private int randVal() {
		return (int)Math.floor(MAX_VALUE * Math.random());
	}

	private void dumpArray(String name, int[] array, int size) {
		System.out.print(name + ": ");
		for (int i=0; i<size; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
}
