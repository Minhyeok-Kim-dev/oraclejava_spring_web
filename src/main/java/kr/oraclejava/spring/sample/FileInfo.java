package kr.oraclejava.spring.sample;

public class FileInfo {
	private String fileName;
	private String fileType;
	private long size;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "FileInfo [fileName=" + fileName + ", fileType=" + fileType + ", size=" + size + "]";
	}
	
	
}
