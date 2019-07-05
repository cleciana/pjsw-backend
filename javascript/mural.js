class MuralComentario extends HTMLElement{
    constructor() {
        super();
        this.$shadow = this.attachShadow({"mode": "open"});
    }

    connectedCallback() {
        this.id = this.getAttribute('id');
        this.usuario = this.getAttribute('usuario');
        this. comentario= this.getAttribute('comentario');
        this.render();
    }

    render() {
        this.$shadow.innerHTML = 
            `<link rel="stylesheet" href=";/css/comentario.css">
            <div>
                <div class="dados">
                    <h4 class="usuario" >${this.usuario}</h4>
                </div>
                <div class="coisas">
                    <h3 class="comentario">${this.comentario}</h3>
                    <img id="lixeira" onclick="excluir(this)" title= "${this.id}"src="https://image.flaticon.com/icons/png/512/39/39220.png">
                </div>
            </div>`;

    }
}

window.customElements.define('ps-comentario', MuralComentario)
