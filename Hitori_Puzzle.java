import java.util.Random;
import java.util.ArrayList;
//import Artificial_Intelligence_Astar.


public class Hitori_Puzzle {

	//int[][] Puzzle;
	//ArrayList<int[]> CanBeBlack = new ArrayList<int[]>();
	
	public Hitori_Puzzle(){
		//System.out.println(aPuzzle[1][1]);
		//Puzzle = aPuzzle;
	}
	
	public int CostCalculator(boolean[][] BWpuzzle, int [][] Puzzle){
		boolean current;
		int currentNum;
		int cost=0;
		for(int i=0;i<Puzzle.length;i++){
			for(int j=0;j<Puzzle[i].length;j++){
				current = BWpuzzle[i][j];
				currentNum = Puzzle[i][j];
				if(current == false){
					if(j<Puzzle[i].length-1){
						if(BWpuzzle[i][j+1] == false){
							cost = cost + 1;
							//System.out.println("dexia");
						}
					}
					if(j>0){
						if(BWpuzzle[i][j-1] == false){
							cost = cost + 1;
							//System.out.println("aristera");
						}
					}
					if(i<Puzzle.length-1){
						if(BWpuzzle[i+1][j] == false){
							cost = cost + 1;
							//System.out.println("katw");
						}
					}
					if(i>0){
						if(BWpuzzle[i-1][j]==false){
							cost = cost + 1;
							//System.out.println("panw");
						}
					}	
				}
				for(int k=j+1;k<Puzzle[i].length;k++){
					if(current != false){
						if (currentNum == Puzzle[i][k] && BWpuzzle[i][k] != false){
							cost = cost + 1;
							
						}
					}
					
				} 
			}
		}
		for(int j=0;j<Puzzle.length;j++){
			for(int i=0;i<Puzzle.length;i++){
				current = BWpuzzle[i][j];
				currentNum = Puzzle[i][j];
				for(int k=i+1;k<Puzzle[i].length;k++){
					if(current != false){
						if (currentNum == Puzzle[k][j] && BWpuzzle[k][j] != false){
							cost = cost + 1;
							
						}
					}
					
				} 
			}
		}
		ArrayList<ArrayList<Integer>> BinMaze = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<Puzzle.length;i++){
			ArrayList<Integer> Row = new ArrayList<Integer>();
			BinMaze.add(Row);
			for(int j=0;j<Puzzle.length;j++){
				if(BWpuzzle[i][j]==true){
					BinMaze.get(i).add(0);
				}else{
					BinMaze.get(i).add(1);
				}
			}
		}
		Maze_Creator Maze = new Maze_Creator(Puzzle.length,0);
		Maze.setMaze(BinMaze);
		int t=0;
		for(int i=0;i<Puzzle.length;i++){
			for(int j=0;j<Puzzle.length;j++){
				t=j+1;
				for(int x=i;x<Puzzle.length;x++){
					for(int k=t;k<Puzzle.length;k++){
						//Maze_Creator Maze = new Maze_Creator(Puzzle.length,0);
						//Maze.setMaze(BinMaze);
						if(BinMaze.get(i).get(j)==0&&BinMaze.get(x).get(k)==0){
							PathFinder PathG1 = new PathFinder(j,i,k,x,Maze);
							PathG1.ManchattanDistanceCalculator();
							PathG1.GCalculator();
							ArrayList<Node> Open = PathG1.getOpen();
							if(Open.size()==0){
								cost = cost+1;
							}
						}
					}
					t=0;
				}
			}
		}
		return cost;
	}
	
	public boolean[][] getRandomState(boolean [][] BWpuzzle, ArrayList<int[] > CanBeBlack,int[][] Hitori){
		Random rand = new Random();
		//int current;
		int x = rand.nextInt(CanBeBlack.size()-1);
		//int y = rand.nextInt(10);
			
			int i = CanBeBlack.get(x)[0];
			int j = CanBeBlack.get(x)[1];
			//System.out.println("{"+i+","+j+"}");
			//System.out.println(Hitori[i][j]);
			//int k = CanBeBlack.get(y)[0];
			//int l = CanBeBlack.get(y)[1];
			//int l
			for(int k=0;k<CanBeBlack.size();k++){
				if((Hitori[i][j]==Hitori[i][CanBeBlack.get(k)[1]])&&(j!=CanBeBlack.get(k)[1])){
					BWpuzzle[i][j]=true;
					ArrayList<ArrayList<Integer>> BinMaze = new ArrayList<ArrayList<Integer>>();
					for(int a=0;a<Hitori.length;a++){
						ArrayList<Integer> Row = new ArrayList<Integer>();
						BinMaze.add(Row);
						for(int b=0;b<Hitori.length;b++){
							if(BWpuzzle[a][b]==true){
								BinMaze.get(a).add(0);
							}else{
								BinMaze.get(a).add(1);
							}
						}
					}
					BinMaze.get(i).add(CanBeBlack.get(k)[1], 1);
					BinMaze.get(i).remove(CanBeBlack.get(k)[1]+1);
					if(CanBeBlack.get(k)[1]<Hitori.length-1){
						BinMaze.get(i).add(CanBeBlack.get(k)[1]+1, 0);
						BinMaze.get(i).remove(CanBeBlack.get(k)[1]+1+1);
					}
					if(CanBeBlack.get(k)[1]>0){
						BinMaze.get(i).add(CanBeBlack.get(k)[1]-1, 0);
						BinMaze.get(i).remove(CanBeBlack.get(k)[1]-1+1);
					}
					if(i<Hitori.length-1){
						BinMaze.get(i+1).add(CanBeBlack.get(k)[1], 0);
						BinMaze.get(i+1).remove(CanBeBlack.get(k)[1]+1);
					}
					if(i>0){
						BinMaze.get(i-1).add(CanBeBlack.get(k)[1], 0);
						BinMaze.get(i-1).remove(CanBeBlack.get(k)[1]+1);
					}
					Maze_Creator Maze = new Maze_Creator(Hitori.length,0);
					Maze.setMaze(BinMaze);
					int flag=0;
					if(CanBeBlack.get(k)[1]<Hitori.length-1){
						PathFinder PathG1 = new PathFinder(i,j,i,CanBeBlack.get(k)[1]+1,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(CanBeBlack.get(k)[1]>0){
						PathFinder PathG1 = new PathFinder(i,j,i,CanBeBlack.get(k)[1]-1,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(i<Hitori.length-1){
						PathFinder PathG1 = new PathFinder(i,j,i+1,CanBeBlack.get(k)[1],Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}	
					}
					if(i>0){
						PathFinder PathG1 = new PathFinder(i,j,i-1,CanBeBlack.get(k)[1],Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(flag==0){
						BWpuzzle[i][CanBeBlack.get(k)[1]]=false;
						if(CanBeBlack.get(k)[1]<Hitori.length-1){
							BWpuzzle[i][CanBeBlack.get(k)[1]+1] = true;
						}
						if(CanBeBlack.get(k)[1]>0){
							BWpuzzle[i][CanBeBlack.get(k)[1]-1] = true;
						}
						if(i<Hitori.length-1){
							BWpuzzle[i+1][CanBeBlack.get(k)[1]] = true;
						}
						if(i>0){
							BWpuzzle[i-1][CanBeBlack.get(k)[1]] = true;
						}
					}/*else{
						BWpuzzle[i][j]=false;
						if(j<Hitori.length-1){
							BWpuzzle[i][j+1] = true;
						}
						if(j>0){
							BWpuzzle[i][j-1] = true;
						}
						if(i<Hitori.length-1){
							BWpuzzle[i+1][j] = true;
						}
						if(i>0){
							BWpuzzle[i-1][j] = true;
						}
					}*/
					
					/*if(BWpuzzle[i][j]==true){
						if(BWpuzzle[i][CanBeBlack.get(k)[1]]==true){
							BWpuzzle[i][CanBeBlack.get(k)[1]]=false;
							//System.out.println("case 1 1");
							//System.out.println(i+","+CanBeBlack.get(k)[1]);
							
							if(CanBeBlack.get(k)[1]<Hitori.length-1){
								BWpuzzle[i][CanBeBlack.get(k)[1]+1] = true;
							}
							if(CanBeBlack.get(k)[1]>0){
								BWpuzzle[i][CanBeBlack.get(k)[1]-1] = true;
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][CanBeBlack.get(k)[1]] = true;
							}
							if(i>0){
								BWpuzzle[i-1][CanBeBlack.get(k)[1]] = true;
							}
							break;
						}else{
							BWpuzzle[i][CanBeBlack.get(k)[1]]=true;
							BWpuzzle[i][j]=false;
							//System.out.println("case 1 2");
							//System.out.println(i+","+CanBeBlack.get(k)[1]);
							
							if(j<Hitori.length-1){
								BWpuzzle[i][j+1] = true;
							}
							if(j>0){
								BWpuzzle[i][j-1] = true;
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][j] = true;
							}
							if(i>0){
								BWpuzzle[i-1][j]= true;
							}
							break;
						}
						
					}else {
						if(BWpuzzle[i][CanBeBlack.get(k)[1]]==false){
							BWpuzzle[i][CanBeBlack.get(k)[1]]=true;
							//System.out.println("case 1 3");
							//System.out.println(i+","+CanBeBlack.get(k)[1]);
							
							if(j<Hitori.length-1){
								BWpuzzle[i][j+1] = true;
							}
							if(j>0){
								BWpuzzle[i][j-1] = true;
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][j] = true;
							}
							if(i>0){
								BWpuzzle[i-1][j]= true;
							}
							break;
						}else{
							BWpuzzle[i][j]=true;
							BWpuzzle[i][CanBeBlack.get(k)[1]]=false;
							//System.out.println("case 1 4");
							//System.out.println(i+","+CanBeBlack.get(k)[1]);
							
							if(CanBeBlack.get(k)[1]<Hitori.length-1){
								BWpuzzle[i][CanBeBlack.get(k)[1]+1] = true;
							}
							if(CanBeBlack.get(k)[1]>0){
								BWpuzzle[i][CanBeBlack.get(k)[1]-1] = true;
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][CanBeBlack.get(k)[1]] = true;
							}
							if(i>0){
								BWpuzzle[i-1][CanBeBlack.get(k)[1]]= true;
							}
							break;
						}
					}*/
				}
			}
			for(int k=0;k<CanBeBlack.size();k++){
				if((Hitori[i][j]==Hitori[CanBeBlack.get(k)[0]][j])&&(i!=CanBeBlack.get(k)[0])){
					BWpuzzle[i][j]=true;
					ArrayList<ArrayList<Integer>> BinMaze = new ArrayList<ArrayList<Integer>>();
					for(int q=0;q<Hitori.length;q++){
						ArrayList<Integer> Row = new ArrayList<Integer>();
						BinMaze.add(Row);
						for(int w=0;w<Hitori.length;w++){
							if(BWpuzzle[q][w]==true){
								BinMaze.get(q).add(0);
							}else{
								BinMaze.get(q).add(1);
							}
						}
					}
					BinMaze.get(CanBeBlack.get(k)[0]).add(j, 1);
					BinMaze.get(CanBeBlack.get(k)[0]).remove(j+1);
					if(j<Hitori.length-1){
						BinMaze.get(CanBeBlack.get(k)[0]).add(j+1, 0);
						BinMaze.get(CanBeBlack.get(k)[0]).remove(j+1+1);
					}
					if(j>0){
						BinMaze.get(CanBeBlack.get(k)[0]).add(j-1, 0);
						BinMaze.get(CanBeBlack.get(k)[0]).remove(j-1+1);
					}
					if(CanBeBlack.get(k)[0]<Hitori.length-1){
						BinMaze.get(CanBeBlack.get(k)[0]+1).add(j, 0);
						BinMaze.get(CanBeBlack.get(k)[0]+1).remove(j+1);
					}
					if(CanBeBlack.get(k)[0]>0){
						BinMaze.get(CanBeBlack.get(k)[0]-1).add(j, 0);
						BinMaze.get(CanBeBlack.get(k)[0]-1).remove(j+1);
					}
					Maze_Creator Maze = new Maze_Creator(Hitori.length,0);
					Maze.setMaze(BinMaze);
					int flag=0;
					if(j<Hitori.length-1){
						PathFinder PathG1 = new PathFinder(i,j,CanBeBlack.get(k)[0],j+1,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(j>0){
						PathFinder PathG1 = new PathFinder(i,j,CanBeBlack.get(k)[0],j-1,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(CanBeBlack.get(k)[0]<Hitori.length-1){
						PathFinder PathG1 = new PathFinder(i,j,CanBeBlack.get(k)[0]+1,j,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}	
					}
					if(CanBeBlack.get(k)[0]>0){
						PathFinder PathG1 = new PathFinder(i,j,CanBeBlack.get(k)[0]-1,j,Maze);
						PathG1.ManchattanDistanceCalculator();
						PathG1.GCalculator();
						ArrayList<Node> Open = PathG1.getOpen();
						if(Open.size()==0){
							flag = flag+1;
						}
					}
					if(flag==0){
						BWpuzzle[CanBeBlack.get(k)[0]][j]=false;
						if(j<Hitori.length-1){
							BWpuzzle[CanBeBlack.get(k)[0]][j+1] = true;
						}
						if(j>0){
							BWpuzzle[CanBeBlack.get(k)[0]][j-1] = true;	
						}
						if(CanBeBlack.get(k)[0]<Hitori.length-1){
							BWpuzzle[CanBeBlack.get(k)[0]+1][j] = true;	
						}
						if(CanBeBlack.get(k)[0]>0){
							BWpuzzle[CanBeBlack.get(k)[0]-1][j] = true;
						}
					}/*else{
						BWpuzzle[i][j]=false;
						if(j<Hitori.length-1){
							BWpuzzle[i][j+1] = true;
						}
						if(j>0){
							BWpuzzle[i][j-1] = true;	
						}
						if(i<Hitori.length-1){
							BWpuzzle[i+1][j] = true;	
						}
						if(i>0){
							BWpuzzle[i-1][j] = true;
						}
					}*/
					
					
					//System.out.println(Hitori[i][j]);
					/*if(BWpuzzle[i][j]==true){
						if(BWpuzzle[CanBeBlack.get(k)[0]][j]==true){
							BWpuzzle[CanBeBlack.get(k)[0]][j]=false;
							//System.out.println("case 2 1");
							//System.out.println(CanBeBlack.get(k)[0]+","+j);
							
							if(j<Hitori.length-1){
								BWpuzzle[CanBeBlack.get(k)[0]][j+1] = true;
							}
							if(j>0){
								BWpuzzle[CanBeBlack.get(k)[0]][j-1] = true;	
							}
							if(CanBeBlack.get(k)[0]<Hitori.length-1){
								BWpuzzle[CanBeBlack.get(k)[0]+1][j] = true;	
							}
							if(CanBeBlack.get(k)[0]>0){
								BWpuzzle[CanBeBlack.get(k)[0]-1][j] = true;
							}
							break;
						}else{
							BWpuzzle[i][j]=false;
							BWpuzzle[CanBeBlack.get(k)[0]][j]=true;
							//System.out.println("case 2 2");
							//System.out.println(CanBeBlack.get(k)[0]+","+j);
							
							if(j<Hitori.length-1){
								BWpuzzle[i][j+1] = true;	
							}
							if(j>0){
								BWpuzzle[i][j-1] = true;	
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][j] = true;	
							}
							if(i>0){
								BWpuzzle[i-1][j]= true;
							}
							break;
						}
						
					}else {
						if(BWpuzzle[CanBeBlack.get(k)[0]][j]==false){
							BWpuzzle[CanBeBlack.get(k)[0]][j]=true;
							//System.out.println("case 2 3");
							//System.out.println(CanBeBlack.get(k)[0]+","+j);
							
							if(j<Hitori.length-1){
								BWpuzzle[i][j+1] = true;	
							}
							if(j>0){
								BWpuzzle[i][j-1] = true;	
							}
							if(i<Hitori.length-1){
								BWpuzzle[i+1][j] = true;	
							}
							if(i>0){
								BWpuzzle[i-1][j]= true;
							}
							break;
						}else{
							BWpuzzle[i][j]=true;
							BWpuzzle[CanBeBlack.get(k)[0]][j]=false;
							//System.out.println("case 2 4");
							//System.out.println(CanBeBlack.get(k)[0]+","+j);
							
							if(j<Hitori.length-1){
								BWpuzzle[CanBeBlack.get(k)[0]][j+1] = true;	
							}
							if(j>0){
								BWpuzzle[CanBeBlack.get(k)[0]][j-1] = true;	
							}
							if(CanBeBlack.get(k)[0]<Hitori.length-1){
								BWpuzzle[CanBeBlack.get(k)[0]+1][j] = true;	
							}
							if(CanBeBlack.get(k)[0]>0){
								BWpuzzle[CanBeBlack.get(k)[0]-1][j]= true;
							}
							break;
						}
						
					}*/
				}
			}
		return BWpuzzle;
		
	}
	
	
	
	public String toString(boolean [][] Puzzle,int[][] Hitori){
		String M = "";
		for(int i=0; i<Puzzle.length;i++){
			M = M + "\n";
			for(int j=0; j<Puzzle.length;j++){
				if(Puzzle[i][j] == false){
					M = M + "*"+"|";
				}else{
					M = M + Hitori[i][j]+"|";
				}
				//M = M + Puzzle[i][j];
			}
		}
		return M;
	}

}