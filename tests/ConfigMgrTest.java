package tests;

import ptg.ConfigMgr;

/**
 * Unit Testing ConfigMgr Class
 *
 * This test is performed to ensure that the data passed 
 * to this class is parsed and stored correctly. More than 
 * 5 arguments can be passed to this class which allows for
 * future arguments to be added to the PTG software system; 
 * the system will be tested with up to 16 arguments..
 * 
 * Test Data
 * The expected data is a string array with an '=' sign separating the string(s).
 * this data will be split with the left string serving as the key to a hashmap and
 * the right string the value of the map.The entire string to be passed i.e. 'string1=string1" 
 * will be trimmed so that whitespace is removed from the beginning and the end of the 
 * string if it exist. The string will also be converted to lower case.
 * 
 * @version ver.1.1
 * 
 */
public class ConfigMgrTest extends junit.framework.TestCase
{
    public void testStringSplit()
    {
        String[] str = new String[1];
        str[0] = "ABCDEFG=1234567";
        ConfigMgr configMg1 = new ConfigMgr(str);
        assertEquals("1234567", configMg1.getConfig("abcdefg"));
    }
    
    public void testStringTrimBckLowerCase()
    {
        String[] str = new String[1];
        str[0] = "A B C D E F G = 1 2 3 4 5 6 7 ";
        ConfigMgr configMg1 = new ConfigMgr(str);
        assertEquals("1 2 3 4 5 6 7", configMg1.getConfig("a b c d e f g "));        
    }
    


	public void testToLowerCaseTrimBck()
	{
	    String[] str = new String[1];
	    str[0] = " A a B b C c D d E e=1a2b3c4d5e ";
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("1a2b3c4d5e", configMg1.getConfig(" a a b b c c d d e e"));
	}

	public void testPassManyArgs()
	{
	    String[] str = new String[16];
	    str[0] = "1=a";
	    str[1] = "2=b";
	    str[2] = "3=c";
	    str[3] = "4=d";
	    str[4] = "5=e";
	    str[5] = "6=f";
	    str[6] = "7=g";
	    str[7] = "8=h";
	    str[8] = "9=i";
	    str[9] = "10=j";
	    str[10] = "11=k";
	    str[11] = "12=l";
	    str[12] = "13=m";
	    str[13] = "14=n";
	    str[14] = "15=o";
	    str[15] = "16=p";
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("a", configMg1.getConfig("1"));
		assertEquals("b", configMg1.getConfig("2"));
		assertEquals("c", configMg1.getConfig("3"));
		assertEquals("d", configMg1.getConfig("4"));
		assertEquals("e", configMg1.getConfig("5"));
		assertEquals("f", configMg1.getConfig("6"));
		assertEquals("g", configMg1.getConfig("7"));
		assertEquals("h", configMg1.getConfig("8"));
		assertEquals("i", configMg1.getConfig("9"));
		assertEquals("j", configMg1.getConfig("10"));
		assertEquals("k", configMg1.getConfig("11"));
		assertEquals("l", configMg1.getConfig("12"));
		assertEquals("m", configMg1.getConfig("13"));
		assertEquals("n", configMg1.getConfig("14"));
		assertEquals("o", configMg1.getConfig("15"));
		assertEquals("p", configMg1.getConfig("16"));
	}

	//negative tests
	public void testNoQuotes()
	{
	}
    
}




