package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Dp")
	public Object[][] loginData() throws Exception {

		String path = ".\\src\\test\\java\\utilities\\ExcelUtiltyFile.java";

		ExcelUtiltyFile xlutilty = new ExcelUtiltyFile(path);

		int totalrows = xlutilty.getRowCount("Sheet1");
		int totalcols = xlutilty.getCellCount("Sheet1", 1);

		Object data[][] = new Object[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {

			for (int j = 0; j < totalcols; j++) {
				data[i - 1][j] = xlutilty.getCellData("Sheet1", i, j);
			}
		}

		return data;
	}

}
