document.addEventListener('DOMContentLoaded', function () {
    const telefoneInput = document.getElementById('telefone');

    telefoneInput.addEventListener('input', function (event) {
        let inputValue = event.target.value.replace(/\D/g, '');
        if (inputValue.length > 11) {
            inputValue = inputValue.slice(0, 11);
        }

        const formattedValue = inputValue
            .replace(/^(\d{2})(\d)/g, '($1) $2')
            .replace(/(\d{5})(\d{4})$/, '$1-$2');

        event.target.value = formattedValue;
    });
});