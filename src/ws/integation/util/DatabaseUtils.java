package ws.integation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Thitipong Roongprasert
 * @date 1 ม.ค. 2563
 * @version 1.0
 */
public final class DatabaseUtils {
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();				
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}	
	
	public static void close(Statement st, ResultSet rs) {
		close(rs);
		close(st);
	}
	
	public static void close(Connection conn, Statement st, ResultSet rs) {
		close(rs);
		close(st);
		close(conn);
	}
	
	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static int getRowCount(ResultSet set) throws SQLException
	{
		int rowCount;
		int currentRow = set.getRow();            // Get current row
	   	rowCount = set.last() ? set.getRow() : 0; // Determine number of rows
	   	if (currentRow == 0)                      // If there was no current row
	      set.beforeFirst();                     // We want next() to go to first row
	   	else                                      // If there WAS a current row
	      set.absolute(currentRow);              // Restore it
		return rowCount;
	}
	
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}	
	
	public static String getMonth(int month) {
		Locale locale = new Locale("th", "TH");
		Locale.setDefault(locale);
		System.out.println(locale.getDisplayLanguage());
		DateFormatSymbols symbol = new DateFormatSymbols(locale);
		System.out.println(symbol.getMonths()[12-1]);
		return symbol.getMonths()[month-1];
	}	

	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	public static String[] get24Month(String date_s, int previ)
	{
		String [] arry  = new String[4];
		String previousMonthYear2 = "";
		String previousMonthOnly = "";
		String previousYearOnly = "";
		Locale locale = new Locale("th", "TH");
		Locale.setDefault(locale);
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMM", Locale.getDefault());
		Date date;
		try {
			date = dt.parse(date_s);
			Calendar cal = Calendar.getInstance(locale);
			cal.setTime(date);
			cal.add(Calendar.MONTH, previ);
			previousMonthOnly = new SimpleDateFormat("MMMM", locale).format(cal.getTime());
			previousYearOnly  = new SimpleDateFormat("yyyy",locale).format(cal.getTime());
			previousMonthYear2  = new SimpleDateFormat("yyyyMM",locale).format(cal.getTime());
			System.out.println(" -==== "+previousYearOnly);
			System.out.println(" -==== "+previousMonthOnly);
			int c = Integer.parseInt(previousMonthYear2.substring(4, 6));
			int y = Integer.parseInt(previousMonthYear2.substring(0, 4))+543;
			int year = Integer.parseInt(previousYearOnly)+543;
			arry[0] = previousMonthOnly+" "+year;
			arry[1]= previousMonthYear2;
			arry[2] = String.valueOf(c);
			arry[3]= String.valueOf(y);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return arry;
	}
	
	public static String genRandomPassword() {
        int length = 4;
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numm = "0123456789";
        String ranpass = "";
        for (int i = 0; i < length; i++) {
            ranpass += characters.charAt(new Random().nextInt(characters
                    .length()));
        }
        if (ranpass.length() < 3) {
            for (int i = 0; i < (length - ranpass.length()); i++) {
                ranpass += characters.charAt(new Random().nextInt(characters
                        .length()));
            }
        }
        for (int i = 0; i < length; i++) {
            ranpass += numm.charAt(new Random().nextInt(numm.length()));
        }
        if (ranpass.length() < 6) {
            for (int i = 0; i < (length - ranpass.length()); i++) {
                ranpass += numm.charAt(new Random().nextInt(numm.length()));
            }
        }
        return ranpass;
    }
    
    public static long  createRandomInteger(int aStart, long aEnd, Random aRandom){
            if ( aStart > aEnd ) {
				throw new IllegalArgumentException("Start cannot exceed End.");
            }
            long range = aEnd - (long)aStart + 1;
            long fraction = (long)(range * aRandom.nextDouble());
            long randomNumber =  fraction + (long)aStart;   
            return randomNumber;
	}
	
    public static String getMD5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                String hashtext = number.toString(16);
                // Now we need to zero pad it if you actually want the full 32 chars.
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
	}

    public static boolean exists(String URLName){
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con =
				(HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
