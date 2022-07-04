package AcidRain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract public class Word {
    public ArrayList<String> arr = new ArrayList<>();

    abstract void create(int selected_type);

    abstract void shuffle();

}

class WordData extends Word {

    @Override
    public void create(int selected_type) {
        if(selected_type == 0) {
            try {
                Scanner inputStream = new Scanner(new File("/Users/user/IntelliJ/AcidRain/Kor.txt"));
                while (inputStream.hasNextLine()) // Kor.txt에 더이상 단어가 없을 때 까지 읽음
                    this.arr.add(inputStream.nextLine());
            } catch (FileNotFoundException e) { // 파일을 찾을 수 없을 때
                System.out.println("File Not Found");
            }
        }
        else{
            try {
                Scanner inputStream = new Scanner(new File("/Users/user/IntelliJ/AcidRain/Eng.txt"));
                while (inputStream.hasNextLine()) // Eng.txt에 더이상 단어가 없을 때 까지 읽음
                    this.arr.add(inputStream.nextLine());
            } catch (FileNotFoundException e) { // 파일을 찾을 수 없을 때
                System.out.println("File Not Found");
            }
        }

    }

    @Override
    void shuffle() {
        Collections.shuffle(this.arr);
         //arr의 값들을 무작위로 섞는다(출제 문제를 매번 다르게 하기위함)

    }
}