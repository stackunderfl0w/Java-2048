import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static JFrame f = new JFrame("2048 Patrick Iacob");
    public static keylistener keyboard =new keylistener();
    public static board board;
    public static Screen screen;
    public static boolean rotate=false;
    private static int x=4;
    private static int y=4;
    public static boolean screen_on=true;
    public static void main(String[] args) {
        board = new board(x,y);
        if(screen_on) {
            board.print_board();
        }
        String next;
        if (screen_on) {
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.addKeyListener(keyboard);
            screen = new Screen();
            f.add(screen);


            f.pack();
            f.setFocusable(true);
            f.setVisible(true);
            f.setFocusable(true);
            f.requestFocusInWindow();
        }
        while (true){
            next = input("next");
            move(next);
        }

    }
    private static String input(String output) {
        System.out.println(output);
        return scanner.nextLine();
    }
    public static void move(String next){
        if(next.equals("undo")){
            board.undo();
        }
        else {
            board.move(next);
        }
        screen.paintScreen();

    }
    public static void reset(){
        board=new board(x,y);
        screen.paintScreen();
}
}
