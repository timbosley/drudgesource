package drudge.page;

import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import java.lang.ref.*;

import drudge.*;
import drudge.spider.*;
import drudge.data.*;
import drudge.global.DataObjects;

/*this is the skeletal class of all page objects*/
final public class Page implements Serializable {
static public Proxy proxyserver = Proxy.NO_PROXY;
final static private int pagesize = 35_000;
//private variables for info about page itself which are set to the default values
//The default value for MOST of these should be null
final private Source content = new Source(pagesize);
final private Header header = new Header();
private URL url = null;  
private URLConnection connection = null;
final private Data<Page> dlist = new DataList<Page>();
final private Data<URL> elist = new DataListEmail<URL>();
final private Data<String> klist = new DataList<String>();
private boolean connected = false;

	public Page(URL u) {
	u.toString();//this checks if u is null and if it is it will throw a nullpointer exception
	this.url = u;
	}
	
	public Page(URL p, String l) throws MalformedURLException {
	this.url = new URL(p, l);//this throws malformedurlexception
	}

	public Page(String u) throws MalformedURLException {
	this.url = new URL(u);//this will throw malformedurlexception
	}
	
	public Page(String u, String l) throws MalformedURLException {
	URL purl = new URL(u);//this will throw malformedurlexception
	this.url = new URL(purl, l);//this will throw malformedurlexception
	}
	
	public Page(Page op, String l) throws MalformedURLException {
	URL oldurl = op.getURL();
	this.url = new URL(oldurl, l);//this will throw malformedurlexception
	}
	
	public boolean equals(Object obj) {
	boolean isequal = false;
		if (obj instanceof Page) {
		String urlstring = this.toString();
		String pstring = obj.toString();
		int e = urlstring.compareTo(pstring);
			if (e == 0) {
			isequal = true;
			}
		}
	return isequal;
	}
	
	/*Most override to avoid warning*/
	public int hashCode() {
	return toString().hashCode();
	}
	
	public Data<Page> getLinks() {
		//I just liked anonymous classes/methods so I decided to keep this
		//It also refers to the this object url variable for its absolute path
		if (content.wasFilled() && content.size() > 0) {
			P.LinkAction<String> action = new P.LinkAction<String>() {
				
				public void act(String link) throws URISyntaxException, UselessURLException, MalformedURLException {
				Page p = new Page(url, link);
				//WeakReference<Page> w = new WeakReference<Page>(p);
				//SoftReference<Page> s = new SoftReference<Page>(p);
				dlist.put(p);
				}
			};
		P.GetLinkAction.get(content, action);
		}
	Debug.time("Getting Links");
	return dlist;
	}
	
	public Data<URL> getEmails() {
	P.getEmails(content, elist);
	Debug.time("Getting Emails");
	return elist;
	}

	public int getEmailCount() {
	return elist.size();
	}

	public int getLinkCount() {
	return dlist.size();
	}

	public String getTitle() {
	Debug.println(content.size());
	String title = P.getTitle(content);
	Debug.time("Getting Title");
	return title;
	}
	
	public Data<String> getKeywords() {
	P.getKeywords(content, klist);
	Debug.time("Getting Keywords");
	return klist;
	}

	public int getKeywordCount() {
	return klist.size();
	}

	//this will essentiallly handle all the prechecking needed before it gets content
	public boolean isUseless() throws RedirectedURLException, InvalidURLException, 
	       NotOKURLException, NoContentURLException, BadEncodingURLException, IOException {
	Debug.time("Checking Uselessness");
	connection = P.getConnection(url, proxyserver);//this throws IOException
	//P.checkHeaders(connection);
	//P.checkHeaders(header, toString());
	P.checkHeaders(this);
	return false; //if it made it this far then this is the default answer
	}

	public boolean isValid() throws URISyntaxException, InvalidURLException {
	P.checkHtmlFile(url);//this will throw InvalidURLException when it is not an html file and URISyntaxException
	return true;
	}

	public Source getSource() throws IOException {
	final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), content.length());//throws ioexcpetion above 
	connected = true;
		for (int i = 0; i < content.length(); i++) {
			try {
			reader.mark(0);
			int n =  reader.read();//this throws ioexception
				if (n == -1) {
				break;
				}
			content.append((char)n, i);
			}
			catch (IOException I) {
			//Debug.here(I);
			reader.reset();
			}
		}
	reader.close();//closes the reader and throws ioexception
	return content;
	}
	
	public URL getURL() {
	return url;
	}

	public URL getRobotURL() {
		try {
		URL roboturl = new URL(url, "/robots.txt");
		return roboturl;
		}
		catch (IOException I) {
		D.error(I);
		return null;
		}			
	}
	
	public URLConnection openConnection() throws IOException {
	connection = P.getConnection(url, proxyserver);
	return connection;
	}

	public Header header() {
	return header;
	}
     	
	public Source source() {
	return content;
	}

	public String toString() {
	return url.toString();
	}

	public boolean didConnect() {
	return connected;
	}
	
	public boolean sameHost(Page p) {
	return P.sameHost(this, p);
	}
	
	public boolean isIncluded() throws ExcludedURLException {
	boolean included = false;
		for (Page p : DataObjects.include) {		
		included = this.sameHost(p);
			if (included) {
			break;
			}
		}
		if (included == false) {
		throw new ExcludedURLException(this.url);
		}	
	return true;
	}
	
	public boolean isExcluded() throws ExcludedURLException {
		if (DataObjects.exclude.contains(this)) {
		throw new ExcludedURLException(this.url);
		}
	return false;
	}

	public boolean isRobotAllowed() throws NoRobotsURLException {
	final URL roboturl = getRobotURL();
	Data<URL> dr = DataObjects.norobothash.get(roboturl);//this could throw a null pointer exception
		if (dr != null) {
			if (dr.contains(this.url)) {
			throw new NoRobotsURLException(this.url);
			}
		}
		else {
			try {
			URLConnection c = roboturl.openConnection(proxyserver);
			dr = new DataList<URL>();
			P.readRobotFile(c, dr);
			DataObjects.norobothash.put(roboturl, dr);
				if (dr.contains(this.url)) {
				throw new NoRobotsURLException(this.url);
				}
			}
			catch (IOException I) {
			D.error(I);
			}
		}	
	return true;
	}

	//inner classes - Header - Source 
	final public class Header implements Serializable {
	
		public String getRedirectLocation() {
		final String loc = "Location";
		return get(loc);
		}
		
		public String get(String key) {
		int k = 1;//this has to start at one so it will work.  This won't get null key or OK message status since that is 0
		String field = null;
			for (field = connection.getHeaderField(k); field != null; field = connection.getHeaderField(k)) {
			/*I had to make this because jave api is case sensitive*/
			String ke = connection.getHeaderFieldKey(k);
				if (ke.equalsIgnoreCase(key)) {
				break;
				}
			k++;
			} 
		return field;
		}

		public String getResponse() {
		String r = connection.getHeaderField(null);
		return r;
		}
			
		public int getResponseCode() {
		int code = -1;
			try {
			HttpURLConnection h = (HttpURLConnection)connection;
			code = h.getResponseCode();
			}
			catch (ClassCastException C) {
			D.error(C);
			}
			catch (IOException I) {
			D.error(I);
			}
		return code;
		}

		public int getContentLength() {
		int l = -1;
		final String length = "Content-Length";
		final String r = get(length);
			if (r != null) {
				try {
				Integer I = Integer.valueOf(r);
				l = I.intValue();
				}
				catch (NumberFormatException N) {
				D.error(N);
				}
			}
		return l;
		}

		public String getContentEncoding() {
		final String encoding = "Content-Encoding";
		String r = get(encoding);
		return r;
		}

		public String getContentType() {
		final String type = "Content-Type";
		String r = get(type);
		return r;
		}

	}//end of headers class 
	
	final public class Source implements CharSequence, Serializable { 
	/*This is really an attempt to make my own string object to increase efficiency of string, stringbuffer classes */
	private int size = 0;
	private char[] allc = null;	
	private boolean filled = false;

		private Source(int s) {
		allc = new char[s];	
		}
		
		private Source(char[] c) {
		allc = c;
		}

		void append(char c, int i) {
		allc[i] = c;
		size++;
		filled = true;
		}
		
		public boolean isComplete() {
		boolean complete = false;
			if (size < allc.length) {
			complete = false;
			}
			else {
			complete = true;
			}
		return complete;
		}
		
		public boolean wasFilled() {
		return filled;
		}
		
		public char charAt(int i) {
		return allc[i];
		}
		
		public int size() {
		return size;
		}

		public int length() {
		return allc.length;
		}

		public String toString() {
		return new String(allc);
		}

		public CharSequence subSequence(final int b, final int e) {
		int l = e - b;
		Source s = new Source(l);
			for (int i = 0; i < l; i++) {
			s.append(allc[b + i], i);
			}
		return s;
		}
	}//end of Source class
}//end of page class
