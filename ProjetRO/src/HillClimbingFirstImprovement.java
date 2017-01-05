import java.util.Random;

public class HillClimbingFirstImprovement extends Search {

	private int nbStep;
	private int maxNbEval;
	private double maxFitness;
	private Random random;
	private RandomInit randomInit;

	public HillClimbingFirstImprovement(Random random, Eval eval, int maxEval,
			double max) {
		super(eval);

		this.maxNbEval = maxEval;
		this.maxFitness = max;

		this.random = random;
		this.randomInit = new RandomInit(random, eval.size());
	}

	public void run() {

		this.solution = new Solution();
		this.randomInit.init(this.solution);
		this.eval.apply(this.solution);

		this.nbEval = 1;

		int i = 0;
		int j = 0;

		double currentFitness = 0.0;

		this.nbStep = 0;

		while (this.nbEval < this.maxNbEval && this.solution.fitness() < this.maxFitness) {
			currentFitness = this.solution.fitness();

			i = this.random.nextInt(this.eval.size());
			j = this.random.nextInt(this.eval.size());
			
			int solTemp = this.solution.solution[i];
			
			this.solution.solution[i] = this.solution.solution[j];
			this.solution.solution[j] = solTemp;

			this.eval.apply(this.solution);

			this.nbEval += 1;

			if (this.solution.fitness() <= currentFitness) {
				this.solution.solution[j] = this.solution.solution[i];
				this.solution.solution[i] = solTemp;
				this.solution.fitness(currentFitness);
			} else {
				this.nbStep += 1;
			}
		}
	}

	public String info() {
		return "" + this.nbStep;
	}
}
