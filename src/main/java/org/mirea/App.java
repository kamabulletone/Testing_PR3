package org.mirea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;


public class App {

    public static ArrayList<String> initMask(int wordLength) {
        ArrayList<String> mask = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            mask.add("*");
        }
        return mask;
    }

    public static List<Object> chooseWordChar(String word, ArrayList<String> mask) {
        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println();
        do {
            System.out.println("Выберите букву");
            answer = scanner.nextLine();

        } while (answer.length() != 1);

        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (answer.toLowerCase().charAt(0) == word.charAt(i)) {
                mask.set(i, answer);
                found = true;
            }
        }
        return Arrays.asList(found, mask);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean nextGame = true;
        String inp;
        do {

            System.out.println("1. Начать игру.\n" + "2. Завершить");
            inp = scanner.next();
            if (!inp.equals("1")) {
                break;
            }

            Document doc = Jsoup.connect("https://calculator888.ru/random-generator/sluchaynoye-slovo").get();
            String word = doc.getElementById("bov").text().toLowerCase(Locale.ROOT);
            ArrayList<String> mask = initMask(word.length());

            do {
                System.out.println(word);
                for (int i = 0; i < word.length(); i++) {

                    System.out.print(mask.get(i));
                }

                List<Object> res = chooseWordChar(word, mask);
                boolean found = (Boolean) res.get(0);
                mask = (ArrayList<String>) res.get(1);


                if (!found) {
                    System.out.println("Неверный ответ");
                }
                if (!mask.contains("*")) {

                    System.out.println("Победа");
                    System.out.println("Закончить игру? Y/N");
                    Thread.sleep(2000);
                    inp = scanner.next();

                    if (inp.equals("Y")) {

                        return;
                    }
                    break;

                }

            } while (true);


        } while (true);


    }
}
