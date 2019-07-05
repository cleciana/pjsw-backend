function apagaComentario(id){
    return fetch("https://pjsw.herokuapp.com/api/v1/comentarios", {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          "Authorization":token
        },
        body: JSON.stringify({ id:id})
      });
}
function excluir($i){
    apagaComentario($i.title).then(result=>{
        return result.json()
    }).then(data=>{
        if(data.message=='O Comentario nao existe.'){
            alert("Ops... algo deu errado, certeza que esse comentario é seu?")
        }else{
            alert("aluatize a pagina!!")
        }
    })
}