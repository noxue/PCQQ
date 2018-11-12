import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.json.*;
import java.util.*;
import java.net.*;
import java.io.*;



public class HttpWebClient
{
	private CookieManager manager = new CookieManager();
	private CookieStore cool = null;
	public JSONObject Cookies = new JSONObject();
	public JSONObject Headers = new JSONObject();
	private HttpURLConnection connection = null;

	public HttpWebClient()
	{
		CookieHandler.setDefault(manager);

	}

	public String UploadData(String url,String data){
		try
		{
			Iterator iterator = Headers.keys();
			String cookie_inside = "";

			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("Post");  


			while(iterator.hasNext()){
				String key = (String) iterator.next();
				try
				{
					Object value = Headers.get(key);
					connection.setRequestProperty(key,value.toString());
				}
				catch (JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}
			Iterator iterator2 = Cookies.keys();

			while(iterator2.hasNext()){
				String key = (String) iterator2.next();
				try
				{
					Object value = Cookies.get(key);
					if (cookie_inside == null){
						cookie_inside = key+"="+value;
					}else{
						cookie_inside = cookie_inside+" ;"+ key+"="+value;

					}
				}
				catch (JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}
			connection.setRequestProperty("cookie",cookie_inside);
		    CookieHandler.setDefault(manager);
			connection.connect();// 连接会话  
			PrintWriter writer = new PrintWriter(connection.getOutputStream());
			writer.print(data);                                    
			writer.flush();
			CookieStore cookieJar = manager.getCookieStore();
			List<HttpCookie> cookies = cookieJar.getCookies();
			for (HttpCookie cookie : cookies) {
				try
				{
					Cookies.put(cookie.getName(),cookie.getValue());

				}
				catch (JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}

			BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null)
			{// 循环读取流  
				sb.append(line);  
			}  
			br.close();// 关闭流
			connection.disconnect();// 断开连接  
			return sb.toString();

		}
		catch (IOException e)
		{
			return null;
		}  
	}

	public String DownloadData(String url){
		try
		{
			Iterator iterator = this.Headers.keys();
			String cookie_inside = "";

			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");  


			while(iterator.hasNext()){
				String key = (String) iterator.next();
				try
				{
					Object value = Headers.get(key);
					connection.setRequestProperty(key,value.toString());
				}
				catch (JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}

			Iterator iterator2 = Cookies.keys();

			while(iterator2.hasNext()){
				String key = (String) iterator2.next();
				try
				{
					Object value = Cookies.getString(key);
					if (cookie_inside == null){
						cookie_inside = key+"="+value;
					}else{
						cookie_inside = cookie_inside+" ;"+ key+"="+value;

					}
				}
				catch (JSONException e)
				{
					System.out.println("ggg"+e.getMessage());
				}
			}
			connection.setRequestProperty("cookie",cookie_inside);
		    CookieHandler.setDefault(manager);
			connection.connect();// 连接会话  


			BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null)
			{// 循环读取流  
				sb.append(line);  
			}  
			br.close();// 关闭流
			CookieStore cookieJar = manager.getCookieStore();
			List<HttpCookie> cookies = cookieJar.getCookies();
			for (HttpCookie cookie : cookies) {
				try
				{
					Cookies.put(cookie.getName(),cookie.getValue());

				}
				catch (JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}
			connection.disconnect();// 断开连接  

			return sb.toString();

		}
		catch (IOException e)
		{
			return null;
		}  
	}

	public int Timeout ;




}

