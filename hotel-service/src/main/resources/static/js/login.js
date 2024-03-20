$(function() {

    $("#login-button").on('click', function() {
        let email = $("#email").val();
        let password = $("#password").val();
        validate(email, password);
        return false;
    });

    let validate = function(email, password) {
        if (email.length > 0 && password.length > 0) {
            let req = {'email' : email, 'password' : password};
            $.ajax({
                "type": 'POST',
                "contentType": 'application/json; charset=UTF-8',
                "url": '/login.html/',
                "data": JSON.stringify(req),
                "dataType":'json',
                "cache" : false,
                "processData" : false,
                "success": function(response) {
                    console.log(response);
                    let token = "valid";
                    sessionStorage.setItem("token", token);
                    window.location.href = "http://localhost:8080/";
                },
                "error": function(jqXHR, status, err) {
                    alert("Error with login");
                }
            });
        }
    }
});