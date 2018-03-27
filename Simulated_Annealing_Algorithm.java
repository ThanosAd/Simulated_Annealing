/*import java.util.Random;
public class Simulated_Annealing_Algorithm {
	
	//Hitori_Puzzle Current;
	//Hitori_Puzzle NextPuzzle;
	//Hitori_Puzzle Next;
	int [][] Puzzle;
	int currentCost = 0;
	int iterations = 0;
	double T = 10.0;
	int FailedTries = 0;
	public Simulated_Annealing_Algorithm(int[][] aPuzzle){
		Puzzle = aPuzzle;
		
		//System.out.println(Initial.toString());
		
	}
	
	public Hitori_Puzzle Algorithm(){
		Current = new Hitori_Puzzle(Initial.getPuzzle());
		currentCost = Current.CostCalculator();
		while(currentCost>0 && FailedTries != 6*Current.getPuzzle().length*Current.getPuzzle().length){
			System.out.println(Current.toString());
			System.out.println(currentCost);
			System.out.println(6*Current.getPuzzle().length*Current.getPuzzle().length);
			System.out.println(T);
			System.out.println(FailedTries);
			iterations = iterations +1 ;
			for(int i=0; i<200 ;i++){
				//NextPuzzle = Initial.getRandomState();
				//Next = new Hitori_Puzzle(NextPuzzle.getRandomState());
				//int NextPuzzle[][] = Next.getPuzzle();
				int NextCost = NextPuzzle.CostCalculator();
				int DCost = NextCost - currentCost;
				Random rand = new Random();			
				//System.out.println(Initial.toString());
				if(DCost<=0 || rand.nextInt(100) < Math.exp(-DCost/T)*100){
					Current = Next;
					currentCost = NextCost;
					//System.out.println(Math.exp(-DCost/T));
				}else{
					FailedTries = FailedTries +1;
					//System.out.println(Current.toString());
				}
			}
			T = T*0.999;
			
		}
		return Current;
	}
	
	
	
}*/
