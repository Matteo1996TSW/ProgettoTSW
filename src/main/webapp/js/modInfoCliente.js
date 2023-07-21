$(document).ready(function() {
    let btnInfoCliente = $('#submitModInfoCliente');
    let btnInfoSpedCliente = $('#submitModInfoSped');
    let btnPass = $('#submitModPass');

    btnInfoCliente.click(function () {
        if(validateInfoPers()) {
            let nick = $('#nick').val();
            let tel = $('#tel').val();
            $.ajax({
                url: 'updateInfoCliente',
                type: 'POST',
                data: {
                    input_nick: nick, input_tel: tel
                },
                success: function (response) {
                    alert("Informazioni inserite correttamente");
                    btnInfoCliente.attr('disabled', true);
                },
                error: function () {
                $(location).attr('href','error.page.jsp');
                }
            });
        }
    });
    btnInfoSpedCliente.click(function(){
        if(validateInfoSped()){
            let via = $('#via').val();
            let provincia = $('#provincia').val();
            let citta = $('#citta').val();
            let cap = $('#cap').val();
            $.ajax({
                url: 'modInfoSpedizione',
                type: 'POST',
                data: {
                    input_via: via, input_provincia: provincia, input_citta: citta, input_cap: cap
                },
                success: function (response){
                    alert("Informazioni inserite correttamente");
                    btnInfoSpedCliente.attr('disabled', true);
                },
                error: function () {
                    $(location).attr('href','error.page.jsp');
                }
            });
        }
    });
    btnPass.click(function() {
        if(testPassword()){
            let pass = $('#password').val();
            $.ajax({
                url: 'updatePassword',
                type: 'POST',
                data: {
                    input_pass: pass
                },
                success: function (){
                    alert("Informazioni inserite correttamente");
                    btnPass.attr('disabled', true);
                },
                error: function () {
                    $(location).attr('href','error.page.jsp');
                }
            });
        }
    });
    //Blur deli input non modificati
    btnInfoCliente.attr('disabled', true);
    btnInfoSpedCliente.attr('disabled', true);
    btnPass.attr('disabled', true);
    //Se tento di modificare uno dei campi rendo il bottone visibile
    $('.infoPers').keyup(function(){
        btnInfoCliente.attr('disabled', false);
    });
    $('.infoSped').keyup(function(){
        btnInfoSpedCliente.attr('disabled', false);
    });
    $('.pass').click(function(){
        $('#password').val('');
        btnPass.attr('disabled', false);
    });
});