# ZipcodeWebService
Web service donde se obtiene la información de un codigo postal (zipcode) a partir del código postal utilizando Spring Boot, DB H2 y Spring Batch.

## Objetivo del proyecto
El presente proyecto fue realizado como parte del proceso de selección para el puesto de Developer Backend Java.

## Requisitos
Para consumir el servicio de manera local se requiere tener instalado Java (JDK 11) y Spring Boot en su versión 2.2.6.

## Descripción

### Funcionalidad
El servicio permite buscar la información de cualquier código postal en México a partir de una REST API endpoint la cual se consume con una petición HTTP __GET /zip-codes/{zip_code}__ y recibiendo un JSON con la siguiente estructura:

```json
{
  "zip_code": "06140",
  "locality": "CIUDAD DE MEXICO",
  "federal_entity": "CIUDAD DE MEXICO",
  "settlements": [
    {
      "name": "CONDESA",
      "zone_type": "Urbano",
      "settlement_type": "Colonia"
    }
  ],
  "municipality": "CUAUHTEMOC"
}
```

Una llamada de ejemplo de manera local seria _http://localhost:9090/zip-codes/23040_

Así también con __GET /list_all__ permite obtener igualmente una respuesta json con todos los registros postales hasta el momento.

En caso de no encontrar el código solicitado regresa una respuesta HTTP 404 Not Found.

### Herramientas utilizadas
El proyecto fue realizado con el framework de Java Spring Boot, el cual es una implementación de Spring Framework que cuenta con herramientas que facilitan en gran medida el desarrollo de microservicios web en Java. El proyecto fue desarrollado utilizando Java JDK 11 y SpringBoot 2.2.6 (Versión estable hasta el momento). Así tambien como medio de optimización de busqueda (mayor velocidad y comodidad para el usuario) se decidió utilizar una base de datos de tipo H2 la cual tiene la caracteristica que se almacena en memoria y se crea al iniciar el servicio y se destruye una vez se termine la ejecución.

### Estructura utilizada
El proyecto fue dividido en paquetes de tal manera que no genere dependencias y la funcionalidad interna de cada paquete sea transparente para los otros paquetes. Consta en total 6 paquetes que se describen a continuación:
* __Main__: Paquete donde se encuentra la clase main y donde se encuentra el script que ejecuta el servicio.
```java
public static void main(String[] args) {
		SpringApplication.run(WebserviceZipcodesGendraApplication.class, args);
	}
 ```
 
* __Config__: Paquete que se encarga de manejar los archivos de configuración para Spring Batch que a su vez se encarga de la lectura y población de la base de datos a partir del archivo _CPMexico.txt_ que se encuentra en la carpeta _resources_.
```java
    @Bean
    protected Step step1(ItemReader<Zipcode> reader,
    	      ItemWriter<Zipcode> writer) {
    	        return steps
    	        		.get("step1")
    	        		.<Zipcode, Zipcode> chunk(10)
    	        		.reader(reader)
    	        		.writer(writer)
    	        		.build();
    }
    
    @Bean(name = "populateZipcodes")
    public Job job(@Qualifier("step1") Step step1) {
    	
		return jobs
				  .get("createZipCodesJob")
				  .incrementer(new RunIdIncrementer())
				  .flow(step1)
				  .end()
				  .build();
    }
 ```

* __Controllers__: Paquete que contiene los controladores del servicio los cuales son los que manejan la petición HTTP (GET) desde el endpoint.
```java
	@GetMapping(value="/zipcode/{z_code}")
	public ZipcodeInfo getZipcodeInfo(@PathVariable String z_code) {
		try {
			return zipcodeService.findByZipcode(z_code);
		} catch(ZipcodeNotFoundException ex) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND, "Zipcode not found", ex);
		}
		
	}
 ```
* __Models__ : Paquete que contiene los paquetes encargados del manejo de la obtención de datos.
  * __Entity__: Paquete contenedor de los modelos utilizados para el manejo de los datos dentro del servicio manejandolos como entidades.
  * __Repository__: Paquete que contiene el interface repositorio del servicio que hereda de _CrudRepository<T, Id>_ que es un interfaz de Spring que permite implementar metodos personalizados de busqueda asi como utilizar metodos preimplementados por el framework.
  
  ```java
  public interface ZipcodeRepository extends CrudRepository<Zipcode, Long>{
	
	List<Zipcode> findByzCode(String zCode);
	
  }
  ```
  * __Service__: Archivo que contiene la implementación de los metodos de busqueda declarados en Repository.
  
  ```java
  	@Override
	@Transactional(readOnly = true)
	public ZipcodeInfo findByZipcode(String z_code) throws ZipcodeNotFoundException {
		List<Zipcode> response = zipcodeRepository.findByzCode(z_code);
		if(response.isEmpty()) {
			throw new ZipcodeNotFoundException();
		}
		return new ZipcodeInfo(response);
	}
  ```
