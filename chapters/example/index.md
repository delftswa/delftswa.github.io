---
layout: default
project: a
title: Example chapter
chapter: true
---

# Place your chapter here _with appealing title_

**Author1, Author2, Author3, Author4**<br/>
*Delft University of Technology*

## How it works

Generally you can simply copy your content from your team repository.
If you want to use the `git filter` as proposed by @hermanbanken, please see [that issue](https://github.com/delftswa2014/course-info-2015/issues/124)


__Some remarks__:

- Only refer to images in your images subfolder, not your repo (that one is not public)
- Relative links
- Name your file `index.md`
- Don't forget the Jekyll headings just as in this file!
- Run the `test_links.rb` script in the repo root like `ruby test_links.rb <YOURPROJECTNAME>` and check if all links are ok

## Submitting

It's simple really!

1. Create a fork of the project
1. You can place your content as described here
1. Commit, push, and submit a pull request
1. I (@rogierslag) will check it and merge
1. Within some minutes your new site should be available on delftswa.github.io

You can use vagrant to start a virtual machine with the site generator.

1. Install Vagrant & Virtualbox
1. Run `vagrant up`
1. Run `vagrant ssh`, then `cd /vagrant`, finally `jekyll serve --force_polling`

The site is now available on http://localhost:4000

## Questions?

Just raise an issue and tag me!

## Got it

Cool!

![high five](images/got-it.gif)
