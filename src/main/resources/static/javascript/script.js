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

        document.getElementById("writeForm").appendChild(fileInput);
        fileInput.click();
    }



