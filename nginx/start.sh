#!/bin/bash
docker run -it --rm -d -p 12080:80 --name web -v $(pwd)/content:/usr/share/nginx/html nginx
