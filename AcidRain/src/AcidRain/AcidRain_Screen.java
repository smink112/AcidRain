package AcidRain;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class AcidRain_Screen extends JFrame {

    private String[] type = {"한글", "ENGLISH"};      //한,영 타입 선택
    private String[] level = {"Level 1", "Level 2", "Level 3", "Level 4",
            "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10"};     //난이도 선택
    private JLabel game_type, game_LV;
    private JButton start_btn, end_btn;
    private JComboBox<String> box_level, box_type;
    private int selected_level, selected_type;


    public AcidRain_Screen(){
        setTitle("Acid Rain Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);
        GridLayout grid = new GridLayout(3,2);
        grid.setVgap(15);
        contentPane.setLayout(grid);


        game_type = new JLabel("언  어");  //언어 선택
        game_type.setHorizontalAlignment(SwingConstants.CENTER);
        game_type.setFont(new Font("굴림",Font.BOLD,15));
        getContentPane().add(game_type);
        box_type = new JComboBox<String>(type); //언어선택박스
        getContentPane().add(box_type);
        box_type.addActionListener(new ActionListener() {   //선택한 언어 반환
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                selected_type = cb.getSelectedIndex();

            }
        });



        game_LV = new JLabel("난이도");      //난이도 선택
        game_LV.setHorizontalAlignment(SwingConstants.CENTER);
        game_LV.setFont(new Font("굴림",Font.BOLD,15));
        getContentPane().add(game_LV);
        box_level = new JComboBox<String>(level); //난이도선택박스
        getContentPane().add(box_level);
        box_level.addActionListener(new ActionListener() {  //선택한 난이도 반환
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                selected_level = cb.getSelectedIndex();

            }
        });



        start_btn = new JButton("START");   //start 버튼
        start_btn.setFont(new Font("굴림",Font.BOLD,20));
        contentPane.add(start_btn,CENTER_ALIGNMENT);
        start_btn.addActionListener(new ActionListener() {//start 버튼 클릭시 게임창 열기
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AcidRain_GameScreen(selected_level, selected_type);  //선택한 난이도, 언어 바탕으로 게임창 열기
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                setVisible(false); // 창 안보이게 하기
            }
        });



        end_btn = new JButton("게임종료");   //종료 버튼
        end_btn.setFont(new Font("굴림",Font.BOLD, 17));
        contentPane.add(end_btn,CENTER_ALIGNMENT);
        end_btn.addActionListener(new ActionListener() {    //버튼 클릭시 창닫기(종료)
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        setSize(300,200);
        setVisible(true);


    }

}
