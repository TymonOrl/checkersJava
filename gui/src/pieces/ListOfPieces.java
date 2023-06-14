package pieces;

import boardPackage.Selection;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListOfPieces {
    private ArrayList<Piece> listOfPieces;

    public ListOfPieces(ArrayList<Piece> inListOfPieces){
        listOfPieces = inListOfPieces;
    }

    public Piece getPiece(int x, int y){
        Piece tmp = null;

        for (Piece piece:listOfPieces){
            if( (piece.getX() == x)
                    && (piece.getY() == y)){
                tmp = piece;
            }
        }

        return tmp;
    }

    public void movePiece(int x, int y, int newX, int newY){
        Piece tmp = null;
        tmp = getPiece(x, y);

        if(tmp != null){
            tmp.move(newX,newY);
            /*
            // Striking other pieces
            if( Math.abs( x-newX )>1 ){
                if(newX - x > 0){
                    // Bottom right
                    if(newY - y > 0){
                        x += 1;
                        y += 1;
                        while ( x!= newX ){
                            erasePiece(x, y);
                            x += 1;
                            y += 1;
                        }
                    }else { // Top right
                        x += 1;
                        y -= 1;
                        while ( x!= newX ){
                            erasePiece(x, y);
                            x += 1;
                            y -= 1;
                        }
                    }
                }else {
                    // Bottom Left
                    if(newY - y > 0){
                        x -= 1;
                        y += 1;
                        while ( x!= newX ){
                            erasePiece(x, y);
                            x -= 1;
                            y += 1;
                        }
                    }else { // Top left
                        x -= 1;
                        y -= 1;
                        while ( x!= newX ){
                            erasePiece(x, y);
                            x -= 1;
                            y -= 1;
                        }
                    }
                }
                //System.out.println("x: "+x+"\t y: "+y);
            }

             */

        }
    }

    public Point singleStrike (int x, int y, int newX, int newY){
        Point struckedPiece = null;

        if( Math.abs( x-newX )>1 ) {
            if (newX - x > 0) {
                // Bottom right
                if (newY - y > 0) {
                    int LOLX = newX - 1;
                    int LOLY = newY - 1;
                    System.out.println("x: " + LOLX + " y: " + LOLY);
                    struckedPiece = new Point(newX - 1, newY - 1);
                } else { // Top right
                    int LOLX = newX + 1;
                    int LOLY = newY - 1;
                    System.out.println("x: " + LOLX + " y: " + LOLY);
                    struckedPiece = new Point(newX + 1, newY - 1);
                }
            } else {
                // Bottom Left
                if (newY - y > 0) {
                    int LOLX = newX -1;
                    int LOLY = newY +1;
                    System.out.println("x: "+LOLX+" y: "+LOLY);
                    struckedPiece = new Point(newX-1,newY+1);
                } else { // Top left
                    int LOLX = newX -1;
                    int LOLY = newY -1;
                    System.out.println("x: "+LOLX+" y: "+LOLY);
                    struckedPiece = new Point(newX-1,newY-1);
                }
            }
        }
        movePiece(x, y, newX, newY);
        return struckedPiece;
    }

    public void erasePiece(int x, int y){
        for (Iterator<Piece> it = listOfPieces.iterator(); it.hasNext();) {
            Piece next = it.next();
            if((next.getX() == x)
                    && (next.getY() == y))
                it.remove(); //metoda remove() iteratora
        }
    }

    public void update(int inSize, Selection selection){
        // Crowning Pieces
        for (int x = 0; x < inSize; x++) {
            Piece toChange = null;

            Piece piece = getPiece(x, 0);
            if (piece != null) {
                if (piece.getType() == Checker.WCHECKER) {
                    toChange = piece;
                }
            }
            piece = getPiece(x, inSize - 1);
            if (piece != null) {
                if (piece.getType() == Checker.BCHECKER) {
                    toChange = piece;
                }
            }

            if (toChange != null) {
                erasePiece(toChange.getX(), toChange.getY());
                if (toChange.getType() == Checker.WCHECKER) {
                    listOfPieces.add(new WKingChecker(toChange.getX(), toChange.getY()));
                } else {
                    listOfPieces.add(new BKingChecker(toChange.getX(), toChange.getY()));
                }
            }
        }

        // End of game
        int blackPieces=0,whitePieces=0;
        for (Piece piece:listOfPieces){
            if(piece.getType()==Checker.BCHECKER
                    || piece.getType()==Checker.BKINGCHECKER){
                blackPieces++;
            } else if (piece.getType()==Checker.WCHECKER
                    || piece.getType()==Checker.WKINGCHECKER){
                whitePieces++;
            }
        }

        if( blackPieces == 0 || whitePieces == 0 ){
            if(!selection.isFinished()){
                selection.gameOver();
            }
        }
    }

    public ArrayList<Piece> getListOfPieces() {
        return listOfPieces;
    }

}
