package Server;

import java.io.*;
import java.net.*;

import Constants.*;

public class ServerClientHOPP {
	protected BufferedReader reader;
	protected PrintStream writer;
	String server = null;
	ServerImpl serverImp;
	ServerFileHOPP serverFileHOPP;

	public ServerClientHOPP() {
		ServerSocket serverSocket = null;
		try {
			
				serverSocket = new ServerSocket(
						Constants.PORT);
				serverFileHOPP = new ServerFileHOPP();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Server is running.");

		while (true) {
			Socket incoming = null;
			try {
				incoming = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
				continue;
			}
			new ServerHandler(incoming, serverFileHOPP).start();
		}
	}
}

class ServerHandler extends Thread {
	BufferedReader reader;
	PrintStream writer;
	Socket incoming;
	BufferedReader console;
	ServerFileHOPP serverHOPP;
	ServerImpl serverImp;

	ServerHandler(Socket incoming, ServerFileHOPP serverHOPP) {
		this.incoming = incoming;
		this.serverHOPP = serverHOPP;
	}

	public void run() {
		try {
			writer = new PrintStream(incoming.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(
					incoming.getInputStream()));
			serverImp = new ServerImpl();

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					continue;
				}
				System.out.println(Constants.CR_LF + "Entre Request: " + line);
				if (line.equalsIgnoreCase(Constants.CITYHOTELINFO)) {					
					cityHotelInfo();				
				} else if (line.equalsIgnoreCase(Constants.RECORDINFO)) {
					recordInfo();
				} else if (line.equalsIgnoreCase(Constants.BOOK)) {
					book();
				} else if (line.equalsIgnoreCase(Constants.COMPARE)) {
					compareRate();
				} else if (line.equalsIgnoreCase(Constants.CITY)) {
					getCity();
				} else if (line.equalsIgnoreCase(Constants.BEIJING)) {
					getHotel();
				} else if (line.equalsIgnoreCase(Constants.SHANGHAI)) {
					getHotel();						
				} else if (line.equalsIgnoreCase(Constants.BEIJING+Constants.SEVENDAYS)) {
					getLocation("1");	
				} else if (line.equalsIgnoreCase(Constants.BEIJING+Constants.JJSTAR)) {
					getLocation("2");	
				} else if (line.equalsIgnoreCase(Constants.SHANGHAI+Constants.SEVENDAYS)) {
					getLocation("3");	
				} else if (line.equalsIgnoreCase(Constants.SHANGHAI+Constants.JJSTAR)) {
					getLocation("4");						
					
					
				} else {
					writer.print(Constants.ERROR + Constants.CR_LF);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void compareRate() throws IOException {
		String line = serverImp.compareRate();
		writer.print(line+ Constants.CR_LF);
	}

	private void getLocation(String i) {
		String num = serverImp.getLocation(i);
		writer.print(num+ Constants.CR_LF);		
	}

	private void getHotel() throws IOException {
		// TODO Auto-generated method stub
		String line = serverImp.getHotel();
		writer.print(line+ Constants.CR_LF);
	}

	private void getCity() throws Exception {
		// TODO Auto-generated method stub
		String line = serverImp.getCity();
		writer.print(line+ Constants.CR_LF);		
	}

	public void recordInfo() throws Exception {
		String line = serverImp.recordInfo();
		writer.print(line + Constants.CR_LF);
	}

	public void cityHotelInfo() throws Exception {
		String path = reader.readLine();
		String line = serverImp.cityHotelInfo(path);
		writer.print(line + Constants.CR_LF);
	}

	public void book() throws Exception {
		try {
			String pathString = reader.readLine();
			String idString = reader.readLine();
			String line = reader.readLine();
			String lineString = serverImp.book(pathString,idString,line);
			writer.print(lineString+Constants.CR_LF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
