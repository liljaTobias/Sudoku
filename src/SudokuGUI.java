

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SudokuGUI extends Application{

	private Sudoku sudoku = new Sudoku();
	private TilePane board;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Sudoku Solver: H4xx0r Edition");
		
		BorderPane root = new BorderPane();
		board = initBoard();
		HBox control = addControl();
		
		root.setCenter(board);
		root.setBottom(control);
		
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
	
	private void updateBoard(TilePane tile){
		tile.getChildren().clear();
		for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				tile.getChildren().add(createCell(col, row));
			}
		}
	}
	
	private TilePane initBoard(){
		TilePane tile = new TilePane();
		
		tile.setHgap(4);
		tile.setVgap(4);
		tile.setPadding(new Insets(0, 10, 0, 10));
		tile.setPrefColumns(9);
		tile.setPrefRows(9);
		//tile.setStyle("-fx-background-color: rgba(255, 215, 0, 0.1);");
		tile.setAlignment(Pos.CENTER);
		
		tile.getChildren().clear();
		for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				tile.getChildren().add(createCell(col, row));
			}
		}
		
		return tile;
	}
	
	//Skapar "celler" med textfält
	private TextField createCell(int col, int row){
		TextField txtfield = new TextField();
		String text;
		
		if(sudoku.getValueAt(col, row) != 0){
			text = Integer.toString(sudoku.getValueAt(col, row));
		}else{
			text = "0";
		}
		
		txtfield.setText(text);
		txtfield.setPrefWidth(45);
		txtfield.setPrefHeight(45);
		txtfield.setAlignment(Pos.CENTER);
		//txtfield.setEditable(false);
		txtfield.setStyle("-fx-font-weight: bold;");
		if(((row < 6 && row > 2) && (col < 6 && col > 2)) 
				|| ((row < 3) && (col < 3)) //Vänster Upp 
				|| ((row < 3) && (col > 5))	//Höger Upp
				|| ((row > 5) && (col > 5))	//Höger Ner
				|| ((row > 5) && (col < 3)))//Vänster Ner
		{	
			txtfield.setStyle("-fx-background-color: rgba(255, 178, 0, 0.9);" + "-fx-font-weight: bold;");
		}
		
		return txtfield;
	}
	
	//LÄgger till knappar i en Box
	private HBox addControl(){
		HBox hbox = new HBox();
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		Button set = new Button("Set");
		
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		
		solve.setPrefSize(100, 20);
		solve.setOnAction(e -> solve());
		clear.setPrefSize(100, 20);
		clear.setOnAction(e -> clear());
		set.setPrefSize(100,  20);
		set.setOnAction(e -> set());
		
		hbox.getChildren().addAll(solve, clear, set);
		
		return hbox;
	}
	
	//Knapp-funktioner
	private void solve(){
//		try{
//			sudoku.solve();
//		}catch(Exception e){
//			sudoku.clear();
//			System.err.println(e.getMessage());
//		}finally{
//			updateBoard(board);
//		}
		if(sudoku.solve()){
			updateBoard(board);
		}else{
			sudoku.clear();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("YOLO SWAGGINS");
			alert.setHeaderText("Sudokut SWAG ERROR");
			alert.setContentText("Sudokut har inte tillräckligt med SWAG");		
		}
//		if(sudoku.solve() == false){
////			Alert alert = new Alert(AlertType.ERROR);
////			alert.setTitle("YOLO SWAGGINS");
////			alert.setHeaderText("Sudokut SWAG ERROR");
////			alert.setContentText("Sudokut har inte tillräckligt med SWAG");
//			System.out.println("ERROR");
//		}else{			
//			updateBoard(board);
//		}
	}
	
	//Hämtar värden från textfälten
	private void set(){
		int newset[][] = new int[9][9];
		int array[] = new int[board.getChildren().size()];
		
		for(int i = 0; i < board.getChildren().size(); i++){			
			TextField tmp = (TextField) board.getChildren().get(i);		
			//System.out.println(tmp.getText());
			array[i] = Integer.parseInt(tmp.getText());
		}
		
		int n = 0;
		for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				newset[col][row] = array[n];
				n++;
			}
		}
		
		sudoku.setBoard(newset);
		updateBoard(board);
	}
	
	private void clear(){
		sudoku.clear();
		updateBoard(board);
	}
}
