import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.lang.Math.*;

class Screen extends JPanel {
    //setup emulated screen
    private Graphics g;
    private int scale = 1; //10 pixels for each emulated-system pixel.
    private int width = 720 * scale;
    private int height = 720 * scale;
    private int size;
    private int size_x;
    private int size_y;
    private int board_size;
    private int board_x;
    private int board_y;
    //final Font font = new Font("Arial", Font.BOLD, 12);

    //Random random = new Random();
    //long z = 0;
    //setup audio variables
    //AudioFormat audioFormat;
    //AudioInputStream audioInputStream;
    //JLabel imgLabel = new JLabel(new ImageIcon("path_to_image.png"));

    //used to check if screen was set up
    public Screen() {
        System.out.println("Screen initialized");
    }
    //set screen size/resolution
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }




    /**
     * Paints full screen from screen memory.
     */
    private void paintFullScreen() {
        int tile_size=size/(Main.board.board.length+1);
        int tile_spacing=tile_size/(Main.board.board.length+1);
        int arc_size=size/24;
        Font font = new Font("Arial", Font.BOLD, size/(2*Main.board.board.length));
        for(int x=0; x<Main.board.board.length;x++){
            for(int y=0; y<Main.board.board[0].length;y++){
                int tile_x=(tile_size*x)+tile_spacing*(x+1);
                int tile_y=(tile_size*y)+tile_spacing*(y+1);
                g.setFont(font);
                int value= Main.board.board[x][y];
                g.setColor(getBackground(value));
                g.fillRoundRect(tile_y,tile_x,tile_size,tile_size,arc_size,arc_size);

                g.setColor(Color.WHITE);
                String s = String.valueOf(value);
                final FontMetrics fm = getFontMetrics(font);
                final int w = fm.stringWidth(s);
                final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
                if (value != 0) {
                    g.drawString(s,tile_y + tile_size/2 -w/2,tile_x + tile_size/2 +h/3);
                }
            }
        }
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Score:"+ Main.board.score,0,size);


    }


    /**
     * Paints full screen from screen memory. Public.
     */
    public void paintScreen() {
        repaint();
    }


    /**
     * Paints the component. It has to be called through paintScreen().
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;

        // Draw background
        g.setColor(Color.GRAY);
        board_x=Main.board.board[0].length;
        board_y=Main.board.board.length;
        board_size=board_y;

        if(getHeight()/board_y>getWidth()/board_x){
            size=getWidth()*board_size/board_x;
        }
        else{
            size=getHeight()*board_size/board_y;
        }
        /*
        if(getHeight()>getWidth()){
            size=getWidth();
        }
        else {
            size=getHeight();
        }*/
        g.fillRect(0, 0, size*board_x/board_size,size*board_y/board_size);

        paintFullScreen();
    }
    //audio player
    //synchronized as to not freeze screen while playing audio
    public synchronized void playAudio(final String url) {
        new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
            public void run() {
                try {
                    //gets audio file and starts playing
                    BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream(url));
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audio2 = AudioSystem.getAudioInputStream(myStream);
                    clip.open(audio2);
                    clip.start();
                    clip.addLineListener( new LineListener() {
                        public void update(LineEvent evt) {
                            if (evt.getType() == LineEvent.Type.STOP) {
                                evt.getLine().close();
                            }
                        }
                    });

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
    public Color getBackground(int value) {
        switch (value) {
            case 0:    return Color.GRAY;
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }
}
