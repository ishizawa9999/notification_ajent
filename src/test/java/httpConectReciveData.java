import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;

public class httpConectReciveData {
	public static String main(String... args) throws IOException {
		String url = "http://localhost:8080/senddata";
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");

		if(args.length != 0){

		String parameterString = new String(args[0]);
		PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
		printWriter.print(parameterString);
		printWriter.close();
		}

			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder buf = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}

			System.out.println(buf.toString());
			reader.close();
			return buf.toString();
		}

}