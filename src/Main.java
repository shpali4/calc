import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = new Scanner(System.in).nextLine();
        System.out.println(calc(input));
    }

    public static String calc (String input) throws Exception {
        String str = input.replaceAll("\\s", "");
        String operator = str.replaceAll("\\w", "");
        String[] strings = str.split("[-+/*]");
        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romanDozen = new String[]{"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] arab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int k = 0;
        int[] calc = new int[2];
        for (String string : strings) {
            for (int i = 0; i < roman.length; i++) {
                if (string.equals(roman[i]) || string.equals(arab[i])) {
                    try {
                        calc[k] = i + 1;
                        k++;
                    } catch (Exception e) {
                        throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }
                }
            }
        }
        int res = 0;
        if ((calc[0] > 0 && calc[0] < 11) && (calc[1] > 0 && calc[1] < 11)) {
                switch (operator) {
                    case "+" -> res = calc[0] + calc[1];
                    case "-" -> res = calc[0] - calc[1];
                    case "*" -> res = calc[0] * calc[1];
                    case "/" -> res = calc[0] / calc[1];
                }
        } else throw new Exception("т.к. строка не является допустимой математической операцией: два операнда, каждый от 1 до 10 включительно, один оператор (+, -, /, *)");

        String output;
        try {
            if (strings[0].equals(arab[calc[0]-1]) && strings[1].equals(arab[calc[1]-1])) {
                output = String.valueOf((res));
                return output;
            }
        } catch (Exception e) {
            throw new Exception("т.к. строка не является математической операцией");
            }
        if (strings[0].equals(roman[calc[0] - 1]) && strings[1].equals(roman[calc[1] - 1])) {
            if (res % 10 == 0)
                try {
                    output = romanDozen[res / 10 - 1];
                } catch (Exception e) {
                    throw new Exception("т.к. в римской системе нет нуля");
                }
            else if (res / 10 == 0)
                try {
                    output = roman[res % 10 - 1];
                } catch (Exception e) {
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
            else
                output = romanDozen[res / 10 - 1] + roman[res % 10 - 1];
        } else if (strings[0].equals(""))
            throw new Exception("т.к. строка не является допустимой математической операцией: два операнда, каждый от 1 до 10 включительно, один оператор (+, -, /, *)");
        else
            throw new Exception("т.к. используются одновременно разные системы счисления");
        return output;
    }
}