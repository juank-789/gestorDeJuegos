***Gestor de Juegos***
* El proyecto "Gestor de Juegos" es una aplicación de escritorio desarrollada en Java utilizando JavaFX para la interfaz gráfica de usuario y MySQL como sistema de gestión de base de datos. Su objetivo principal es ofrecer una herramienta sencilla y eficaz para gestionar información relacionada con videojuegos desarrolladoras y comentarios del usuario

  ##  Diseño de Pantallas (JavaFX)

###  Pantalla Principal – Menú de Navegación
- Botones:
  - Gestionar Juegos
  - Gestionar Desarrolladoras
  - Gestionar Comentarios

---

###  Vista de Juegos
- Tabla de juegos.
- Botones:
  - Añadir
  - Editar
  - Eliminar
- Campos de búsqueda y filtros.

---

###  Formulario de Juego
- Campos:
  - Título
  - Género
  - Fecha
  - Desarrolladora (ComboBox)

---

###  Vista de Comentarios
- Lista de comentarios por juego.
- Botones:
  - Añadir
  - Editar
  - Eliminar

---

###  Vista de Desarrolladoras
- Tabla de desarrolladoras.
- Botones:
  - Añadir
  - Editar
  - Eliminar

---

##  Control de Integridad y Consistencia

- **Claves primarias** en todas las tablas (`AUTO_INCREMENT`).
- **Restricciones de unicidad**:  
  `UNIQUE(nombre)` en `desarrolladora`.
- **Integridad referencial**:
  - Si una **desarrolladora** se elimina, los juegos quedan con `NULL` en `id_desarrolladora`.
  - Si un **juego** se elimina, se eliminan también sus comentarios (eliminación en cascada).
- **Restricciones de formato**: tipos adecuados (`DATE`, `VARCHAR`, etc.).
- **Índices**: mejoran el rendimiento de búsquedas.

---

##  Puntos Fuertes del Diseño

### 1. Estructura clara y modular
- Aplicación del patrón **MVC (Modelo-Vista-Controlador)** para facilitar el mantenimiento.
- Separación entre modelos (entidades), vistas (`.fxml`) y controladores (lógica de presentación).

### 2. Uso de DAO (Data Access Object)
- Acceso a base de datos encapsulado.
- Código limpio y reutilizable.
- Permite cambiar fácilmente la fuente de datos (por ejemplo, de MySQL a SQLite).

### 3. Base de datos relacional bien diseñada
- Relaciones entre tablas con claves foráneas.
- Control de integridad mediante `FOREIGN KEY`, `UNIQUE` y `NOT NULL`.
- Soporte para **eliminación en cascada** y **actualizaciones coherentes**.

### 4. Interfaz JavaFX intuitiva
- Interfaz diseñada para facilitar la interacción del usuario.
- Formulario validado con campos seleccionables (ComboBox, DatePicker).
- Estilo coherente y diseño responsive.

### 5. Escalabilidad
- Preparada para añadir más funcionalidades sin reescribir el código base.
- Ejemplos: autenticación de usuarios, exportación de datos.

### 6. Buenas prácticas
- Uso de **Maven** para la gestión de dependencias.
- Proyecto versionado en **GitHub**.
- Código comentado y documentado.

---
