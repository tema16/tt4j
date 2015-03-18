The following [Groovy](http://groovy.codehaus.org) script prints some information about a given TreeTagger model, e.g. version and dictionary sizes. Additionally, it extracts the dictionaries and tagset from the model and saves them to disk.

```
#!/usr/bin/env groovy
/**
 * SYNOPSIS: ttmodelinfo.groovy [file] [encoding]
 *
 * EXAMPLE:  ./ttmodelinfo.groovy english-par-linux-3.2.bin.gz ISO-8859-1
 *
 * Gets information from a TreeTagger model. Dumps dictionaries to disk.
 */
@Grab(group='org.annolab.tt4j', module='org.annolab.tt4j', version='1.1.2')
import static org.annolab.tt4j.TreeTaggerModelUtil.*;

def modelFile = new File(args[0]);
def model = readModel(modelFile, args[1]);

def tags = new File(modelFile.getPath()+".tags")
def lemmas = new File(modelFile.getPath()+".lemmas")
def tokens = new File(modelFile.getPath()+".tokens")

println "== Header =="
println "  Source     : ${model.source}"
println "  Version    : ${model.version}"
println "  Encoding   : ${args[1]}"
println "== Dictionaries =="
println "  Tags       : ${model.tags.size()}"
println "  Lemmas     : ${model.lemmas.size()}"
println "  Tokens     : ${model.tokens.size()}"

println "Writing dictionaries may take a while..."

println "... writing tags ..."
tags.delete()
model.tags.each { tags << "${it}\n" } 

println "... writing lemmas ..."
lemmas.delete()
model.lemmas.each { lemmas << "${it}\n" } 

println "... writing tokens ..."
tokens.delete()
model.tokens.each { tokens << "${it}\n" } 

println "... done."
```