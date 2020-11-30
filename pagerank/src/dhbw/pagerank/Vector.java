
package dhbw.pagerank;


public class Vector
{
	private double[] values;
	private int size;

	public Vector(int size, double initialValue) {
		this.size = size;
		this.values = new double[size];
		for (int i=0; i<size; i++)
			this.values[i] = initialValue;
	}

	public int size() {
		return size;
	}

	// Get value at position i
	public double get(int i) {
		return values[i];
	}

	// Set value at position i
	public void set(int i, double value) {
		values[i] = value;
	}

	// Add scalar value to this
	public void addScalar(double v) {
		for (int i=0; i<size; i++)
			set(i, v+get(i));
	}

	public void dump() {
		System.out.print("VECTOR:");
		for (double d : values)
			System.out.format(" %.5f", d);
		System.out.println();
	}
}
