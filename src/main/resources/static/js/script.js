
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