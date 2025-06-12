package result;

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
import org.openqa.selenium.WebDriver;
import liveMarket.MostActiveSecurities;

public class MyReportingUtil {

	WebDriver driver;
	MostActiveSecurities MASBYU;
	static File junitReport;
	static BufferedWriter junitWriter;
	int counter = 1;
	static int TotalStepExecuted;
	static Date ExecutionStartTime;
	static Date EndTimenow;

	@BeforeClass
	public static void Reportsetup() {
		String Path = System.getProperty("user.dir");

		String testcaseName = "LiveMarket_MostActiveSecurities";
		System.out.println(Path + "\\Result\\" + testcaseName + ".html");

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		ExecutionStartTime = date;
		String junitReportFile = Path + "\\Result\\" + testcaseName + ".html";
		junitReport = new File(junitReportFile);
		try {
			junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
			junitWriter.write("<!DOCTYPE html><html>");
			junitWriter.write("<h1 align=\"center\" style=\"background-color:DodgerBlue;\">Test Execution Summary - "
					+ dateFormat.format(date) + "</h1>");
			junitWriter
					.write("<h2 style=\"background-color:DodgerBlue;\"><span style=\"float:left;\">Test case Name :: "
							+ testcaseName + "</span><span style=\"float:right;\">Executed By :: Jitendra</span></h2>");
			junitWriter.write(
					"<head><style>table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 15px;}</style></head>");
			junitWriter.write("<body>");
			junitWriter.write("<table style=\"width:100%\">");
			junitWriter.write(
					"<th>S.no#</th><th>Test Case Name</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Start Time</th><th>End Time</th>");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Before
	public void stepcounterStart() {
		TotalStepExecuted = 0;
		TotalStepExecuted = TotalStepExecuted + counter;
		System.out.println();
	}

	@After
	public void StepcounterEnd() {
		TotalStepExecuted = TotalStepExecuted + counter;
	}

	public void reporting(String StatementDescription, String Expected, String Actual, String Status) {
		SimpleDateFormat Starttime = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String strTime = Starttime.format(now);
		try {
			junitWriter.write("<tr>");
			junitWriter.write("<td align=\"center\">" + counter + "</td>");
			junitWriter.write("<td>" + StatementDescription + "</td>");
			junitWriter.write("<td>" + Expected + "</td>");
			junitWriter.write("<td>" + Actual + "</td>");

			if (Status.equals("Pass")) {
				junitWriter.write("<td align=\"center\"><font color=\"Green\">Pass</font></td>");
			} else {
				junitWriter.write("<td align=\"center\"><font color=\"Red\">Fail</font></td>");
			}
			junitWriter.write("<td align='center'>" + strTime + "</td>");

			SimpleDateFormat EndTime = new SimpleDateFormat("HH:mm:ss");
			Date EndTimenow = new Date();
			String EndTiming = EndTime.format(EndTimenow);
			junitWriter.write("<td align='center'>" + EndTiming + "</td>");
			junitWriter.write("</tr>");
			counter++;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@AfterClass
	public static void ReportClose() {
		// TotalStepExecuted=this.TotalStepExecuted;
		try {
			junitWriter.write("</table>");
			junitWriter.write("<footer>");
			EndTimenow = new Date();
			long TotalTime = EndTimenow.getTime() - ExecutionStartTime.getTime();
			long differenceSeconds = TotalTime / 1000 % 60;
			long differenceMinutes = TotalTime / (60 * 1000) % 60;
			long differenceHours = TotalTime / (60 * 60 * 1000) % 24;
			junitWriter.write("<hr><span style=\"float:left;\">Total Step Executed :: " + TotalStepExecuted
					+ "</span><span style=\"float:right;\">Total Execution Time ::" + differenceHours + " :HH "
					+ differenceMinutes + " :MM " + differenceSeconds + " :SS</span></hr>");

			junitWriter.write("</footer>");
			junitWriter.write("<br>");
			junitWriter.write("</body></html>");
			junitWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
