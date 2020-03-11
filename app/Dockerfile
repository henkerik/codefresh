FROM nginx:alpine

WORKDIR /usr/share/nginx/html

COPY nginx.conf /etc/nginx
COPY build/ /usr/share/nginx/html/app

EXPOSE 3000
