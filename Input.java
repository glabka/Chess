
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class Input {

//    private static int readInt() {
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            if (in.hasNextInt()) {
//                int a = in.nextInt();
//                in.nextLine();
//                return a;
//            } else {
//                System.out.println("There's been problem with entered number, please enter correct number.");
//                in.nextLine();
//            }
//        }
//    }
    public static char readChar() {
        Scanner in = new Scanner(System.in);
        char ch;
        while (true) {
            if (in.hasNext()) {
                ch = in.next().charAt(0);
                in.nextLine();
                return ch;
            } else {
                System.out.println("There's been problem with input, please enter correct character.");
                in.nextLine();
            }
        }
    }

    public static void readCoordinates(int[] output) {
        if (output == null || output.length != 4) {
            throw new IllegalArgumentException("output can't be null and have to refer to array of length 4.");
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            String input;
            if (in.hasNext() && (input = in.next()).length() == 4) {
                for (int i = 0; i < 4; i++) {
                    char ch = input.charAt(i);
                    if ((i + 1) % 2 == 0) {
                        // chars
                        output[i] = Board.positionCharToIndex(ch);
                    } else {
                        // ints
                        output[i] = Board.positionNumToIndex(Integer.valueOf(String.valueOf(ch)));
                    }
                    if (i == 3) {
                        in.nextLine();
                        return;
                    }
                }
            } else {
                System.out.println("There's been problem with entered input, please enter coordinates for example 1b3a.");
                in.nextLine();
            }
        }
    }

    public static void read4Int(int[] output) {
        if (output == null || output.length != 4) {
            throw new IllegalArgumentException("output can't be null and have to refer to array of length 4.");
        }
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while (true) {
            if (in.hasNextInt()) {
                output[counter] = in.nextInt();
                counter++;
                if (counter == 4) {
                    in.nextLine();
                    return;
                }
            } else {
                System.out.println("There's been problem with entered number, please enter correct 4 numbers.");
                in.nextLine();
            }
        }
    }
}
