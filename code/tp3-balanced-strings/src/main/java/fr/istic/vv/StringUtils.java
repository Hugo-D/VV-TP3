package fr.istic.vv;

import java.util.LinkedList;

public class StringUtils {

    private StringUtils() {}
    
    private static int isAt(char c, char[] cTab) {
    	for(int i=0; i < cTab.length; ++i) {
    		char iChar = cTab[i];
    		if(c==iChar) return i;
    	}
    	return -1;
    }
    
    private static boolean isBalancedIter(String str) {
    	LinkedList<Character> toClose = new LinkedList<Character>();
    	char[] openSymb = {'(','{','['};
    	char[] closeSymb = {')','}',']'};
    	int counter = 1;
    	int i=0;
        while(i<str.length()) {
        	//System.out.println(counter + " :\t" + str + "\t" + i);
        	char iChar = str.charAt(i);
        	if(isAt(iChar,closeSymb) != -1) {
        		if(!toClose.isEmpty() && iChar == toClose.getFirst()) {
        			toClose.removeFirst();
        			// ----*----
        			str = str.substring(i+1);
        			i = -1;
        			counter++;
        			// ----*----
        		} else { return false; }
        	} else if(isAt(iChar, openSymb) != -1) {
        		toClose.addFirst(closeSymb[isAt(iChar,openSymb)]);
        		// ----*----
        		str = str.substring(i+1);
        		i = -1;
        		counter++;
        		// ----*----
        	}
        	++i;
        }
        return true;
    }

    public static boolean isBalanced(String str) {
    	//return isBalancedRecu(str, new LinkedList<Character>());
    	return isBalancedIter(str);
    }

}
