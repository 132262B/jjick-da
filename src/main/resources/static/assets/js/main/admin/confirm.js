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
                <div class="confirm_list">
                    <div class="confirm_checkbox"><input name="confirmedExam" type="checkbox" value="${i.idx}"></div>
                    <div class="confirm_number">${i.idx}</div>
                    <div id="confirm_name_hover" class="confirm_name">${i.examName}</div>
                    <div class="confirm_reg_date">${i.regDate}</div>
                    <div class="confirm_reg_name">${i.regName}</div>
                </div>
            `
        }
        $(".scroll").html(html);
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






