package drudge.data;

import java.util.function.*;
import java.util.*;
import java.net.*;
import java.io.*;
import drudge.*;
import drudge.page.*;
import drudge.global.*;


/*This interface has the methods that this program will use*/
public interface Data extends Iterable, RandomAccess {

	/*These are important methods because they interface with the rest of the program*/
	public Object remove(int cycle);
	public void put(Object page) throws DuplicateURLException, IllegalArgumentException;
	public Object get(int cycle);
	public void setSource(String s);
	public String source();
	public void setLevel(int l);
	public int level();
	public int firstIndexNumber();
	public void checkError();
	public void begin() throws Exception;
	public void end() throws Exception;
	public void truncate();
	public int size();
	public boolean contains(Object link);
	public Iterator iterator();
	public boolean add(Object p);//this is different than put(Object) because it doesn't check for duplicates and other errors.  This should only be used for data.begin methods!

	default public void put(Data links) {
		for (Object link : links) {
			try {
			put(link);
			}
			catch (DuplicateURLException Du) {
			Du.printRow();
			}
			catch (IllegalArgumentException I) {
			Print.row(I, link);
			}
		}
	}

}
