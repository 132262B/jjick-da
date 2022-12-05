function getSubDetail() {
    let html = "";
    let subIdx = existId("subIdx").value;
    getSubjectList(subIdx);
    httpUtil.defaultRequest("/api/admin/sub/category/detail/"+subIdx,'get',null, (data) => {
    let useStatus = data.data.useStatus;
    if(useStatus == 1){
       useStatus = "사용중";
    }else{
       useStatus = "사용안함";
    }
    let regDate = cutTime(data.data.regDate);
    let udtDate = data.data.udtDate;
    if(udtDate == null || udtDate == "없음"){
        udtDate = "수정 없음"
    }else{
        udtDate = cutTime(udtDate);
    }
    html += `<div class="info_subject">
                <a href="/admin/sub?search=${data.data.mainCategoryName}&sort=main">${data.data.mainCategoryName}</a>-${data.data.subCategoryName}
              </div>
              <div class="info_details">
              <div class="info_right">
                  <div id="subjects" class="">No : <span> ${data.data.idx}.</span></div>
                  <div id="subjects" class="">사용 유무 : <span> ${useStatus} </span></div>
                  <div id="subjects" class="">등록자 : <span> ${data.data.regUserName}</span></div>
                  <div id="subjects" class="">등록일 :<span> ${regDate}</span></div>
              </div>
              <div class="info_left">
                  <div id="subjects" class="">과목 개수 :<span> ${data.data.subjectCount}개</span></div>
                  <div id="subjects" class="">수정일 : <span> ${udtDate}</span></div>
                  <div id="subjects" class="">수정자 : <span> ${data.data.udtUserName}</span></div>
                  <div id="subjects" class=""><a href="">수정</a></div>
              </div>
          </div>`
        $("#html").html(html)
    });
}

function getSubjectList(subIdx) {
    let subjectList = "";
    httpUtil.defaultRequest("/api/admin/subject/"+subIdx,'get',null, (data) => {
        for(let i of data.data){
            subjectList += `
                <div class="list">
                    <div class="scroll_element list_checkbox">&nbsp;</div>
                    <div class="scroll_element list_number">${i.idx}</div>
                    <div class="scroll_element list_name list_name_hover">${i.subjectName}</div>
                    <div class="scroll_element list_reg_date">${i.regDate}</div>
                    <div class="scroll_element list_reg_name">${i.regName}</div>
                </div>
            `
        }
        $("#html2").html(subjectList);
    })
}

function registSubject() {
    let subjectData = {};
    subjectData.subCategoryIdx = existId("subIdx").value;
    subjectData.subjectName = existId("subjectName").value;
    subjectData.subjectQuestionCnt = existId("subjectQuestionCnt").value;
    subjectData.subjectCutOffScore = existId("subjectCutOffScore").value;
    if(isEmptyStr(subjectData.subCategoryIdx)){
       warningMessageToast("가용하지 않는 페이지입니다.");
       return false;
    }
    if(isEmptyStr(subjectData.subjectQuestionCnt)){
       warningMessageToast("문제 개수를 입력하세요.");
       return false;
    }
    if(isEmptyStr(subjectData.subjectName)){
       warningMessageToast("과목 이름을 입력하세요.");
       return false;
    }
    if(isEmptyStr(subjectData.subjectCutOffScore)){
       warningMessageToast("과목 합격 기준을 입력하세요.");
       return false;
    }

    httpUtil.defaultRequest('/api/admin/subject','post', subjectData, function(data) {
        if(data.data.success){
            getSubDetail();
            successMessageToast(data.data.message);
            $("#subjectName").val("");
            $("#subjectQuestionCnt").val("");
            $("#subjectCutOffScore").val("");
            $("#subjectName").focus();
        }
    })
}
