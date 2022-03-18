package production.business;

public class CheckersPiece {
    private String color;
    private boolean king=false;

    public CheckersPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing() {
        this.king = true;
    }
}
