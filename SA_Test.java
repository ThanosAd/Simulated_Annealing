import java.util.ArrayList;
import java.util.Random;

public class SA_Test {
	public static void main(String[] args){
		int HitoriArray[][] = { {4,8,1,6,3,2,5,7},
				{3,6,7,2,1,6,5,4},
				{2,3,4,8,2,8,6,1},
				{4,1,6,5,7,7,3,5},
				{7,2,3,1,8,5,1,2},
				{3,5,6,7,3,1,8,4},
				{6,4,2,3,5,4,7,8},
				{8,7,1,4,2,3,5,6}};
		boolean [][] CurrentArray ={{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true}};
		int current;
		ArrayList<int[]> CanBeBlack = new ArrayList<int[]>();
		for(int i=0;i<HitoriArray.length;i++){
			for(int j=0;j<HitoriArray[i].length;j++){
				current = HitoriArray[i][j];
				for(int k=j+1;k<HitoriArray[i].length;k++){
					if(current != HitoriArray.length + 1){
						if (current == HitoriArray[i][k]){
							int [] CoordinatesNext ={i,k};
							int[] CoordinatesCurrent = {i,j};
							boolean flag1 = true;
							boolean flag2 = true;
							for(int q=0;q<CanBeBlack.size();q++){
								if(CanBeBlack.get(q)[0]==CoordinatesNext[0]&&CanBeBlack.get(q)[1]==CoordinatesNext[1]){
									flag2 =false;
								}
								if(CanBeBlack.get(q)[0]==CoordinatesCurrent[0]&&CanBeBlack.get(q)[1]==CoordinatesNext[1]){
									flag1 = false;
								}
							}
							if(flag1){
								CanBeBlack.add(CoordinatesCurrent);
							}
							if(flag2){
								CanBeBlack.add(CoordinatesNext);
							}
						}
					}	
				} 
			}
		}
		for(int j=0;j<HitoriArray.length;j++){
			for(int i=0;i<HitoriArray.length;i++){
				current = HitoriArray[i][j];
				for(int k=i+1;k<HitoriArray[i].length;k++){
					if(current != HitoriArray.length + 1){
						if (current==HitoriArray[k][j]){
							int [] Coordinates ={k,j};
							int [] CoordinatesCurrent = {i,j};
							boolean flag1 = true;
							boolean flag2 = true;
							for(int q=0;q<CanBeBlack.size();q++){
								if(CanBeBlack.get(q)[0]==Coordinates[0]&&CanBeBlack.get(q)[1]==Coordinates[1]){
									flag2 =false;	
								}
								if(CanBeBlack.get(q)[0]==CoordinatesCurrent[0]&&CanBeBlack.get(q)[1]==CoordinatesCurrent[1]){
									flag1 = false;	
								}
							}
							if(flag1){
								CanBeBlack.add(CoordinatesCurrent);	
							}
							if(flag2){
								CanBeBlack.add(Coordinates);
							}	
						}
					}	
				} 
			}
		}
		
		
		
		Hitori_Puzzle Current = new Hitori_Puzzle();
		
		int currentCost = 0;
		int iterations = 0;
		double T = 10.0;
		int FailedTries = 0;
		currentCost = Current.CostCalculator(CurrentArray,HitoriArray);
		while(currentCost>0 && FailedTries < 6*CurrentArray.length*CurrentArray.length){
			int i =0;
			while(currentCost>0 && i<2*CurrentArray.length*CurrentArray.length ){
				iterations = iterations +1 ;
				int HitoriArray1[][] = { {4,8,1,6,3,2,5,7},
						{3,6,7,2,1,6,5,4},
						{2,3,4,8,2,8,6,1},
						{4,1,6,5,7,7,3,5},
						{7,2,3,1,8,5,1,2},
						{3,5,6,7,3,1,8,4},
						{6,4,2,3,5,4,7,8},
						{8,7,1,4,2,3,5,6}};
				Hitori_Puzzle	NextPuzzle = new Hitori_Puzzle();
				boolean[][] NextArray = NextPuzzle.getRandomState(CurrentArray,CanBeBlack,HitoriArray1);
				int NextCost = NextPuzzle.CostCalculator(NextArray,HitoriArray1);
				int DCost = NextCost - currentCost;
				Random rand = new Random();			
				//System.out.println(Current.toString(CurrentArray,HitoriArray1));
				//System.out.println(currentCost);
				//System.out.println(1/(1+Math.exp(DCost/T)));
				//System.out.println(T);
				//System.out.println(currentCost);
				if(DCost<0 ){
					CurrentArray = NextArray;
					currentCost = NextCost;
				}else if(DCost>0){
					if(rand.nextDouble() < 1/(1+Math.exp(DCost/T))){
						CurrentArray = NextArray;
						currentCost = NextCost;
					}else{
						FailedTries = FailedTries +1;
					}
				}
				i=i+1;
			}
			T = T*0.999;	
		}
		System.out.println(Current.toString(CurrentArray,HitoriArray));
		System.out.println(currentCost);
		System.out.println(iterations);
	}
}
