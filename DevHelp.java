package drudge;

import drudge.global.FileNames;







enum DevHelp {
HELP("-HELP", "<-------------------------!!!SPECIAL DEVELOPER OPTIONS!!!------------------------->"),
A("-A", "This will see if http://your.url.here robot.txt policy will let you crawl their website"), 
B("-B"),
C("-C", "This will list out specific times in the cycle"),
D("-D", "This will Decode/Encode strings"),
E("-E", "This will check to see if there are any errors in the data storage"),
F("-F"),
G("-G", "This will send output to a lind by line grep-able file called " + FileNames.out),
H("-H", "http://your.url.here will print out the server headers that it spits out"),
I("-I", "This will test to see if a page is in the " + FileNames.include + " file"),
IMG("-IMG", "This is for testing this programs ability to get images from a url link"),
IP("-IP", "This will look up the IP number associated with domain name and vice-versa"),
J("-J"),
K("-K", "This will show the keywords of http://your.url.here"),
L("-L", "This will show the links of http://your.url.here"),
M("-M", "This is for testing this programs ability to get emails from a url link"), 
MIME("-MIME", "This will tell youw what the MIME type is for a file"),
N("-N"), 
O("-O"), 
P("-P", "This will \"ping\" a website to test the quality of the connection"),
Q("-Q"),
R("-R", "This will see show you the sites robots.txt file"),
S("-S", "This will show the source of http://your.url.here"),
STOP("-STOP", "This will force the spider to stop on each cycle"),
T("-T", "This will show the title of http://your.url.here"),
U("-U", "http://your.url.here will test if this url is useless for this program"),
V("-V", "http://your.url.here will print out if this is a valid html file for this program"),
W("-W", "This will test to see if a word is in the " + FileNames.words + " file"),
X("-X", "This will test to see if a page is in the " + FileNames.exclude + " file"),
Y("-Y"),
Z("-Z");

String parameter;
String message = "";

	private DevHelp(String p) {
	parameter = p;
	}

	private DevHelp(String p, String m) {
	this(p);
	message = m;
	}

}
