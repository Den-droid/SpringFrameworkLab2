function makeRequest(url, e) {
    let httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        setContent(httpRequest, e);
    };
    httpRequest.open('GET', url, true);
    httpRequest.setRequestHeader("Access-Control-Allow-Origin", "*");
    httpRequest.send(null);

}

function setContent(httpRequest, e) {
    if (httpRequest.readyState === 4) {
        if (httpRequest.status === 200) {
            let obj = JSON.parse(httpRequest.responseText);
            let form = e.target.parentNode.parentNode;
            console.log(form.getElementsByTagName("span"));

            if (form.getElementsByTagName("span").length === 0) {
                let br = document.createElement("br");
                let spanPrice = document.createElement("span");
                spanPrice.textContent = "Price: " + obj["price"];
                form.appendChild(br);
                form.appendChild(spanPrice);

                if (obj["isBooked"]) {
                    let spanBooked = document.createElement("span");
                    spanBooked.textContent = "This place is booked!";
                    spanBooked.style.marginLeft = "5px";
                    form.appendChild(spanBooked);
                } else {
                    let inputBook = document.createElement("input");
                    inputBook.setAttribute("type", "submit");
                    inputBook.setAttribute("value", "Book");
                    inputBook.style.marginLeft = "5px";
                    form.appendChild(inputBook);
                }
            } else {
                form.removeChild(form.lastElementChild.previousElementSibling);
                form.removeChild(form.lastElementChild);
                if (obj["isBooked"]) {
                    let spanBooked = document.createElement("span");
                    spanBooked.textContent = "This place is booked!";
                    spanBooked.style.marginLeft = "5px";
                    form.appendChild(spanBooked);
                } else {
                    let inputBook = document.createElement("input");
                    inputBook.setAttribute("type", "submit");
                    inputBook.setAttribute("value", "Book");
                    inputBook.style.marginLeft = "5px";
                    form.appendChild(inputBook);
                }
            }
            let brLast = document.createElement("br");
            form.appendChild(brLast);
        }
    }
}

function rowNumberChanged(e) {
    let seatNumber = document.getElementsByName("seat")[0];
    let movieSessionId = e.target.parentNode.parentNode.firstElementChild.value;
    let url = "http://" + window.location.host + "/movieSessions/" + movieSessionId +
        "/seats/" + e.target.value + "/" + seatNumber.value;
    makeRequest(url, e);
}

function seatNumberChanged(e) {
    let rowNumber = document.getElementsByName("row")[0];
    let movieSessionId = e.target.parentNode.parentNode.firstElementChild.value;
    let url = "http://" + window.location.host + "/movieSessions/" + movieSessionId +
        "/seats/" + rowNumber.value + "/" + e.target.value;
    makeRequest(url, e);
}

let rows = document.getElementsByName("row");
for (let i = 0; i < rows.length; i++) {
    rows[i].addEventListener('input', rowNumberChanged, false);
}

let seats = document.getElementsByName("seat");
for (let i = 0; i < seats.length; i++) {
    seats[i].addEventListener('input', seatNumberChanged, false);
}