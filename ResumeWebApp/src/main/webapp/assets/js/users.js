function whatImActuallyTyping() {
  let input = document.getElementById("whatImTyping");
  let span = document.getElementById("typing");
  let typed = input.value;
  span.innerHTML = typed;
}

function changeColor() {
  let sign = document.getElementById("btnSearch");
  sign.style = "background-color: red";
}

function showHide() {
  let sign = document.getElementById("btnSearch");
  if (sign.visible) {
    sign.visible = false;
    sign.style = "display: none";
  } else {
    sign.visible = true;
    sign.style = "display: block";
  }
}

function setIdDifferently(id){
  let elem = document.getElementById("myModalId");
  elem.value = id;
}