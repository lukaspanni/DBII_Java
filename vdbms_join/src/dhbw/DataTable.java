
package dhbw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class DataTable
{
	private List<Data> mData = new ArrayList<Data>();

	public void addData(Data d) {
		mData.add(d);
	}

	public List<Data> data() {
		return mData;
	}

	public Iterator<Data> getIterator() {
		return mData.iterator();
	}

	public int size() {
		return mData.size();
	}

	public void sort() {
		Collections.sort(mData);
	}

	public void dump() {
		System.out.println();
		for (Data d : mData)
			System.out.println(d);
	}

	public boolean equals(DataTable rhs) {
		if (mData.size() != rhs.mData.size())
			return false;
		// assume both data tables are sorted
		for (int i=0; i<mData.size(); i++) {
			if (!mData.get(i).equals(rhs.mData.get(i)))
				return false;
		}
		return true;
	}
}

