package dumpster;



import java.util.*;

/* Nicholas Sam
 * Find the h-index of a given researcher
 * input format: firstname lastname num1 num2 ... numN
three-A three-A 2 0 0 1 3 1 7 3 0 0 1 0 2 1 1 3 1
five-A five-A 1 5 2 4 13 2 7 7 2 2 2 2 6 2 4 4 3
one-A one-A 0 2 0 0 0 1 1 1 1 0 0 0 1 0 0 0 0
six-A six-A 7 8 10 1 1 3 3 1 1 2 6 6 2 3 2 7 4
seven-A seven-A 11 1 13 1 7 4 3 4 15 9 2 3 4 9 3 12 1
two-A two-A 0 1 1 1 0 1 0 1 2 1 1 4 1 2 1 0 1
thirteen-A thirteen-A 8 15 35 4 17 14 15 18 1 14 31 18 13 27 13 32 6
thirteen-B thirteen-B 13 19 24 17 14 17 14 5 13 16 7 20 13 36 5 18 17
 */

public class testtt 
{
    private static List<String> researchers = new ArrayList<String>();
    public static void main(String[] args) 
    {
        //input
        Scanner in = new Scanner(System.in);
        String input = null;
        while (!(input = in.nextLine()).isEmpty())
        {
            researchers.add(input);
        }
        in.close();
        //calculate h-index and sort
        hindex();
        //print name and h-index
        for (String str : researchers)
        {
            String[] temp = str.split(" ");
            System.out.println(temp[1] + " " + temp[2] + ": " + temp[0]);
        }
    }
    
    //helper class to reverse an array
    //used to change ascending sort to descending sort
    private static int[] reverse(int[] arr)
    {
        int n = arr.length;
        int temp;
        for (int i = 0; i < n/2; i++)
        {
            temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
        return arr;
    }
    private static void bSearch(int[] arr, int l, int r, int c) 
    {
        int index;
        int m = -1;
        while (l <= r) 
        {
            m = (l+r)/2;
            if (arr[m] < m+1)
            {
                r = m-1;
            }
            else if (arr[m] > m+1)
            {
                l = m+1;
            }
            else
            {
                l++;
            }
        }
        researchers.set(c, r+1 + " " + researchers.get(c));
    }
    private static void hindex()
    {
        //counter
        int c = 0;
        
        //for every researcher inputted
        for (String str : researchers) 
        {
            //split
            String[] researcher = str.split(" ");
            
            //array of citations
            int[] papers = new int[researcher.length-2];
            for (int i = 2; i < researcher.length; i++)
            {
                papers[i-2] = Integer.valueOf(researcher[i]);
            }
            //sort array of citations
            Arrays.sort(papers);
            papers = reverse(papers);
            
            //find h-index
            bSearch(papers,0,papers.length-1,c);
            c++;
        }
        //sort main arraylist by descending h-index then ascending first name then ascending last name
        Collections.sort(researchers,new Comparator<Object>(){ 
            public int compare(Object o1, Object o2) {
                //h-index
                Integer x1 = Integer.valueOf(((String) o1).split(" ")[0]);
                Integer x2 = Integer.valueOf(((String) o2).split(" ")[0]);
                int indexComp = x2.compareTo(x1);
                if (indexComp != 0) {
                    return indexComp;
                }
                //first name
                String s1 = ((String) o1).split(" ")[1];
                String s2 = ((String) o2).split(" ")[1];
                int fnameComp = s1.compareTo(s2);
                if (fnameComp != 0) {
                    return fnameComp;
                }
                //last name
                String l1 = ((String) o1).split(" ")[2];
                String l2 = ((String) o2).split(" ")[2];
                int lnameComp = l1.compareTo(l2);
                return lnameComp;
            }
        });
    }
}