package gaussian;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Gaussian {

    public static void main(String[] args) throws IOException {

        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("/home/spit/Desktop/input.png"));
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int rgb = 0, pos = 0;
        int []r,g,b,a = new int[9];
        r = new int[9];
        g = new int[9];
        b = new int[9];
        int low = 10, high = 245;
        
        BufferedImage out = new BufferedImage(w, h, img.getType());
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	
                a = new int[9];
                r = new int[9];
                g = new int[9];
                b = new int[9];
                
                rgb = img.getRGB(j, i);                            
                Color c = new Color(rgb);
                pos = 0;
                if (!(c.getRed() > low && c.getRed() < high) || !(c.getBlue() > low && c.getBlue() < high) || !(c.getGreen() > low && c.getGreen() < high))
                {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {        
                        try {
                            rgb = img.getRGB(j + x, i + y);
                            c = new Color(rgb);
                            a[pos] = c.getAlpha();
                            r[pos] = c.getRed();
                            g[pos] = c.getGreen();
                            b[pos] = c.getBlue();
                        } catch (Exception e) {
                            rgb = img.getRGB(j, i);
                            c = new Color(rgb);
                            a[pos] = c.getAlpha();
                            r[pos] = c.getRed();
                            g[pos] = c.getGreen();
                            b[pos] = c.getBlue();
                        }
                        pos++;
                    }
                }
                    Arrays.sort(a);
                    Arrays.sort(r);
                    Arrays.sort(g);
                    Arrays.sort(b);
                            
                    out.setRGB(j, i, new Color(r[4], g[4], b[4], a[4]).getRGB());
                } else {
                    out.setRGB(j, i, rgb);
                }
                
            }
        }
        
        File f = new File("/home/spit/Desktop/output11.png");
        ImageIO.write(out, "png", f);  
        
    }
}
