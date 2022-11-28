import java.sql.*;
import java.util.*;

import java.io.*;


public class Midproject {
	static String insertData = "" ;
	static String dropString = "" ;
	static String createString = "" ;

	public static void main(String[] args) throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		BookDao Mybook = new BookDaoImpl();
		int fuc ;
		//直接去創
		Mybook.create();
		Mybook.insert();
		//依照功能去跑
		while(!((fuc =functionList(in)) == 5)) {
			if(fuc == 1) {
				//讀取想要的ID並輸出到桌面
				int slecetedId = functionlist(in);
				BookBean showedBean = Mybook.read(slecetedId);
				OutPutToFile(showedBean.toString(), slecetedId);	
			}
			else if(fuc == 2) {
				//選取要讀的範圍
				int chose2 = searchWithConditon2(in);
				int chose3 = searchWithConditon3(in);
				Mybook.findDelete(chose2,chose3);
			}
			else if(fuc == 3) {
				//讀取圖片
				Mybook.readimage();
			}
			else if(fuc == 4) {
				//輸出圖片
				Mybook.outputImage(searchForOut(in));
			}
		}
		System.out.println("結束");
		in.close();
	}

	public static int functionlist(Scanner in) {
		System.out.println("資料輸入完成"+"測試擷取....");
		System.out.println("測試擷取....");
		System.out.println("請輸入想要擷取的資料(請輸入LisID: 1 2 3 .... )");

		int insert = in.nextInt();
		return insert;
	}
	public static void OutPutToFile(String Data, int filename) throws IOException {
		File dir = new File("C:\\Users\\Student\\Desktop");
		if (!dir.exists()){
			dir.mkdirs();
		}
		if(Data.isEmpty()) {
			System.out.println("沒有文字輸入");
		}
		else {
			FileWriter fw = new FileWriter(new File(dir, filename + ".txt"));
			fw.write(Data);
			fw.close();
		}
		System.out.println("檔案創建成功");
	}
	public static String searchWithConditon(Scanner in) {
		System.out.println("輸入想刪除的資料");
		System.out.println("ListID 為 1,依年度別分 為 2,乳癌觀察存活率 為 3,乳癌相對存活率 為 4");
		System.out.println("子宮頸癌觀察存活率 為 5,子宮頸癌相對存活率 為 6,大腸直腸癌觀察存活率 為 7"
				+ " 大腸直腸癌相對存活率 為 8");
		int chose = in.nextInt();
		String output = "";
		
		if (chose == 1) 
			output = "ListID";
		else if(chose == 2)
			output = "依年度別分";
		else if(chose == 3)
			output = "乳癌觀察存活率";
		else if(chose == 4)
			output = "乳癌相對存活率";
		else if(chose == 5)
			output = "子宮頸癌觀察存活率";
		else if(chose == 6)
			output = "子宮頸癌相對存活率";
		else if(chose == 7)
			output = "大腸直腸癌觀察存活率";
		else if(chose == 8)
			output = "大腸直腸癌相對存活率";
		else
			output = null;
		return output;
	}
	public static int searchWithConditon2(Scanner in) {
		System.out.println("輸入想刪除的資料範圍(開始)");
		int chose = in.nextInt();		
		return chose;
	}
	public static int searchWithConditon3(Scanner in) {
		System.out.println("輸入想刪除的資料範圍(結束)");
		int chose = in.nextInt();		
		return chose;
	}
	public static int searchForOut(Scanner in) {
		System.out.println("請輸入想輸出的圖片ID");
		return in.nextInt();
	}
	public static int functionList(Scanner in) {
		System.out.println("功能表清單" + "\n" + "(一)讀取想要的ID並輸出到桌面" + "\n" + "(二)選取要讀的範圍" + "\n" + 
							"(三)讀取圖片"+ "\n" + "(四)輸出圖片" + "\n" + "(五)結束");
		return in.nextInt();
	}
}

