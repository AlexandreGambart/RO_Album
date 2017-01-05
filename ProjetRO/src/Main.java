import java.util.Random;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int maxEval = 1000;
	    int maxFit = 20000;
	    Random random = new Random(100);
	    
		AlbumEval eval = new AlbumEval(55); // 55 photos

		Search search = new HillClimbingFirstImprovement(random, eval, maxEval, maxFit);
		  
		search.run();

	    System.out.println("Nombre d'évaluation : " + search.nbEval() + "\nInfo : " + search.info() + "\nSolution : " + search.solution().fitness() + "\n");
	    
	    System.out.println("la solution proposée : " + search.solution().toString());
	    
	    
	}
}
