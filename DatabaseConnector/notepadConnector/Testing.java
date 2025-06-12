package notepadConnector;

import java.util.HashMap;

import org.junit.Test;

public class Testing extends TextFileOperation{

	@Test
	public void info()
	{
	System.out.println("Hello");
	readtxt();
	}
	
	public void write()
	{
		HashMap<String,String> writedata = new HashMap<>();
		writedata.put("Name", "Jitendra");
	}
}
