
const nome = document.getElementById('txtNome').value;
const sobrenome = document.getElementById('txtSobrenome').value;
const email = document.getElementById('txtEmail').value;
const senha = document.getElementById('txtSenha').value;

function cadastraUsuario(){
  return fetch('https://pjsw.herokuapp.com/api/v1/user/', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({name: nome, lastName: sobrenome, email:email,password:senha})
  });
}

function cadastra(){
  cadastraUsuario().then(result => {
  return result.json()
}).then(data => {
  console.log(data)
  if(data.message == 'Voce nao tem permissao. Por favor, faca login.'){
    alert('E-mail já esta cadastrado!!!')
  }else if(data.message == 'Ops, algo deu errado.'){
    alert('Ops, algo deu errado.')

  }else{
    window.open('login.html', '_self')
  }
})}
