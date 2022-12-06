function getMainDataList() {
    let dataList = "";
    httpUtil.defaultRequest('/api/admin/main/category','GET', null, function(data) {
        for(let i of data.data){
            dataList += `<option data-value = "${i.idx}" value ="${i.mainCategoryName}" />`
        }
        $("#data_list").html(dataList);
    })
}
function getSubList() {
let data = {};
data.search = existId('search_object').value;
data.sort = existId('search_sort').value;
httpUtil.notStringifyRequest('/api/admin/sub/category', 'GET',data ,
    function (data) {
        let subList = "";
        for(let i of data.data){
            subList += `
                    <tr class="cursor" onclick="write_subject(${i.idx})">
                        <td>${i.idx}</td>
                        <td>${i.mainCategoryName}</td>
                        <td>${i.subCategoryName}</td>
                        <td>${i.regDate}</td>
                        <td>${i.regUserName}</td>
                    </tr>
                      `
            }
            console.log(subList);
            $(".html").html(subList);
    });
}
function registSub() {
    let subCategoryName = existId("subCategoryName").value;
    let mainCategoryName = existId("mainCategoryName").value;
    let mainCategoryIdx = $("#data_list [value='" + mainCategoryName + "']").data("value");
    let optionsCnt = existId("optionsCnt").value;
    let examCutOffScore = existId("examCutOffScore").value;
    if(subCategoryName == null || subCategoryName == "") {
        warningMessageToast("서브 카테고리 이름 기재 필수");
        return false;
    }else if(mainCategoryIdx == null || mainCategoryIdx == "") {
        warningMessageToast("메인 카테고리 선택 필수");
        return false;
    }else if(optionsCnt == 0){
        warningMessageToast("선지 개수 선택 필수");
        return false;
    }else if(examCutOffScore == null || examCutOffScore == "") {
        warningMessageToast("시험 합격 기준 점수 필수");
        return false;
    }else{
    let data = {};
    data.subCategoryName = subCategoryName;
    data.mainCategoryIdx = mainCategoryIdx;
    data.optionsCnt = optionsCnt;
    data.examCutOffScore = examCutOffScore;
        httpUtil.defaultRequest('/api/admin/sub/category','post', data, function(data) {
            if(data.data.success){
                existId("subCategoryName").value = "";
                existId("mainCategoryName").value = "";
                existId("examCutOffScore").value = "";
                $("#optionsCnt").val("4").prop("selected",true);
                getSubList();
                modalClose();
                successMessageToast(data.data.message);
            }
        })
    }
}
  function write_subject(idx) {
    location.href="/admin/subject/"+idx;
  }

