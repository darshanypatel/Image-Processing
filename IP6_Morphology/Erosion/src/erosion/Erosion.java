package erosion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Erosion {

    public static void main(String[] args) throws IOException {
        String imageType = "GIF";
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("/home/spit/Desktop/img." + imageType));
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int s[][] = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        int temp[][] = new int[5][5];
        
        BufferedImage out1 = new BufferedImage(w, h, img.getType());
        BufferedImage out2 = new BufferedImage(w, h, img.getType());
        BufferedImage out3 = new BufferedImage(w, h, img.getType());
        BufferedImage out4 = new BufferedImage(w, h, img.getType());
        
        out1 = erosion(img, s, temp);
        out2 = dilation(img, s, temp);
        out3 = opening(img, s, temp);
        out4 = closing(img, s, temp);
        
        File f1 = new File("/home/spit/Desktop/erosion." + imageType);
        File f2 = new File("/home/spit/Desktop/dilation." + imageType);
        File f3 = new File("/home/spit/Desktop/opening." + imageType);
        File f4 = new File("/home/spit/Desktop/closing." + imageType);        
        
        ImageIO.write(out1, imageType, f1);          
        ImageIO.write(out2, imageType, f2);  
        ImageIO.write(out3, imageType, f3);  
        ImageIO.write(out4, imageType, f4);  
                
    }
    
    static BufferedImage erosion (BufferedImage img, int[][] s, int[][] temp) {
        int h, w, pos, rgb = 0;
        h = img.getHeight();
        w = img.getWidth();
        BufferedImage out1 = new BufferedImage(w, h, img.getType());
        
        for (int i = 2; i < h; i++) {
            for (int j = 2; j < w; j++) {            	             
                
                pos = 0;
                for (int x = -2; x <= 2; x++) {
                    for (int y = -2; y <= 2; y++) {        
                        try {
                            rgb = img.getRGB(j + x, i + y);
                            temp[x + 2][y + 2] = s[x + 2][y + 2] * rgb;
                            //System.out.println(s[pos]);
                        } catch (Exception e) {
                            rgb = img.getRGB(j, i);
                            temp[x + 2][y + 2] = s[x + 2][y + 2] * rgb;
                            //System.out.println(s[pos]);
                        }
                        pos++;
                    }
                }                        
                
                int min = Integer.MAX_VALUE;
                for (int z = 0; z < 5; z++) {
                    for (int q = 0; q < 5; q++) {
                        if (min > temp[z][q] && temp[z][q] != 0) {
                            min = temp[z][q];
                        }
                    }
                }
                out1.setRGB(j - 2, i - 2, min);    
            }
        }
        return out1;
    }
    
    static BufferedImage dilation (BufferedImage img, int[][] s, int[][] temp) {
        int h, w, pos, rgb = 0;
        h = img.getHeight();
        w = img.getWidth();
        BufferedImage out1 = new BufferedImage(w, h, img.getType());
        
        for (int i = 2; i < h; i++) {
            for (int j = 2; j < w; j++) {            	             
                
                pos = 0;
                for (int x = -2; x <= 2; x++) {
                    for (int y = -2; y <= 2; y++) {        
                        try {
                            rgb = img.getRGB(j + x, i + y);
                            temp[x + 2][y + 2] = s[x + 2][y + 2] * rgb;
                            //System.out.println(s[pos]);
                        } catch (Exception e) {
                            rgb = img.getRGB(j, i);
                            temp[x + 2][y + 2] = s[x + 2][y + 2] * rgb;
                            //System.out.println(s[pos]);
                        }
                        pos++;
                    }
                }                        
                
                int max = Integer.MIN_VALUE;
                for (int z = 0; z < 5; z++) {
                    for (int q = 0; q < 5; q++) {
                        if (max < temp[z][q] && temp[z][q] != 0) {
                            max = temp[z][q];
                        }
                    }
                }
                out1.setRGB(j - 2, i - 2, max);    
            }
        }
        return out1;
    }
    
    static BufferedImage opening (BufferedImage img, int[][] s, int[][] temp) {
        BufferedImage out1 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());  
        out1 = erosion(img, s, temp);     
        out1 = dilation(out1, s, temp);
        return out1;
    }
    
    static BufferedImage closing (BufferedImage img, int[][] s, int[][] temp) {
        BufferedImage out1 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());        
        out1 = dilation(img, s, temp);
        out1 = erosion(out1, s, temp);
        return out1;
    }
    
    
}