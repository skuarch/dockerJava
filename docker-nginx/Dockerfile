 
FROM ubuntu:16.04

COPY nginx.conf /tmp

RUN apt-get update -y && \
    apt-get install nginx -y -f && \
    mv /tmp/nginx.conf /etc/nginx/nginx.conf

EXPOSE 8080

CMD ["/etc/init.d/nginx", "start"]