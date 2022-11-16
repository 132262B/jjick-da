
// 로그인
function login() {
    const email = existId('email');
    const password = existId('password');

    // 이메일 형태가 아닌경우
    if (!validator.isEmail(email.value)) {
        email.classList.add('is-invalid');
        existId('invalidEmail').innerText = messageUtil.MESSAGE_EMAIL_CHECK;
        return false;
    }

    const data = {};
    data.email = email.value;
    data.password = sha256(password.value);

    httpUtil.defaultRequest('/api/user/login', 'post', data,
        function (data) {
            if(data.data.success) {
                location.href = '/home'
            } else {
                email.classList.add('is-invalid');
                existId('invalidEmail').innerText = '';
                password.classList.add('is-invalid');
                existId('invalidPassword').innerText = data.data.message;
            }
        });
}

// 엔터키 누를시
function enter() {
    if (window.event.keyCode === 13) {
        login();
    }
}
