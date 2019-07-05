const comentarios = [];
const idDisciplina = localStorage.getItem(idDisciplina);

async function getComentario(){
  let dis = getDisciplina();  
  let response = await fetch('https://pjsw.herokuapp.com/api/v1/comentarios/',{
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },body: JSON.stringify({id:idDisciplina})
  })
  let data = await response.json()
  let tmp = await carregaComentario(data);
  comentarios.push(...tmp);
  console.log(comentarios)
}

function carregaComentario(data){
  var lista = document.getElementById('listaComentarios');
  var comen = [];
  comen = comen.concat(data.map(r => geraArray(r)));
  return comen;
}

const geraArray = (obj =>{
  var arra = [];
  arra.push(obj.id);
  arra.push(obj.usuario);
  arra.push(obj.hora);
  arra.push(obj.comentario);
  return arra;
})

export{getComentario, comentarios}