
public class Sudoku {

	private int board[][];
	
	public Sudoku(){
		this.board = new int[9][9];
		clear();
		initBoard();
	}
	
	public void printBoard(){
		for(int i = 0; i < 9; i++){
			if((i % 3) == 0){
				System.out.print("-------------------------" + "\n");
			}
			for(int n = 0; n <9; n++){
				if((n % 3) == 0){
					System.out.print("| ");
				}
				System.out.print(board[i][n] + " ");
			}
			System.out.print("|" + "\n");
		}
		System.out.print("-------------------------");
	}
	
	public int getValueAt(int col, int row){
		return board[col][row];
	}
	
	public boolean solve(){
		boolean tmp = solve(0,0);
		System.out.println(tmp);
		return tmp;
	}
	
	public void clear(){
		for( int row = 0; row < 9; row++ ){
			for( int col = 0; col < 9; col++ ){	        	 
				board[row][col] = 0 ;
			}			
		}
	}
	
	public void setBoard(int input[][]){
		clear();
		
		board = input;
	}
	
	private void initBoard(){
		clear();

		//Rad 1
		board[0][2] = 8;
		board[0][5] = 8;
		board[0][7] = 6;
		board[0][8] = 2;
		//Rad 2
		board[1][8] = 5;
		//Rad 3
		board[2][0] = 1;
		board[2][2] = 2;
		board[2][3] = 5;
		//Rad 4
		board[3][3] = 2;
		board[3][4] = 1;
		board[3][7] = 9;
		//Rad 5
		board[4][1] = 5;
		board[4][6] = 6;
		//Rad 6
		board[5][0] = 6;
		board[5][7] = 2;
		board[5][8] = 8;
		//Rad 7
		board[6][0] = 4;
		board[6][1] = 1;
		board[6][3] = 6;
		board[6][5] = 8;
		//Rad 8
		board[7][0] = 8;
		board[7][1] = 6;
		board[7][4] = 3;
		board[7][6] = 1;
		//Rad 9
		board[8][6] = 4;
	}

	private boolean solve(int i, int j){
		//Ifall en lösning har hittats
		if(i > 8){
			return true;
		}
		
		//Ifall positionen inte är satt
		if(board[i][j] != 0){
			//next(i, j);
			if(j < 8){
				if(solve(i, j+1)){
					return true;
				}
			}else{
				if(solve(i+1, 0)){
					return true;
				}
			}
		}
		//Ifall positionen är satt
		else{
			for(int n = 1; n < 10; n++){
				//Kollar ifall siffran "n" inte finns på någon av raderna
				if(rowIsOk(i, n) && colIsOk(j, n) && boxIsOk(i, j ,n)){
					board[i][j] = n;
					//next(i, j);
					if(j < 8){
						if(solve(i, j+1)){
							return true;
						}
					}else{
						if(solve(i+1, 0)){
							return true;
						}
					}
				}
			}
			board[i][j] = 0;
		}

		return false;
	}
	
	//Kollar om raden är ok
	private boolean rowIsOk(int row, int num){
		for(int i = 0; i < 9; i++){
			if(board[row][i] == num){
				return false;
			}
		}		
		return true;
	}
	
	//Kollar om kolumnen är ok
	private boolean colIsOk(int col, int num){
		for(int i = 0; i < 9; i++){
			if(board[i][col] == num){
				return false;
			}
		}		
		return true;
	}
	
	private boolean boxIsOk(int row, int col, int num){
		row = (row/3) * 3;
		col = (col/3) * 3;
		
		for(int r = 0; r < 3; r++){
			for(int c = 0; c < 3; c++){
				if(board[row+r][col+c] == num){
					return false;
				}
			}
		}
		
		return true;
	}

}
