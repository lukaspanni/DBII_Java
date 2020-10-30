
package dhbw;


public class QuorumConsensus
{
	public static final int NROUNDS = 100000;
	public static final int NSTATIONS = 4;
	public static final int READ_QUORUM = 4;
	public static final int WRITE_QUORUM = 4;

	private Station[] mStations = new Station[NSTATIONS];

	public Station[] getStations() {
		return mStations;
	}

	public Station getStation(int i) {
		return mStations[i];
	}
	
	
	private void run() {
		// create stations with given weight
		mStations[0] = new Station(3);
		mStations[1] = new Station(1);
		mStations[2] = new Station(2);
		mStations[3] = new Station(2);
		
		if (!checkQuorumConditions()) {
			System.out.println("Quorum conditions not met");
			return;
		}
		
		doReadWrite();
	}


	private boolean checkQuorumConditions() {
		return true;
	}
	
	
	private void doReadWrite() {
		Data d = new Data();
		for (int i=0; i<NROUNDS; i++) {
			d = read();
			write(new Data(d.getValue()+1, d.getVersion()+1));
		}

		// final value should be 1000 + NROUNDS - 1
		if (d.getValue() == 1000 + NROUNDS - 1)
			System.out.println("Final value OK");
		else
			System.out.println("Wrong final value " + d.getValue());

		// final version should be NROUNDS
		if (d.getVersion() == NROUNDS)
			System.out.println("Final version OK");
		else
			System.out.println("Wrong final version " + d.getVersion());
	}
	
	private Data read() {
		Request rq = new Request(this);
		return rq.read();
	}
	
	private void write(Data d) {
		Request wq = new Request(this);
		wq.write(d);
	}

	
	public static void main(String[] args) {
		QuorumConsensus qc = new QuorumConsensus();
		qc.run();
	}
}

