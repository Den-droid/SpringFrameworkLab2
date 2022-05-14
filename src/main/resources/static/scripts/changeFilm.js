function deleteActor(e) {
    e.preventDefault();
    let actorSpan = e.target.parentNode;
    let actorsLabel = e.target.parentNode.parentNode;
    actorsLabel.removeChild(actorSpan);
}

function addActor(e) {
    e.preventDefault();
    let actorsLabel = document.getElementById("actorsLabel");
    let zeroActors = document.getElementById("noActorsMessage");

    if (zeroActors != null){
        actorsLabel.removeChild(zeroActors);
    }

    let span = document.createElement("span");
    let newInput = document.createElement("input");
    newInput.setAttribute("type", "text");
    newInput.setAttribute("name", "actors[]");
    newInput.style.marginBottom = "5px";
    newInput.required = true;

    let br = document.createElement("br");
    span.appendChild(newInput);

    let spans = document.querySelectorAll("#actorsLabel span");
    if (spans.length > 0) {
        let button = document.createElement("button");
        button.classList.add("delButton");
        button.style.marginLeft = "5px";
        button.textContent = "Delete actor";
        button.addEventListener("click", deleteActor, false);
        span.appendChild(button);
    }

    span.appendChild(br);
    actorsLabel.appendChild(span);
}

let elements = document.getElementsByClassName("delButton");

for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', deleteActor, false);
}
document.addFilmForm.addActorButton.addEventListener("click", addActor);