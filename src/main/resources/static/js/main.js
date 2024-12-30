document.getElementById('select-all').addEventListener('change', function () {
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
    paginationContainer.innerHTML = ''; // Réinitialiser la pagination

    // Génération des boutons de pagination
    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li'); // Créer un élément <li>
        pageItem.className = `page-item ${i === 1 ? 'active' : ''}`; // Ajouter une classe active pour la première page

        const pageLink = document.createElement('a'); // Créer un élément <a>
        pageLink.className = 'page-link'; // Classe pour le style
        pageLink.href = '#'; // Empêcher la navigation
        pageLink.textContent = i; // Numéro de page

        // Ajout de l'événement de clic
        pageLink.addEventListener('click', () => {
            // Mise à jour des lignes visibles
            const start = (i - 1) * rowsPerPage;
            const end = start + rowsPerPage;

            document.querySelectorAll('.filter-item').forEach((row) => {
                row.classList.add('hidden');
            });

            filteredRows.slice(start, end).forEach((row) => {
                row.classList.remove('hidden');
            });

            // Mise à jour des classes actives dans la pagination
            document.querySelectorAll('.page-item').forEach((item) => item.classList.remove('active'));
            pageItem.classList.add('active');
        });

        pageItem.appendChild(pageLink); // Ajouter le lien dans l'élément <li>
        paginationContainer.appendChild(pageItem); // Ajouter l'élément <li> dans la pagination
    }
}


function setupDynamicPagination(dataArray, rowsPerPage) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = ''; // Réinitialiser la pagination

    const totalPages = Math.ceil(dataArray.length / rowsPerPage); // Calculer le nombre total de pages

    // Générer les boutons de pagination
    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        pageItem.className = `page-item ${i === 1 ? 'active' : ''}`; // dar la premiere page active

        const pageLink = document.createElement('a'); 
        pageLink.className = 'page-link';
        pageLink.href = '#';
        pageLink.textContent = i;

        pageLink.addEventListener('click', () => {
            const start = (i - 1) * rowsPerPage;
            const end = start + rowsPerPage;

            document.querySelectorAll('.filter-item').forEach((row) => {
                row.classList.add('hidden');
            });

            dataArray.slice(start, end).forEach((row) => {
                row.classList.remove('hidden');
            });

            // ghay7iyed active l precedente o y y3tiha leli 7na watyin 3liha
            document.querySelectorAll('.page-item').forEach((item) => item.classList.remove('active'));
            pageItem.classList.add('active');
        });

        pageItem.appendChild(pageLink); // ghadi y ajouter le lien à l'élément <li> bach ymchi l section lakhra dyal la liste
        paginationContainer.appendChild(pageItem); // Ajouter l'élément <li> dans la pagination
    }

    // hna ghadi y afficher awel elements dyal la page
    const start = 0;
    const end = rowsPerPage;
    dataArray.slice(start, end).forEach((row) => {
        row.classList.remove('hidden');
    });
}



// Activer la fonction de recherche dès que la page est chargée
document.addEventListener('DOMContentLoaded', () => {
    filtrer();
});

// dar eventListener bach y3iyet la méthode mn mor ma DOM ytcharja kamel (DOM ???)
document.addEventListener('DOMContentLoaded', () => {
    const rows = Array.from(document.querySelectorAll('.filter-item')); 
    const rowsPerPage = 5;
    setupDynamicPagination(rows, rowsPerPage);
});
// Pour les boutons de téléchargement
document.querySelectorAll('.download-fiche').forEach(button => {
    button.addEventListener('click', function() {
        const userId = this.getAttribute('data-id');
        if (!userId || userId === 'null') {
            console.error('ID utilisateur invalide pour le téléchargement');
            return;
        }
        downloadFicheLecture(userId);
    });
});
function downloadFicheLecture(userId) {
    if (!userId || userId === 'null') {
        console.error('ID utilisateur invalide pour le téléchargement');
        return;
    }

    const loadingAnimation = document.querySelector('.loading-animation');
    if (loadingAnimation) {
        loadingAnimation.style.display = 'block';
    }

    fetch(`/GestionUsager/ficheLecture/${userId}`, {
        method: 'GET',
        headers: {
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]')?.content
        },
        credentials: 'same-origin'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erreur lors du téléchargement');
        }
        return response.blob();
    })
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `fiche_lecture_${userId}.pdf`;
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);

        if (loadingAnimation) {
            loadingAnimation.style.display = 'none';
        }
    })
    .catch(error => {
        console.error('Erreur:', error);
        alert('Une erreur est survenue lors du téléchargement de la fiche');
        if (loadingAnimation) {
            loadingAnimation.style.display = 'none';
        }
    });
}