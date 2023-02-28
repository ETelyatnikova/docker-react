FROM node:19-alpine AS builder
WORKDIR /app
COPY /my-app/package.json .
RUN npm install
COPY /my-app/. .
RUN npm run build 

FROM nginx
COPY --from=builder /app/build /usr/share/nginx/html