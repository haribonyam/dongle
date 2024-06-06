//지역선택
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


// 페이지 로딩 시 초기 게시물 목록과 페이징을 가져옵니다.
    console.log("ddd");
    window.onload = function() {
        loadPosts(0);
    };

    // RESTful API를 호출하여 페이징 처리된 게시물 목록을 가져옵니다.
    function loadPosts(page) {
        const size = 5; // 페이지당 게시물 수
        fetch(`/api/post/list?page=${page}&size=${size}`)
            .then(response => response.json())
            .then(data => {
                // 게시물 목록을 표시합니다.
                renderPosts(data.content);
                // 페이징 버튼을 표시합니다.
                renderPagination(data.totalPages, page);
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    // 게시물 목록을 동적으로 생성하여 HTML에 추가합니다.
    function renderPosts(posts) {
        const box = document.getElementById("boardList");
        box.innerHTML='';
        posts.forEach(post => {

                const postElement = document.createElement('div');
                postElement.classList.add('board-contents');

                // 내부 HTML 설정
                postElement.innerHTML = `
                    <div style="background-image: url('${post.filePaths[0]}');" class='board-img'></div>
                    <ul>
                        <li onclick='location.href="/posts/"+${post.id}'>${post.title}<div>${post.commentsCount}</div></li>
                        <li>${post.nickname}</li>
                        <li>${post.town}</li>
                    </ul>
                `;

                // board-location 클래스를 가진 요소에 추가

                box.appendChild(document.querySelector('.board-location').appendChild(postElement));
        });

    }

    // 페이징 버튼을 동적으로 생성하여 HTML에 추가합니다.
    function renderPagination(totalPages, currentPage) {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = ''; // 기존 버튼 삭제
        console.log(currentPage);
        for (let i = 0; i < totalPages; i++) {
            const pageButton = document.createElement('button');
            pageButton.textContent = i + 1;
            pageButton.onclick = function() {
                loadPosts(i);

            };

             if(i==currentPage){
             pageButton.style.color="#ccc";
             }
            pagination.appendChild(pageButton);
        }
    }

    // 필터링 적용 버튼을 클릭할 때 호출되는 함수입니다.
    function applyFilters() {
        const province = document.getElementById('provinceSelect').value;
        const city = document.getElementById('citySelect').value;
        const town = document.getElementById('townSelect').value;
        // 필터링된 게시물을 가져오고 페이징을 초기화합니다.
        loadFilteredPosts(0, province, city, town);
    }

    // 필터링된 게시물을 가져오고 페이징을 초기화합니다.
    function loadFilteredPosts(page, province, city, town) {
        const size = 10; // 페이지당 게시물 수
        fetch(`/api/post/filter?page=${page}&size=${size}&province=${province}&city=${city}&town=${town}`)
            .then(response => response.json())
            .then(data => {
                // 게시물 목록을 표시합니다.
                renderPosts(data.content);
                // 페이징 버튼을 표시합니다.
                renderPagination(data.totalPages, page);
            })
            .catch(error => console.error('Error fetching data:', error));
    }



    //postWrite

        function triggerFileInput() {
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.name = 'files';
        fileInput.accept = 'image/*';
        fileInput.style.display = 'none';

        fileInput.onchange = function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const previewContainer = document.createElement('div');
                    previewContainer.className = 'photo-preview';

                    const img = document.createElement('img');
                    img.src = e.target.result;
                    previewContainer.appendChild(img);

                    const removeButton = document.createElement('button');
                    removeButton.className = 'remove-photo';
                    removeButton.innerHTML = 'X';
                    removeButton.onclick = function() {
                        document.querySelector('.photo-container').removeChild(previewContainer);
                        fileInput.remove();
                    };
                    previewContainer.appendChild(removeButton);

                    document.querySelector('.photo-container').appendChild(previewContainer);
                };
                reader.readAsDataURL(file);
            }
        };

        document.body.appendChild(fileInput);
        fileInput.click();
    }



