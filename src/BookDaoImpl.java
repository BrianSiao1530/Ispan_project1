import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoImpl implements BookDao {
	String dropString = "IF OBJECT_ID('dbo.Midterm', 'U') IS NOT NULL " 
			   + " DROP TABLE dbo.Midterm; ";
	String createString = "CREATE TABLE Midterm ( "
			 + "ListID  	INTEGER NOT NULL IDENTITY, "
			 + "依年度別分          		INTEGER, "
		     + " 乳癌觀察存活率			DECIMAL(4, 2)," 
		     + " 乳癌相對存活率 	 		DECIMAL(4, 2),"
		     + " 子宮頸癌觀察存活率 		DECIMAL(4, 2)," 
		     + " 子宮頸癌相對存活率 		DECIMAL(4, 2) ,"
		     + " 大腸直腸癌觀察存活率 	DECIMAL(4, 2) ,"
		     + " 大腸直腸癌相對存活率 	DECIMAL(4, 2) , "
		     + " PRIMARY KEY (ListID)"
		     + ")" ;
	
	String insertBookString = "INSERT INTO Midterm " + "(依年度別分, 乳癌觀察存活率,"
		     +" 乳癌相對存活率, 子宮頸癌觀察存活率, 子宮頸癌相對存活率,"
		     +"大腸直腸癌觀察存活率, 大腸直腸癌相對存活率 "
		     + ")"
		     + " VALUES " + "(?, ?, ?, ?, ?, ?, ?)";
	
	String updateBookString = "UPDATE Midterm SET 依年度別分 = ?, 乳癌觀察存活率 = ?, 乳癌相對存活率 = ?, "
			+ " 子宮頸癌觀察存活率 = ?, 子宮頸癌相對存活率 = ?, 大腸直腸癌觀察存活率 = ?, 大腸直腸癌相對存活率 = ?  "
			+ " WHERE bookId  =  ?";
	
	String queryById = "SELECT * FROM Midterm b WHERE b.ListID = ?";

	String DeleteWith = "Delete * from Midterm  where 依年度別分 = between ? and ?";
	
	String inputImage = "INSERT INTO midtermtable(FileName, FileContent, FileType) VALUES (?, ?, ?)";
	String dropString1 = "IF OBJECT_ID('dbo.midtermtable', 'U') IS NOT NULL " 
			   + " DROP TABLE dbo.midtermtable; ";
	String createString1 = "CREATE TABLE midtermtable ( "
			 + "ListID  	INTEGER NOT NULL IDENTITY, "
			 + "FileName    		nvarchar(MAX), "
		     + " FileContent		image,"
		     + " FileType	 		nvarchar(MAX)"
		     + ")" ;
	String outputImage ="SELECT * from midtermtable " ;
	
	public void create() throws SQLException {
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	

				PreparedStatement stmt1 = conn.prepareStatement(dropString);	
				PreparedStatement stmt2 = conn.prepareStatement(createString);
			) {
				stmt1.executeUpdate();
				stmt2.executeUpdate();	
				System.out.println("新建表格成功");
			} 
	}

	@Override
	public void insert() throws SQLException {
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	
				PreparedStatement stmt = conn.prepareStatement(insertBookString);
			)
			{
			ArrayList<String> firstRow = new ArrayList<>();
			//存橫的第一行文字
			String tempString = "";
			
			////
			URL fileURL = new URL("https://www.hpa.gov.tw/File/Attach/12664/File_14576.csv");
			URLConnection urlConn = fileURL.openConnection();
		    InputStreamReader inputCSV = new InputStreamReader(
	                    ((URLConnection) urlConn).getInputStream());
		    BufferedReader br = new BufferedReader(inputCSV);
		    /////
		    
//			FileReader targetFile = new FileReader("C:\\text\\File_14576.csv"); 
//			BufferedReader br = new BufferedReader(targetFile);
				
			while((tempString = br.readLine()) != null) {
				if(firstRow.isEmpty()) {
					for(String text : tempString.split(",")) {
						firstRow.add(text);
					}
				}else{
					String[] content = tempString.split(",");

					BookBean bookBean = new BookBean(Integer.parseInt(content[0]), Double.parseDouble(content[1]), 								Double.parseDouble(content[2]), Double.parseDouble(content[3]),
								Double.parseDouble(content[4]), Double.parseDouble(content[5]),
								Double.parseDouble(content[6]));
						stmt.setInt(1, bookBean.getYear());
						stmt.setDouble(2, bookBean.getBCOSR());
						stmt.setDouble(3, bookBean.getBCRSR());
						stmt.setDouble(4, bookBean.getCCOSR());
						stmt.setDouble(5, bookBean.getCCRSR());
						stmt.setDouble(6, bookBean.getCRCOSR());
						stmt.setDouble(7, bookBean.getCRCOSR());
						stmt.executeUpdate();
				}
			}
			System.out.println("新增成功");
//			targetFile.close();
			br.close();
		}catch(Exception e){
			System.err.print("IO時發生例外: " + e);
			e.printStackTrace();
		}
	}
	public BookBean read(int ID) throws SQLException{
		BookBean foundBean = new BookBean(0, 0, 0, 0, 0, 0, 0, 0);
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	
				PreparedStatement stmt = conn.prepareStatement(queryById);
			)
		{
			stmt.setInt(1,ID);
			try (
					ResultSet resultSet = stmt.executeQuery();
				) {
				if (resultSet.next()) {
					foundBean= new BookBean(
							resultSet.getInt("ListId"),
							resultSet.getInt("依年度別分"),
							resultSet.getDouble("乳癌觀察存活率"),
							resultSet.getDouble("乳癌相對存活率"),
							resultSet.getDouble("子宮頸癌觀察存活率"),
							resultSet.getDouble("子宮頸癌相對存活率"),
							resultSet.getDouble("大腸直腸癌觀察存活率"),
							resultSet.getDouble("大腸直腸癌相對存活率")
							);
					}
				}
		catch(Exception e){
			System.err.print("存取資料庫時發生例外: " + e);
			e.printStackTrace();
			}
		}
		System.out.println(foundBean.toString());
		System.out.println("查詢成功");
		return foundBean;
	}
	public void findDelete( int start,int end){
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	
				PreparedStatement stmt = conn.prepareStatement(DeleteWith);
			)
			{
				stmt.setInt(1, start);
				stmt.setInt(2, end);
				int n = stmt.executeUpdate();
				if(n < 0) {
					System.out.println("n < 0 :找不到");
				}
			}
		catch(Exception e) {
			System.err.print("存取資料庫時發生例外: " + e);
			e.printStackTrace();
		}
		System.out.println("刪除成功");
 }
	public void readimage() {
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	
				PreparedStatement stmt1 = conn.prepareStatement(dropString1);	
				PreparedStatement stmt2 = conn.prepareStatement(createString1);
				PreparedStatement stmt = conn.prepareStatement(inputImage);
				){
			 		stmt1.executeUpdate();
			 		stmt2.executeUpdate();
			 		File in = new File("C:\\Users\\Student\\Downloads\\4132913_211.jpg");
			 		FileInputStream fis = new FileInputStream(in);
			 		BufferedInputStream bis = new BufferedInputStream(fis);
			 		byte[] bytes = bis.readAllBytes();
			 		String filename = (in.getName());
			 		int num = filename.length();
			 		bis.close();
			   ;
		      stmt.setString(1, filename.substring(0,(num-4)));
		      stmt.setBytes(2, bytes);
		      stmt.setString(3,filename.substring((num-4), num));
		      stmt.executeUpdate();
		      System.out.println("成功");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void outputImage(int ScanInt) {
		try (
				Connection conn = DriverManager.getConnection(
						SystemConstant.getDbURL(), 
						SystemConstant.getUser(), 
						SystemConstant.getPassword());	
				PreparedStatement stmt = conn.prepareStatement(outputImage);
				){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(Integer.parseInt(rs.getString(1)) == ScanInt ){
					String filname = rs.getString(2);
					byte[] bytes = rs.getBytes(3);
					String filType = rs.getString(4);
					FileOutputStream fos = new FileOutputStream("C:\\midOut\\" + filname + filType);
					fos.write(bytes);
					fos.close();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
	}
}


