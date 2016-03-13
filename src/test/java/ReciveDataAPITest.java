import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import apis.BasicAPI;
import junit.framework.TestCase;
public class ReciveDataAPITest extends TestCase {

	public void testAPICaseTrue() {
		/* 想定値 */ 
		String 	expectValue = "{\"result\": \"true\", \"message\": \"sending data successfully recieved.\"}";
		/* 想定値 */
		String afterValue = "";
		//	APIを叩く
		try {
			afterValue = httpConectReciveData.main("brightness=10");
		} catch (IOException e1) {
		}
		/*検証*/
		assertEquals(expectValue,afterValue);    	
	}

	public void testAPICaseFalse() {
		/* 想定値 */ 
		String 	expectValue = "{\"result\": \"false\", \"message\": \"No 'brightness' in received names of page parameter.\"}";
		/* 想定値 */
		String afterValue = "";
		//	APIを叩く
		try {
			afterValue = httpConectReciveData.main();
		} catch (IOException e1) {
		}
		/*検証*/
		assertEquals(expectValue,afterValue);    	
	}

}