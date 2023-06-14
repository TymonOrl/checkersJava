package pieces;

import boardPackage.CheckersPositions;

import java.awt.*;
import java.util.ArrayList;

public class BKingChecker extends Piece {
    public BKingChecker(int inX, int inY){
        type = Checker.BKINGCHECKER;

        positionX = inX;
        positionY = inY;
    }


    @Override
    public void draw(Graphics2D g2d, int fieldSize, int margin) {
        int xOffset = positionX*fieldSize + margin;
        int yOffset = positionY*fieldSize + margin;

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(xOffset,  yOffset, fieldSize, fieldSize);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawOval(xOffset ,  yOffset, fieldSize, fieldSize);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(xOffset-2 ,  yOffset-3, fieldSize, fieldSize);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawOval(xOffset-2 ,  yOffset-3, fieldSize, fieldSize);
        g2d.setColor(Color.YELLOW);
        g2d.drawOval(xOffset-2+10 ,  yOffset-3+10, fieldSize-20, fieldSize-20);
    }

    @Override
    public ArrayList<Point> possibleMoves(CheckersPositions checkersPositions, int sizeOfTheBoard) {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();

        // Bottom right
        int x = positionX+1;
        int y = positionY+1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 0 ){
                possibleMoves.add(new Point(x,y));
            } else if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                break;
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x += 1;
            y += 1;
        }

        // Bottom Left
        x = positionX-1;
        y = positionY+1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 0 ){
                possibleMoves.add(new Point(x,y));
            } else if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2) {
                break;
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x -= 1;
            y += 1;
        }

        // Top Left
        x = positionX-1;
        y = positionY-1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 0 ){
                possibleMoves.add(new Point(x,y));
            } else if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                break;
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x -= 1;
            y -= 1;
        }

        // Top Right
        x = positionX+1;
        y = positionY-1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 0 ){
                possibleMoves.add(new Point(x,y));
            } else if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                break;
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x += 1;
            y -= 1;
        }

        return possibleMoves;
    }

    @Override
    public ArrayList<Point> possibleStrikes(CheckersPositions checkersPositions, int sizeOfTheBoard) {
        ArrayList<Point> possibleStikes = new ArrayList<Point>();

        // Bottom right
        int x = positionX+1;
        int y = positionY+1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                x += 1;
                y += 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                        break;
                    } else {
                        break;
                    }
                }
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x += 1;
            y += 1;
        }

        // Bottom Left
        x = positionX-1;
        y = positionY+1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                x -= 1;
                y += 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                        break;
                    } else {
                        break;
                    }
                }
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x -= 1;
            y += 1;
        }

        // Top Left
        x = positionX-1;
        y = positionY-1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                x -= 1;
                y -= 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                        break;
                    } else {
                        break;
                    }
                }
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x -= 1;
            y -= 1;
        }

        // Top Right
        x = positionX+1;
        y = positionY-1;

        while( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard) ){
            if(checkersPositions.get(x,y) == 1 || checkersPositions.get(x,y) == 2){
                x += 1;
                y -= 1;
                if ( (x >= 0 && x<sizeOfTheBoard) && (y >= 0 && y < sizeOfTheBoard)){
                    if(checkersPositions.get(x,y) == 0 ){
                        possibleStikes.add(new Point(x,y));
                        break;
                    } else {
                        break;
                    }
                }
            } else if (checkersPositions.get(x,y) == 3 || checkersPositions.get(x,y) == 4) {
                break;
            }
            x += 1;
            y -= 1;
        }

        return possibleStikes;
    }
}
