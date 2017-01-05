public class Solution {

	public int[] solution;
	
	private double fitness;

	public Solution() {
		this.solution = null;
		this.fitness = 0;
	}

	public Solution(Solution s) {
		if (s.solution != null) {
			this.solution = new int[s.solution.length];
			for (int i = 0; i < this.solution.length; i++)
				this.solution[i] = s.solution[i];
		} else {
			this.solution = null;
		}

		this.fitness = s.fitness();
	}

	public Solution clone() {
		Solution solution = new Solution(this);
		return solution;
	}

	public void copy(Solution s) {
		if (s.solution != null) {
			if (this.solution == null)
				this.solution = new int[s.solution.length];
			for (int i = 0; i < this.solution.length; i++)
				this.solution[i] = s.solution[i];
		} else {
			this.solution = null;
		}
		this.fitness = s.fitness();
	}

	public void fitness(double fit) {
		this.fitness = fit;
	}

	public double fitness() {
		return this.fitness;
	}
	
	public int size() {
		if (this.solution == null) {
			return 0;
		} else {
			return this.solution.length;
		}
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < this.solution.length; i++) {
			s += new Picture(this.solution[i]).name() + " ";
		}
		return s;
	}
}
