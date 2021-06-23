FROM nginx:1.19.0-alpine
RUN mkdir /app
COPY ./frontend /app
COPY nginx.conf /etc/nginx/nginx.conf