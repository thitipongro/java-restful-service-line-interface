package ws.integation.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallWSUtils {	
	private static final Logger logger = LoggerFactory.getLogger(CallWSUtils.class);
	
	public String callCus20(String request) throws Exception {
		URL url = new URL(PropertiesConfig.getInstance().getSearchCustomer());
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Accept-Charset", "UTF-8");
		httpConn.setDoOutput(true);
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		return callWS(httpConn, request);
	}

	private String callWS(HttpURLConnection httpConn, String request) throws Exception {
		OutputStream os = null;
		BufferedReader br = null;
		String tmp = "";
		String output = "";
		try {
			os = httpConn.getOutputStream();
			os.write(request.toString().getBytes());
			os.flush();
			if (httpConn.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader((httpConn.getInputStream()), "UTF8"));
				while ((tmp = br.readLine()) != null) {
					output += tmp;
				}
				logger.info("callToken access " + output);
			} else {
				br = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
				while ((tmp = br.readLine()) != null) {
					output += tmp;
				}
				logger.info("check http 1  " + httpConn.getResponseCode());
				throw new Exception("Failed : HTTP error code : " + httpConn.getResponseCode() + "\n, Error : " + output);
			}
			return output;
		} catch (Exception e) {
			throw e;
		} finally {
			if (os != null)
				os.close();
			if (br != null)
				br.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
	}
}
