//JavaScript for the dynamic search box
//use javascript once the DOM is loaded
window.addEventListener("DOMContentLoaded", function () {

    //store the search box in a var
    let searchBox = document.getElementById("editSearchBox");

    //store all tr in a var array
    let tableRows = document.getElementsByClassName("teamData"); //[]

    //add event to the search box
    searchBox.addEventListener("input", function () {
        //callback function since its only being executed once
        //iterate thru the array and check if it contains the search
        for (i = 0; i < tableRows.length; i++) {
            //store the table data in a variable for easier reading
            let teamNameData = tableRows[i].getElementsByTagName("td")[1].textContent.toLowerCase(); //team name is index 1 of the td
            let continentData = tableRows[i].getElementsByTagName("td")[2].textContent.toLowerCase(); //team name is index 2 of the td

            //check if the current tableRow td[1] or [2] which is team name or continent starts with the user search query
            if ((teamNameData.startsWith(searchBox.value.toString().toLowerCase())) ||
                continentData.startsWith(searchBox.value.toString().toLowerCase())) {
                tableRows[i].style.display = ""; //leave it visible
            } else {
                //if not, display=none
                tableRows[i].style.display = "none";
            }


        }
    });


});

