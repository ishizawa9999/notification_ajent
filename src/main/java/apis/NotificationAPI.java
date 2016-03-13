package apis;

import java.nio.charset.StandardCharsets;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

import functions.AllInOne;
import twitter4j.TwitterException;
import utils.Twitter4JUtil;

@MountPath("notify")
public class NotificationAPI extends WebPage{

	private static final long serialVersionUID = -1698230246355292321L;

	final String returningFormat = "{\"result\": \"%s\", \"message\": \"%s\"}";

	//メッセージ
		final String successMessage = "successfully notified.";
		final String unsatisfiedParameterMessage = "No '%s' in received names of page parameter.";
		final String failInitTwitterClientMessage = "failed initializing twitter client.";
		
		//必要なパラメター
		final String necessaryPageParameter = "name";
	
	public NotificationAPI(){

		String returning = excute();		
		
		RequestCycle.get().scheduleRequestHandlerAfterCurrent(
				new TextRequestHandler("application/json",
				StandardCharsets.UTF_8.toString(),
				returning));
	}
	
	//実際の処理
		private String excute(){
			//APIから呼び出し元に返すテキスト
			String returning = "";
			
			//受け取ったページパラメターを取得
			IRequestParameters pageParameters = getRequest().getRequestParameters();
			
			//処理に必要なパラメターがあるか確認
			if(pageParameters.getParameterNames().contains(necessaryPageParameter) == false) 
				return returning = String.format(returningFormat, "false",
						String.format(unsatisfiedParameterMessage,necessaryPageParameter));
			
			//通知したいユーザ名
			StringValue name = pageParameters.getParameterValue(necessaryPageParameter);
			
			//TODO nameの状況をCSVから取得する
			boolean condition = new Read().readCsv(name.toString());
			
			//状況を通知
			String tweetMsg = "";
			
			if(condition){
				tweetMsg = name + " 忘れ物はありません.安心して下さい：）";
			}else{
				tweetMsg = name + " お弁当を忘れてますよ！";
			}

			
//			// メッセージをツイートする
//			Twitter4JUtil twitter =new Twitter4JUtil();
//			try {
//				twitter.initialize();
//				twitter.postTweet(tweetMsg);
//				returning = String.format(returningFormat, "true",successMessage);
//			} catch (TwitterException e) {
//				//初期化失敗時
//				e.printStackTrace();
//				return returning = String.format(returningFormat, "false",failInitTwitterClientMessage);
//			}	
			
			returning = String.format(returningFormat, "true",tweetMsg);
		
			return returning;
		}
}
		