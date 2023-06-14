package strikes;

import boardPackage.CheckersPositions;
import pieces.ListOfPieces;
import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class ListOfStikes {
    ArrayList<Strike> listOfStrikes;

    public ListOfStikes(Piece inPiece, CheckersPositions checkersPositions,int sizeOfTheBoard, ListOfPieces inListOfPieces){
        int startX = inPiece.getX();
        int startY = inPiece.getY();
        listOfStrikes = new ArrayList<Strike>();
        ArrayList<Point> possibleStrikes;

        int amountOfBranches = 1;
        int currentBranch = 0;
        int oldX = startX;
        int oldY = startY;
        ArrayList<Point>  branches = new ArrayList<>();
        branches.add(new Point(inPiece.getX(),inPiece.getY()));

        CheckersPositions tmpCheckersPosition = new CheckersPositions(checkersPositions.getTiles());
        while (currentBranch<amountOfBranches) {
            int tmpPossibleMoves = inPiece.possibleStrikes(tmpCheckersPosition, sizeOfTheBoard).size();
            possibleStrikes = inPiece.possibleStrikes(tmpCheckersPosition, sizeOfTheBoard);

            if(tmpPossibleMoves == 0){
                currentBranch++;
                if(currentBranch<amountOfBranches){
                    oldX = (int)branches.get(currentBranch).getX();
                    oldY = (int)branches.get(currentBranch).getY();
                    inPiece.move(oldX, oldY);
                }
            } else if (tmpPossibleMoves == 1) {
                int newX = (int) possibleStrikes.get(0).getX();
                int newY = (int) possibleStrikes.get(0).getY();

                Point strucked = singleStrike(oldX,oldY,newX,newY);

                if (currentBranch>=listOfStrikes.size()) {
                    listOfStrikes.add(new Strike(new Point(newX, newY), strucked));
                } else{
                    listOfStrikes.get(currentBranch).addStruckPieceAndMove(strucked,possibleStrikes.get(0));
                }
                tmpCheckersPosition.changeTo((int)strucked.getX(),(int)strucked.getY(),0);

                inPiece.move(newX,newY);
                oldX = inPiece.getX();
                oldY = inPiece.getY();
            } else if (tmpPossibleMoves > 1) {
                if (listOfStrikes.size()==0) {
                    for (int i = tmpPossibleMoves-1; i > 0; i--) {
                        int newX = (int) possibleStrikes.get(i).getX();
                        int newY = (int) possibleStrikes.get(i).getY();

                        Point strucked = singleStrike(oldX,oldY,newX,newY);

                        branches.add(new Point(newX, newY));
                        listOfStrikes.add(new Strike(new Point(newX, newY), strucked));
                        tmpCheckersPosition.changeTo((int)strucked.getX(),(int)strucked.getY(),0);

                        amountOfBranches++;
                    }

                    int newX = (int) possibleStrikes.get(0).getX();
                    int newY = (int) possibleStrikes.get(0).getY();

                    Point strucked = singleStrike(oldX,oldY,newX,newY);

                    if (amountOfBranches>listOfStrikes.size()) {
                        listOfStrikes.add(new Strike(new Point(newX, newY), strucked));
                    } else{
                        listOfStrikes.get(currentBranch).addStruckPieceAndMove(strucked,possibleStrikes.get(0));
                    }
                    tmpCheckersPosition.changeTo((int)strucked.getX(),(int)strucked.getY(),0);

                    inPiece.move(newX,newY);
                    oldX = inPiece.getX();
                    oldY = inPiece.getY();
                } else {
                    for (int i = tmpPossibleMoves-1; i > 0; i--) {
                        int newX = (int) possibleStrikes.get(i).getX();
                        int newY = (int) possibleStrikes.get(i).getY();

                        Point strucked = singleStrike(oldX,oldY,newX,newY);

                        branches.add(new Point(newX, newY));
                        amountOfBranches++;
                        listOfStrikes.add(new Strike(listOfStrikes.get(currentBranch))); //hmmm
                        listOfStrikes.get(amountOfBranches-1).addStruckPieceAndMove(strucked, new Point(newX, newY));
                        tmpCheckersPosition.changeTo((int)strucked.getX(),(int)strucked.getY(),0);
                    }

                    int newX = (int) possibleStrikes.get(0).getX();
                    int newY = (int) possibleStrikes.get(0).getY();

                    Point strucked = singleStrike(oldX,oldY,newX,newY);

                    listOfStrikes.get(currentBranch).addStruckPieceAndMove(strucked,possibleStrikes.get(0));
                    tmpCheckersPosition.changeTo((int)strucked.getX(),(int)strucked.getY(),0);

                    inPiece.move(newX,newY);
                    oldX = inPiece.getX();
                    oldY = inPiece.getY();
                }

            }
        }
        inPiece.move(startX, startY);
    }

    public ArrayList<Point> getListOfPossibleStikes(){
        ArrayList<Point> listOfPossibleStikes = new ArrayList<>();

        for (Strike strike:listOfStrikes){
            listOfPossibleStikes.add(strike.getEndPoint());
        }

        return listOfPossibleStikes;
    }

    public void executeStrike(Point inPoint, ListOfPieces inListOfPieces){
        for (Strike strike:listOfStrikes){
            if(inPoint.getX()==strike.getEndPoint().getX()
            && inPoint.getY()==strike.getEndPoint().getY()){
                strike.executeStrike(inListOfPieces);
            }
        }
    }

    public Point singleStrike (int x, int y, int newX, int newY){
        Point struckedPiece = null;

        if( Math.abs( x-newX )>1 ) {
            if (newX - x > 0) {
                // Bottom right
                if (newY - y > 0) {
                    struckedPiece = new Point(newX - 1, newY - 1);
                } else { // Top right
                    struckedPiece = new Point(newX - 1, newY + 1);
                }
            } else {
                // Bottom Left
                if (newY - y > 0) {
                    struckedPiece = new Point(newX+1,newY-1);
                } else { // Top left
                    struckedPiece = new Point(newX+1,newY+1);
                }
            }
        }
        return struckedPiece;
    }

}
