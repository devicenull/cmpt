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
 */
public class ConfigMgrTest extends junit.framework.TestCase
{
    public void testStringSplit()
    {
        String str[] = {"ABCDEFG=1234567"};
        ConfigMgr configMg1 = new ConfigMgr(str);
        assertEquals("1234567", configMg1.getConfig("abcdefg"));
    }
    
    public void testStringTrimBckLowerCase()
    {
        String[] str = {"A B C D E F G = 1 2 3 4 5 6 7 "};
        ConfigMgr configMg1 = new ConfigMgr(str);
        assertEquals("1 2 3 4 5 6 7", configMg1.getConfig("a b c d e f g "));        
    }
    
	public void testToLowerCaseTrimBck()
	{
	    String[] str = {" A a B b C c D d E e=1a2b3c4d5e "};
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("1a2b3c4d5e", configMg1.getConfig(" a a b b c c d d e e"));
	}

	public void testPassManyArgs()
	{
	    String[] str = {"1=a","2=b","3=c","4=d","5=e","6=f","7=g","8=h"};
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("a", configMg1.getConfig("1"));
		assertEquals("b", configMg1.getConfig("2"));
		assertEquals("c", configMg1.getConfig("3"));
		assertEquals("d", configMg1.getConfig("4"));
		assertEquals("e", configMg1.getConfig("5"));
		assertEquals("f", configMg1.getConfig("6"));
		assertEquals("g", configMg1.getConfig("7"));
		assertEquals("h", configMg1.getConfig("8"));
	}
	
	public void testEmptyConfig()
	{
		String[] str = {"1="};
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("",configMg1.getConfig("1"));
	}

	public void testExtraEquals()
	{
		String[] str = {"1=a=2"};
		ConfigMgr configMg1 = new ConfigMgr(str);
		assertEquals("a=2",configMg1.getConfig("1"));
	}
    
}




