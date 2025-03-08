.PHONY: build test lint
build: test lint
	./gradlew build

test:
	./gradlew test

lint:
	./gradlew check

run-dist:
	./gradlew installDist
	./build/install/app/bin/app

report:
	./gradlew jacocoTestReport

clean:
	./gradlew clean