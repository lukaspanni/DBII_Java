
package dhbw;


public class DataResult extends Data
{
	public int A;
	public int B;
	// C is inherited from Data
	public int D;
	public int E;

	public DataResult(Data d1, Data d2) {
		// one must be DataR, the other DataS
		DataR r;
		DataS s;
		if (d1 instanceof DataR) {
			r = (DataR)d1;
			s = (DataS)d2;
		} else {
			r = (DataR)d2;
			s = (DataS)d1;			
		}
		A = r.A; B = r.B; C = r.C;
		D = s.D; E = s.E;
	}
	
	public void genRandom() {
		throw new UnsupportedOperationException();
	}

	// compare DataResult by order C, A, B, D, E
	@Override
    public int compareTo(Data rhs) {
		if (C < rhs.C) return -1;
		if (C > rhs.C) return  1;
		if (rhs instanceof DataResult) {
			DataResult r = (DataResult)rhs;
			if (A < r.A) return -1;
			if (A > r.A) return  1;
			if (B < r.B) return -1;
			if (B > r.B) return  1;
			if (D < r.D) return -1;
			if (D > r.D) return  1;
			if (E < r.E) return -1;
			if (E > r.E) return  1;
		}
		return 0;
    }

	@Override
    public String toString() {
		return String.format("A:%2d B:%2d C:%2d D:%2d E:%2d", A, B, C, D, E);
	}
}

