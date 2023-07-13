function testNickname() {
    let reNick = /(?=.{2,50}$)^([a-zA-Z]{1,}){1}( [a-zA-Z]{1,})*$/ ;
    if(reNick.test($("#nick").val()))
        return true;
    alert('Hai inserito un nickname non valido. Dovrebbe essere tra i 2 e i 50 caratteri e solo con lettere maiuscole o minuscole');
    return false;
}

function testMail() {
    const cerca = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    if(cerca.test($("#mail").val()))
        return true;
    alert('Hai inserito una mail non valida');
    return false;
}

function testPassword() {
    const cerca = /^.{5,130}$/;
    if (cerca.test($("#password").val()))
        return true;
    alert('Hai inserito una passwordnon valida. La password deve essere minimo 5 caratteri e massimo 130');
    return false;
}

function testTel() {
    const cerca = /^\+[0-9]{2}[0-9]{10}$/;
    if((cerca.test($("#tel").val())) && ($("#tel").val().length===13)) return true;
    alert('Il numero di telefono deve iniziare con il prefisso \"+XX\ e deve essere di massimo 13 caratteri"');
    return false;
}

function testCap() {
    const cerca = /^[0-9]{5}$/;
    if(cerca.test($("#cap").val()))
        return true;
    alert('Hai inserito un CAP non valido. Dovrebbe essere composto da 5 numero tra 0 e 9');
    return false;
}

function testProv() {
    const cerca = /^[A-Z]{2}$/;
    if(cerca.test($("#provincia").val()))
        return true
    alert('Devi inserire solo le iniziali della provincia in maiuscolo, es:CE, SA, RO');
    return false;
}

function testVia() {
    const cerca = /(?=.{3,100}$)^([a-zA-Z]{1,}){1}([a-zA-Z]{1,}\s)*[1-9]{1,3}$/;
    if(cerca.test($("#via").val()))
        return true;
    alert('La via deve essere corredata del numero dell\'abitazione e massimo 100 caratteri es: Giacomo Matteotti 32');
    return false;
}

function testCitta() {
    const cerca = /(?=.{2,100}$)^([a-zA-Z]{1,}){1}( [a-zA-Z]{1,})*$/;
    if(cerca.test($("#citta").val()))
        return true;
    alert('La città deve essere composta da minimo due, massimo 100 caratteri e può contenere solo lettere');
    return false;
}

function validateForm(){
    return testNickname() && testMail() && testPassword() && testTel() && testCap() && testProv()
        && testVia() && testCitta();
}

function validateInfoPers(){
    if(testNickname() && testTel())
        return true;
    return false;
}
function validateInfoSped(){
    if(testCap() && testProv() && testVia() && testCitta()){
        return true;
    }
    return false;
}