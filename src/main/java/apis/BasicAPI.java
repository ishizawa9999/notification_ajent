package apis;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * 受け取ったパラメーターを表示するだけのAPI
 * @author m-okabe
 *
 */

@MountPath("baseapi")
public class BasicAPI extends WebPage{

	private static final long serialVersionUID = 4987394094263706460L;

	final String returningFormat = "{\"result\": \"%s\", \"message\": \"%s\"}";

	public BasicAPI(){
		IRequestParameters pageParameters = getRequest().getRequestParameters();

		Set<String> names = pageParameters.getParameterNames();
		for(String name : names){
			System.out.println("name : " + name + " value: " + pageParameters.getParameterValue(name));
		}

		String returning = "";
		if(names.isEmpty()){
			returning = String.format(returningFormat, "false","you might miss attaching parameter ;(");
		}else{
			returning = String.format(returningFormat, "success","recieved value. thanks sending ;)");
		}

		RequestCycle.get().scheduleRequestHandlerAfterCurrent(
				new TextRequestHandler("application/json",
				StandardCharsets.UTF_8.toString(),
				returning));
	}

}
