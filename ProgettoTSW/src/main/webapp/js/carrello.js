$(document).ready(function(){
    function saveOldQuant() {
        let id = $('#submit').parent().attr("id");
        return $('#quantOf' + id).val();
    }
    let oldQuant = saveOldQuant();
    $('.submit').click(function(){
        let id = $(this).parent().attr("id");
        let quantOf = $('#quantOf'+id);
        let quant = Number(quantOf.val());
        if(quant > 0 && quant<=Number(quantOf.attr("max"))) {
            $.ajax({
                url: 'modQuantCartDB',
                type: 'GET',
                data: {
                    attr_id: id, attr_newQuant: quant, attr_OldQuant: oldQuant
                },
                success: function (){
                    alert("Quantità aggiornata correttamente");
                },
                error: function () {
                    $(location).attr('href','error.page.jsp');
                }
            });
            oldQuant = quant;
        }
        else{
            quantOf.val(oldQuant);
        }
    });
});