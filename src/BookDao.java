import java.sql.SQLException;

public interface BookDao {
	void create() throws SQLException;

	void insert() throws SQLException;

	BookBean read(int ID) throws SQLException;

	void findDelete(int searchWithConditon1 , int searchWithConditon2) throws SQLException;

	void readimage() throws SQLException;

	void outputImage(int Scan)throws SQLException;
}