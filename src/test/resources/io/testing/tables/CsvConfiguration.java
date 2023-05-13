package io.testing.tables.csvtable;

import java.util.Objects;

public class CsvConfiguration {

	private final String fileName;

	public String getFileName() {
		return fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public int getIndex() {
		return index;
	}

	private final String fileLocation;
	private int index = -1;

	private CsvConfiguration(String fileName, String fileLocation, int index) {
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.index = index;
	}

	public static class CsvConfigurationBuilder {
		private String fileName;
		private String fileLocation;
		private int index = -1;

		public CsvConfigurationBuilder setFileName(String fileName) {
			this.fileName = fileName;
			return this;

		}

		public CsvConfigurationBuilder setFileLocation(String fileLocation) {
			this.fileLocation = fileLocation;
			return this;

		}

		public CsvConfigurationBuilder setIndex(int index) {
			this.index = index;
			return this;

		}

		public CsvConfiguration build() {
			Objects.requireNonNull(fileName);
			Objects.requireNonNull(fileLocation);
			return new CsvConfiguration(fileName, fileLocation, index);
		}

	}

}
