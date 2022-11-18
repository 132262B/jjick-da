let emptyData = {};
function getMainList() {
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, function(data) {
            let mainCategoryList = "";
            for(let i of data.data){
                let reg_date = cutTime(i.regDate);
                let udt_date = i.udtDate;
                let use_sts = i.useStatus;
                let udt_seq = i.udtIdx;
                    if(udt_date == null){
                      udt_date = "없음";
                    }else{
                      udt_date = cutTime(udt_date);
                    }
                    if(use_sts == 1){
                    use_sts = "사용중";
                    }else{
                    use_sts = "미사용중";
                    }
                mainCategoryList += `<div class="col-md-12" id="main_list">
                              <div class="list_1" id="main_lists">${i.idx}</div>
                              <div class="list_2" id="main_lists">${i.mainCategoryName}</div>
                              <div class="list_3" id="main_lists">${reg_date}</div>
                              <div class="list_4" id="main_lists">${i.regUserName}</div>
                              <div class="list_5" id="main_lists">${udt_date}</div>
                              <div class="list_6" id="main_lists">${i.udtUserName}</div>
                              <div class="list_7" id="main_lists">${use_sts}</div>
                            </div>`
            }
      $(".htmls").html(mainCategoryList);
    })
}

function registMain() {
      let main_name = existId('mainCategoryName');
      if(main_name == null || main_name == "") {
      errorMessageToast("이름 입력 필수!");
      }else{
        let data = {};
        data.mainCategoryName = main_name.value;
            httpUtil.defaultRequest('/api/admin/regist-main','post', data, function(data) {
                getMainList();
                successMessageToast(data.data.message);
        })
      }

}
    //function getMainList() {
    //    let emptyData = {};
    //    let mainData = "";
    //    httpUtil.asyncRequest('/api/admin/get-main-category','post', emptyData, function(data) {
    //        mainData = data;
    //        })
    //        return mainData;
    //}

    //function placeMainList() {
    //    let data = getMainList();
    //    alert(data);
    //    let mainCategoryList = "";
    //    for(let i of data.data){
    //        let reg_date = cutTime(i.regDate);
    //        let udt_date = i.udtDate;
    //        let use_sts = i.useStatus;
    //        let udt_seq = i.udtIdx;
    //            if(udt_date == null){
    //              udt_date = "없음";
    //            }else{
    //              udt_date = cutTime(udt_date);
    //            }
    //            if(use_sts == 1){
    //            use_sts = "사용중";
    //            }else{
    //            use_sts = "미사용중";
    //            }
    //        mainCategoryList += `<div class="col-md-12" id="main_list">
    //                      <div class="list_1" id="main_lists">${i.idx}</div>
    //                      <div class="list_2" id="main_lists">${i.mainCategoryName}</div>
    //                      <div class="list_3" id="main_lists">${reg_date}</div>
    //                      <div class="list_4" id="main_lists">${i.regUserName}</div>
    //                      <div class="list_5" id="main_lists">${udt_date}</div>
    //                      <div class="list_6" id="main_lists">${i.udtUserName}</div>
    //                      <div class="list_7" id="main_lists">${use_sts}</div>
    //                    </div>`
    //    }
    //          $(".htmls").html(mainCategoryList);
    //}
    //function registMain() {
    //      let main_name = existId('mainCategoryName');
    //      if(main_name == null || main_name == "") {
    //      errorMessageToast("이름 입력 필수!");
    //      }else{
    //        let data = {};
    //        data.mainCategoryName = main_name.value;
    //            httpUtil.defaultRequest('/api/admin/regist-main','post', data, function(data) {
    //                placeMainList()
    //                successMessageToast(data.data.message);
    //        })
    //      }
    //
    //}