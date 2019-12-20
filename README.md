# INTERSTELLAR WEATHER FORECAST

El ejemplo es un **API REST** desarrollado en **Java 8** con **Spring Boot 2.1**. El objetivo fue simular el comportamiento de un **Sistema Solar** en donde según la posición de los distintos planetas y del sol, se presenta determinada condición meteorológica. El archivo **SOLARSYSTEM.PDF** contiene los detalles del ejercicio resuelto.

### Se brindan las siguientes funcionalidades:

* **Predicción meteorológica para un día determinado** : Calculando la posición de cada planeta en forma individual y analizando como estos se disponen en el espacio y con respecto al sol, el sistema puede predecir en forma exacta la condición climática para un día  determinado.

* **Predicción meteorológica para un período de años determinado**: Calculando la posición de cada planeta en forma individual y analizando como estos se disponen en el espacio y con respecto al sol, el sistema puede predecir en forma exacta la condición climática para cada día de un período de años determinado.

### Dentro de las consideraciones que tuve para el desarrollo se encuentran las siguientes:

* **Separación en capas**. Para que la lógica quede organizada de forma correcta.
* **Mantener la lógica de negocio 100% en el Service**. Para que la llamada desde el Controller sea completamente transparente.
* **Buen tratamiento de errores**. Para que desde la capa de servicios se emita siempre un **BusinessException** y el Controller solamente vea este tipo de errores.
* **Buena asignación de responsabilidades, según principios GRASP**. Para asignarle a cada elemento aquellas responsabilidades que pueden llevar adelante, sin recurrir a comunicaciones complejas, tratando siempre que tengamos **alta cohesión** y **bajo acomplamiento**.
* **Aplicación de principios SOLID**. Para tratar principalmente de tener código **cohesivo** y con **bajo acoplamiento**. 
* **Usar un nombrado correcto en las URLs de los endpoints**. Para lograr que los Servicios Rest sean un **API REST**, y no solamente servicios. Teniendo en cuenta el **versionado del API REST**.
* **Documentación del API REST con Swagger**. Para poder documentar y probar los endpoints desde un único lugar, sin necesidad de recurrir a herramientas externas o a pruebas realizadas desde el navegador. 
* **Utilización de H2 en memoria como Database**. Para que el ejemplo sea mas portable y no requiera de un Postgres, MySql, etc.
* **Habilitación de auditoria de en Spring Boot**. Para que todas las clases del modelo incluyan Timestamp de creación y de actualización.
* **Utilización de VOs como elementos retornados por el API REST**. Para no exponer contenido innecesario de mis entidades y para no verme forzado a usar la notación **JsonIgnore** en las entidades del modelo.
* **Realización de Pruebas Unitarias como parte del proceso de Test Driven Development**. Para poder cumplir con las premisas de TDD fueron desarrolladas dos clases de Testing, que contienen las pruebas del core del negocio. Se trata de los dos métodos que condicionan el funcionamiento de toda la app.
* **Realización de Pruebas Integración**. Para poder verificar el comportamiento de cada método/endpoint del sistema y controllar que la integración entre componentes funciona de forma correcta.


### Notas:

* Se puede acceder a la consola de administración de H2 en: BASE_URL/h2 (En donde BASE_URL depende de donde este hospedada la app).
* Se puede acceder a la vista Swagger de documentación del API en: BASE_URL/swagger-ui.html (En donde BASE_URL depende de donde este hospedada la app).
* Con relación a la BD, fue usada una misma BD en memoria tanto para la app, como para la ejecución de las pruebas de integración. Si bien en un entorno real esto no podría realizarse (por una cuestión de independencia y de confiabilidad de los datos en las pruebas) pude hacerlo en este caso porque la app no altera nunca los datos, se limita solo a hacer consultas. De esta forma, también se simplifica un poco el desarrollo del ejercicio planteado.
* Soy consciente que en las pruebas de integración es conveniente usar siempre una BD real, ya que en definitiva, no se puede determinar una integración real si la base de datos se encuentra en memoria. Pero al igual que en el caso anterior, esto se hace para simplificar el desarrollo del ejemplo.
* En las Pruebas Unitarias no hay ingración con Spring Boot, dado que probar de esta forma complica el TDD. Esto sucede porque las pruebas atadas a Spring necesitan levantar el contexto de la app completo o parcial (para que podamos usar inyección de dependencias principalmente) y esta sobrecarga complica demasiado el uso de TDD como técnica de desarrollo. 
* La app se encuentra alojada en **HEROKU**.


## API REST ENDPOINTS

### OBTENER PREDICCIÓN PARA UN DÍA DETERMINADO

```sh
GET http://sleepy-coast-62087.herokuapp.com/api/v1/solarsystems/1/weathercondition?day=2  
```

`NOTA 1: En caso de no enviar el parámetro Day se obtiene un BAD REQUEST (400).`

`NOTA 2: Existe un único Sistema Solar cargado en la app, por eso el ID en este caso es 1.`


### OBTENER PREDICCIÓN PARA UN PERÍODO DE AÑOS DETERMINADO

```sh
GET http://sleepy-coast-62087.herokuapp.com/api/v1/solarsystems/1/weathercondition?years=2  
```

`NOTA 1: En caso de no enviar el parámetro Years se obtiene un BAD REQUEST (400).`

`NOTA 2: En caso de enviar una cantidad de años mayor a 10 se obtiene un BAD REQUEST (400).`

`NOTA 3: Existe un único Sistema Solar cargado en la app, por eso el ID en este caso es 1.`

`NOTA 4: El parámetro Years indica la cantidad de años del período a calcular. El máximo es 10 años.`


Licencia MIT
----
**Para uso totalmente libre**.
