package au.com.mebank.transaction.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;

import org.junit.Test;

public class FileUtilTest {

	@Test(expected = RuntimeException.class)
	public void shouldThrowFileNotFoundExceptionWhenWrongFileName() {
		BufferedReader bufferedReader = FileUtil.getFileReader("test_input_basic.csv");
		assertNull(bufferedReader);
	}
	@Test
	public void shouldGetFileReader() {
		BufferedReader bufferedReader = FileUtil.getFileReader("src/test/resources/test_input_basic.csv");
		assertNotNull(bufferedReader);
	}

}
