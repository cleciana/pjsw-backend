const token = localStorage.getItem(token)
function rankingLike(){
    return fetch('https://pjsw.herokuapp.com/v1/disciplinas/1',{
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization':token
        },
      })
}

function carregaRanking(){
    rankingLike().then(resul=>{return resul.json()}).then(data=>{
        let disc = [];
        console.log(data);
        if(data.message == "Voce nao tem permissao. Por favor, faca login."){
            alert('algo deu errado, tente novamente...')
        }else{
            disc = disc.concat(data.map(r => `${r.nome} - ${r.like}`));
            listar(disc);
        }
    })
}

function listar(arr){
    let node = document.getElementById("rankingLike");
    
    var i = 0;
    var n = arr.length;
  while(i < n){
      let filho = document.createElement('li')
      filho.textContent = arr[i];
      node.appendChild(filho);
      i++;
  }
}
carregaRanking()
