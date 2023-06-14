package fileManagement;

import Frames.GameFrame;
import Frames.MainMenuFrame;
import boardPackage.Board;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileLoading {
    private Path path = null;
    private byte[] bytes = null;
    private List<String> allLines = null;
    private int lineInfile = 0;

    public FileLoading(){
        String savedDir = System.getProperty("user.dir")+"\\savedFiles";
        JFileChooser j = new JFileChooser(savedDir);

        int r = j.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            path = Paths.get(j.getSelectedFile().getAbsolutePath());
        }
    }

    public Board loadBoard(){
        int sizeOfBoard = 0;
        try {
            allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            sizeOfBoard =  Integer.parseInt(allLines.get(0));
            lineInfile = 1;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Wrog file","Wrong name",JOptionPane.ERROR_MESSAGE);
            path = null;
        }

        int piecePlacement[][] = new int[sizeOfBoard][sizeOfBoard];

        String[] tmp = null;
        for(int y=lineInfile; y<sizeOfBoard+1; y++) {
            try {
                tmp = allLines.get(y).split("\t",sizeOfBoard);
            } catch (Exception e) {
                if(path!=null){
                    JOptionPane.showMessageDialog(null,"Wrog file","Wrong name",JOptionPane.ERROR_MESSAGE);
                    path = null;
                }
            }
            for(int x=0; x<sizeOfBoard; x++) {
                try {
                    piecePlacement[x][y-1] = Integer.parseInt(tmp[x]);
                } catch (NumberFormatException e) {
                    if(path!=null) {
                        JOptionPane.showMessageDialog(null, "Wrog file", "Wrong name", JOptionPane.ERROR_MESSAGE);
                        path = null;
                    }
                }
            }
        }
        lineInfile=+sizeOfBoard+1;
        boolean isFirstPlayersTurn = false;
        try {
            if( Integer.parseInt(allLines.get(lineInfile))==1){
                isFirstPlayersTurn = true;
            }
        } catch (NumberFormatException e) {
            if(path!=null) {
                JOptionPane.showMessageDialog(null, "Wrog file", "Wrong name", JOptionPane.ERROR_MESSAGE);
                path = null;
            }
        } catch (IndexOutOfBoundsException e1){
            if(path!=null) {
                JOptionPane.showMessageDialog(null, "Wrog file", "Wrong name", JOptionPane.ERROR_MESSAGE);
                path = null;
            }
        }

        if(isFileLoaded()){
            return new Board(sizeOfBoard,piecePlacement,isFirstPlayersTurn );
        }
        return null;
    }

    public boolean isFileLoaded(){
        boolean is = true;
        if(path == null){
            is = false;
        }
        return is;
    }

}

