package boardPackage;

import Frames.MainMenuFrame;
import fileManagement.FileLoading;
import fileManagement.FileSaving;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Board extends JButton{

    int sizeOfTheBoard;
    ListOfPieces listOfPieces;
    BufferedImage image = null;
    int margin;
    CheckersPositions checkersPositions;
    Selection selection = new Selection();


    Color darkTile = new Color(0,0,0);
    Color lightTile = new Color(250,250,250);

    public Board(int inSize) {
        super.setBorderPainted(false);
        super.setFocusPainted(false);
        super.setContentAreaFilled(false);

        margin = 10;
        sizeOfTheBoard = inSize;
        checkersPositions = new CheckersPositions(sizeOfTheBoard);
        listOfPieces = new ListOfPieces(checkersPositions.updateList(sizeOfTheBoard));
        selection.setListOfPossiblePieces(
                listOfPieces,
                checkersPositions,
                sizeOfTheBoard);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int fieldSize = getFieldSize(getWidth(),getHeight());

                int x = e.getX()-margin;
                int y = e.getY()-margin;

                if( (x > 0 && x<sizeOfTheBoard*fieldSize) &&
                        (y > 0 && y<sizeOfTheBoard*fieldSize) ){

                    x = x/fieldSize;
                    y = y/fieldSize;

                    if( selection.isClickMove(x, y) ) {
                        if (selection.isClickAStrike(x, y)){
                            selection.executeStrike(new Point(x,y), listOfPieces);
                            MainMenuFrame.soundEffects.playStrike();
                        } else {
                            MainMenuFrame.soundEffects.playMove();
                        }
                        listOfPieces.movePiece(selection.getX(),
                                selection.getY(), x, y);
                        selection.nextPlayer();
                        checkersPositions.update(listOfPieces.getListOfPieces(), sizeOfTheBoard);
                        selection.setListOfPossiblePieces(
                                listOfPieces,
                                checkersPositions,
                                sizeOfTheBoard);
                    }

                    selection.selectPieceAndUpdatePossibleMoves(listOfPieces.getPiece(x,y),
                            checkersPositions, sizeOfTheBoard, listOfPieces);

                    listOfPieces.update(sizeOfTheBoard, selection);
                    checkersPositions.update(listOfPieces.getListOfPieces(), sizeOfTheBoard);

                }
            }
        });
    }

    public Board(int inSize, int[][] placements, boolean isFirstPlayerTurn) {
        super.setBorderPainted(false);
        super.setFocusPainted(false);
        super.setContentAreaFilled(false);

        margin = 10;
        sizeOfTheBoard = inSize;
        checkersPositions = new CheckersPositions(placements);
        listOfPieces = new ListOfPieces(checkersPositions.updateList(sizeOfTheBoard));
        selection.setFirstPlayersTurn(isFirstPlayerTurn);
        selection.setListOfPossiblePieces(listOfPieces,checkersPositions,sizeOfTheBoard);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int fieldSize = getFieldSize(getWidth(),getHeight());

                int x = e.getX()-margin;
                int y = e.getY()-margin;

                if( (x > 0 && x<sizeOfTheBoard*fieldSize) &&
                        (y > 0 && y<sizeOfTheBoard*fieldSize) ){

                    x = x/fieldSize;
                    y = y/fieldSize;

                    if( selection.isClickMove(x, y) ) {
                        if (selection.isClickAStrike(x, y)){
                            selection.executeStrike(new Point(x,y), listOfPieces);
                            MainMenuFrame.soundEffects.playStrike();
                        } else {
                            MainMenuFrame.soundEffects.playMove();
                        }
                        listOfPieces.movePiece(selection.getX(),
                                selection.getY(), x, y);
                        selection.nextPlayer();
                        checkersPositions.update(listOfPieces.getListOfPieces(), sizeOfTheBoard);
                        selection.setListOfPossiblePieces(
                                listOfPieces,
                                checkersPositions,
                                sizeOfTheBoard);
                    }

                    selection.selectPieceAndUpdatePossibleMoves(listOfPieces.getPiece(x,y),
                            checkersPositions, sizeOfTheBoard, listOfPieces);

                    listOfPieces.update(sizeOfTheBoard, selection);
                    checkersPositions.update(listOfPieces.getListOfPieces(), sizeOfTheBoard);

                }
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int fieldSize = getFieldSize(getWidth(), getHeight());

        if(image == null){
            // Drawing Board
            g2d.setColor(darkTile);
            g2d.fillRect(margin, margin, fieldSize*sizeOfTheBoard,
                    fieldSize*sizeOfTheBoard);

            g2d.setColor(lightTile);
            for(int y = 0; y < sizeOfTheBoard; y++ ) {
                for(int x = 0;x < sizeOfTheBoard; x++) {
                    if( (x+y)%2 == 0 ) {
                        g2d.fillRect( fieldSize * x + margin,
                                fieldSize * y + margin, fieldSize, fieldSize);
                    }
                }
            }


            if( selection.isOn() ) {
                //Selected Piece Rec
                g2d.setColor(new Color(51, 133, 255, 200));
                g2d.fillRect((fieldSize * selection.getX() + margin),
                        (fieldSize * selection.getY() + margin), fieldSize, fieldSize);

                //Possible moves
                g2d.setColor(new Color(255, 26, 26, 200));
                for (Point point : selection.getListOfPossibleMoves()) {
                    g2d.fillRect((int) (fieldSize * point.getX() + margin),
                            (int) (fieldSize * point.getY() + margin), fieldSize, fieldSize);
                }
                if (selection.getListOfPossibleStrikes() != null){
                    for (Point point : selection.getListOfPossibleStrikes()) {
                        g2d.fillRect((int) (fieldSize * point.getX() + margin),
                                (int) (fieldSize * point.getY() + margin), fieldSize, fieldSize);
                    }
                }
            }

        }

        for(Piece piece: listOfPieces.getListOfPieces()) {
            piece.draw(g2d, fieldSize, margin);
        }
    }

    int getFieldSize(int width, int height){
        int limiter = 0;

        if(width<height) {
            limiter = width;
        }
        else {
            limiter = height;
        }
        int fieldSize = (limiter-2*margin)/sizeOfTheBoard;

        return fieldSize;
    }

    public boolean isWhiteStarting(){
        return selection.isFirstPlayersTurn();
    }

    public void saveBoard(){
        FileSaving fileSaving = new FileSaving();
        if(!fileSaving.isPathNull()){
            fileSaving.saveBoard(
                    sizeOfTheBoard,
                    checkersPositions.getTiles(),
                    selection.isFirstPlayersTurn());
        }
    }

}
