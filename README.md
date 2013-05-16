## Day of Datomic

The Day of Datomic project is a collection of samples and tutorials
for learning [Datomic](http://datomic.com) at a Clojure REPL.


## Getting Started

Start a REPL:

If you have a network connection, you can use
[Leiningen](http://leiningen.org) to launch a REPL.

On Unix-like systems, including Mac OS X:

    ./lein repl

On Windows:

    lein.bat repl


Alternately, if you do not have a network connection, you should
be able to launch a Clojure REPL by running one of the scripts
provided in this directory:

On Unix-like systems, including Mac OS X:

    ./clojure.sh

On Windows:

    clojure.cmd    


# Tutorials

Work through some of the tutorials in the tutorial directory,
evaluating each form at the REPL. You might start with:

* hello_world.clj
* social_news.clj
* provenance.clj
* datalog_on_defrecords.clj
* data_functions.clj
* binding.clj
* graph.clj

You can also run all of the examples and see their output by running:

    lein run -m datomic.samples.tutorials


## Study the Samples

As or after you work through the tutorial, you may want to also study
the helper functions in src/datomic/samples.


## Questions, Feedback?

For specific feedback on the tutorials, please create an
[issue](https://github.com/Datomic/day-of-datomic/issues).

For questions about Datomic, try the [public mailing
list](http://groups.google.com/group/datomic).


## License

EPL. See epl-v10.html at the project root.
