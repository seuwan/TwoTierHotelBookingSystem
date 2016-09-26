package Server;

import java.io.*;
import java.util.*;

import Constants.*;

/**
 * run as: run configurations
 * input: localhost
 * @author Wendy
 *
 */
public class ServerImpl {

	protected ServerClientHOPP serverClientHOPP;
	protected ServerFileHOPP serverFileHOPP;
	protected BufferedReader reader;
	protected PrintStream writer;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Server address");
			System.exit(1);
		}
		new ServerClientHOPP();
	}

	public String recordInfo() throws Exception {
		serverFileHOPP = new ServerFileHOPP();
		ArrayList<String> temString = serverFileHOPP.recordInfo();
		StringBuilder sb = new StringBuilder();
		for (String str : temString) {
			sb.append(str).append(Constants.DELIMITER);
		}
		return sb.toString();
	}

	public String cityHotelInfo(String path) throws Exception {		
		serverFileHOPP = new ServerFileHOPP();
		ArrayList<String> temString = serverFileHOPP.cityHotelInfo(path);
		StringBuilder sb = new StringBuilder();
		for (String str : temString) {
			sb.append(str).append(Constants.DELIMITER);
		}
		return sb.toString();
	}
	
	public String book(String path,String id,String line) throws Exception {
		serverFileHOPP = new ServerFileHOPP();
		String lineString = serverFileHOPP.book(path,id,line);
		return lineString;
	}

	public String getCity() throws IOException {
		serverFileHOPP = new ServerFileHOPP();
		String line = serverFileHOPP.getCity();
		return line;
	}

	public String getHotel() throws IOException {
		serverFileHOPP = new ServerFileHOPP();
		String line = serverFileHOPP.getHotel();
		return line;
	}

	public String getLocation(String i) {
		serverFileHOPP = new ServerFileHOPP();
		String num = serverFileHOPP.getLocation(i);
		return num;
	}

	public String compareRate() throws IOException {
		serverFileHOPP = new ServerFileHOPP();
		String line = serverFileHOPP.compareRate();
		return line;
	}

}
