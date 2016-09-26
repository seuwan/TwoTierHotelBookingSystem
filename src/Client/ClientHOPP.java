package Client;

import java.io.*;
import java.net.*;

import Constants.*;

public class ClientHOPP {

	protected Socket clientSocket;
	protected BufferedReader reader;
	protected PrintStream writer;
	protected BufferedReader console;

	public ClientHOPP() throws Exception {

		clientSocket = null;
		InputStream inStream = null;
		OutputStream outStream = null;

		clientSocket = new Socket(InetAddress.getLocalHost(), Constants.PORT);
		System.out.println("Client is running.");
		inStream = clientSocket.getInputStream();
		outStream = clientSocket.getOutputStream();

		reader = new BufferedReader(new InputStreamReader(inStream));
		writer = new PrintStream(outStream);
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public void city() {
		writer.print(Constants.CITY + Constants.CR_LF);
		System.out.print("CityList :");
		try {
			String line = reader.readLine();
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCity() {
		while (true) {
			System.out.print("Please choose CITY : ");
			String line = null;
			String line0 = null;
			try {
				line0 = console.readLine();
				if (line0.equalsIgnoreCase("1")) {
					line = Constants.BEIJING;
				} else if (line0.equalsIgnoreCase("2")) {
					line = Constants.SHANGHAI;
				} else {
					line = Constants.ERROR;
				}
				writer.print(line + Constants.CR_LF);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String line2 = null;
			try {
				line2 = reader.readLine();
				if (line2.equalsIgnoreCase(Constants.ERROR)) {
					System.out.println(Constants.ERROR + Constants.INPUTAGAIN);
					continue;
				} else {
					System.out.println("HotelList : " + line2);
					getHotel(line);
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}

	public void getHotel(String line) {
		while (true) {
			System.out.print("Please choose HOTEL : ");
			String lineOne = null;
			String lineTwo = null;
			try {
				lineOne = console.readLine();
				if (lineOne.equalsIgnoreCase("1")
						|| lineOne.equalsIgnoreCase("2")) {
					if (lineOne.equalsIgnoreCase("1")) {
						lineTwo = Constants.SEVENDAYS;
					} else {
						lineTwo = Constants.JJSTAR;
					}
					String line3 = line + lineTwo;
					System.out.println("You have choose " + line + " "
							+ lineTwo + " Hotel.");
					getLocation(line3);
				} else {
					System.out.println(Constants.ERROR + Constants.INPUTAGAIN);
					continue;
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getLocation(String line3) {
		writer.print(line3 + Constants.CR_LF);
		try {
			String path = reader.readLine();
			cityHotelInfo(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recordInfo() {
		writer.print(Constants.RECORDINFO + Constants.CR_LF);
		System.out.println("The record information details :");
		try {
			String line = reader.readLine();
			String[] recordInfo = line.split(Constants.DELIMITER);
			if (recordInfo.length > 0) {
				System.out.println("Name inDay outDay creCard Phone:");
				for (String str : recordInfo) {
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cityHotelInfo(String path) throws IOException {
		writer.print(Constants.CITYHOTELINFO + Constants.CR_LF);
		writer.print(path + Constants.CR_LF);
		System.out.println("There is the Hotel Details :");
		try {
			String line = reader.readLine();
			String[] info = line.split(Constants.DELIMITER);
			if (info.length > 1) {
				for (String str : info) {
					System.out.println(String.valueOf(str) + " ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		book(path);
	}

	public void selectID() throws IOException {
		boolean flag = true;
		String string = null;
		while (flag) {
			System.out.println("Select the room id: form: 1 2 3 4 5");
			System.out.println("Not choose the room id that vacancy is 1.");
			console = new BufferedReader(new InputStreamReader(System.in));
			string = console.readLine();
			if ("1".equals(string) || "2".equals(string) || "3".equals(string)
					|| "4".equals(string) || "5".equals(string)) {
				flag = false;
			} else {
				System.out.println(Constants.ERROR + Constants.INPUTAGAIN);
			}
		}
		writer.print(string + Constants.CR_LF);
	}

	public void book(String path) throws IOException {
		writer.print(Constants.BOOK + Constants.CR_LF);
		writer.print(path + Constants.CR_LF);
		selectID();
		try {
			System.out
					.println("Input the information: Name inDay outDay CreCard Phone");
			System.out
					.print("Follow the form: name xx-xx xx-xx [0-9]{6} [0-9]{11}: ");
			String line1 = console.readLine();
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append(line1);
			writer.print(sbBuilder.toString() + Constants.CR_LF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String line = reader.readLine();
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void compareRate() throws IOException {
		writer.print(Constants.COMPARE + Constants.CR_LF);
		String line = reader.readLine();
		String[] lineStrings = line.split("\040");
		for (int i = 0; i < lineStrings.length; i++) {
			System.out.println(lineStrings[i]);
			if ((i + 1) % 2 == 0) {
				System.out.print('\n');
			} else {
				System.out.print('\t');
			}
		}
	}

	public void quit() {
		try {
			writer.print(Constants.QUIT + Constants.CR_LF);
			reader.close();
			writer.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
