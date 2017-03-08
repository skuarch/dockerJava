# WORKSHOP JAVA AND DOCKER

This workshop is about to create a load balancer between 2 web applications, the load balancer is a simple round robin with nginx and the web apps are made in java with spring boot, the images are based in ubuntu 16.04

what I need for this workshop?
  - Spring Tool Suite
  - Docker-ce
  - Ubuntu image 16.04
  - Internet connection

# Docker network
Firts off all create one network in docker
```docker
docker network create load-balancer-net
```
# Create web app
We need a simple web app that shows the hostname and this is the code 
```java
package application;

import java.net.InetAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = {"/"})
    public ModelAndView main(ModelAndView mav) throws Exception {
        mav.setViewName("index");
        String hostname = InetAddress.getLocalHost().getHostName();
        mav.addObject("hostname", hostname);
        return mav;
    }
}
```
This app only has one simple controller that shows the hostname throught InetAddress class

# Create Dockerfile
Dockerfile is a file with no extension and it contains code like bash
```docker
FROM ubuntu:16.04
COPY target/web.jar /
RUN apt-get update -y && \
    apt-get install default-jdk -y -f
EXPOSE 8080
CMD ["java", "-jar", "web.jar"]
```

# Create image for web app
```docker
docker build --no-cache=true -t workshop/web .
```

# Create container for web app
With this simple command you can create and run the container for our web app
```docker
docker run --name web1 -t -i -d --net=load-balancer-net workshop/web
```

# Config load balancer
This is the configuration of the load balancer (/etc/nginx/nginx.conf)
```javascript
events { }

http {
  upstream nginx {
    server web1:8080;
    server web2:8080;    
  }

  server {
    listen 8080;    
    location / {
      proxy_pass http://nginx/;
    }
  }
}
```
# start.sh
We will use this file in order to run the load balancer
```sh
#!/bin/bash

/etc/init.d/nginx start
tail -100f /var/log/nginx/error.log
```
# Create image load balancer
```docker
docker build --no-cache=true --network load-balancer-net -t workshop/nginx .
```
# Create container load balancer
```docker
docker run --name nginx --net=load-balancer-net -i -t -d -p 8080:8080 workshop/nginx
```
### Highlights
>Did you see that the configuration of the load balancers has hostnames? thats right, inside of the docker network the comunnication is with the name of the host.
>Remember to uses mvn or your favorite IDE to build the project in java.