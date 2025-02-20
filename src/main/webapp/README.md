# ClinicApp-1.0-SNAPSHOT

## 📌 Descripción del Proyecto

ClinicApp es una aplicación web desarrollada en Java utilizando Servlets y JSP para gestionar una clínica. La aplicación sigue el patrón de diseño **Modelo-Vista-Controlador (MVC)** y utiliza el modelo **DAO (Data Access Object)** para la persistencia de datos.

### 🚀 **Características principales:**

- Inicio de sesión y autenticación de usuarios.
- Gestión de pacientes, médicos y citas médicas.
- Administración de usuarios y roles con diferentes niveles de acceso.
- Uso de **MySQL** como base de datos.
- Diseño responsivo con **Bootstrap y CSS personalizado**.

## 📁 **Estructura del Proyecto**

```
ClinicApp-1.0-SNAPSHOT/
├── pom.xml  (Maven - gestión de dependencias)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mycompany/
│   │   │           └── clinicapp/
│   │   │               ├── model/
│   │   │               │   ├── Usuario.java
│   │   │               │   ├── Rol.java
│   │   │               │   ├── Paciente.java
│   │   │               │   ├── HistoriaClinica.java
│   │   │               │   └── Cita.java
│   │   │               ├── dao/
│   │   │               │   ├── UsuarioDAO.java
│   │   │               │   ├── RolDAO.java
│   │   │               │   ├── PacienteDAO.java
│   │   │               │   ├── HistoriaClinicaDAO.java
│   │   │               │   └── CitaDAO.java
│   │   │               ├── dao/
│   │   │               │   └── impl/
│   │   │               │       ├── UsuarioDAOImpl.java
│   │   │               │       ├── RolDAOImpl.java
│   │   │               │       ├── PacienteDAOImpl.java
│   │   │               │       ├── HistoriaClinicaDAOImpl.java
│   │   │               │       └── CitaDAOImpl.java
│   │   │               ├── controller/
│   │   │               │   ├── UsuarioServlet.java
│   │   │               │   ├── RolServlet.java
│   │   │               │   ├── PacienteServlet.java
│   │   │               │   ├── HistoriaClinicaServlet.java
│   │   │               │   ├── CitaServlet.java
│   │   │               │   ├── LoginServlet.java
│   │   │               │   └── LogoutServlet.java
│   │   │               ├── filter/
│   │   │               │   └── AuthenticationFilter.java
│   │   │               └── util/
│   │   │                   └── ConexionBD.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── JSP/
│   │       │   ├── login.jsp
│   │       │   ├── home.jsp
│   │       │   ├── usuario.jsp
│   │       │   ├── usuarioForm.jsp
│   │       │   ├── rol.jsp
│   │       │   ├── rolForm.jsp
│   │       │   ├── paciente.jsp
│   │       │   ├── pacienteForm.jsp
│   │       │   ├── historiaClinica.jsp
│   │       │   ├── historiaClinicaForm.jsp
│   │       │   ├── cita.jsp
│   │       │   └── citaForm.jsp
│   │       ├── CSS/ (Hojas de estilo CSS)
│   │       ├── images/ (Imágenes y recursos gráficos)
│   │       ├── index.html (Página de inicio)
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── mycompany/
│   │               └── clinicapp/
│   │                   └── test/
│   │                       └── [clases de prueba unitaria]
└── [archivos adicionales]
```

## 📜 **Licencia**

Este proyecto está bajo la Licencia MIT.

---
📢 **¡Listo para usar!** Si tienes dudas o necesitas soporte, abre un issue en el repositorio. 🚀

**Creado por Wladymir Escobar - gwescobar@espe.edu.ec**

**Colaboradores:**
- Margarita Fajardo - mcfajardo1@espe.edu.ec
- Sandy Mariño - sjmarino1@espe.edu.ec
- Alex Quishpe - apquishpe@espe.edu.ec

**Grupo 5 - Desarrollo Web Integral TEC**


