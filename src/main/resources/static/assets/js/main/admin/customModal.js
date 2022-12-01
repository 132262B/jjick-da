function modal() {
    let target = existId("modal");
    let target_content = existId("modal_content");
    target_content.style.display = "block";
    target.style.display = "block";
}

function modalClose() {
    let target = existId("modal");
    let target_content = existId("modal_content");
    target_content.style.display = "none";
    target.style.display = "none";
}