package apis;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

import twitter4j.TwitterException;
import utils.Twitter4JUtil;

/**
 * 受け取ったパラメーターをTweetするAPI
 * @author m-okabe
 *
 */

@MountPath("tweet")
public class TweetTestAPI extends WebPage{

	private static final long serialVersionUID = 4987394094263706460L;

	//APIから呼び出し元に返すテキストのフォーマット
	final String returningFormat = "{\"result\": \"%s\", \"message\": \"%s\"}";
	
	//メッセージ
	final String successMessage = "Your message successfully Tweet via @notify_agent :)";
	final String unsatisfiedParameterMessage = "No '%s' in received names of page parameter.";
	final String failInitTwitterClientMessage = "failed initializing twitter client.";
	
	//必要なパラメター
	final String necessaryPageParameter = "tweet";
	
	public TweetTestAPI(){
		
		String returning = excute();

		//呼び出し元に実行結果をレスポンス
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
		
		//ツイートしたいメッセージを取得
		StringValue msg = pageParameters.getParameterValue(necessaryPageParameter);
		
		// メッセージをツイートする
		Twitter4JUtil twitter =new Twitter4JUtil();
		try {
			twitter.initialize();
			twitter.postTweet(msg.toString());
			returning = String.format(returningFormat, "true",successMessage);
		} catch (TwitterException e) {
			//初期化失敗時
			e.printStackTrace();
			return returning = String.format(returningFormat, "false",failInitTwitterClientMessage);
		}	
		
		return returning;
	}
}
