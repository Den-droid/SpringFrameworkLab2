function makeRequest(url) {
    let httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        setContent(httpRequest);
    };
    httpRequest.open('GET', url, true);
    httpRequest.setRequestHeader("Access-Control-Allow-Origin", "*");
    httpRequest.send(null);

}

function setContent(httpRequest) {
    if (httpRequest.readyState === 4) {
        if (httpRequest.status === 200) {
            let obj = JSON.parse(httpRequest.responseText);
            let isBooked = document.getElementsByName("isBooked")[0];
            isBooked.checked = obj["isBooked"];
            isBooked.disabled = false;

            let price = document.getElementsByName("price")[0];
            price.value = obj["price"];
            price.disabled = false;
        }
    }
}

function rowNumberChanged(e) {
    let seatNumber = document.getElementsByName("seatNumber")[0];
    let movieSessionId = window.location.pathname.split("/")[3];
    let url = "http://" + window.location.host + "/movieSessions/" + movieSessionId +
        "/seats/" + e.target.value + "/" + seatNumber.value;
    makeRequest(url);
}

function seatNumberChanged(e) {
    let rowNumber = document.getElementsByName("rowNumber")[0];
    let movieSessionId = window.location.pathname.split("/")[3];
    let url = "http://" + window.location.host + "/movieSessions/" + movieSessionId +
        "/seats/" + rowNumber.value + "/" + e.target.value;
    makeRequest(url);
}

document.getElementsByName("rowNumber")[0].addEventListener('input', rowNumberChanged);
document.getElementsByName("seatNumber")[0].addEventListener('input', seatNumberChanged);