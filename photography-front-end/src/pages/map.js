import MapView from '../components/MapView'
import React from 'react'
import 'https://api.mapbox.com/mapbox-gl-js/v2.8.1/mapbox-gl.css'

var mapboxgl = require('mapbox-gl/dist/mapbox-gl.js');

mapboxgl.accessToken = 'pk.eyJ1IjoiZGFraWV0aGtpbnNvbiIsImEiOiJjbHZwejlqejIwN2lpMmttb3M1bGE4ZGZpIn0.c468nv8TY5EjqQ3ZZCihXQ';
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11'
});