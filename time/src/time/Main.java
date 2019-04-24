package time;

import java.util.Scanner;

/**
 * Main class used in speed comparison.
 * 
 * @author Antoni Żewierżejew
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Treap root = null;
        while (true) {
            int command = input.nextInt();

            if (command == 0) {
                break;
            } else {
                int value = input.nextInt();
                if (command == 1) {
                    root = Treap.add(root, value);
                } else if (command == 2) {
                    root = Treap.remove(root, value);
                } else if (command == 3) {
                    Treap.setSeed(value);
                } else if (command == 4) {
                    System.out.println(Treap.isMember(root, value) ? "1" : "0");
                }
            }
        }
        input.close();
    }
}
