from flask import Flask, jsonify, send_from_directory
from flask_cors import CORS
import os

app = Flask(__name__)
CORS(app)

VIDEO_FOLDER = 'videos'

# RUTA ABSOLUTA DEL ESCRITORIO A PRUEBA DE BALAS
app = Flask(__name__, static_folder='static')

# NOTA: Cambié la IP a 192.168.100.5 porque es la IP real de tu PC para el emulador.
# Si esta IP cambia, solo cámbiala en las líneas de abajo (imageUrl y url).

MOVIES = [
    {
        "id": 1, 
        "title": "Video 1: Aventura", 
        "description": "Un emocionante viaje por la naturaleza.", 
        "url": "http://192.168.100.5:5000/videos/video1.mp4", 
        "imageUrl": "http://192.168.100.5:5000/static/video1.png"
    },
    {
        "id": 2, 
        "title": "Video 2: Comedia", 
        "description": "Las situaciones más divertidas de la vida.", 
        "url": "http://192.168.100.5:5000/videos/video2.mp4", 
        "imageUrl": "http://192.168.100.5:5000/static/video2.png"
    },
    {
        "id": 3, 
        "title": "Video 3: Ciencia Ficción", 
        "description": "El futuro está a la vuelta de la esquina.", 
        "url": "http://192.168.100.5:5000/videos/video3.mp4", 
        "imageUrl": "http://192.168.100.5:5000/static/video3.png"
    }
]

@app.route('/api/movies', methods=['GET'])
def get_movies():
    return jsonify(MOVIES)

@app.route('/prueba')
def prueba():
    import os
    return f"""
    <h1>Diagnóstico</h1>
    <p>Ruta del script: {__file__}</p>
    <p>Carpeta actual (cwd): {os.getcwd()}</p>
    <p>Contenido de la carpeta actual: {os.listdir(os.getcwd())}</p>
    <hr>
    <p>Intentando leer la carpeta 'static'...</p>
    <p>Contenido de 'static': {os.listdir('static')}</p>
    """

@app.route('/videos/<path:filename>')
def serve_video(filename):
    return send_from_directory(VIDEO_FOLDER, filename)

# AGREGAMOS ESTA RUTA PARA QUE EL SERVIDOR PUEDA ENVIAR LAS IMÁGENES DE LA CARPETA STATIC
@app.route('/static/<path:filename>')
def serve_static(filename):
    return send_from_directory(STATIC_FOLDER, filename)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
