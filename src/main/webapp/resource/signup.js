// HTML 폼 제출 이벤트 핸들러
document.querySelector('.form__signup').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 동작 중지

    // 비밀번호 필드 값 가져오기
    var passwordField = document.getElementById('password');
    var confirmPasswordField = document.getElementById('confirm_password');
    var password = passwordField.value;
    var confirmPassword = confirmPasswordField.value;

    // 비밀번호 확인
    if (password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        confirmPasswordField.value = ''; // 확인용 비밀번호 필드 초기화
        confirmPasswordField.focus(); // 확인용 비밀번호 필드에 포커스
    } else {
        // 비밀번호가 일치하면 폼을 서버로 제출
        this.submit();
    }
});