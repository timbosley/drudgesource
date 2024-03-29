package drudge.spider;

import java.io.*;
import java.util.*;
import java.net.*;
import drudge.data.*;
import drudge.*;
import drudge.page.*;
import drudge.global.*;

public class SpiderComp extends Spider {
private Page top;
private Comparator compare = new TopComparator();

	public SpiderComp(Comparator co) {
	compare = co;
	}

	//I put this annotation in because I think Colections.sort(List) and Collections.sort(List, Comparator) are being confused by the compiler which results in a compiler warning
	@SuppressWarnings(value="unchecked")
	protected void links(Page p) {
	p.source().fill();
	DataList pages = (DataList)p.getLinks();
	Collections.sort(pages, compare);//this sorts it the way it should be sorted for this spider	
	DataEnum.links.data.insert(pages);
	}

}









