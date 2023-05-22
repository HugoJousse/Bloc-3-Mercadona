console.log("hello world!");
document.addEventListener("DOMContentLoaded", function(event) {

    const nameProduct = document.getElementById("nameProduct");
    const restrictName = document.getElementById("restrictName");

    const description = document.getElementById("description");
    const restrictDescription = document.getElementById("restrictDescription");

    const price = document.getElementById("price");
    const restrictPrice = document.getElementById("restrictPrice");

    const category = document.getElementById("category");
    const restrictCategory = document.getElementById("restrictCategory");

    const newCategory = document.getElementById("newCategory");
    const restrictNewCategory = document.getElementById("restrictNewCategory");

    const formFile = document.getElementById("formFile");
    const restrictImage = document.getElementById("restrictImage");
    var submitButton = document.getElementById("submitButton");
    const fileAccepted = /(\.jpg|\.jpeg|\.png)$/i;



    submitButton.disabled = true;
    var nameVerif = false;
    var fileVerif = false;
    var descriptionVerif = false;
    var priceVerif = false;
    var categoryVerif = true;
    var newCategoryVerif = true;
    console.log(category.value);
    if(category.value == ""){
        categoryVerif = false;
        newCategoryVerif = false
    }
    

    function verif() {
        if(nameVerif && fileVerif && descriptionVerif
            && priceVerif && categoryVerif && newCategoryVerif){
            submitButton.disabled = false;
        } else {
            submitButton.disabled = true;
        }
        
    }

    nameProduct.addEventListener("input", (e) =>{
        if(nameProduct.value.length < 3 || nameProduct.value.length > 25) {
            restrictName.style.color = "#bf3f3f";
            nameVerif = false;
            verif();
        }
        else {
            restrictName.style.color = "#52c471";
            nameVerif = true;
            verif();
        }
    });

    formFile.addEventListener("input", (e) =>{
        if(formFile.value == '' || !fileAccepted.exec(formFile.value)) {
            restrictImage.style.color = "#bf3f3f";
            formFile.value = '';
            fileVerif = false;
            verif();
        } else {
            restrictImage.style.color = "#52c471";
            fileVerif = true;
            verif();
        }
    });

    description.addEventListener("input", (e) =>{
        if(description.value.length < 10 || description.value.length > 255) {
            restrictDescription.style.color = "#bf3f3f";
            descriptionVerif = false;
            verif();
        }
        else {
            restrictDescription.style.color = "#52c471";
            descriptionVerif = true;
            verif();
        }
    });

    price.addEventListener("input", (e) =>{
        if(price.value == 0 || price.value < 0) {
            restrictPrice.style.color = "#bf3f3f";
            priceVerif = false;
            verif();
        }
        else {
            restrictPrice.style.color = "#52c471";
            priceVerif = true;
            verif();
        }
    });

    category.addEventListener('input', (e) =>{
        if(category.value == "" && newCategoryVerif == false) {
            restrictCategory.style.color = "#bf3f3f";
            categoryVerif = false;
            verif();
        }
        else {
            restrictCategory.style.color = "#52c471";
            categoryVerif = true;
            verif();
        }
        
    });

    newCategory.addEventListener('input', (e) => {
        if((newCategory.value.length < 3 || newCategory.value.length > 25) && category.value == "") {
            restrictNewCategory.style.color = "#bf3f3f";
            restrictCategory.style.color = "#bf3f3f";
            newCategoryVerif = false;
            categoryVerif = true;
            verif();
        }
        else if(newCategory.value.length < 3 || newCategory.value.length > 25){
            restrictNewCategory.style.color = "#bf3f3f";
            newCategoryVerif = false;
        } 
        else{
            restrictNewCategory.style.color = "#52c471";
            restrictCategory.style.color = "#52c471";
            newCategoryVerif = true;
            categoryVerif = true;
            verif();
        }
    });

});
    