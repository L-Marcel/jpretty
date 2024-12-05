javac -cp "./lib/jline.jar:./lib/jcolor.jar" pretty/*.java pretty/**/*.java -d target
jar cf ./release/jpretty.jar -C target .
rm -rf target