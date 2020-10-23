
package dhbw;


public abstract class Data
	implements Comparable<Data>
{
	public int C;

	public Data() {
		C = randVal();
	}

	protected int randVal() {
		return (int)Math.round(DistributedJoin.MAXVALUE * Math.random());
	}

	@Override
	public boolean equals(Object rhs) {
		if (!(this.getClass().equals(rhs.getClass())))
			return false;
		return (compareTo((Data)rhs) == 0);
	}
}

