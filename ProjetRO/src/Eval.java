abstract public class Eval {

	protected int pbSize;

	public Eval(int n) {
		this.pbSize = n;
	}

	public int size() {
		return this.pbSize;
	}

	public void setSize(int n) {
		this.pbSize = n;
	}

	abstract public void apply(Solution solution);
};
