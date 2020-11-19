package assignment.virtualmedicalhome.vmh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/index.html")
    public String indexMapping() {
        return "index";
    }

    @RequestMapping("/Admin")
    public String AdminMapping() {
        return "AdminLogin";
    }

    @RequestMapping("/Patient")
    public String PatientMapping() {
        return "PatientLogin";
    }

    @RequestMapping("/Doctor")
    public String DoctorMapping() {
        return "DoctorLogin";
    }

    @RequestMapping("/patienthomepage")
    public String patientpageMapping(){
        return "patienthomepage";
    }

    @RequestMapping("/AdminHomePage")
    public String AdminHomePage() {
        return "NewAdminPage";
    }


    @RequestMapping("/SearchResults")
    public String SearchResultsMapping(){  return "SearchResults"; }


    @RequestMapping("/Doctorhomepage")
    public String DoctorpageMapping(){return "Doctorhomepage";}

    @RequestMapping("/DoctorPendingApnt")
    public String DoctorPendingApntMapping(){return "DoctorPendingApnt";}

    @RequestMapping("/dummy")
    public String dummy(){return "dummy";}

}
