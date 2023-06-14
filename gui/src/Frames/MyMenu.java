package Frames;

import boardPackage.Board;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenu extends JMenuBar {
    JMenu menu, submenu;
    JMenuItem i1, i2, i3;
    MyMenu(Board board){
        JMenuBar mb=new JMenuBar();
        menu = new JMenu("Menu");

        i1=new JMenuItem(MainMenuFrame.language.getSave());
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.saveBoard();
            }
        });
        submenu = new JMenu("Author");
        menu.add(i1); menu.add(submenu);

        i2=new JMenuItem("Tymon");
        i3=new JMenuItem("Orłowski");
        submenu.add(i2); submenu.add(i3);
        menu.add(submenu);

        this.add(menu);
    }

}
