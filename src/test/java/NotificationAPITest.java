import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import apis.BasicAPI;
import junit.framework.TestCase;
public class NotificationAPITest extends TestCase {

	public void testAPICaseTrue() {
		/* 想定値 */ 
		String 	expectValue = "{\"result\": \"true\", \"message\": \"successfully notified.\"}";
		/* 想定値 */
		String afterValue = "";
		//	APIを叩く
		try {
			afterValue = httpConectNotification.main("name=@kyabe");
		} catch (IOException e1) {
		}
		/*検証*/
		assertEquals(expectValue,afterValue);    	
	}

	public void testAPICaseFalse() {
		/* 想定値 */ 
		String 	expectValue = "{\"result\": \"false\", \"message\": \"No 'name' in received names of page parameter.\"}";
		/* 想定値 */
		String afterValue = "";
		//	APIを叩く
		try {
			afterValue = httpConectNotification.main();
		} catch (IOException e1) {
		}
		/*検証*/
		assertEquals(expectValue,afterValue);    	
	}


}