let emptyData = {};
function getMainDataList() {
    let dataList = "";
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, function(data) {
        for(let i of data.data){
            dataList += `<option data-value = "${i.idx}" value ="${i.mainCategoryName}" />`
        }
        $("#data_list").html(dataList);
    })
}
function getSubList() {
    httpUtil.defaultRequest('/api/admin/get-sub-list', 'post', emptyData,
        function (data) {
            let subList = "";
            for(let i of data.data){
            let reg_date = cutTime(i.regDate);
            let udt_date = i.udt_date
            let use_sts = i.useStatus;
            if(udt_date == null || udt_date == ''){
                udt_date = "없음";
            }else{
                udt_date = cutTime(udt_date);
            }
            if(use_sts == 1){
                use_sts = "사용중";
            }else{
                use_sts = "미사용중";
            }
                subList += `<div onclick="write_subject(${i.idx})" class="col-md-12" id="main_list">
                          <div class="list_1" id="main_lists">${i.idx}</div>
                          <div class="list_2" id="main_lists">${i.subCategoryName}</div>
                          <div class="list_3" id="main_lists">${reg_date}</div>
                          <div class="list_4" id="main_lists">${i.regUserName}</div>
                          <div class="list_5" id="main_lists">${udt_date}</div>
                          <div class="list_6" id="main_lists">${i.udtUserName}</div>
                          <div class="list_7" id="main_lists">${use_sts}</div>
                          </div>`
                $(".htmls").html(subList);
                }
    });
}
function registSub() {
    let subCategoryName = $("#subCategoryName").val();
    let mainCategoryName = $("#mainCategoryName").val();
    let mainCategoryIdx = $("#data_list [value='" + mainCategoryName + "']").data("value");
    if(subCategoryName == null || subCategoryName == "") {
        warningMessageToast("서브 카테고리 이름 기재 필수");
    }else if(mainCategoryIdx == null || mainCategoryIdx == "") {
        warningMessageToast("메인 카테고리 선택 필수");
    }else{
    let data = {};
    data.subCategoryName = subCategoryName;
    data.mainCategoryIdx = mainCategoryIdx;
        httpUtil.defaultRequest('/api/admin/regist-sub','post', data, function(data) {
            if(data.data.success){
                getSubList();
                successMessageToast(data.data.message);
            }
        })
    }
}
  function write_subject(ob) {
    location.href="/admin/write-sub-question/subject/"+ob;
  }

