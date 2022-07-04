package AcidRain;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AcidRain_EndScreen extends JFrame{

    private Container endScreen;
    private JLabel gameOver, win, prt_score;
    private JButton mainPage;

    public AcidRain_EndScreen(int score, int life){
        if(life==0){
            setTitle("Game Over");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endScreen = getContentPane();
            endScreen.setBackground(Color.LIGHT_GRAY);
            GridLayout grid = new GridLayout(3,1);
            grid.setVgap(20);
            endScreen.setLayout(grid);


            gameOver = new JLabel("GAME OVER");
            gameOver.setHorizontalAlignment(SwingConstants.CENTER);
            gameOver.setFont(new Font("굴림", Font.BOLD, 20));
            endScreen.add(gameOver);

            prt_score = new JLabel("game over..."+ score + "점");
            prt_score.setHorizontalAlignment(SwingConstants.CENTER);
            prt_score.setFont(new Font("굴림", Font.BOLD, 15));
            prt_score.setForeground(Color.ORANGE);
            endScreen.add(prt_score);

            mainPage = new JButton("초기화면");
            mainPage.setFont(new Font("굴림",Font.BOLD,20));
            endScreen.add(mainPage,CENTER_ALIGNMENT);
            mainPage.addActionListener(new ActionListener() {//버튼 클릭시 초기화면 열기
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AcidRain_Screen();
                    setVisible(false);
                }
            });
        }
    else {
            setTitle("W I N");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endScreen = getContentPane();
            endScreen.setBackground(Color.LIGHT_GRAY);
            GridLayout grid = new GridLayout(3,1);
            grid.setVgap(20);
            endScreen.setLayout(grid);


            win = new JLabel("W I N");
            win.setHorizontalAlignment(SwingConstants.CENTER);
            win.setFont(new Font("굴림", Font.BOLD, 20));
            endScreen.add(win);

            prt_score = new JLabel(score + " 점");
            prt_score.setHorizontalAlignment(SwingConstants.CENTER);
            prt_score.setFont(new Font("굴림", Font.BOLD, 20));
            prt_score.setForeground(Color.ORANGE);
            endScreen.add(prt_score);

            mainPage = new JButton("초기화면");
            mainPage.setFont(new Font("굴림",Font.BOLD,20));
            endScreen.add(mainPage,CENTER_ALIGNMENT);
            mainPage.addActionListener(new ActionListener() {//버튼 클릭시 초기화면
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AcidRain_Screen();
                    setVisible(false); // 창 안보이게 하기
                }
            });

        }

        setSize(300,200);
        setVisible(true);
    }
}

