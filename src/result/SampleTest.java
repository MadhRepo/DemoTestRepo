package result;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SampleTest extends XmlReportGenerator{
	
	@BeforeClass
	public static void setup() {
		String File_Location = "C:\\Users\\LENOVO\\git\\NSE_Automation\\Result\\XmlReport.xml";
		reportinitialize(File_Location,"Sample Project","Jitendra");
	}

	@Test()
	public void test() {
		ScenarioName("Scenario 1");
		TestCaseName("Test Case 1");
		int a=1;
		if(a==10)
		{
		TestStep("1", "Verify Input Data Matches", "Match should Success", "Match Success", "Pass");
		}else
		{
			TestStep("1", "Verify Input Data Matches", "Match should Success", "Match not Success", "Fail");
		}
		TestStep("2", "Verify Product Added", "Product Added Success", "Product Added Success", "Pass");
		TestStep("3", "Verify Product dispaly in Cart", "Product dispaly in Cart Success",
				"Product dispaly in Cart Success", "Pass");		
		Testdata("Sweet Home");

	}

	@Test()
	public void test2() {

		ScenarioName("Scenario 2");
		TestCaseName("Test Case 1");
		TestStep("1", "Verify Login", "Login Success", "Login Success", "Pass");
		TestStep("2", "Verify Product Added", "Product Added Success", "Product Added Success", "Pass");
		TestStep("3", "Verify Product dispaly in Cart", "Product dispaly in Cart Success",
				"Product dispaly in Cart Success", "Pass");
	}

	@Test()
	public void test3() {
		ScenarioName("Scenario 3");
		TestCaseName("Test Case 1");
		TestStep("1", "Verify Login", "Login Success", "Login Success", "Pass");
		TestStep("2", "Verify Product Added", "Product Added Success", "Product Added Success", "Pass");
		TestStep("3", "Verify Product dispaly in Cart", "Product dispaly in Cart Success",
				"Product dispaly in Cart Success", "Pass");
	}

	@AfterClass()
	public static void close() {
		reportClosure();
	}
}
