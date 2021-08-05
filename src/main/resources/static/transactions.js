$(document).ready(() => {
    let transactionToEdit = null;

    $('#add-transaction-button').click(() => {
        if (transactionToEdit) {
            editTransaction(transactionToEdit);
        } else {
            addNew();
        }
    });

    function editTransaction(transactionToEdit) {
        const product = $('#modal-transaction-product').val();
        const amount = $('#modal-transaction-amount').val();
        const type = $('#modal-transaction-type').val();

        fetch(`/api/transactions/${transactionToEdit}`, {
            method: 'PUT',
            body: JSON.stringify({
                product: product,
                amount: amount,
                type: type
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert("There were errors " + response.status);
            }
        });
    }

    function addNew() {
        const product = $('#modal-transaction-product').val();
        const amount = $('#modal-transaction-amount').val();
        const type = $('#modal-transaction-type').val();

        fetch('/api/transactions', {
            method: 'POST',
            body: JSON.stringify({
                product: product,
                amount: amount,
                type: type
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                location.reload();
                clearModal();
            } else {
                alert("There were errors " + response.status);
            }
        });
    }

    function clearModal() {
        $('#modal-transaction-product').val('');
        $('#modal-transaction-amount').val('');
        $('#modal-transaction-type').val('');
    }

    $('.delete-icon').click(function () {
        const transactionId = this.parentElement.id;
        fetch(`api/transactions/${transactionId}`, {
            method: 'DELETE'
        }).then(response => location.reload());
    });

    $('.edit-icon').click(function () {
        transactionToEdit = this.parentElement.id;
        const row = this.parentElement.parentElement.parentElement;
        const product = row.children[1].innerText;
        const amount = row.children[2].innerText;
        const type = row.children[3].innerText;

        $('#modal-transaction-product').val(product);
        $('#modal-transaction-amount').val(amount);
        $('#modal-transaction-type').val(type);
    });

    $('#add-transaction-main-button').click(() => {
        clearModal();
        transactionToEdit = null
    });
});
