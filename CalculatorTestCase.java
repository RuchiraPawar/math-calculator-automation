import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;

public class CalculatorTestCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.math.com/students/calculators/source/basic.htm");

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// @Test
	public void test() {
		String expected = "";
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0, "plus");
		map.put(1, "minus");
		map.put(2, "times");
		map.put(3, "div");
		int operator = (int) Math.round(Math.random() * 3);
		long num1 = Math.round(Math.random() * 9);
		long num2 = Math.round(Math.random() * 9);
		System.out.println(num1);
		System.out.println(num2);
		String str = map.get(operator);
		System.out.println(str);
		// driver.findElement(By.name("Input")).sendKeys(String.valueOf(num1));
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + num1 + "  ']")).click();
		driver.findElement(By.xpath("//input[@name='" + str + "']")).click();
		// driver.findElement(By.name("Input")).sendKeys(String.valueOf(num2));
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + num2 + "  ']")).click();

		driver.findElement(By.name("DoIt")).click();

		String actual = driver.findElement(By.name("Input")).getAttribute("value");
		switch (str) {

		case "plus":
			expected = String.valueOf(num1 + num2);
			break;

		case "minus":
			expected = String.valueOf(num1 - num2);
			break;

		case "times":
			expected = String.valueOf(num1 * num2);
			break;

		case "div":
			if (num2 == 0) {
				expected = "Infinity";
				break;
			}

			expected = String.valueOf(((double) num1 / (double) num2));
			break;
		}

		// String expected=String.valueOf(num1 + num2);
		String message = "Expected '" + num1 + "' " + str + " '" + num2 + "' to equal '" + expected + "' ,but it was '"
				+ actual + "'";
		assertEquals(message, expected, actual);
	}

	public long generateThreeDigitRandomNum() {
		long num1 = Math.round(Math.random() * 999);
		int var1 = (int) (num1 / 100 % 10);
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + var1 + "  ']")).click();
		int var2 = (int) (num1 / 10 % 10);
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + var2 + "  ']")).click();
		int var3 = (int) (num1 % 10);
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + var3 + "  ']")).click();
		return num1;
	}

	public long generateOneDigitRandomNum() {
		long num1 = Math.round(Math.random() * 99);
		int var2 = (int) (num1 / 10 % 10);
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + var2 + "  ']")).click();
		int var3 = (int) (num1 % 10);
		driver.findElement(By.xpath("//input[@type='button' and @value='  " + var3 + "  ']")).click();
		return num1;
	}

	public long generateTwoDigitRandomNum() {
		long num1 = Math.round(Math.random() * 9);
        driver.findElement(By.xpath("//input[@type='button' and @value='  " + num1 + "  ']")).click();
		return num1;
	}

	public String getRandomOperator() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0, "plus");
		map.put(1, "minus");
		map.put(2, "times");
		map.put(3, "div");
		int operator = (int) Math.round(Math.random() * 3);
		String str = map.get(operator);
		System.out.println(str);
		driver.findElement(By.xpath("//input[@name='" + str + "']")).click();
		return str;

	}

	public String getExpectedResult(long num1, long num2, String operator) {
		String expected1 = "";
		switch (operator) {

		case "plus":
			expected1 = String.valueOf(num1 + num2);
			break;

		case "minus":
			expected1 = String.valueOf(num1 - num2);
			break;

		case "times":
			expected1 = String.valueOf(num1 * num2);
			break;

		case "div":
			if (num2 == 0) {
				expected1 = "Infinity";
				break;
			}

			expected1 = String.valueOf(((double) num1 / (double) num2));
			break;
		}

		return expected1;
	}

	@Test
	public void test1() {

		long number1;
		long number2;
		String str1 = "";
		long numb = Math.round(Math.random() * 2);
		if (numb == 0) {
			number1 = generateOneDigitRandomNum();
			str1 = getRandomOperator();
			number2 = generateOneDigitRandomNum();
			driver.findElement(By.name("DoIt")).click();

		} else if (numb == 1) {
			number1 = generateTwoDigitRandomNum();
			str1 = getRandomOperator();
			number2 = generateTwoDigitRandomNum();
			driver.findElement(By.name("DoIt")).click();

		} else {
			number1 = generateThreeDigitRandomNum();
			str1 = getRandomOperator();
			number2 = generateThreeDigitRandomNum();
			driver.findElement(By.name("DoIt")).click();
		}
		String actual = driver.findElement(By.name("Input")).getAttribute("value");
		String expected = getExpectedResult(number1, number2, str1);

		String message = "Expected '" + number1 + "' " + str1 + " '" + number2 + "' to equal '" + expected
				+ "' ,but it was '" + actual + "'";
		assertEquals(message, expected, actual);
	}

}