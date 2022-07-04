package AcidRain;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Random;


public class AcidRain_GameScreen extends JFrame {

    private JTextField textField;
    private Container contentPane;
    WordData word_create = new WordData();  //단어생성
    private JPanel life1, life2, life3;
    private JPanel end_line;
    private JButton quit_btn, end_btn;
    private JLabel selected_LV, my_score, rmCount;
    private JLabel label[] = new JLabel[10000];
    private JTextField t;
    private int level, type, i, spd=1000;
    private int score = 0, life = 3;
    private int removeCount = 0;            //제거시도횟수


    public AcidRain_GameScreen(int selected_level, int selected_type) throws FileNotFoundException {

        level = selected_level;
        type = selected_type;

        textField = new JTextField(20);
        textField.setBounds(450, 700, 200, 40);
        add(textField);
        textField.setVisible(true);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t = (JTextField) e.getSource();
                removeAnswer();
                t.setText("");
            }
        });

        setTitle("Acid Rain Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        my_score = new JLabel(score + "점");
        my_score.setBounds(500, 30, 200, 30);
        my_score.setFont(new Font("굴림", Font.BOLD, 20));
        my_score.setForeground(Color.YELLOW);
        contentPane.add(my_score);

        rmCount = new JLabel("시도 횟수 : " + removeCount + "번");
        rmCount.setBounds(1000, 70, 200, 30);
        rmCount.setFont(new Font("굴림", Font.BOLD, 12));
        rmCount.setForeground(Color.YELLOW);
        contentPane.add(rmCount);


        life1 = new JPanel();
        life1.setBackground(Color.RED);
        life1.setBounds(1000, 30, 30, 30);
        contentPane.add(life1);

        life2 = new JPanel();
        life2.setBackground(Color.RED);
        life2.setBounds(1040, 30, 30, 30);
        contentPane.add(life2);

        life3 = new JPanel();
        life3.setBackground(Color.RED);
        life3.setBounds(1080, 30, 30, 30);
        contentPane.add(life3);


        quit_btn = new JButton("초기화면");   //초기화면 버튼
        quit_btn.setFont(new Font("굴림", Font.BOLD, 20));
        quit_btn.setLocation(950, 730);
        quit_btn.setSize(180, 40);
        contentPane.add(quit_btn);

        end_btn = new JButton("게임종료");   //결과화면 버튼
        end_btn.setFont(new Font("굴림", Font.BOLD, 20));
        end_btn.setLocation(950, 670);
        end_btn.setSize(180, 40);
        contentPane.add(end_btn);


        if (type == 0) {     //한글 설정시 x 단계
            selected_LV = new JLabel((selected_level + 1) + " 단계");
        } else {            //영어 설정시 Level x
            selected_LV = new JLabel("Level " + (selected_level + 1));
        }
        selected_LV.setFont(new Font("굴림", Font.BOLD, 20));
        selected_LV.setBounds(30, 30, 100, 30);
        selected_LV.setForeground(Color.YELLOW);
        contentPane.add(selected_LV);

        end_line = new JPanel(); //산성비 바닥라인 설정
        end_line.setBackground(Color.DARK_GRAY);
        end_line.setBounds(0, 600, 1000, 10);
        contentPane.add(end_line);


        quit_btn.addActionListener(new ActionListener() {    //버튼 클릭시 초기화면
            @Override
            public void actionPerformed(ActionEvent e) {
                new AcidRain_Screen();
                setVisible(false); // 창 안보이게 하기
            }
        });

        end_btn.addActionListener(new ActionListener() {    //버튼 클릭시 결과화면
            @Override
            public void actionPerformed(ActionEvent e) {
                new AcidRain_EndScreen(score, life);
                setVisible(false); // 창 안보이게 하기
            }
        });

        setSize(1200, 900);
        setLayout(null);
        setVisible(true);

        word_create.create(selected_type);
        word_create.shuffle();

        new AcidRain_Thread().start();


    }

    public void keyPressed(KeyEvent e) { // 키이벤트 정의
        // TODO Auto-generated method stub
        if (e.getKeyCode() == 10) { // 엔터가 눌리면
            removeAnswer(); // 정답처리 메소드 실행
        }
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }


    class AcidRain_Thread extends Thread {

        @Override
        public void run() {

            spd = 4000-(level*400);     //각 level 에 맞게 속도 설정

            for (i = 0; i <= 10000; i++) {

                try {
                    Random random = new Random();
                    for (int i = 0; i < label.length; i++) {
                        label[i] = new JLabel(word_create.arr.get(i));
                        label[i].setBounds(0, 0, 150, 30);
                        label[i].setFont(new Font("굴림", Font.BOLD, 15));
                        label[i].setForeground(Color.WHITE);
                        label[i].setLocation(random.nextInt(1000), 60);
                        //Label 의 위치지정, x축 1000이상 못넘게 설정, y축 60부터 내려오게 설정
                        add(label[i]); //Label 을 패널에 추가한다
                        new AcidRain_Move().start();
                        sleep(spd);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class AcidRain_Move extends Thread{

        @Override
        public void run(){
            for (int i = 0; i < 10000; i++) {
                try {
                    for (int j = 0; j< label.length; j++) {
                        int x = label[j].getX(); //Label 의 x 좌표값을 x 변수에 저장한다
                        int y = label[j].getY();//Label 의 y 좌표값을 y 변수에 저장한다
                        y += 20; // 좌표를 20씩 증가시킨다
                        label[j].setLocation(x, y); //Label 의 위치를 다시 지정한다
                        if (label[j].isVisible() && label[j].getY() > 590) {    //label 이 바닥에 닿으면 사라지고 life 감소
                            label[j].setVisible(false); // 만약 label 의 Y 좌표가 590이상이 되면(바닥에 진입하면)
                            life -= 1; // 라이프를 1감소시킨다
                            switch (life) {
                                case 0: //남은 life 가 없다면
                                    life1.setBackground(Color.GRAY);
                                    life2.setBackground(Color.GRAY);
                                    life3.setBackground(Color.GRAY);
                                    setVisible(false);                      //현재화면 가리기
                                    new AcidRain_EndScreen(score, life);    //결과화면 호출
                                    break;

                                case 1:
                                    life1.setBackground(Color.GRAY);
                                    life2.setBackground(Color.GRAY);
                                    life3.setBackground(Color.RED);
                                    break;

                                case 2:
                                    life1.setBackground(Color.GRAY);
                                    life2.setBackground(Color.RED);
                                    life3.setBackground(Color.RED);
                                    break;
                                case 3:
                                    life1.setBackground(Color.RED);
                                    life2.setBackground(Color.RED);
                                    life3.setBackground(Color.RED);
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                    sleep(spd); //spd 변수만큼 떨어지는 속도를 지정한다
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    private void removeAnswer() { // 정답처리
        for (int i = 0; i < 800; i++) {
            if (t.getText().equals(word_create.arr.get(i))) { // 입력된 값이 리스트 값과 일치하면
                label[i].setVisible(false); //레이블을 안보이게한다.
                score += 5;                 //점수를 5점 증가시킨다.
                my_score.setText(score + "점");  //현재점수
            }
        }
        removeCount++;
        rmCount.setText("시도 횟수 : " + removeCount + "번"); //제거시도횟수
        if(removeCount == 100){                     //제거시도를 100번하면
            setVisible(false);                      //현재화면 가리기
            new AcidRain_EndScreen(score, life);    //결과화면 호출
        }
    }
}