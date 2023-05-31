# 1º DAW - Programación :school: :computer:

UT7. Ficheros y Accesso BBDD

Repositorio con el código fuente para realizar las actividades de UT7.

## Java Spring Boot

API con Spring Framework, y la base de datos de MySQL ***EjPROut7***

### Instalación

> 
1. Clona este repositorio: `git clone https://github.com/cifp-villa-aguimes/pro-ut7-2023.git` o  
descarga [el archivo .zip](https://github.com/cifp-villa-aguimes/pro-ut7-2023/archive/refs/heads/main.zip "UT7 Source Code para actividades")
2. Importa/Abre el directorio del proyecto en tu IDE favorito
3. Ejecuta el script ***.bd/EjPROut7_Script.sql*** en tu herramienta o IDE de administración de bases de datos, o por consola
4. Revisa el archivo pom.xml para que se ajuste a tú version de JAVA  
`<properties>
	<java.version>20</java.version>
</properties>`

### Uso

> 
1. Inicia tu servidor de MySQL
2. Comprubea en `src/main/resources/application.properties` que los datos de puerto, nombre de la base de datos, usuario y contraseña corresponde con los de tu base de datos creadas en el script anterior:  
`spring.datasource.url= jdbc:mysql://localhost:puerto/nombre-BD?useSSL=false&serverTimezone=UTC`    
`spring.datasource.username= tu-usuario`  
`spring.datasource.password= tu-contraseña`
3. Ejecuta: `Clean and Build` del proyecto
4. Ejecuta la aplicación: `Run File` de tu archivo donde se encuentre tu ***main con SpringApplication.run***
5. Se crea un servidor con Apache Tomcat inicializado en el puerto 8080 
6. Comprubea que todo esta bien funcionando en `http://localhost:8080/api/v1/` de tu navegador
7. Muestra el mensaje: `Hello Api Spring Bootiful!`

