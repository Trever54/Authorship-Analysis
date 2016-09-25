This program uses the command prompt to run. 
The main file is AuthorshipAnalysis.java. There are several commands
available to this program as listed below:

	'java AuthorshipAnalysis help' will give a list of all commands and respected information

	'java AuthorshipAnalysis store Author Name file1.txt file2.txt...' 
	will store gathered information from the text files for the respected author. 
	Multiple authors can be accounted for at once using the following method:
	'java AuthorshipAnalysis store Author One file1.txt file2.txt Author Two file3.txt file4.txt...' 

	'java AuthorshipAnalysis clear' will clear any memory stored

	'java AuthorshipAnalysis find textFile.txt' will find the closest author
	to that text files analyzed metrics. The best match is returned along
	with a number that indicates how close of a match the author is. The
	lower the number, the closer the match. 0 indicates an exact match.

The text files included with this project are listed below
with their respected authors. They can be used for testing purposes.

--ALL TEXT FILES TAKEN FROM www.gutenberg.org--
Jane Austen - Pride and Prejudice
Mark Twain - Adventures of Huckleberry Finn
Mark Twain - Adventures of Tom Sawyer
Lewis Carroll - Alice's Adventures in Wonderland
Lewis Carroll - Through the Looking-Glass