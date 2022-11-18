function getSubDetail() {
    let htmls = "";
    let subIdx = existId("subIdx");
    let data = subIdx.value;
    httpUtil.defaultRequest(`/api/admin/get-sub-detail`, 'post', data,
    function (data) {
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

    htmls += `<div class="info_subject">
                <a href="">${data.data.mainCategoryName}</a>-${data.data.subCategoryName}
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
        $("#htmls").html(htmls)
    });
}
function registSubject() {
    let subjectData = {};
    subjectData.subCategoryIdx = existId("subIdx").value;
    subjectData.subjectName = existId("subject_name").value;
    httpUtil.defaultRequest('/api/admin/regist-subject','post', subjectData, function(data) {
        if(data.data.success){
            getSubDetail();
            successMessageToast(data.data.message);
        }
    })
}
