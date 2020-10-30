
package dhbw;


public class Data
{
	private int mValue;
	private int mVersion;
	
	public Data() {
		mValue = -1;
		mVersion = -1;
	}
	public Data(int value, int version) {
		mValue = value;
		mVersion = version;
	}
	
	public int getValue() {
		return mValue;
	}
	public int getVersion() {
		return mVersion;
	}
	
	public void setValue(int value) {
		mValue = value;
		++mVersion;
	}
}

