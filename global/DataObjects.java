package drudge.global;

import drudge.data.*;
import drudge.page.*;
import java.net.URL;

public interface DataObjects {
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:default:/home/time/java/db;");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:MySQL://localhost/home/time/java/db;");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc:derby:/home/tim/java/db");
//final public static Data<Page> dada = (Data<Page>)new DataBase<Page>("jdbc", "derby", "/home/tim/java/db", ";create=true");
final public static Data<Page> dada = (Data<Page>)new DataCollection<Page>(FileNames.links);
//final public static Data<Page> dada = (Data<Page>)new DataArray<Page>(new Page[10], FileNames.links);
//final public static Data<Page> dada = (Data<Page>)new DataList<Page>();
//final public static Data<Page> dada = (Data<Page>)new DataFileWriter<Page>();
//final public static Data<Page> dada = (Data<Page>)new DataStringWriter<Page>();
//final public static Data<Page> dada = (Data<Page>)new DataStringBuilder<Page>();
//final public static Data<Page> dada = (Data<Page>)new DataSort<Page>(new Page[10], FileNames.links);
//final public static Data<URL> dada_emails = (Data<URL>)new DataArray<URL>(new URL[10], FileNames.emails);
//final public static Data<URL> dada_emails = (Data<URL>)new DataList<URL>();
//final public static Data<URL> dada_emails = (Data<URL>)new DataCollection<URL>(FileNames.emails);
final public static Data<URL> dada_emails = (Data<URL>)new DataEmailCollection<URL>(FileNames.emails);
final public static Data<Page> excludedlinks = new DataList<Page>();
//final public static Data<Page> includedlinks = new DataList<Page>();



}
