package model;
import java.util.HashMap;
import java.util.Map;

public class BlockText {
    String text;
    static char[][] repetitivenessTab;
    Map<Character, Integer> count = new HashMap<>();

    public BlockText(String text) {
        this.text = text;
        countRepetitiveness(text, count);
    }

    public static Map<Character, Integer> countRepetitiveness(String text, Map<Character, Integer> count) {
        char[] inputTab = text.toCharArray();

        for (char znak : inputTab) {
            if (count.containsKey(znak)) {
                count.put(znak, count.get(znak) + 1);
            } else {
                count.put(znak, 1);
            }
        }
        System.out.println(count);
        return count;
    }

    public Map<Character, Integer> getCount() {
        return count;
    }

    /*
    public static char[][] getRepetitivenessTab() {
        return repetitivenessTab;
    }
    public static char[][] test(Map<Character, Integer> map) {

        char[][] tablica = new char[2][map.size()];
        repetitivenessTab = tablica;

        int index = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            tablica[0][index] = entry.getKey(); // Znak
            tablica[1][index] = Character.forDigit(entry.getValue(), 10); // Liczba wystąpień jako znak
            index++;
        }

        // Wyświetlenie zawartości tablicy dwuwymiarowej
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < map.size(); j++) {
                System.out.print(tablica[i][j] + " ");
            }
            System.out.println(); // Nowa linia po każdym wierszu
        }
        return tablica;
    }

 */

}
