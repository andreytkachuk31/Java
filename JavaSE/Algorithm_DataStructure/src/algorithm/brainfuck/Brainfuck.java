package algorithm.brainfuck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Brainfuck {

    private char[] memoryBuffer = new char[30_000];
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Brainfuck brainfuck = new Brainfuck();
        brainfuck.interpreter(readSourceCode("brain_fuck_program.bf"));
    }

    public void interpreter(final String sourceCode) {
        Stack<Integer> stack = new Stack<Integer>();
        char[] commands = sourceCode.toCharArray();
        int positionCursor = 0;
        int positionCommand = 0;
        while (positionCommand < commands.length) {
            char command = commands[positionCommand];
            switch (command) {
                case '+':
                    memoryBuffer[positionCursor]++;
                    break;
                case '-':
                    memoryBuffer[positionCursor]--;
                    break;
                case '>':
                    positionCursor++;
                    break;
                case '<':
                    positionCursor--;
                    break;
                case '.':
                    System.out.print(memoryBuffer[positionCursor]);
                    break;
                case ',':
                    memoryBuffer[positionCursor] = sc.next().charAt(0);
                    break;
                case '[':
                    if (memoryBuffer[positionCursor] == 0) {
                        positionCommand = findPositionCommandLoopEnd(commands, positionCommand);
                    } else {
                        stack.push(positionCommand);
                    }
                    break;
                case ']':
                    if (memoryBuffer[positionCursor] != 0) {
                        if (!stack.empty()) {
                            positionCommand = stack.pop();
                            positionCommand--;
                        }
                    } else {
                        if (!stack.empty()) {
                            stack.pop();
                        }
                    }
                    break;
            }
            positionCommand++;
        }
    }

    private int findPositionCommandLoopEnd(char[] commands, int start) {
        for (int i = start; i < commands.length; i++) {
            char command = commands[i];
            if (command == ']') {
                return i;
            }
        }
        throw new RuntimeException("Incorrect syntax: not find end loop");
    }

    private static String readSourceCode(final String fileName) {
        File file = new File(fileName);
        StringBuilder sourceCode = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                sourceCode.append(sc.nextLine());
            }
            return sourceCode.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
