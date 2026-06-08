# FDP-Market_sc403

Plataforma transaccional C2C (Client-to-Client) para activos digitales de *Old School RuneScape* (OSRS). Este sistema utiliza un flujo Escrow para mitigar fraudes, y ofrece herramientas para auditar transacciones y resolución de disputas.

## Características Principales

* **Sistema Escrow:** Retención de fondos neutral hasta confirmación de entrega por ambas partes.
* **Auditoría:** Chat exclusivo por orden y sub-menús para carga de evidencia multimedia.
* **Gestión Multinivel:** Roles diferenciados para Admin, Moderador, Vendedor y Sub-vendedor.
* **Entregas:** Bifurcación operativa entre entrega digital automática (serial) y entrega manual.

## 1. Estructura del Repositorio

* `/src`: Código fuente del backend desarrollado en Java & Spring Boot.
* `/sql`: Scripts de base de datos (`schema.sql` para esquema y `data.sql` para datos predefinidos).
* `/docs/latex`: Documentación técnica y reporte académico. Contiene el diagrama Entidad-Relación, flujos de navegación y el backlog de **Historias de Usuario**.
* `/docs/diagramas`: Diagramas editables del sistema (formato SVG).

## 2. Requisitos del Sistema

* **Entorno Java:** JDK 21
* **IDE Recomendado:** Apache NetBeans 20+
* **Gestor de Dependencias:** Maven 3.8+
* **Base de Datos:** MySQL Server
* **Documentación:** Distribución local de LaTeX (MiKTeX)
* **Control de Versiones:** Cuenta de GitHub con un *Personal Access Token (Classic)*

## 3. Clonación e Inicialización del Repositorio

Antes de ejecutar el proyecto, debes obtener el código fuente.

### 3.1. Opción A: Terminal (Recomendado)

Ejecuta los siguientes comandos desde tu consola de comandos:

    git clone https://github.com/Fidelitas-FDP/FDP-Market_sc403.git
    cd FDP-Market_sc403

### 3.2. Opción B: NetBeans (GUI) **(<u>No recomendada</u>)**

1. Ve al menú superior: **Team** (Equipo) -> **Git** -> **Clone** (Clonar).
2. Pega la URL del repositorio e ingresa tus credenciales (usa tu Token como contraseña).
3. **Nota Aclaratoria (<u>Anidamiento</u>):** En el paso de seleccionar la carpeta destino, asegúrate de no crear una subcarpeta redundante para evitar rutas duplicadas como `Proyectos/FDP-Market_sc403/FDP-Market_sc403`.

## 4. Guía de Instalación y Ejecución

### 4.1. Configuración de Base de Datos

1. Accede a tu consola de MySQL o Workbench instalado.
2. Ejecuta el script `/sql/schema.sql` para generar la estructura del esquema y las tablas.
3. Ejecuta el script `/sql/data.sql` para cargar los catálogos y usuarios predefinidos.
4. Edita el archivo `src/main/resources/application.properties` con tus credenciales locales de base de datos.

### 4.2. Compilación y Ejecución del Proyecto

#### Opción A: Mediante terminal

Es estrictamente necesario ejecutar estos comandos desde el directorio raíz del proyecto:

    mvn clean install
    mvn spring-boot:run

*(El servidor iniciará en: `http://localhost:8080`)*

#### Opción B: Mediante NetBeans IDE

1. Haz clic derecho sobre el proyecto `FDP-Market_sc403` y selecciona **Clean and Build** (Limpiar y Construir).
2. Haz clic derecho nuevamente y selecciona **Run** (Ejecutar).

## 5. Diagramas del Sistema

### 5.1. Diagrama Entidad-Relación
![Diagrama DER](docs/diagramas/diagrama_bd.svg)

### 5.2. Mapa de Navegación
![Mapa de Navegación](docs/diagramas/mapa_navegacion.svg)

## 6. Flujo de Trabajo y Colaboración (Git)

### 6.1. Configuración de Acceso (<u>Token GitHub</u>)

Para sincronizar el código mediante el IDE, es obligatorio generar un token de autenticación:
1. En GitHub, navega a **Settings** -> **Developer settings** -> **Personal access tokens** -> **Tokens (classic)**.
2. Genera un nuevo token con permisos de repositorio (`repo`).
3. Utiliza este token como contraseña al realizar operaciones de *Push/Pull* desde NetBeans.

### 6.2. Operaciones de Sincronización Básica

**Nota Aclaratoria (<u>Ramas y Convenciones</u>):** 
1. Históricamente Git usaba la rama `master`, pero el estándar actual es `main`. Asegúrate de que tu entorno apunte a `main`.
2. **Nunca hagas commits directos sobre `main`**. Todo desarrollo debe aislarse en una rama nueva. 
3. Se recomienda usar la convención de prefijos para nombrar ramas: `feature/nombre-del-modulo` (para nuevas funciones) o `bugfix/nombre-del-error` (para correcciones).

**NetBeans (GUI):**
* Utiliza el submenú **Team** (Equipo) -> **Git** para las acciones de **Add** (Añadir), **Commit** (Confirmar) y **Remote -> Push** (Empujar).

**Terminal:**

    git add .
    git commit -m "Descripción clara del cambio"
    git push origin feature/nombre-del-modulo

### 6.3. Ciclo de Pull Requests

Para mantener la integridad del código, el equipo debe apegarse a este ciclo:

1. Actualiza tu entorno local desde la rama principal:

       git checkout main
       git pull origin main

2. Crea una rama de desarrollo para el módulo asignado:

       git checkout -b feature/nombre-del-modulo

3. Sube los cambios al servidor remoto tras realizar tus commits:

       git push origin feature/nombre-del-modulo

4. Abre un *Pull Request* hacia `main` en la interfaz web de GitHub, etiquetando a los revisores correspondientes mediante `@usuario_github` para su validación e integración.

## 7. Equipo de Desarrollo

* [**@Fidelitas-FDP**](https://github.com/Fidelitas-FDP) - Scrum Master / Lead Backend (<u>Módulo 3</u>: Transacciones y Flujo Escrow)
* [**@Andresitoo76**](https://github.com/Andresitoo76) - Developer Backend (<u>Módulo 1</u>: Autenticación y Perfiles | <u>Módulo 4</u>: Auditoría)
* [**@Douglas1404a**](https://github.com/Douglas1404a) - Developer Fullstack (<u>Módulo 2</u>: Catálogo y Carrito)
* [**@sdgselva**](https://github.com/sdgselva) - QA & Developer (<u>Módulo 5</u>: Disputas | <u>Módulo 6</u>: Soporte | Documentación LaTeX)

---
*Proyecto universitario desarrollado para el curso SC-403: Desarrollo de Aplicaciones Web y Patrones.*
