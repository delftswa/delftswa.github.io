# Place your chapter here

## How it works

Generally you can simply copy your content from your team repository.
If you want to use the `git filter` as proposed by @hermanbanken, please see that issue

__Some remarks__:

- Only refer to images in your images subfolder, not your repo (that one is not public)
- Relative links

## Submitting

It's simple really!

1. Create a fork of the project
1. You can place your content as described here
1. Commit, push, and submit a pull request
1. I (@rogierslag) will check it and merge
1. Within some minutes your new site should be available on delftswa2014.github.io

You can use vagrant to start a virtual machine with the site generator.

1. Install Vagrant & Virtualbox
1. Run `vagrant up`
1. Run `vagrant ssh`, then `cd /vagrant`, finally `jekyll serve --force_polling`

The site is now available on http://localhost:4000

## Questions?

Just raise an issue and tag me!

