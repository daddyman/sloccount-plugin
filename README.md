SLOCCount Plugin
================

This [Jenkins CI](http://jenkins-ci.org/) plug-in generates the trend report for [SLOCCount](http://www.dwheeler.com/sloccount/), an open source program which counts the number of lines of codes in over 25 different languages, including C/C++, Ada, COBOL, Fortran, SQL, Ruby, Python, etc...

For more information, visit the wiki page <http://wiki.jenkins-ci.org/display/JENKINS/SLOCCount+Plugin>.

Building
--------

Run `docker build .` to run the build in a Docker.

At the end of the build you will see something like this:

```(bash)
[INFO] Skipping packaging of the test-jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:46 min
[INFO] Finished at: 2020-09-10T23:17:23Z
[INFO] ------------------------------------------------------------------------
Removing intermediate container 70dd4596c818
 ---> 5f862fa03d18
Successfully built 5f862fa03d18
```

The string in the last last line is the Docker image id that was built use that number to get the file.

- Create a docker container with the new image. The container id is printed out
`docker create 5f862fa03d18`
87b732c25d46bcbde8ad0bc556c780954461cd7a06b8f9dc443c6dc5f5667624

- Copy the file out of the container using the container id
`docker cp 87b732c25d46bcbde8ad0bc556c780954461cd7a06b8f9dc443c6dc5f5667624:/workspace/target/sloccount.hpi .`

- Delete the container
`docker rm 87b732c25d46bcbde8ad0bc556c780954461cd7a06b8f9dc443c6dc5f5667624`

After this the image can be deleted also:
`docker image rm 5f862fa03d18`

The sloccount.hpi can now be installed on the Jenkins server.
