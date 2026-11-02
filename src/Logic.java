import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWd = 360;
    int frameHg = 640;

    //Posisi player
    int playerStartPosX = frameWd / 8;
    int playerStartPosY = frameHg / 2;
    int playerWd = 34;
    int playerHg = 24;

    //Atribut panjang lebar pipa
    int pipeStrPosX = frameWd;
    int pipeStrPosY = 0;
    int pipeWd = 64;
    int pipeHg = 512;

    //biar gamenya looping selagi burung ga nabrak
    Timer loop;
    Timer pipesCd;
    int grav = 1;
    int pipeVeloX = -2;

    //buat nentuin kapan gamenya mulai, berenti, dan hitungan score
    boolean gameStarted = false;
    boolean gameOver = false;
    int score = 0;

    Image lowPipeImg;
    Image upPipeImg;
    ArrayList<Pipe> pipes;

    View view;
    Image birdImg;
    Player player;
    private Runnable onReturnToMenu; //buat callback

    public Logic(){
        //set image manuk dan pipa
        birdImg = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWd, playerHg, birdImg);

        lowPipeImg = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upPipeImg = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        //timer untuk pipa
        pipesCd = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe(); //ketika ada aksi, pipa nya ditempatin
            }
        });

        loop = new Timer(1000/60, this); //set loop game
    }

    //setter
    public void setOnReturnToMenu(Runnable onReturnToMenu){
        this.onReturnToMenu = onReturnToMenu;
    }

    public void startGame(){ //isinya kondisi awal game
        gameStarted = false; //false karena game bener bener mulai ketika baru pencet space
        gameOver = false; //belum nabrak
        pipes.clear(); //pipa masih kosong
        score = 0;
        player.setPosy(playerStartPosY);
        player.setVeloY(0);
        if (!loop.isRunning()) {
            loop.start();
        }
    }

    //setter getter
    public void setView(View view){
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return pipes;
    }

    public int getScore(){
        return score;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

    //randomize pipa atas bawah
    public void placePipe(){
        int randPosY = (int)(pipeStrPosY - pipeHg/4 - Math.random() * (pipeHg/2));
        int openingSpace = frameHg/4;

        Pipe upPipe = new Pipe(pipeStrPosX, randPosY, pipeWd, pipeHg, upPipeImg);
        pipes.add(upPipe);

        Pipe lowPipe = new Pipe(pipeStrPosX, randPosY + openingSpace + pipeHg, pipeWd, pipeHg, lowPipeImg);
        pipes.add(lowPipe);
    }

    //tentuin nabrak atau ngga
    public boolean collision(Player a, Pipe b) {
        //sumoa aku nemu di github logic nya, jadi intinya saling berlawanan aja. Burungnya terlalu kiri dengan sisi kanan pipa, burung terlalu kanan untuk sisi kiri pipa
        //burungnya terllau atas untuk sisi bawah pipa, burungnya terlalu bawah untuk sisi atas pipa
        return a.getPostX() < b.getPostX() + b.getWidth() && a.getPostX() + a.getWidth() > b.getPostX() && a.getPosY() < b.getPosY() + b.getHeight() && a.getPosY() + a.getHeight() > b.getPosY();
    }

    public void move(){
        if (!gameStarted || gameOver) return; //kalo gamenya kelar

        player.setVeloY(grav + player.getVeloY());
        player.setPosy(player.getPosY() + player.getVeloY());

        //kalo manuknya berusaha keluar frame (jatuh atau ketinggian)
        if (player.getPosY() + player.getHeight() >= frameHg || player.getPosY() < 0) {
            gameOver = true; //end
        }

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPostX() + pipeVeloX);

            if (collision(player, pipe)) {
                gameOver = true; //nabrak = end
            }

            //kalau bisa lewatin pipa
            if(!pipe.pass && player.getPostX() > pipe.getPostX() + pipe.getWidth()){
                pipe.pass = true;
                if(pipe.getImg() == upPipeImg){
                    score++; //nambah
                }
            }
        }
    }

    //fitur restart
    public void restartGame(){
        //reset player, pipa, score. literally balik kondisi awal
        player.setPosy(playerStartPosY);
        player.setVeloY(0);
        pipes.clear();
        score = 0;
        gameOver = false;
        gameStarted = false;
        pipesCd.stop();
        if (!loop.isRunning()) {
            loop.start();
        }
        if(view != null){
            view.requestFocusInWindow();
        }
    }

    public void returnToMenu(){
        pipesCd.stop();
        loop.stop();
        gameOver = false;
        gameStarted = false;
        pipes.clear();
        score = 0;
        player.setPosy(playerStartPosY);
        player.setVeloY(0);

        if(onReturnToMenu != null){
            onReturnToMenu.run();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        if(view != null){
            view.repaint();
        }
        if(gameOver){
            pipesCd.stop();
            loop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { //kalau pencet space
            if (!gameOver) { //net
                if (!gameStarted) { //kalo belum mulai
                    gameStarted = true; //mulai dan karakter gerak
                    pipesCd.start(); //random pipe
                }
                player.setVeloY(-10);
            }
        }
        //R restart
        else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
        //M back menu
        else if (e.getKeyCode() == KeyEvent.VK_M && gameOver) {
            returnToMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}
}