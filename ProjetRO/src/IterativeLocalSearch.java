import java.util.Random;

public class IterativeLocalSearch extends Search {

	private int maxNbEval;
	private int maxNbEvalLocal;
	private Random random;
	private RandomInit randomInit;

	public IterativeLocalSearch(Random random, Eval eval, int nbEvalMaxLocal,
			int nbEvalMaxTotal) {
		super(eval);

		this.random = random;
		this.maxNbEval = nbEvalMaxTotal;
		this.maxNbEvalLocal = nbEvalMaxLocal;
		this.randomInit = new RandomInit(random, eval.size());
	}

	private void localSearch() {

		this.eval.apply(this.solution);

		int nbEval = 1;
		int i = 0, j = 0;
		double currentFitness = 0.0;
		int solTemp;

		while (nbEval < this.maxNbEvalLocal) {
			currentFitness = this.solution.fitness();

			i = this.random.nextInt(this.eval.size());
			j = this.random.nextInt(this.eval.size());
			solTemp = this.solution.solution[i];
			this.solution.solution[i] = this.solution.solution[j];
			this.solution.solution[j] = solTemp;

			this.eval.apply(this.solution);
			nbEval++;

			if (this.solution.fitness() <= currentFitness) {
				this.solution.solution[j] = this.solution.solution[i];
				this.solution.solution[i] = solTemp;
				this.solution.fitness(currentFitness);
			}
		}
	}

	private void perturbation() {

		int i = 0, j = 0;
		int solTemp;

		int nPertubation = this.random.nextInt(11);

		for (int k = 0; k < nPertubation; k++) {
			i = this.random.nextInt(this.eval.size());
			j = this.random.nextInt(this.eval.size());
			solTemp = this.solution.solution[i];
			this.solution.solution[i] = this.solution.solution[j];
			this.solution.solution[j] = solTemp;
		}
	}

	public void run() {
		this.solution = new Solution();
		this.randomInit.init(this.solution);

		localSearch();

		Solution bestSol = this.solution.clone();

		this.eval.apply(this.solution);

		double bestEval = this.solution.fitness();

		int i = 0;

		while (i < this.maxNbEval) {
			perturbation();
			localSearch();

			this.eval.apply(this.solution);

			if (this.solution.fitness() > bestEval) {
				bestEval = this.solution.fitness();
				bestSol = this.solution.clone();
			}

			i++;
		}

		this.solution = bestSol.clone();
	}
}