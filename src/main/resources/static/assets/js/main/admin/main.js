function getMainList() {
        let search = $(".search_object").val();

    httpUtil.defaultRequest('/api/admin/main/category','GET',search , (data) => {
            let mainCategoryList = "";
            for(let i of data.data){
                mainCategoryList += `
                    <tr class='table_content'>
                        <td>${i.idx}</td>
                        <td class="cursor" onclick='location.href="/admin/sub?search=${i.mainCategoryName}&sort=main"'>${i.mainCategoryName}</td>
                        <td>${i.regDate}</td>
                        <td>${i.regUserName}</td>
                    </tr>
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
            httpUtil.defaultRequest('/api/admin/main/category','post', data,  (data) => {
                getMainList();
                modalClose();
                main_name.value = "";
                successMessageToast(data.data.message);
        })
      }
}
