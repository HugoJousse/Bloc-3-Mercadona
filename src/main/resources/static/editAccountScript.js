console.log("Hello world! It's a test!");

document.addEventListener("DOMContentLoaded", function(event) {
    const pseudo = document.getElementById("pseudo");
    const restrictPseudo = document.getElementById("restrictPseudo");
    const password = document.getElementById("password");
    const restrictPass = document.getElementById("restrictPass");
    const passConf = document.getElementById("passConf");
    const restrictPassConf = document.getElementById("restrictPassConf");
    const form = document.getElementById("form");
    var submitButton = document.getElementById("submitButton");

    var pseudoVerif = true;
    var passVerif = false;
    var passConfVerif = false;
    submitButton.disabled = true;
    function Verif() {
    if(pseudoVerif == true && passVerif == true && passConfVerif == true){
        submitButton.disabled = false;
        }
        else { submitButton.disabled = true; } 
    }


    pseudo.addEventListener('input', (e) =>{
        if(pseudo.value.length < 5 || pseudo.value.length > 25) {
            restrictPseudo.style.color = "#bf3f3f";
            pseudoVerif = false;     
        } else {
            restrictPseudo.style.color = "#52c471";
            pseudoVerif = true;
        }
        Verif();
    });

    password.addEventListener('input', (e) =>{
        if(password.value.length < 10 || password.value.length > 25) {
            restrictPass.style.color = "#bf3f3f";
            passVerif = false;     
        } else {
            restrictPass.style.color = "#52c471";
            passVerif = true;
        }
        Verif();
    });

    passConf.addEventListener('input', (e) =>{
        if(password.value !== passConf.value) {
            restrictPassConf.style.color = "#bf3f3f";
            passConfVerif = false;     
        } else {
            restrictPassConf.style.color = "#52c471";
            passConfVerif = true;
        }
        Verif();
    });
});

