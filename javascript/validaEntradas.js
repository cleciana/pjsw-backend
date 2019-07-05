function validacaoEmail() {
    let field = document.getElementById("txtEmail");
    console.log(field);
    let usuario = field.value.substring(0, field.value.indexOf("@"));
    let dominio = field.value.substring(field.value.indexOf("@")+ 1, field.value.length);
     
    if ((usuario.length >=1) &&
        (dominio.length >=3) && 
        (usuario.search("@")==-1) && 
        (dominio.search("@")==-1) &&
        (usuario.search(" ")==-1) && 
        (dominio.search(" ")==-1) &&
        (dominio.search(".")!=-1) &&      
        (dominio.indexOf(".") >=1)&& 
        (dominio.lastIndexOf(".") < dominio.length - 1)) {
        document.getElementById("msgemail").innerHTML="E-mail válido";
    }
    else{
        document.getElementById("msgemail").innerHTML="<font color='red'>E-mail inválido </font>";
        alert("E-mail invalido");
    }
}

function validaSenha(){
    var senha = document.getElementById("txtSenha").value;
    var confirmaSenha = document.getElementById("txtConfirmarSenha").value;
    
    if (senha != confirmaSenha){
        document.getElementById("msgemail").innerHTML="<font color='red'>SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS</font>";
        alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS");
    }else if(senha.length < 6){
        document.getElementById("msgemail").innerHTML="<font color='red'>Senha Invalida!!\nEla Precisar Ter Mais de 6 caracteres</font>";
        alert("Senha Invalida!!\nEla Precisar Ter Mais de 6 caracteres");
    }else{
        document.getElementById("msgemail").innerHTML="Senha válida";
    }
        

}