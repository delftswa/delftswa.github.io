---
layout: default
project: kodi
title: Kodi - Appendix
chapter: false
---

## Appendix - ***"Oh, one more thing..!"***

### Contributions - ***Using our gained knowledge***
An freeware open source project can only survive if a core group of developers voluntarily contribute to the development of these projects. During the journey through Kodi's layers and viewing it from various architectural viewpoints, the ambition is to actually contribute to the quality enhancement process of the Kodi Media Player. This varies from solving bugs, adding features, adding documentation, or refactoring code to make it 'future-proof'. 

#### How to start contributing to Kodi
We are armed with the different architectural tools, an experience richer. Kodi's quality is analysed and measured and the guidelines that should be abided are included. Although, not much about the real functionality of smaller pieces of code have been debated. Where to start in the 100.000+ lines of code? Since it has taken us a long time to gain understanding, a short tutorial of how starting to contribute to Kodi is displayed, for example by solving issues. 

1. Clone a local copy from the [master Kodi source code](http://github.com/xbmc/xbmc) on Github. 
2. Compile the source code for your operating system by using the [compiling read-me](https://github.com/xbmc/xbmc/tree/master/docs) .
3. Open the issue tracker on [http://trac.kodi.tv](http://trac.kodi.tv) and pick an interesting issue that **already has been solved**.
4. Open the [Pull-Requests overview](http://github.com/xbmc/xbmc/pulls) on the Kodi Github repository. 
5. See how issues are solved in the Pull Requests and on what areas this is debated.
6. Now it is time to find your own issue (for example [#15457](http://trac.kodi.tv/ticket/15457)). Read it through so that you understand the problem of the bug.
7. Open your .cpp code editor (for example xCode or Visual Studio).
8. This is a **tricky part**. Now that you have the terms of the issue (for example with **Rename**), this can be found in the language strings located at ````./addons/resource.language.en_gb/resources/string.po````. In this way you get a string number that correlates with the languages sting.po files.
9. The functions within the ````.cpp```` source files call these strings by their ````msgctxt```` number (see code fragment 1). 
		  


	````
	---In language string:---
	msgctxt "#118"
	msgid "Rename"
	
	---In .cpp file:---
	if ((CSettings::Get().GetBool("filelists.allowfiledeletion")
	....
	buttons.Add(CONTEXT_BUTTON_RENAME, 118);
	....
	````
	*Code fragment 1: The function relating to the #118 string - [source](https://github.com/xbmc/xbmc/blob/master/xbmc/video/windows/GUIWindowVideoNav.cpp#L965-L972)*

10. By searching in your code editor to this string number, there are less results shown. This clarifies your scope more precisely.
11. Now you have a clear view since there are a accessible amount of lines of code where the problem can be found. 
12. Start the Kodi Media Player and see how your changes do appear. 
13. When solved an issue, create a Pull Request with the ````.cpp```` file included. 
14. Add an explanation to your solution and argument your choices. 
15. Wait for the reaction of at least 2 of the main integrators (for example [*mkortstiege*](https://github.com/mkortstiege) or [*MartijnKaijser*](https://github.com/MartijnKaijser)). 
16. When they call the keyword "*Jenkins build this please*", their build bot named Jenkins is checking if the code runs with your patch (see Figure 8)
   <p align="center">
      <img src="images/jenkins.png" width=700px>
      <p align="center"><i>Figure 8: Jenkins the build bot</i></p>
   </p>
17. When you receive a message that your Pull Request is accepted; congratulations.  
18. Good luck with contributing!

