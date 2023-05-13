package io.testing.tables.csvtable;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.testing.tables.datatables.IDataReader;

public class CsvDataReader implements IDataReader {

	private final CsvConfiguration csvConfig;
	private Logger logger = LoggerFactory.getLogger(CsvDataReader.class);

	public CsvDataReader(CsvConfiguration csvConfig) {
		this.csvConfig = csvConfig;
	}

	@Override
	public List<Map<String, String>> getAllRows() {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		try (Reader in = new FileReader(new File(csvConfig.getFileLocation()))) {
			CSVFormat format = getCsvFormat();
			Iterable<CSVRecord> csvRecords = format.parse(in);

			csvRecords.forEach((csvRecord) -> {

				Map<String, String> csvRecordMap = new HashedMap<String, String>();
				csvRecordMap.put(CsvHeaders.first_name.name(), csvRecord.get(CsvHeaders.first_name.name()));
				csvRecordMap.put(CsvHeaders.last_name.name(), csvRecord.get(CsvHeaders.last_name.name()));
				csvRecordMap.put(CsvHeaders.email.name(), csvRecord.get(CsvHeaders.email.name()));
				csvRecordMap.put(CsvHeaders.gender.name(), csvRecord.get(CsvHeaders.gender.name()));
				csvRecordMap.put(CsvHeaders.city.name(), csvRecord.get(CsvHeaders.city.name()));

				data.add(csvRecordMap);

			});

		} catch (Exception e) {
			logger.error(e, () -> {
				return String.format("Not able to read the CSV %s from location %s", csvConfig.getFileName(),
						csvConfig.getFileLocation());
			});
			return Collections.emptyList();
		}

		return Collections.unmodifiableList(data);
	}

	private CSVFormat getCsvFormat() {
		return CSVFormat.EXCEL.builder().setSkipHeaderRecord(true).setHeader(CsvHeaders.class).build();
	}

	@Override
	public Map<String, String> getASingleRow() {
		Map<String, String> csvRecordMap = new LinkedHashMap<String, String>();

		try (Reader in = new FileReader(new File(csvConfig.getFileLocation()))) {
			CSVFormat format = getCsvFormat();
			Iterable<CSVRecord> csvRecords = format.parse(in);

			forEachWithIndex(csvRecords, (csvRecord) -> {
				csvRecordMap.put(CsvHeaders.first_name.name(), csvRecord.get(CsvHeaders.first_name.name()));
				csvRecordMap.put(CsvHeaders.last_name.name(), csvRecord.get(CsvHeaders.last_name.name()));
				csvRecordMap.put(CsvHeaders.email.name(), csvRecord.get(CsvHeaders.email.name()));
				csvRecordMap.put(CsvHeaders.gender.name(), csvRecord.get(CsvHeaders.gender.name()));
				csvRecordMap.put(CsvHeaders.city.name(), csvRecord.get(CsvHeaders.city.name()));

			});

		} catch (Exception e) {
			logger.error(e, () -> {
				return String.format("Not able to read the CSV %s from location %s", csvConfig.getFileName(),
						csvConfig.getFileLocation());
			});
			return Collections.emptyMap();
		}
		return Collections.unmodifiableMap(csvRecordMap);
	}

	private void forEachWithIndex(Iterable<CSVRecord> csvRecords, Consumer<CSVRecord> consumer) {
		int i = 1;
		for (CSVRecord record : csvRecords) {
			if (i >= csvConfig.getIndex()) {
				consumer.accept(record);
				return;
			}
			i++;
		}
	}

}
