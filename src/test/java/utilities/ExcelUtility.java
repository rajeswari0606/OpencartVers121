package utilities;

	import java.io.File;
import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.FillPatternType;
	import org.apache.poi.ss.usermodel.IndexedColors;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtility {
		//just gonna create method once and refer in the below code
		public  FileInputStream fi;
		public  FileOutputStream fo;
		public  XSSFWorkbook wb;
		public  XSSFSheet sheet;
		public  XSSFRow row;
		public  XSSFCell cell;
		public  CellStyle style;
		String path;
	//create one constructor - getting the path	 whereever i need a path i am refering it
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
		//1st method - this will get the row count
		public int getRowcount(String sheetName) throws IOException //we need to pass excel file path and sheet name also we need to pass
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sheet=wb.getSheet(sheetName);
			int rowcount=sheet.getLastRowNum();
			wb.close();
			fi.close();
			return rowcount; // this will return the row count.
		}
		//2nd method - get the cell count
		public  int getCellcount(String sheetName,int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sheet=wb.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount=row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
		}
		//3rd method - get the cell data - this will read data from only one cell
			public String getCellData(String sheetName ,int rownum,int column) throws IOException
			{
				fi=new FileInputStream(path);
				wb=new XSSFWorkbook(fi);
				sheet=wb.getSheet(sheetName);
				row=sheet.getRow(rownum);
				cell=row.getCell(column);
				
					DataFormatter formatter=new DataFormatter();
					String data;
					try {
						data=formatter.formatCellValue(cell); //returns the formatted value of a cell as string regardless
				}
					catch(Exception e)
				{
					data="";//data value is equal to null
				}
				//what will happen if u don't put in try catch block? - at the time of reading the data from the cell either by using tostring or data formatter -sometime we dont have any data in excel.= at the time of reading  data from empty cell - sometimes it will throught DataNotFound Exception-to avoid that we are putting it in try catch block.
			wb.close();
			fi.close();
			return data;
			}
			//4th method - this particular method will write data into the cell
			public  void setCellData(String sheetName,int rownum,int column,String data) throws IOException
			{//read the data
				File xlfile=new File(path);
				if(!xlfile.exists()) //if file not exists then create new file
				{
					wb=new XSSFWorkbook();
					fo=new FileOutputStream(path);
					wb.write(fo);
					
				}
				fi=new FileInputStream(path);
				wb=new XSSFWorkbook(fi);
				if(wb.getSheetIndex(sheetName)==-1) //if sheet not exists then create new sheet
				wb.createSheet(sheetName);
				sheet=wb.getSheet(sheetName);
				
				if(sheet.getRow(rownum)==null) //if row not exists then create new row
					sheet.createRow(rownum);
				row=sheet.getRow(rownum);
				
				//we are doing combination of reading and writing
				//write the data
				cell=row.createCell(column);
				cell.setCellValue(data);
				fo=new FileOutputStream(path);
				wb.write(fo);
				wb.close();
				fi.close();
				fo.close();
			}
			//5th method - optional method for color change
			public  void FillGreenColor(String sheetName,int rownum,int column) throws IOException
			{//read the data
				fi=new FileInputStream(path);
				wb=new XSSFWorkbook(fi);
				sheet=wb.getSheet(sheetName);
				
				row=sheet.getRow(rownum);
				cell=row.getCell(column);
				
				style=wb.createCellStyle();
				
				style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				cell.setCellStyle(style);
				
				wb.write(fo);
				wb.close();
				fi.close();
				fo.close();
			}
	}

