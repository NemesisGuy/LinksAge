This application is intened for use with the LINKS A.I. It Calculates how old the current instilation of LINKS is and speaks it back to you.

Prerequisite :
	Ensure that you hae the latest version of Java runtime environment installed eg. JRE 8 
   	http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html


Instructions :
	In the shell commands section of the LINKS U.I. add the folowing 

		Command: How old are you?
		Response: {{!Action!}}
		Action: -ret"java" -cp "%AppData%\LINKS\Customization\Scripts\;" LinksAge

	Please note that the LinksAge.class file needs to be placed in this folder  %AppData%\LINKS\Customization\Scripts\


