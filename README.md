spuštění pomocí `./mvnw spring-boot:run` v rootu projektu

frontend běží na `http://localhost:8080/index.html`

ověření v postmanu pomocí POST na `http://localhost:8080/api/shape/full`

![tvar](https://github.com/user-attachments/assets/6513e40c-3313-4d61-9465-acd1c8f19028)


pro aplikaci použit template https://github.com/danvega/full-stack-java-vue
# Spring Boot + VueJS Template

This is a template for anyone looking to use Spring Boot + Vue.JS in the same project. 

## Development

- Run the Spring Boot application which will run on port 8080
- Run the Vue application (/src/frontend)  which will run on port 3000
- All calls to `/api/**` are proxied to 8080 thanks to `vue.config.js`

## Packaging 

When you run `mvn clean package` the frontend Vue application will build in the `dist` directory. 
The Maven plugin `maven-resources-plugin` will copy the contents of the build directory into `/target/static/classes`

## Deployment

The `system.properties` is a file I used for Heroku to set the Java version and deploy the application. 

https://spring-boot-vue-template.herokuapp.com/



