import java.awt.event.*;
public class keylistener implements KeyListener{
    keylistener(){

    }
    //do wheneer key is pressed
    public void keyPressed(KeyEvent e) {
        try
        {
            //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:Main.move("up"); break;
                case KeyEvent.VK_LEFT:Main.move("left"); break;
                case KeyEvent.VK_DOWN:Main.move("down"); break;
                case KeyEvent.VK_RIGHT:Main.move("right"); break;
                case KeyEvent.VK_F:System.exit(0);break;
                case KeyEvent.VK_BACK_SPACE:Main.move("undo");break;
                case KeyEvent.VK_R:Main.reset();break;
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
