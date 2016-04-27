echo =====================================
echo  Automatic development preparation
echo =====================================
echo This state:.$state
echo Project Version:.$projectversion
echo ----- List All 1 Start
ls -R
echo ----- List All 1 End
if [ $state = "develop"]
then
	echo --- Development Version
	rename target/ev3dev-lang-java-$projectversion-jar-with-dependencies.jar target/ev3dev-lang-java-$projectversion-$state-B$TRAVIS_BUILD_NUMBER.jar
	rename target/ev3dev-lang-java-$projectversion-jar-with-dependencies.jar target/ev3dev-lang-java-$projectversion-jar-with-dependencies-$state-B$TRAVIS_BUILD_NUMBER.jar
fi
if [ $state = "unstable"]
then
	echo --- Unstable Version
	rename target/ev3dev-lang-java-$projectversion.jar target/ev3dev-lang-java-$projectversion-$state.jar
fi
echo ----- List All 2 Start
ls -R
echo ----- List All 2 End
git config --global user.email "builds@travis-ci.com"
git config --global user.name "Travis CI"
export GIT_TAG=$projectversion-$state-B$TRAVIS_BUILD_NUMBER
git tag $GIT_TAG -a -m "Generated tag from TravisCI for $state build $TRAVIS_BUILD_NUMBER"
git push -q https://$GITPERM@github.com/mob41/ev3dev-lang-java --tags
echo =====================================
echo  Deploy Preparation done.
echo =====================================
exit 0