# Memoria del Proyecto

## Introducción
El trabajo consisten e nrealizar un generador de páginas estáticas sacando la informacion de un archivo Json, y usando plantillas para con formato HTML.


### 1. Estructura de Datos
- Tenemos las clases Equipo, Jugador y ListaEquipos.
  En estas ponemos la información que extraemos del JSON usando Jackson.

- El Schema.  
 Este define los atributos obligatorios para equipos, 

### 2. Generación de Archivos HTML
- Plantillas.
  Con las plantillas mostramos la informacion, y se procesan mediante Tymeleaf. Tambien se encargan de generar los enlaces dinámicos a las páginas.

### 3. Generación de RSS
- El RSS contiene informacion de los equipos y de los jugadores

## Implementación Técnica

### 1. Carga de Datos
Con cargarDatosDesdeJSON convertimos el JSON en objetos Java usando Jackson

### 2. Motor de Plantillas Thymeleaf
Con ClassLoaderTemplateResolver cargamos las plantillas que hayamos puesto para las páginas

### 3. Salida Generada
Como archivos de salida tendremos: 
- El archivo index.html
- Las páginas de cada equipo 
- El archivo RSS
- 
### 4. Manejo de Errores
Con un try catch simple capturamos las excepciones de lectura y escritura

## Conclusión
El proyecto saca la información de un Json y mediante Jackson y Tymeleaf creamos unas páginas que mediante plantillas HTMl y archivos css ponen la información en la disposicion que queramos