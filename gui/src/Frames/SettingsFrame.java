package Frames;

import languages.English;
import languages.French;
import languages.Polish;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsFrame extends JFrame {
    JButton bReturn, bEng, bFr, bPol;

    public SettingsFrame() {
        // Loading and Basic settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);

        Dimension buttonSize = new Dimension(150,50);
        bReturn = setButton(MainMenuFrame.language.getExit(), buttonSize);
        BufferedImage img;
        try {
            img = ImageIO.read(new File("images/ENGLISH_Flag.png"));
            bEng = flagButton(img,buttonSize);
            img = ImageIO.read(new File("images/POLISH_Flag.png"));
            bPol = flagButton(img,buttonSize);
            img = ImageIO.read(new File("images/FRENCH_Flag.png"));
            bFr = flagButton(img,buttonSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        underPanel.setLayout(new BorderLayout());//new BoxLayout(underPanel, BoxLayout.Y_AXIS));


        JPanel fakePanel = new JPanel();
        fakePanel.setBackground(new Color(200,0,0,0));
        fakePanel.setPreferredSize(new Dimension(0,50));
        underPanel.add(fakePanel, BorderLayout.NORTH);

        // Menu Panel
        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(200,0,0,0));

        Dimension gapBetweenButtons = new Dimension(0,20);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bEng);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bPol);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bFr);
        menuPanel.add(Box.createRigidArea(gapBetweenButtons));
        menuPanel.add(bReturn);

        underPanel.add(menuPanel,BorderLayout.CENTER);

        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bEng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame.language = new English();
                updateLanguage();
                System.out.println("Eng");
            }
        });

        bPol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame.language = new Polish();
                updateLanguage();
                System.out.println("Pol");
            }
        });

        bFr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame.language = new French();
                updateLanguage();
                System.out.println("Fr");
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

    public JButton flagButton(BufferedImage img, Dimension buttonSize){
        JButton button = new CustomButton("",img);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setPreferredSize(buttonSize);
        //button.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        return button;
    }

    private void updateLanguage(){
        bReturn.setText(MainMenuFrame.language.getExit());

        try {
            MainMenuFrame.img = ImageIO.read(new File("images/"+MainMenuFrame.language.getLanguageChosen()+"_Title.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainMenuFrame.graphicPanel.repaint();

        MainMenuFrame.bExit.setText(MainMenuFrame.language.getExit());
        MainMenuFrame.bSettings.setText(MainMenuFrame.language.getSettings());
        MainMenuFrame.bLoad.setText(MainMenuFrame.language.getLoad());
        MainMenuFrame.bPlay.setText(MainMenuFrame.language.getPlay());
        MainMenuFrame.bRanking.setText(MainMenuFrame.language.getRanking());

    }
}