package drudge.data;

import java.io.*;
import java.net.*;
import java.util.*;
import drudge.Debug;
import drudge.page.*;
import drudge.global.*;
import drudge.spider.NaturalComparator;

/*this class is a superclass of objects that use an array as the storage device*/
/*T has to be assumed to be an object so it retains is general behavior for all types*/
/*This is the default Data object for this program when not using database object*/
public class DataList<T> extends AbstractData<T> {
private int size = 0;
private Object[] objs = new Object[10];

	public DataList() {

	}

	public DataList(String s) {
	this();
	this.source = s;
	}
	
	public DataList(String s, int i) {
	this(s);
	this.objs = new Object[i];
	}

	public void put(T page) throws DuplicateURLException, IllegalArgumentException {
		if (contains(page) == false) {
			
			if (this.size >= objs.length - 1) {
			this.objs = Arrays.copyOf(objs, objs.length * 2);
			}
			if (this.objs[size] == null) {	
			this.objs[this.size] = page;
			this.size++;//increases size by 1
			}
			else {
			throw new IllegalStateException("Size variable was not set correctly.  It is erasing previous data.");
			}	
		}
		else {
		throw new DuplicateURLException(page);
		}
	}
		
	/* Do not erase remove, get, set, or add(T) methods!!!!!!!!!!!!!!!!!!!*/
	/* Let add(int, T) throw unsupportedoperationexception */
	public T remove(final int i) {
	Debug.here();
	T toberemoved = (T)this.objs[i];
		for (int j = i; j < this.size - 1; j++) {
		this.objs[j] = this.objs[j + 1];
		}
	objs[this.size - 1] = null;
	this.size--;//this has to be last in order to make it work right
	return toberemoved;
	}
	
	public T get(int i) {	
	T entry = (T)this.objs[i];
	return entry;
	}

	public T set(final int i, final T newt) {
	T oldt = (T)this.get(i);
	objs[i] = newt;
	return oldt;
	}

	public boolean add(T p) {
	boolean added = false;
		try {
		this.put(p);
		added = true;
		}
		catch (DuplicateURLException Du) {
		D.error(Du);
		}
		catch (IllegalArgumentException I) {
		D.error(I);
		}
	return added;
	}
	
	public int size() {
	return this.size;
	}
	
	/*The default assumes that it is working with a page object*/
	public void begin() throws IOException, Exception {
	LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(source())));//this throws IOException and breaks the method if it can't read from soruce
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
		String[] ns = line.split(CountFile.sep);
			try {
			T p = (T)new Page(ns[0]);
			this.put(p);
			}
			catch (URISyntaxException U) {
			D.error(U);
			}
			catch (MalformedURLException M) {
			D.error(M);
			}
			catch (UselessURLException U) {
			D.error(U);
			}
		}
	}

	/*The default assumes you are working with a page object.  Subclasses should override*/
	public void end() throws IOException, Exception {
	final BufferedWriter link_writer = new BufferedWriter(new FileWriter(source()));
		for (T t : this) {
		Page page = (Page)t;
		D.writeEntry(page, link_writer, level());
		}
	link_writer.close();
	}
	
	public void truncate() {
	File linkfile = new File(source());
		try {
		linkfile.delete();
		linkfile.createNewFile();
		}
		catch (IOException I) {
		D.error(I);
		}
	}
	
	public boolean checkError() {
	String file = this.source();
	System.out.println("Checking " + file + " file for errors.");
	boolean duplicate = false;
	boolean linkerror = false;
	int linecount = 0;
		try { 
		LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(file)));
		int firstcolumnlength = 0;
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			linecount = reader.getLineNumber();
			String[] columns = line.split(CountFile.sep);
			/*This will see if the total number of columns for each line is the same as the previous one*/
				if (linecount == 1) {
				firstcolumnlength = columns.length;
				}
				if (columns.length != firstcolumnlength) {
				System.out.println("...There is a column length error at line " + String.valueOf(linecount));
				}
			/*this will check for decoding errors*/
			String link = columns[0];
				if (link.contains("%")) {
				System.out.println("...Possible encoding error at line " + String.valueOf(linecount));
				}
				
			/*This will check for duplicates in the data*/
				try {
				Page p = new Page(link);//this throws malformedurlexception
				p.isValid();//this throws InvalidURLException, URISyntaxException
				URL u = p.getURL();
					if (u.getAuthority() == null) {
					throw new MalformedURLException("No Host");
					}
				LineNumberReader reader2 = new LineNumberReader(new BufferedReader(new FileReader(file)));
					for (String line2 = reader2.readLine(); line2 != null; line2 = reader2.readLine()) {
					int linecount2 = reader2.getLineNumber();
						if (linecount2 < linecount) {
						String[] columns2 = line2.split(CountFile.sep);
						String link2 = columns2[0];
							if (link.equals(link2)) {
							duplicate = true;
							System.out.print("..." + link + " is a duplicate entry found at ");
					       	System.out.println(String.valueOf(linecount) 
					       	+ " and " 
					       	+ String.valueOf(linecount2));
							}
						}
					}
				}
				catch (InvalidURLException N) {
				linkerror = true;
				System.out.print("...Line " + String.valueOf(linecount) + " has a link that isn't an html file: ");
				System.out.println(link);
				}
				catch (URISyntaxException U) {
				linkerror = true;
				System.out.print("...Line " + String.valueOf(linecount) + " has a link that isn't quite right: ");
				System.out.println(link);
				}
				catch (MalformedURLException M) {
				linkerror = true;
				System.out.print("...Line " + String.valueOf(linecount) + " has a link that isn't quite right: ");
				System.out.println(link);
				}
			}
		System.out.println("...The number of lines in " + file +  " is "  + linecount + ".");
		}
		catch (IOException I) {
		D.error(I);
		}
	return (duplicate || linkerror);
	}

}
