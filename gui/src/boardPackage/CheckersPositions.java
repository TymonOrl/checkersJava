package boardPackage;

import pieces.BChecker;
import pieces.BKingChecker;
import pieces.Checker;
import pieces.Piece;

import java.util.ArrayList;

public class CheckersPositions {
	int[][] tiles ;

	
	CheckersPositions(int sizeOfTheBoard) {
		tiles = new int[sizeOfTheBoard][sizeOfTheBoard];
		boolean white[][] = new boolean[sizeOfTheBoard][sizeOfTheBoard];
		int playersRows = (sizeOfTheBoard - 2)/2;

		for (int x = 0; x < sizeOfTheBoard; x++) {
			for (int y = 0; y < sizeOfTheBoard; y++) {
				if( y%2==0 && x%2==1){
					white[x][y] = false;
				}
				else if ( y%2==1 && x%2==0 ) {
					white[x][y] = false;
				}
				else {
					white[x][y] = true;
				}
			}
		}

		for (int x = 0; x < sizeOfTheBoard; x++) {
			for (int y = 0; y < sizeOfTheBoard; y++) {
				if(y < playersRows  &&  white[x][y] == false){
					tiles[x][y] = Checker.BCHECKER.value;
				} else if (y > (sizeOfTheBoard - playersRows - 1)
						&& white[x][y] == false) {
					tiles[x][y] = Checker.WCHECKER.value;
				}
				else{
					tiles[x][y] = 0;
				}
			}
		}
	}


	public CheckersPositions(int [][]inTable){
		tiles = inTable;
	}

	public int get(int x, int y){
		return tiles[x][y];
	}

	public void changeTo(int x, int y, int in){
		tiles[x][y] = in;
	}

	public void update(ArrayList<Piece> inListOfPieces, int sizeOfTheBoard){
		for (int x = 0; x < sizeOfTheBoard; x++) {
			for (int y = 0; y < sizeOfTheBoard; y++) {
				tiles[x][y] = 0;
			}
		}
		for(Piece piece: inListOfPieces) {
			tiles[piece.getX()][piece.getY()] =
					piece.getType().value;
		}
	}

	public ArrayList<Piece> updateList(int sizeOfTheBoard){
		ArrayList<Piece> listOfPieces = new ArrayList<Piece>();

		for (int x = 0; x < sizeOfTheBoard; x++) {
			for (int y = 0; y < sizeOfTheBoard; y++) {
				if(tiles[x][y] == Checker.WCHECKER.value){
					listOfPieces.add(new pieces.WChecker(x,y));
				} else if (tiles[x][y] == Checker.WKINGCHECKER.value) {
					listOfPieces.add(new pieces.WKingChecker(x,y));
				} else if (tiles[x][y] == Checker.BCHECKER.value) {
					listOfPieces.add(new BChecker(x,y));
				} else if (tiles[x][y] == Checker.BKINGCHECKER.value) {
					listOfPieces.add(new BKingChecker(x,y));
				}
			}
		}

		return listOfPieces;
	}

	public void print(int sizeOfTHeBoard){
		for (int y = 0; y < sizeOfTHeBoard ; y++) {
			for (int x = 0; x < sizeOfTHeBoard; x++) {
				System.out.print((x+y*sizeOfTHeBoard) + " " +
						tiles[x][y] + " \t" );
			}
			System.out.print("\n");
		}
	}


	public int[][] getTiles() {
		return tiles;
	}
}
