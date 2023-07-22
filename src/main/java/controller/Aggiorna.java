package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Prodotto;
import model.ProdottoDAO;
import model.Archiviazione_.ArchivioDati;
import model.Archiviazione_.ArchivioDatiDAO;
import model.Archiviazione_.HDD_.Hdd;
import model.Archiviazione_.HDD_.HddDAO;
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
import java.nio.file.FileSystems;
import java.sql.SQLException;

@MultipartConfig(location = "tmp"
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

////Aggiorna il prodotto nal DB e nella sessione
@WebServlet(name = "Aggiorna", value = "/Aggiorna")
public class Aggiorna extends HttpServlet {
    private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("ID"));
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        Integer quantita = Integer.parseInt(request.getParameter("quantita"));
        String desc = request.getParameter("desc");
        String url = request.getParameter("url");
        String tipo = request.getParameter("tipo");
       

        if(id==null || marca==null || modello==null || prezzo==null || quantita==null || url==null || tipo==null)
            request.getRequestDispatcher("WEB-INF/error-page.jsp").forward(request, response);

      
        //Inizializza un campo a null e in caso sia stato passato ne aggiorna il valore
        Integer wattaggio = null;
        if (request.getParameter("watt") != null) {
            wattaggio = Integer.parseInt(request.getParameter("watt"));
        }
        Float frequenza = null;
        if (request.getParameter("frequenza") != null) {
            frequenza = Float.parseFloat(request.getParameter("frequenza"));
        }
        Integer n_Core = null;
        if (request.getParameter("numCore") != null) {
            n_Core = Integer.parseInt(request.getParameter("numCore"));
        }
        Integer n_Ram = null;
        if (request.getParameter("nRam") != null) {
            n_Ram = Integer.parseInt(request.getParameter("nRam"));
        }
        Integer n_Usb = null;
        if (request.getParameter("nUsb") != null) {
            n_Usb = Integer.parseInt(request.getParameter("nUsb"));
        }
        Integer n_Pci = null;
        if (request.getParameter("nPci") != null) {
            n_Pci = Integer.parseInt(request.getParameter("nPci"));
        }
        Integer mBs = null;
        if (request.getParameter("mbs") != null) {
            mBs = Integer.parseInt(request.getParameter("mbs"));
        }
        Integer vram = null;
        if (request.getParameter("Vram") != null) {
            vram = Integer.parseInt(request.getParameter("Vram"));
        }
        Short formaMobo = null;
        if (request.getParameter("forma") != null) {
            formaMobo = Short.parseShort(request.getParameter("forma"));
        }

        try {
            //Aggiorna il prodotto nal DB. Il metodo Upload gestisce eventuali paramentri nulli
            switch (tipo) {
                case "CPU":
                    Cpu cpu = new Cpu();
                    cpu.setID(id);
                    cpu.setMarca(marca);
                    cpu.setModello(modello);
                    cpu.setPrezzo(prezzo);
                    cpu.setQuantita(quantita);
                    cpu.setWattaggio(wattaggio);
                    cpu.setFrequenza(frequenza);
                    cpu.setN_Core(n_Core);
                    cpu.setUrl(url);
                    cpu.setDescrizione(desc);
                    CpuDAO.Update(cpu);
                    break;
                case "CASE":
                    Case case_ = new Case();
                    case_.setID(id);
                    case_.setMarca(marca);
                    case_.setModello(modello);
                    case_.setPrezzo(prezzo);
                    case_.setQuantita(quantita);
                    case_.setFormaMobo(formaMobo);
                    case_.setUrl(url);
                    case_.setDescrizione(desc);
                    CaseDAO.Update(case_);
                    break;
                case "DISSIPATORE":
                    Dissipatore diss = new Dissipatore();
                    diss.setID(id);
                    diss.setMarca(marca);
                    diss.setModello(modello);
                    diss.setPrezzo(prezzo);
                    diss.setQuantita(quantita);
                    diss.setUrl(url);
                    diss.setDescrizione(desc);
                    DissipatoreDAO.Update(diss);
                    break;
                case "PSU":
                    Psu psu = new Psu();
                    psu.setID(id);
                    psu.setMarca(marca);
                    psu.setModello(modello);
                    psu.setPrezzo(prezzo);
                    psu.setQuantita(quantita);
                    psu.setN_Watt(wattaggio);
                    psu.setUrl(url);
                    psu.setDescrizione(desc);
                    PsuDAO.Update(psu);
                    break;
                case "MOBO":
                    Mobo mobo = new Mobo();
                    mobo.setID(id);
                    mobo.setMarca(marca);
                    mobo.setModello(modello);
                    mobo.setPrezzo(prezzo);
                    mobo.setQuantita(quantita);
                    mobo.setForma(formaMobo);
                    mobo.setN_RAM(n_Ram);
                    mobo.setN_USB(n_Usb);
                    mobo.setN_PCI(n_Pci);
                    mobo.setUrl(url);
                    mobo.setDescrizione(desc);
                    MoboDAO.Update(mobo);
                    break;
                case "RAM":
                    Ram ram = new Ram();
                    ram.setID(id);
                    ram.setMarca(marca);
                    ram.setModello(modello);
                    ram.setPrezzo(prezzo);
                    ram.setQuantita(quantita);
                    ram.setFrequenza(frequenza);
                    ram.setUrl(url);
                    ram.setDescrizione(desc);
                    RamDAO.Update(ram);
                    break;
                case "HDD":
                    Hdd hdd = new Hdd();
                    hdd.setID(id);
                    hdd.setMarca(marca);
                    hdd.setModello(modello);
                    hdd.setPrezzo(prezzo);
                    hdd.setQuantita(quantita);
                    hdd.setMBs(mBs);
                    hdd.setUrl(url);
                    hdd.setDescrizione(desc);
                    ArchivioDatiDAO.Update(hdd);
                    break;
                case "SSD":
                    Ssd ssd = new Ssd();
                    ssd.setID(id);
                    ssd.setMarca(marca);
                    ssd.setModello(modello);
                    ssd.setPrezzo(prezzo);
                    ssd.setQuantita(quantita);
                    ssd.setMBs(mBs);
                    ssd.setUrl(url);
                    ssd.setDescrizione(desc);
                    ArchivioDatiDAO.Update(ssd);
                    break;
                case "GPU":
                    Gpu gpu = new Gpu();
                    gpu.setID(id);
                    gpu.setMarca(marca);
                    gpu.setModello(modello);
                    gpu.setPrezzo(prezzo);
                    gpu.setQuantita(quantita);
                    gpu.setWattaggio(wattaggio);
                    gpu.setFrequenza(frequenza);
                    gpu.setVRam(vram);
                    gpu.setUrl(url);
                    gpu.setDescrizione(desc);
                    GpuDAO.Update(gpu);
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
