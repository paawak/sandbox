FROM nginx:1.15.7-alpine

RUN rm -rf /usr/share/nginx/html/*
COPY dist/* /usr/share/nginx/html/

COPY nginx.conf /etc/nginx/nginx.conf
