

function existIdValue(id) {
    const value = document.getElementById(id).value;
    return (value !== undefined && value !== null ? value : '');


}
