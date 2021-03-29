package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileController {

	public static ArrayList<String[]> read(String filePath, String regEx) throws Exception {
		ArrayList<String[]> data = new ArrayList<String[]>();

		FileReader frIn = new FileReader(filePath);
		BufferedReader brIn = new BufferedReader(frIn);

		String line;
		while ((line = brIn.readLine()) != null) {
			data.add(line.split(regEx));
		}

		brIn.close();
		frIn.close();

		return data;
	}

	public static void write(String filePath, ArrayList<String> data) throws Exception {
		FileWriter fwOut = new FileWriter(filePath);
		BufferedWriter bwOut = new BufferedWriter(fwOut);
		for(String line : data) {
			bwOut.write(line);
		}
		bwOut.close();
		fwOut.close();
		System.out.println("Successfully Save!");
		
	}

}
