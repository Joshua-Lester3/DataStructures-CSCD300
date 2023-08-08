/** Author: Joshua Lester
 * CSCD300
 * Programming Assignment 4
 */

package joshua_lester_00932346_cscd300_prog4;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class PostFix {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No arguments given");
        } else {
            File file = new File(args[0]);
            SLinkedList list = new SLinkedList();
            Scanner scanner;
            try {
                scanner = new Scanner(file);
                System.out.println(runPostFix(list, scanner));
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /** Reads in the input, pushes, pops, and delegates operations to operate() method
     *
     * @param list
     * @param scanner
     * @return
     */
    public static String runPostFix(SLinkedList list, Scanner scanner) {
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                String input = scanner.next();
                list.push(input);
            } else {
                if (list.getSize() < 2) {
                    throw new IllegalArgumentException("Less than two operands before an operator");
                }
                String input = scanner.next().trim();
                double pop1 = Double.parseDouble(list.pop());
                double pop2 = Double.parseDouble(list.pop());
                String result = operate(input, pop1, pop2);
                list.push(result);
            }
        }
        if (list.getSize() == 1) {
            return list.getTop();
        } else if (list.getSize() == 0) {
            return "0";
        } else {
            throw new IllegalArgumentException("More than one operand left, cannot return");
        }
    }

    /** Does arithmetic on pop1 and pop2 using the operator
     *
     * @param operator
     * @param pop1
     * @param pop2
     * @return
     */
    public static String operate(String operator, double pop1, double pop2) {
        String result;
        switch (operator) {
            case "+":
                result = "" + (pop1 + pop2);
                break;
            case "-":
                result = "" + (pop2 - pop1);
                break;
            case "*":
                result = "" + (pop1 * pop2);
                break;
            case "/":
                result = "" + (pop2 / pop1);
                break;
            default:
                throw new IllegalArgumentException("Scanner has input other than number, +, -, *, or /");
        }
        return result;
    }
}
