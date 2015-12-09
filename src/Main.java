
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku sudoku = new Sudoku();
		
		//sudoku.printBoard();
		if(sudoku.solve()){			
			sudoku.printBoard();
		}else{
			System.out.println("No solution found");
		}
	}

}
