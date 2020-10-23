
package dhbw;


public class DataS extends Data
{
	// C is inherited from Data
	public int D;
	public int E;

	public DataS() {
		D = randVal();
		E = randVal();
	}

	// compare DataS by order C, D, E
	@Override
    public int compareTo(Data rhs) {
		if (C < rhs.C) return -1;
		if (C > rhs.C) return  1;
		if (rhs instanceof DataS) {
			DataS r = (DataS)rhs;
			if (D < r.D) return -1;
			if (D > r.D) return  1;
			if (E < r.E) return -1;
			if (E > r.E) return  1;
		}
		return 0;
    }

	@Override
    public String toString() {
		return String.format("C:%2d D:%2d E:%2d", C, D, E);
	}
}

