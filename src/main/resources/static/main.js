

document.getElementById('search').onkeyup = function () {

    var query = thi.value.toUpperCase();
    var table = document.getElementsByTagName('table')[0];
    var table_row = document.getElementsByTagName('tr');

    for(var i = 0; i < table_row.length; i++; {

       if(name_row) {

            var name = name.textContent || td.innerText;
            if(name_row.toUpperCase().indexOf(query) > - 1) {
            table_row[i].style.display='';
            }else{
            table_row[i].style.display='none'
            }
        }
    }
    console.log(query);
}