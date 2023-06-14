package pieces;

import boardPackage.CheckersPositions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    protected int positionX = 0,positionY = 0;
    Checker type = null;
    protected BufferedImage image = null;

    abstract public void draw(Graphics2D g2d, int fieldSize, int margin);
    abstract public ArrayList<Point> possibleMoves(
            CheckersPositions checkersPositions, int sizeOfTheBoard);
    abstract public ArrayList<Point> possibleStrikes(
            CheckersPositions checkersPositions, int sizeOfTheBoard);
    public void move(int x,int y){
        positionX = x;
        positionY = y;
    }
    public Checker getType(){
        return type;
    }
    public int getX(){
        return positionX;
    }
    public int getY(){
        return positionY;
    }

}
