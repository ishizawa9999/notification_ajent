package apis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Read {

	public boolean readCsv(String userId) {

		boolean result = false;
        try {
        	String path = new File(".").getAbsoluteFile().getParent();
            System.out.println(path);
            //ファイルを読み込む
            FileReader fr = new FileReader(path + "\\判定結果_" + userId + ".csv");
            BufferedReader br = new BufferedReader(fr);
            //読み込んだファイルを１行ずつ画面出力する
            String line;
            while ((line = br.readLine()) != null) {

                // 指定した文字より後ろの文字取り出し

                    int index = line.indexOf("boolean=");
                    index += "boolean=".length();
            		System.out.println("取り出し文字列=" + line.substring(index));

            		result = Boolean.valueOf(line.substring(index));
//                    new TweetTestAPI2().excute(line);
            }
            //終了処理
            br.close();
            fr.close();
        } catch (IOException ex) {
            //例外発生時処理
            ex.printStackTrace();
        }
        return result;
	}
}
