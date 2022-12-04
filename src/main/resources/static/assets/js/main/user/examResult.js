function result(idx, token) {
    console.log(idx);
    console.log(token);
    let data = {};
    data.idx = idx;
    data.token = token;
    httpUtil.defaultRequest(``, 'POST', data, (data) => {

    });

}