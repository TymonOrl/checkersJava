package fileManagement;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaving {
    private Path path = null;

    public FileSaving(){
        String savedDir = System.getProperty("user.dir")+"\\savedFiles";
        JFileChooser j = new JFileChooser(savedDir);

        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            path = Paths.get(j.getSelectedFile().getAbsolutePath());
        }
    };

    public void saveBoard(int sizeOfTheBoard, int[][] checkersPosition,boolean isFirstPlayersTurn){
        FileWriter myWriter = null;
        String checkersString ="";

        for (int y=0; y<sizeOfTheBoard; y++){
            for (int x=0; x<sizeOfTheBoard; x++){
                checkersString += checkersPosition[x][y];
                if(x!=sizeOfTheBoard-1){
                    checkersString += "\t";
                }
            }
            checkersString += "\n";
        }
        String isFirstPlayersTurnStr = isFirstPlayersTurn ? "1" : "0";
        try {
            myWriter = new FileWriter(String.valueOf(path));
            myWriter.write( Integer.toString(sizeOfTheBoard) );
            myWriter.write("\n");
            myWriter.write(checkersString);
            myWriter.write(isFirstPlayersTurnStr);
            myWriter.close();
            JOptionPane.showMessageDialog(null,"Poprawnie zapisano");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isPathNull() {
        boolean is = (path == null);
        return is;
    }
}
