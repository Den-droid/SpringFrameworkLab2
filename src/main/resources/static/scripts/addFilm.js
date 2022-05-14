function addActor(e) {
    e.preventDefault();
    let actorsLabel = document.getElementById("actorsLabel");

    let newInput = document.createElement("input");
    newInput.setAttribute("type", "text");
    newInput.setAttribute("name", "actors[]");
    newInput.style.marginBottom = "5px";
    newInput.required = true;

    let br = document.createElement("br");
    actorsLabel.appendChild(br);
    actorsLabel.appendChild(newInput);
}

document.addFilmForm.addActorButton.addEventListener("click", addActor);