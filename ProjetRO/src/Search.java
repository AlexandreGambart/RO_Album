abstract public class Search {

	protected Eval eval;
	protected Solution solution;
	protected int nbEval;

	public Search(Eval evalFunc) {
		this.eval = evalFunc;
		this.nbEval = 0;
	}

	abstract public void run();

	public Solution solution() {
		return this.solution;
	}

	public double fitness() {
		return this.solution.fitness();
	}

	public int nbEval() {
		return this.nbEval;
	}

	public String info() {
		return "";
	}
}
