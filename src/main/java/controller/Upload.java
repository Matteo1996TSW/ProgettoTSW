package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.ProdottoDAO;
import model.Archiviazione_.ArchivioDati;
import model.Archiviazione_.ArchivioDatiDAO;
import model.Archiviazione_.HDD_.Hdd;
import model.Archiviazione_.SDD_.Ssd;
import model.CASE_.Case;
import model.CASE_.CaseDAO;
import model.CATALOGO_.Catalogo;
import model.CATALOGO_.CatalogoDAO;
import model.CPU_.Cpu;
import model.CPU_.CpuDAO;
import model.DISSIPATORE_.Dissipatore;
import model.DISSIPATORE_.DissipatoreDAO;
import model.GPU_.Gpu;
import model.GPU_.GpuDAO;
import model.MOBO_.Mobo;
import model.MOBO_.MoboDAO;
import model.PSU_.Psu;
import model.PSU_.PsuDAO;
import model.RAM_.Ram;
import model.RAM_.RamDAO;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

//Upload di un prodotto (info. e foto) nel DB/server

//Configurazione di multipart per permettere di prendere le immagini
@MultipartConfig(location = "/tmp/"
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "Upload", value = "/Upload")
public class Upload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String Marca = request.getParameter("marca");
        String Modello = request.getParameter("modello");
        String prezzo1 = request.getParameter("prezzo");
        //Il prezzo deve essere inserito alla maniera americana. In caso di errore lo correggo
        prezzo1=prezzo1.replace(",", ".");
        Double Prezzo = Double.parseDouble(prezzo1);
        Integer Quantita = Integer.parseInt(request.getParameter("quantita"));
        String Tipo = request.getParameter("tipo");

        if(Marca==null || Modello==null || Prezzo==null || prezzo1==null ||  Quantita==null || Tipo==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer wattaggio = null;
        if (request.getParameter("wattaggio") != null) {
            wattaggio = Integer.parseInt(request.getParameter("wattaggio"));
        }
        Float frequenza = null;
        if (request.getParameter("frequenza") != null) {
            frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer n_Core = null;
        if (request.getParameter("N_Core") != null) {
            n_Core = Integer.parseInt(request.getParameter("N_Core"));
        }
        Integer N_Ram = null;
        if (request.getParameter("N_Ram") != null) {
            N_Ram = Integer.parseInt(request.getParameter("N_Ram"));
        }
        Integer n_Usb = null;
        if (request.getParameter("N_Usb") != null) {
            n_Usb = Integer.parseInt(request.getParameter("N_Usb"));
        }
        Integer n_Pci = null;
        if (request.getParameter("N_Pci") != null) {
            n_Pci = Integer.parseInt(request.getParameter("N_Pci"));
        }
        Integer mBs = null;
        if (request.getParameter("MBs") != null) {
            mBs = Integer.parseInt(request.getParameter("MBs"));
        }
        Integer vram = null;
        if (request.getParameter("Vram") != null) {
            vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Short formaMobo = null;
        if (request.getParameter("formaMobo") != null) {
            formaMobo = Short.parseShort(request.getParameter("formaMobo"));
        }

       
        String descrizione = request.getParameter("descrizione");
        try {
            //Carica il prodotto nal DB.
            switch (Tipo) {
                case "CPU":
                    Cpu cpu = new Cpu();
                    cpu.setMarca(Marca);
                    cpu.setModello(Modello);
                    cpu.setPrezzo(Prezzo);
                    cpu.setQuantita(Quantita);
                    cpu.setWattaggio(wattaggio);
                    cpu.setFrequenza(frequenza);
                    cpu.setN_Core(n_Core);
                    cpu.setUrl("Default/CPU.jpg");
                    cpu.setDescrizione(descrizione);
                    CpuDAO.Upload(cpu);
                    break;
                case "CASE":
                    Case case_ = new Case();
                    case_.setMarca(Marca);
                    case_.setModello(Modello);
                    case_.setPrezzo(Prezzo);
                    case_.setQuantita(Quantita);
                    case_.setFormaMobo(formaMobo);
                    case_.setUrl("Default/CASE.jpg");
                    case_.setDescrizione(descrizione);
                    CaseDAO.Upload(case_);
                    break;
                case "DISSIPATORE":
                    Dissipatore diss = new Dissipatore();
                    diss.setMarca(Marca);
                    diss.setModello(Modello);
                    diss.setPrezzo(Prezzo);
                    diss.setQuantita(Quantita);                   
                    diss.setUrl("Default/Dissipatore.jpg");
                    diss.setDescrizione(descrizione);
                    DissipatoreDAO.Upload(diss);
                    break;
                case "PSU":
                    Psu psu = new Psu();
                    psu.setMarca(Marca);
                    psu.setModello(Modello);
                    psu.setPrezzo(Prezzo);
                    psu.setQuantita(Quantita);
                    psu.setN_Watt(wattaggio);
                    psu.setUrl("Default/PSU.jpg");
                    PsuDAO.Upload(psu);
                    break;
                case "MOBO":
                    Mobo mobo = new Mobo();
                    mobo.setMarca(Marca);
                    mobo.setModello(Modello);
                    mobo.setPrezzo(Prezzo);
                    mobo.setQuantita(Quantita);
                    mobo.setForma(formaMobo);
                    mobo.setN_RAM(N_Ram);
                    mobo.setN_USB(n_Usb);
                    mobo.setN_PCI(n_Pci);
                    mobo.setUrl("Default/MOBA.jpg");
                    mobo.setDescrizione(descrizione);
                    MoboDAO.Upload(mobo);
                    break;
                case "RAM":
                    Ram ram = new Ram();
                    ram.setMarca(Marca);
                    ram.setModello(Modello);
                    ram.setPrezzo(Prezzo);
                    ram.setQuantita(Quantita);
                    ram.setFrequenza(frequenza);
                    ram.setUrl("Default/RAM.jpg");
                    ram.setDescrizione(descrizione);
                    RamDAO.Upload(ram);
                    break;
                case "HDD":
                    Hdd hdd = new Hdd();
                    hdd.setMarca(Marca);
                    hdd.setModello(Modello);
                    hdd.setPrezzo(Prezzo);
                    hdd.setQuantita(Quantita);
                    hdd.setMBs(mBs);
                    hdd.setUrl("Default/HDD.jpg");
                    hdd.setDescrizione(descrizione);
                    ArchivioDatiDAO.Upload(hdd);
                    break;
                case "SSD":
                    Ssd ssd = new Ssd();
                    ssd.setMarca(Marca);
                    ssd.setModello(Modello);
                    ssd.setPrezzo(Prezzo);
                    ssd.setQuantita(Quantita);
                    ssd.setMBs(mBs);
                    ssd.setUrl("Default/SSD.jpg");
                    ssd.setDescrizione(descrizione);
                    ArchivioDatiDAO.Upload(ssd);
                    break;
                case "GPU":
                    Gpu gpu = new Gpu();
                    gpu.setMarca(Marca);
                    gpu.setModello(Modello);
                    gpu.setPrezzo(Prezzo);
                    gpu.setQuantita(Quantita);
                    gpu.setWattaggio(wattaggio);
                    gpu.setFrequenza(frequenza);
                    gpu.setVRam(vram);
                    gpu.setUrl("Default/GPU.jpg");
                    gpu.setDescrizione(descrizione);
                    GpuDAO.Upload(gpu);
                    break;
                default:
                	break;
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Rigenera il catalogo con i nuovi prodotti
        CatalogoDAO service = new CatalogoDAO();
        Catalogo newCatalogo = new Catalogo();
        newCatalogo.setCatalogo(service.doRetriveAll());
        HttpSession ss = request.getSession();
        ss.setAttribute("catalogo", newCatalogo);

        request.getRequestDispatcher("./WEB-INF/admin.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
