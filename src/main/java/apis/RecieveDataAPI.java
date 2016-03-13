package apis;

import java.nio.charset.StandardCharsets;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

import functions.AllInOne;

@MountPath("senddata")
public class RecieveDataAPI extends WebPage{

	private static final long serialVersionUID = -1698230246355292321L;

	final String returningFormat = "{\"result\": \"%s\", \"message\": \"%s\"}";

	//メッセージ
		final String successMessage = "sending data successfully recieved.";
		final String unsatisfiedParameterMessage = "No '%s' in received names of page parameter.";
		final String unmatchData = "need to be double.";
		
		//必要なパラメター
		final String necessaryPageParameter = "brightness";
	
	public RecieveDataAPI(){

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
			
			//ツイートしたいメッセージを取得
			StringValue brightness = pageParameters.getParameterValue(necessaryPageParameter);
			
			try{
				boolean result = new AllInOne().judge(brightness.toDouble());
				
				//TODO CSVへの書き込み処理 username は @kyabe
				
//				if(result){
//					returning = String.format(returningFormat, "true","閾値以上");
//				}else{
//					returning = String.format(returningFormat, "true","閾値以下");
//				}
				
				returning = String.format(returningFormat, "true",successMessage);
			}catch(Exception e){
				returning = String.format(returningFormat, "false",unmatchData);
			}
			
			return returning;
		}
		
}
