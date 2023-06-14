package Frames;

import boardPackage.Board;
import fileManagement.FileLoading;
import languages.English;
import languages.Language;
import soundEffects.SoundEffects;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainMenuFrame extends JFrame implements ChangeListener {
    public static JButton bPlay, bExit, bRanking, bSettings, bLoad;
    Color themeColor = Color.GRAY;
    public static JPanel graphicPanel;
    public static BufferedImage img;
    public static Language language = new English();
    public static SoundEffects soundEffects;
    public static GameFrame gameFrame = null;
    public static String player1name,player2name;


    public MainMenuFrame() {
        // Loading and Basic settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);

        try {
            soundEffects = new SoundEffects();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        Dimension buttonSize = new Dimension(150,50);
        bPlay = setButton(language.getPlay(), buttonSize);
        bLoad = setButton(language.getLoad(), buttonSize);
        bRanking = setButton(language.getRanking(), buttonSize);
        bSettings = setButton(language.getSettings(), buttonSize);
        bExit = setButton(language.getExit(), buttonSize);

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
        underPanel.setBackground(themeColor);
        underPanel.setLayout(new BorderLayout());//new BoxLayout(underPanel, BoxLayout.Y_AXIS));


        // Menu Panel
        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(200,0,0,0));

        Dimension gapBetweenButtons = new Dimension(0,20);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bPlay);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bLoad);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bSettings);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bRanking);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bExit);

        underPanel.add(menuPanel,BorderLayout.CENTER);

        try {
            img = ImageIO.read(new File("images/"+language.getLanguageChosen()+"_Title.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graphicPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int x = (this.getWidth() - img.getWidth(null)) / 2;
                int y = (this.getHeight() - img.getHeight(null)) / 2;
                g2d.drawImage(img, x, y, null);
            }
        };
        graphicPanel.setBackground(new Color(10,100,10,0));
        graphicPanel.setPreferredSize(new Dimension(0,150));
        underPanel.add(graphicPanel, BorderLayout.NORTH);


        // Button's ActionListeners
        bPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameFrame!=null){
                    gameFrame.dispose();
                }
                player1name = JOptionPane.showInputDialog(language.getPlayer()+" 1:",language.getEnterName());
                if(player1name!=null){
                    player2name = JOptionPane.showInputDialog(language.getPlayer()+" 2:",language.getEnterName());
                    if(player2name!=null) {
                        if(!player2name.equals(player1name)){
                            gameFrame = new GameFrame();
                            gameFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null,language.getWrongName(),"Wrong name",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileLoading fileLoading = new FileLoading();
                if(fileLoading.isFileLoaded()){
                    //gameFrame = fileLoading.loadCurrentGame();
                    //gameFrame.setVisible(true);

                    Board board = fileLoading.loadBoard();
                    if(fileLoading.isFileLoaded()){
                        gameFrame = new GameFrame(board);
                        gameFrame.setVisible(true);
                        if(!board.isWhiteStarting()){
                            gameFrame.changePlayer(false);
                        }
                    }
                }
            }
        });

        bRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankingFrame().setVisible(true);
            }
        });

        bSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsFrame().setVisible(true);
            }
        });

        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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

    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            img = ImageIO.read(new File("images/"+language.getLanguageChosen()+"_Title.png"));
        } catch (IOException e11) {
            throw new RuntimeException(e11);
        }
        this.repaint();

        bExit.setText(language.getExit());
        bSettings.setText(language.getSettings());
        bLoad.setText(language.getLoad());
        bPlay.setText(language.getPlay());
        bRanking.setText(language.getRanking());

    }
}
