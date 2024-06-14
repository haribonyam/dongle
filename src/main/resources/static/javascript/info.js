document.addEventListener("DOMContentLoaded",function(){

            const myinfo = document.getElementById("myinfo");
            const mypost = document.getElementById("mypost");
            const postlist = document.getElementById("user-info-post");
            const userinfo = document.getElementById("user-info-summary");
            myinfo.addEventListener("click",function(){
                toggleInfo(myinfo,mypost,userinfo,postlist);
            });
            mypost.addEventListener("click",function(){
                toggleInfo(mypost,myinfo,userinfo,postlist);
            });

            function toggleInfo(select,other,userinfo,postlist){
                select.style.opacity = "0.3";
                other.style.opacity = "1.0";
                if(select == myinfo){
                      userinfo.style.display="block";
                      postlist.style.display="none";
                }else{
                      userinfo.style.display="none";
                      postlist.style.display="block";
                }
            }
        });
