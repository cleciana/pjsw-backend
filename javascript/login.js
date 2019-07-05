const usuario = document.getElementById('txtUsuario').value;
const senha = document.getElementById('txtSenha').value;

function fazLogin(){
    return fetch('https://pjsw.herokuapp.com/api/v1/auth/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ email:usuario,password:senha})
  });
}

function login(){
    login().then(result => {
        return result.json()
    }).then(data => {
        console.log(data)
        if(data.message == 'User not found.'){
            alert('Usario ou senha incorretos!!');
        }else if(data.message == 'Password invalid or incorrect. Try again.'){
            alert('senha incorreta ou invalida, tente novamente')}
        else{
            localStorage.setItem('token', data.token)
            window.open('home.html', '_self')
        }
    })
}

