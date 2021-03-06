package drudge;

import drudge.spider.*;
import drudge.data.*;
import drudge.global.*;
import drudge.page.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Drudge implements DataObjects {
public static String XFACTOR = null;

	public static void main(final String[] arg) {
	long MemoryStart = Runtime.getRuntime().freeMemory();
	//these are for method wide variables other helper metheds will use
	int maxcyc = 1;//default value
	int crawlmethod = 1;//default value
	int begcyc = 0;//default starting number for program
	boolean getemails = false;//default value
	boolean okays = true;//default value
	boolean robotsallowed = true;//default value
	long delay = 0;//default value
	Spider spider = null;
	eraseFile(FileNames.error);
	
		//this is the main program loop
LOOP:		for (int i = 0; i < arg.length; i++) {
		final String a = arg[i];
		/*TRY TO PUT OPTIONS IN ALPHABETICAL ORDER*/
			if ((a.equals("-about") || a.equals("-a")) && arg.length == 1) {
			Help.print(a);
			break;
			}
			//the only commands that should have startWith vs equals are ones that use a combination like c=xxxxx
			else if (a.startsWith("-c=")) {
			String[] b = a.split("=", 2);
				try {
				crawlmethod = Integer.parseUnsignedInt(b[1]);
				}
				catch (NumberFormatException N) {
				Print.error(N);
				break;
				}
				
			}
			else if (a.startsWith("-d=")) {
			String[] b = a.split("=", 2);
				try {
				int pagedata = Integer.parseUnsignedInt(b[1]);
				dada.setLevel(pagedata);
					if (pagedata == 3) {
						try {
						donotusewords.begin();
						}
						catch (IOException I) {
						Print.error(I);
						}
						catch (Exception E) {
						Print.error(E);
						}
					}
				}
				catch (NumberFormatException N) {
				Print.error(N);
				}
			}
			else if (a.equals("-e")) {
			getemails = true;
				try {	
				dada_emails.begin();
				}
				catch (Exception E) {
				Print.error(E);
				}
			}	
			else if (a.startsWith("-exc") || a.startsWith("-exclude=")) {
			dada.setExcluded(true);	
			String file = exclude.source();
				//this is for custom exclude file
				if (a.startsWith("-exclude=")) {
				String[] b = a.split("=", 2);
				file = b[1];
				}
				try {
				exclude.setSource(file);
				exclude.begin();
				}
				catch (IOException I) {
				Print.error(I);
				}
				catch (Exception E) {
				Print.error(E);
				}	
			}
			else if ((a.equals("-help") || a.equals("-h")) && arg.length == 1) {
			Help.print(a);
			break;
			}
			else if ((a.equals("-help") || a.equals("-h")) && i == 0 && arg.length == 2) {
			Help.print(arg[i + 1]);
			break;	
			}
			else if (a.equals("-i") && arg.length == 1) {
				try {
				LineNumberReader reader = new LineNumberReader(new FileReader(FileNames.in));
				String[] inputs = new String[0];
					for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					String[] lineinputs = line.split(" ");
					final int size = inputs.length + lineinputs.length;
					String[] newinput = new String[size];
						for (int k = 0; k < inputs.length; k++) {
						newinput[k] = inputs[k];
						}	
						for (int k = inputs.length; k < size; k++) {
						newinput[k] = lineinputs[k - inputs.length];
						}
					inputs = newinput;
					
					}
				main(inputs);
				}
				catch (FileNotFoundException F) {
				System.out.println("File " + FileNames.in + " could not be foun");
				Help.print(a);
				Print.error(F);
				}
				catch (IOException I) {
				System.out.println("Trouble in reading file " + FileNames.in);
				Print.error(I);
				}
			break;
			}
			else if (a.startsWith("-inc") || a.startsWith("-include=")) {
			dada.setIncluded(true);
			String file = include.source();
				//this is for custom exclude file
				if (a.startsWith("-include=")) {
				String[] b = a.split("=", 2);
				file = b[1];
				}
				try {
				include.setSource(file);
				include.begin();
				}	
				catch (IOException I) {
				Print.error(I);
				}
				catch (Exception E) {
				Print.error(E);
				}
				
			}
			else if (a.equals("-l") && arg.length == 1) {
			System.out.println(ThisProgram.license);
			break;
			}
			else if (a.startsWith("-m=")) {
			String[] b = a.split("=", 2);
				try {
				final int c = Integer.parseUnsignedInt(b[1]);
				Print.UselessClass = UselessMessages.getClass(c);
				}
				catch (NumberFormatException N) {
				Help.print(a);
				Print.error(N, b[1]);
				break;
				}
			}
			else if (a.startsWith("-n=")) {
			String[] b = a.split("=", 2);
				try {
				maxcyc = Integer.parseUnsignedInt(b[1]);
				}
				catch (NumberFormatException N) {
				Print.error(N);
				break;
				}
			}
			else if (a.equals("-nok")) {
			okays = false;//this means it won't check for ok responses in spider
			}
			else if (a.equals("-o")) {
				try {
				PrintStream p = new PrintStream(FileNames.out);
				System.out.println("Printing to file called " + FileNames.out);
				Print.GSTREAM = p;
				}
				catch (FileNotFoundException F) {
				System.out.println("Could not find file " + FileNames.out);
				break;
				}
			}
			else if (a.startsWith("-p=")) {
			String[] b = a.split("=", 2);
				try {
				InetAddress inetadress = InetAddress.getByName(b[1]);//I net a dress funny :)
					try {
					InetSocketAddress sockdress = new InetSocketAddress(inetadress, 80);//more funny stuff
						try {
						Page.proxyserver = new Proxy(Proxy.Type.HTTP, sockdress);
						}
						catch (IllegalArgumentException I) {
						Print.error(I);
						}
					}
					catch (IllegalArgumentException I) {
					Print.error(I);
					}	
				}
				catch (UnknownHostException U) {
				Print.error(U);
				break;
				}
		
			}
			else if (a.equals("-r")) {
			robotsallowed = false;
			}
			else if (a.startsWith("-w=")) {
			String[] b = a.split("=", 2);
				try {
				delay = Long.parseUnsignedLong(b[1]);
				}
				catch (NumberFormatException N) {
				Print.error(N);
				break;
				}
			
			}
			else if (a.startsWith("-x=")) {
			String[] b = a.split("=", 2);
			Drudge.XFACTOR = b[1];
			}
	
			//secret debugger options down here
			else if (a.equals("-A") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);
					try {
					p.isUseless();	
						try {
						p.isValid();
							try {
							p.isRobotAllowed();
							System.out.println("Yes!  " + ThisProgram.name + " is allowed");
							}
							catch (NoRobotsURLException N) {
							Print.error(N, p);
							}
						}
						catch (URISyntaxException U) {
						Print.error(U, arg[i + 1]);
						}
						catch (InvalidURLException N) {
						Print.error(N, arg[i + 1]);
						}
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-C") && arg.length == 2 && i == 0) {
			Debug.cycletimeon = true;
			continue;
			}
			else if (a.equals("-E") && arg.length == 1) {
				for (Data<?> d : all_dadas) {
				d.checkError();
				}
			System.out.println("Have a nice day :)");
			break;
			}
			else if (a.equals("-G") && i == 0) {
			System.out.println("Creating GREP-able file to " + FileNames.gout + " so you can use grep on the results.");
				try {
				Print.GSTREAM = new PrintStream(FileNames.gout);
				}
				catch (FileNotFoundException F) {
				Print.GSTREAM = System.out;
				Print.error(F, FileNames.gout);
				}
			continue;//this will make sure it continues to the next the loop	
			}
			else if ((a.equals("-H")) && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);
					try {	
					URLConnection c = p.openConnection();
					System.out.println("----------------Request-Fields------------------");
					Map<String, List<String>> request = c.getRequestProperties();
						for (Map.Entry<String, List<String>> entry: request.entrySet()) {
						System.out.println( entry.getKey() + " : " + entry.getValue());
						}	
			
					System.out.println("----------------Response-Headers----------------");	
					Map<String, List<String>> heads = c.getHeaderFields();
						for (Map.Entry<String, List<String>> entry : heads.entrySet()) {
						System.out.println(entry.getKey() + " : " + entry.getValue());
						}
					}
					catch (IOException I) {
					Print.error(I, p);
					}
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-I") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);	
					try {
					include.begin();
						try {
						p.isIncluded();
						System.out.println(p.toString() + " is in the " + FileNames.include + " file.");	
						}
						catch (ExcludedURLException E) {
						Print.error(E, p);
						}
					}	
					catch (IOException I) {
					Print.error(I);
					}
					catch (Exception E) {
					Print.error(E);
					}	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-HELP") && arg.length == 1) {
			//Print.HELP();
			Help.print(a);
			break;
			}
			else if (a.equals("-K") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);
					try {
					p.isUseless();
						try {
						p.isValid();
						p.getSource();
						Data<String> keywords = p.getKeywords();
							for (String keyword : keywords) {
							System.out.println(keyword);
							}	
						}
						catch (URISyntaxException U) {
						Print.error(U, arg[i + 1]);
						}
						catch (InvalidURLException N) {
						Print.error(N, arg[i + 1]);
						}	
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-L") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);	
					try {
					p.isUseless();	
					p.getSource();
						try {
						p.isValid();
						Data<Page> pages = p.getLinks();
							for (Page page : pages) {
							System.out.println(page.toString());
							}
						}
						catch (URISyntaxException U) {
						Print.error(U, arg[i + 1]);
						}
						catch (InvalidURLException N) {
						Print.error(N, arg[i + 1]);
						}
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.startsWith("-P") && arg.length == 2 && i == 0) {
				try {
				long pt = System.currentTimeMillis();
				URL url = new URL(arg[i + 1]);
				URLConnection connection = url.openConnection();
				connection.connect();
				//successful connection
				long et = System.currentTimeMillis();
				long time = et - pt;
				System.out.print("The total time it took to connect to ");
				System.out.print(arg[i + 1]);
				System.out.print(" was ");
				Print.totalTime(time);
				System.out.print("\n");	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (SocketTimeoutException S) {
				Print.error(S, arg[i + 1]);	
				}
				catch (IOException I) {
				Print.error(I, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-R") && arg.length == 2 && i == 0) {//this doesn not work yet!
				try {
				Page p = new Page(arg[i + 1]);
					
					try {
					p.isRobotAllowed();
					System.out.println(arg[i + 1] + 
					" is NOT disallowed by sites robot.txt file."); 
					}
					catch (NoRobotsURLException N) {
					Print.error(N, arg[i + 1]);
					}
			
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
			break;	
			}
			else if (a.equals("-S") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);
					try {
					p.isUseless();
						try {
						p.isValid();
						System.out.println(p.getSource());
						}
						catch (URISyntaxException U) {
						Print.error(U, arg[i + 1]);
						}
						catch (InvalidURLException N) {
						Print.error(N, arg[i + 1]);
						}
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-T") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);
					try {
					p.isUseless();
						try {
						p.isValid();
						p.getSource(); 
						String title = p.getTitle();
						System.out.println(title);
						}
						catch (InvalidURLException I) {
						Print.error(I, arg[i + 1]);
						}
						catch (URISyntaxException U) {
						Print.error(U, arg[i + 1]);
						}
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-U") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);	
					try {	
					p.isUseless();
					System.out.println(p.toString() + " is not a useless url for this program");	
					}
					catch (IOException I) {
					Print.error(I, p);
					}
					catch (UselessURLException U) {
					Print.error(U, p);
					}
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-V") && arg.length == 2 && i == 0) {
				try { 
				Page p = createFirstPage(arg[i + 1]); 
					try {
					p.isValid();
					System.out.println(p.toString() + " is a valid html file for " + ThisProgram.name);
					}
					catch (URISyntaxException U) {
					Print.error(U, arg[i + 1]);
					}
					catch (InvalidURLException N) {
					Print.error(N, arg[i + 1]);
					}	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			else if (a.equals("-W") && arg.length == 2 && i == 0) {
				
				try {
				donotusewords.begin();
					if (donotusewords.contains(arg[i + 1])) {
					System.out.println(arg[i + 1] + " is in the " + FileNames.words + " file.");
					}
					else {
					System.out.println(arg[i + 1] + " is NOT in the " + FileNames.words + " file.");
					}		
				}	
				catch (IOException I) {
				Print.error(I);
				}
				catch (Exception E) {
				Print.error(E);
				}			
			break;
			}
			else if (a.equals("-X") && arg.length == 2 && i == 0) {
				try {
				Page p = createFirstPage(arg[i + 1]);	
					try {
					exclude.begin();
						try {
						p.isExcluded();
						System.out.println(p.toString() + " is NOT in the " + FileNames.exclude + " file.");	
						}
						catch (ExcludedURLException E) {
						Print.error(E, p);
						}
					}	
					catch (IOException I) {
					Print.error(I);
					}
					catch (Exception E) {
					Print.error(E);
					}	
				}
				catch (MalformedURLException M) {
				Print.error(M, arg[i + 1]);
				}
				catch (UnknownHostException U) {
				Print.error(U, arg[i + 1]);
				}
			break;
			}
			/*!this is the last parameter in the program!*/
			else if (i == arg.length - 1) {
			Print.printColumnHeaders();//this should be called first to show errors correctly in output columns
			Debug.time("Print Column Headers");
			String lastarg = arg[arg.length - 1];
				if (lastarg.equals("-s")) {
					try {
					begcyc = CountFile.get();
					maxcyc = maxcyc + begcyc;
					dada.begin();
					}
					catch (IOException I) {
					Print.error(I);
					}
					catch (Exception E) {
					Print.error(E);
					}				

				}
				else if (lastarg.startsWith("-s=")) {
				String[] b = a.split("=", 2);
					try {
					int num = Integer.parseUnsignedInt(b[1]);
					begcyc = num - 1;
					maxcyc = maxcyc + begcyc;
					dada.begin();
					}
					catch (NumberFormatException N) {
					Print.error(N);
					break;
					}
					catch (Exception E) {
					Print.error(E);
					}
			
				}
				else {
					try {
					dada.truncate();//this will delete existing data and start over
					Page firstpage = createFirstPage(lastarg);
					dada.put(firstpage);
					}
					catch (MalformedURLException M) {
					Print.printRow(M, lastarg);
					}
					catch (UnknownHostException U) {
					Print.printRow(U, lastarg);
					}
					catch (URISyntaxException U) {
					Print.printRow(U, lastarg);
					}
					catch (InvalidURLException N) {
					N.printRow();
					}
					catch (ExcludedURLException E) {
					E.printRow();
					}
					catch (DuplicateURLException D) {
					D.printRow();
					}
					catch (Exception E) {
					Print.printRow(E, lastarg);
					}
				}

			spider = createSpider(crawlmethod);
			spider.setCheckOK(okays);
			spider.setRobotsAllowed(robotsallowed);
			spider.setDelay(delay);
			
			final long begintime = System.currentTimeMillis(); 
				do {
				Page p = dada.get(begcyc);
					if (p == null) {
					break;
					}
				boolean remove = spider.crawl(p);
					if (remove == true) {
					dada.remove(begcyc);
					continue;//this skips all of the rest of the loop and restarts it
					}
					if (getemails) {
					Data<URL> emails = p.getEmails();
					dada_emails.put(emails);
					}
				Print.printRow(p, begcyc);
				begcyc++;
				} while (begcyc < maxcyc);
			Debug.time("Spider Crawl");
				try {
				dada.end();
				}
				catch (Exception E) {
				Print.error(E);
				}
				if (getemails) {
					try {
					dada_emails.end();
					}
					catch (Exception E) {
					Print.error(E);
					}
				}
			final long endtime = System.currentTimeMillis();
			Debug.time("DataBase disconnect");
				try {
				CountFile.set(begcyc);
				}
				catch (IOException I) {
				Print.error(I);
				}
			Debug.time("Writing Countfile");
				
			//this is to print out last stats of progam	
			System.out.printf("Links:\t\t%d\n",  dada.size());
			System.out.print("Time:\t\t");
			Print.totalTime(endtime - begintime);
			long MemoryNow = Runtime.getRuntime().freeMemory();
			long MemoryUsed = MemoryNow - MemoryStart;
			System.out.println("Memory:\t\t" + String.valueOf(MemoryUsed));
			System.out.printf("Date:\t\t%s\n", D.getDate());
			System.out.printf("Appreciation:\t%s\n", "Thank you for using this program");
			System.out.printf("Salutation:\t%s\n", "Have a nice day!");
			}
			else {
			System.out.println("Option " + a + " was ignored by this program.");
			}//end of lastarg of loop
		}//end of loop
	}//end of program

	static Page createFirstPage(String link) throws MalformedURLException, UnknownHostException {
	Page firstpage = null;
		if (link.equals("-samp") || link.equals("-t")) {
		File sampf = new File(FileNames.samp);
		File absfile = sampf.getAbsoluteFile();
		String samplepage = absfile.toURI().toString();
		firstpage = new Page(samplepage);
		}
		else if (link.matches("http[s]://.*")) {
		firstpage = new Page(link);
		}
		else if (
		link.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") || 
		link.matches(
		"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\." + 
		"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\." +
		"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\." + 
		"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")
		) {
		String[] bites = link.split("\\.");
		byte[] ip = new byte[bites.length];
			for (int i = 0; i < bites.length; i++) {
			ip[i] = Byte.valueOf(bites[i]);
			}
		InetAddress address = InetAddress.getByAddress(ip);
		String host = address.getCanonicalHostName();
		firstpage = new Page(host);
		}
		else if (link.equals("yourcomputer")) {
		InetAddress address = InetAddress.getLocalHost();
		String host = address.getCanonicalHostName();
		firstpage = new Page(host);
		}
		else {
		firstpage = new Page(link);
		}
	return firstpage;
	}

	static Spider createSpider(int c) {
	Debug.time("Creating Spider");//this starts cycle time for entire thing
	Spider spider = null;
		switch (c) {
			
			case 1:  
			spider = new Spider();
			break;

			case 2:
			BEGIN:	while (true) {
				System.out.println("WARNING: You are going to the dark side of this program!");
				System.out.print("This is overloading website.  Are you sure you want to continue? Y|N ");
					try {
					char answer = (char)System.in.read();
					System.in.skip(1);//this is for the return value
						if (answer == 'Y' || answer == 'y') {
							if (Drudge.XFACTOR == null) {
							System.out.print("How many times do you want to spam? ");
							Drudge.XFACTOR = "";
								for (char h = (char)System.in.read(); h != '\n'; h = (char)System.in.read()) {
									if (Character.isDigit(h)) {
									Drudge.XFACTOR += String.valueOf(h);
									}
								}
							}
							try {
							int times = Integer.parseUnsignedInt(Drudge.XFACTOR);
							spider = new SpiderSpam(times);
							break BEGIN;
							}
							catch (NumberFormatException N) {
							System.out.println("You did not enter a number.");	
							}
						}
						else if (answer == 'N' || answer == 'n') {
						System.out.println("That is good to know.  Goodbye.");
						System.exit(0);
						break BEGIN;
						}	
					}
					catch (IOException I) {
					Hashtable<String, Object> h = new Hashtable<String, Object>();
					h.put("Exception", I);
					h.put("Location", "Drudge.main()");
					Print.error(h);
					}
				}
			break;

			case 5:
				try {
				Integer I = Integer.valueOf(Drudge.XFACTOR);
				int s = I.intValue();
				spider = new SpiderRandom(s);	
				}
				catch (NumberFormatException N) {
				spider = new SpiderRandom((int)System.currentTimeMillis());
				}
			break;

			case 6:
			spider = new SpiderComp(new TopComparator<Page>()); 
			break;

			case 7:
			spider = new SpiderCrawlRedirects();
			break;

			case 8:
			spider = new SpiderComp(new NaturalComparator<Page>());
			break;

			default:
			System.out.println("There is no spider option for " + String.valueOf(c));
			Help.print("-c");	
			break;
		}
		
	Debug.time("Create Spider Object");
	return spider;
	}
	
	static boolean eraseFile(String filename) {
	boolean erased = false;
	File efile = new File(filename);
		if (efile.exists()) {
		erased = efile.delete();
		}
	return erased;
	}
	
	static private int getInteger(String arg) throws NumberFormatException {
	String[] ins = arg.split("=", 2);
	return Integer.parseUnsignedInt(ins[1]);
	}	
	
	static private String getString(String arg) {
	String[] ins = arg.split("=", 2);
	return ins[1];
	}
}
