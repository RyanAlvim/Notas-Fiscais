package Ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Ftp {

	private static FTPClient ftp = new FTPClient();
	
	public Ftp(String host, String user, String senha) throws SocketException, IOException {
		ftp.connect(host);
		ftp.login(user, senha);
		ftp.enterLocalPassiveMode();
	}
	
	public boolean isConnected() {
		return ftp.isConnected();
	}
	
	public boolean joinDirectory(String dir) throws IOException {
		return ftp.changeWorkingDirectory(dir);
	}
	
	public FTPFile[] listFiles() throws IOException{
		return ftp.listFiles();
	}
	
	
	public boolean FtpMove(String from, String to) throws IOException {
		return ftp.rename(from, to);
	}
	
	public void Disconnect() throws IOException {
		ftp.disconnect();
	}
	
	public boolean downloadFile(String source, String destination) throws IOException {
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
	    FileOutputStream out = new FileOutputStream(destination);
	    return ftp.retrieveFile(source, out);
	}
	
	
	
	
}
