let emptyData = {};

function unconfirmedExamData() {

    httpUtil.defaultRequest('/api/admin/getUnconfirmedExamData','post', emptyData, (data) => {
    let html = "";
        for(let i of data.data){
            html += `
                <div class="confirm_list">
                    <div class="confirm_checkbox"><input type="checkbox" value="${i.idx}"></div>
                    <div class="confirm_number">${i.idx}</div>
                    <div class="confirm_name">${i.examName}</div>
                    <div class="confirm_reg_date">${i.regDate}</div>
                    <div class="confirm_reg_name">${i.regName}</div>
                </div>
            `
        }
        $(".scroll").html(html);
    })
}