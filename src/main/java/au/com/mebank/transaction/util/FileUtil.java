package au.com.mebank.transaction.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtil {

	public static BufferedReader getFileReader(String filename) {
		try {
			File inputFile = new File(filename);
			InputStream inputStream = new FileInputStream(inputFile);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			return bufferedReader;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeReader(Reader reader) {
		try {
			reader.close();
		} catch (IOException e) {
		}
	}

}
