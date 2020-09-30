FROM ubuntu:latest

# install all the dependencies 
RUN apt-get -y update && apt-get -y install gcc gdb mingw-w64 git
WORKDIR /classes/cp264

# footer (to keep container running)
CMD ["tail","-f","/dev/null"]