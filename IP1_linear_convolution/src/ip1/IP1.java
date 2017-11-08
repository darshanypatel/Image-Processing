package ip1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class IP1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter n1 : ");
        int n1 = Integer.parseInt(br.readLine());
        System.out.print("Enter x(n) : ");
        int x[] = new int[n1];
        String s[] = br.readLine().split(" ");
        for (int i = 0; i < n1; i++) {
            x[i] = Integer.parseInt(s[i]);
        }
        System.out.print("Enter the origin : ");
        int o1 = Integer.parseInt(br.readLine());
        
        System.out.print("Enter n2 : ");
        int n2 = Integer.parseInt(br.readLine());
        System.out.print("Enter h(n) : ");
        int h[] = new int[n2];
        s = br.readLine().split(" ");
        for (int i = 0; i < n2; i++) {
            h[i] = Integer.parseInt(s[i]);
        }
        System.out.print("Enter the origin : ");
        int o2 = Integer.parseInt(br.readLine());
        
        int finalOrigin = 0;
        int matrix[][] = new int[n2][n1];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n1; j++) {
                matrix[i][j] = x[j] * h[i];
            }
        }
        
        int y[] = new int[n1 + n2 - 1];
        int pos = 0;
        for (int z = 0; z < n1 + n2 - 1; z++) {
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n2; j++) {
                    if ((i + j < pos + 1) && (i + j > pos - 1)) { 
                        y[pos] += matrix[j][i];
                        if (i == o1 && j == o2) 
                            finalOrigin = pos;
                    }
                }
            }
            pos++;
        }
        
        System.out.println(Arrays.toString(y));
        System.out.println("Origin = " + finalOrigin);
        
    }
}
