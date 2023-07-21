$(document).ready(function(){

    $('#open-button').click(function (){
        $('.filter-div').css('max-height', '450px');
        $('.filter-div').css('background-color', 'white');
        $('.filter-div').css('box-shadow', '2.8px 2.8px 2.2px rgba(0, 0, 0, 0.02), 6.7px 6.7px 5.3px rgba(0, 0, 0, 0.028), 12.5px 12.5px 10px rgba(0, 0, 0, 0.035), 22.3px 22.3px 17.9px rgba(0, 0, 0, 0.042), 41.8px 41.8px 33.4px rgba(0, 0, 0, 0.05), 100px 100px 80px rgba(0, 0, 0, 0.07);\n');
        $('#filter-container').slideDown();
        $('#open-button').slideUp();
    });

    $('#close-button').click(function (){
        $('.filter-div').css('max-height', '100px');
        $('.filter-div').css('background-color', 'transparent');
        $('.filter-div').css('box-shadow', 'none');
        $('#filter-container').slideUp();
        $('#open-button').slideDown();
    });
});