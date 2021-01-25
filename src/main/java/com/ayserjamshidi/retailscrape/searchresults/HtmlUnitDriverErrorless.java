package com.ayserjamshidi.retailscrape.searchresults;

/*import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlUnitDriverErrorless extends HtmlUnitDriver {
	public HtmlUnitDriverErrorless(boolean enableJavascript) {
		super(BrowserVersion.FIREFOX_31, enableJavascript);
	}

	@Override
	protected WebClient modifyWebClient(WebClient client) {
		WebClient modifiedWebClient = super.modifyWebClient(client);

		// Disable complaining about JS errors
		modifiedWebClient.getOptions().setThrowExceptionOnScriptError(false);
		modifiedWebClient.getOptions().setPrintContentOnFailingStatusCode(true);

		modifiedWebClient.getOptions().setCssEnabled(false);

		modifiedWebClient.getOptions().setPopupBlockerEnabled(true);
		modifiedWebClient.getOptions().setGeolocationEnabled(false);
		modifiedWebClient.getOptions().setDoNotTrackEnabled(true);

		modifiedWebClient.getOptions().setActiveXNative(false);
		modifiedWebClient.getOptions().setAppletEnabled(false);

		Logger.getLogger("org.apache.http").setLevel(Level.OFF);
		Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);

		return modifiedWebClient;
	}
}*/