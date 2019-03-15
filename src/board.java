//import needed utilities
import java.util.Random;
import java.util.Arrays;
//declare new class
public class board {
    //declare needed variables
    private int size_x;
    private int size_y;
    Random rand = new Random();
    public int score=0;
    public final int undo_depth=50;
    public int[][] board;
    private int[][][] previous= new int[undo_depth][][];
    private int[] previous_score=new int[undo_depth];
    public boolean four;
    //create new board object
    public board(int x,int y){
        //create new board with size x,y and add 2 tiles
        board = new int[x][y];
        size_x=x;
        size_y=y;
        new_tile();
        new_tile();

    }
    //declare move meathod
    public void move(String next){
        //make copy of 2d array
        int[][] old_board= new int[board.length][board[0].length];
        int old_score=score;
        for(int x=0; x<board.length;x++){
            old_board[x]= Arrays.copyOf(board[x],board[x].length);
        }
        //determine move direction then move
        switch (next){
            case "left":left(); break;
            case "right":right(); break;
            case "up":up(); break;
            case "down":down(); break;
        }
        //check if anything happened
        //and if it did update the screen and add a new tile
        //and if nothing changed check if an moves are possible
        //if not, end the game
        if(!Arrays.deepEquals(board, old_board)){
            for(int x=previous.length-1;x>0;x--){
                previous[x]=previous[x-1];
                previous_score[x]=previous_score[x-1];
            }
            previous_score[0]=old_score;
            previous[0]=new int[board.length][];
            for(int x=0; x<board.length;x++){
                previous[0][x]= Arrays.copyOf(old_board[x],board[x].length);
            }
            new_tile();
            if(!Main.screen_on) {
                print_board();
            }
        }
        else{
            get_available(board);
        }
    }
    //undo last move(upto undo_depth moves)
    public void undo(){
        for(int x=0; x<board.length;x++){
            board[x]= Arrays.copyOf(previous[0][x],board[x].length);
        }
        score=previous_score[0];
        for(int x=0; x<previous.length-1;x++){
            previous[x]=previous[x+1];
            previous_score[x]=previous_score[x+1];
        }
    }
    //print board to console(debugging)
    public void print_board(){
        for (int x=0; x<board.length;x++){
            System.out.print("|");
            for (int y=0;y<board[x].length;y++){
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
        System.out.println();

    }
    //add a new tile to board
    private void new_tile(){
        int next=2;
        int[]cord= get_available(board);;
        if (rand.nextInt(10)==0){
            next=4;
        }
        if(four){next=4;}
        board[cord[0]][cord[1]]=next;
    }
    private int[] get_available(int[][] board){
        int available=board.length*board[0].length;
        for (int x=0;x<board.length;x++){
            for (int y=0;y<board[x].length;y++){
                if (board[x][y]!=0){
                    available--;
                }
            }
        }
        if (available==0){
            game_over();
        }
        while(true){
            int x=rand.nextInt(size_x);
            int y=rand.nextInt(size_y);
            if (board[x][y]==0){
                return new int[] {x,y};
            }
        }
    }
    //move all tiles left
    public void left(){
        //move all tiles to edge
        for (int x=0;x<board.length;x++){
            int[] line= new int[board[0].length];
            int i=0;
            for (int y=0;y<board[0].length;y++){
                if (board[x][y]!=0){
                    line[i]=board[x][y];
                    i++;
                }
            }
            board[x]=line;
        }
        //merge like tiles and move to edge again
        for (int x=0;x<board.length;x++){
            for (int y=0;y<board[x].length-1;y++){
                if (board[x][y]==0){
                    board[x][y]=board[x][y+1];
                    board[x][y+1]=0;
                }
                else if(board[x][y]==board[x][y+1]){
                    board[x][y]*=2;
                    board[x][y+1]=0;
                    score+=board[x][y];
                }
            }
        }
    }
    private void right() {
        //rotate board and then move left then rotate back
        board=rotate(board);
        board=rotate(board);
        left();
        board=rotate(board);
        board=rotate(board);
    }
    private void up() {
        //rotate board and then move left then rotate back
        board=rotate(board);
        left();
        board=rotate(board);
        board=rotate(board);
        board=rotate(board);
    }
    private void down() {
        //rotate board and then move left then rotate back
        board=rotate(board);
        board=rotate(board);
        board=rotate(board);
        left();
        board=rotate(board);
    }
    //print game over(todo add graphics gameover screen)
    private void game_over(){
        System.out.println("Game over");
        System.exit(0);
    }
    //rotate the board 90 degrees
    private int[][] rotate(int[][] old_board){
        int[][] new_board=new int[board[0].length][board.length];
        for(int x=0; x<old_board[0].length; x++){
            for(int y=0; y<old_board.length; y++){
                new_board[x][y] = old_board[y][old_board[0].length-1-x];
            }
        }
        return new_board;
    }
}
