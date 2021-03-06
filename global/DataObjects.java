package drudge.global;

import drudge.data.*;
import drudge.page.*;
import java.net.URL;
import java.util.*;

public interface DataObjects {
/*THIS IS FOR DADA OBJECT*/
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:default:/home/time/java/db;");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:MySQL://localhost/home/time/java/db;");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:derby:/home/tim/java/db");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc", "derby", "/home/tim/java/db", ";create=true");
final public static Data<Page> dada = (Data<Page>)new DataList<Page>(FileNames.links);
//final public static Data<Page> dada = (Data<Page>)new DataObject("files");
//final public static Data<Page> dada = (Data<Page>)new DataFileWriter<Page>(FileNames.links);
//final public static Data<Page> dada = (Data<Page>)new DataStringWriter<Page>();
//final public static Data<Page> dada = (Data<Page>)new DataStringBuilder<Page>();

/*THIS IS FOR DADA_EMAILS OBJECT*/
//final public static Data<URL> dada_emails = (Data<URL>)new DataList<URL>();
final public static Data<URL> dada_emails = (Data<URL>)new DataListEmail<URL>(FileNames.emails);

/*THIS IS FOR EXCLUDE/INCLUDE OBJECTS*/
final public static Data<Page> exclude = new DataListExclude<Page>(FileNames.exclude);
final public static Data<Page> include = new DataListExclude<Page>(FileNames.include);

/*THIS IS FOR NOROBOT OBJECTS*/
final public static Hashtable<URL, Data<URL>> norobothash = new Hashtable<URL, Data<URL>>();

/*THIS IS FOR ALL OTHER OBJECTS MISCILANIOUS*/
final public static Data<String> donotusewords = new DataListExcludeWords<String>(FileNames.words);

/*This is an array to store all dada obects*/
final public static Data<?>[] all_dadas = {dada, dada_emails, exclude, include, donotusewords};

}
