
setInterval(getData, 5000);

function getData(){
    const xhr = new XMLHttpRequest(),
        method = "GET",
        url = "http://localhost/rolstoelen.php";
    xhr.open(method, url, true);
    xhr.onreadystatechange = function () {

        if(xhr.readyState === XMLHttpRequest.DONE) {
            var status = xhr.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                var json = JSON.parse(xhr.responseText);
                    for (let i = 0; i < json.length; i++) {
                        update(json[i]);
                    }
            }
        }
    };
    xhr.send();
}

function update(data){

    for (let i = 0; i < document.getElementsByClassName("rolstoellocatie").length; i++) {
        var element = document.getElementsByClassName("rolstoellocatie")[i];
        if (data.id == element.dataset.id){
            element.innerHTML = "Locatie: " + data.locatie;
            return;
        }
    }
}
