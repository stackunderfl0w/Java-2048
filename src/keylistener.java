import java.awt.event.*;
public class keylistener implements KeyListener{
    keylistener(){

    }
    //do wheneer key is pressed
    public void keyPressed(KeyEvent e) {
        try
        {
            //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            switch (KeyEvent.getKeyText(e.getKeyCode())){
                case "↑":Main.move("up"); break;
                case "←":Main.move("left"); break;
                case "↓":Main.move("down"); break;
                case "→":Main.move("right"); break;
                case "F":System.exit(0);break;
                case "P":Main.move("undo");break;
                case "⎋":Main.reset();break;
            }


            //if(KeyEvent.getKeyText(e.getKeyCode()).equals("A")){ System.out.println("sdadw");Main.move("left");}

            if(KeyEvent.getKeyText(e.getKeyCode()).equals("4")){Main.board.four=true;}
        }
        catch(Exception f)
        {
            f.printStackTrace(System.out);
            //error handling code
        }

    }
    //do whenever key is released
    public void keyReleased(KeyEvent e) {
        try
        {

            //if(KeyEvent.getKeyText(e.getKeyCode()).equals("U")){Main.max_fps=60;}"Left"
            if(KeyEvent.getKeyText(e.getKeyCode()).equals("4")){Main.board.four=false;}


        }
        catch(Exception f)
        {
            //error handling code
        }

    }
    //do whenever key is typed
    public void keyTyped(KeyEvent e) {
        //l.setText("Key Typed");
    }
}
