# 👑 Kingdom King - Prototipo de Juego Temático Vikingos

<p align="center">
  <img src="https://raw.githubusercontent.com/KevinFlow-ai/Kingdom_King/master/app/src/main/res/raw/vikingo_app_gift.gif" width="300" alt="Vikingo App Gift">
</p>


[![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Viking Theme](https://img.shields.io/badge/Theme-Viking-brown.svg)

## 📝 Descripción
**Kingdom King** es un prototipo inmersivo de un juego ambientado en la era vikinga. El proyecto se centra en ofrecer una experiencia de usuario fluida y temática, destacando por su sistema de interacción mediante **comandos de voz en español**. 

Desarrollado íntegramente en Java para Android, este proyecto es una muestra de diseño de interfaces (frontend) robustas y accesibles, utilizando tecnologías nativas para la navegación y la interacción.

---

## ✨ Características Principales
- 🛡️ **Interfaz Temática**: Diseño visual coherente con la estética vikinga.
- 🗣️ **Comandos de Voz**: Integración con Google Speech Recognition para controlar el juego sin manos.
- 📑 **Navegación Intuitiva**: Sistema de navegación por pestañas y ventanas emergentes (pop-ups) para una experiencia fluida.
- ⚡ **Frontend Optimizado**: Arquitectura ligera enfocada en la respuesta inmediata del usuario.



## 🛠️ Tecnologías Utilizadas
- **Lenguaje:** [Java](https://www.oracle.com/java/)
- **Interfaz de Usuario:** XML (Material Design)
- **Reconocimiento de Voz:** Google Speech Recognition API
- **Herramientas:** Android Studio, Gradle

---

## ⚙️ Instalación
Para probar este prototipo en tu entorno local, sigue estos pasos:

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/KevinFlow-ai/Kingdom_King.git
   ```
2. **Abre el proyecto** en Android Studio.
3. **Sincroniza Gradle** para descargar las dependencias necesarias.
4. **Ejecuta la aplicación** en un dispositivo físico o emulador.
   *Nota: Asegúrate de tener instalado el motor de reconocimiento de voz de Google.*

---

## 🎮 Guía de Uso
Al iniciar el juego, puedes navegar manualmente o utilizar los siguientes comandos de voz:
- 🗣️ **"Iniciar juego"**: Comienza una nueva partida.
- 🗣️ **"Ayuda"**: Despliega información sobre la mecánica del juego.
- 🗣️ **"Cerrar"**: Finaliza la sesión actual.

---

## 📂 Estructura del Proyecto
```text
Kingdom_King/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/kingdom_king/    # Lógica de actividades y voz
│   │   │   ├── res/                              # Recursos (Layouts, Drawables, Raw)
│   │   │   └── AndroidManifest.xml               # Configuración de permisos y actividades
└── README.md                                     # Documentación del proyecto
```

---

## 🗺️ Roadmap / Mejoras Futuras
- [ ] **V1.1**: Mejora y ampliación del diccionario de comandos de voz.
- [ ] **V1.2**: Integración de animaciones avanzadas para la interfaz.
- [ ] **V2.0**: Implementación de un backend para persistencia de datos y progreso.

---

## 🤝 Contribuciones
¡Las contribuciones son bienvenidas! Si tienes ideas para mejorar la jugabilidad o la interfaz:
1. Haz un **Fork** del proyecto.
2. Crea una nueva **Rama** (`git checkout -b feature/MejoraIncreible`).
3. Realiza tus cambios y haz **Commit** (`git commit -m 'Añadir MejoraIncreible'`).
4. Haz **Push** a la rama (`git push origin feature/MejoraIncreible`).
5. Abre un **Pull Request**.

---




*Desarrollado con pasión por la temática nórdica y el desarrollo Android.*
