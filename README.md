# Prerequisite

* Download an example MP4 video (e.g.
  from https://file-examples.com/index.php/sample-video-files/sample-mp4-files/), place it
  as `video.mp4` in the root folder and run `bash setup.sh`.

# Build

```
(cd micronaut \
    && mvn clean install)
(cd spring \
    && mvn clean install)
```

# Run

Open `index.htm` in your Browser, preferably Chromium or Chrome (Firefox does not encounter the same
problems due to being more resilient to unexpected HTTP responses): The video is not being displayed
as no
server is running right now.

# Demo

## Micronaut

```
(cd micronaut \
    && mvn mn:run)
```

Reload `index.htm`: The video will be displayed and even selecting unbuffered parts of the video can
be selected.

## NGINX

```
(cd nginx && bash start.sh)
```

Reload `index.htm`: The video will be displayed and even selecting unbuffered parts of the video can
be selected.

```
(cd nginx && bash stop.sh)
```

Reload `index.htm` to make sure NGINX is stopped and the video is not being displayed anymore.

## Spring

```
(cd spring \
  && java -jar target/spring-boot-range-header-1.0-SNAPSHOT.jar)
```

Reload `index.htm`: The video will be displayed and even selecting unbuffered parts of the video can
be selected.