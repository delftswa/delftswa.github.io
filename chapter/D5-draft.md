<!---
The outline should consist of:

A 100 word abstract summarizing your chapter. ~Done
A table of contents for your chapter ~ Partly done
A plan of < 1 page explaining:
- which research on your chapter has finished (deliverables D1-D4 and maybe more) ~Done.
- which research on your chapter still needs to be done (maybe a pull request / contribution, an additional view/perspective, an interview) ~ Partly Done.
- a status per section in your table of contents indicating what still needs to be written / how far you are.
- A first version of your chapter reflecting the current status.
--->

#Outline of final chapter

###Abstract
Play Framework is a Scala/Java based web application framework that focusses on the development of scalable web applications and thus companies that have large and scalable websites like LinkedIn. 
Typesafe is the company that maintains the development of Play. 
Play's development is leaning towards a very strong modular design. 
This improves the maintainability and scalability of the framework. 
This chapter described the developer view and an **xxx** view. 
It also explains the software product line and **xxx** perspectives. 
Finally it provides important metrics that define the assessment of the frameworks Goal and an overview of the future plans of Play.

###Introduction to/Goal of Play Framework
	- Briefly touch upon what Play is
	- Briefly explain how it distinguishes itself from other web dev frameworks (Goals)
	- Ideology of Play/developers? What are key competitive features? Why should Play be chosen instead of other web dev frameworks?
	
###Important stakeholders
_Research from D1 that has been finished._

###Context view & module overview
_Research from D2 that has been finished._

###Standardisation & organisation
_Research from D2 that has been finished. This includes:_

* _Design_
* _Testing_
* _Monitoring_
* _Instrumentation_
* _Code organisation_

###Perspectives
_Research from D2 that has been finished._
_This includes the Software product line perspective._
_Another perspective still has to be researched and written._

####Evolution perspective on the Context view
In D1 we've talked about the context view.
In this view we took a very neutral perspective, describing the context of Play in a diagram, and explaining the different enitities (people, organisations, competitors etc.).
In this new perspective, that we have dubbed the 'evolution perspective', we will make an attempt to describe the same entities as before, but in the light of the evolution of Play: Where will it go?

#####Typesafe
Typesafe would like to see a broad adoption of all of their tools: Play, sbt, Scala, Akka.
Since all of these support each other as well are dependent on another it is key that all of these progress.
Typesafe states they are all in on Reactive development.
They support this movement by giving trainings, consulting as well as going to conferences.

#####External organisations
Typesafe relies on GitHub for coding and Googlegroups for communication.
However they are not dependent on these platforms.
In case there arises a problem in using these, Typesafe has alternatives to choose from.

#####Tools
Play is already trying to become less dependant of other tools such as Netty.
This is visible from the fact that they are trying to push Akka as their HTTP backend (which currently is in experimental status). 
This allows Play to be less dependant of other systems.

#####Competition
We've talked about competitors alot in D4 (adoption). For an open-source platform such as Play it always is important to attract enough users and developers to keep the framework alive and advancing.
Supporting a modern language (Scala) and a modern design philosophy (Reactive) Play offers what one should expect from a web-framework. But the competition is fierce and Scala is not a very widely adopted language.
The ability of Play to support both Java and Scala should form the unique selling point that keeps this framework ahead of the competition.

#####Users
The list of well-known users of Play is not very large. The framework could definetly benefit from the exposure that can come when well-known companies embrace a technology.
As long as there are a handfull of large users, Play could enjoy enough exposure to be able to sustain itself.
To keep the users with the framework as it develops, it is important for the users to be able to install newer versions of the framework without too much trouble.
We have noted that Play has extensive documentation on migrating to newer versions in the form of [Migration Guides](https://www.playframework.com/documentation/2.3.x/Migration23).
This way Play can be more easily upgraded to newer versions.
We also see that on the [roadmap](https://docs.google.com/document/d/11sVi1-REAIDFVHvwBrfRt1uXkBzROHQYgmcZNGJtDnA/pub) there are measures taken to make sure the upgrade to the next major version (3.0) can occur as smoothly as possible by providing a binary and source compatible in-between version (2.5) that will warn the developers of any deprecated API use.
Now developers have an additional step in between that their application will still work on, and allows developers to move on to the next version, without completely breaking their apps.

#####Concluding the evolution perspective
In the landscape of web-application frameworks Play doesn't form a huge mountain, but rather an odd-shaped hill that can prove to be very versatile and unique.
Its <abbr title="Unique Selling Point">USP</abbr> that it both supports Java and Scala can be a big win, 
but it can also hold the project back too much because effectively it needs to support twice as much.

###Metrics
_Research from D4 that has been finished._

	- Summary of the outcome of D4. This should characterise Play framework
	- Own experience while investigating in Play?
	
###Future plans/work of Play
	- Maybe this can be our second view that we have to include?

###Conclusion



