function validateMarcaAndModello() {
    let marcaAndModello = /^[a-zA-Z0-9]{1,30}$/;
    if(!(marcaAndModello.test($("#marca").val()))){
        console.error("Err: Marca non valida");
    }
    if(!(marcaAndModello.test($("#modello").val()))){
        console.error("Err: Modello non valido");
    }
    return marcaAndModello.test($("#marca").val()) && marcaAndModello.test($("#modello").val());
}

function validatePrezzo() {
    let prezzo = /^(?!0\d)\d*(\.\d+)?$/;
    if(!(prezzo.test($("#prezzo").val()))){
        console.error("Err: Prezzo non valida");
    }
    return prezzo.test($("#prezzo").val());
}

function validateQuantita() {
    let quant = /^[0-9]{0,3}$/;
    if(!(quant.test($("#quantita").val()))){
        console.error("Err: Quantita non valida");
    }
    return quant.test($("#quantita").val());
}

function validateDesc() {
    let desc = /^[a-zA-Z0-9].{1,10000}$/;
    if(!(desc.test($("#desc").val()))){
        console.error("Err: Descrizione non valida");
    }
    return desc.test($("#desc").val());
}

function validateCompulsoryProduct() {
    return (validateMarcaAndModello() && validatePrezzo() && validateQuantita() && validateDesc());
}

function validateByTipo(tipo) {
    switch (tipo) {
        case "CPU" :
            const ncore = new RegExp('[1-9][0-9]{0,1}');
            let str = document.getElementById("ncore").value;
            return ncore.test(str);
            break;
        case "CASE" :
            let formaMobo = /[0-2]$/;
            let num = /^[0-9]{1,2}$/;
            let strFormaMobo = document.getElementById("formaMobo").value;
            let strNram = document.getElementById("n_ram").value;
            let strNusb = document.getElementById("n_usb").value;
            let strNpci = document.getElementById("n_pci").value;
            return formaMobo.test(strFormaMobo) && num.test(strNram) && num.test(strNusb) && num.test(strNpci);
            break;
        case "DISSIPATORE" :
            let wCpu = /[1-9][0-9]{0,3}/;
            let strwCpu = document.getElementById("wCpu").value;
            return wCpu.test(strwCpu);
            break;
        case "GPU" :
            let wGpu = /[1-9][0-9]{0,3}/;
            let freqGpu = /^(?!0\d)\d*(\.\d+)?$/mg; //Frequenza espressa in GHz
            let vRam = /[1-9][0-9]{0,2}/;
            let strwGpu = document.getElementById("wGpu").value;
            let strFreqGpu = document.getElementById("freqGpu").value;
            let strvRam = document.getElementById("vRam").value;
            return (wGpu.test(strwGpu) && freqGpu.test(strFreqGpu) && vRam.test(strvRam));
            break;
        case "MOBO" :
            let forma = /[0-2]/;
            let n = /[1-9][0-9]{0,2}/;
            let strForma = document.getElementById("forma").value;
            let strnRam = document.getElementById("nRam").value;
            let strnPci = document.getElementById("nPci").value;
            let strnUsb = document.getElementById("nUsb").value;
            return (forma.test(strForma) && n.test(strnRam) && n.test(strnPci) && n.test(strnUsb));
            break;
        case "PSU" :
            let watt = /^[1-9](\d{1,4})?/;
            let strWatt = document.getElementById("watt").value;
            return watt.test(strWatt);
            break;
        case "RAM" :
            let frequenza = /^(?!0\d)\d*(\.\d+)?$/mg;
            let strFrequenza = document.getElementById("frequenza").value;
            return frequenza.test(strFrequenza);
            break;
        case "HDD":
        case "SSD":
            let mbps = /\d{1,100}/;
            let strMbps = document.getElementById("mbps").value;
            return mbps.test(strMbps);
            break;
        default :
            return false;
    }


    return false;
}

function validateProductUpdate() {
    let tipo = $('#tipo').val();

    if(!(validateByTipo(tipo))){
        console.error("Err: Validazione su tipo non valid");
    }

    if(!(validateCompulsoryProduct()))
        console.error("ERR: Validazione prodotti obbligatori non valida");

    if (validateCompulsoryProduct() && validateByTipo(tipo))
        return true;
    return false;
}