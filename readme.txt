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
4.  Data mehtods that use Debug methods should be changed because check no longer throws exception;
5.  Make a way to get image files as it crawls and perhaps other types of data.
6.  BUG! DataObject doesn't save content field of the Page class.  It doesn't write title, or keywords;
9.  Make more efficient P.getKeywords method;
14. Change Help enum so that it has a getHelpDetails() for each help option;
15. BUG! Figure out a better way to write error file so we don't have exepected nullpointer issues in the future;
16. BUG! On SpiderTop it will throw null pointer exception if the page in question has no external links;
17. BUG! It is picking up non-link objects when I search for url links in P class. Must refine a little bit;
18. BUG! email file re-writes first one in test link;
19. Figure out if you can have debug methods automatically discover what method and class they are being called from;
23. Think of way of making each crawler a seperate thread that can run indepently or have multiple threads running at once;
24. Experiment with references to see if deleting page objects when appopriate will improves program speed;

*************************OFFICIAL FORMAT OF THIS FILE*********************************************************
1. All sections of this file must have ******Section title********** to seperate it from the rest of the file;
2. All items in a sectin are to be listed with a number at the beginning and a ; at the end of the sentence;
3. If the item is a bug then it must be listed in the TO DO section with the word BUG! in front of it like BUG! bug item;
4. There must always be a todo section of this file and the items that need to be done continuously must be at the top;
5. The numbers on the other items do not change if an item is removed;
6. A new item can be appended to the end or inserted bebetween two items.  The only thing it has to be is in proper numerical order;
7. Anyone can add to the list of things to do but don't expect people to immediatly work on it since your idea may not be that great;

************************End User License Agreement*************************************************************************************
This software is free for personal use only so if you are a business then you have to pay the author of this program.
