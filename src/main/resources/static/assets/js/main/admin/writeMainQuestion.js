function getMainList() {
        let data = {};
        let searchObject = $(".search_object").val();
        if(isEmpty(searchObject)){
            data.searchObject = null;
        }else{
            data.searchObject = searchObject;
        }
    httpUtil.defaultRequest('/api/admin/get-main-category','post',data , (data) => {
            let mainCategoryList = "";
            for(let i of data.data){
                mainCategoryList += `
                    <div class="list">
                        <div class="scroll_element list_checkbox">&nbsp;</div>
                        <div class="scroll_element list_number">${i.idx}</div>
                        <div class="list_name_hover scroll_element list_name" onclick='location.href="/admin/write-sub-question/main/${i.mainCategoryName}"'>${i.mainCategoryName}</div>
                        <div class="scroll_element list_reg_date">${i.regDate}</div>
                        <div class="scroll_element list_reg_name">${i.regUserName}</div>
                    </div>
            `
            }
      $(".html").html(mainCategoryList);
    })
}
function registMain() {
      let main_name = existId('mainCategoryName');
      if(main_name == null || main_name == "") {
      errorMessageToast("이름 입력 필수!");
      }else{
        let data = {};
        data.mainCategoryName = main_name.value;
            httpUtil.defaultRequest('/api/admin/regist-main','post', data,  (data) => {
                getMainList();
                modalClose();
                main_name.value = "";
                successMessageToast(data.data.message);
        })
      }
}
