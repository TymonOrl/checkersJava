package pieces;

import boardPackage.CheckersPositions;

import java.awt.*;
import java.util.ArrayList;

public class WChecker extends Piece {

    public WChecker(int inX, int inY){
        type = Checker.WCHECKER;

        positionX = inX;
        positionY = inY;
    }

    @Override
    public void draw(Graphics2D g2d, int fieldSize, int margin) {
        int xOffset = positionX*fieldSize + margin;
        int yOffset = positionY*fieldSize + margin;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(xOffset ,  yOffset, fieldSize, fieldSize);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawOval(xOffset,  yOffset, fieldSize, fieldSize);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(xOffset-2 ,  yOffset-3, fieldSize, fieldSize);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawOval(xOffset-2 ,  yOffset-3, fieldSize, fieldSize);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawOval(xOffset-2+10 ,  yOffset-3+10, fieldSize-20, fieldSize-20);
    }


    @Override
    public ArrayList<Point> possibleMoves(
            CheckersPositions checkersPositions, int sizeOfTheBoard) {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        // Left up
        int x = positionX-1;
        int y = positionY-1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 0 ) {
                possibleMoves.add(new Point(x, y));
            }
        }
        // Right up
        x = positionX+1;
        y = positionY-1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 0 ){
                possibleMoves.add(new Point(x,y));
            }
        }

        return possibleMoves;
    }

    @Override
    public ArrayList<Point> possibleStrikes(CheckersPositions checkersPositions, int sizeOfTheBoard) {
        ArrayList<Point> possibleStikes = new ArrayList<Point>();
        // strike Right down
        int x = positionX+1;
        int y = positionY+1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4){
                x += 1;
                y += 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                    }
                }
            }
        }
        // strike Left down
        x = positionX-1;
        y = positionY+1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4){
                x -= 1;
                y += 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                    }
                }
            }
        }
        // Sriking only Left up
        x = positionX-1;
        y = positionY-1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4){
                x -= 1;
                y -= 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                    }
                }
            }
        }
        // Sriking only Right up
        x = positionX+1;
        y = positionY-1;
        if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
            if(checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4){
                x += 1;
                y -= 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                    }
                }
            }
        }

        return possibleStikes;
    }
}
