
document.getElementById('filterButton').addEventListener('click', function () {
    const dropdown = document.getElementById('filterDropdown');
    dropdown.classList.toggle('hidden');
});

window.addEventListener('click', function (e) {
    const dropdown = document.getElementById('filterDropdown');
    const button = document.getElementById('filterButton');
    if (!dropdown.contains(e.target) && !button.contains(e.target)) {
        dropdown.classList.add('hidden');
    }
});

const rowsPerPage = 6; 
const rows = document.querySelectorAll('.filter-item'); 
const paginationContainer = document.getElementById('pagination'); 


function displayPage(page) {
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    rows.forEach((row, index) => {
        if (index >= start && index < end) {
            row.classList.remove('hidden'); 
        } else {
            row.classList.add('hidden'); 
        }
    });
}


function setupPagination() {
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement('button');
        button.textContent = i;
        button.className = 'bg-gray-200 px-3 py-1 rounded hover:bg-gray-300';
        button.addEventListener('click', () => displayPage(i));
        paginationContainer.appendChild(button);
    }
}

displayPage(1); 
setupPagination();

const addDocumentButton = document.getElementById('addDocumentButton');
const addDocumentModal = document.getElementById('addDocumentModal');
const cancelButton = document.getElementById('cancelButton');
const importButton = document.getElementById('importButton');


addDocumentButton.addEventListener('click', () => {
    addDocumentModal.classList.remove('hidden');
});


cancelButton.addEventListener('click', () => {
    addDocumentModal.classList.add('hidden');
});


importButton.addEventListener('click', () => {
    alert('Ici, vous pouvez importer un fichier Excel qui ...');
});

addDocumentModal.addEventListener('click', (e) => {
    if (!formContainer.contains(e.target)) {
        addDocumentModal.classList.add('hidden');
    }
});