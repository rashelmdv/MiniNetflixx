# MiniNetflixx - Aplicación de Streaming para Android TV

## 📖 Descripción del proyecto
MiniNetflixx es una aplicación nativa desarrollada en **Kotlin** para dispositivos **Android TV**.
Su objetivo es simular el funcionamiento de una plataforma de streaming como Netflix, permitiendo a los usuarios navegar por un catálogo de videos, visualizar las carátulas (imágenes) de cada contenido y reproducir videos en streaming a través de una conexión real con un servidor backend.

## 🏗️ Arquitectura utilizada
El proyecto sigue una arquitectura **Cliente-Servidor**, separando claramente la capa de presentación, la capa de datos y el backend.

*   **Frontend:** Aplicación Android TV (Kotlin + XML).
*   **Backend:** Servidor local desarrollado en Python con Flask.
*   **Comunicación:** La aplicación se comunica con el servidor mediante peticiones HTTP utilizando la librería **Retrofit**.
*   **Patrón de diseño:** La interfaz de usuario utiliza el patrón de **MVP (Model-View-Presenter)** proporcionado por las librerías de Android Leanback, separando la lógica de negocio de las vistas.

## 💻 Tecnologías utilizadas

### Backend (Servidor):
*   **Python 3.11+**
*   **Flask:** Framework web para crear la API.
*   **Flask-CORS:** Para permitir la comunicación segura entre el servidor y el emulador/App.
*   **JSON:** Formato de intercambio de datos.

### Frontend (Aplicación Android):
*   **Lenguaje:** Kotlin.
*   **IDE:** Android Studio.
*   **Librería UI:** `androidx.leanback` (componentes especializados para Android TV).
*   **Consumo de API:** Retrofit 2 + Gson para el parseo de JSON.
*   **Carga de imágenes:** Glide.
*   **Reproducción de video:** ExoPlayer.
*   **Multithreading:** Corrutinas de Kotlin para llamadas asíncronas a la red.

## 🚀 Cómo ejecutar el servidor

1.  Asegúrate de tener Python 3 instalado en tu PC.
2.  Abre una terminal (CMD, PowerShell o la terminal de Android Studio).
3.  Navega hasta el escritorio (o la carpeta) donde se encuentra el archivo `server.py`.
4.  Ejecuta el siguiente comando:
    ```bash
    python server.py