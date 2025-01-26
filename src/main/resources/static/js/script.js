document.addEventListener('DOMContentLoaded', function () {
    const telefoneInput = document.getElementById('telefone');

    telefoneInput.addEventListener('input', function (event) {
        let inputValue = event.target.value.replace(/\D/g, ''); // Remove tudo que não for número
        if (inputValue.length > 11) {
            inputValue = inputValue.slice(0, 11); // Limita a 11 dígitos
        }

        const formattedValue = inputValue
            .replace(/^(\d{2})(\d)/g, '($1) $2') // Adiciona parênteses no DDD
            .replace(/(\d{5})(\d{4})$/, '$1-$2'); // Adiciona o traço no número

        event.target.value = formattedValue; // Atualiza o valor do campo com a máscara
    });
});