package Frames;

import dataBaseOfPlayers.DataBase;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RankingFrame extends JFrame {

    JButton bReturn;

    public RankingFrame() {
        // Loading and Basic settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);

        Dimension buttonSize = new Dimension(150,50);
        bReturn = setButton(MainMenuFrame.language.getExit(), buttonSize);

        // UnderPanel
        JPanel underPanel =new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(100,100,100);
                Color color2 = new Color(205,126,0);
                GradientPaint gp = new GradientPaint(0, 0, color1, w+200, h+200, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        underPanel.setLayout(new BorderLayout());

        // Text Field
        JPanel txtField = new JPanel();
        DataBase dataBase;
        try {
             dataBase = new DataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JTable table = dataBase.getData();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(36, 37, 407, 79);
        txtField.setBackground(new Color(0,0,0,0));
        txtField.add(scroll);
        underPanel.add(txtField, BorderLayout.NORTH);

        // Menu Panel
        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(200,0,0,0));

        Dimension gapBetweenButtons = new Dimension(0,20);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bReturn);

        underPanel.add(menuPanel,BorderLayout.CENTER);

        JPanel graphicPanel = new JPanel();
        graphicPanel.setBackground(new Color(10,100,10,0));
        graphicPanel.setPreferredSize(new Dimension(0,150));
        JLabel grafika = new JLabel("Grafika");
        grafika.setAlignmentX(Component.CENTER_ALIGNMENT);
        grafika.setAlignmentY(Component.CENTER_ALIGNMENT);
        graphicPanel.add(grafika);
        //underPanel.add(graphicPanel, BorderLayout.NORTH);


        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(underPanel);
    }

    public JButton setButton(String string, Dimension buttonSize){
        JButton button = new CustomButton(string);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        return button;
    }

}
