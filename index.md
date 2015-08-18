---
layout: default
title: Delft Students on Software Architecture -- DESOSA 2015
overview: true
creator: 
- role: Editor
- text: Arie van Deursen and Rogier Slag (editors)
date: August 2015, Version 1.1-RC1
---

# Delft Students on Software Architecture

## DESOSA 2015


**[Arie van Deursen] and [Rogier Slag].**<br/>
*Delft University of Technology, The Netherlands, July 2, 2015*

[arie van deursen]: http://avandeursen.com
[rogier slag]: https://github.com/rogierslag



_Delft Students on Software Architecture_ is a collection of architectural descriptions of open source software systems written by students from Delft University of Technology during a [master-level course][in4315] taking place in the spring of 2015.

[in4315]: http://www.studiegids.tudelft.nl/a101_displayCourse.do?course_id=31246

At the start of the course, teams of 3-4 students could adopt a project of choice on GitHub. The projects selected had to be sufficiently complex and actively maintained (one or more pull requests merged per day).

During a 10 week period, the students spent one third of their time on this course,and engaged with these systems in order to understand and describe their software architecture.

Inspired by Brown and Wilsons' [Architecture of Open Source Applications][aosa], we decided to organize each description as a chapter, resulting in the present online book.

## Recurring Themes

The chapters share several common themes, which are based on smaller assignments the students conducted as part of the course. These themes cover different architectural 'theories' as available on the web or in textbooks. The course used  Rozanski and Woods' [Software Systems Architecture][rw], and therefore several of their architectural [viewpoints] and [perspectives] recur.

[viewpoints]: http://www.viewpoints-and-perspectives.info/home/viewpoints/
[perspectives]: http://www.viewpoints-and-perspectives.info/home/perspectives/

The first theme is outward looking, focusing on the use of the system. Thus, many of the chapters contain an explicit [stakeholder analysis], as well as a description of the [context] in which the systems operate. These were based on available online documentation, as well as on an analysis of open and recently closed issues for these systems.

[context]: http://www.viewpoints-and-perspectives.info/home/viewpoints/context/
[stakeholder analysis]: http://www.mindtools.com/pages/article/newPPM_07.htm

A second theme involves the [development viewpoint][development], covering modules, layers, components, and their inter-dependencies. Furthermore, it addresses integration and testing processes used for the system under analysis.

[development]: http://www.viewpoints-and-perspectives.info/home/viewpoints/

A third recurring theme is _variability management_. Many of today's software systems are highly configurable. In such systems, different features can be enabled or disabled, at compile time or at run time. Using techniques from the field of [product line engineering][fospl], several of the chapters provide feature-based variability models of the systems under study.

A fourth theme is [metrics-based evaluation of software architectures][bouwers]. Using such metrics architects can discuss  (desired) quality attributes (performance, scaleability, maintainability, …) of a system quantitatively. Therefore various chapters discuss metrics and in some cases actual measurements tailored towards the systems under analysis.


## First-Hand Experience

Last but not least, the chapters are also based on the student's experience in actually contributing to the systems described. As part of the course over 75 pull requests to the projects under study were made, including refactorings
([Jekyll 3545](https://github.com/jekyll/jekyll/pull/3545),
  [Docker 11350](https://github.com/docker/docker/pull/11350),
  [Docker 11323](https://github.com/docker/docker/pull/11323),
  [Syncany 391](https://github.com/syncany/syncany/pull/391)), bug fixes
([Diaspora 5714](https://github.com/diaspora/diaspora/pull/5714),
 [OpenRA 7486](https://github.com/OpenRA/OpenRA/pull/7486), [OpenRA 7544](https://github.com/OpenRA/OpenRA/pull/7544),
 [Kodi 6570](https://github.com/xbmc/xbmc/pull/6570)), and helpful documentation such as a [Play Framework screen cast](https://www.youtube.com/watch?v=47TqFH8MXhk).

Through these contributions the students often interacted with lead developers and architects of the systems under study, gaining first-hand experience with the architectural trade-offs made in these systems.

## Feedback

While we worked hard on the chapters to the best of our abilities, there will be plenty of omissions and inaccuracies.
We value your feedback on any of the material in the book. For your feedback, you can:

* Open an issue on our [GitHub repository for this book][dswa.io].
* Offer an improvement to a chapter by posting a pull request on our [GitHub repository][dswa.io].
* Contact @[delftswa][dswa.tw] on Twitter.
* Send an email to Arie.vanDeursen at tudelft.nl.

[dswa.io]: https://github.com/delftswa/delftswa.github.io
[dswa.tw]: https://twitter.com/delftswa


## Acknowledgments

We would like to thank:

* The additional members of the teaching team: Eric Bouwers, Nicolas Dintzner, Annibale Panichella, and Felienne Hermans.
* Michael de Jong and Alex Nederlof who were instrumental in the earlier editions of this course.
* All open source developers who helpfully responded to the student's questions and contributions.




## Further Reading

1. Arie van Deursen, Alex Nederlof, and Eric Bouwers. Teaching Software Architecture: with GitHub! [avandeursen.com][teaching-swa], December 2013.
2. Nick Rozanski and Eoin Woods. [Software Systems Architecture: Working with Stakeholders Using Viewpoints and Perspectives][rw]. Addison-Wesley, 2012, 2nd edition.
3. Sven Apel, Don Batory, Christian Kästner, Gunter Saake. [Feature-Oriented Software Product Lines: Concepts and Implementation][fospl]. Springer-Verlag, 2013.
4. Eric Bouwers. [Metric-based Evaluation of Implemented Software Architectures][bouwers]. PhD Thesis, Delft University of Technology, 2013.
5. Amy Brown and Greg Wilson (editors). [The Architecture of Open Source Applications][aosa]. Volumes 1-2, 2012.

[teaching-swa]: http://avandeursen.com/2013/12/30/teaching-software-architecture-with-github/
[rw]: http://www.viewpoints-and-perspectives.info/
[aosa]: http://aosabook.org/
[fospl]: http://link.springer.com/book/10.1007/978-3-642-37521-7
[bouwers]: http://repository.tudelft.nl/view/ir/uuid:6b65c5f5-398c-4a41-8806-31c638b1891c/


## Copyright and License

The copyright of the chapters is with the authors of the chapters. All chapters are licensed under the [Creative Commons Attribution 4.0 International License][cc-by].
Reuse of the material is permitted, provided adequate attribution (such as a link to chapter on the [DESOSA book site][desosa]) is included.

Cover image: Inntel Hotel, Zaandam, [Wikimedia](https://commons.wikimedia.org/wiki/File:1211_Zaandam_051.JPG#/media/File:1211_Zaandam_051.JPG).

[![Creative Commons](cc-by.png)][cc-by]

[cc-by]: http://creativecommons.org/licenses/by/4.0/
[desosa]: http://delftswa.github.io/
