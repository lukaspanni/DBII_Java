
package dhbw;

import java.util.Iterator;

public class DistributedJoin
{
	public static final int NDATA = 100;
	public static final int MAXVALUE = 200;

	private Station mStationR = new Station();
	private Station mStationS = new Station();
	private Station mStationResult = new Station();
	
	private DataTable mReference = new DataTable();


	public static void main(String[] args) {
		DistributedJoin dj = new DistributedJoin();
		dj.run();
	}

	private void run() {
		createDataR();
		createDataS();
		computeReference();
	
		System.out.print("computeJoinNestedLoops: ");
		mStationResult.computeJoinNestedLoops(mStationR, mStationS);
		checkResult();

		System.out.print("computeJoinTransferOne: ");
		mStationResult.computeJoinTransferOne(mStationR, mStationS);
		checkResult();

		System.out.print("computeJoinTransferTwo: ");
		mStationResult.computeJoinTransferTwo(mStationR, mStationS);
		checkResult();

		System.out.print("computeJoinFilterOne:   ");
		mStationResult.computeJoinFilterOne(mStationR, mStationS);
		checkResult();

		System.out.print("computeJoinHash:        ");
		mStationResult.computeJoinHash(mStationR, mStationS);
		checkResult();
	}

	private void createDataR() {
		DataTable d = mStationR.mData;
		for (int i=0; i<NDATA; i++)
			d.addData(new DataR());
		d.sort();
	}
	private void createDataS() {
		DataTable d = mStationS.mData;
		for (int i=0; i<NDATA; i++)
			d.addData(new DataS());
		d.sort();
	}

	private void computeReference() {
		// compute reference result using nested loops
		//mStationR.mData.dump();
		//mStationS.mData.dump();

		Iterator<Data> itR = mStationR.mData.getIterator();
		while (itR.hasNext()) {
			DataR r = (DataR)itR.next();
			Iterator<Data> itS = mStationS.mData.getIterator();
			while (itS.hasNext()) {
				DataS s = (DataS)itS.next();
				if (r.C == s.C) {
					DataResult res = new DataResult(r, s);
					mReference.addData(res);
				}
			}
		}

		mReference.sort();
	}

	private void checkResult() {
		DataTable result = mStationResult.mData;
		DataTable reference = mReference;
		result.sort();
		//result.dump();
		//reference.dump();
		if (result.equals(reference))
			System.out.print("OK    ");
		else
			System.out.print("WRONG ");
		
		int valuesTransferred = 0;
		valuesTransferred += mStationResult.getValuesTransferred();
		valuesTransferred += mStationR.getValuesTransferred();
		valuesTransferred += mStationS.getValuesTransferred();
		System.out.format("%5d values transferred\n", valuesTransferred);
		
		mStationResult.resetDataTable();
		mStationResult.resetValuesTransferred();
		mStationR.resetValuesTransferred();
		mStationS.resetValuesTransferred();
	}
}

