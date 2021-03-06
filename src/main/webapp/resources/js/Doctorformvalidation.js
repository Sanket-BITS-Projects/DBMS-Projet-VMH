function checkDoctorInputs() {

    checkname();
    checkpassword();
    checkemail();
    checkphone();
    checkdate();
    checkfees();
    checkaddress();

    if (checkname() && checkpassword() && checkemail() && checkphone() && checkdate() && checkfees() && checkaddress()) {
        let data = {};
        data.name = document.getElementById('name').value.trim();
        data.email = document.getElementById('email').value.trim();
        data.phone = document.getElementById('phone').value.trim();
        data.dob = document.getElementById('dob').value;
        data.address = document.getElementById('address').value.trim();
        data.specializationId = document.getElementById('specialization').value;
        data.doctorFees=document.getElementById('fees').value.trim();
        data.password = document.getElementById('confirmpassword').value;

        $.ajax({
            method: "POST",
            url: "signUpDoctor",
            data: data,
            dataType: 'json',
            async: false,
            success: function () {
                alert("Account has been created"); //will alert ok
                location.reload()
            },
            error: function (error) {
                alert(error.responseJSON.error);
            }
        });
    }
}


function logindoctor() {

    let data = {};
    data.email = document.getElementById('loginemail').value.trim();
    data.password = document.getElementById('pass').value.trim();

    $.ajax({
        method: "POST",
        url: "login",
        data: data,
        dataType: 'json',
        async: false,
        success: function (response) {
            if (response.result.person.roleId === 2) {
                window.location.replace("Doctorhomepage");
            } else {
                alert("Only Doctor can Login through this page");
                location.reload()
            }

        },
        error: function (error) {
            setErrorFor(document.getElementById('pass'), error.responseJSON.error);
        }
    });

}

function checkdate() {
    let date = document.getElementById('dob');
    if (date.value === "") {
        setErrorFor(date, 'please Select Date From Calendar ');
    } else {
        setSuccessFor(date);
        return true;
    }
}

function checkfees() {
    let fees = document.getElementById('fees');
    if (fees.value === "") {
        setErrorFor(fees, 'fees cannot be blank ');
    } else if (isfees(fees.value.trim())){
        setErrorFor(fees, 'fees should contain numbers');
    }
    else {
        setSuccessFor(fees);
        return true;
    }
}


function checkname() {
    let username = document.getElementById('name');
    if (username.value.trim().length === 0) {
        setErrorFor(username, 'Name cannot be Blank');
    } else if (Check_Name_Value(username.value.trim())) {
        setErrorFor(username, 'Enter Valid Name');
    } else {
        setSuccessFor(username);
        return true;
    }

}

function checkaddress() {
    let address = document.getElementById('address');
    if(address.value === ""){
        setErrorFor(address,'address cannot be Blank ');
    }else{
        setSuccessFor(address);
        return true;
    }
}


function checkpassword() {
    let password = document.getElementById('password');
    let confirmpassword = document.getElementById('confirmpassword');

    if (password.value.length === 0) {
        setErrorFor(password, 'Password cannot be Blank');
    } else if (password.value.length < 8) {
        setErrorFor(password, 'Password should have Min of 8 Characters');
    } else {
        setSuccessFor(password);
    }

    if (confirmpassword.value.length === 0) {
        setErrorFor(confirmpassword, 'Password cannot be Blank');
    } else if (confirmpassword.value.length < 8) {
        setErrorFor(confirmpassword, 'Password should have Min of 8 Characters');
    } else if (confirmpassword.value.length !== password.value.length) {
        setErrorFor(confirmpassword, 'Password Does Not Match');
    } else {
        setSuccessFor(confirmpassword);
        return true;
    }
}


function checkemail() {
    let email = document.getElementById('email');
    if (email.value.trim().length === 0) {
        setErrorFor(email, 'Email cannot be blank');
    } else if (!isEmail(email.value.trim())) {
        setErrorFor(email, 'Not a valid email');
    } else {
        setSuccessFor(email);
        return true;
    }
}


function checkphone() {
    let phone = document.getElementById('phone');
    if (phone.value.trim().length === 0) {
        setErrorFor(phone, 'Phone Number cannot be blank');
    } else if (isphone(phone.value.trim())) {
        setErrorFor(phone, 'Not a valid Phone Number');
    } else {
        setSuccessFor(phone);
        return true;
    }
}

function Check_Name_Value(inputvalue) {
    let letters = /^[A-Za-z ]+$/;
    return (!inputvalue.match(letters) || inputvalue.length < 4) ? true : false;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'group error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'group success';
}

function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function isphone(phone) {

    let numbers = /^[0-9]+$/;
    return (!phone.match(numbers) || phone.length !== 10) ? true : false;
}

function isfees(fees) {
    let numbers = /^[0-9]+$/;
    return (!fees.match(numbers)) ? true : false;
}
