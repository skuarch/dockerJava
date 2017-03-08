 
FROM ubuntu:16.04

COPY nginx.conf start.sh /tmp/

RUN apt-get update -y && \
    apt-get install nginx -y -f && \
    mv /tmp/nginx.conf /etc/nginx/nginx.conf && \
    /etc/init.d/nginx start && \
    mv /tmp/start.sh / && \
    chmod +x /start.sh

EXPOSE 8080

CMD ["/start.sh"]