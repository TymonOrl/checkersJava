package boardPackage;

import Frames.MainMenuFrame;
import pieces.Checker;
import pieces.ListOfPieces;
import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

import strikes.ListOfStikes;

public class Selection {
    int currentX = 0, currentY = 0;
    boolean on = false;
    boolean loaded = false;
    boolean firstPlayersTurn = true;
    boolean finished = false;
    ArrayList<Point> listOfPossiblePieces = new ArrayList<Point>();
    ArrayList<Point> listOfPossibleMovesOfSelectedPiece = new ArrayList<Point>();
    ArrayList<Point> listOfPossibleStrikesOfSelectedPiece = new ArrayList<Point>();
    ListOfStikes localListOfStrikes;
    Selection(){}

    public void gameOver(){
        finished = true;
        if(!loaded) {
            MainMenuFrame.gameFrame.endGame(!isFirstPlayersTurn());
        } else {
            MainMenuFrame.gameFrame.endLoadedGame(!isFirstPlayersTurn());
        }
    }

    public void setListOfPossiblePieces(
            ListOfPieces listOfPieces,
            CheckersPositions checkersPositions,
            int sizeOfTheBoard){
        if (!finished){
            for (Piece piece:listOfPieces.getListOfPieces()) {
                if (isWhitePieceCorrectlyChosen(piece) ||
                        isBlackPieceCorrectlyChosen(piece)) {
                    listOfPossiblePieces.add(new Point(piece.getX(), piece.getY()));
                }
            }
        }
        for (int i = 0; i<listOfPossiblePieces.size(); i++){
            Piece tmpPiece = listOfPieces.getPiece((int)listOfPossiblePieces.get(i).getX(),
                    (int)listOfPossiblePieces.get(i).getY());
            if(tmpPiece!=null){
                if(tmpPiece.possibleMoves(checkersPositions, sizeOfTheBoard).size()==0
                && tmpPiece.possibleStrikes(checkersPositions, sizeOfTheBoard).size()==0){
                    listOfPossiblePieces.remove(i);
                    i--;
                }
                //System.out.println("x: "+listOfPossiblePieces.get(i).getX()+" y: "+listOfPossiblePieces.get(i).getY());
            }
        }

        if(listOfPossiblePieces.size() == 0){
            if(!finished){
                gameOver();
            }
        }
    }
    void selectPieceAndUpdatePossibleMoves(Piece inPiece, CheckersPositions checkersPositions,
                                           int sizeOfTheBoard, ListOfPieces listOfPieces){
        if( inPiece != null ){
            for(Point possiblePiece:listOfPossiblePieces) {
                if (possiblePiece.getX() == inPiece.getX() &&
                        possiblePiece.getY() == inPiece.getY()) {
                    on = true;

                    currentX = inPiece.getX();
                    currentY = inPiece.getY();

                    listOfPossibleMovesOfSelectedPiece = inPiece.possibleMoves(
                            checkersPositions, sizeOfTheBoard);

                    localListOfStrikes = new ListOfStikes(inPiece,checkersPositions,sizeOfTheBoard, listOfPieces);
                    listOfPossibleStrikesOfSelectedPiece = localListOfStrikes.getListOfPossibleStikes();

                }
            }
        }
    }

    boolean isClickMove(int x, int y){
        if(on){
            for(Point point: listOfPossibleMovesOfSelectedPiece) {
                if( (point.getX() == x)
                        && (point.getY() == y)){
                    listOfPossiblePieces = new ArrayList<>();
                    return true;
                }
            }
            for(Point point: listOfPossibleStrikesOfSelectedPiece) {
                if( (point.getX() == x)
                        && (point.getY() == y)){
                    listOfPossiblePieces = new ArrayList<>();
                    return true;
                }
            }
        }

        return false;
    }

    boolean isClickAStrike(int x, int y){
        for(Point point: listOfPossibleStrikesOfSelectedPiece) {
            if( (point.getX() == x)
                    && (point.getY() == y)){
                listOfPossiblePieces = new ArrayList<>();
                //System.out.println("strike");
                return true;
            }
        }
        return false;
    }

    public void nextPlayer() {
        firstPlayersTurn = !firstPlayersTurn;
        on = false;
        MainMenuFrame.gameFrame.changePlayer(firstPlayersTurn);
    }

    public void executeStrike(Point inPoint, ListOfPieces inListOfPieces){
        localListOfStrikes.executeStrike(inPoint, inListOfPieces);
    }

    public void setFirstPlayersTurn(boolean in){
        loaded = true;
        if(in != firstPlayersTurn){
            firstPlayersTurn = !firstPlayersTurn;
            on = false;
        }
    }

    // Functions
    private boolean isWhitePieceCorrectlyChosen(Piece inPiece){
        return ( firstPlayersTurn && (
                    ( inPiece.getType() == Checker.WCHECKER ) ||
                    ( inPiece.getType() == Checker.WKINGCHECKER )
                                     )
        );
    }

    private boolean isBlackPieceCorrectlyChosen(Piece inPiece){
        return ( !firstPlayersTurn && (
                        ( inPiece.getType() == Checker.BCHECKER ) ||
                        ( inPiece.getType() == Checker.BKINGCHECKER )
                                      )
        );
    }

    public boolean isFirstPlayersTurn() {
        return firstPlayersTurn;
    }

    public int getX() {
        return currentX;
    }
    public int getY() {
        return currentY;
    }
    public boolean isOn() {
        return on;
    }

    public boolean isFinished(){
        return finished;
    }

    public ArrayList<Point> getListOfPossibleMoves() {
        return listOfPossibleMovesOfSelectedPiece;
    }

    public ArrayList<Point> getListOfPossibleStrikes() {
        return listOfPossibleStrikesOfSelectedPiece;
    }
}
