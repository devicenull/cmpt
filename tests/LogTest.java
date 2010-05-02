package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ptg.Log;

public class LogTest {
	protected static String logFile = "C:\\ptglogtest.txt";

	@Before
	public void setUp() throws Exception
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
	}

	@After
	public void tearDown() throws Exception
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
	}

	@Test
	public void testInit()
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
		
		Log.Init(logFile);

    	assertTrue(logTest.exists());		
	}

	@Test
	public void testWarn() throws IOException
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
		Log.warn("__warn__");
		Log.flush();
		
		LineNumberReader f = new LineNumberReader(new FileReader(logFile));
		StringBuilder sb = new StringBuilder();
		while (f.ready())
		{
			sb.append(f.readLine());
		}
		assertTrue(-1 != sb.indexOf("__warn__"));
	}

	@Test
	public void testError() throws IOException
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
		Log.error("__error__");
		Log.flush();
		
		LineNumberReader f = new LineNumberReader(new FileReader(logFile));
		StringBuilder sb = new StringBuilder();
		while (f.ready())
		{
			sb.append(f.readLine());
		}
		assertTrue(-1 != sb.indexOf("__error__"));
	}

	@Test
	public void testInfo() throws IOException
	{
		File logTest = new File(logFile);
		if (logTest.exists())
		{
			logTest.delete();
		}
		Log.info("__info__");
		Log.flush();
		
		LineNumberReader f = new LineNumberReader(new FileReader(logFile));
		StringBuilder sb = new StringBuilder();
		while (f.ready())
		{
			sb.append(f.readLine());
		}
		assertTrue(-1 != sb.indexOf("__info__"));
	}

}
