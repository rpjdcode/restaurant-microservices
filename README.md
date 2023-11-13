# restaurant-microservices
Proyecto de Microservicios Spring Boot + Spring Cloud + Angular + despliegue con Ansible

**Tecnologías utilizadas en backend de Spring Boot 3**

**Spring Cloud Security-Keycloak: Autenticación, autorización y seguridad**

- Keycloak 22.0.5 : Servidor de autorización y autenticación. Implementa el estándar de seguridad OAuth2 y OpenID Connect.
- OAuth2: Protocolo de autorización para permitir el acceso a recursos por parte de un usuario en los microservicios, proporcionando tokens de acceso.
- OpenID Connect: Capa de identidad sobre OAuth2. Permite a Keycloak manejar la autorización, controlar el acceso a los recursos y proporcionar información de identidad sobre el usuario que es autenticado.
- Spring Security proporciona los mecanismos necesarios para el uso de las tecnologías anteriores y el control de la seguridad de los microservicios.

**Protección de credenciales/secretos**

- Vault 1.15.1: Herramienta de gestión de secretos y protección de información confidencial. Puede ser utilizado para almacenar propiedades de conexión, claves de API, tokens, etc.

![](.\images\vault_ejemplo.JPG)

**Servidor de registro  (Discovery/Registry Server)**

- Eureka: Servidor de descubrimiento y registro de aplicaciones cliente. Permite la comunicación de los microservicios.

![](.\images\eureka_ejemplo.JPG)

**Spring Cloud Config Server**

- Permite la gestión centralizada de archivos de configuración de los microservicios.

- Permite la actualización dinámica. Cuando se detecta un cambio en el repositorio, las aplicaciones pueden actualizar sus configuraciones sin la necesidad de ser reiniciadas.

- Las configuraciones son almacenadas en un repositorio GIT. El Config Server administra la configuración a los Config Client.

  ![](.\images\configuraciones.JPG)

**Spring Cloud Gateway**

- Enrutamiento dinámico: Permite especificar cómo se deben dirigir las peticiones a los microservicios según ciertos criterios como los encabezados HTTP, rutas, parámetros, etc.
- Proporciona un sistema de filtrado que permite realizar acciones antes o después de enviar una solicitud HTTP.
- Permite balanceo de carga en caso de contar con múltiples instancias de microservicios
- En el caso de este proyecto, el gateway es securizado gracias a Keycloak y a Spring Security, por lo que centraliza la configuración de seguridad de toda la arquitectura.

**ZipKin: Trazabilidad y monitorización de peticiones distribuidas**

Ayuda en el análisis de problemas en entornos distribuidos. Rastrea el flujo de una petición a medida que a traviesa varios servicios del entorno, por lo que permite diagnosticar problemas de rendimiento

![](.\images\zipkin_ejemplo.JPG)

**Spring Cloud Circuit Breaker: Resiliencia de microservicios**

El patrón Circuit Breaker evita la propagación de errores en los microservicios en caso de que se produjera uno, para mejorar la recuperación de la arquitectura y evitar daños mayores al propio fallo.

- Emplea un sistema de umbral de errores, de tal manera que si la tasa de errores supera el umbral, el Circuit Breaker cambia a estado abierto para evitar que se propague.
- Estado abierto: implica que el Circuit Breaker intercepta las solicitudes y las trata como si hubieran fallado, sin ejecutar la lógica detrás de la solicitud.
- Después de un cierto tiempo, Circuit Breaker pasa a un estado semiabierto y permite algunas solicitudes para comprobar el funcionamiento del sistema. Si las solicitudes tienen éxito, se cierra el Circuit Breaker, en caso contrario vuelve al estado abierto.