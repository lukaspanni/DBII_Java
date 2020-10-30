
package dhbw;

import java.util.Random;


public class Request
{
	private static Random mRandom = new Random();
	private int mStationMask = 0;

	protected QuorumConsensus mQC;

	Request(QuorumConsensus qc) {
		this.mQC = qc;
	}
	
	protected Station getNextStation() {
		int nStations = QuorumConsensus.NSTATIONS;
		// check if there is another station
		if (mStationMask == (1<<nStations)-1)
			return null;
		// find a station not returned before
		int i;
		do {
			i = mRandom.nextInt(nStations);
		}
		while ((mStationMask & (1<<i)) != 0);
		// record selected station in mask
		mStationMask |= (1<<i);
		// return the station
		return mQC.getStation(i);
	}
	

	// ASSUMPTION! either read or write is called once

	public Data read() {
		Station s = getNextStation();
		return s.getData();
	}

	public void write(Data d) {
		for (Station s : mQC.getStations())
			s.setData(d);
	}
}
