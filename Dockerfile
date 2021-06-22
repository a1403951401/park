FROM node:lts-alpine as node
LABEL auto-update-builder=node

COPY ./park-web /app

WORKDIR /app

RUN npm config set registry https://registry.npm.taobao.org && \
    yarn config set registry https://registry.npm.taobao.org && \
    yarn install && \
    yarn build
 
FROM nginx:stable-alpine

COPY --from=node /app/dist /app

COPY /nginx.conf /etc/nginx/nginx.conf
