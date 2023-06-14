package strikes;

import pieces.ListOfPieces;
import pieces.Piece;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Strike {
    Point endPoint;
    ArrayList<Point> struckPieces;

    Strike(Point inEndPoint, ArrayList<Point> inStruckPieces){
        endPoint = inEndPoint;
        struckPieces = new ArrayList<>(inStruckPieces);
    }

    Strike(Point inEndPoint, Point inStruckPiece){
        endPoint = inEndPoint;
        struckPieces = new ArrayList<>();
        struckPieces.add(inStruckPiece);
    }

    Strike(Strike inStrike){
        endPoint = inStrike.getEndPoint();
        struckPieces = new ArrayList<>(inStrike.getStruckPieces());
    }

    void executeStrike(ListOfPieces inListOfPieces){
        //System.out.println("List of stuck pieces: "+struckPieces);
        for (Point struckPiece:struckPieces){
            inListOfPieces.erasePiece((int)struckPiece.getX(),(int)struckPiece.getY());
        }
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public ArrayList<Point> getStruckPieces() {
        return struckPieces;
    }

    public void addStruckPieceAndMove(Point inPoint, Point inEndPoint){
        struckPieces.add(inPoint);
        endPoint = inEndPoint;
    }
    public int strikeValue(){
        return struckPieces.size();
    }
}
