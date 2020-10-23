
package dhbw;


public class DataR extends Data
{
	public int A;
	public int B;
	// C is inherited from Data

	public DataR() {
		A = randVal();
		B = randVal();
	}

	// compare DataR by order C, A, B
	@Override
    public int compareTo(Data rhs) {
		if (C < rhs.C) return -1;
		if (C > rhs.C) return  1;
		if (rhs instanceof DataR) {
			DataR r = (DataR)rhs;
			if (A < r.A) return -1;
			if (A > r.A) return  1;
			if (B < r.B) return -1;
			if (B > r.B) return  1;
		}
		return 0;
    }

	@Override
    public String toString() {
		return String.format("A:%2d B:%2d C:%2d", A, B, C);
	}
}

