import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IP2 {

    public static void main(String[] args) throws IOException {
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("C:\\Users\\Foram\\Desktop\\input.jpg"));
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int l = 256;
        int rgb = 0, a, r, g, b;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	rgb = img.getRGB(j, i);
            	Color c = new Color(rgb);
            	a = c.getAlpha();
            	r = c.getRed();
            	g = c.getGreen();
            	b = c.getBlue();
            	
            	a = l - 1 - a;
            	r = l - 1 - r;
            	g = l - 1 - g;
            	b = l - 1 - b;
            	
                img.setRGB(j, i, new Color(a, r, g, b).getRGB());
            }
        }
        
        File f = new File("C:\\Users\\Foram\\Desktop\\output.jpg");
        ImageIO.write(img, "JPG", f);  
        
    }
}
