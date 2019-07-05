function like(){
    var disc = localStorage.getItem('idDisciplina');
    var token = localStorage.getItem('token');
    
    
    fetch('https://pjsw.herokuapp.com/api/v1/disciplina/' + disc, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          "Authorization":token
        }
      }).then(resul =>{return resul.json()}).then(data =>{
          console.log(data)
          acaoLike(data)
      })
}

function acaoLike(msg){
    if(msg.message == '"Disciplina nao existe."'){
        alert('Por Favor, tente novamente!!')
    }else if(msg.message == 'Você não tem permissão. Por favor, faça login.'){
        alert('Você não esta cadastrado, cadastre-se para poder curtir!!')
    }else{
        let like = document.getElementById('textoCurti');
        let qtdLike = document.getElementById('qtdLike')
        let tmp = msg.map(r => `${r.qtdLikes}`)
        if(like.textContent == 'curtir'){
            like.textContent = 'curtiu'
        }else{
            like.textContent = 'curtir';
        }
        qtdLike.textContent = tmp;
    }

}

