***********************************READ ME**************************************************************************************
1.  This is the readme.txt file that gives a brief description of the program and the author; 
2.  The author is Tim Bosley (ursending@gmail.com) and it is programmed for the java virtual machine;  
3.  It is a webcrawler program that is designed to be as small and efficient as possible which is where it gets its speed;
4.  The idea behind it is the faster the program runs the more links it can capture on the web;
5.  This is why the code must always strive to be as efficient as possible;
6.  It is opensource freeware so that other programmers can find more ways to make it run faster;
7.  This readme file contains a todo list of things to do such as fixing bugs or ideas to make the code run faster;
8.  Any programmer can add things to the todo list below as long as they follow the format listed below;
9.  Any progrmmer that adds an item to the list doesn't necessarily have to fix it themselves;
10. They can let other programmers look at it and address it if they want to;



************************THINGS TO DO IN DRUDGE*****************************************************
1.  Make a todo list of things to do like adding new features OR cleaning up bugs in the program;
2.  Elimnate unnescessary variables, methods, and classes in programs for efficiency;
3.  Use D.error in more places so I have a written log of errors that I would like to see more data on;
4.  Must find broken links in code;
6.  BUG! DataObject doesn't save content field of the Page class.  It doesn't write title, or keywords;
10. Figure out why isUseless seems to bottleneck everything;
13. BUG! Must find broken link where there is only one quote quoting it
16. Work on TRUE skip option that doesn't remove non-oks;
17. BUG! It is picking up non-link objects when I search for url links in P class. Must refine a little bit;
20. Investigate to see if translating international urls unicode to american ascii will be useful;
23. Think of way of making each crawler a seperate thread that can run indepently or have multiple threads running at once;
25. Consider have Help enums have own object that can execute the help command;
27. See if Debug should be its own independent package instead of something attached to Drudge project;
29. Create a good sample sitemap.xml file for testing purposes;
32. Update license option to make sure license printed is up to date and can't be used to undo the license in readme.txt;
42. Create findlinks using xml api;
43. Link all the page objects together as a way of storing them;

*************************OFFICIAL FORMAT OF THIS FILE*********************************************************
1. All sections of this file must have ******Section title********** to seperate it from the rest of the file;
2. All items in a sectin are to be listed with a number at the beginning and a ; at the end of the sentence;
3. If the item is a bug then it must be listed in the TO DO section with the word BUG! in front of it like BUG! bug item;
4. There must always be a todo section of this file and the items that need to be done continuously must be at the top;
5. The numbers on the other items do not change if an item is removed;
6. A new item can be appended to the end or inserted bebetween two items.  The only thing it has to be is in proper numerical order;
7. Anyone can add to the list of things to do but don't expect people to immediatly work on it since your idea may not be that great;
8. This file must always have a data in the form of Month/Day/Year in number format like 01/01/2001 to indicate when it was last updated;

************************End User License Agreement*************************************************************************************
This software is free for personal use only so if you are a business then you have to pay the author of this program.

**************************************Last Updated****************************************************************************
02/07/2023
02/11/2023
02/13/2023
02/14/2023
