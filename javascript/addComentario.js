const comentario = document.getElementById('comentario').value;
const token = localStorage.getItem(token);
const idDisciplina = getDisciplina(idDisciplina);

function enviaComentario(){
    return fetch('https://pjsw.herokuapp.com/api/v1/comentarios"', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      "Authorization":token
    },
    body:JSON.stringify({id:idDisciplina,comentario:comentario})
})};

function addComentario(){
    enviaComentario().then(result =>{return result.json}).then(data =>{
    if(data.message == "Você não tem permissão. Por favor, faça login." ||data.message=="Ops, algo deu errado."){
        alert("Ops, algo deu errado, tente nocamente")
    }else{
        alert("atualize a pagina!!")
    }
    })
}
