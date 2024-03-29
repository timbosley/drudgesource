package drudge.data;

import java.io.*;
import java.net.*;
import java.sql.*;

public enum UselessMessages {
NONE(null, "This is for no messages"), 
ALL(Exception.class, "This option will print out all messages"),	
//Common UselessURLExceptions here
USELESS(UselessURLException.class, "This is for all uselessurls messages (rarely used)"),
DUPLICATE(DuplicateURLException.class, "This is for all duplicate urls that are found"),
EXCLUDED(ExcludedURLException.class, "This if for all excluded urls that are found"), 
NOTHTML(InvalidURLException.class, "This is for cases when a url is not a valid html file for this program to use"),
NOTOK(NotOKURLException.class, "This is when the response is not a OK response"),
NOLINKS(NoLinksURLException.class, "This is when there are no links found on a page"),
FILETOBIG(FileToBigURLException.class, "This is when a file is too big"),
NOCONTENT(NoContentURLException.class, "This is when there is no content generated by the link"),
REDIRECTEDURL(RedirectedURLException.class, "This is when a url request gets redirected to another url"),
BADENCODE(BadEncodingURLException.class, "This is when there is a bad encoding"),
NOROBOTS(RobotsExcludedURLException.class, "This is when the website doesn't allow robots"),
EMAIL(EmailURLException.class, "This is when it finds an email link"),
IMAGE(ImageURLException.class, "This is when it finds an image link"),
//General IOExceptions
IO(IOException.class, "This indicates an IO exception has occurred"),
FILENOTFOUND(FileNotFoundException.class, "This indicates the file indicated in the url wasn't found"),
//java.net Exceptions
MALFORMEDURLEXCEPTION(MalformedURLException.class, "A malformed url was found"),
UNKNOWNHOST(UnknownHostException.class, "This indicates the specified host is unknown"), 
URISYNTAX(URISyntaxException.class, "This is used to show when an url syntax error is found"),
UNKNOWNSERVICE(UnknownServiceException.class, "Attempting to implement an unworkable service"),
SOCKETTIMEOUT(SocketTimeoutException.class, "This happens when a socket connection has timed out"),
SOCKETEXCEPTION(SocketException.class, "This happens when an error has happened when creating a socket"),
PROTOCOLEXCEPTION(ProtocolException.class, "Happens when there is an error in the protocol"),
PORTUNREACHABLE(PortUnreachableException.class, "Indicates a port can't be found"),
NOROUTETOHOST(NoRouteToHostException.class, "Indicates that server can't be reached because of some kind of firewall"),
HTTPRETRY(HttpRetryException.class, "Indicates http request needs to be retried but can't be"),
CONNECTEXCEPTION(ConnectException.class, "Indicates a connection was refused by host"),
BINDEXCEPTION(BindException.class, "Indicates an error has been made trying to connect to a local host or port"),
//SQL exceptions
SQLEXCEPTION(SQLException.class, "This is used for all SQL exceptions!"),	
//unusual exceptions
NULLPOINTER(NullPointerException.class, "This is used to indicate a null pointer value was thrown"),
ILLEGALARGUMENT(IllegalArgumentException.class, "Somewhere an illegal value was passed into a method");

/*this is a standard variable for determining which messages get printed*/
static public UselessMessages uselessmessage = UselessMessages.NONE;

/*This is for the actual class for the enum values above*/
public String hlp = "No Help at all!";
public Class<?> cls = null;

	private UselessMessages(Class<?> c) {
	cls = c;
	}

	private UselessMessages(Class<?> c, String h) {
	this(c);
	hlp = h;
	}

	public static UselessMessages get(int c) {
	UselessMessages useless = UselessMessages.NONE;
		for (UselessMessages u : values()) {
			if (u.ordinal() == c) {
			useless = u;
			break;
			}
		}
	return useless;
	}

}
