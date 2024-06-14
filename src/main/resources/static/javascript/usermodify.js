 function userModifySubmit(){
        if(confirm("회원정보를 수정하시겠습니까?")){
            document.getElementById("userModifyForm").submit();
        }
    }

    function check(){
        const nickname = document.getElementById("modifyNickname");
        const origin  = document.getElementById("originPass");
        const cp = document.getElementById("confirmPass");
        if(nickname.value=="" ||origin.value=="" ||cp.value==""){
                alert("수정할 내용을 입력해 주세요.");
        }
        else if(!origin.value!=cp.value){
            alert("비밀번호가 일치하지 않습니다.")
            cp.value="";
            cp.focus();
        }else{
            userModifySubmit();
        }
    }

    async function checkNickname(nickname) {
  try {
    const response = await fetch(`/api/member/nickname/${encodeURIComponent(nickname)}`);
    const data = await response.json();
    const mnickname = document.getElementById("modifyNickname");
    if (response.status === 409) {
      alert("중복된 닉네임 입니다.")
      mnickname.value="";
      mnickname.focus();
    } else {
      alert("예상치 못한 오류가 발생했습니다.");
    }
  } catch (error) {
    console.error('닉네임 확인 중 오류:', error);
    alert("닉네임 확인에 실패했습니다. 나중에 다시 시도하세요.");
  }
}