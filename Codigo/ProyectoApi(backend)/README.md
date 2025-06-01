
Gestion de Catering

========================

La finalidad del sistema es gestionar un servicio de catering 
proporcionando informacion sobre todos los pedidos, cliente y trabajadores implicados.
Esta facilita la gestion de un restaurante y sus pedidos

=========================================================================================================

La aplicacion fue programada en Java 17, usando el framework Spring Boot 3, 
se uso H2 como base de datos para simplificar la aplicacion, las dependencias 
se usaron las de Maven, la autentificacion se hizo con Spring Security y JWT, y el 
frontend con Thymeleaf y Bootstrap.

La documentacion esta hecha con Swagger y Postman

De IDE recomiendo usar Intelij y para ejecutarlo haremos los siguientes pasos:
Para ejecutarlo lo primero tenemos 2 opciones, descargar el proyecto y ejecutarlo directamente, o  crear el .jar para ello haremos lo siguiente:
Abriremos el proyecto y a la derecha clickaremos la “M” y en el nombre del proyecto le 
daremos a install y nos llevaremos el .jar, despues tenemos otras dos opciones o nos llevamos 
la carpeta data o en caso de no llevarnosnola con el proyecto en la consola de h2 al ejecutarlo 
introducimos lo que hay en data2.sql

====================================================================================================
Estructura de la Base de Datos:

clientes: Contiene información sobre los clientes, incluyendo nombre, correo electrónico, teléfono, dirección y DNI.

personal: Almacena datos del personal, con roles específicos como 'Cocinero' o 'Conductor'.

alergenos: Lista los alérgenos que pueden estar presentes en los artículos del menú.

articulos_menu: Contiene información sobre los artículos del menú, incluyendo nombre, descripción y precio.

pedidos: Registra los pedidos realizados por los clientes y su estado actual.

articulos_pedido: Relaciona los artículos del menú con los pedidos y su cantidad.

articulos_menu_alergenos: Relaciona los alérgenos con los artículos del menú.

===================================================================================================

Para importar la colección en Postman y realizar pruebas con la API:

Abre Postman.

Ve a la pestaña "Import" en la parte superior izquierda.

Selecciona "Upload Files" y elige el archivo JSON de la colección.

Una vez importado, revisa los endpoints y configura las variables necesarias (como la URL base o el token de autenticación).

Realiza las peticiones a la API según sea necesario.