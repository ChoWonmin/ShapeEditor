package shape;

public class ShapeColor {
    private int rgb;

    public ShapeColor(int r, int g, int b) {
        rgb = ((255 & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
    }

    public ShapeColor(int rgb) {
        this.rgb = 0xff000000 | rgb;
    }

    public void setRGB(int rgb) {
        this.rgb = rgb;
    }

    public int getRGB() {
        return rgb;
    }
}
