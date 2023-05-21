document.addEventListener("DOMContentLoaded", function(event) {
    const productName = document.getElementById("productName");
    const restrictName = document.getElementById("restrictName");

    const productImage = document.getElementById("productImage");
    const restrictImage = document.getElementById("restrictImage");

    const productDescription = document.getElementById("productDescription");
    const restrictDescription = document.getElementById("restrictDescription");

    const productPrice = document.getElementById("productPrice");
    const restrictPrice = document.getElementById("restrictPrice");

    const productDiscount = document.getElementById("productDiscount");
    const restrictDiscount = document.getElementById("restrictDiscount");

    const discountStart = document.getElementById("discountStart");
    const discountEnd = document.getElementById("discountEnd");

    const newCategory = document.getElementById("newCategory");
    const restrictCategory = document.getElementById("restrictCategory");

    const submitButton = document.getElementById("submitButton");
    
    

    var nameVerif = true;
    var descriptionVerif = true;
    var categoryVerif = true;
    var discountVerif = true;
    var discountStartVerif = true;
    var discountEndVerif = true;


    function Verif() {
        if(nameVerif && descriptionVerif && categoryVerif && discountVerif
            && discountStartVerif && discountEndVerif) {
        submitButton.disabled = false;
        } else {
            submitButton.disabled = true;
        }
    }
    

    if(productDiscount.value != 0) {
        discountStart.disabled = false;
        discountEnd.disabled = false;
        discountStartVerif = true;
        discountEndVerif = true;
    } else { 
        discountStart.disabled = true;
        discountStart.value = null;
        discountEnd.disabled = true;
        discountEnd.value = null;
    }

    productName.addEventListener('input', (e) =>{
        if(productName.value.length < 3 || productName.value.length > 25) {
            restrictName.style.color = "#bf3f3f";
            nameVerif = false;   
            Verif()  
        } else {
            restrictName.style.color = "#52c471";
            nameVerif = true;
            Verif()
        }
    });
    
    productDescription.addEventListener('input', (e) =>{
        if(productDescription.value.length < 10 || productDescription.value.length > 255) {
            restrictDescription.style.color = "#bf3f3f";
            descriptionVerif = false;   
            Verif()  
        } else {
            restrictDescription.style.color = "#52c471";
            descriptionVerif = true;
            Verif()
        }
    });
    
    productDiscount.addEventListener('input', (e) =>{
        if((productDiscount.value > 100 || productDiscount.value < 0) && productDiscount.value != 0 ) {
            restrictDiscount.style.color = "#bf3f3f";
            discountVerif = false;
            discountStart.disabled = true;
            discountEnd.disabled = true;  
            Verif()
        } else {
            restrictDiscount.style.color = "#52c471";
            discountVerif = true;
            discountStart.disabled = false;
            discountEnd.disabled = false;  
            Verif()
        }
        if(productVerif = true && productDiscount.value != 0) {
            discountStart.value ? discountStartVerif = true : discountStartVerif = false;
            discountEnd.value ?  discountEndVerif = true : discountEndVerif = false;

            discountStart.addEventListener('input', (e) =>{
                discountStart.value ? discountStartVerif = true : discountStartVerif = false;
                Verif()
            })
            discountEnd.addEventListener('input', (e) =>{
                discountEnd.value ?  discountEndVerif = true : discountEndVerif = false;
                Verif()
            })
        }
    })

    newCategory.addEventListener('input', (e) =>{
        if((newCategory.value.length < 3 || newCategory.value.length > 25) && newCategory.value.length != 0) {
                restrictCategory.style.color = "#bf3f3f";
            categoryVerif = false;
            Verif()          
        } else {
            restrictCategory.style.color = "#52c471";
            categoryVerif = true;
            Verif()
        }
    });
});