package liveMarket;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MostActiveSecurities {
	WebDriver driver;

	@FindBy(xpath = "//h2[text()='Most Active Securities']")
	private WebElement Heading;

	@FindBy(id = "dataTime")
	private WebElement TimingInstance;

	@FindBy(xpath = "//*[@id='tab7']")
	private WebElement ValueTab;

	@FindBy(id = "tab8")
	private WebElement VolumeTab;

	@FindBy(xpath = "//*[@id='topValue']/tbody/tr")
	private List<WebElement> tabledata;

	@FindBy(xpath = "//*[@id='topVolume']/tbody/tr")
	private List<WebElement> tabledatatoVolume;

	public void MostActiveSecuritiesByValue(WebDriver driver, String inputvalue) {
		System.out.println(driver.getCurrentUrl());
		PageFactory.initElements(driver, this);

		System.out.println(driver.getCurrentUrl());
		if (inputvalue == "Value") {
			System.out.println(inputvalue);
			System.out.println(Heading.getText());
			System.out.println(TimingInstance.getText());
			System.out.println(tabledata.size());

			int counting = 0;
			do {
				System.out.println(tabledata.get(counting).getText());
				counting++;
			} while (counting < tabledata.size());
		} else {
			VolumeTab.click();
			System.out.println(Heading.getText());
			System.out.println(TimingInstance.getText());
			System.out.println(tabledatatoVolume.size());

			int counting = 0;
			do {
				System.out.println(tabledatatoVolume.get(counting).getText());
				counting++;
			} while (counting < tabledatatoVolume.size());
		}
	}

}
