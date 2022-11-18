let emptyData = {};
let index = 1;
function getMainDataList() {
    let dataList = "";
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, function(data) {
        for(let i of data.data){
            dataList += `<option data-value = "${i.idx}" value ="${i.mainCategoryName}" />`
        }
        $("#data_list").html(dataList);
    })
}
function getSubDataList() {
      let main_ctg_name = $("#main_ctg_name").val();
      let mainIdx = $("#data_list [value='" + main_ctg_name + "']").data("value");
      if(mainIdx == "" || mainIdx == null){
        $("#data_list2").html("");
        $("#data_list3").html("");
        $("#sub_ctg_name").val("");
        for(let i=1; i<=index; i++){
            $(".subject_"+i).val("");
        }
      }else{
            $.ajax({
                  type:"POST",
                  url:"/api/admin/get-sub-category",
                  data:{"mainIdx":mainIdx},
                  dataType:"json"
            }).done(function(data){
              let subDataList = "";
              for(let i of data.data){
                subDataList += `<option data-value='${i.idx}' value='${i.subCategoryName}' />`
              }
              $("#data_list2").html(subDataList);
            }).fail(function(){
            })
      }
}

function getSubjectDataList() {
      let sub_ctg_name = $("#sub_ctg_name").val();
      let subIdx = $("#data_list2 [value='" + sub_ctg_name + "']").data("value");
      if(subIdx == "" || subIdx == null){
        for(let i=1; i<=index; i++){
            $(".subject_"+i).val("");
        }
        $("#data_list3").html("");
      }else{
            $.ajax({
                  type:"POST",
                  url:"/api/admin/get-subject-category",
                  data:{"subIdx":subIdx},
                  dataType:"json"
            }).done(function(data){
              let subjectDataList = "";
              for(let i of data.data){
                subjectDataList += "<option data-value='" + i.idx + "' value='" + i.subjectName + "' />"
              }
              $("#data_list3").html(subjectDataList);
            }).fail(function(){
            })
      }
}

function changeOptionCnt() {
      let count = $("#option_count").val()
      if(count == "5"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).append(
            `<div class='col-md-12' id='choice'>
              <div id='choice_index'>5.</div>
              <input type='radio' name='correct_check_"+i+"' id='correct_check' value='check_5'>
              <textarea id='question' class='form-control' name='' rows='2' placeholder='보기' required></textarea>
              </div>`
          )
        }
      }
      if(count == "4"){
        for(let i = 1; i<=index; i++){
          $("#No" + i).children("#choice").last().remove();
        }
      }
}

function plusIcon() {
                index++;
                let html_first = "";
                let html_middle = "";
                let html_end = "";
                let option_count = $("#option_count").val()
                html_first += `<div class='question_data_${index} row gy-4' id='add'>
                      <div class='col-md-12'>
                        <div class='accordion-item'>
                          <input id='question_subject' type='text' class='form-control' name='subject' placeholder='No ${index}.' required>
                          <button id='hide_button' class='accordion-button collapsed' type='button' data-bs-toggle='collapse' data-bs-target='#No${index}' />
                        </div>
                      </div>
                      <div id='No${index}' class='accordion-collapse collapse' data-bs-parent='#faqlist'>
                        <div class='filebox'>
                          <input class='upload-name form-control' id='upload_name_${index}' readonly placeholder='첨부파일'>
                          <label for='file${index}'>파일찾기</label>
                          <input type='file' id='file${index}'>
                          <div class="" id="select_subject">
                              <input type="text" class="subject_${index} form-control" id="subject_name" list="data_list3" placeholder="과목 선택">
                              <datalist id="data_list3" class="subject_datalist_${index} col-md-6">
                              </datalist>
                          </div>
                          </div>
                          <div class='img_wrap'>
                            <img class='mt-5' id='img${index}' />
                            </div>`
                for(let i=1; i<=option_count;i++){
              html_middle +=    `<div class='col-md-12' id='choice'>
                                    <div id='choice_index'>${i}.</div>
                                    <input type='radio' name='correct_check_${index}' id='correct_check' value='check_${i}'>
                                    <textarea id='question' class='form-control' name='' rows='2' placeholder='보기' required></textarea>
                                </div>`
            }
            html_end ="</div></div>"

            let html = html_first+html_middle+html_end;
            $("#plus_icon").before(html);
}

function minusIcon() {
      $(this).prev().prev("#add:not(:first-child)").remove();
      index--;
      if(index < 1){
        index = 1;
      }
}

