package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.Timer;

import boardPackage.Board;
import boardPackage.BoardPanel;
import dataBaseOfPlayers.DataBase;
import dataBaseOfPlayers.Player;

public class GameFrame extends JFrame {
    JButton buttonEnd, buttonResign1, buttonResign2;
    JLabel labelPlayer1, labelPlayer2, labelClock;
    Timer timer;

    public GameFrame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);


        // UnderPanel
        JPanel underPanel =new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(80,80,80);
                Color color2 = new Color(235,176,0);
                GradientPaint gp = new GradientPaint(0, 0, color1, w+200, h+200, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        underPanel.setLayout(new BorderLayout());
        this.add(underPanel, BorderLayout.CENTER);

        // Buttons
        Dimension buttonSize = new Dimension(150,50);
        buttonEnd = setButton(MainMenuFrame.language.getExit(), buttonSize);
        buttonResign1 = setButton(MainMenuFrame.language.getResign(), buttonSize);
        buttonResign2 = setButton(MainMenuFrame.language.getResign(), buttonSize);

        // Labels
        labelPlayer1 = new JLabel(MainMenuFrame.language.getPlayer()+" 1: "+MainMenuFrame.player1name);
        labelPlayer1.setForeground(Color.ORANGE);
        labelPlayer1.setFont(new Font("Arial", Font.BOLD,20));
        labelPlayer2 = new JLabel(MainMenuFrame.language.getPlayer()+" 2: "+MainMenuFrame.player2name);
        labelPlayer2.setForeground(Color.ORANGE);
        labelPlayer2.setFont(new Font("Arial", Font.PLAIN,20));

        labelClock = new JLabel("00:00");
        labelClock.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelClock.setForeground(new Color(200,0,0));
        labelClock.setFont(new Font("Arial", Font.BOLD,40));

        // Board
        BoardPanel boardPanel = new BoardPanel(new Board(8));
        this.setJMenuBar(new MyMenu(boardPanel.getBoard()));
		underPanel.add(boardPanel, BorderLayout.CENTER);

        // Side Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.DARK_GRAY);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(labelClock);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(labelPlayer1);
        labelPlayer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(buttonResign1);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        sidePanel.add(labelPlayer2);
        labelPlayer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(buttonResign2);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        sidePanel.add(buttonEnd);

        // Action Listeners
        buttonEnd.addActionListener(e -> {this.dispose();});

        buttonResign1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame(false);
            }
        });

        buttonResign2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame(true);
            }
        });

        // Timer
        timer = new Timer();
        TimerTask clock = new TimerTask() {
            int min,sec = 0;
            @Override
            public void run() {
                if(sec == 60){
                    min++;
                    sec =0;
                }
                String tmpStr = String.format("%02d:%02d",min,sec);
                labelClock.setText(tmpStr);
                sec++;
            }
        };
        timer.schedule(clock, 0, 1000);

        sidePanel.setPreferredSize(new Dimension(300,0));
        underPanel.add(sidePanel, BorderLayout.EAST);

    }

    public GameFrame(Board board) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);


        // UnderPanel
        JPanel underPanel =new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(80,80,80);
                Color color2 = new Color(235,176,0);
                GradientPaint gp = new GradientPaint(0, 0, color1, w+200, h+200, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        underPanel.setLayout(new BorderLayout());
        this.add(underPanel, BorderLayout.CENTER);

        // Buttons
        Dimension buttonSize = new Dimension(150,50);
        buttonEnd = setButton(MainMenuFrame.language.getExit(), buttonSize);
        buttonResign1 = setButton(MainMenuFrame.language.getResign(), buttonSize);
        buttonResign2 = setButton(MainMenuFrame.language.getResign(), buttonSize);

        // Labels
        labelPlayer1 = new JLabel(MainMenuFrame.language.getPlayer()+" 1");
        labelPlayer1.setForeground(Color.ORANGE);
        labelPlayer1.setFont(new Font("Arial", Font.BOLD,20));
        labelPlayer2 = new JLabel(MainMenuFrame.language.getPlayer()+" 2");
        labelPlayer2.setForeground(Color.ORANGE);
        labelPlayer2.setFont(new Font("Arial", Font.PLAIN,20));

        // Board
        BoardPanel boardPanel = new BoardPanel(board);
        underPanel.add(boardPanel, BorderLayout.CENTER);

        // Side Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.DARK_GRAY);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        sidePanel.add(Box.createRigidArea(new Dimension(0, 60)));
        sidePanel.add(labelPlayer1);
        labelPlayer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(buttonResign1);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        sidePanel.add(labelPlayer2);
        labelPlayer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(buttonResign2);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        sidePanel.add(buttonEnd);

        // Action Listeners
        buttonEnd.addActionListener(e -> {this.dispose();});

        buttonResign1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endLoadedGame(false);
            }
        });

        buttonResign2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endLoadedGame(true);
            }
        });

        sidePanel.setPreferredSize(new Dimension(300,0));
        underPanel.add(sidePanel, BorderLayout.EAST);

    }

    public void changePlayer(Boolean firstPlayerTurn){
        Font fontBold = new Font("Arial", Font.BOLD,20);
        Font font = new Font("Arial", Font.PLAIN,20);
        if(firstPlayerTurn){
            labelPlayer1.setFont(fontBold);
            buttonResign1.setEnabled(true);
            labelPlayer2.setFont(font);
            buttonResign2.setEnabled(false);
        } else {
            labelPlayer2.setFont(fontBold);
            buttonResign2.setEnabled(true);
            labelPlayer1.setFont(font);
            buttonResign1.setEnabled(false);
        }
    }

    public void endGame(Boolean firstPlayerWon){
        timer.cancel();
        MainMenuFrame.soundEffects.playEnd();
        DataBase dataBase;
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Player player1;
        if(dataBase.isPartOfDataBase(MainMenuFrame.player1name)){
            player1 = dataBase.getPlayer(MainMenuFrame.player1name);
        } else {
            player1 = new Player(MainMenuFrame.player1name);
        }

        Player player2;
        if(dataBase.isPartOfDataBase(MainMenuFrame.player2name)){
            player2 = dataBase.getPlayer(MainMenuFrame.player2name);
        } else {
            player2 = new Player(MainMenuFrame.player2name);
        }

        if(firstPlayerWon){
            player1.playerWon();
            new EndFrame(MainMenuFrame.player1name).setVisible(true);
            player2.playerLost();
        } else {
            player2.playerWon();
            new EndFrame(MainMenuFrame.player2name).setVisible(true);
            player1.playerLost();
        }

        dataBase.updatePlayer(player1);
        dataBase.updatePlayer(player2);
    }

    public void endLoadedGame(Boolean firstPlayerWon){
        if(firstPlayerWon){
            new EndFrame("Player 1",true).setVisible(true);
        } else {
            new EndFrame("Player 2",true).setVisible(true);
        }
        MainMenuFrame.soundEffects.playEnd();
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
