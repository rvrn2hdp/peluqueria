$("#repeat").on("change", () => {
    
    if ($("#clave").val() != $("#repeat").val() && !$(`#repeat`).hasClass("is-invalid")) {
        $(`#repeat`).addClass("is-invalid");
        $(`#error-repeat`).addClass("invalid-feedback").append("<span class='error-span'>Las contraseñas no coinciden</span>");
    } else {
        $(`#repeat`).removeClass("is-invalid");
        $(`#error-repeat`).removeClass("invalid-feedback").find(".error-span").remove();
        }
});

const limpiar = () => {

    // limpiar form de cargas anteriores...
    $("span").closest(".error-span").remove();

    // Remover border rojos si los hay...
    $(".is-invalid").removeClass("is-invalid");

}

const borrarTextos = () => {

    // Limpiar textos...
    $("#form :input").each(function() {
        $(this).val("");
    });

}

// Botones CRUD:

// Botón Crear...
$("#btn-crear").on("click", () => {
    limpiar();
    borrarTextos();

    $("#titleModal").text("Crear usuario");
    
    // Abrir bs5 dialog...
    let tareaModal = new bootstrap.Modal(document.getElementById("usuariosModal"), { backdrop: "static", keyboard: false });

    tareaModal.show();
});

// Botón Editar...
$("#btn-editar").on("click", function () {

    let id = Number($(this).attr("id"));

    $.ajax({
        
        method: "GET",
        url: `/editar/${id}`,

        beforeSend: function () {
            
            limpiar();

            $("#titleModal").text("Editar usuario");

            // Abrir bs5 dialog...
            let tareaModal = new bootstrap.Modal(document.getElementById("usuariosModal"), { backdrop: "static", keyboard: false });

            tareaModal.show();
        },

        success: function (data) {
            
            $("#id").val(data.id);
            $("#nombre").val(data.nombre);
            $("#notas").val(data.notas);
            $("#genero").val(data.genero);
            $("#direccion").val(data.direccion);
            $("#telefono").val(data.telefono);
            $("#email").val(data.email);
            $("#clave").val(data.clave);
            $("#repeat").val(data.clave);
            $(`#rol-${data.rol}`).prop("checked", true);
        },

        error: function (xhr) {
            
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'No se pudo comunicar con el servidor!',
                footer: 'Verifique su conexión a internet o contacte con el administrador.'
            });
        }
    });
});


// Hacer el submit del usuario:

$('#user-form').submit(function (event) {
    event.preventDefault();

    var formData = new FormData(this);

    $.ajax({
        url: "/guardar",
        type: "POST",
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
        success: function () {

            // Cerrar bs5 dialog...
            $("#usuariosModal").modal("hide");

            Swal.fire({
                title: "Listo!",
                text: "Se ha guardado el usuario!",
                icon: "success",
                confirmButtonText: "Ok"

            }).then(() => {
                location.reload();        
            });
        },

        statusCode: {
            422: function (xhr) {
                
                console.log("Status Error: " + xhr.status);
                var errors = $.parseJSON(xhr.responseText);

                $.each(errors, function (key, value) {
                    
                    $(`#${key}`).addClass("is-invalid");
                    $(`#${key}-error`).addClass("invalid-feedback").append("<span class='error-span'>" + value + "</span>");
                });

                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Verifique los datos introducidos!',
                    footer: 'Verifique los mensajes de error'
                });
            },

            404: function (xhr) {
                
                console.log("Status Error: " + xhr.status);

                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'No se pudo comunicar con el servidor!',
                    footer: 'Verifique su conexión a internet o contacte con el administrador.'
                });
            }
        }
    });

});

// Borrar:

$(".borrar").on("click", function () {
    
    let id = Number($(this).attr("id"));

    if (id !== 1) {

        Swal.fire({
            title: '¡Atención!',
            text: '¿Estas seguro de borrar este usuario?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, borrar'
        }).then((result) => {
          
            if (result.value) {

                $.ajax({
                    url: `/borrar/${id}`,
                    type: "GET",
                }).done(function (resp) {
                    location.reload();
                });
            }
        });
    } else {
        
        Swal.fire(
            "Ops!",
            "No puedes borrar el usuario 'Administrador'!",
            "warning"
        );
    }
});