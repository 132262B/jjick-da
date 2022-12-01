function unconfirmedExamData() {
    let data = {};
    let searchObject = $(".search_object").val();
    if(isEmpty(searchObject)){
        data.searchObject = null;
    }else{
        data.searchObject = searchObject;
    }
    httpUtil.defaultRequest('/api/admin/getUnconfirmedExamData','post', data, (data) => {
    let html = "";
        for(let i of data.data){
            html += `
                <div class="list">
                    <div class="scroll_element list_checkbox"><input name="confirmedExam" type="checkbox" value="${i.idx}"></div>
                    <div class="scroll_element list_number">${i.idx}</div>
                    <div class="scroll_element list_name list_name_hover">${i.examName}</div>
                    <div class="scroll_element list_reg_date">${i.regDate}</div>
                    <div class="scroll_element list_reg_name">${i.regName}</div>
                </div>
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
        httpUtil.defaultRequest('/api/admin/confirmExam','post', checked, (data) => {
            unconfirmedExamData();
            successMessageToast(data.data.message);
        })
}






