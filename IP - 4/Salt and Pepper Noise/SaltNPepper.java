package saltnpepper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class SaltNPepper {
    public static void main(String[] args) throws IOException {

        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("/home/spit/Desktop/input2.jpg"));
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int rgb = 0, a, r, g, b;
        int low = 10, high = 240;
        
        BufferedImage out = new BufferedImage(w, h, img.getType());
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	a = 0;
                r = 0;
                g = 0;
                b = 0;
                
                rgb = img.getRGB(j, i);                            
                Color c = new Color(rgb);
                
                if (!(c.getRed() > low && c.getRed() < high) || !(c.getBlue() > low && c.getBlue() < high) || !(c.getGreen() > low && c.getGreen() < high))
                {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {        
                        try {
                            rgb = img.getRGB(j + x, i + y);
                            c = new Color(rgb);
                            a += c.getAlpha();
                            r += c.getRed();
                            g += c.getGreen();
                            b += c.getBlue();
                        } catch (Exception e) {
                            rgb = img.getRGB(j, i);
                            c = new Color(rgb);
                            a += c.getAlpha();
                            r += c.getRed();
                            g += c.getGreen();
                            b += c.getBlue();
                        }
                    }
                }
                    out.setRGB(j, i, new Color(r/9, g/9, b/9, a/9).getRGB());
                } else {
                    out.setRGB(j, i, rgb);
                }
                
            }
        }
        
        File f = new File("/home/spit/Desktop/output2.jpg");
        ImageIO.write(out, "jpg", f);  
        
    }
}