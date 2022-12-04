let emptyData = {};
let index = 0;
let totalScore = 0;
let optionCnt = 0;
function getExamInfo(callback){
    index = 0;
    $("#question_html").html("");
    let subIdx = $("#sub_ctg_name").val();
    httpUtil.pathRequest('/api/admin/get-exam-information/'+subIdx, (data) => {
    optionCnt = data.data.optionsCnt;
        for(let i of data.data.subjectInformation){
            for(let k = 1; k<=i.subjectQuestionCnt; k++){
                                index++;
                                let html_first = "";
                                let html_middle = "";
                                let html_end = "";
                                html_first += `<div class='add question_data_${index} row gy-4'>
                                    <div class="col-md-12">
                                        <div class="info_wrap accordion-item">
                                            <div id='hidden_html${index}'></div>
                                            <input id="question${index}" type="text" class="question_subject form-control" name="subject" placeholder="No ${k}." required>
                                            <button class="hide_button accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#No${index}" />
                                        </div>
                                    </div>
                                      <div id='No${index}' class='accordion-collapse collapse' data-bs-parent='#faqlist'>
                                        <div class='filebox'>
                                          <input class='upload-name form-control' id='upload_name_${index}' readonly placeholder='첨부파일'>
                                          <label for='file${index}'>파일찾기</label>
                                          <input type='file' id='file${index}' onchange='uploadFile(this);'>
                                          <input type="text" id="th_${index}" class="subjects_name form-control" value="${i.subjectName}" readonly>
                                          <input type="hidden" id="subjectIdx${index}" value="${i.subjectIdx}">
                                          </div>
                                          <div class="img_div" id="img${index}"></div>`
                                for(let j=1; j<=optionCnt;j++){
                              html_middle +=    `<div class="choice col-md-12" id="">
                                                      <div class="choice_index">${j}.</div>
                                                      <input type="radio" name="correct_check_${index}" class="correct_check correct_check_${j}" value="${j}">
                                                      <textarea id="" class="question choice_content_${j} form-control" name="" rows="2" placeholder="보기" required></textarea>
                                                  </div>`
                            }
                            html_end ="</div></div>"

                            let html = html_first+html_middle+html_end;
                            $("#question_html").append(html);
            }
        }
        callback(data.data);
    })
}

function getMainDataList() {
    let dataList = `<option disabled selected value=''>메인 카테고리 선택</option>`;
    httpUtil.defaultRequest('/api/admin/get-main-category','post', emptyData, (data) => {
        for(let i of data.data){
            dataList += `<option value = "${i.idx}"/>${i.mainCategoryName}</option>`
        }
        $("#main_ctg_name").html(dataList);
    })
}
function getSubDataList() {
      $("#question_html").html("");
      $("#data_list2").html("");
      $("#sub_ctg_name").val("");
      for(let i=1; i<=index; i++){
      $("#th_"+i).val("");
      }
      let mainIdx = existIdValue("main_ctg_name");
      if(mainIdx == "" || mainIdx == null){
      }else{
            let subDataList = `<option disabled selected value=''>서브 카테고리 선택</option>`;
            let data = mainIdx;
            httpUtil.pathRequest('/api/admin/get-sub-category/'+mainIdx, (data) => {
                for(let i of data.data){
                  subDataList += `<option value='${i.idx}'>${i.subCategoryName}</option>`
                }
            $("#sub_ctg_name").html(subDataList);
          })
      }
}
function uploadFile(target) {
    let file_id_name = $(target).attr('id');
    let file_index = file_id_name.substr(4);
    let file = document.getElementById("file"+file_index);
    let fileName = $("#"+file_id_name).val();
    let hidden = "";
    if(file.files.length != 1) {
        $("#upload_name_"+file_index).val("");
        $("#hidden_html"+file_index).html(hidden);
        $("#img"+file_index).html("");
        return false;
    }
    if(!extensionValidation(target)){
        $("#upload_name_"+file_index).val("");
        $("#hidden_html"+file_index).html(hidden);
        $("#img"+file_index).html("");
        return false;
    }
    $("#upload_name_"+file_index).val(fileName);

    var fileData = new FormData();
    fileData.append("file", $("#file"+file_index)[0].files[0]);

    httpUtil.uploadRequest('/api/upload/file','post', fileData, (data) => {
        successMessageToast("1건의 파일이 등록 되었습니다.");
        hidden = `<input type='hidden' id='multiMedia${file_index}' value='${data.data.multiMediaIdx}'>
                  <input type='hidden' id='multiMediaName${file_index}' value='${data.data.fileId}'>`
        $("#hidden_html"+file_index).html(hidden);
        let img = `<img class='img_file' src='/api/image/${data.data.fileId}'>`
        $("#img"+file_index).html(img);
    })
}

function loadData() {
        getExamInfo(function(subData) {

                    let mainCategoryIdx = existIdValue("main_ctg_name");
                    let subCategoryIdx = existIdValue("sub_ctg_name");
                    let saveData = window.localStorage.getItem(`saveData_${mainCategoryIdx}_${subCategoryIdx}`);
                    saveData = JSON.parse(saveData);
                    if(!isEmptyStr(saveData)){
                        let loadData = confirm("이전에 작성하신 데이터를 가져옵니까?(취소시 삭제)");
                        if(loadData){
                            $("#examName").val(saveData.examInfo.examName);
                            for(let i of saveData.questions){
                            let hidden = "";
                            if(!isEmptyStr(i.multiMediaName)){
                                hidden = `<input type='hidden' id='multiMedia${i.questionNumber}' value='${i.multiMediaIdx}'>
                                          <input type='hidden' id='multiMediaName${i.questionNumber}' value='${i.multiMediaName}'>`
                                    $("#hidden_html"+i.questionNumber).html(hidden);
                                    let img = `<img class='img_file' src='/api/image/${i.multiMediaName}'>`
                                    $("#img"+i.questionNumber).html(img);
                            }else{
                                    $("#hidden_html"+i.questionNumber).html(hidden);
                            }
                                $("#question"+i.questionNumber).val(i.questionName);
                                $("#No"+i.questionNumber).find(".correct_check_"+i.answerNumber).prop("checked",true)
                                for(let k of i.options){
                                    $("#No"+i.questionNumber).find(".choice_content_"+k.optionNumber).val(k.optionContent);
                                }
                            }
                        }else{
                            window.localStorage.removeItem(`saveData_${mainCategoryIdx}_${subCategoryIdx}`);
                        }
                    }
        });
}

function saveData() {
        let data = {};
        let examInfo = {};
        let mainCategoryIdx = existIdValue("main_ctg_name");
        let subCategoryIdx = existIdValue("sub_ctg_name");
        if(mainCategoryIdx == null || mainCategoryIdx == "" || subCategoryIdx == null || subCategoryIdx == ""){
            warningMessageToast("임시 저장전 메인,서브 카테고리를 선택해주세요");
            return false;
        }
        let examName = existIdValue("examName");
        let questionCnt = index;
        examInfo.mainCategoryIdx = mainCategoryIdx;
        examInfo.subCategoryIdx = subCategoryIdx;
        examInfo.examName = examName;
        examInfo.questionCnt = questionCnt;
        let questions = [];

        for(let i = 1; i <= index; i++){
            let question = {};
            question.questionNumber = i;
            question.questionName = $("#question"+i).val();
            question.subjectIdx = $("#subjectIdx"+i).val();
            if(isEmptyStr($("#multiMedia"+i).val)){
                question.multiMediaIdx = null;
                question.multiMediaName = null;
            }else{
                question.multiMediaName = $("#multiMediaName"+i).val();
                question.multiMediaIdx = $("#multiMedia"+i).val();
            }
            const getAnswerNumber = document.getElementsByName("correct_check_"+i);
            getAnswerNumber.forEach((answer) => {
                if(answer.checked) {
                    question.answerNumber = answer.value;
                }
            })

            let options = [];
            let option = {};
            for(let k = 1;k <= optionCnt; k++){
                option = {};
                option.optionNumber = k;
                option.optionContent = $("#No"+i).find(".choice_content_"+k).val();
                options.push(option);
            }
            question.options = options;
            questions.push(question);
        }
        data.examInfo = examInfo;
        data.questions = questions;
        data = JSON.stringify(data);
        console.log(data);
        window.localStorage.setItem(`saveData_${mainCategoryIdx}_${subCategoryIdx}`,data);
}


function registQuestion() {
        let data = {};
        let examInfo = {};
        let mainCategoryIdx = existIdValue("main_ctg_name");
        mainCategoryIdx= parseInt(mainCategoryIdx);
        if(isEmptyStr(mainCategoryIdx)){
           warningMessageToast("메인 카테고리를 선택해야 합니다.");
           return false;
        }
        let subCategoryIdx = existIdValue("sub_ctg_name");
        subCategoryIdx= parseInt(subCategoryIdx);
        if(isEmptyStr(subCategoryIdx)){
           warningMessageToast("서브 카테고리를 선택해야 합니다.");
           return false;
        }
        let examName = existIdValue("examName");
        if(isEmptyStr(examName)){
           warningMessageToast("문항 제목을 입력해야 합니다.");
           return false;
        }
        let questionCnt = index;
        questionCnt = parseInt(questionCnt);

        examInfo.mainCategoryIdx = mainCategoryIdx;
        examInfo.subCategoryIdx = subCategoryIdx;
        examInfo.examName = examName;
        examInfo.questionCnt = questionCnt;
        let questions = [];
        for(let i = 1; i <= index; i++){
            let question = {};
            question.questionNumber = i;
            question.questionName = $("#question"+i).val();
            question.subjectIdx = $("#subjectIdx"+i).val();
            if(isEmptyStr($("#multiMedia"+i).val)){
                question.multiMediaIdx = null;
            }else{
                question.multiMediaIdx = $("#multiMedia"+i).val();
            }
            if(isEmptyStr(question.questionName)){
                warningMessageToast(i+"번 문항의 제목을 입력해야 합니다.");
                return false;
            }
            const getAnswerNumber = document.getElementsByName("correct_check_"+i);
            getAnswerNumber.forEach((answer) => {
                if(answer.checked) {
                    question.answerNumber = answer.value;
                }
            })
            if(isEmptyStr(question.answerNumber)){
                warningMessageToast(i+"번 문항의 정답을 체크해야 합니다.");
                return false;
            }
            let options = [];
            let option = {};
            for(let k = 1;k <= optionCnt; k++){
                option = {};
                option.optionNumber = k;
                option.optionContent = $("#No"+i).find(".choice_content_"+k).val();
                if(isEmptyStr(option.optionContent)){
                    warningMessageToast(i+"번 문항의 "+k+"번 선지를 입력해야 합니다.");
                    return false;
                }
                options.push(option);
            }
            question.options = options;
            questions.push(question);
        }
        data.examInfo = examInfo;
        data.questions = questions;
        console.log(JSON.stringify(data));
    httpUtil.defaultRequest('/api/admin/add-exam','post', data, (data) => {
        successMessageToast(data.data.message);
    })

}
