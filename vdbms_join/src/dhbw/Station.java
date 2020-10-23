
package dhbw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Station
{
	public DataTable mData = new DataTable();
	private int mValuesTransferred = 0;

	
	public void resetDataTable() {
		mData = new DataTable();
	}
	public void resetValuesTransferred() {
		mValuesTransferred = 0;
	}
	
	public int getValuesTransferred() {
		return mValuesTransferred;
	}


	// ************************************************************************
	// Merge-Join

	public void mergeJoin(DataTable result, Iterator<Data> jIt1, Iterator<Data> jIt2) {
	}
	

	// ************************************************************************
	// Join with Nested Loops
	
	// StationResult
	public void computeJoinNestedLoops(Station stR, Station stS) {
	}
	

	// ************************************************************************
	// Join transferring two relations

	// StationResult
	public void computeJoinTransferTwo(Station stR, Station stS) {
	}
	

	// ************************************************************************
	// Join transferring one relation

	// StationResult
	public void computeJoinTransferOne(Station stR, Station stS) {
	}
	
	
	// ************************************************************************
	// Join filtering one relation

	// StationResult
	public void computeJoinFilterOne(Station stR, Station stS) {
	}
	
	
	// ************************************************************************
	// Join using hash

	// StationResult
	public void computeJoinHash(Station stR, Station stS) {
	}
}

