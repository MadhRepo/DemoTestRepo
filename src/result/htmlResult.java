package result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class htmlResult {
	static File junitReport;
	static BufferedWriter junitWriter;
	
	
	@Test
	public void Setup(){
		
		String Path= System.getProperty("user.dir");
		htmlResult ht= new htmlResult();
		
		String testcaseName=ht.getClassName();
		System.out.println(Path+"\\Result\\"+testcaseName+".html");
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		String junitReportFile= Path+"\\Result\\"+testcaseName+".html";
		junitReport = new File(junitReportFile);
		try {
			junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
			junitWriter.write("<!DOCTYPE html><html>");
			junitWriter.write("<h1 align=\"center\" style=\"background-color:DodgerBlue;\">Test Execution Summary - " + dateFormat.format(date)+ "</h1>");	
			junitWriter.write("<h2 style=\"background-color:DodgerBlue;\"><span style=\"float:left;\">Scenario Name :: Testing </span><span style=\"float:right;\">Executed By :: Jitendra</span></h2>");
			junitWriter.write("<head><style>table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 15px;}</style></head>");
			junitWriter.write("<body>");
			
			junitWriter.write("<table style=\"width:100%\">");
			junitWriter.write("<th>S.no#</th><th>Test Case Name</th><th>Expected Result</th><th>Actual Result</th><th>Status</th><th>Start Time</th><th>End Time</th>");
				
			for(int i =1;i<=10;i++)
			{
				result(i);
			}
			
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
 }
	
	public String getClassName() {
        String className = this.getClass().getSimpleName(); 
        System.out.println("Name:" + className);
		return className;
    }
	
	public void result(int counter)
	{
		try {
			junitWriter.write("<tr>");
			junitWriter.write("<td align=\"center\">"+counter+"</td>");
			junitWriter.write("<td>Verify User Name</td>");
			junitWriter.write("<td>jitsolution19@gmail.com</td>");
			junitWriter.write("<td>jitsolution19@gmail.com</td>");
			junitWriter.write("<td align=\"center\"><font color=\"Green\">Pass</font></td>");
			SimpleDateFormat Starttime = new SimpleDateFormat("HH:mm:ss");

		    Date now = new Date();

		    String strTime = Starttime.format(now);
			junitWriter.write("<td align='center'>"+strTime+"</td>");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat EndTime = new SimpleDateFormat("HH:mm:ss");
			Date now1 = new Date();

		    String EndTim = EndTime.format(now1);

			junitWriter.write("<td align=\"center\">"+EndTim+"</td>");
			junitWriter.write("</tr>");


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}
}
