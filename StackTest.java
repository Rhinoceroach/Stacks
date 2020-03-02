// The "StackTest" class.
// Author: Jackson
// Date: 12/20/2018
// Purpose: To create a stack class and test it
// Input: Keyboard
// Output: Console/screen

import java.awt.*;
import hsa.Console;
import hsa.*;

public class StackTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	c.println ("Welcome to the Parenthesis checker, where we will check if you have used your");
	c.println ("brackets correctly.");
	c.println ();
	c.println ("Please enter '1' or '2' for your method of inputting your text to be checked:");
	String data;
	int method;
	Parenthesis parenthesis;
	boolean stop = false;
	char key = ' ';

	while (!stop)
	{
	    method = 0;
	    while (method != 1 && method != 2)
	    {
		c.println ("1) Input text via string");
		c.println ("2) Input via file");
		c.print ("Please enter your choice: ");
		method = c.readInt ();
		if (method != 1 && method != 2)
		{
		    c.println ("You did not enter a valid number. Please try again.");
		}
	    }
	    if (method == 1)
	    {
		c.print ("Please enter a string: ");
		data = c.readLine ();
		parenthesis = new Parenthesis (data);
		parenthesis.stringBracketCheck (c);
	    }
	    else
	    {
		c.print ("Please enter the name of the file with the extension: ");
		data = c.readLine ();
		parenthesis = new Parenthesis (data);
		parenthesis.fileBracketCheck (c);
	    }
	    c.println ();
	    c.print ("Enter 'x' to stop or anything else to continue...");
	    key = c.getChar ();
	    if (key == 'x' || key == 'X')
	    {
		stop = true;
	    }
	    c.clear();
	}
	// Place your program here.  'c' is the output console
    } // main method
} // StackTest class


// Author: Jackson
// Date: 12/6/2018
// Purpose: To  create a node class
// Fields:
//      data - contains the data of the node
//      next - contains the next Node in the linked list
// Methods:
//      clone - creates a copy of the Node
//      toString - creates a string containing all the data in the Nodes

class Node implements Cloneable
{
    public String data;
    public Node next;

    public Node (String info)
    {
	data = info;
	next = null;
    }


    public Node ()
    {
	this ("");
    }


    // Author: Jackson
    // Date: 12/6/2018
    // Purpose: To create a copy of the Node
    // Input: None
    // Output: Returns a Node object which is the copy of the the Node

    public Object clone () throws CloneNotSupportedException
    {
	Node newObject = (Node) super.clone ();
	if (next == null)
	    newObject.next = null;
	else
	    newObject.next = (Node) next.clone ();
	return newObject;
    }


    // Author: Jackson
    // Date: 12/6/2018
    // Purpose: To return the data of alln the Nodes as a string
    // Input: None
    // Output: Returns a string which contains the data of all the Nodes in the list

    public String toString ()
    {
	String info = "";
	info = info + data + " ";
	if (next != null)
	    info = info + next.toString ();
	return info;
    }
}


// Author: Jackson
// Date: 12/6/2018
// Purpose: To create a stack of nodes
// Fields:
//      top - contains the Nodes
// Methods:
//      isEmpty - returns true if the list is empty, otherwise returns false
//      clone - creates a copy of the SingleLinkedList
//      toString - creates a string containing all the data of the nodes, returns "empty" if liust is empty
//      push - pushes a Node onto Stack and changes top
//      pop - deletes the last node insert and returns it, changes top

class Stack implements Cloneable
{
    protected Node top;

    public Stack (Node nodes)
    {
	this.top = nodes;
    }


    public Stack ()
    {
	top = null;
    }


    // Author: Jackson
    // Date: 12/6/2018
    // Purpose: To check if this.list is empty
    // Input: None
    // Output: Returns true if the list is empty, otherwise returns false

    public boolean isEmpty ()
    {
	return top == null;
    }


    // Author: Jackson
    // Date: 12/6/2018
    // Purpose: To create a copy of the SingleLinkedList
    // Input: None
    // Output: Returns a new SingleLinkedList which is a copy of this SingleLinkedList

    public Object clone () throws CloneNotSupportedException
    {
	Stack newObject = (Stack) super.clone ();
	if (top != null)
	{
	    newObject.top = (Node) top.clone ();
	}
	else
	{
	    newObject.top = null;
	}
	return newObject;
    }


    // Author: Jackson
    // Date: 12/6/2018
    // Purpose: To create a string containing all the data of the nodes
    // Input: None
    // Otuput: Returns a string containing all the data in the list of Nodes,
    //         return "empty" if this.list is empty

    public String toString ()
    {
	String info;
	if (isEmpty ())
	{
	    info = "Empty";
	}
	else
	{
	    info = top.toString ();
	}
	return info;
    }


    // Author: Jackson
    // Date: 1/1/2019
    // Input: Parameters(Node node)
    // Output: Pushes a node onto the stack and changes top

    public void push (Node node)
    {
	node.next = top;
	top = node;
    }


    // Author: Jackson
    // Date: 1/1/2019
    // Input: None
    // Output: Returns the last item inserted and changes top, deletes last item inserted

    public Node pop ()
    {
	Node temp = null;
	if (!isEmpty ())
	{
	    temp = top;
	    top = top.next;
	}
	return temp;
    }
}


// Author: Jackson
// Date: 12/20/2018
// Purpose: To create a parenthesis class
// Fields:
//      data - contains the string
//      stack - contains the  words in the string as a stack
// Methods:
//      getString - gets the string from the user using Console c
//      stringBracketCheck - to check if a string has good struture depending on if the brackets are used correctly
//      fileBracketCheck - to check if a file has a good structure depending on if the brackers are used correctly

class Parenthesis
{
    public String data;
    public Stack stack;

    public Parenthesis (String info)
    {
	this.data = info;
	this.stack = new Stack ();
    }


    public Parenthesis ()
    {
	this ("");
    }


    // Author: Jackson
    // Date: 1/7/2018
    // Purpose: To check if a string uses proper brackets
    // Input: Parameters(Console c)
    // Output: Prints a message depending if the string is valid,
    //         prints good structure if valid string or error message if not

    public void stringBracketCheck (Console c)
    {
	Node bracket;
	int pos = 1;
	boolean error = false;
	char character = ' ';
	for (int i = 1 ; i <= data.length () ; i++)
	{
	    character = data.charAt (i - 1);
	    if (character == '(' || character == '{' || character == '[')
	    {
		bracket = new Node (String.valueOf (character));
		stack.push (bracket);
	    }
	    else if (character == ')' || character == '}' || character == ']')
	    {
		if (!stack.isEmpty ())
		{
		    bracket = stack.pop ();
		    if ((bracket.data.equals ("(") && character != ')') || (bracket.data.equals ("{") && character != '}') || (bracket.data.equals ("[") && character != ']'))
		    {
			pos = i;
			error = true;
			i = data.length ();
		    }
		}
		else
		{
		    pos = i * -1;
		    error = true;
		    i = data.length ();
		}
	    }
	}
	if (error)
	{
	    if (pos > 0)
		c.println ("Mismatched brackets at position " + pos);
	    else
	    {
		pos = pos * -1;
		c.println ("Too many close brackets at position " + pos);
	    }
	}
	else if (!stack.isEmpty ())
	{
	    c.println ("Too many open brackets at position " + (data.length ()));
	}
	else
	{
	    c.println ("Good structure");
	}
    }


    // Author: Jackson
    // Date: 1/8/2018
    // Purpose: To check if a file uses proper brackets
    // Input: Parameters(Console c)
    // Output: Prints a message depending if the file is valid,
    //         prints good structure if valid file or error message if not

    public void fileBracketCheck (Console c)
    {
	String line;
	boolean error = false;
	Node bracket;
	int pos = 1;
	int count = 0;
	char character = ' ';
	TextInputFile input = new TextInputFile (data);
	while (!input.eof () && !error)
	{
	    line = input.readLine ();
	    for (int i = 1 ; i <= line.length () ; i++)
	    {
		character = line.charAt (i - 1);
		if (character == '(' || character == '{' || character == '[')
		{
		    bracket = new Node (String.valueOf (character));
		    stack.push (bracket);
		}
		else if (character == ')' || character == '}' || character == ']')
		{
		    if (!stack.isEmpty ())
		    {
			bracket = stack.pop ();
			if ((bracket.data.equals ("(") && character != ')') || (bracket.data.equals ("{") && character != '}') || (bracket.data.equals ("[") && character != ']'))
			{
			    pos = i;
			    error = true;
			    i = line.length ();
			}
		    }
		    else
		    {
			pos = i * -1;
			error = true;
			i = line.length ();
		    }
		}
	    }
	    count++;
	}
	input.close ();
	if (error)
	{
	    if (pos > 0)
		c.println ("Mismatched brackets at line " + count + " position " + pos);
	    else
	    {
		pos = pos * -1;
		c.println ("Too many close brackets at line " + count + " position " + pos);
	    }
	}
	else if (!stack.isEmpty ())
	{
	    c.println ("Too many open brackets at line " + count + " position " + (data.length ()));
	}
	else
	{
	    c.println ("Good structure");
	}
    }
}

