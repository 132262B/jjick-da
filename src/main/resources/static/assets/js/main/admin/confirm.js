function unconfirmedExamData() {
    let data = {};
    let searchObject = $(".search_object").val();
    if(isEmpty(searchObject)){
        data.searchObject = null;
    }else{
        data.searchObject = searchObject;
    }
    httpUtil.defaultRequest('/api/admin/confirm','GET', null, (data) => {
    let html = "";
        for(let i of data.data){
            html += `
                <tr class='table_content'>
                    <td><input name="confirmedExam" type="checkbox" value="${i.idx}"></td>
                    <td>${i.idx}</td>
                    <td>${i.examName}</td>
                    <td>${i.regDate}</td>
                    <td>${i.regName}</td>
                </tr>
            `
        }
        $(".html").html(html);
    })
}

function confirmExam() {
    let checked = [];
    $("input[name=confirmedExam]:checked").each(function(){
        checked.push($(this).val());
    })
        httpUtil.defaultRequest('/api/admin/confirm','put', checked, (data) => {
            unconfirmedExamData();
            successMessageToast(data.data.message);
        })
}






