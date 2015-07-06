---
layout: default
project: openra
title: OpenRA
chapter: false
---

# Contributions
This document contains the contributions made to the OpenRA project and some detail about the discussions involved.

## Pull Requests

* Issue [[7472](http://github.com/OpenRA/OpenRA/issues/7472)] - Occupancy of enemy APCs is visible
    Occupancy of enemy APCs where visible, this shouldn't be the case so we did a [PR](https://github.com/OpenRA/OpenRA/pull/7486) fixing this issue. One of the main contributers noticed that this could be solved much easier. After applying these changes the PR was merged after getting two upvotes.

* Issue [[7505](http://github.com/OpenRA/OpenRA/issues/7505)] - /all cheat code on activated cheats
    The /all cheat code will deactivate already activated cheats. This could be solved easily so we created a [PR](https://github.com/OpenRA/OpenRA/pull/7507#issuecomment-75372948)

* Issue [[7447](https://github.com/OpenRA/OpenRA/issues/7447)] - Underscore is not shown in lobby chat [PR](https://github.com/OpenRA/OpenRA/pull/7527)

* Issue [[7008](https://github.com/OpenRA/OpenRA/issues/7008)] - Resources spawn out of ore deposits. Whenever a unit or a vehicle is standing near an ore deposit, resources were unable to grow at the location of that unit. This issue is fixed in this [PR](https://github.com/OpenRA/OpenRA/pull/7555).

* Issue [[7505](https://github.com/OpenRA/OpenRA/pull/7521)] - More fixes on the /all cheat code as a tiny error slipped through. [PR](https://github.com/OpenRA/OpenRA/pull/7521).

* Multiple Issues [[7403](https://github.com/OpenRA/OpenRA/issues/7442)] and [[7403](https://github.com/OpenRA/OpenRA/issues/7403)] - In [PR](https://github.com/OpenRA/OpenRA/pull/7544) we proposed a fix for multiple issues. The first one is that a SAM site or turret that is being captured while shooting, will continue shooting at his target resulting in friendly fire.
We fixed this by clearing the targets of an actor on capture.
The second issue [[7442](https://github.com/OpenRA/OpenRA/issues/7442)] was much more complicated as it looks.
A SAM site would stay if it was sold during a low-power attacking state.
This problem wasn't always reproducible (see the comments) so it was much harder to convince the OpenRA team.
We found out that a SAM site was still in an attacking state while it was powered down.
This wasn't efficient at all but we didn't dug deeper into the problem because we proposed a simple fix that will terminate the attack activity when being disabled.
This seemed to be working at first hand but soon the developers of OpenRA found huge regression on turrets and SAM sites.
So we dug deeper into the activity mess and untangled it. We found that on sell, the current activities will be cancelled and the sell animation acitivity is started. After the animation, the real sell activity is started but in the meantime, another attack activity was queued resulting in the artifact of the issue. We fixed this by preventing activities to be scheduled after the sell-animation.
* Issue [[7508](https://github.com/OpenRA/OpenRA/issues/7508)] - Sell icon not disabled while building is being captured. This was a pretty straightforward fix as it was only the icon that was wrong. We did more as the same issue was present for powering down a building. We fixed this and proposed a [PR](https://github.com/OpenRA/OpenRA/issues/7508). 
* Issue [[7518](https://github.com/OpenRA/OpenRA/issues/7518)] - Changed chat messages from allied players to be marked as such. [[#7528](https://github.com/openra/openra/pull/7528)]
* Issue [[6858](https://github.com/OpenRA/OpenRA/issues/6858)] - This added the feature to see the Kills/Death ratio in the score tab. [PR](https://github.com/OpenRA/OpenRA/pull/7529).

## Pending Pull Requests
