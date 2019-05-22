
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
