let modal = document.getElementById('modal')
modal.addEventListener('show.bs.modal', function (event) {
    let botao = event.relatedTarget
    let produtoid = botao.getAttribute('data-bs-whatever')
    let produtodescricao = botao.getAttribute('data-bs-whatever2')
    let idProduto = modal.querySelector('#idproduto')
    let descricaoModal = modal.querySelector('#descricaoProduto')
    idProduto.value = produtoid
    descricaoModal.innerHTML = produtodescricao
})

let modal2 = document.getElementById('modal2')
modal2.addEventListener('show.bs.modal', function (event) {
    let botao = event.relatedTarget
    let vendaid = botao.getAttribute('data-bs-whatever')
    let total = botao.getAttribute('data-bs-whatever2')
    let idVenda = modal2.querySelector('#vendaid')
    let totalValor = modal2.querySelector('#total')
    idVenda.value = vendaid
    totalValor.innerHTML = parseFloat(total).toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        style: 'currency',
        currency: 'BRL'
    })
})