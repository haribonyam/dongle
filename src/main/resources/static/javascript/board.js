
function locationSelect() {
    const rotate = document.getElementById("rotate-item");
    const locationList = document.getElementById("locationList");

    if (locationList.style.display === "none") {
        locationList.style.display = "block";
        rotate.classList.add("rotate-180");
        fetchSidoInfo(); // Fetching initial data
    } else {
        locationList.style.display = "none";
        rotate.classList.remove("rotate-180");
    }
}

async function fetchSidoInfo() {
    const response = await fetch("/api/v3/sido-info");
    const data = await response.json();
    const locationList = document.getElementById("location-list");

    locationList.innerHTML = ""; // Clear previous items

    if (data.errCd === 0) {
            const li = document.createElement("li");
            li.classList.add("location-sido");
            const results = data.result;
            results.forEach(result => {
                const span = document.createElement("div");
                span.textContent = result.addr_name;
                span.addEventListener("click", () => {
                    fetchSggInfo(result.cd,result.addr_name);
                });
                li.appendChild(span);
            });
            const arrow = document.createElement("li");
            arrow.textContent=">";
            locationList.appendChild(li);
            locationList.appendChild(arrow);

        } else {
            console.error("Error:", data.errMsg);
        }
}

async function fetchSggInfo(cd,sido) {
    const response = await fetch(`/api/v3/sgg-info?cd=${cd}`);
    const data = await response.json();
    const locationList = document.getElementById("location-list");
    const locationSgg = document.querySelector(".location-sgg");
        if (locationSgg) {
            locationSgg.remove();
        }
    if (data.errCd === 0) {
                const li = document.createElement("li");
                li.classList.add("location-sgg");
                const results = data.result;
                results.forEach(result => {
                    const span = document.createElement("div");
                    span.textContent = result.addr_name;
                    span.addEventListener("click", () => {
                       findByTown(sido,result.addr_name);
                    });
                    li.appendChild(span);
                });
                locationList.appendChild(li);

            } else {
                console.error("Error:", data.errMsg);
            }
}

        function findByTown(sido,sgg){

                document.getElementById("locationText").textContent = sido+" "+sgg;
                locationSelect();
        }

