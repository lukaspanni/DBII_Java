
package dhbw;


public class Station
{
	private Data mData = new Data(1000, 1);
	private int mWeight;
	
	public Station(int weight) {
		mWeight = weight;
	}
	
	public void setData(Data d) {
		mData = d;
	}
	public Data getData() {
		return mData;
	}
	
	public int getWeight() {
		return mWeight;
	}
}

