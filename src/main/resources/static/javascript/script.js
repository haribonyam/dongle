//지역선택

/*
function locationSelect(){
        const rotate = document.getElementById("rotate-item");
        const locationList = document.getElementById("locationList");
        if (locationList.style.display === "none") {
        locationList.style.display = "block";
        rotate.classList.add("rotate-180");
    } else {
        locationList.style.display = "none";
        rotate.classList.remove("rotate-180");
    }
    }
*/
    // userinfo
    function UserInfo(nickname){
       if(nickname == "anonymousUser"){
            alert("로그인이 필요한 서비스 입니다.")
       }else{
            location.href = "/userinfo/"+nickname;
       }
    }

    function UserModify(){

    }




