package shape;

import java.io.Serializable;

public class CentralPoint implements Serializable{
    private int centralX;
    private int centralY;

    public int getCentralX() {
        return centralX;
    }
    public void setCentralX(int centralX) {
        this.centralX = centralX;
    }
    public int getCentralY() {
        return centralY;
    }
    public void setCentralY(int centralY) {
        this.centralY = centralY;
    }

    public int getFloorX(int size){
        return centralX - size;
    }
    public int getFloorY(int size){
        return centralY - size;
    }

    public int getCeilX(int size){
        return centralX + size;
    }

    public int getCeilY(int size){
        return centralY + size;
    }




}
