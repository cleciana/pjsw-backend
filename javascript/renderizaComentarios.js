import {getComentario, comentarios} from './javascript/requisicaoComentario.js';
import './javascript/mural.js';

function render() {
    let $comen = document.getElementById("comentarios");
    $comen.innerHTML = '';
    console.log(comentarios)
    comentarios.map(r => renderiza(r, $comen));
}

function renderiza(r,t) {
    let novo = document.createElement("ps-comentario");
    novo.setAttribute('id', r[0]);
    novo.setAttribute('usuario', r[1]);
    novo.setAttribute('hora', r[2]);
    novo.setAttribute('comentario', r[3]);
    t.appendChild(novo);
}


async function init() {
    await getComentario();
    render()
}


init()
