import java.util.Random;

public class RandomInit extends Init {
	
	private int size;
	private Random random;

	public RandomInit(Random random, int n) {
		this.random = random;
		this.size = n;
	}

	public void init(Solution solution) {
		if (solution.size() != this.size) {
			solution.solution = new int[size];
		}
		for (int i = 0; i < solution.size(); i++) {
			solution.solution[i] = i;
		}
		shuffleArray(solution.solution);
	}

	public void shuffleArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int randomPosition = random.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
	}
}
