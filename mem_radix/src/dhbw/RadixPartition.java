package dhbw;


import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class RadixPartition {
    final static int N_MASK_BITS = 2;
    final static int N_WORKER = (1 << N_MASK_BITS); // 2^n
    final static int N_DATA = 3 * N_WORKER;    // must be multiple of N_WORKER

    final static int BITS_VALUE = 7;  // number of bits for our test data
    final static int MAX_VALUE = (1 << BITS_VALUE);

    int[] mInputData = new int[N_DATA];
    int[][] mOutputData = new int[N_WORKER][N_DATA];
    int[] mOutputDataSize = new int[N_WORKER];

    Worker[] mWorkers = new Worker[N_WORKER];

    CyclicBarrier mBarrier = new CyclicBarrier(N_WORKER, new BarrierAction());

    class Worker extends Thread {
        private int mIndex;
        private int[] mHistogram = new int[N_WORKER];
        private int[] mWriteIndex = new int[N_WORKER];

        public int[] getHistogram() {
            return mHistogram;
        }

        Worker(int index) {
            mIndex = index;
        }

        @Override
        public void run() {
            //Build Histogramm
            int size = N_DATA / N_WORKER;
            int start = mIndex * size;
            int end = start + size;
            for (int i = start; i < end; i++) {
                int idx = getBucket(mInputData[i]);
                mHistogram[idx]++;
            }
            try {
                //Sync
                mBarrier.await();
                //Copy
                for (int i = start; i < end; i++) {
                    int idx = getBucket(mInputData[i]);
                    mOutputData[idx][mWriteIndex[idx]++] = mInputData[i];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private int getBucket(int inputValue) {
            // Return n highest bits
            return (inputValue >> (BITS_VALUE - N_MASK_BITS)) & (1 << N_MASK_BITS) - 1;
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

        for (int i = 0; i < N_WORKER; i++) {
            mWorkers[i] = new Worker(i);
            mWorkers[i].start();
        }

        for (Worker w : mWorkers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < N_WORKER; i++)
            dumpArray("output " + i, mOutputData[i], mOutputDataSize[i]);
    }

    private void createInputData() {
        for (int i = 0; i < N_DATA; i++)
            mInputData[i] = randVal();
    }

    private int randVal() {
        return (int) Math.floor(MAX_VALUE * Math.random());
    }

    private void dumpArray(String name, int[] array, int size) {
        System.out.print(name + ": ");
        for (int i = 0; i < size; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    private class BarrierAction implements Runnable {
        @Override
        public void run() {
            //dump histograms
            for (int i = 0; i < N_WORKER; i++) {
                int[] hist = mWorkers[i].getHistogram();
                dumpArray("Histogram " + i, hist, N_WORKER);
            }
            //get write indices
            for (int i = 0; i < N_WORKER; i++) {
                mWorkers[0].mWriteIndex[i] = 0;
            }
            dumpArray("Write-Index " + 0, mWorkers[0].mWriteIndex, N_WORKER);
            for (int i = 1; i < N_WORKER; i++) {
                for (int j = 0; j < N_WORKER; j++) {
                    mWorkers[i].mWriteIndex[j] = mWorkers[i - 1].mWriteIndex[j] + mWorkers[i - 1].mHistogram[j];
                }
                dumpArray("Write-Index " + i, mWorkers[i].mWriteIndex, N_WORKER);
            }
            for (int i = 0; i < N_WORKER; i++) {
                mOutputDataSize[i] = mWorkers[N_WORKER - 1].mWriteIndex[i] + mWorkers[N_WORKER - 1].mHistogram[i];
            }
        }
    }
}
