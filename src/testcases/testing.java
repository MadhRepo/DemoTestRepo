package testcases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testing {
	static File junitReport;
	static BufferedWriter junitWriter;
	public static String TestCaseName;
	public static String junitReportFile;
	static String testvalue;

	public static void ReportPathsetup() {
		String value = "testing";
		TestCaseName = value;
		System.out.println("Before Class :: " + TestCaseName);
		String Path = System.getProperty("user.dir");
		// htmlResult ht = new htmlResult();

		// String testcaseName = ht.getClassName();
		System.out.println(Path + "\\Result\\" + TestCaseName + ".html");

		junitReportFile = Path + "\\Result\\" + TestCaseName + ".html";

	}

	@BeforeClass
	public static void setup() {

		ReportPathsetup();
		junitReport = new File(junitReportFile);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		try {
			junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
			junitWriter.write("<!DOCTYPE html><html>");
			junitWriter.write("<h1 align=\"center\" style=\"background-color:DodgerBlue;\">Test Execution Summary - "
					+ dateFormat.format(date) + "</h1>");
			junitWriter.write(
					"<h2 style=\"background-color:DodgerBlue;\"><span style=\"float:left;\">Test case Name :: Testing </span><span style=\"float:right;\">Executed By :: Jitendra</span></h2>");
			junitWriter.write(
					"<head><style>table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 15px;}</style></head>");
			junitWriter.write("<body>");

			junitWriter.write("<table style=\"width:100%\">");
			junitWriter.write(
					"<th>S.no#</th><th>Test Case Name</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Start Time</th><th>End Time</th>");

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	@Before
	public void writeReport() {
		System.out.println("Before Method");
	}

	@Test
	public void temp() {
		System.out.println("Inside method");
	}

	@After
	public void FinalReport() {
		System.out.println("After Method");
	}

	@AfterClass
	public static void Closing() {
		try {
			junitWriter.write("</table>");
			junitWriter.write("<footer>");
			junitWriter.write("<hr>");
			junitWriter.write("</footer>");
			junitWriter.write("</body></html>");

			junitWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("After Class");
	}

}
