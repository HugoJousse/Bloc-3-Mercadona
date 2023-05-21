document.addEventListener("DOMContentLoaded", function(event) {

    var tableRows = document.getElementById("productsTable");
    var filter = document.getElementById("filter");

    var filterSelected = ""; 

    filter.addEventListener("change", (e) =>{

        filterSelected = filter.options[filter.selectedIndex].text;
        var filterSelectedIndex = filter.options[filter.selectedIndex].value;

        for(var ii = 1; ii< tableRows.rows.length; ii++) {
            var actualRow = tableRows.rows[ii];
            for(var yy = 0; yy< actualRow.cells.length; yy++) {
                var actualCellContent = actualRow.cells[yy].childNodes[0].innerHTML;
                if(yy == 3){
                   if(actualCellContent != filterSelected && filterSelectedIndex != "all") {
                    actualRow.classList.add("hidden");
                   } else if(actualCellContent == filterSelected || filterSelectedIndex == "all") {
                    actualRow.classList.remove("hidden");
                   }
                }
                
                
                
            }
        }
    })
});