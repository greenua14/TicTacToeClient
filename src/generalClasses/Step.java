package generalClasses;

import java.io.Serializable;

public class Step implements Serializable{
    private int x;
    private int y;
    private String loginLast;
    private String loginNext;

    public Step(int x, int y, String loginLast, String loginNext) {
        this.x = x;
        this.y = y;
        this.loginLast = loginLast;
        this.loginNext = loginNext;
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
}
