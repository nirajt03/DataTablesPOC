package io.testing.tables.datatables;

import java.util.List;
import java.util.Map;

public interface IDataReader {
	
	/**
	 * To get all the rows from the excel
	 * @return
	 */
	public List<Map<String, String>> getAllRows();
	
	
	/**
	 * To get a single row from the excel
	 * @return
	 */
	public Map<String, String> getASingleRow();
}
