//funzione per controllare che i campi siano stati inseriti correttamente
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
    let desc = /^.{1,10000}$/;
    if(!(desc.test($("#descrizione").val()))){
        console.error("Err: Descrizione non valida");
    }
    return desc.test($("#descrizione").val());
}

function validateCompulsoryProduct() {
    return (validateMarcaAndModello() && validatePrezzo() && validateQuantita() && validateDesc());
}

function validateByTipo() {
    let tipo = document.getElementById("tipo").value;
    switch (tipo) {
        case "CPU" :
            const ncore = new RegExp('[1-9][0-9]{0,1}');
            let str = document.getElementById("n_core").value;
            return ncore.test(str);
            break;
        case "CASE" :
            let formaMobo = /^[0-2]$/;
            let num = /^[0-9]{1,2}$/;
            let strFormaMobo = document.getElementById("formaMobo").value;
            let strNram = document.getElementById("n_ram").value;
            let strNusb = document.getElementById("n_usb").value;
            let strNpci = document.getElementById("n_pci").value;
            return formaMobo.test(strFormaMobo) //&& num.test(strNram) && num.test(strNusb) && num.test(strNpci);
            break;
        case "DISSIPATORE" :
            let wCpu = /[1-9][0-9]{0,3}/;
            let strwCpu = document.getElementById("w_cpu").value;
            return wCpu.test(strwCpu);
            break;
        case "GPU" :
            let wGpu = /[1-9][0-9]{0,3}/;
            let freqGpu = /^(?!0\d)\d*(\.\d+)?$/mg; //Frequenza espressa in GHz
            let vRam = /[1-9][0-9]{0,2}/;
            let strwGpu = document.getElementById("wattaggio").value;
            let strFreqGpu = document.getElementById("frequenza").value;
            let strvRam = document.getElementById("vram").value;
            return (wGpu.test(strwGpu) && freqGpu.test(strFreqGpu) && vRam.test(strvRam));
            break;
        case "MOBO" :
            let forma = /[0-2]/;
            let n = /[1-9][0-9]{0,2}/;
            let strForma = document.getElementById("formaMobo").value;
            let strnRam = document.getElementById("n_ram").value;
            let strnPci = document.getElementById("n_pci").value;
            let strnUsb = document.getElementById("n_usb").value;
            return (forma.test(strForma) && n.test(strnRam) && n.test(strnPci) && n.test(strnUsb));
            break;
        case "PSU" :
            let watt = /^[1-9](\d{1,4})?/;
            let strWatt = document.getElementById("wattaggio").value;
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
            let strMbps = document.getElementById("mbs").value;
            return mbps.test(strMbps);
            break;
        default :
            return false;
    }


    return false;
}

function validateProductUpdate() {
    let tipo = document.getElementById("tipo").value;

    if(!(validateByTipo(tipo))){
        console.error("Err: Validazione su tipo non valid");
    }

    if(!(validateCompulsoryProduct()))
        console.error("ERR: Validazione prodotti obbligatori non valida");

    if (validateCompulsoryProduct() && validateByTipo())
        return true;
    return false;
}

function disableAll() {
    document.getElementById("marca").disabled = true;
    document.getElementById("modello").disabled = true;
    document.getElementById("prezzo").disabled = true;
    document.getElementById("quantita").disabled = true;
    document.getElementById("wattaggio").disabled = true;
    document.getElementById("frequenza").disabled = true;
    document.getElementById("n_core").disabled = true;
    document.getElementById("n_ram").disabled = true;
    document.getElementById("n_usb").disabled = true;
    document.getElementById("n_pci").disabled = true;
    document.getElementById("mbs").disabled = true;
    document.getElementById("vram").disabled = true;
    document.getElementById("w_cpu").disabled = true;
    document.getElementById("formaMobo").disabled = true;
    document.getElementById("descrizione").disabled = true;
}

function aggiorna(){
    document.getElementById("stampa").style.display="none";
    document.getElementById("frameCatalogo").style.display="flex";
    document.getElementById("frameCatalogo").src="Catalogo.jsp";
}

function resetForm(){
    document.getElementById("wattaggio").disabled = false;
    document.getElementById("frequenza").disabled = false;
    document.getElementById("n_core").disabled = false;
    document.getElementById("n_ram").disabled = false;
    document.getElementById("n_usb").disabled = false;
    document.getElementById("n_pci").disabled = false;
    document.getElementById("mbs").disabled = false;
    document.getElementById("vram").disabled = false;
    document.getElementById("w_cpu").disabled = false;
    document.getElementById("formaMobo").disabled = false;
}

function enableDefault(){
    document.getElementById("marca").disabled = false;
    document.getElementById("modello").disabled = false;
    document.getElementById("prezzo").disabled = false;
    document.getElementById("quantita").disabled = false;
    document.getElementById("descrizione").disabled = false;
}

//prende il tipo di prodotto scelto e disabilita i campi non necessari
function controllo() {
	
    let header = document.getElementsByClassName("instruction-header");
    header[0].style.display = "none";
    let tipo = document.getElementById("tipo").value;
    let wattaggio, frequenza, core, ram, usb, pci, vram, wcpu, forma, mbs;
    resetForm();
    enableDefault();
    switch (tipo) {
        case "HDD":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            break;

        case "SSD":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            break;

        case "CASE":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "CPU":
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "DISSIPATORE":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "GPU":
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "MOBO":
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            break;

        case "PSU":
            frequenza = document.getElementById("frequenza"); frequenza.disabled = true; frequenza.value = "";
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            break;

        case "RAM":
            core = document.getElementById("n_core"); core.disabled = true; core.value = "";
            ram = document.getElementById("n_ram"); ram.disabled = true; ram.value = "";
            usb = document.getElementById("n_usb"); usb.disabled = true; usb.value = "";
            pci = document.getElementById("n_pci"); pci.disabled = true; pci.value = "";
            mbs = document.getElementById("mbs"); mbs.disabled = true; mbs.value = "";
            vram = document.getElementById("vram"); vram.disabled = true; vram.value = "";
            wcpu = document.getElementById("w_cpu"); wcpu.disabled = true; wcpu.value = "";
            forma = document.getElementById("formaMobo"); forma.disabled = true; forma.value = "";
            wattaggio = document.getElementById("wattaggio"); wattaggio.disabled = true; wattaggio.value = "";
            break;

        default:
            disableAll();
            break;

    }
}

//Funzione che stampa il form per l'inserimento di un nuovo prodotto nel database
function nuovo() {
    document.getElementById("frameCatalogo").style.display="none";
    document.getElementById("stampa").style.display="flex";
    document.getElementById("stampa").innerHTML =
        "<div class=\"instruction-header\"><h2>Per compilare i campi selezionare una tipologia di pezzo</h2></div>" +
        "<form action=\"Upload\" method=\"\" onsubmit=\"return validateProductUpdate()\" enctype=\"multipart/form-data\" class=\"flex-container\"'>" +
        //La funzione controllo() è chiamata per disabilitare i campi del form in base al tipo di prodotto scelto
        "<select name=\"tipo\" id=\"tipo\"  class=\"form-input\" onchange=\"controllo()\")>" +
        "<option value=\"none\" selected disabled hidden>Selezione un tipo</option>"+
        "<option \" value=\"HDD\">Hard Disk</option>" +
        "<option  value=\"SSD\">SSD</option>" +
        "<option  value=\"CASE\">Case</option>" +
        "<option  value=\"CPU\">CPU</option>" +
        "<option  value=\"DISSIPATORE\">Dissipatore</option>" +
        "<option  value=\"GPU\">GPU</option>" +
        "<option  value=\"MOBO\">Scheda madre</option>" +
        "<option  value=\"PSU\">Alimentatore</option>" +
        "<option  value=\"RAM\">RAM</option>" +
        "</select>" +
        "<input type=\"text\" name=\"marca\" id=\"marca\"  class=\"form-input\" placeholder=\"Marca\" required disabled />" +
        "<input type=\"text\" name=\"modello\" id=\"modello\"  class=\"form-input\" placeholder=\"Modello\" required disabled/>" +
        "<input type=\"text\" name=\"prezzo\" id=\"prezzo\"  class=\"form-input\" placeholder=\"Prezzo\" required disabled/>" +
        "<input type=\"text\" name=\"quantita\" id=\"quantita\"  class=\"form-input\" placeholder=\"Quantità\" required disabled/>" +
        "<input type=\"text\" name=\"wattaggio\" id=\"wattaggio\"  class=\"form-input\" placeholder=\"Wattaggio\" disabled/>" +
        "<input type=\"text\" name=\"frequenza\" id=\"frequenza\"  class=\"form-input\" placeholder=\"Frequenza\" disabled/>" +
        "<input type=\"text\" name=\"N_Core\" id=\"n_core\"  class=\"form-input\" placeholder=\"Numero di core\" disabled/>" +
        "<input type=\"text\" name=\"N_Ram\" id=\"n_ram\"  class=\"form-input\" placeholder=\"Numero di slot RAM\" disabled/>" +
        "<td><input type=\"text\" name=\"N_Usb\" id=\"n_usb\"  class=\"form-input\" placeholder=\"Numero di porte USB\" disabled/></td>" +
        "<input type=\"text\" name=\"N_Pci\" id=\"n_pci\"  class=\"form-input\" placeholder=\"Numero di slot PCI\" disabled/>" +
        "<input type=\"text\" name=\"MBs\" id=\"mbs\"  class=\"form-input\" placeholder=\"Mb/s\" disabled/>" +
        "<input type=\"text\" name=\"Vram\" id=\"vram\"  class=\"form-input\" placeholder=\"Quantità VRAM\" disabled/>" +
        "<input type=\"text\" name=\"W_Cpu\" id=\"w_cpu\"  class=\"form-input\" placeholder=\"Massimo watt dissipabili\" disabled/>" +
        "<input type=\"text\" name=\"formaMobo\" id=\"formaMobo\"  class=\"form-input\" placeholder=\"Formato scheda madre\" disabled/>" +
        "<input type=\"text\" name=\"descrizione\" id=\"descrizione\"  class=\"form-input\" placeholder=\"Descrizione\" required disabled/>" +
        "<input type=\"submit\" value=\"Invia\" class=\"form-input\"/>" +
        "</form>";

}