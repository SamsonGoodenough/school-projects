package dumpster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Index {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String[]> results = new ArrayList<String[]>();
 
        String s = in.nextLine();
        while (s != "") {
        	String[] parts = s.split(" ");
        	String lastname = parts[0];
        	String firstname = parts[1];
        	Integer[] cites = new Integer[parts.length - 2];
        	for (int i = 0; i < cites.length; i++) {
        		cites[i] = Integer.parseInt(parts[i+2]);
        	}
        	
        	Arrays.sort(cites, Collections.reverseOrder());
        	
        	int h = hIndex(0, cites.length-1, cites);
            
            results.add(new String[] {lastname, firstname, Integer.toString(h)});
            s = in.nextLine();
        }
       
		in.close();
		results.sort(Comparator.comparingInt(el -> Integer.parseInt(el[2])));
		Collections.reverse(results);
		for (String[] str : results) {
			System.out.println(str[0] + " " + str[1] + " " + str[2]);
		}
	}
	
	public static int hIndex(int start, int end, Integer[] cites) {
		if (start == end) {
			return -1;
		}
		
		int i = (int) Math.ceil((start + end) / 2);
		if (cites[i] == i) {
			return i;
		} else if (cites[i] > i) {
			return hIndex(i+1, end, cites);
		} else {
			return hIndex(start, i-1, cites);
		}
	}

}
