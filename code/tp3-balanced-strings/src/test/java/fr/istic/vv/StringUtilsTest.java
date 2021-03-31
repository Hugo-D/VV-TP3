package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

class StringUtilsTest {
	
	private static char[] openSymb = new char[]{'(','{','['};
	private static char[] closeSymb = new char[]{')','}',']'};

	@Test
	public void Test_isBalanced_basicTrue() {
		String str = "()";
        assertTrue(isBalanced(str));
    }

    @Test
    public void Test_isBalanced_basicFalse() {
        String str = ")(";
        assertFalse(isBalanced(str));
    }
    @Test
    public void Test_isBalanced_multipleImbrications_True() {
        String str = "( ( ( ( ) ) ) )";
        assertTrue(isBalanced(str));
    }

    @Test
    public void Test_isBalanced_multipleImbricationsANDText_False() {
        String str = "( ( no rien de rien ( je ne regrette (rien  ) ) ) ) )";
        assertFalse(isBalanced(str));
    }
    @Test
    public void Test_isBalanced_sameLevelImbrications_True() {
        String str = "([][])";
        assertTrue(isBalanced(str));
    }
    @Test
    public void Test_isBalanced_complexImbrications_True() {
        String str = "({[()()][()()]})";
        assertTrue(isBalanced(str));
    }
    
    private static boolean isIn(int cAscii, int[] forbiddenAscii) {
    	for(int inTab : forbiddenAscii) {
    		if(cAscii == inTab) { return true; }
    	}
    	return false;
    }
    
    private static char generateRandomChar() {
    	int cAscii = (int)(Math.random()*(126-32)) + 32;
    	int[] forbiddenAscii = {40,41,91,92,93,123,125};
    	while(isIn(cAscii, forbiddenAscii)) {
    		cAscii = (int)(Math.random()*(126-32)) + 32;
    	}
    	return (char)cAscii;
    }
    
    private static String generateValideString() {
    	StringBuffer str = new StringBuffer();
    	LinkedList<Character> toClose = new LinkedList<Character>();
    	int nbIter = (int)(Math.random()*100)+300;
    	int i=0;
    	while(i< nbIter || !toClose.isEmpty()) {
    		if(Math.random() > 0.67) {
    			if(!toClose.isEmpty() && Math.random()>0.25) {
    				str.append(toClose.getFirst());
    				toClose.removeFirst();
    			} else {
    				int iSymb = (int)(Math.random()*3);
    				str.append(openSymb[iSymb]);
    				toClose.addFirst(closeSymb[iSymb]);
    			}
    		} else {
    			str.append(generateRandomChar());
    		}
    		++i;
    	}
    	return str.toString();
    }
    
    @Test
    public void Test_isBalanced_generator() {
    	int nbIter=1000;
    	for(int i=0; i<nbIter; ++i) {
    		String str = generateValideString();
    		assertTrue(str,isBalanced(str));
    	}
    }
    
    @Test
    public void Test_isBalanced_compactGeneration() {

        StringBuffer str = new StringBuffer();

        int nb1 = (int)(Math.random()*100)+100;

        for(int nbOuverte1=0; nbOuverte1< nb1; ++nbOuverte1) {
            if(Math.random()>0.80) {
                str.insert(0,'{');
                str.append('}');
            }else if (Math.random() > 0.5) {
                str.insert(0,'(');
                str.append(')');
            }else if(Math.random() >0.1) {
                str.insert(0,'[');
                str.append(']');
            }else {
                str.append(str);
                str.insert(0,'[');
                str.append(']');
            }
        }
        assertTrue(str.toString(),isBalanced(str.toString()));
    }

}