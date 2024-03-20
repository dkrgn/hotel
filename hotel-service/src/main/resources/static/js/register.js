$(function() {

    $('#register-button').on('click', function() {
        let first_name = $('#first_name').val();
        let last_name = $('#last_name').val();
        let mobile_number = $('#mobile_number').val();
        let email = $('#email').val();
        let password = $('#password').val();
        let confirm = $('#confirm-password').val();

        let req = {
            "firstName" : first_name,
            "lastName" : last_name,
            "mobileNumber" : mobile_number,
            "email" : email,
            "password" : password};
        let json = JSON.stringify(req);
        if (password !== confirm) {
            alert("Passwords are not the same");
            location.reload();
        }
        register(json);
        return false;
    });


    let register = function(json) {
        $.ajax({
            "type": 'POST',
            "contentType": 'application/json; charset=UTF-8',
            "url": '/register.html/',
            "data": json,
            "dataType":'json',
            "cache" : false,
            "processData" : false,
            "success": function(response) {
                alert("User registered successfully!")
                window.location.href = "http://localhost:8080/login.html";
            },
            "error": function() {
                alert("Error registering user: email either is taken or invalid");
                location.reload();
            }
        });
    }
})