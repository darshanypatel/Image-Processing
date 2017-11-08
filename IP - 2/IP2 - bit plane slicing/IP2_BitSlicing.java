package ip2_bitslicing;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

class IP2_BitSlicing {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/home/spit/Desktop/input.jpeg"));
		} catch (IOException e) {
			System.out.println(e);
		}

		int w = img.getWidth();
		int h = img.getHeight();

		int rgb = 0, r, g, b;
                String sr, sg, sb, s;
                                            int bc=0,wc=0;
                for (int i1 = 0; i1 < 24; i1++) {
                
                    BufferedImage out = ImageIO.read(new File("/home/spit/Desktop/input.jpeg"));
                    
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                            rgb = img.getRGB(j, i);
                            Color c = new Color(rgb);
                            r = c.getRed();
			    g = c.getGreen();
                            b = c.getBlue();                            
                            
                            sr = Integer.toBinaryString(r);
                            sg = Integer.toBinaryString(g);
                            sb = Integer.toBinaryString(b);
                            if (sr.length() != 8) {
                                while (sr.length() != 8) {
                                    sr = "0" + sr;
                                }
                            }
                            if (sg.length() != 8) {
                                while (sg.length() != 8) {
                                    sg = "0" + sg;
                                }
                            }
                                              
                                              if (sb.length() != 8) {
                                while (sb.length() != 8) {
                                    sb = "0" + sb;
                                }
                            }
                            
                            s = sr + sg + sb;
                            //if (i1 <= 1)
                            //System.out.println(s);                        
                            int d = s.charAt(i1) == '0' ? 0 : 1;
                            //System.out.println(d);

                            if (d == 1)
                            {    out.setRGB(j, i, Color.WHITE.getRGB());
                                wc++;
                            }
                            else 
                            {
                                out.setRGB(j, i, Color.BLACK.getRGB());
                                bc++;
                            }
                    	}
                    }
                    File f = new File("/home/spit/Desktop/output/out" + i1 + ".jpeg");
                    ImageIO.write(out, "JPEG", f);
                    System.out.println(wc+" "+bc);
                    wc = 0;
                    bc = 0;
                }
                //System.out.println(Color.WHITE.getRGB() + " " + Color.BLACK.getRGB());
                sc.close();

	}
}