# https://github.com/IDPF/epubcheck/releases/tag/v3.0.1
EPUBCHECKJAR=$(HOME)/pkgs/epubcheck-3.0.1/epubcheck-3.0.1.jar

DOC=desosa2015

CHAPTERS=syncany openra playframework angulardart docker diaspora vagrant jekyll joomla kodi

CHAPTERS_MD=$(patsubst %,chapters/%/index.md,$(CHAPTERS))

EXTRACTDIR=zzz-epub-extract
TARGET_DIR=target

EPUB_OUT=$(TARGET_DIR)/$(DOC).epub

all:
	$(MAKE) clean img epub 

epub:
	mkdir -p $(TARGET_DIR)
	pandoc \
	 --smart \
	 --toc \
	 --number-sections \
	 --toc-depth=2 \
	 --epub-cover-image=css/cover.jpg \
	 --output=$(EPUB_OUT) \
	 index.md \
	 $(CHAPTERS_MD)

img:
	mkdir -p images
	$(foreach chapter, $(CHAPTERS), cp -r -i chapters/$(chapter)/images/* images/;)

check:
	java -jar $(EPUBCHECKJAR) $(EPUB_OUT)

# An epub file is just a zip file with html content.
# Provide target for unzipping to help fixing incorrect epubs.
unzip:
	rm -rf $(TMPDIR)
	mkdir -p $(TMPDIR)
	cp $(DOC).epub $(TMPDIR)/$(DOC).zip
	cd $(TMPDIR); unzip $(DOC).zip

clean:
	rm -rf images $(EXTRACTDIR) $(TARGET_DIR)
