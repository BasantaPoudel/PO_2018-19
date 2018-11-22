all:
	(cd project; make $(MFLAGS) all)

clean:
	(cd project; make $(MFLAGS) clean)

install:
	(cd project; make $(MFLAGS) install)
