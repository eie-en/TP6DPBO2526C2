import java.awt.*;

public class Player {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image img;
    private int veloY = -0;


    public Player(int posX, int posY, int width, int height, Image img){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.img = img;
        this.veloY = -0;
    }

    public  Player(){
    }

    //cuma setter getter untuk semua atribut
    public int getPostX(){
        return posX;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosy(int posy){
        this.posY = posy;
    }

    public int getWidth(){

        return width;
    }

    public void setWidth(int width){

        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {

        this.img = img;
    }

    public int getVeloY(){
        return veloY;
    }

    public void setVeloY(int veloY) {
        this.veloY = veloY;
    }
}
