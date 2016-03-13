package apis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Write {

	public boolean writeCsv(String id, String param) {

		   try {
	            //出力先を作成する
	        	String path = new File(".").getAbsoluteFile().getParent();
	            System.out.println(path);

	            FileWriter fw = new FileWriter(path + "\\判定結果_" + id + ".csv", false);  //※１
	            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

	            //内容を指定する
	            pw.print("userId");
	            pw.print("=");
	            pw.print(id);
	            pw.print(",");
	            pw.print("boolean");
	            pw.print("=");
	            pw.print(param);

	            //ファイルに書き出す
	            pw.close();

	            //終了メッセージを画面に出力する
	            System.out.println("出力が完了しました。");

	        } catch (IOException ex) {
	            //例外時処理
	            ex.printStackTrace();
	        }

		   new Read().readCsv(id);

		   return Boolean.valueOf(param);
	}
}
