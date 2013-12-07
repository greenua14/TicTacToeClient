package generalClasses;

import java.io.Serializable;

public class Step implements Serializable{
    private int x;
    private int y;
    private String loginLast;
    private String loginNext;
    private boolean flag ;      //if true then u make a step else u read step

    public Step(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLoginLast() {
        return loginLast;
    }

    public String getLoginNext() {
        return loginNext;
    }

    public void setLoginLast(String loginLast) {
        this.loginLast = loginLast;
    }

    public void setLoginNext(String loginNext) {
        this.loginNext = loginNext;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
