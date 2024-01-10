let table = new Tabulator("#tablaUsuarios", {
    responsive : true,
    order: [[ 4, "asc" ]],
    lengthMenu: [ [10, 25, 50, -1], [10, 25, 50, "All"] ],
    languajes: {
        "language": {
            "url": "https://cdn.jsdelivr.net/npm/tabulator-tables@4.9.3/dist/js/tabulator_es-ES.json"
        }
    }
})