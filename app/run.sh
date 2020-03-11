#! /bin/bash

docker build . -t markdj/app:local

docker run -p 127.0.0.1:3000:3000/tcp --interactive --tty --rm markdj/app:local
