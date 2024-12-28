document.getElementById('select-all').addEventListener('change', function() {
    const checkboxes = document.querySelectorAll('.select-item');
    checkboxes.forEach(checkbox => {
        checkbox.checked = this.checked;
    });
});

function filtrer() {
    const filtre = document.getElementById('searchBar'); 
    const allRows = Array.from(document.querySelectorAll('.filter-item')); 
    const rowsPerPage = 6;

    filtre.addEventListener('input', function () {
        const searchValue = filtre.value.toLowerCase();
        const filteredRows = allRows.filter((row) => {
            const rowText = row.textContent.toLowerCase();
            return rowText.includes(searchValue);
        });

        displayFilteredResults(filteredRows, rowsPerPage);
    });
}

function displayFilteredResults(filteredRows, rowsPerPage) {
    document.querySelectorAll('.filter-item').forEach((row) => {
        row.classList.add('hidden');
    });
    const totalPages = Math.ceil(filteredRows.length / rowsPerPage);
    const start = 0;
    const end = rowsPerPage;
    filteredRows.slice(start, end).forEach((row) => {
        row.classList.remove('hidden');
    });

    setupFilteredPagination(filteredRows, rowsPerPage, totalPages);
}

function setupFilteredPagination(filteredRows, rowsPerPage, totalPages) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement('button');
        button.textContent = i;
        button.className = 'page-item active';
        button.addEventListener('click', () => {
            const start = (i - 1) * rowsPerPage;
            const end = start + rowsPerPage;
            document.querySelectorAll('.filter-item').forEach((row) => {
                row.classList.add('hidden');
            });
            
            filteredRows.slice(start, end).forEach((row) => {
                row.classList.remove('hidden');
            });
        });
        paginationContainer.appendChild(button);
    }
}

// Activer la fonction de recherche dès que la page est chargée
document.addEventListener('DOMContentLoaded', () => {
    filtrer();
});
