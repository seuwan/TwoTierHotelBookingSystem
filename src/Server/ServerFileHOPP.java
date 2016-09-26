package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import Constants.*;

public class ServerFileHOPP {

	protected Socket serverSocket = null;
	protected BufferedReader reader;
	protected PrintStream writer;
	protected InputStream inStream = null;
	protected OutputStream outStream = null;

	public ServerFileHOPP() {
	}

	public String book(String path, String id, String line) {
		FileWriter lineWriter = null;
		try {
			lineWriter = new FileWriter(Constants.Files.RECORDINFO, true);
			lineWriter.write(line);
			lineWriter.write(Constants.CR_LF);
			lineWriter.flush();
			lineWriter.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		String[] strings = new String[100];
		int idInt = Integer.parseInt(id);
		String vaString1 = null;
		String vaString2 = null;
		String vaString3 = null;
		String string4 = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path)));
			vaString1 = br.readLine();
			vaString2 = br.readLine();
			vaString3 = br.readLine();
			string4 = (vaString1 + "\t" + vaString2 + "\t" + vaString3);
			strings = string4.split("\t");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(path);
			fWriter.write("");
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int k = 0; k < strings.length; k++) {
			if ("vacancy".equals(strings[k])) {
				if ("1".equals(strings[k + idInt])) {
					strings[k + idInt] = "1";
				}
			}
			try {
				fWriter = new FileWriter(path, true);
				fWriter.write(strings[k]);
				if ((k + 1) % 6 == 0) {
					fWriter.write(Constants.CR_LF);
				} else {
					fWriter.write('\t');
				}
				fWriter.flush();
				fWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String str = "Information has been stored.";
		return str;
	}

	public String getCity() throws IOException {
		File file = new File(Constants.Files.FILECITY);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String str = null;
		str = reader.readLine();
		return str;
	}

	public String getHotel() throws IOException {
		File file = new File(Constants.Files.FILEHOTEL);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String str = null;
		str = reader.readLine();
		return str;
	}

	public String getLocation(String i) {
		String path = null;
		if ("1".equalsIgnoreCase(i)) {
			path = Constants.Files.BEIJING_SEVENDAYS;
		} else if ("2".equalsIgnoreCase(i)) {
			path = Constants.Files.BEIJING_JINGJIANGSTAR;
		} else if ("3".equalsIgnoreCase(i)) {
			path = Constants.Files.SHANGHAI_SEVENDAYS;
		} else if ("4".equalsIgnoreCase(i)) {
			path = Constants.Files.SHANGHAI_JINGJIANGSTAR;
		}
		return path;
	}

	public String compareRate() throws IOException {
		File file = new File(Constants.Files.RATE);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String str = null;
		str = reader.readLine();
		return str;
	}

	public ArrayList<String> recordInfo() {
		ArrayList<String> tempString = new ArrayList<String>();
		File file = new File(Constants.Files.RECORDINFO);
		recordInfo(tempString, file);
		return tempString;
	}

	private ArrayList<String> recordInfo(ArrayList<String> tempString, File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String str = null;
			while ((str = reader.readLine()) != null) {
				tempString.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tempString;
	}

	public ArrayList<String> cityHotelInfo(String path) throws Exception {
		ArrayList<String> tempString = new ArrayList<String>();
		File file = new File(path);
		cityHotelInfo(tempString, file);
		return tempString;
	}

	private ArrayList<String> cityHotelInfo(ArrayList<String> tempString,
			File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String str = null;
			while ((str = reader.readLine()) != null) {
				tempString.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tempString;
	}

	public void quit() {
		try {
			writer.print(Constants.QUIT + Constants.CR_LF);
			reader.close();
			writer.close();
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
