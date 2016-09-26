package Client;

import java.io.*;
import Constants.*;

/**
 * This program is a 2-tier system which achieve the multiple clients talking to
 * a single server. It is implemented satisfactorily, provides the required
 * functionality, is robust, exhibits the application of sound design principles
 * and code conventions.
 * 
 * tips: run server in advance
 * @author Wendy
 *
 */
public class ClientUI {

	protected BufferedReader console;
	protected ClientHOPP clientHOPP;

	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: Client address");
			System.exit(1);
		}
		ClientUI ui = new ClientUI(args[0]);
		ui.loop();
	}

	public ClientUI(String args) {

		clientHOPP = null;
		try {
			clientHOPP = new ClientHOPP();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public void loop() throws IOException {
		String line = null;
		while (true) {
			try {
				System.out.print(Constants.CR_LF + "Entre Request: 1."
						+ Constants.BOOK + "  2. " + Constants.RECORDINFO
						+ "  3." + Constants.COMPARE + "  4." + Constants.QUIT+Constants.CR_LF);
				line = console.readLine();
			} catch (IOException e) {
				clientHOPP.quit();
				e.printStackTrace();
				System.exit(1);
			}
			if (line.equalsIgnoreCase("1")) {
				book();
			} else if (line.equalsIgnoreCase("2")) {
				recordInfo();
			} else if (line.equalsIgnoreCase("3")) {
				compare();
			} else if (line.equalsIgnoreCase("4")) {
				clientHOPP.quit();
				System.exit(0);
			} else {
				System.out.println(Constants.INPUTAGAIN+Constants.CR_LF);
			}
		}
	}

	public void book() {
		clientHOPP.city();
		clientHOPP.getCity();
	}

	public void recordInfo() {
		System.out.print("The order information are as followed: ");
		clientHOPP.recordInfo();
	}

	private void compare() throws IOException {
		clientHOPP.compareRate();
	}
}
