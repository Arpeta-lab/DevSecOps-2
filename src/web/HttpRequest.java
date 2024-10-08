package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class HttpRequest {

	private String method;
	private String url;
	private String protocol;
	private NavigableMap<String, String> headers;
	private List<String> body;

	/*
	 * simple constructor for initializes the fields
	 */
	private HttpRequest() {
		body = new ArrayList<String>();
		headers = new TreeMap<String, String>();
	}

	public static HttpRequest parseAsHttp(InputStream in) {
		try {
			HttpRequest request = new HttpRequest();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine();
			if (line == null) {
				throw new IOException("Server accepts only HTTP requests.");
			}
			String[] requestLine = line.split(" ", 3);
			if (requestLine.length != 3) {
				throw new IOException("Cannot parse request line from \"" + line + "\"");
			}
			if (!requestLine[2].startsWith("HTTP/")) {
				throw new IOException("Server accepts only HTTP requests.");
			}
			
			//TODO checks the various tokens
			request.method = requestLine[0];
			request.url = requestLine[1];
			request.protocol = requestLine[2];
			
			line = reader.readLine();
			while(line != null && !line.equals("")) {
				String[] header = line.split(": ", 2);
				if (header.length != 2)
					throw new IOException("Cannot parse header from \"" + line + "\"");
				else 
					request.headers.put(header[0], header[1]);
				line = reader.readLine();
			}
			
			while(reader.ready()) {
				line = reader.readLine();
				request.body.add(line);
			}
			
			return request;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	
	/*
	 *  getter methods
	 */
	
	public String getMethod() {
		return method;
	}
	
	public String getUrl() {
		return url;
	}
	
	// -- end getter methods ---
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = method + " " + url + " " + protocol + "\n";
		for (String key : headers.keySet()) {
			result += key + ": " + headers.get(key) + "\n";
		}
		result += "\r\n";
		for (String line : body) {
			result += line + "\n"; 
		}
		return result;
	}
	
	/*
	 * inert class for string constants
	 */
	public static class HttpMethod {
		public static final String GET = "GET";
		public static final String HEAD = "HEAD";
	}
}
