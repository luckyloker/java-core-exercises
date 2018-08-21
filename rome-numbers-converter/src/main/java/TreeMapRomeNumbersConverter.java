import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapRomeNumbersConverter {

    private static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    private static String convertToRome(int arabic) {
        int key = map.floorKey(arabic);
        if (arabic == 1) {
            return map.get(arabic);
        } else {
            return map.get(key) + convertToRome(arabic - key);
        }
    }

    private static int getNumber() {
        System.out.println("please type arabic number, \n press enter");
        int arabic;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            arabic = scanner.nextInt();
        } else {
            throw new IllegalArgumentException("type not valid");
        }
        return arabic;
    }

    public static void main(String[] args) {
        int arabic = getNumber();
        System.out.println("your number in rome style is: \n" + convertToRome(arabic));
    }
}
